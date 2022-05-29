package com.alpha67.AMCBase.init;

import com.alpha67.AMCBase.tileentity.LightningChannelerTile;
import com.alpha67.AMCBase.tileentity.ModSignTileEntity;
import com.alpha67.AMCBase.tileentity.StoneMarketTile;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.alpha67.AMCBase.AMCBase;

public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, AMCBase.MOD_ID);

    public static RegistryObject<TileEntityType<LightningChannelerTile>> LIGHTNING_CHANNELER_TILE =
            TILE_ENTITIES.register("lightning_channeler_tile", () -> TileEntityType.Builder.create(
                    LightningChannelerTile::new, ModBlocks.LIGHTNING_CHANNELER.get()).build(null));

    public static RegistryObject<TileEntityType<StoneMarketTile>> STONE_MARKET_TILE =
            TILE_ENTITIES.register("stone_market_tile", () -> TileEntityType.Builder.create(
                    StoneMarketTile::new, ModBlocks.STONE_MARKET.get()).build(null));

    public static final RegistryObject<TileEntityType<ModSignTileEntity>> SIGN_TILE_ENTITIES =
            TILE_ENTITIES.register("sign", () -> TileEntityType.Builder.create(ModSignTileEntity::new,
                    ModBlocks.REDWOOD_SIGN.get(),
                    ModBlocks.REDWOOD_WALL_SIGN.get()
                    ).build(null));


    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
