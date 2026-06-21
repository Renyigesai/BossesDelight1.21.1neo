package io.github.reneg.bossesdelight.common.items;

import io.github.reneg.bossesdelight.common.init.BossesDelightItems;
import io.github.reneg.bossesdelight.common.init.BossesDelightTriggers;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.List;

@EventBusSubscriber
public class NectarJellyItem extends Item {
    public NectarJellyItem(Properties p_41383_) {
        super(p_41383_);
    }

    private static InteractionResult nectarJellyUseOn(UseOnContext p_40637_){
        Level level = p_40637_.getLevel();
        ItemStack itemstack = p_40637_.getItemInHand();
        BlockPos blockpos = p_40637_.getClickedPos();
        BlockState state = level.getBlockState(blockpos);
        BlockState defaultState = state.getBlock().defaultBlockState();
        if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING) && defaultState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            defaultState = defaultState.setValue(BlockStateProperties.HORIZONTAL_FACING, direction);
        }
        if(!defaultState.equals(state)){
            if (!level.isClientSide() && p_40637_.getPlayer() instanceof ServerPlayer player) {
                BossesDelightTriggers.USE_NECTAR_JELLY_TRIGGER.get().trigger(player);
            }
            level.setBlock(blockpos, defaultState, 2);
            level.playSound((Player)null, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.SLIME_BLOCK_PLACE, SoundSource.PLAYERS, 1.0F, 1.0F);
            if (!p_40637_.getPlayer().getAbilities().instabuild) {
                itemstack.shrink(1);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.FAIL;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext p_339594_, List<Component> tooltip, TooltipFlag p_41424_) {
        String string = "tooltip." + stack.getItem();
        String newString = string.replaceAll(":", ".");
        tooltip.add(Component.translatable(newString).withStyle(ChatFormatting.YELLOW));
    }

    @SubscribeEvent
    private static void onUseBlock(PlayerInteractEvent.RightClickBlock event){
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        BlockState state = event.getLevel().getBlockState(event.getPos());
        if (stack.is(BossesDelightItems.NECTAR_JELLY)){
            if (state.getBlock() instanceof CakeBlock || state.getBlock() instanceof FeastBlock || state.getBlock() instanceof PieBlock) {
                event.setCanceled(true);
                event.setCancellationResult(nectarJellyUseOn(new UseOnContext(player,event.getHand(),event.getHitVec())));
            }
        }
    }

    /*@SubscribeEvent*/
//    private static void onUseBlock(PlayerInteractEvent.RightClickBlock event) {
//        Player entity = event.getEntity();
//        if (entity.getMainHandItem().is(BossesDelightItems.NECTAR_JELLY)){
//            event.setCanceled(true);
//            BlockPos pos = event.getPos();
//            BlockState blockState = event.getLevel().getBlockState(pos);
//            List<? extends String> classs = ServerConfig.BLOCK_CLASSES.get();
//            Class<? extends Block> blockClass = blockState.getBlock().getClass();
//            boolean flag = false;
//            try {
//                for (String string : classs) {
//                    Class<?> loadClass = Class.forName(string);
//                    if (loadClass.isAssignableFrom(blockClass)){
//                        flag = true;
//                        break;
//                    }
//                }
//                if (flag){
//                    if (blockState.getBlock().asItem() instanceof BlockItem blockItem){
//
//                        float matchingYaw = findMatchingYaw(blockState, blockItem, entity, event.getLevel(), event.getHand(), event.getHitVec());
//                        float originalYRot = entity.getYRot();
//                        float originalYHeadRot = entity.getYHeadRot();
//                        float originalXRot = entity.getXRot();
//                        float originalXRotO = entity.xRotO;
//
//                        entity.setYRot(matchingYaw);
//                        entity.yRotO = matchingYaw;
//                        entity.setYHeadRot(matchingYaw);
//                        entity.yHeadRotO = matchingYaw;
//
//                        event.getLevel().removeBlock(pos,false);
//                        placeWithFakePlayerPosition(blockItem, pos, Direction.fromYRot(matchingYaw), entity, event.getLevel(), event.getHand(), event.getHitVec());
//
//                        entity.setYRot(originalYRot);
//                        entity.yRotO = originalYRot;
//                        entity.setYHeadRot(originalYHeadRot);
//                        entity.yHeadRotO = originalYHeadRot;
//                        entity.setXRot(originalXRot);
//                        entity.xRotO = originalXRotO;
//
//                        event.setCancellationResult(InteractionResult.SUCCESS);
//                    }
//                }
//            }catch (ClassNotFoundException exception){
//                BossesDelight.LOGGER.error(String.valueOf(exception));
//            }
//        }
//    }

    private static float findMatchingYaw(BlockState originalState, BlockItem blockItem, Player player, Level level, InteractionHand hand, BlockHitResult hit) {
        Direction originalDir = null;
        if (originalState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
            originalDir = originalState.getValue(BlockStateProperties.HORIZONTAL_FACING);
        } else if (originalState.hasProperty(BlockStateProperties.FACING)) {
            Direction facing = originalState.getValue(BlockStateProperties.FACING);
            if (facing.getAxis().isHorizontal()) {
                originalDir = facing;
            }
        }
        if (originalDir == null) {
            return player.getYRot();
        }

        float originalYRot = player.getYRot();
        float originalYHeadRot = player.getYHeadRot();

        Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

        for (Direction tryDir : directions) {
            float tryYaw = tryDir.toYRot();

            player.setYRot(tryYaw);
            player.yRotO = tryYaw;
            player.setYHeadRot(tryYaw);
            player.yHeadRotO = tryYaw;

            BlockPlaceContext context = new BlockPlaceContext(
                    new UseOnContext(level, player, hand, player.getItemInHand(hand), hit)
            );
            BlockState predictedState = blockItem.getBlock().getStateForPlacement(context);

            boolean matched = false;
            if (predictedState != null) {
                if (predictedState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)
                        && originalDir == predictedState.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
                    matched = true;
                } else if (predictedState.hasProperty(BlockStateProperties.FACING)
                        && predictedState.getValue(BlockStateProperties.FACING) == originalDir) {
                    matched = true;
                }
            }

            player.setYRot(originalYRot);
            player.yRotO = originalYRot;
            player.setYHeadRot(originalYHeadRot);
            player.yHeadRotO = originalYHeadRot;

            if (matched) {
                return tryYaw;
            }
        }

        return originalDir.toYRot();
    }

    private static void placeWithFakePlayerPosition(BlockItem blockItem, BlockPos targetPos,
                                                    Direction desiredFacing, Player player,
                                                    Level level, InteractionHand hand, BlockHitResult hit) {
        Vec3 blockCenter = targetPos.getCenter();
        Vec3 fakePlayerPos = blockCenter.subtract(
                desiredFacing.getStepX() * 1,
                0,
                desiredFacing.getStepZ() * 1
        );

        Vec3 originalPos = player.position();

        player.teleportTo(fakePlayerPos.x, fakePlayerPos.y, fakePlayerPos.z);
        player.setYRot(desiredFacing.toYRot());
        BlockHitResult topHit = new BlockHitResult(
                targetPos.getCenter().add(0, 0.5, 0), // 命中位置（方块顶面中心）
                Direction.UP,                          // 始终为顶面
                targetPos,                             // 被点击的方块坐标
                false                                  // 不在方块内部
        );
        BlockPlaceContext context = new BlockPlaceContext(
                new UseOnContext(level, player, hand, player.getItemInHand(hand), topHit)
        );
        blockItem.place(context);
        player.teleportTo(originalPos.x, originalPos.y, originalPos.z);
    }

}
