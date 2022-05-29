package com.alpha67.AMCBase.world.dimension;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import com.alpha67.AMCBase.AMCBase;

public class ModDimensions {
    public static RegistryKey<World> KJDim = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
            new ResourceLocation(AMCBase.MOD_ID, "kjdim"));
}
