package com.alpha67.AMCBase.init;

import com.alpha67.AMCBase.container.*;
import com.alpha67.AMCBase.container.market.DiamondMarketContainer;
import com.alpha67.AMCBase.container.market.GoldMarketContainer;
import com.alpha67.AMCBase.container.market.StoneMarketContainer;
import com.alpha67.AMCBase.container.market.WoodMarketContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.alpha67.AMCBase.AMCBase;

public class ModContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, AMCBase.MOD_ID);

    public static final RegistryObject<ContainerType<LightningChannelerContainer>> LIGHTNING_CHANNELER_CONTAINER
            = CONTAINERS.register("lightning_channeler_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new LightningChannelerContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<ContainerType<StoneMarketContainer>> STONE_MARKET_CONTAINER
            = CONTAINERS.register("stone_market_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new StoneMarketContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<ContainerType<WoodMarketContainer>> WOOD_MARKET_CONTAINER
            = CONTAINERS.register("wood_market_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new WoodMarketContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<ContainerType<GoldMarketContainer>> GOLD_MARKET_CONTAINER
            = CONTAINERS.register("gold_market_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new GoldMarketContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<ContainerType<DiamondMarketContainer>> DIAMOND_MARKET_CONTAINER
            = CONTAINERS.register("diamond_market_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new DiamondMarketContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<ContainerType<compressorBlockContainer>> COMPRESSOR_BLOCK_CONTAINER
            = CONTAINERS.register("compressor_block_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new compressorBlockContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<ContainerType<electricBankContainer>> ELECTIC_BANK_CONTAINER
            = CONTAINERS.register("electric_bank_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new electricBankContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<ContainerType<ATMBlockContainer>> ATM_BLOCK_CONTAINER
            = CONTAINERS.register("atm_block_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new ATMBlockContainer(windowId, world, pos, inv, inv.player);
            })));

    public static final RegistryObject<ContainerType<CoalGeneratorContainer>> COAL_GENERATOR_CONTAINER
            = CONTAINERS.register("coal_generaotor_container",
            () -> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new CoalGeneratorContainer(windowId, world, pos, inv, inv.player);
            })));


    public static void register(IEventBus eventBus) {
        CONTAINERS.register(eventBus);
    }
}
