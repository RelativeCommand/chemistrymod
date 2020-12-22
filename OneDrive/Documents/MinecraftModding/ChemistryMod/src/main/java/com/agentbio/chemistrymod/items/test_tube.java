package com.agentbio.chemistrymod.items;

import com.agentbio.chemistrymod.ChemistryMod;
import net.minecraft.item.Item;

public class test_tube extends Item{
    public test_tube(){
        super(new Item.Properties().group(ChemistryMod.setup.itemGroup)
                .maxStackSize(64));
        setRegistryName("test_tube");

    }
}
