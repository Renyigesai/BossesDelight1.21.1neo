package io.github.reneg.bossesdelight.common.init;

import com.mojang.datafixers.types.Type;
import io.github.reneg.BossesDelight;
import io.github.reneg.bossesdelight.common.blocks.*;
import io.github.reneg.bossesdelight.common.blocks.soul_cooking.SoulCookingPotBlock;
import io.github.reneg.bossesdelight.common.blocks.soul_cooking.SoulCookingPotBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

import java.util.function.Supplier;

public class BossesDelightBlock {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(BossesDelight.MODID);


    public static final DeferredBlock<Block> OBSIDIAN_GLAZED_DRAGON_HEAD_BLOCK  = BLOCKS.register("obsidian_glazed_dragon_head_block", () -> new ObsidianGlazedDragonHeadBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), BossesDelightItems.BOWL_OF_OBSIDIAN_GLAZED_DRAGON_HEAD, true));

    public static final DeferredBlock<Block> OBSIDIAN_ONION_BLOCK = BLOCKS.register("obsidian_onion",()-> new ObsidianOnionsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    public static final DeferredBlock<Block> CRYSTAL_FRUIT_BLOCK = BLOCKS.register("crystal_fruit", () -> new CrystalFruitPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    public static final DeferredBlock<Block> CRYSTAL_FRUIT_CUBE_BLOCK  = BLOCKS.register("crystal_fruit_cube_block", () -> new CrystalFruitCubeBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.SLIME_BLOCK).pushReaction(PushReaction.DESTROY), BossesDelightItems.BOWL_OF_CRYSTAL_FRUIT_CUBE, false));

    public static final DeferredBlock<Block> BLAZING_EYE_PIE_BLOCK  = BLOCKS.register("blazing_eye_pie_block", () -> new PieBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.NETHERITE_BLOCK), BossesDelightItems.BLAZING_EYE_PIE_SLICE));

    public static final DeferredBlock<Block> HAM_ABOVE_PALM_BLOCK  = BLOCKS.register("ham_above_palm_block", () -> new LichSmoothiesBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.NETHERITE_BLOCK).pushReaction(PushReaction.DESTROY), BossesDelightItems.BOWL_OF_HAM_ABOVE_PALM, true));

    public static final DeferredBlock<Block> LICH_SMOOTHIES_BLOCK  = BLOCKS.register("lich_smoothies_block", () -> new LichSmoothiesBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.5F).sound(SoundType.SOUL_SAND).pushReaction(PushReaction.DESTROY).lightLevel((state) -> 4), BossesDelightItems.BOWL_OF_LICH_SMOOTHIES, true));

    public static final DeferredBlock<Block> MAGIC_FROZEN_NOODLES_BLOCK  = BLOCKS.register("magic_frozen_noodles_block", () -> new MagicFrozenNoodlesBlock(BlockBehaviour.Properties.of().forceSolidOn().strength(0.3F).sound(SoundType.SOUL_SAND).pushReaction(PushReaction.DESTROY).lightLevel((state) -> 15), BossesDelightItems.BOWL_OF_MAGIC_FROZEN_NOODLES, true));

    public static final DeferredBlock<Block> SOUL_COOKING_POT = BLOCKS.register("soul_cooking_pot", () -> {
        return new SoulCookingPotBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN));
    });

    public static class EntityTypes {
        public static final DeferredRegister<BlockEntityType<?>> TILES;
        public static final Supplier<BlockEntityType<SoulCookingPotBlockEntity>> SOUL_COOKING_POT;
        static {
            TILES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, BossesDelight.MODID);
            SOUL_COOKING_POT = TILES.register("soul_cooking_pot", () -> BlockEntityType.Builder.of(SoulCookingPotBlockEntity::new, new Block[]{BossesDelightBlock.SOUL_COOKING_POT.get()}).build(null));
        }
    }



}
