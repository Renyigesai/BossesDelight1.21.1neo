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

    private static final String ADVANCEMENT = "advancements.bosses_delight.";
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
        addToolTip("nectar_jelly","Right Click on consumed placeable food block to renew the consumed parts.","右击食用过的可放置的食物来补全消耗的部分。");
        addToolTip("obsidian_heart_knife","Apply Resistance Effect on you when holding, attack with stronger knock back to the target.","手持时获得抗性提升效果，攻击时可对目标造成更远的击退");
        addToolTip("void_thorn_knife","Apply Weakness Effect on the target on attack.","攻击时可对目标造成虚弱效果");
        addToolTip("anima_ice_drink","Add 1 level to a random effect you have after consuming (Up to %s).","饮用后随机将你的效果增加1级（至多%s级）");
        addToolTip("glare_stout","Disorganize all effects duration and level you have which level is lower than %s after consuming.","饮用后打乱你拥有的不大于%s级的药水效果时长和等级");

        addToolTip("gauntlet","It's a knife.","没错，这是一把刀！");
        addToolTip("gauntlet_1","Hold on right mouse to break and collect blocks.","长按右键使用来破坏并收集方块");
        addToolTip("gauntlet_2","Maybe you can use it to collect something which impossible to gain...","也许可以用来收集一些平时不可能获得的东西...");

        addFarmersdelightToolTip("anima_ice_drink","Add 1 level to a random effect you have after consuming.","饮用后随机将你的效果增加1级");
        addFarmersdelightToolTip("brimstone_nectar_tea","Apply random beneficial effect after consuming.","饮用后随机获得正面效果");
        addFarmersdelightToolTip("crystal_fruit_cider","Add 30 seconds duration to a random effect you have after consuming.","饮用后随机将你的效果增加30秒持续时间");
        addFarmersdelightToolTip("glare_stout","Disorganize all effects duration and level you have after consuming.","饮用后打乱你拥有的药水效果时长和等级");

        add("item_group.bosses_delight.bosses_delight","Bosses Delight","BOMD乐事");
        add("bosses_delight.container.cooking_pot.heated","Frosted","冻结中");
        add("bosses_delight.container.cooking_pot.not_heated","Needs cold block from below","下方需要低温方块");
        add("bosses_delight.container.cooking_pot.served_on","Served on: %s","需盛装于：%s");
        add("bosses_delight.tooltip.cooking_pot.empty","Empty","空");
        add("bosses_delight.tooltip.cooking_pot.single_serving","Holds 1 serving of:","装有1份：");
        add("bosses_delight.tooltip.cooking_pot.many_servings","Holds %s servings of:","装有%s份：");
        add("bosses_delight.container.soul_cooking_pot","Soul Cooking Pot","灵魂锅");
        add("bosses_delight.jei.soul_cooking","Soul Cooking","灵魂烹饪");

        add("effect.bosses_delight.last_stand.description", "You will keep at least 1 health after hurt by a lethal damage when your health is more than 1, and then gain resistance 5 effect according to the effect level.", "当你生命值大于1时受到致命伤害至少会保留1点生命值，而后根据药水等级获得抗性提升5的效果。");
        add("effect.bosses_delight.gauntlet_protection.description", "The damage you taken will be reduced if the damage source is not in front of you.", "受伤时如果伤害来源不在你面前，则降低你受到的伤害。");
        add("effect.bosses_delight.intangible.description", "Has chance to avoid damage, and always avoid damage from projectiles", "有概率规避伤害，完全规避弹射物伤害。");
        add("effect.bosses_delight.breakdown.description", "You can hit through the protection of Nether Gauntlet (including Gauntlet Protection effect)", "你可以击穿下界铁掌的防护(包括铁腕加护效果)");
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
        addAdvancement("root", translateText("Bosses Delight", "BOMD乐事"), translateText("Exploring These Danger Attractions.", "探索那些危险的诱惑"));
        addAdvancement("obsidian_glazed_dragon_head", translateText("Son Of Soul", "灵魂之子，浇给"), translateText("Cooking the dragon head and pour obsidian tear on that.", "烹饪末影龙的头，并淋上黑曜石浇汁"));
        addAdvancement("crystal_fruit_cube", translateText("Edible Cube", "可食用立方体"), translateText("Gain the Crystal Fruit Cube.", "制作晶果立方"));
        addAdvancement("ham_above_palm", translateText("Ham Above Palm", "掌上明“猪”"), translateText("Put a ham on the gauntlet, remember to recycle this container.", "制作掌上明猪腿，记得回收容器"));
        addAdvancement("lich_smoothies", translateText("Descent", "深呼吸，头晕是正常的"), translateText("Filling a skull with magic smoothies, don't curious about how it is cooked.", "用充满魔力的冰沙填满骷髅头！不要问是怎么用锅炖出来的"));
        addAdvancement("last_stand", translateText("Purple Mood", "紫色心情"), translateText("Consume a food which made of Obsidian Heart to get Last Stand effect.", "食用含有黑曜石之心的食物获得屹立不倒的效果"));
        addAdvancement("gauntlet_protection", translateText("Social Dexterity", "社交的手腕"), translateText("Consume a food which made of Blazing Eye to get Gauntlet Protection effect.", "食用含有烈焰之眼的食物获得铁腕加护的效果"));
        addAdvancement("intangible", translateText("Evader", "我是个闪避！"), translateText("Consume a food which made of Ancient Anima to get Intangible effect.", "食用含有古老的灵魂的食物获得无形的效果"));
        addAdvancement("gauntlet", translateText("Tangible Giant Hand", "有形的大手"), translateText("Gain the Gauntlet after you killed the Gauntlet", "击杀下界铁掌，从它留下的遗产中获得铁掌"));
        addAdvancement("bosses_hodgepodge", translateText("Put All Together", "传奇拼好饭"), translateText("You get the supreme meal now!", "制作一份BOMD乐事的终极料理"));
        addAdvancement("ancient_roll", translateText("Crunchy!", "嘎嘣脆！"), translateText("Obtain an Ancient Roll.", "获得残骸脆脆卷"));
        addAdvancement("obsidian_onion", translateText("Who is Cutting Obsidian?", "苦命鸳“洋”"), translateText("How are onions and obsidian connected together?", "洋葱？黑曜石？这两个怎么联系到一起的？"));
        addAdvancement("crystal_fruit_seeds", translateText("Jackpot!", "\u2B50\u2B50\u2B50\u2B50\u2B50\u2B50"), translateText("The seed of infinity fruits...", "无限晶果之种！"));
        addAdvancement("nectar_jelly", translateText("Feast Reviver", "重生的佳肴"), translateText("It's a Feature, not a Bug!", "这是特性，不是BUG！"));
        addAdvancement("magic_frozen_noodles", translateText("Tornoodle", "面条龙卷风"), translateText("Yes, those floating thing are noodles.", "是的，那些飘在空中的是面条。"));
        addAdvancement("soul_cooking_pot", translateText("Cooking Souls", "烹饪灵魂"), translateText("Get soul cooking out.", "获得灵魂锅。"));
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
        add(ADVANCEMENT + key + ".description",en_us,zh_cn);
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