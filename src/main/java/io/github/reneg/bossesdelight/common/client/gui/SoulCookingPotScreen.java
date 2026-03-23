//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.github.reneg.bossesdelight.common.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.github.reneg.BossesDelight;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.Configuration;

@ParametersAreNonnullByDefault
public class SoulCookingPotScreen extends AbstractContainerScreen<SoulCookingPotMenu> implements RecipeUpdateListener {
    private static final WidgetSprites RECIPE_BUTTON = new WidgetSprites(ResourceLocation.withDefaultNamespace("recipe_book/button"), ResourceLocation.withDefaultNamespace("recipe_book/button"));
    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(BossesDelight.MODID, "textures/gui/soul_cooking_pot.png");
    private static final Rectangle HEAT_ICON = new Rectangle(47, 55, 17, 15);
    private static final Rectangle PROGRESS_ARROW = new Rectangle(89, 25, 0, 17);
    private final SoulCookingPotRecipeBookComponent recipeBookComponent = new SoulCookingPotRecipeBookComponent();
    private boolean widthTooNarrow;

    public SoulCookingPotScreen(SoulCookingPotMenu screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
    }

    public void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.titleLabelX = 28;
        this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, (RecipeBookMenu)this.menu);
        this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        if ((Boolean)Configuration.ENABLE_RECIPE_BOOK_COOKING_POT.get()) {
            this.addRenderableWidget(new ImageButton(this.leftPos + 5, this.height / 2 - 49, 20, 18, RECIPE_BUTTON, (button) -> {
                this.recipeBookComponent.toggleVisibility();
                this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
                button.setPosition(this.leftPos + 5, this.height / 2 - 49);
            }));
        } else {
            this.recipeBookComponent.hide();
            this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        }

        this.addWidget(this.recipeBookComponent);
        this.setInitialFocus(this.recipeBookComponent);
    }

    protected void containerTick() {
        super.containerTick();
        this.recipeBookComponent.tick();
    }

    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTicks) {
        if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
            this.renderBackground(gui, mouseX, mouseY, partialTicks);
            this.recipeBookComponent.render(gui, mouseX, mouseY, partialTicks);
        } else {
            super.render(gui, mouseX, mouseY, partialTicks);
            this.recipeBookComponent.render(gui, mouseX, mouseY, partialTicks);
            this.recipeBookComponent.renderGhostRecipe(gui, this.leftPos, this.topPos, false, partialTicks);
        }

        this.renderMealDisplayTooltip(gui, mouseX, mouseY);
        this.renderHeatIndicatorTooltip(gui, mouseX, mouseY);
        this.recipeBookComponent.renderTooltip(gui, this.leftPos, this.topPos, mouseX, mouseY);
    }

    private void renderHeatIndicatorTooltip(GuiGraphics gui, int mouseX, int mouseY) {
        if (this.isHovering(HEAT_ICON.x, HEAT_ICON.y, HEAT_ICON.width, HEAT_ICON.height, (double)mouseX, (double)mouseY)) {
            String key = "container.cooking_pot." + (this.menu.isHeated() ? "heated" : "not_heated");
            gui.renderTooltip(this.font, Component.translatable(BossesDelight.MODID + "." + key), mouseX, mouseY);
        }

    }

    protected void renderMealDisplayTooltip(GuiGraphics gui, int mouseX, int mouseY) {
        if (this.minecraft != null && this.minecraft.player != null && this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
            if (this.hoveredSlot.index == 6) {
                List<Component> tooltip = new ArrayList();
                ItemStack mealStack = this.hoveredSlot.getItem();
                tooltip.add(((MutableComponent)mealStack.getItem().getDescription()).withStyle(mealStack.getRarity().getStyleModifier()));
                ItemStack containerStack = this.menu.blockEntity.getContainer();
                String container = !containerStack.isEmpty() ? containerStack.getItem().getDescription().getString() : "";
                tooltip.add(Component.translatable(BossesDelight.MODID + "." + "container.cooking_pot.served_on", new Object[]{container}).withStyle(ChatFormatting.GRAY));
                gui.renderComponentTooltip(this.font, tooltip, mouseX, mouseY);
            } else {
                gui.renderTooltip(this.font, this.hoveredSlot.getItem(), mouseX, mouseY);
            }
        }

    }

    protected void renderLabels(GuiGraphics gui, int mouseX, int mouseY) {
        super.renderLabels(gui, mouseX, mouseY);
        gui.drawString(this.font, this.playerInventoryTitle, 8, this.imageHeight - 96 + 2, 4210752, false);
    }

    protected void renderBg(GuiGraphics gui, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.minecraft != null) {
            gui.blit(BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
            if (this.menu.isHeated()) {
                gui.blit(BACKGROUND_TEXTURE, this.leftPos + HEAT_ICON.x, this.topPos + HEAT_ICON.y, 176, 0, HEAT_ICON.width, HEAT_ICON.height);
            }

            int l = this.menu.getCookProgressionScaled();
            gui.blit(BACKGROUND_TEXTURE, this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height);
        }
    }

    protected boolean isHovering(int x, int y, int width, int height, double mouseX, double mouseY) {
        return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(x, y, width, height, mouseX, mouseY);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int buttonId) {
        if (this.recipeBookComponent.mouseClicked(mouseX, mouseY, buttonId)) {
            this.setFocused(this.recipeBookComponent);
            return true;
        } else {
            return this.widthTooNarrow && this.recipeBookComponent.isVisible() || super.mouseClicked(mouseX, mouseY, buttonId);
        }
    }

    protected boolean hasClickedOutside(double mouseX, double mouseY, int x, int y, int buttonIdx) {
        boolean flag = mouseX < (double)x || mouseY < (double)y || mouseX >= (double)(x + this.imageWidth) || mouseY >= (double)(y + this.imageHeight);
        return flag && this.recipeBookComponent.hasClickedOutside(mouseX, mouseY, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, buttonIdx);
    }

    protected void slotClicked(Slot slot, int mouseX, int mouseY, ClickType clickType) {
        super.slotClicked(slot, mouseX, mouseY, clickType);
        this.recipeBookComponent.slotClicked(slot);
    }

    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }

    @Nonnull
    public RecipeBookComponent getRecipeBookComponent() {
        return this.recipeBookComponent;
    }
}
