package io.github.fallOut015.cartomancy.client.registry;

import io.github.fallOut015.cartomancy.client.renderer.entity.CardRenderer;
import io.github.fallOut015.cartomancy.client.renderer.entity.DivineArrowRenderer;
import io.github.fallOut015.cartomancy.client.renderer.entity.SigilRenderer;
import io.github.fallOut015.cartomancy.entity.EntityTypeCartomancy;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class RenderingRegistryCartomancy {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeCartomancy.CARD.get(), CardRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeCartomancy.DIVINE_ARROW.get(), DivineArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityTypeCartomancy.SIGIL.get(), SigilRenderer::new);
    }
}