package com.alpha67.AMCBase.data.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import com.alpha67.AMCBase.AMCBase;

public interface ILightningChannelerRecipe extends IRecipe<IInventory> {
    ResourceLocation TYPE_ID = new ResourceLocation(AMCBase.MOD_ID, "lightning");

    @Override
    default IRecipeType<?> getType(){
        return Registry.RECIPE_TYPE.getOptional(TYPE_ID).get();
    }

    @Override
    default boolean canFit(int width, int height) {
        return true;
    }

    @Override
    default boolean isDynamic(){
        return true;
    }
}
