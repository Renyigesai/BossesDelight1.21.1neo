package io.github.reneg.bossesdelight.data;

import io.github.reneg.bossesdelight.api.annotation.ItemData;
import io.github.reneg.bossesdelight.common.init.BossesDelightEffects;
import io.github.reneg.bossesdelight.common.init.BossesDelightItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@ParametersAreNonnullByDefault
public class Languages extends AbstractLanguageProvider {
    private final PackOutput output;
    private final String locale;

    private static final String ADVANCEMENT = "advancements.bakeries.";
    private static final String CATEGORIES = "guide.bakeries.categories.";
    private static final String PATCHOULI_ENTRIES = "guide.bakeries.entries.";
    private static final String PATCHOULI_DESCR = "guide.bakeries.descr.";

    public Languages(PackOutput output, String locale) {
        super(output, locale);
        this.output = output;
        this.locale = locale;
    }

    @Override
    protected void addTranslations() {
        addCreativeModeTabs();
        try {
            addItems();
        } catch (IllegalAccessException e) {
            LOGGER.error("Failed to access item fields", e);
            throw new RuntimeException("Failed to access item fields during language generation", e);
        }
        addBlocks();
        addFluidTypes();
        addElements();
        add();
        addEffects();
        addEntity();
        addAdvancements();
    }


    private void add() {
        addToolTip("obsidian_onion","Apply Resistance Effect on you when holding, attack with stronger knock back to the target.","只能种在黑曜石或哭泣的黑曜石上。");
        addToolTip("crystal_fruit_seeds","Can only be planted on Bedrock and Budding Amethyst.","只能种在基岩或紫水晶母岩上。");

        addFarmersdelightToolTip("anima_ice_drink","Add 1 level to a random effect you have after consuming.","饮用后随机将你的效果增加1级");
        addFarmersdelightToolTip("brimstone_nectar_te","Apply random beneficial effect after consuming.","饮用后随机获得正面效果");
        addFarmersdelightToolTip("crystal_fruit_cider","Add 30 seconds duration to a random effect you have after consuming.","饮用后随机将你的效果增加30秒持续时间");
        addFarmersdelightToolTip("glare_stout","Disorganize all effects duration and level you have after consuming.","饮用后打乱你拥有的药水效果时长和等级");

        add("item_group.bosses_delight.bosses_delight","Bosses Delight","BOMD乐事");
    }

    private void addCreativeModeTabs() {

    }


    private void addElements() {
    }

    private void addEffects() {
        addEffect(BossesDelightEffects.LAST_STAND::value,"Last Stand","屹立不倒");
        addEffect(BossesDelightEffects.BREAKDOWN::value,"Breakdown","瓦解");
        addEffect(BossesDelightEffects.GAUNTLET_PROTECTION::value,"Gauntlet Protectionn","铁腕加护");
        addEffect(BossesDelightEffects.INTANGIBLE::value,"Intangible","无形");
    }

    private void addItems() throws IllegalAccessException {
        Class<BossesDelightItems> _class = BossesDelightItems.class;
        for (Field field : _class.getDeclaredFields()) {
            if (field.isAnnotationPresent(ItemData.class)) {
                Object object = field.get(null);
                DeferredItem<?> deferredItem = null;
                if (object instanceof DeferredItem<?>) {
                    deferredItem = (DeferredItem<?>) object;
                }
                if (deferredItem != null) {
                    ItemData itemData = field.getAnnotation(ItemData.class);
                    if (itemData != null) {
                        String zh = itemData.zhCn();
                        String en = itemData.enUs();
                        if (itemData.itemType() == ItemData.ItemType.ITEM) {
                            if (en.isEmpty()) {
                                addItem(deferredItem, zh);
                            } else {
                                addItem(deferredItem, en, zh);
                            }
                        }
                        if (itemData.itemType() == ItemData.ItemType.BLOCK) {
                            Item item = deferredItem.get();
                            if (item instanceof BlockItem blockItem) {
                                addBlock(blockItem::getBlock, en, zh);
                            } else {
                                throw new IllegalStateException("Field <<<" + field.getName() + ">>> is annotated as BLOCK but is not a BlockItem!");
                            }
                        }
                    }
                }
            }
        }
    }

    private void addBlocks(){

    }

    private void addFluidTypes(){

    }

    private void addEntity(){

    }

    private void addAdvancements(){

    }
    //farmersdelight
    private void addToolTip(String name,String en_us,String zh_cn){
        add("tooltip.bosses_delight." + name,en_us,zh_cn);
    }

    private void addFarmersdelightToolTip(String name,String en_us,String zh_cn){
        add("farmersdelight.tooltip." + name,en_us,zh_cn);
    }

    private void addCategories(String key,String en_us,String zh_cn){
        add(CATEGORIES + key,en_us,zh_cn);
    }

    private void addPatchouliDescr(String key,String en_us,String zh_cn){
        add(PATCHOULI_DESCR + key,en_us,zh_cn);
    }

    private void addPatchouliEntries(String key,String en_us,String zh_cn){
        add(PATCHOULI_ENTRIES + key,en_us,zh_cn);
    }


    private void addDesc(String key,String en_us,String zh_cn){
        add(ADVANCEMENT + key + ".descr",en_us,zh_cn);
    }

    private void addTitle(String key,String en_us,String zh_cn){
        add(ADVANCEMENT + key + ".title",en_us,zh_cn);
    }

    private void addAdvancement(String name,List<String> title,List<String> desc){
        addTitle(name,title.get(0),title.get(1));
        addDesc(name,desc.get(0),desc.get(1));
    }

    public List<String> translateText(String en_us,String zh_cn){
        List<String> text = new ArrayList<>();
        text.add(en_us);
        text.add(zh_cn);
        return text;
    }
}