package com.agentbio.chemistrymod.items;

import com.agentbio.chemistrymod.ChemistryMod;
import net.minecraft.item.Item;

public class hydrogen extends Item{
    public hydrogen(){
        super(new Item.Properties().group(ChemistryMod.setup.itemGroup)
                .maxStackSize(64));
        setRegistryName("hydrogen");

    }
}
