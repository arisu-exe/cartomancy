package io.github.fallOut015.cartomancy.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsCartomancy {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "cartomancy");



    public static final RegistryObject<Item> CARD = ITEMS.register("card", () -> new CardItem(new Item.Properties().tab(ItemGroupCartomancy.TAB_CARTOMANCY).rarity(Rarity.UNCOMMON)));



    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
