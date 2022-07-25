package com.alpha67.AMCBase.block.tileBlock.market;

import com.alpha67.AMCBase.container.market.StoneMarketContainer;
import com.alpha67.AMCBase.init.ModBlocks;
import com.alpha67.AMCBase.init.ModItems;
import com.alpha67.AMCBase.init.ModTileEntities;
import com.alpha67.AMCBase.tileentity.market.StoneMarketTile;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class StoneMarketBlock extends HorizontalBlock {

    int x;
    int y;
    int z;

    public StoneMarketBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }


    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();

        StoneMarketTile tile = (StoneMarketTile) worldIn.getTileEntity(pos);

        if(ModItems.CREDIT_CARD.get() == player.getHeldItemMainhand().getItem())
        {

            if (!tile.getTileData().getString("owner").contains("-"))
            {
                tile.getTileData().putString("owner", String.valueOf(player.getUniqueID()));

                String message = "This stone market block is now yours";
                //player.sendMessage(ITextComponent.getTextComponentOrEmpty(message), player.getUniqueID());
            }
        }

        else if (!worldIn.isRemote())
        {

            StoneMarketTile tileEntity = (StoneMarketTile) worldIn.getTileEntity(pos);
            String owner = tileEntity.getTileData().getString("owner");

            if(owner.contains(String.valueOf(player.getUniqueID()))) {

                if(worldIn.getBlockState(new BlockPos((int) x, (int) y + 1, (int) z)).getBlock() == ModBlocks.ANTENNA.get())
                {
                    System.out.println(worldIn.getBlockState(new BlockPos((int) x, (int) y+1, (int) z)).getBlock());
                    System.out.println(ModBlocks.ANTENNA.get());

                    tileEntity.getTileData().putString("player", String.valueOf(player.getUniqueID()));

                    if (tileEntity instanceof StoneMarketTile) {
                        INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);

                        NetworkHooks.openGui(((ServerPlayerEntity) player), containerProvider, tileEntity.getPos());
                    } else {
                        throw new IllegalStateException("Our Container provider is missing!");
                    }
                }
                else{
                    String message = "sorry but your stone market block is not connecte to the the network ! You need an antenna.";
                   // player.sendMessage(ITextComponent.getTextComponentOrEmpty(message), player.getUniqueID());
                }
            }

            else {
                String message = "Sorry but this is not your Stone market block";
                //player.sendMessage(ITextComponent.getTextComponentOrEmpty(message), player.getUniqueID());
            }
        }

        else {


        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new StoneMarketContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.STONE_MARKET_TILE.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
