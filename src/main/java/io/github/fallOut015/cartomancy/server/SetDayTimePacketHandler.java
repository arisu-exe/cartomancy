package io.github.fallOut015.cartomancy.server;

import io.github.fallOut015.cartomancy.MainCartomancy;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SetDayTimePacketHandler extends PacketHandler {
    public static final int SET_DAY_TIME_ID = PacketHandler.getNewID();
    final long time;

    public SetDayTimePacketHandler(final long time) {
        super(SET_DAY_TIME_ID);
        this.time = time;
    }

    public static void encoder(SetDayTimePacketHandler msg, PacketBuffer buffer) {
        //MainCartomancy.LOGGER.debug("Encoding {} to {}", msg, buffer);
        buffer.writeLong(msg.time);
    }
    public static SetDayTimePacketHandler decoder(PacketBuffer buffer) {
        //MainCartomancy.LOGGER.debug("Decoding {}", buffer);
        return new SetDayTimePacketHandler(buffer.readLong());
    }
    public static void handle(SetDayTimePacketHandler msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            //MainCartomancy.LOGGER.debug("Received set day time packet on " + ctx.get().getDirection().getReceptionSide().name());
            ((ServerWorld) ctx.get().getSender().level).setDayTime(msg.time);
            MainCartomancy.LOGGER.debug("set time on server to " + msg.time);
        });
        ctx.get().setPacketHandled(true);
    }
}