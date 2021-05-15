package io.github.fallOut015.cartomancy.item;

import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ItemModelsPropertiesCartomancy {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        ItemModelsProperties.register(ItemsCartomancy.CARD.get(), new ResourceLocation("cartomancy", "number"), (stack, world, entity) -> {
            try {
                return CardItem.getCard(stack).getNumber();
            } catch(NullPointerException exception) {
                return -1;
            }
        });
    }
}