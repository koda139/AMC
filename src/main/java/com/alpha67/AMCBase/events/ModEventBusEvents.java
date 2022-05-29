package com.alpha67.AMCBase.events;

import com.alpha67.AMCBase.init.ModEntityTypes;
import com.alpha67.AMCBase.entity.custom.BuffZombieEntity;
import com.alpha67.AMCBase.entity.custom.PigeonEntity;
import com.alpha67.AMCBase.events.loot.FirestoneAdditionModifier;
import com.alpha67.AMCBase.events.loot.FirestoneStructureAdditionModifier;
import com.alpha67.AMCBase.item.custom.ModSpawnEggItem;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.alpha67.AMCBase.AMCBase;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = AMCBase.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void addEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.BUFF_ZOMBIE.get(), BuffZombieEntity.setCustomAttributes().create());
        event.put(ModEntityTypes.PIGEON.get(), PigeonEntity.setCustomAttributes().create());
    }

    @SubscribeEvent
    public static void onRegisterEntities(RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.initSpawnEggs();
    }

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new FirestoneAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(AMCBase.MOD_ID,"firestone_from_magma")),
                new FirestoneStructureAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(AMCBase.MOD_ID,"firestone_in_igloo"))
        );
    }
}
