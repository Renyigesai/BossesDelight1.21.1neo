package io.github.reneg;

import com.mojang.logging.LogUtils;
import io.github.reneg.bossesdelight.common.advancement.UseNectarJellyTrigger;
import io.github.reneg.bossesdelight.common.config.Config;
import io.github.reneg.bossesdelight.common.config.ServerConfig;
import io.github.reneg.bossesdelight.common.init.*;
import net.minecraft.advancements.CriteriaTriggers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BossesDelight.MODID)
public class BossesDelight {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "bosses_delight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BossesDelight(IEventBus modEventBus, ModContainer modContainer) {
        BossesDelightItems.ITEMS.register(modEventBus);
        BossesDelightBlock.BLOCKS.register(modEventBus);
        BossesDelightBlock.EntityTypes.TILES.register(modEventBus);
        BossesDelightEffects.EFFECTS.register(modEventBus);
        BossesDelightTabs.REGISTER.register(modEventBus);
        BossesDelightRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        BossesDelightRecipeTypes.RECIPE_TYPES.register(modEventBus);
        BossesDelightMenuTypes.MENU_TYPES.register(modEventBus);
        BossesDelightTriggers.TRIGGERS.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
        modContainer.registerConfig(ModConfig.Type.SERVER, ServerConfig.COMMON_SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }
}
