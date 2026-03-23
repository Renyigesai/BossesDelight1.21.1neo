package io.github.reneg.bossesdelight.common.blocks.soul_cooking;

import io.github.reneg.bossesdelight.common.tags.BossesDelightTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface FrostableBlockEntity {
    default boolean isHeated(Level level, BlockPos pos) {
        BlockState stateBelow = level.getBlockState(pos.below());
        return stateBelow.is(BossesDelightTags.COLD_SOURCES);
    }
}