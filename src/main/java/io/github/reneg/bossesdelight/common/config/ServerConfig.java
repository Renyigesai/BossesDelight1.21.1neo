package io.github.reneg.bossesdelight.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class ServerConfig {

    public static final ModConfigSpec COMMON_SPEC;

    public static final ModConfigSpec.ConfigValue<List<? extends String>> BLOCK_CLASSES;

    static {
        ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.push("Block Classes");
        BLOCK_CLASSES = COMMON_BUILDER
                .comment("List of fully qualified block class names that can be replaced with Nectar Jelly.\n"
                        + "Example: \"vectorwing.farmersdelight.common.block.PieBlock\"")
                .defineList("nectarReplaceableBlocks",
                        List.of(
                                "vectorwing.farmersdelight.common.block.PieBlock",
                                "io.github.reneg.bossesdelight.common.blocks.ObsidianGlazedDragonHeadBlock",
                                "vectorwing.farmersdelight.common.block.FeastBlock",
                                "net.minecraft.world.level.block.CakeBlock"
                        ),
                        () -> "",
                        obj -> obj instanceof String && ((String) obj).contains(".")
                );
        COMMON_BUILDER.pop();

        COMMON_SPEC = COMMON_BUILDER.build();
    }
}