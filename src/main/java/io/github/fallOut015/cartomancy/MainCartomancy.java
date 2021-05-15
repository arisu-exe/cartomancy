package io.github.fallOut015.cartomancy;

import io.github.fallOut015.cartomancy.client.particle.ParticleManagerCartomancy;
import io.github.fallOut015.cartomancy.client.registry.RenderingRegistryCartomancy;
import io.github.fallOut015.cartomancy.entity.EntityTypeCartomancy;
import io.github.fallOut015.cartomancy.item.ItemModelsPropertiesCartomancy;
import io.github.fallOut015.cartomancy.item.ItemsCartomancy;
import io.github.fallOut015.cartomancy.item.card.Cards;
import io.github.fallOut015.cartomancy.particles.ParticleTypesCartomancy;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("cartomancy")
public class MainCartomancy {
    // TODO add village witch, with an altar inside and a chest with cards
    public static final Logger LOGGER = LogManager.getLogger();

    public MainCartomancy() {
        ItemsCartomancy.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityTypeCartomancy.register(FMLJavaModLoadingContext.get().getModEventBus());
        ParticleTypesCartomancy.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        Cards.setup(event);
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistryCartomancy.doClientStuff(event);
        ItemModelsPropertiesCartomancy.doClientStuff(event);
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {

    }
    private void processIMC(final InterModProcessEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onParticleFactoryRegistry(final ParticleFactoryRegisterEvent event) {
            ParticleManagerCartomancy.onParticleFactoryRegistry(event);
        }
    }
    @Mod.EventBusSubscriber
    public static class Events {
        @SubscribeEvent
        public static void onLivingEventLivingUpdate(final LivingEvent.LivingUpdateEvent event) {
            //if(event.getEntityLiving() instanceof PlayerEntity) {
                //TheChariotCard THE_CHARIOT = ((TheChariotCard) Card.THE_CHARIOT);
                /*if(THE_CHARIOT.cardPresent() && THE_CHARIOT.getCasterID() == event.getEntityLiving().getUniqueID()) {
                    if(event.getEntityLiving().isSneaking()) {
                        Vector3d warpPosition = THE_CHARIOT.getWarpPosition();
                        event.getEntityLiving().attemptTeleport(warpPosition.getX(), warpPosition.getY(), warpPosition.getZ(), true);
                        THE_CHARIOT.postDeactivate();
                    }
                }*/
            //}
        }
    }
}