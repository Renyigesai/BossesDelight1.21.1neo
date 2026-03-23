package io.github.reneg.bossesdelight.common.items;

import io.github.reneg.BossesDelight;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.List;

public class TippedKnifeItems extends KnifeItem {


    public TippedKnifeItems(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext p_339594_, List<Component> tooltip, TooltipFlag p_41424_) {
        String string = "tooltip." + stack.getItem();
        String newString = string.replaceAll(":", ".");
        tooltip.add(Component.translatable(newString).withStyle(ChatFormatting.YELLOW));
    }
}
