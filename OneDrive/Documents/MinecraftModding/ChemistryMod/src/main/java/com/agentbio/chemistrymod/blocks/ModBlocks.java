package com.agentbio.chemistrymod.blocks;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks  {
    @ObjectHolder("chemistrymod:testtuberackblock")
    public static TestTubeRackBlock TEST_TUBE_RACK_BLOCK;

    @ObjectHolder("chemistrymod:testtuberackblock")
    public static TileEntityType<TestTubeRackBlockTile> TEST_TUBE_RACK_BLOCK_TILE;

    @ObjectHolder("chemistrymod:testtuberackblock")
    public static ContainerType<TestTubeRackContainer> TEST_TUBE_RACK_CONTAINER;
}

