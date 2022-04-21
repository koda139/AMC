package com.alpha67.amcbase.network;

import com.alpha67.amcbase.AMCBase;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import org.spongepowered.asm.mixin.MixinEnvironment;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import java.util.function.Supplier;

public class packetTest {

    public static String hour;

    public packetTest(String hour)
    {
        this.hour = hour;
    }

    public void encode(PacketBuffer packetBuffer) {
        packetBuffer.writeUtf(packetTest.hour);
    }

    public static packetTest decode(PacketBuffer packetBuffer) {
        String hour = packetBuffer.readUtf();
        return new packetTest(hour);
    }

    public static void handle(packetTest packettest, Supplier<NetworkEvent.Context> contextSupplier) {

        //ServerPlayerEntity player = contextSupplier.get().getSender();
        String hour = packetTest.hour;
        System.out.println("message recu." + hour);
        System.out.println(new ResourceLocation(AMCBase.MODID, "main"));

        //player.displayClientMessage(new StringTextComponent(hour), true);

    }
}
