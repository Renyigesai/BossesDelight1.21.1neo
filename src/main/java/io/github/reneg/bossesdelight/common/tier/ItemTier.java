package io.github.reneg.bossesdelight.common.tier;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class ItemTier {
    public static final Tier GAUNTLET = new Tier() {
        public int getUses() {
            return 2048;
        }

        public float getSpeed() {
            return 8.0F;
        }

        public float getAttackDamageBonus() {
            return 5.0F;
        }

        public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.NEEDS_DIAMOND_TOOL;
        }

        public int getEnchantmentValue() {
            return 15;
        }

        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(Items.NETHERITE_INGOT);
        }
    };

    public static final Tier  OBSIDIAN_HEART = new Tier() {
        public int getUses() {
            return 1024;
        }

        public float getSpeed() {
            return 8.0F;
        }

        public float getAttackDamageBonus() {
            return 4.0F;
        }

        public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.NEEDS_DIAMOND_TOOL;
        }

        public int getEnchantmentValue() {
            return 25;
        }

        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(Items.OBSIDIAN);
        }
    };

    public static final Tier  VOID_THORN = new Tier() {
        public int getUses() {
            return 768;
        }

        public float getSpeed() {
            return 12.0F;
        }

        public float getAttackDamageBonus() {
            return 2.0F;
        }

        public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
            return BlockTags.NEEDS_DIAMOND_TOOL;
        }

        public int getEnchantmentValue() {
            return 15;
        }

        public @NotNull Ingredient getRepairIngredient() {
            return Ingredient.of(Items.DRAGON_BREATH);
        }
    };
}
