package io.github.reneg.bossesdelight.common.init;

import io.github.reneg.BossesDelight;
import io.github.reneg.bossesdelight.api.annotation.ItemData;
import io.github.reneg.bossesdelight.common.items.*;
import io.github.reneg.bossesdelight.common.tier.ItemTier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;

public class BossesDelightItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BossesDelight.MODID);

    @ItemData(zhCn = "铁掌",model = ItemData.ModelType.CUSTOM)
    public static final DeferredItem<Item> GAUNTLET;

    @ItemData(zhCn = "破碎的黑曜石之心")
    public static final DeferredItem<Item> BROKEN_OBSIDIAN_HEART;

    @ItemData(zhCn = "黑曜石秘制酱")
    public static final DeferredItem<Item> OBSIDIAN_SAUCE;

    @ItemData(zhCn = "黑曜石洋葱")
    public static final DeferredItem<Item> OBSIDIAN_ONION;

    @ItemData(zhCn = "黑曜石符文浓汤")
    public static final DeferredItem<Item> OBSIDIAN_RUNE_PUREE;

    @ItemData(zhCn = "黑曜石符文鸡尾酒")
    public static final DeferredItem<Item> OBSIDIAN_RUNE_COCKTAIL;

    @ItemData(zhCn = "黑曜浇汁龙首")
    public static final DeferredItem<Item> OBSIDIAN_GLAZED_DRAGON_HEAD;

    @ItemData(zhCn = "碗装黑曜浇汁龙首")
    public static final DeferredItem<Item> BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD;

    @ItemData(zhCn = "水晶果实切片")
    public static final DeferredItem<Item> CRYSTAL_FRUIT_SLICE;

    @ItemData(zhCn = "水晶果实种子")
    public static final DeferredItem<Item> CRYSTAL_FRUIT_SEEDS;

    @ItemData(zhCn = "水晶蛋糕杯")
    public static final DeferredItem<Item> CRYSTAL_CUP_CAKE;

    @ItemData(zhCn = "花簇沙拉")
    public static final DeferredItem<Item> BLOSSOM_SALAD;

    @ItemData(zhCn = "晶果立方")
    public static final DeferredItem<Item> CRYSTAL_FRUIT_CUBE;

    @ItemData(zhCn = "碗装晶果立方")
    public static final DeferredItem<Item> BOWL_OF_CRYSTAL_FRUIT_CUBE;

    @ItemData(zhCn = "水晶果酒")
    public static final DeferredItem<Item> CRYSTAL_FRUIT_CIDER;

    @ItemData(zhCn = "烈焰之眼碎片")
    public static final DeferredItem<Item> BLAZING_EYE_SHARDS;

    @ItemData(zhCn = "残骸脆脆卷")
    public static final DeferredItem<Item> ANCIENT_ROLL;

    @ItemData(zhCn = "仰望列空派")
    public static final DeferredItem<Item> BLAZING_EYE_PIE;

    @ItemData(zhCn = "仰望列空派切片")
    public static final DeferredItem<Item> BLAZING_EYE_PIE_SLICE;

    @ItemData(zhCn = "掌上明猪腿")
    public static final DeferredItem<Item> HAM_ABOVE_PALM;

    @ItemData(zhCn = "碗装掌上明猪腿")
    public static final DeferredItem<Item> BOWL_OF_HAM_ABOVE_PALM;

    @ItemData(zhCn = "怒目苏打")
    public static final DeferredItem<Item> GLARE_STOUT;

    @ItemData(zhCn = "灵魂冰饮")
    public static final DeferredItem<Item> ANIMA_ICE_DRINK;

    @ItemData(zhCn = "灵魂冰棒")
    public static final DeferredItem<Item> ANIMA_POPSICLE;

    @ItemData(zhCn = "巫妖冰沙")
    public static final DeferredItem<Item> LICH_SMOOTHIES;

    @ItemData(zhCn = "碗装巫妖冰沙")
    public static final DeferredItem<Item> BOWL_OF_LICH_SMOOTHIES;

    @ItemData(zhCn = "蕴魔冷炙意面")
    public static final DeferredItem<Item> MAGIC_FROZEN_NOODLES;

    @ItemData(zhCn = "碗装蕴魔冷炙意面")
    public static final DeferredItem<Item> BOWL_OF_MAGIC_FROZEN_NOODLES;

    @ItemData(zhCn = "祸乱大杂烩")
    public static final DeferredItem<Item> BOSSES_HODGEPODGE;

    @ItemData(zhCn = "硫磺花蜜茶")
    public static final DeferredItem<Item> BRIMSTONE_NECTAR_TEA;

    @ItemData(zhCn = "花蜜果冻冰")
    public static final DeferredItem<Item> NECTAR_JELLY;

    static {

        GAUNTLET = ITEMS.register("gauntlet", () -> new Gauntlet(ItemTier.GAUNTLET,defaultBuilder()));

        BROKEN_OBSIDIAN_HEART = food("broken_obsidian_heart",BossesDelightFoods.BROKEN_OBSIDIAN_HEART);
        OBSIDIAN_SAUCE = ITEMS.register("obsidian_sauce",()-> new ConsumableItem(bowlFoodBuilder().food(BossesDelightFoods.OBSIDIAN_SAUCE)));
        OBSIDIAN_ONION = ITEMS.register("obsidian_onion",()-> new TippedBlockItems(BossesDelightBlock.OBSIDIAN_ONION_BLOCK.get(), defaultBuilder().food(BossesDelightFoods.OBSIDIAN_ONION)));
        OBSIDIAN_RUNE_PUREE = ITEMS.register("obsidian_rune_puree",()-> new ConsumableItem(defaultBuilder().food(BossesDelightFoods.OBSIDIAN_RUNE_PUREE),true){
            @Override
            public UseAnim getUseAnimation(ItemStack p_41452_) {
                return UseAnim.DRINK;
            }
        });
        OBSIDIAN_RUNE_COCKTAIL = ITEMS.register("obsidian_rune_cocktail",()-> new DrinkableItem(defaultBuilder().food(BossesDelightFoods.OBSIDIAN_RUNE_COCKTAIL),true));
        OBSIDIAN_GLAZED_DRAGON_HEAD = ITEMS.register("obsidian_glazed_dragon_head",()-> new BlockItem(BossesDelightBlock.OBSIDIAN_GLAZED_DRAGON_HEAD_BLOCK.get(), defaultBuilder().stacksTo(1)));
        BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD = ITEMS.register("bowl_of_obsidian_glazed_dragon_head",()-> new ConsumableItem(bowlFoodBuilder().food(BossesDelightFoods.BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD)));
        CRYSTAL_FRUIT_SLICE = food("crystal_fruit_slice",BossesDelightFoods.CRYSTAL_FRUIT_SLICE);
        CRYSTAL_FRUIT_SEEDS = ITEMS.register("crystal_fruit_seeds", () -> new TippedBlockItems(BossesDelightBlock.CRYSTAL_FRUIT_BLOCK.get(),defaultBuilder()));
        CRYSTAL_CUP_CAKE = food("crystal_cup_cake",BossesDelightFoods.CRYSTAL_CUP_CAKE,true);
        BLOSSOM_SALAD = ITEMS.register("blossom_salad",()-> new ConsumableItem(defaultBuilder().food(BossesDelightFoods.BLOSSOM_SALAD).craftRemainder(Items.BOWL),true));
        CRYSTAL_FRUIT_CUBE = ITEMS.register("crystal_fruit_cube_block",()-> new ItemNameBlockItem(BossesDelightBlock.CRYSTAL_FRUIT_CUBE_BLOCK.get(),defaultBuilder().stacksTo(1)));
        BOWL_OF_CRYSTAL_FRUIT_CUBE = ITEMS.register("bowl_of_crystal_fruit_cube",()-> new ConsumableItem(bowlFoodBuilder().food(BossesDelightFoods.BOWL_OF_CRYSTAL_FRUIT_CUBE)));
        CRYSTAL_FRUIT_CIDER = ITEMS.register("crystal_fruit_cider",()-> new CrystalFruitCiderItem(defaultBuilder().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
        BLAZING_EYE_SHARDS = food("blazing_eye_shards",BossesDelightFoods.BLAZING_EYE_SHARDS);
        ANCIENT_ROLL = food("ancient_roll",BossesDelightFoods.ANCIENT_ROLL);
        BLAZING_EYE_PIE = ITEMS.register("blazing_eye_pie",()-> new ItemNameBlockItem(BossesDelightBlock.BLAZING_EYE_PIE_BLOCK.get(),defaultBuilder().stacksTo(1)));
        BLAZING_EYE_PIE_SLICE = food("blazing_eye_pie_slice",BossesDelightFoods.BLAZING_EYE_PIE_SLICE,true);
        HAM_ABOVE_PALM = ITEMS.register("ham_above_palm",()-> new ItemNameBlockItem(BossesDelightBlock.HAM_ABOVE_PALM_BLOCK.get(),defaultBuilder().stacksTo(1)));
        BOWL_OF_HAM_ABOVE_PALM = ITEMS.register("bowl_of_ham_above_palm",()-> new ConsumableItem(bowlFoodBuilder().food(BossesDelightFoods.BOWL_OF_HAM_ABOVE_PALM),true));
        GLARE_STOUT = ITEMS.register("glare_stout", () -> new GlareStoutItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
        ANIMA_ICE_DRINK = ITEMS.register("anima_ice_drink", () -> new AnimaIceDrinkItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
        ANIMA_POPSICLE = food("anima_popsicle",BossesDelightFoods.ANIMA_POPSICLE,true);
        LICH_SMOOTHIES = ITEMS.register("lich_smoothies_block", () -> new ItemNameBlockItem(BossesDelightBlock.LICH_SMOOTHIES_BLOCK.get(), defaultBuilder().stacksTo(1)));
        BOWL_OF_LICH_SMOOTHIES = ITEMS.register("bowl_of_lich_smoothies",()-> new ConsumableItem(bowlFoodBuilder().food(BossesDelightFoods.BOWL_OF_LICH_SMOOTHIES),true));
        MAGIC_FROZEN_NOODLES = ITEMS.register("magic_frozen_noodles_block", () -> new ItemNameBlockItem(BossesDelightBlock.MAGIC_FROZEN_NOODLES_BLOCK.get(), defaultBuilder().stacksTo(1)));
        BOWL_OF_MAGIC_FROZEN_NOODLES = ITEMS.register("bowl_of_magic_frozen_noodles", () -> new ConsumableItem(bowlFoodBuilder().food(BossesDelightFoods.BOWL_OF_MAGIC_FROZEN_NOODLES),true));
        BOSSES_HODGEPODGE = ITEMS.register("bosses_hodgepodge", ()-> new ConsumableItem(bowlFoodBuilder().food(BossesDelightFoods.BOSSES_HODGEPODGE),true));
        BRIMSTONE_NECTAR_TEA = ITEMS.register("brimstone_nectar_tea", () -> new BrimstoneNectarTeaItem(new Item.Properties().craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
        NECTAR_JELLY = ITEMS.register("nectar_jelly", () -> new NectarJellyItem(defaultBuilder().food(BossesDelightFoods.NECTAR_JELLY)));
    }

    private static DeferredItem<Item> food(String name, FoodProperties foodProperties){
        return ITEMS.register(name,()-> new Item(defaultBuilder().food(foodProperties)));
    }

    private static DeferredItem<Item> food(String name, FoodProperties foodProperties,boolean isEffect){
        return ITEMS.register(name,()-> new ConsumableItem(defaultBuilder().food(foodProperties),isEffect));
    }

    private static Item.Properties defaultBuilder() {
        return new Item.Properties();
    }

    private static Item.Properties bowlFoodBuilder() {
        return new Item.Properties().craftRemainder(Items.BOWL).stacksTo(16);
    }

}
