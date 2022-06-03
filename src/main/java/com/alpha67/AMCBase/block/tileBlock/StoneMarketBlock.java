package com.alpha67.AMCBase.block.tileBlock;

import com.alpha67.AMCBase.container.StoneMarketContainer;
import com.alpha67.AMCBase.init.ModBlocks;
import com.alpha67.AMCBase.init.ModItems;
import com.alpha67.AMCBase.init.ModTileEntities;
import com.alpha67.AMCBase.tileentity.StoneMarketTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class StoneMarketBlock extends Block {

    public StoneMarketBlock(Properties properties) {
        super(properties);
    }

    int x;
    int y;
    int z;

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        StoneMarketTile tile = (StoneMarketTile) worldIn.getTileEntity(pos);

        if(ModItems.CREDIT_CARD.get() == player.getHeldItemMainhand().getItem())
        {

            if (!tile.getTileData().getString("owner").contains("-"))
            {
                tile.getTileData().putString("owner", String.valueOf(player.getUniqueID()));
            }
        }

        else if (!worldIn.isRemote())
        {

            StoneMarketTile tileEntity = (StoneMarketTile) worldIn.getTileEntity(pos);
            String owner = tileEntity.getTileData().getString("owner");
            System.out.println(owner);
            System.out.println(player.getUniqueID().toString());

            if(owner.contains(String.valueOf(player.getUniqueID()))) {


                tileEntity.getTileData().putString("player", String.valueOf(player.getUniqueID()));

                System.out.println("2");

                if (tileEntity instanceof StoneMarketTile) {
                    INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);

                    System.out.println("1");

                    NetworkHooks.openGui(((ServerPlayerEntity) player), containerProvider, tileEntity.getPos());
                } else {
                    throw new IllegalStateException("Our Container provider is missing!");
                }
            }

            else {
            }
        }

        else {
            System.out.println("can't open GUI");


        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.amcmode.stone_market_block");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new StoneMarketContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }

    @Override
    public void tick(BlockState blockstate, ServerWorld world, BlockPos pos, Random random) {
        super.tick(blockstate, world, pos, random);

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
