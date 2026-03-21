package io.github.reneg.bossesdelight.common.items;

import io.github.reneg.BossesDelight;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class TippedBlockItems extends ItemNameBlockItem {
    public TippedBlockItems(Block block, Properties Properties) {
        super(block, Properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext p_339655_, List<Component> tooltip, TooltipFlag p_40575_) {
        String string = "tooltip." + stack.getItem();
        String newString = string.replaceAll(":", ".");
        tooltip.add(Component.translatable(newString).withStyle(ChatFormatting.YELLOW));
    }
}
