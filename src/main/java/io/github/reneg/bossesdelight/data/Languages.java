package io.github.reneg.bossesdelight.data;

import io.github.reneg.bossesdelight.api.annotation.ItemData;
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

    }

    private void addCreativeModeTabs() {

    }


    private void addElements() {
    }

    private void addEffects() {

    }

    private void addItems() throws IllegalAccessException {
//        Class<BakeriesItems> _class = BakeriesItems.class;
//        for (Field field : _class.getDeclaredFields()) {
//            if (field.isAnnotationPresent(ItemData.class)) {
//                Object object = field.get(null);
//                DeferredItem<?> deferredItem = null;
//                if (object instanceof DeferredItem<?>) {
//                    deferredItem = (DeferredItem<?>) object;
//                }
//                if (deferredItem != null) {
//                    ItemData itemData = field.getAnnotation(ItemData.class);
//                    if (itemData != null) {
//                        String zh = itemData.zhCn();
//                        String en = itemData.enUs();
//                        if (itemData.itemType() == ItemData.ItemType.ITEM) {
//                            if (en.isEmpty()) {
//                                addItem(deferredItem, zh);
//                            } else {
//                                addItem(deferredItem, en, zh);
//                            }
//                        }
//                        if (itemData.itemType() == ItemData.ItemType.BLOCK) {
//                            Item item = deferredItem.get();
//                            if (item instanceof BlockItem blockItem) {
//                                addBlock(blockItem::getBlock, en, zh);
//                            } else {
//                                throw new IllegalStateException("Field <<<" + field.getName() + ">>> is annotated as BLOCK but is not a BlockItem!");
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }

    private void addBlocks(){
        add("block.bakeries.salt_water_block","Salt Water","盐水");

    }

    private void addFluidTypes(){
        add("fluid_type.bakeries.salt_water","Salt Water","盐水");
    }

    private void addEntity(){
        add("entity.minecraft.villager.bakeries.pistrinamaster","Pistrina Master","面包师");
    }

    private void addAdvancements(){
        // 根进度
        addAdvancement("root", translateText("Bakery", "烘焙坊"), translateText("Fine baking.", "精致烘焙。"));
        addAdvancement("sieving", translateText("Sieving", "唰唰唰!!"), translateText("Get a flour sieve.", "获得一个面粉筛。"));
        addAdvancement("start_the_experiment", translateText("Start The Experiment", "开始实验吧"), translateText("Get a Fermentation Tank.", "获得一个发酵罐"));
        addAdvancement("natural_activity", translateText("Natural Activity", "天然活性"), translateText("Use the bottle to scoop out the natural yeast from the fermenter.", "用瓶子舀出发酵罐里的天然酵母。"));
        addAdvancement("precise_temperature_control", translateText("Precise Temperature Control", "精确控温"), translateText("In the oven", "使用烤箱。"));
        addAdvancement("perfect_temperature", translateText("Perfect Temperature", "完美温度"), translateText("With a more precise temperature, the food will be more delicious.", "用更加精准的温度烤制，食物会更加可口。"));
        addAdvancement("rough_flavor", translateText("Rough Flavor", "粗犷风味"), translateText("Wheat is used to make whole wheat flour.", "用小麦制作全麦面粉。"));
        addAdvancement("my_stomach_is_hungry_too", translateText("My Stomach Is Hungry Too", "我的肚子也饿了"), translateText("Make a baked good.", "制作一个烘焙食品。"));
        addAdvancement("it.s.bread", translateText("It s Bread", "是个面包"), translateText("I've eaten ten different kinds of bread!", "食用过十种不同的面包！"));
        addAdvancement("mix_well", translateText("Mix Well", "搅拌均匀"), translateText("It's not Mixin...", "这不是Mixin..."));
        addAdvancement("cheese_power", translateText("Cheese Power!", "芝士力!"), translateText("Only power. Cheese is power!", "只是力，芝士就是力量!"));
        addAdvancement("glossy_green", translateText("Glossy Green", "绿油油"), translateText("Get a bottle of olive oil.", "获得一瓶橄榄油。"));
        addAdvancement("immortalers_the_bakeries", translateText("Immortalers? The Bakeries!", "千古？烘焙坊！"), translateText("Take Sniffer and dig up coffee beans in the jungle biome.", "带着嗅探兽在丛林生物群系挖掘出咖啡豆。"));
        addAdvancement("very_hard", translateText("It can't be eaten. It can't be eaten at all", "不能吃根本不能吃"), translateText("Get an Eternal Baguette (Hide progress)", "获得一个永恒法棍（隐藏进度）"));
        addAdvancement("get_cream_cake", translateText("Cake! Eat Cake!", "蛋糕！吃下蛋糕！"), translateText("Get Cream Cake.", "获得一个奶油蛋糕。"));
        addAdvancement("get_sofa", translateText("Come on, sit down", "来啊,你坐啊"), translateText("Get Sofa.", "获得一个沙发。"));
        addAdvancement("get_pineapple_oil", translateText("“Ice and Fire”", "“冰火传说”"), translateText("Get Pineapple Oil.", "获得一个冰火菠萝油."));
        addAdvancement("get_flat_croissant",translateText("Don't！","压没得！"),translateText("Make flat croissants using a falling anvil.","使用下落的铁砧制作扁可颂。"));
        addAdvancement("get_taro",translateText("Meetion","芋见你"),translateText("Get Taro.","获得芋头。"));
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