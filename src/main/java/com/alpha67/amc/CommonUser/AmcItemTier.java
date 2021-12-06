package com.alpha67.amc.CommonUser;

import com.alpha67.amc.vultorio.init.ItemInitVultorio;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;


public enum AmcItemTier implements IItemTier {
    ALPHARIUM(1, 3000, 12.0F, 5.0F, 20, () -> Ingredient.of(ItemInitVultorio.alpharium_ingot.get()));

    /*   WOOD(0, 59, 2.0F, 0.0F, 15, () -> {
      return Ingredient.of(ItemTags.PLANKS);
   }),
   STONE(1, 131, 4.0F, 1.0F, 5, () -> {
      return Ingredient.of(ItemTags.STONE_TOOL_MATERIALS);
   }),
   IRON(2, 250, 6.0F, 2.0F, 14, () -> {
      return Ingredient.of(Items.IRON_INGOT);
   }),
   DIAMOND(3, 1561, 8.0F, 3.0F, 10, () -> {
      return Ingredient.of(Items.DIAMOND);
   }),
   GOLD(0, 32, 12.0F, 0.0F, 22, () -> {
      return Ingredient.of(Items.GOLD_INGOT);
   }),
   NETHERITE(4, 2031, 9.0F, 4.0F, 15, () -> {
      return Ingredient.of(Items.NETHERITE_INGOT);
   });*/

    private final Supplier<Ingredient> repairmaterial;
    private final int enchantability;
    private final float attackDamage;
    private final float speed;
    private final int maxUses;
    private final int harvestLevel;


    AmcItemTier(int harvestLevel, int maxUses, float speed, float attackDamage, int enchantability, Supplier<Ingredient> repairmaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.speed = speed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairmaterial = repairmaterial;
    }


    public Ingredient getRepairMaterial() {
        return this.repairmaterial.get();
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairmaterial.get();
    }
}

