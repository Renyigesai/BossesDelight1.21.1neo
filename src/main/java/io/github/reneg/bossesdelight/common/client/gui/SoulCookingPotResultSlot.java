//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.github.reneg.bossesdelight.common.client.gui;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.github.reneg.bossesdelight.common.blocks.soul_cooking.SoulCookingPotBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;

@ParametersAreNonnullByDefault
public class SoulCookingPotResultSlot extends SlotItemHandler {
    public final SoulCookingPotBlockEntity tileEntity;
    private final Player player;
    private int removeCount;

    public SoulCookingPotResultSlot(Player player, SoulCookingPotBlockEntity tile, IItemHandler inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
        this.tileEntity = tile;
        this.player = player;
    }

    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    @Nonnull
    public ItemStack remove(int amount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(amount, this.getItem().getCount());
        }

        return super.remove(amount);
    }

    public void onTake(Player thePlayer, ItemStack stack) {
        this.checkTakeAchievements(stack);
        super.onTake(thePlayer, stack);
    }

    protected void onQuickCraft(ItemStack stack, int amount) {
        this.removeCount += amount;
        this.checkTakeAchievements(stack);
    }

    protected void checkTakeAchievements(ItemStack stack) {
        stack.onCraftedBy(this.player.level(), this.player, this.removeCount);
        if (!this.player.level().isClientSide) {
            this.tileEntity.awardUsedRecipes(this.player, this.tileEntity.getDroppableInventory());
        }

        this.removeCount = 0;
    }
}
