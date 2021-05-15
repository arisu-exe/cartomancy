package io.github.fallOut015.cartomancy.item;

import io.github.fallOut015.cartomancy.item.card.Card;
import io.github.fallOut015.cartomancy.item.card.Cards;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemGroupCartomancy {
    public static final ItemGroup TAB_CARTOMANCY = new ItemGroup("cartomancy") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            ItemStack card = new ItemStack(ItemsCartomancy.CARD.get());
            CardItem.putCard(card, Cards.THE_MAGICIAN);
            return card;
        }
    };
}
