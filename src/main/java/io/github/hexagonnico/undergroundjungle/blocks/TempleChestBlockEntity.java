package io.github.hexagonnico.undergroundjungle.blocks;

import io.github.hexagonnico.undergroundjungle.UndergroundJungleBlockEntities;
import io.github.hexagonnico.undergroundjungle.UndergroundJungleItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ChestLidAnimator;
import net.minecraft.block.entity.LidOpenable;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TempleChestBlockEntity extends LootableContainerBlockEntity implements LidOpenable {

    private DefaultedList<ItemStack> items = DefaultedList.ofSize(27, ItemStack.EMPTY);

    private final ViewerCountManager stateManager = new ViewerCountManager() {
        @Override
        protected void onContainerOpen(World world, BlockPos pos, BlockState state) {
            world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5f, world.random.nextFloat() * 0.1f + 0.9f);
        }

        @Override
        protected void onContainerClose(World world, BlockPos pos, BlockState state) {
            world.playSound(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5f, world.random.nextFloat() * 0.1f + 0.9f);
        }

        @Override
        protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {
            world.addSyncedBlockEvent(pos, state.getBlock(), 1, newViewerCount);
        }

        @Override
        protected boolean isPlayerViewing(PlayerEntity player) {
            if(player.currentScreenHandler instanceof GenericContainerScreenHandler chestMenu) {
                return chestMenu.getInventory() == TempleChestBlockEntity.this;
            }
            return false;
        }
    };

    private final ChestLidAnimator lidAnimator = new ChestLidAnimator();

    private boolean unlocked = false;

    public TempleChestBlockEntity(BlockPos pos, BlockState state) {
        super(UndergroundJungleBlockEntities.TEMPLE_CHEST, pos, state);
    }

    @Override
    public int size() {
        return 27;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.temple_chest");
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.items = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if(!this.deserializeLootTable(nbt)) {
            Inventories.readNbt(nbt, this.items);
        }
        this.unlocked = nbt.getBoolean("unlocked");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if(!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.items);
        }
        nbt.putBoolean("unlocked", this.unlocked);
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, TempleChestBlockEntity blockEntity) {
        blockEntity.lidAnimator.step();
    }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        if(type == 1) {
            this.lidAnimator.setOpen(data > 0);
            return true;
        }
        return super.onSyncedBlockEvent(type, data);
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return this.unlocked && super.canPlayerUse(player);
    }

    @Override
    public void onOpen(PlayerEntity player) {
        World world = this.getWorld();
        if(!this.removed && !player.isSpectator() && world != null) {
            this.stateManager.openContainer(player, world, this.getPos(), this.getCachedState());
        }
    }

    @Override
    public void onClose(PlayerEntity player) {
        World world = this.getWorld();
        if(!this.removed && !player.isSpectator() && world != null) {
            this.stateManager.closeContainer(player, world, this.getPos(), this.getCachedState());
        }
    }

    public void tryUnlock(PlayerEntity player, Hand hand) {
        if(!this.unlocked) {
            ItemStack itemInHand = player.getStackInHand(hand);
            if(itemInHand.isOf(UndergroundJungleItems.TEMPLE_KEY)) {
                if(!player.isCreative()) {
                    itemInHand.decrement(1);
                }
                this.unlocked = true;
                this.markDirty();
            }
        }
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.items;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> items) {
        this.items = items;
    }

    @Override
    public float getAnimationProgress(float delta) {
        return this.lidAnimator.getProgress(delta);
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
    }

    public void onScheduledTick() {
        World world = this.getWorld();
        if(!this.removed && world != null) {
            this.stateManager.updateViewerCount(world, this.getPos(), this.getCachedState());
        }
    }

//    @Override
//    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
//        return LazyOptional.empty();
//    }
//
//    @Override
//    public int @NotNull [] getSlotsForFace(@NotNull Direction face) {
//        return new int[0];
//    }
//
//    @Override
//    public boolean canPlaceItemThroughFace(int i, @NotNull ItemStack itemStack, Direction side) {
//        return false;
//    }
//
//    @Override
//    public boolean canTakeItemThroughFace(int i, @NotNull ItemStack itemStack, @NotNull Direction side) {
//        return false;
//    }
}
