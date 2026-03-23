//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package io.github.reneg.bossesdelight.common.client.gui;

import com.mojang.datafixers.util.Pair;
import java.util.Objects;

import io.github.reneg.bossesdelight.common.blocks.soul_cooking.SoulCookingPotBlockEntity;
import io.github.reneg.bossesdelight.common.init.BossesDelightBlock;
import io.github.reneg.bossesdelight.common.init.BossesDelightMenuTypes;
import io.github.reneg.bossesdelight.common.recipes.SoulCookingPotRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMealSlot;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotResultSlot;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModMenuTypes;
import vectorwing.farmersdelight.common.tag.ModTags;

public class SoulCookingPotMenu extends RecipeBookMenu<RecipeWrapper, SoulCookingPotRecipe> {
    public static final ResourceLocation EMPTY_CONTAINER_SLOT_BOWL = ResourceLocation.fromNamespaceAndPath("farmersdelight", "item/empty_container_slot_bowl");
    public final SoulCookingPotBlockEntity blockEntity;
    public final ItemStackHandler inventory;
    private final ContainerData cookingPotData;
    private final ContainerLevelAccess canInteractWithCallable;
    protected final Level level;

    public SoulCookingPotMenu(int windowId, Inventory playerInventory, final FriendlyByteBuf data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data), new SimpleContainerData(4));
    }

    public SoulCookingPotMenu(int windowId, Inventory playerInventory, SoulCookingPotBlockEntity blockEntity, ContainerData cookingPotDataIn) {
        super(BossesDelightMenuTypes.SOUL_COOKING_POT.get(), windowId);
        this.blockEntity = blockEntity;
        this.inventory = blockEntity.getInventory();
        this.cookingPotData = cookingPotDataIn;
        this.level = playerInventory.player.level();
        this.canInteractWithCallable = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        int startX = 8;
        int startY = 18;
        int inputStartX = 30;
        int inputStartY = 17;
        int borderSlotSize = 18;
        for (int row = 0; row < 2; ++row) {
            for (int col = 0; col < 3; ++col) {
                int slotIndex = row * 3 + col;
                this.addSlot(new SlotItemHandler(this.inventory, slotIndex,
                        inputStartX + col * borderSlotSize,
                        inputStartY + row * borderSlotSize));
            }
        }

        this.addSlot(new CookingPotMealSlot(this.inventory, 6, 124, 26));
        this.addSlot(new SlotItemHandler(this.inventory, 7, 92, 55) {
            public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, SoulCookingPotMenu.EMPTY_CONTAINER_SLOT_BOWL);
            }
        });
        this.addSlot(new SoulCookingPotResultSlot(playerInventory.player, blockEntity, this.inventory, 8, 124, 55));
        int playerInvStartY = startY * 4 + 12;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int slotIndex = 9 + row * 9 + col;
                this.addSlot(new Slot(playerInventory, slotIndex,
                        startX + col * borderSlotSize,
                        playerInvStartY + row * borderSlotSize));
            }
        }
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col,
                    startX + col * borderSlotSize,
                    142));
        }
        this.addDataSlots(cookingPotDataIn);
    }

    private static SoulCookingPotBlockEntity getTileEntity(Inventory playerInventory, FriendlyByteBuf data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "data cannot be null");
        BlockEntity tileAtPos = playerInventory.player.level().getBlockEntity(data.readBlockPos());
        if (tileAtPos instanceof SoulCookingPotBlockEntity) {
            return (SoulCookingPotBlockEntity)tileAtPos;
        } else {
            throw new IllegalStateException("Tile entity is not correct! " + String.valueOf(tileAtPos));
        }
    }

    public boolean stillValid(Player playerIn) {
        return stillValid(this.canInteractWithCallable, playerIn, BossesDelightBlock.SOUL_COOKING_POT.get());
    }

    public ItemStack quickMoveStack(Player playerIn, int index) {
        int indexMealDisplay = 6;
        int indexContainerInput = 7;
        int indexOutput = 8;
        int startPlayerInv = indexOutput + 1;
        int endPlayerInv = startPlayerInv + 36;
        ItemStack slotStackCopy = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            slotStackCopy = slotStack.copy();
            if (index == indexOutput) {
                if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index <= indexOutput) {
                if (!this.moveItemStackTo(slotStack, startPlayerInv, endPlayerInv, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                boolean isValidContainer = slotStack.is(ModTags.SERVING_CONTAINERS) || slotStack.is(this.blockEntity.getContainer().getItem());
                if (isValidContainer && !this.moveItemStackTo(slotStack, indexContainerInput, indexContainerInput + 1, false)) {
                    return ItemStack.EMPTY;
                }

                if (!this.moveItemStackTo(slotStack, 0, indexMealDisplay, false)) {
                    return ItemStack.EMPTY;
                }

                if (!this.moveItemStackTo(slotStack, indexContainerInput, indexOutput, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (slotStack.getCount() == slotStackCopy.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, slotStack);
        }

        return slotStackCopy;
    }

    public int getCookProgressionScaled() {
        int i = this.cookingPotData.get(0);
        int j = this.cookingPotData.get(1);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    public boolean isHeated() {
        return this.blockEntity.isHeated();
    }

    public void fillCraftSlotsStackedContents(StackedContents helper) {
        for(int i = 0; i < this.inventory.getSlots(); ++i) {
            helper.accountSimpleStack(this.inventory.getStackInSlot(i));
        }

    }

    public void clearCraftingContent() {
        for(int i = 0; i < 6; ++i) {
            this.inventory.setStackInSlot(i, ItemStack.EMPTY);
        }

    }

    @Override
    public boolean recipeMatches(RecipeHolder<SoulCookingPotRecipe> recipe) {
        return recipe.value().matches(new RecipeWrapper(this.inventory), this.level);
    }

//    public boolean recipeMatches(RecipeHolder<CookingPotRecipe> recipe) {
//        return ((CookingPotRecipe)recipe.value()).matches(new RecipeWrapper(this.inventory), this.level);
//    }

    public int getResultSlotIndex() {
        return 7;
    }

    public int getGridWidth() {
        return 3;
    }

    public int getGridHeight() {
        return 2;
    }

    public int getSize() {
        return 7;
    }

    public RecipeBookType getRecipeBookType() {
        return RecipeBookType.valueOf("FARMERSDELIGHT_COOKING");
    }

    public boolean shouldMoveToInventory(int slot) {
        return slot < this.getGridWidth() * this.getGridHeight();
    }
}
