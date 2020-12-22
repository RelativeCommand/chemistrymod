package com.agentbio.chemistrymod.blocks;

import com.agentbio.chemistrymod.ChemistryMod;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class TestTubeRackScreen extends ContainerScreen<TestTubeRackContainer> {

    private ResourceLocation GUI = new ResourceLocation(ChemistryMod.MODID, "textures/gui/test_tube_rack_gui-1.png");

    public TestTubeRackScreen(TestTubeRackContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }
    @Override
    public void render(int mouseX, int mouseY, float partialTicks){ //partialTicks --> value on client side for matching framerate and ticks.s-1
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);

    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
        //this.font.drawString(this.title.getFormattedText(), 8.0F,6.0F, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F,1.0F,1.0F);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) /2;
        this.blit(relX, relY,0,0, this.xSize,this.ySize);

    }

}
