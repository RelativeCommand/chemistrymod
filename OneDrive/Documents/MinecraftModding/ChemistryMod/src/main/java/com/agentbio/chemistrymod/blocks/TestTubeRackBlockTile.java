package com.agentbio.chemistrymod.blocks;

import com.agentbio.chemistrymod.ChemistryMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.agentbio.chemistrymod.blocks.ModBlocks.TEST_TUBE_RACK_BLOCK_TILE;
public class TestTubeRackBlockTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<IItemHandler> handler  = LazyOptional.of(this::createHandler);

    public TestTubeRackBlockTile() {
        super(TEST_TUBE_RACK_BLOCK_TILE);
    }

    @Override
    public void tick() {

    }

    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(invTag));
        super.read(tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        handler.ifPresent(h ->{
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            tag.put("inv", compound);
        });

        return super.write(tag);
    }
    private IItemHandler createHandler(){
        return new ItemStackHandler(3){ //size est le nombre de slots que l'on veut 3 = 3slots
            /*If you want a specificity in the item that can be passed on

                @Nonnull
                @Override
                public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                    if(stack.getItem() != Items.DIAMOND){
                        return stack;
                    }
                    return super.insertItem(0, stack, simulate);
                }
                */

        };

    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Test Tube Rack");
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory , PlayerEntity playerEntity) {
        return new TestTubeRackContainer(i, world, pos, playerInventory, playerEntity);
    }
}
