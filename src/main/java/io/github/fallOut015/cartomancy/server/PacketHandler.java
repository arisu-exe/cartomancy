package io.github.fallOut015.cartomancy.server;

import io.github.fallOut015.cartomancy.MainCartomancy;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public abstract class PacketHandler {
    private static int ids;
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MainCartomancy.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    final int id;

    protected PacketHandler(int id) {
        this.id = id;
    }

    public static int getNewID() {
        return ids++;
    }

    public static void setup(final FMLCommonSetupEvent event) {
        PacketHandler.INSTANCE.registerMessage(SetDayTimePacketHandler.SET_DAY_TIME_ID, SetDayTimePacketHandler.class, SetDayTimePacketHandler::encoder, SetDayTimePacketHandler::decoder, SetDayTimePacketHandler::handle);
    }
}