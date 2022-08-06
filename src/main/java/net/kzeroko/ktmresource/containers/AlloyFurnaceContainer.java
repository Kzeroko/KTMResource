package net.kzeroko.ktmresource.containers;


import net.kzeroko.ktmresource.init.KTMPRContainerTypes;
import net.kzeroko.ktmresource.init.KTMPRTags;
import net.kzeroko.ktmresource.recipes.AlloyFurnaceServerRecipePlacer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class AlloyFurnaceContainer extends RecipeBookMenu<Container> {
    private final ItemStackHandler forgeInventory;
    protected final Level world;
    public ContainerData forgeData;

    public AlloyFurnaceContainer(int id, Inventory playerInventory) {
        this(id, playerInventory, new ItemStackHandler(10), new SimpleContainerData(4));
    }

    public AlloyFurnaceContainer(int id, Inventory playerInventory, ItemStackHandler inventory, ContainerData forgeData) {
        super(KTMPRContainerTypes.ALLOY_FURNACE_CONTAINER.get(), id);
        this.forgeData = forgeData;
        this.forgeInventory = inventory;
        this.world = playerInventory.player.level;

        // new materials slots
        this.addSlot(new MaterialsSlot(inventory, 0, 44, 17));
        this.addSlot(new MaterialsSlot(inventory, 1, 62, 17));
        this.addSlot(new MaterialsSlot(inventory, 2, 80, 17));
        this.addSlot(new MaterialsSlot(inventory, 3, 98, 17));
        this.addSlot(new MaterialsSlot(inventory, 4, 116, 17));
        // new fuels slots
        this.addSlot(new FuelsSlot(inventory, 5, 46, 43)); //35
        this.addSlot(new FuelsSlot(inventory, 6, 64, 43));
        this.addSlot(new FuelsSlot(inventory, 7, 82, 43));
        // new tools slot
        this.addSlot(new ActivatorsSlot(inventory, 8, 114, 43));
        // new output slot
        this.addSlot(new OutputsSlot(inventory, 9, 144, 43)); // 44,53

        // this is player's inventory
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // this is crafting table (did I see this?) or items on hand?
        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        this.addDataSlots(forgeData);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handlePlacement(boolean placeAll, Recipe<?> recipe, ServerPlayer player) {
        (new AlloyFurnaceServerRecipePlacer<>(this)).place(player, (Recipe<Container>) recipe, placeAll);
    }

    public boolean stillValid(Player playerIn) {
        return true;
    }

    public void setData(int id, int data) {
        super.setData(id, data);
        this.broadcastChanges();
    }

    public void fillCraftSlotsStackedContents(StackedContents itemHelperIn) {
        if (this.forgeInventory instanceof StackedContentsCompatible) {
            ((StackedContentsCompatible)this.forgeInventory).fillStackedContents(itemHelperIn);
        }
    }

    public void clearCraftingContent() {
        for(int i = 0; i < 10; i++) {
            this.forgeInventory.setStackInSlot(i, ItemStack.EMPTY);
        }
    }

    public boolean recipeMatches(Recipe<? super Container> recipeIn) {
        SimpleContainer inventory = new SimpleContainer(10);
        for(int i = 0; i < inventory.getContainerSize(); i++) {
            inventory.setItem(i, this.forgeInventory.getStackInSlot(i));
        }
        return recipeIn.matches(inventory, this.world);
    }

    public int getResultSlotIndex() {
        return 9;
    }

    public int getGridWidth() {
        return 4;
    }

    public int getGridHeight() {
        return 3;
    }

    @OnlyIn(Dist.CLIENT)
    public int getSize() {
        return 10;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isForgeActive() {
        return this.forgeData.get(0) == 1;
    }

    @OnlyIn(Dist.CLIENT)
    public int getForgeTimeScaled() {
        int i = this.forgeData.get(3);
        if (i == 0) {
            i = 200;
        }

        return this.forgeData.get(2) * 20 / i;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isRecipeValid() {
        return this.forgeData.get(1) == 1;
    }

    public void setForgeActive(boolean active) {
        this.forgeData.set(0, active ? 1 : 0);
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.valueOf("ALLOY_FURNACE");
    }

    // TBD
    // unused in 1.16?
    @Override
    public boolean shouldMoveToInventory(int slot) {
        return slot != this.getResultSlotIndex();
    }

    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < 10) {
                if (!this.moveItemStackTo(itemstack1, 10, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 10, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    class MaterialsSlot extends SlotItemHandler {
        public MaterialsSlot(ItemStackHandler inventoryIn, int index, int xIn, int yIn) {
            super(inventoryIn, index, xIn, yIn);
        }

        public boolean mayPlace(ItemStack stack) {
            return stack.is(KTMPRTags.Items.FORGING_MATERIALS);
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        @Override
        public int getMaxStackSize(@Nonnull ItemStack stack) {
            return 1;
        }
    }

    class FuelsSlot extends SlotItemHandler {
        public FuelsSlot(ItemStackHandler inventoryIn, int index, int xIn, int yIn) {
            super(inventoryIn, index, xIn, yIn);
        }

        public boolean mayPlace(ItemStack stack) {
            return stack.is(KTMPRTags.Items.FORGING_FUELS);
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        @Override
        public int getMaxStackSize(@Nonnull ItemStack stack) {
            return 1;
        }
    }

    class ActivatorsSlot extends SlotItemHandler {
        public ActivatorsSlot(ItemStackHandler inventoryIn, int index, int xIn, int yIn) {
            super(inventoryIn, index, xIn, yIn);
        }

        public boolean mayPlace(ItemStack stack) {
            return stack.is(KTMPRTags.Items.FORGING_ACTIVATORS);
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        @Override
        public int getMaxStackSize(@Nonnull ItemStack stack) {
            return 64;
        }
    }

    class OutputsSlot extends SlotItemHandler {
        public OutputsSlot(ItemStackHandler inventoryIn, int index, int xIn, int yIn) {
            super(inventoryIn, index, xIn, yIn);
        }

        public boolean mayPlace(ItemStack stack) {
            return stack.is(KTMPRTags.Items.FORGING_OUTPUTS);
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        @Override
        public int getMaxStackSize(@Nonnull ItemStack stack) {
            return 1;
        }
    }
}
