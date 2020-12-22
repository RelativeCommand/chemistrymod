package com.agentbio.chemistrymod.blocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;
import javax.swing.plaf.basic.BasicComboBoxUI;

import static com.agentbio.chemistrymod.blocks.ModBlocks.TEST_TUBE_RACK_CONTAINER;

public class TestTubeRackContainer extends Container {
    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;


    public TestTubeRackContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player){
        super(TEST_TUBE_RACK_CONTAINER, windowId);
        TileEntity tileEntity = world.getTileEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(new SlotItemHandler(h, 0,8,64)); //slot for empty bottle monter y descend le slot
            addSlot(new SlotItemHandler(h, 1,41,32)); //slot1
            addSlot(new SlotItemHandler(h, 2,116,32)); //slot2
        });
        layoutPlayerInventorySlots(10,70);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }


    public void addSlot(IItemHandler handler, int index, int x, int y){
        addSlot(new SlotItemHandler(handler, index, x, y));
    }


    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx){
        for (int i =0; i < amount; i++){
            addSlot(new SlotItemHandler(handler, index, x,y));
            x += dx;
            index++;
        }
        return index;
    }
    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy){
        for(int j=0; j < verAmount; j++){
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow){
        //Player Inventory
        addSlotBox(playerInventory,9, leftCol, topRow, 9,18,3,18);
        //HotBar
        topRow += 58;
        addSlotRange(playerInventory,0, leftCol, topRow,9,18);
    }
}
