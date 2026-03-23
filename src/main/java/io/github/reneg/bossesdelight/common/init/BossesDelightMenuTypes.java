package io.github.reneg.bossesdelight.common.init;

import io.github.reneg.BossesDelight;
import io.github.reneg.bossesdelight.common.client.gui.SoulCookingPotMenu;
import io.github.reneg.bossesdelight.common.client.gui.SoulCookingPotScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;

import java.util.function.Supplier;
@EventBusSubscriber(value = Dist.CLIENT)
public class BossesDelightMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, BossesDelight.MODID);
    public static final Supplier<MenuType<SoulCookingPotMenu>> SOUL_COOKING_POT = MENU_TYPES.register("soul_cooking_pot",() -> IMenuTypeExtension.create(SoulCookingPotMenu::new));


    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(SOUL_COOKING_POT.get(), SoulCookingPotScreen::new);
    }
}
