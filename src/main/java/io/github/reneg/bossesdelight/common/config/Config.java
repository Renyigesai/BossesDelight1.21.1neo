package io.github.reneg.bossesdelight.common.config;

import io.github.reneg.BossesDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
@EventBusSubscriber(modid = BossesDelight.MODID)
public class Config {

    public static final ModConfigSpec COMMON_SPEC;

    public static ModConfigSpec.BooleanValue COLLECT_FEAST;
    public static ModConfigSpec.BooleanValue OBSIDIAN_ONION_CONVERTING;
    public static List<Block> CRYSTAL_FRUIT_PLANTABLE_BLOCKS, OBSIDIAN_ONION_PLANTABLE_BLOCKS;
    public static ModConfigSpec.IntValue GLARE_SODA_LEVEL;
    public static ModConfigSpec.IntValue ANIMA_DRINK_LEVEL;
    private static final ModConfigSpec.ConfigValue<List<? extends String>> CRYSTAL_FRUIT_WHITELIST_BLOCKS;
    private static final ModConfigSpec.ConfigValue<List<? extends String>> OBSIDIAN_ONION_WHITELIST_BLOCKS;

    static {
       ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();

        COMMON_BUILDER.push("Gauntlet Item");
        COLLECT_FEAST = COMMON_BUILDER.comment("If it's true, the Gauntlet Item will able to collect Feast Block even consumed.")
                .comment("Incontrovertibly it's a BUG! If you make this TRUE, it's no different from cheating.")
                .comment("I made this BUG to another item with that feature and make it using more reasonable.")
                .define("ShouldCollectFeastBlock", false);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Crystal Fruits");
        CRYSTAL_FRUIT_WHITELIST_BLOCKS = COMMON_BUILDER
                .comment("Define which blocks allow Crystal Fruits to plant on, leave space will use the default value.")
                .defineListAllowEmpty("CrystalFruitPlantableBlocks",
                        List.of("minecraft:bedrock", "minecraft:budding_amethyst"), Config::validateBlockName);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Obsidian Onions");
        OBSIDIAN_ONION_WHITELIST_BLOCKS = COMMON_BUILDER
                .comment("Define which blocks allow Obsidian Onions to plant on, leave space will use the default value.")
                .defineListAllowEmpty("ObsidianOnionPlantableBlocks",
                        List.of("minecraft:obsidian", "minecraft:crying_obsidian"), Config::validateBlockName);
        OBSIDIAN_ONION_CONVERTING = COMMON_BUILDER.comment("Define if Obsidian Onions converting planted blocks to Crying Obsidian.")
                .define("ShouldObsidianOnionConverting", true);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Glare Soda");
        GLARE_SODA_LEVEL = COMMON_BUILDER.comment("Modify the max effect level of Glare Soda that can be disorganized.")
                .defineInRange("DisorganizeMaxLevel", 5, 1, 255);
        COMMON_BUILDER.pop();


        COMMON_BUILDER.push("Anima Ice Drink");
        ANIMA_DRINK_LEVEL = COMMON_BUILDER.comment("Modify the max effect level of Glare Soda that can be upgraded to.")
                .defineInRange("UpgradeMaxLevel", 5, 1, 255);
        COMMON_BUILDER.pop();

        COMMON_SPEC = COMMON_BUILDER.build();
    }

    private static boolean validateBlockName(final Object obj)
    {
        return obj instanceof final String name && BuiltInRegistries.BLOCK.containsKey(ResourceLocation.parse(name));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        CRYSTAL_FRUIT_PLANTABLE_BLOCKS = CRYSTAL_FRUIT_WHITELIST_BLOCKS.get().stream()
                .map(name -> BuiltInRegistries.BLOCK.get(ResourceLocation.parse(name))).toList();
        OBSIDIAN_ONION_PLANTABLE_BLOCKS = OBSIDIAN_ONION_WHITELIST_BLOCKS.get().stream()
                .map(name -> BuiltInRegistries.BLOCK.get(ResourceLocation.parse(name))).toList();
    }
}