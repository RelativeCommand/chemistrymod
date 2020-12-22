package com.agentbio.chemistrymod.setup;

import com.agentbio.chemistrymod.items.hydrogen;
import com.agentbio.chemistrymod.items.oxygen;
import com.agentbio.chemistrymod.items.test_tube;
import net.minecraftforge.registries.ObjectHolder;

public class ModItems {
    @ObjectHolder("chemistrymod:hydrogen")
    public static hydrogen hydrogen;
    @ObjectHolder("chemistrymod:test_tube")
    public static test_tube test_tube;
    @ObjectHolder("chemistrymod:oxygen")
    public static oxygen oxygen;

}
