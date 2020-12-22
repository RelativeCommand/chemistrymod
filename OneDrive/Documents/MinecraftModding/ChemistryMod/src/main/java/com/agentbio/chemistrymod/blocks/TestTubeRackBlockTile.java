package com.agentbio.chemistrymod.blocks;

import com.agentbio.chemistrymod.setup.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.agentbio.chemistrymod.blocks.ModBlocks.TEST_TUBE_RACK_BLOCK_TILE;

public class TestTubeRackBlockTile extends TileEntity implements ITickableTileEntity {

    private ItemStackHandler itemHandler = createHandler();

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);


    public TestTubeRackBlockTile() {
        super(TEST_TUBE_RACK_BLOCK_TILE);
    }


    @Override
    public void tick() {

    }


    @Override
    public void read(CompoundNBT tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));


        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag.put("inv", itemHandler.serializeNBT());
        return super.write(tag);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {


            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.getItem() == ModItems.test_tube;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (stack.getItem() != ModItems.test_tube) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }



    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }
}