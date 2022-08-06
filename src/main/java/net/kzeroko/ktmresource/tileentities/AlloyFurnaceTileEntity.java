/*
 * Copyright 2021 Infernal Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.kzeroko.ktmresource.tileentities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.kzeroko.ktmresource.KTMResource;
import net.kzeroko.ktmresource.blocks.AlloyFurnaceBlock;
import net.kzeroko.ktmresource.containers.AlloyFurnaceContainer;
import net.kzeroko.ktmresource.init.KTMPRRecipes;
import net.kzeroko.ktmresource.init.KTMPRSounds;
import net.kzeroko.ktmresource.init.KTMPRTags;
import net.kzeroko.ktmresource.init.KTMPRTileEntityTypes;
import net.kzeroko.ktmresource.recipes.ForgingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import static net.minecraft.util.Mth.ceil;


public class AlloyFurnaceTileEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeHolder, StackedContentsCompatible {
    private final int FORGE_PROGRESS_TOTAL = 100;

    @Nullable
    protected Component customName;
    private final ItemStackHandler inventory = new ItemStackHandler(10){
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            setChanged();
        }
    };

    private final Object2IntOpenHashMap<ResourceLocation> recipes = new Object2IntOpenHashMap<>();
    private int forgeTime = 0;
    private int forgeProgress = 0;

    private boolean forgeActive;
    private boolean recipeValid;

    //* used for transferring useful values
    // I know this is sloppy, but Containers can only track Int Arrays
    protected final ContainerData forgeData = new ContainerData() {
        public int get(int index) {
            switch(index) {
                case 0:
                    return AlloyFurnaceTileEntity.this.forgeActive ? 1 : 0;
                case 1:
                    return AlloyFurnaceTileEntity.this.recipeValid ? 1 : 0;
                case 2:
                    return AlloyFurnaceTileEntity.this.forgeProgress;
                case 3:
                    return AlloyFurnaceTileEntity.this.FORGE_PROGRESS_TOTAL;
                default:
                    return 0;
            }
        }

        public void set(int index, int k) {
            switch(index) {
                case 0:
                    AlloyFurnaceTileEntity.this.forgeActive = k == 1;
                    if (!AlloyFurnaceTileEntity.this.level.isClientSide()) {
                        AlloyFurnaceTileEntity.this.level.playSound(null, AlloyFurnaceTileEntity.this.worldPosition, KTMPRSounds.ALLOY_FURNACE_COOK.get(), SoundSource.BLOCKS, 1.0F, AlloyFurnaceTileEntity.this.level.getRandom().nextFloat() * 0.4F + 1.0F);
                    }
                    break;
                case 1:
                    AlloyFurnaceTileEntity.this.recipeValid = k == 1;
                case 2:
                    AlloyFurnaceTileEntity.this.forgeProgress = k;
            }
        }

        public int getCount() {
            return 4;
        }
    };;

    // TBD
    private static final int[] SLOTS_UP = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
    private static final int[] SLOTS_DOWN = new int[]{9};
    private static final int[] SLOTS_HORIZONTAL = new int[]{9}; // why 9 still?

    public AlloyFurnaceTileEntity(BlockPos pos, BlockState state) {
        super(KTMPRTileEntityTypes.ALLOY_FURNACE_TILE_ENTITY.get(), pos, state);
    }

    public static void tickForge(Level level, BlockPos pos, BlockState state, AlloyFurnaceTileEntity AlloyFurnace) {
        boolean flag = AlloyFurnace.isForging();
        boolean flag1 = false;
        if (AlloyFurnace.isForging()) {
            ++AlloyFurnace.forgeTime;
        }

        // TBD
        // happened on server??
        if (!level.isClientSide) {
            // wm this here? this.inv? why not transfer this.inv here but this? => 1. this.inv has a diff type.
            ForgingRecipe recipe = level.getRecipeManager().getRecipeFor(KTMPRRecipes.FORGING_RECIPE_TYPE, AlloyFurnace, level).orElse(null);
            // handled null recipe already
            AlloyFurnace.recipeValid = AlloyFurnace.canForge(recipe);

            if (AlloyFurnace.recipeValid && AlloyFurnace.forgeActive && AlloyFurnace.getItem(9).isEmpty()) {
                ++AlloyFurnace.forgeTime;

                double multiCoeff = 1.0;
                if (!AlloyFurnace.inventory.getStackInSlot(8).isEmpty()) {
                    multiCoeff = AlloyFurnace.loadActivatorCoeffs(AlloyFurnace.getItem(8).serializeNBT().getString("id"));
                }
                AlloyFurnace.forgeProgress = ceil(100 * multiCoeff * AlloyFurnace.forgeTime / recipe.forgingTime);

                if (multiCoeff * AlloyFurnace.forgeTime >= recipe.forgingTime ) {
                    AlloyFurnace.forgeTime = 0;
                    AlloyFurnace.forgeProgress = 0;
                    AlloyFurnace.forge(recipe);
                    flag1 = true;
                }
            } else {
                AlloyFurnace.forgeActive = false;
                AlloyFurnace.forgeTime = 0;
                AlloyFurnace.forgeProgress = 0;
            }

            // activated when forging_state_at_tick_start !=  forging_state_at_tick_end
            if (flag != AlloyFurnace.isForging()) {
                flag1 = true;
                level.setBlock(pos, state.setValue(AlloyFurnaceBlock.LIT, AlloyFurnace.isForging()), 3);
            }
        }

        if (flag1) {
            AlloyFurnace.setChanged();
        }
    }

    private boolean isForging() {
        return this.forgeProgress > 0;
    }

    // TBD
    // unused in 1.18 ??
//    @Nullable
//    public SUpdateTileEntityPacket getUpdatePacket() {
//        return new SUpdateTileEntityPacket(this.pos, 3, this.getUpdateTag());
//    }
//
//    public CompoundNBT getUpdateTag() {
//        return this.write(new CompoundNBT());
//    }

    @Override
    public void setCustomName(Component name) {
        this.customName = name;
    }

    // when were they used? but seems at least, write is used before read, then the setter is not them.
    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        inventory.deserializeNBT(nbt.getCompound("inv"));
        CompoundTag compoundnbt = nbt.getCompound("RecipesUsed");

        this.forgeActive = nbt.getBoolean("ForgeActive");
        this.recipeValid = nbt.getBoolean("RecipeValid");

        for(String s : compoundnbt.getAllKeys()) {
            this.recipes.put(new ResourceLocation(s), compoundnbt.getInt(s));
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.put("inv", inventory.serializeNBT());
        CompoundTag compoundnbt = new CompoundTag();
        this.recipes.forEach((recipeId, craftedAmount) -> {
            compoundnbt.putInt(recipeId.toString(), craftedAmount);
        });
        compound.put("RecipesUsed", compoundnbt);
        compound.putBoolean("ForgeActive", this.forgeActive);
        compound.putBoolean("RecipeValid", this.recipeValid);
    }

    // why menu contains container? This.inv means inv of this TileEntity.
    protected AbstractContainerMenu createMenu(int id, Inventory inv) {
        return new AlloyFurnaceContainer(id, inv, this.inventory, this.forgeData);
    }

    public Component getDisplayName() {
        return this.customName != null ? this.customName : new TranslatableComponent(KTMResource.MOD_ID + ':' + "container.alloy_furnace");
    }

    @Override
    protected Component getDefaultName() {
        return null;
    }

    // what's this recipeIn? => Only called by other functions
    protected boolean canForge(@Nullable ForgingRecipe recipeIn) {
        if (recipeIn != null) {
            ItemStack result = recipeIn.assemble(this);

            return !result.isEmpty();
        } else {
            return false;
        }
    }

    // done forging? when used? => only when tick() => recipe comes only from tick()
    private void forge(@Nullable ForgingRecipe recipe) {
        if (recipe != null && this.canForge(recipe)) {
            ItemStack result = recipe.assemble(this);

            this.inventory.setStackInSlot(9, result.copy());

            if (!this.level.isClientSide) {
                this.setRecipeUsed(recipe);
            }

            for(int i = 0; i < 9; ++i) {
                this.removeItem(i, 1);
            }

            this.forgeActive = false;

            if (!AlloyFurnaceTileEntity.this.level.isClientSide()) {
                AlloyFurnaceTileEntity.this.level.playSound(null, AlloyFurnaceTileEntity.this.worldPosition, SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.BLOCKS, 1.0F, AlloyFurnaceTileEntity.this.level.getRandom().nextFloat() * 0.8F + 0.25F);
            }
        }
    }

    // slot face?
    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return SLOTS_DOWN;
        } else {
            return side == Direction.UP ? SLOTS_UP : SLOTS_HORIZONTAL;
        }
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return this.canPlaceItem(index, itemStackIn);
    }

    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return !stack.is(KTMPRTags.Items.FORGING_MATERIALS) && !stack.is(KTMPRTags.Items.FORGING_FUELS);
    }

    public int getContainerSize() {
        return this.inventory.getSlots();
    }
    public boolean isEmpty() {
        for(int i = 0; i < 10; i++) {
            if (!this.inventory.getStackInSlot(i).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    // Returns the stack in the given slot.
    public ItemStack getItem(int index) {
        return this.inventory.getStackInSlot(index);
    }

    // Removes up to a specified number of items from an inventory slot and returns them in a new stack.
    public ItemStack removeItem(int index, int count) {
        return !this.inventory.getStackInSlot(index).isEmpty() && count > 0 ? this.inventory.getStackInSlot(index).split(count) : ItemStack.EMPTY;
    }

    // Removes a stack from the given slot and returns it.
    public ItemStack removeItemNoUpdate(int index) {
        ItemStack itemStack = this.inventory.getStackInSlot(index);
        this.inventory.setStackInSlot(index, ItemStack.EMPTY);
        return itemStack;
    }

    // Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
    public void setItem(int index, ItemStack stack) {
        this.inventory.setStackInSlot(index, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
        this.setChanged();
    }

    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            System.out.println(false);
            return false;
        } else {
            System.out.println(true);
            return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    public boolean canPlaceItem(int index, ItemStack stack) {
        if (this.getItem(index).getCount() != 0) {
            return false;
        }else{
            return true;
        }
        // TBD
//        else if (index < 9) {
//            return stack.is(MMTags.Items.GEMS);
//        } else {
//            return stack.is(MMTags.Items.CATALYSTS);
//        }
    }

    public double loadActivatorCoeffs(String activator_name) {
        String activators_path = "assets/ktmresource/forging_helper/forging_activators.json";

        BufferedReader bufferedReader  = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader()
                .getResourceAsStream(activators_path)), StandardCharsets.UTF_8));

        Type mapType = new TypeToken<Map<String, Double>>(){}.getType();
        Map<String, Double> activators = new Gson().fromJson(bufferedReader, mapType);

        if (!activators.containsKey(activator_name)){
            throw new RuntimeException("un assigned activator for forging");
        }else{
            double coeff = activators.get(activator_name);
            if (coeff <= 0){
                throw new RuntimeException("unsupported speeding up coefficient");
            }else {
                return coeff;
            }
        }
    }

    public void clearContent() {
        for(int i = 0; i < 10; i++) {
            this.inventory.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    // wm?
    public void setRecipeUsed(@Nullable Recipe<?> recipe) {
        if (recipe != null) {
            ResourceLocation resourcelocation = recipe.getId();
            this.recipes.addTo(resourcelocation, 1);
        }
    }

    @Nullable
    public Recipe<?> getRecipeUsed() {
        return null;
    }

    public void fillStackedContents(StackedContents helper) {
        for(int i = 0; i < 10; i++) {
            helper.accountStack(this.inventory.getStackInSlot(i));
        }
    }

    net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
            net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (int x = 0; x < handlers.length; x++)
            handlers[x].invalidate();
    }
}
