package io.github.reneg.bossesdelight.data;


import io.github.reneg.BossesDelight;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = BossesDelight.MODID)
public class DataGenerator {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        net.minecraft.data.DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();

        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
//        MSTagsProvider.Blocks blockTagsProvider = new MSTagsProvider.Blocks(output, provider, existingFileHelper);
//        generator.addProvider(events.includeServer(), new MSTagsProvider.Blocks(output, provider, existingFileHelper));
//        generator.addProvider(events.includeServer(), new MSTagsProvider.Items(
//                output, provider, blockTagsProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new ItemModel(output, existingFileHelper));
        generator.addProvider(event.includeClient(), new Languages(output, "en_us"));
        generator.addProvider(event.includeClient(), new Languages(output, "zh_cn"));
    }
}