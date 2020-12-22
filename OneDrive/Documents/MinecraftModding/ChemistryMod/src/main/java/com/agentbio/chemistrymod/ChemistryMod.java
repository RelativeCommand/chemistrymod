package com.agentbio.chemistrymod;



import com.agentbio.chemistrymod.blocks.ModBlocks;
import com.agentbio.chemistrymod.blocks.TestTubeRackBlock;
import com.agentbio.chemistrymod.blocks.TestTubeRackBlockTile;
import com.agentbio.chemistrymod.blocks.TestTubeRackContainer;
import com.agentbio.chemistrymod.items.hydrogen;
import com.agentbio.chemistrymod.items.oxygen;
import com.agentbio.chemistrymod.items.test_tube;
import com.agentbio.chemistrymod.setup.ClientProxy;
import com.agentbio.chemistrymod.setup.IProxy;
import com.agentbio.chemistrymod.setup.ModSetup;
import com.agentbio.chemistrymod.setup.ServerProxy;
import com.google.common.eventbus.Subscribe;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.datafix.fixes.TileEntityId;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.awt.event.ContainerEvent;
import java.lang.invoke.SerializedLambda;

@Mod("chemistrymod")
public class ChemistryMod
{
    public static final String MODID = "chemistrymod";
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy()); //double lambda, client proxy acces client stuff, make sure we are on the client side before set the client proxy ( () -> () )
    public static ModSetup setup = new ModSetup();
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "chemistrymod";
    public ChemistryMod() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);



        //MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) //launch after all blocks are registered
    {
        setup.init();
        proxy.init();
    }
    @Mod.EventBusSubscriber (bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        //handle it as a block
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            event.getRegistry().register(new TestTubeRackBlock()); //register the block
        }

        //handle it as an item
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            //blocks
            Item.Properties properties = new Item.Properties().group(setup.itemGroup);
            event.getRegistry().register(new BlockItem(ModBlocks.TEST_TUBE_RACK_BLOCK, properties).setRegistryName("testtuberackblock")); //first set at TEST_TUBE... and we access it


            //items
            event.getRegistry().register(new hydrogen());
            event.getRegistry().register(new test_tube());
            event.getRegistry().register(new oxygen());


        }
        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event){
        event.getRegistry().register(TileEntityType.Builder.create(TestTubeRackBlockTile::new, ModBlocks.TEST_TUBE_RACK_BLOCK).build(null).setRegistryName("testtuberackblock"));
      }
      @SubscribeEvent
      public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event){
            event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new TestTubeRackContainer(windowId, ChemistryMod.proxy.getClientWorld(), pos, inv, ChemistryMod.proxy.getClientPlayer());
            }).setRegistryName("testtuberackblock"));
      }

    }






}
