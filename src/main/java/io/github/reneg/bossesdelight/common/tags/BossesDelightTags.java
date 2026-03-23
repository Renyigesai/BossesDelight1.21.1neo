package io.github.reneg.bossesdelight.common.tags;

import io.github.reneg.BossesDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BossesDelightTags {
    public static final TagKey<Block> COLD_SOURCES = modBlockTag("cold_sources");
    public static final TagKey<Block> TRAY_COLD_SOURCES = modBlockTag("tray_cold_sources");

    private static TagKey<Block> modBlockTag(String path) {
        return BlockTags.create(ResourceLocation.fromNamespaceAndPath(BossesDelight.MODID, path));
    }
}