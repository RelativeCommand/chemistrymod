package com.agentbio.chemistrymod.setup;

import com.agentbio.chemistrymod.blocks.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {
    public ItemGroup itemGroup = new ItemGroup("Chemistry") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModBlocks.TEST_TUBE_RACK_BLOCK);
        }

    };
    public void init(){

    }
}
