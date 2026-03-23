//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.github.reneg.bossesdelight.common.blocks.soul_cooking;

import com.mojang.serialization.MapCodec;
import java.util.Optional;
import javax.annotation.Nullable;

import io.github.reneg.bossesdelight.common.init.BossesDelightBlock;
import io.github.reneg.bossesdelight.common.tags.BossesDelightTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.items.ItemStackHandler;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.state.CookingPotSupport;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.MathUtils;

public class SoulCookingPotBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<SoulCookingPotBlock> CODEC = simpleCodec(SoulCookingPotBlock::new);
    public static final DirectionProperty FACING;
    public static final EnumProperty<CookingPotSupport> SUPPORT;
    public static final BooleanProperty WATERLOGGED;
    protected static final VoxelShape SHAPE;
    protected static final VoxelShape SHAPE_WITH_TRAY;

    public SoulCookingPotBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(SUPPORT, CookingPotSupport.NONE).setValue(WATERLOGGED, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }


    public ItemInteractionResult useItemOn(ItemStack heldStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if (heldStack.isEmpty() && player.isShiftKeyDown()) {
            level.setBlockAndUpdate(pos, state.setValue(SUPPORT, state.getValue(SUPPORT).equals(CookingPotSupport.HANDLE) ? this.getTrayState(level, pos) : CookingPotSupport.HANDLE));
            level.playSound((Player)null, pos, SoundEvents.LANTERN_PLACE, SoundSource.BLOCKS, 0.7F, 1.0F);
        } else if (!level.isClientSide) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof SoulCookingPotBlockEntity cookingPotEntity) {
                ItemStack servingStack = cookingPotEntity.useHeldItemOnMeal(heldStack);
                if (servingStack != ItemStack.EMPTY) {
                    if (!player.getInventory().add(servingStack)) {
                        player.drop(servingStack, false);
                    }

                    level.playSound((Player)null, pos, (SoundEvent)SoundEvents.ARMOR_EQUIP_GENERIC.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
                } else {
                    player.openMenu(cookingPotEntity, pos);
                }
            }

            return ItemInteractionResult.SUCCESS;
        }

        return ItemInteractionResult.SUCCESS;
    }

    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return ((CookingPotSupport)state.getValue(SUPPORT)).equals(CookingPotSupport.TRAY) ? SHAPE_WITH_TRAY : SHAPE;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        FluidState fluid = level.getFluidState(context.getClickedPos());
        BlockState state = (BlockState)((BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())).setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
        return context.getClickedFace().equals(Direction.DOWN) ? (BlockState)state.setValue(SUPPORT, CookingPotSupport.HANDLE) : (BlockState)state.setValue(SUPPORT, this.getTrayState(level, pos));
    }

    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return facing.getAxis().equals(Axis.Y) && !state.getValue(SUPPORT).equals(CookingPotSupport.HANDLE) ? state.setValue(SUPPORT, this.getTrayState(level, currentPos)) : state;
    }

    private CookingPotSupport getTrayState(LevelAccessor level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(BossesDelightTags.TRAY_COLD_SOURCES) ? CookingPotSupport.TRAY : CookingPotSupport.NONE;
    }

    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        ItemStack stack = super.getCloneItemStack(level, pos, state);
        Optional<SoulCookingPotBlockEntity> cookingPot = level.getBlockEntity(pos, BossesDelightBlock.EntityTypes.SOUL_COOKING_POT.get());
        if (cookingPot.isPresent()) {
            stack = cookingPot.get().getAsItem();
        }

        return stack;
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof SoulCookingPotBlockEntity cookingPotEntity) {
                Containers.dropContents(level, pos, cookingPotEntity.getDroppableInventory());
                cookingPotEntity.getUsedRecipesAndPopExperience(level, Vec3.atCenterOf(pos));
                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }

    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(new Property[]{FACING, SUPPORT, WATERLOGGED});
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof SoulCookingPotBlockEntity cookingPotEntity) {
            if (cookingPotEntity.isHeated()) {
                SoundEvent boilSound = !cookingPotEntity.getMeal().isEmpty() ? (SoundEvent)ModSounds.BLOCK_COOKING_POT_BOIL_SOUP.get() : (SoundEvent)ModSounds.BLOCK_COOKING_POT_BOIL.get();
                double x = (double)pos.getX() + 0.5;
                double y = (double)pos.getY();
                double z = (double)pos.getZ() + 0.5;
                if (random.nextInt(10) == 0) {
                    level.playLocalSound(x, y, z, boilSound, SoundSource.BLOCKS, 0.5F, random.nextFloat() * 0.2F + 0.9F, false);
                }
            }
        }

    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof SoulCookingPotBlockEntity) {
            ItemStackHandler inventory = ((SoulCookingPotBlockEntity)tileEntity).getInventory();
            return MathUtils.calcRedstoneFromItemHandler(inventory);
        } else {
            return 0;
        }
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BossesDelightBlock.EntityTypes.SOUL_COOKING_POT.get().create(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        return level.isClientSide ? createTickerHelper(blockEntity,BossesDelightBlock.EntityTypes.SOUL_COOKING_POT.get(), SoulCookingPotBlockEntity::animationTick) : createTickerHelper(blockEntity, BossesDelightBlock.EntityTypes.SOUL_COOKING_POT.get(), SoulCookingPotBlockEntity::cookingTick);
    }

    static {
        FACING = BlockStateProperties.HORIZONTAL_FACING;
        SUPPORT = EnumProperty.create("support", CookingPotSupport.class);
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        SHAPE = Block.box(2.0, 0.0, 2.0, 14.0, 10.0, 14.0);
        SHAPE_WITH_TRAY = Shapes.or(SHAPE, Block.box(0.0, -1.0, 0.0, 16.0, 0.0, 16.0));
    }
}
