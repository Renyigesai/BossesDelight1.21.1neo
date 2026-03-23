package io.github.reneg.bossesdelight.common.blocks;

import com.cerbon.bosses_of_mass_destruction.BossesOfMassDestruction;
import com.cerbon.bosses_of_mass_destruction.item.BMDItems;
import io.github.reneg.bossesdelight.common.config.Config;
import io.github.reneg.bossesdelight.common.init.BossesDelightItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.checkerframework.checker.units.qual.A;

import java.util.List;


public class CrystalFruitPlantBlock extends SweetBerryBushBlock {
    private static final VoxelShape SHAPE_1;
    private static final VoxelShape SHAPE_2;
    private static final VoxelShape SHAPE_3;
    private static final VoxelShape SHAPE_4;
    public static final IntegerProperty AGE;

    public CrystalFruitPlantBlock(Properties p_57249_) {
        super(p_57249_);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int age = state.getValue(AGE);
        if (age == 3) {
            boolean temp = false;
            if (level.isClientSide){
                return InteractionResult.SUCCESS;
            }
            if (level instanceof ServerLevel) {
                popResource(level, pos, new ItemStack(BMDItems.CRYSTAL_FRUIT.get(),1));
                temp = true;
            }
            if (temp) {
                level.setBlock(pos, state.setValue(AGE, 0), 3);
                level.playSound(null, pos, SoundEvents.PLAYER_HURT_SWEET_BERRY_BUSH, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
                return InteractionResult.SUCCESS;
            }
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
        return Config.CRYSTAL_FRUIT_PLANTABLE_BLOCKS.contains(p_51042_.getBlock());
    }

    public boolean canSurvive(BlockState p_51028_, LevelReader p_51029_, BlockPos p_51030_) {
        BlockState below = p_51029_.getBlockState(p_51030_.below());
        return this.mayPlaceOn(below, p_51029_, p_51030_);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(BossesDelightItems.CRYSTAL_FRUIT_SEEDS.get());
    }

    @Override
    public VoxelShape getShape(BlockState p_57291_, BlockGetter p_57292_, BlockPos p_57293_, CollisionContext p_57294_) {
        VoxelShape shape = super.getShape(p_57291_, p_57292_, p_57293_, p_57294_);
        switch(p_57291_.getValue(AGE)){
            case 0 -> shape = SHAPE_1;
            case 1 -> shape = SHAPE_2;
            case 2 -> shape = SHAPE_3;
            case 3 -> shape = SHAPE_4;
            default -> shape = super.getShape(p_57291_, p_57292_, p_57293_, p_57294_);
        }
        return shape;
    }

    static {
        AGE = BlockStateProperties.AGE_3;
        SHAPE_1 = Block.box(4.0, 0.0, 4.0, 12.0, 4.0, 12.0);
        SHAPE_2 = Block.box(3.0, 0.0, 3.0, 13.0, 5.0, 13.0);
        SHAPE_3 = Block.box(2.0, 0.0, 2.0, 14.0, 7.0, 14.0);
        SHAPE_4 = Block.box(1.0, 0.0, 1.0, 15.0, 10.0, 15.0);
    }
}