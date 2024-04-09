package com.alpha67.AMCBase.world.biome;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import com.alpha67.AMCBase.AMCBase;

public class ModBiomesDatapack {
    public static final DeferredRegister<Biome> BIOMES
            = DeferredRegister.create(ForgeRegistries.BIOMES, AMCBase.MOD_ID);

    //public static RegistryKey<Biome> AMETHYST_BIOME = registerBiome("amethyst_biome");

    public static RegistryKey<Biome> registerBiome(String biomeName) {
        BIOMES.register(biomeName, BiomeMaker::makeVoidBiome);
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(AMCBase.MOD_ID, biomeName));
    }

    public static void register(IEventBus eventBus) {
        BIOMES.register(eventBus);

        //BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(AMETHYST_BIOME, 10));
    }
}
