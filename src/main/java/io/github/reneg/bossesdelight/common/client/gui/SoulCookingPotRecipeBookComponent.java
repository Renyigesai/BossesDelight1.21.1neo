//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.github.reneg.bossesdelight.common.client.gui;

import java.util.List;
import javax.annotation.Nonnull;

import io.github.reneg.bossesdelight.common.recipes.SoulCookingPotRecipe;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.utility.TextUtils;

public class SoulCookingPotRecipeBookComponent extends RecipeBookComponent {
    protected static final WidgetSprites RECIPE_BOOK_BUTTONS = new WidgetSprites(ResourceLocation.fromNamespaceAndPath("farmersdelight", "recipe_book/cooking_pot_enabled"), ResourceLocation.fromNamespaceAndPath("farmersdelight", "recipe_book/cooking_pot_disabled"), ResourceLocation.fromNamespaceAndPath("farmersdelight", "recipe_book/cooking_pot_enabled_highlighted"), ResourceLocation.fromNamespaceAndPath("farmersdelight", "recipe_book/cooking_pot_disabled_highlighted"));

    public SoulCookingPotRecipeBookComponent() {
    }

    protected void initFilterButtonTextures() {
        this.filterButton.initTextureValues(RECIPE_BOOK_BUTTONS);
    }

    public void hide() {
        this.setVisible(false);
    }

    @Nonnull
    protected Component getRecipeFilterName() {
        return TextUtils.getTranslation("container.recipe_book.cookable", new Object[0]);
    }

    public void setupGhostRecipe(RecipeHolder<?> recipe, List<Slot> slots) {
        ItemStack resultStack = recipe.value().getResultItem(this.minecraft.level.registryAccess());
        this.ghostRecipe.setRecipe(recipe);
        if (((Slot)slots.get(6)).getItem().isEmpty()) {
            this.ghostRecipe.addIngredient(Ingredient.of(new ItemStack[]{resultStack}), ((Slot)slots.get(6)).x, ((Slot)slots.get(6)).y);
        }

        Recipe var5 = recipe.value();
        if (var5 instanceof SoulCookingPotRecipe cookingRecipe) {
            ItemStack containerStack = cookingRecipe.getOutputContainer();
            if (!containerStack.isEmpty()) {
                this.ghostRecipe.addIngredient(Ingredient.of(new ItemStack[]{containerStack}), ((Slot)slots.get(7)).x, ((Slot)slots.get(7)).y);
            }
        }

        this.placeRecipe(this.menu.getGridWidth(), this.menu.getGridHeight(), this.menu.getResultSlotIndex(), recipe, recipe.value().getIngredients().iterator(), 0);
    }
}
