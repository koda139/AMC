package com.alpha67.AMCBase.init;

import com.alpha67.AMCBase.tileentity.*;
import com.alpha67.AMCBase.tileentity.market.DiamondMarketTile;
import com.alpha67.AMCBase.tileentity.market.GoldMarketTile;
import com.alpha67.AMCBase.tileentity.market.StoneMarketTile;
import com.alpha67.AMCBase.tileentity.market.WoodMarketTile;
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

    public static RegistryObject<TileEntityType<WoodMarketTile>> WOOD_MARKET_TILE =
            TILE_ENTITIES.register("wood_market_tile", () -> TileEntityType.Builder.create(
                    WoodMarketTile::new, ModBlocks.WOOD_MARKET.get()).build(null));

    public static RegistryObject<TileEntityType<GoldMarketTile>> GOLD_MARKET_TILE =
            TILE_ENTITIES.register("gold_market_tile", () -> TileEntityType.Builder.create(
                    GoldMarketTile::new, ModBlocks.GOLD_MARKET.get()).build(null));

    public static RegistryObject<TileEntityType<DiamondMarketTile>> DIAMOND_MARKET_TILE =
            TILE_ENTITIES.register("diamond_market_tile", () -> TileEntityType.Builder.create(
                    DiamondMarketTile::new, ModBlocks.DIAMOND_MARKET.get()).build(null));

    public static RegistryObject<TileEntityType<CompressorBlockTile>> COMPRESSOR_BLOCK_TILE =
            TILE_ENTITIES.register("compressor_block_tile", () -> TileEntityType.Builder.create(
                    CompressorBlockTile::new, ModBlocks.COMPRESSOR_BLOCK.get()).build(null));

    public static RegistryObject<TileEntityType<electricBankTile>> ELECTRIC_BANK_TILE =
            TILE_ENTITIES.register("electric_bank_tile", () -> TileEntityType.Builder.create(
                    electricBankTile::new, ModBlocks.ELECTRIC_BANK.get()).build(null));

    public static RegistryObject<TileEntityType<ATMBlockTile>> ATM_BLOCK_TILE =
            TILE_ENTITIES.register("atm_block_tile", () -> TileEntityType.Builder.create(
                    ATMBlockTile::new, ModBlocks.ATM.get()).build(null));

    public static RegistryObject<TileEntityType<CoalGeneratorTile>> COAL_GENERATOR_BLOCK =
            TILE_ENTITIES.register("coal_generator_tile", () -> TileEntityType.Builder.create(
                    CoalGeneratorTile::new, ModBlocks.COAL_GENERATOR.get()).build(null));



    public static final RegistryObject<TileEntityType<ModSignTileEntity>> SIGN_TILE_ENTITIES =
            TILE_ENTITIES.register("sign", () -> TileEntityType.Builder.create(ModSignTileEntity::new,
                    ModBlocks.REDWOOD_SIGN.get(),
                    ModBlocks.REDWOOD_WALL_SIGN.get()
                    ).build(null));


    public static void register(IEventBus eventBus) {
        TILE_ENTITIES.register(eventBus);
    }
}
