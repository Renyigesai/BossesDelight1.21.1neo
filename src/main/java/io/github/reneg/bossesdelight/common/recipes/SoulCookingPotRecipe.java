//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.github.reneg.bossesdelight.common.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

import io.github.reneg.bossesdelight.common.init.BossesDelightRecipeSerializers;
import io.github.reneg.bossesdelight.common.init.BossesDelightRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;

public class SoulCookingPotRecipe implements Recipe<RecipeWrapper> {
    public static final int INPUT_SLOTS = 6;
    private final String group;
    private final CookingPotRecipeBookTab tab;
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ItemStack container;
    private final ItemStack containerOverride;
    private final float experience;
    private final int cookTime;

    public SoulCookingPotRecipe(String group, @Nullable CookingPotRecipeBookTab tab, NonNullList<Ingredient> inputItems, ItemStack output, ItemStack container, float experience, int cookTime) {
        this.group = group;
        this.tab = tab;
        this.inputItems = inputItems;
        this.output = output;
        if (!container.isEmpty()) {
            this.container = container;
        } else if (!output.getCraftingRemainingItem().isEmpty()) {
            this.container = output.getCraftingRemainingItem();
        } else {
            this.container = ItemStack.EMPTY;
        }

        this.containerOverride = container;
        this.experience = experience;
        this.cookTime = cookTime;
    }

    public String getGroup() {
        return this.group;
    }

    @Nullable
    public CookingPotRecipeBookTab getRecipeBookTab() {
        return this.tab;
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.inputItems;
    }

    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.output;
    }

    public ItemStack getOutputContainer() {
        return this.container;
    }

    public ItemStack getContainerOverride() {
        return this.containerOverride;
    }

    public ItemStack assemble(RecipeWrapper inv, HolderLookup.Provider provider) {
        return this.output.copy();
    }

    public float getExperience() {
        return this.experience;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    public boolean matches(RecipeWrapper inv, Level level) {
        List<ItemStack> inputs = new ArrayList();
        int i = 0;

        for(int j = 0; j < 6; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }

        return i == this.inputItems.size() && RecipeMatcher.findMatches(inputs, this.inputItems) != null;
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.inputItems.size();
    }

    public RecipeSerializer<?> getSerializer() {
        return BossesDelightRecipeSerializers.SOUL_COOKING.get();
    }

    public RecipeType<?> getType() {
        return (RecipeType) BossesDelightRecipeTypes.SOUL_COOKING.get();
    }

    public ItemStack getToastSymbol() {
        return new ItemStack((ItemLike)ModItems.COOKING_POT.get());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            SoulCookingPotRecipe that = (SoulCookingPotRecipe)o;
            if (Float.compare(that.getExperience(), this.getExperience()) != 0) {
                return false;
            } else if (this.getCookTime() != that.getCookTime()) {
                return false;
            } else if (!this.getGroup().equals(that.getGroup())) {
                return false;
            } else if (this.tab != that.tab) {
                return false;
            } else if (!this.inputItems.equals(that.inputItems)) {
                return false;
            } else {
                return !this.output.equals(that.output) ? false : this.container.equals(that.container);
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.getGroup().hashCode();
        result = 31 * result + (this.getRecipeBookTab() != null ? this.getRecipeBookTab().hashCode() : 0);
        result = 31 * result + this.inputItems.hashCode();
        result = 31 * result + this.output.hashCode();
        result = 31 * result + this.container.hashCode();
        result = 31 * result + (this.getExperience() != 0.0F ? Float.floatToIntBits(this.getExperience()) : 0);
        result = 31 * result + this.getCookTime();
        return result;
    }

    public static class Serializer implements RecipeSerializer<SoulCookingPotRecipe> {
        private static final MapCodec<SoulCookingPotRecipe> CODEC = RecordCodecBuilder.mapCodec((inst) -> {
            return inst.group(Codec.STRING.optionalFieldOf("group", "").forGetter(SoulCookingPotRecipe::getGroup), CookingPotRecipeBookTab.CODEC.optionalFieldOf("recipe_book_tab", CookingPotRecipeBookTab.MISC).forGetter(SoulCookingPotRecipe::getRecipeBookTab), Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").xmap((ingredients) -> {
                NonNullList<Ingredient> nonNullList = NonNullList.create();
                nonNullList.addAll(ingredients);
                return nonNullList;
            }, (ingredients) -> {
                return ingredients;
            }).forGetter(SoulCookingPotRecipe::getIngredients), ItemStack.STRICT_CODEC.fieldOf("result").forGetter((r) -> {
                return r.output;
            }), ItemStack.STRICT_CODEC.optionalFieldOf("container", ItemStack.EMPTY).forGetter(SoulCookingPotRecipe::getContainerOverride), Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(SoulCookingPotRecipe::getExperience), Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(SoulCookingPotRecipe::getCookTime)).apply(inst, SoulCookingPotRecipe::new);
        });
        public static final StreamCodec<RegistryFriendlyByteBuf, SoulCookingPotRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        public Serializer() {
        }

        public MapCodec<SoulCookingPotRecipe> codec() {
            return CODEC;
        }

        public StreamCodec<RegistryFriendlyByteBuf, SoulCookingPotRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static SoulCookingPotRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String groupIn = buffer.readUtf();
            CookingPotRecipeBookTab tabIn = CookingPotRecipeBookTab.findByName(buffer.readUtf());
            int i = buffer.readVarInt();
            NonNullList<Ingredient> inputItemsIn = NonNullList.withSize(i, Ingredient.EMPTY);
            inputItemsIn.replaceAll((ignored) -> {
                return (Ingredient)Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
            });
            ItemStack outputIn = (ItemStack)ItemStack.STREAM_CODEC.decode(buffer);
            ItemStack container = (ItemStack)ItemStack.OPTIONAL_STREAM_CODEC.decode(buffer);
            float experienceIn = buffer.readFloat();
            int cookTimeIn = buffer.readVarInt();
            return new SoulCookingPotRecipe(groupIn, tabIn, inputItemsIn, outputIn, container, experienceIn, cookTimeIn);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, SoulCookingPotRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeUtf(recipe.tab != null ? recipe.tab.toString() : "");
            buffer.writeVarInt(recipe.inputItems.size());
            Iterator var2 = recipe.inputItems.iterator();

            while(var2.hasNext()) {
                Ingredient ingredient = (Ingredient)var2.next();
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.output);
            ItemStack.OPTIONAL_STREAM_CODEC.encode(buffer, recipe.container);
            buffer.writeFloat(recipe.experience);
            buffer.writeVarInt(recipe.cookTime);
        }
    }
}
