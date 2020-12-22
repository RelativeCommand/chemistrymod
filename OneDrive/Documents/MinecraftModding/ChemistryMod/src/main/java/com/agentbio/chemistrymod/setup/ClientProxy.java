package com.agentbio.chemistrymod.setup;

import com.agentbio.chemistrymod.blocks.ModBlocks;
import com.agentbio.chemistrymod.blocks.TestTubeRackScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {
    @Override
    public void init() {
        ScreenManager.registerFactory(ModBlocks.TEST_TUBE_RACK_CONTAINER, TestTubeRackScreen::new);
    }

    @Override
    public World getClientWorld(){
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
