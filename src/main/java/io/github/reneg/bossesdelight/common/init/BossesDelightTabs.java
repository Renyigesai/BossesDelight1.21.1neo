package io.github.reneg.bossesdelight.common.init;

import io.github.reneg.BossesDelight;
import io.github.reneg.bossesdelight.api.annotation.ItemData;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.lang.reflect.Field;
import java.util.function.Supplier;


public class BossesDelightTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BossesDelight.MODID);

    public static final Supplier<CreativeModeTab> BOSSES_DELIGHT_TAB = REGISTER.register(
            "bosses_delight",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.bosses_delight.bosses_delight"))
                    .icon(BossesDelightItems.OBSIDIAN_RUNE_PUREE.get()::getDefaultInstance)
                    .displayItems((parameters, output) -> addCreativeModeTab(output,"bosses_delight",BossesDelightItems.class,true)).build());

    private static void addCreativeModeTab(CreativeModeTab.Output output,String tab,Class<?> items,boolean conditions){
        if (!conditions){
            return;
        }
        for (Field field : items.getDeclaredFields()){
            if (field.isAnnotationPresent(ItemData.class)){
                try {
                    Object object = field.get(null);
                    if (object instanceof DeferredItem<?> deferredItem){
                        ItemData annotation = field.getAnnotation(ItemData.class);
                        if (annotation != null && annotation.group().equals(tab)) {
                            output.accept(deferredItem.get());
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
