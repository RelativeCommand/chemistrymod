package com.agentbio.chemistrymod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;


import javax.annotation.Nullable;

public class TestTubeRackBlock extends Block {
    public TestTubeRackBlock(){
        super(Properties.create(Material.WOOD)
                        .hardnessAndResistance(2.0f)
                        .harvestLevel(0)
                        .sound(SoundType.LADDER)

        );
        setRegistryName("testtuberackblock");
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TestTubeRackBlockTile();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        if(entity != null){
            world.setBlockState(pos,state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
        }

    }
    public static Direction getFacingFromEntity(BlockPos clikedBlock, LivingEntity entity)
    {
      return Direction.getFacingFromVector((float) (entity.prevPosX -  clikedBlock.getX()), (float) (entity.prevPosY - clikedBlock.getY()), (float) entity.prevPosZ - clikedBlock.getZ());
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(BlockStateProperties.FACING);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult result) {
        if(!worldIn.isRemote){
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof INamedContainerProvider){
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, result);
    }
}
