package io.github.reneg.bossesdelight.common.events;

import io.github.reneg.bossesdelight.common.init.BossesDelightBlock;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BossesDelightBlock.OBSIDIAN_GLAZED_DRAGON_HEAD_BLOCK.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BossesDelightBlock.OBSIDIAN_GLAZED_DRAGON_HEAD_BLOCK_LEGACY.get(), RenderType.cutout());
    }
}
