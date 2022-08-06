package net.kzeroko.ktmresource.client.integration.jei;

import com.mojang.datafixers.util.Pair;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.kzeroko.ktmresource.KTMResource;
import net.kzeroko.ktmresource.init.KTMPRBlocks;
import net.kzeroko.ktmresource.recipes.ForgingRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class AlloyFurnaceRecipeCategory implements IRecipeCategory<ForgingRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(KTMResource.MOD_ID, "jei_gemforge");
    private static final String TITLE_TRANSLATION_KEY = KTMResource.MOD_ID + ".jei.forging";

    private final Component titleTextComponent = new TranslatableComponent(TITLE_TRANSLATION_KEY);
    private final IDrawable background;
    private final IDrawable icon;

    public AlloyFurnaceRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(new ResourceLocation(KTMResource.MOD_ID, "textures/gui/container/alloy_furnace.png"), 0, 185, 151, 54);
        this.icon = guiHelper.createDrawableIngredient(new ItemStack(KTMPRBlocks.ALLOY_FURNACE.get().asItem()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends ForgingRecipe> getRecipeClass() {
        return ForgingRecipe.class;
    }

    @Override
    @Deprecated
    public Component getTitle() {
        return this.titleTextComponent;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setIngredients(ForgingRecipe recipe, IIngredients ingredients) {
        NonNullList<Ingredient> inputsAll = recipe.getIngredients();
        inputsAll.addAll(recipe.getFuels());

        ingredients.setInputIngredients(inputsAll);
        ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
    }

    // These are hard-coded, not generated, because they don't follow a simple pattern.
    @SuppressWarnings("unchecked")
    private static final Pair<Integer, Integer>[] SLOTS = new Pair[] {
        // Slots 1 - 5
        Pair.of(0, 0),
        Pair.of(18, 0),
        Pair.of(36, 0),
        Pair.of(54, 0),
        Pair.of(72, 0),
        // fuels slot
        Pair.of(0, 18),
        Pair.of(36, 18), //26
        Pair.of(72, 18),
        // Catalyst slot

        // Output slot
        Pair.of(128, 17),
    };

    // wm ingredients? where form? here are sth defined by JEI, but ingredients could be fetched in other ways.
    // does this apply all recipes at the same time??? No
    @Override
    public void setRecipe(IRecipeLayout recipeLayout, ForgingRecipe recipe, IIngredients ingredients) {
        IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
        // load recipe components
        NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
        NonNullList<Ingredient> fuels = recipe.getFuels();
        ItemStack output = recipe.getDefaultedOutput();

        // fetch and store ItemStack for JEI setting
        NonNullList<ItemStack> materialList = NonNullList.create();
        for (Ingredient e : recipeIngredients){
            materialList.add(e.getItems()[0]);
        }
        for (Ingredient e : fuels){
              materialList.add(e.getItems()[0]);
        }

        // set JEI showing recipes: materials and fuels
        for (int i = 0; i < recipeIngredients.size(); i++) {
            itemStacks.init(i, true, SLOTS[i].getFirst(), SLOTS[i].getSecond());  // ms position.
            itemStacks.set(i, materialList.get(i));
        }
        for (int i = 0; i < Math.min(fuels.size(), 3); i++) {
            itemStacks.init(i+5, true, SLOTS[i+5].getFirst(), SLOTS[i+5].getSecond());  // ms position.
            itemStacks.set(i+5, materialList.get(i+ recipeIngredients.size()));
        }

        // TBD
        // which ms no need to set for the output?
        itemStacks.init(9, false, 129, 18);
        itemStacks.set(9, output);
    }
}
