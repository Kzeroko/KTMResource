package net.kzeroko.ktmresource.recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.mojang.datafixers.util.Pair;
import net.kzeroko.ktmresource.init.KtmRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class ForgingRecipe implements IForgingRecipe {
    private final ResourceLocation recipeId;
    private final NonNullList<Ingredient> catalysts;
    private final NonNullList<Ingredient> recipeGems;
    private final NonNullList<Pair<Enchantment,Integer>> enchantments;
    private final ItemStack result;
    public Integer forgingTime;

    public ForgingRecipe(ResourceLocation recipeId, NonNullList<Ingredient> catalysts, NonNullList<Ingredient> recipeGems, NonNullList<Pair<Enchantment,Integer>> enchantments, ItemStack result, Integer forgingTime) {
        this.recipeId = recipeId;
        this.catalysts = catalysts;
        this.recipeGems = recipeGems;
        this.enchantments = enchantments;
        this.result = result;
        this.forgingTime = forgingTime;
    }

    /**
     * used when tick() works to extract valid recipes
     * @param inv :
     */
    public boolean matches(Container inv, Level worldIn) {
        // TBD
        // this.catalyst.test(inv.getStackInSlot(9)); // what does this test do?

        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;

        // check materials
        for(int j = 0; j < 5; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }
        boolean gemsSzMatches = i == this.recipeGems.size(); // ? what things this inv holds?
        boolean gemsItemsMatches = net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  this.recipeGems) != null;

        i = 0;
        inputs.clear();
        // check fuels
        for(int j = 5; j < 8; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }
        boolean fuelsSzMatches = i == this.catalysts.size();
        boolean fuelsItemsMatches = net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  this.catalysts) != null;

        return  gemsSzMatches && fuelsSzMatches && gemsItemsMatches && fuelsItemsMatches;
    }

    public ItemStack getDefaultedOutput() {
        ItemStack itemstack = this.result.copy();

        for (Pair<Enchantment, Integer> enchantment : this.enchantments) {
            itemstack.enchant(enchantment.getFirst(), enchantment.getSecond());
        }

        return itemstack;
    }

    // TBD
    @Override
    public ItemStack assemble(Container inv) {
        ItemStack itemstack = this.result.copy();
        CompoundTag compoundnbt = inv.getItem(0).getTag();
        if (compoundnbt != null) {
            itemstack.setTag(compoundnbt.copy());
        }

        for (Pair<Enchantment, Integer> enchantment : this.enchantments) {
            itemstack.enchant(enchantment.getFirst(), enchantment.getSecond());
        }

        return itemstack;
    }

    @Override
    public ItemStack getResultItem() {
        return this.result;
    }

    // TBD
    // so does this take all gems and catalysts as the same?
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.addAll(this.recipeGems);
        return ingredients;
    }

    public NonNullList<Ingredient> getFuels() {
        NonNullList<Ingredient> fuels = NonNullList.create();
        fuels.addAll(this.catalysts);
        return fuels;
    }

    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return KtmRecipes.FORGING_RECIPE.get();
    }

    // wm?
    public static ItemStack deserializeItem(JsonObject object) {
        String s = GsonHelper.getAsString(object, "item");
        Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(s));
        if (item == null) {
            throw new JsonSyntaxException("Unknown item '" + s + "'");
        }
        if (object.has("data")) {
            throw new JsonParseException("Disallowed data tag found");
        } else {
            int i = GsonHelper.getAsInt(object, "count", 1);
            return net.minecraftforge.common.crafting.CraftingHelper.getItemStack(object, true);
        }
    }

    public static class ForgingRecipeType implements RecipeType<ForgingRecipe> {
        @Override
        public String toString() {
            return TYPE_ID.toString();
        }
    }

    public static class ForgingRecipeSerializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ForgingRecipe> {

        @Override
        public ForgingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            NonNullList<Ingredient> gemList = readIngredients(GsonHelper.getAsJsonArray(json, "gems"));
            // could they use the same procedure? seem ok since no specific codes were used.
            NonNullList<Ingredient> catalystList = readIngredients(GsonHelper.getAsJsonArray(json, "catalysts"));
            NonNullList<Pair<Enchantment,Integer>> enchantmentList = readEnchantments(GsonHelper.getAsJsonArray(json, "enchantments"));

            if (gemList.isEmpty()) {
                throw new JsonParseException("No gems for forging recipe");
            } else if (gemList.size() > 9) {
                throw new JsonParseException("Too many gems for forging recipe the max is 9");
            } else if (catalystList.isEmpty()) {
                throw new JsonParseException("No fuels for forging recipe");
            } else if (catalystList.size() > 3) {
                throw new JsonParseException("Too many fuels for forging recipe the max is 9");
            }{
                ItemStack result = ForgingRecipe.deserializeItem(GsonHelper.getAsJsonObject(json, "result"));
                Integer forgingTime = GsonHelper.getAsInt(json, "forgingTime");

                return new ForgingRecipe(recipeId, catalystList, gemList, enchantmentList, result, forgingTime);
            }
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray ingredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for(int i = 0; i < ingredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(ingredientArray.get(i));
                if (!ingredient.isEmpty()) {
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        private static NonNullList<Pair<Enchantment,Integer>> readEnchantments(JsonArray enchantmentArray) {
            NonNullList<Pair<Enchantment,Integer>> enchantments = NonNullList.create();

            for(int i = 0; i < enchantmentArray.size(); ++i) {
                Enchantment enchantment = parseEnchantment(GsonHelper.convertToJsonObject(enchantmentArray.get(i),"enchantment"));
                int lvl = parseEnchantmentLevel(GsonHelper.convertToJsonObject(enchantmentArray.get(i), "lvl"));

                enchantments.add(new Pair<>(enchantment, lvl));
            }

            return enchantments;
        }

        private static Enchantment parseEnchantment(JsonObject object) {
            if (object.isJsonArray()) {
                throw new JsonSyntaxException("Expected object to be a single Enchantment");
            }
            Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "enchantment")));

            if (enchantment == null) {
                throw new JsonSyntaxException("No valid Enchantment name supplied");
            }

            return enchantment;
        }

        private static int parseEnchantmentLevel(JsonObject object) {
            if (object.isJsonArray()) {
                throw new JsonSyntaxException("Expected object to be a single integer");
            }
            return GsonHelper.getAsInt(object, "lvl");
        }

        @Nullable
        @Override
        public ForgingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            // read materials
            int i = buffer.readVarInt();
            NonNullList<Ingredient> gemList = NonNullList.withSize(i, Ingredient.EMPTY);
            for(int j = 0; j < gemList.size(); j++) {
                gemList.set(j, Ingredient.fromNetwork(buffer));
            }

            // read fuels
            int l = buffer.readVarInt();
            NonNullList<Ingredient> catalystList = NonNullList.withSize(l, Ingredient.EMPTY);
            for(int j = 0; j < catalystList.size(); j++) {
                catalystList.set(j, Ingredient.fromNetwork(buffer));
            }

            // TBD
            // this part should be deleted
            // where does this saved?
            int k = buffer.readVarInt();
            NonNullList<Pair<Enchantment,Integer>> enchantmentList = NonNullList.create();

            for (int j = 0; j < k; j++) {
                Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(buffer.readResourceLocation());
                if (enchantment != null) {
                    enchantmentList.add(new Pair<>(enchantment, buffer.readVarInt()));
                }
            }

            ItemStack result = buffer.readItem();
            Integer forgingTime = buffer.readInt();

            return new ForgingRecipe(recipeId, catalystList, gemList, enchantmentList, result, forgingTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ForgingRecipe recipe) {
            // save materials
            buffer.writeVarInt(recipe.recipeGems.size());
            for(Ingredient ingredient : recipe.recipeGems) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeVarInt(recipe.enchantments.size());
            for(Pair<Enchantment,Integer> enchantment : recipe.enchantments) {
                buffer.writeResourceLocation(enchantment.getFirst().getRegistryName());
                buffer.writeVarInt(enchantment.getSecond());
            }

            // save fuels
            buffer.writeVarInt(recipe.catalysts.size());
            for(Ingredient ingredient : recipe.catalysts) {
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(recipe.result);

            buffer.writeInt(recipe.forgingTime);
        }
    }
}
