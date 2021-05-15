package io.github.fallOut015.cartomancy.item;

import io.github.fallOut015.cartomancy.entity.EntityTypeCartomancy;
import io.github.fallOut015.cartomancy.entity.item.CardEntity;
import io.github.fallOut015.cartomancy.item.card.Card;
import io.github.fallOut015.cartomancy.item.card.EntityCard;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.*;

public class CardItem extends Item {
    // divinate? (tell the future or bend the future)
    // spell cast?
    // throw cards
    // play as a game (shuffle and draw and stuff)
    // accurate tarot reading

    public CardItem(Item.Properties properties) {
        super(properties);
    }

    public static void putCard(ItemStack stack, Card card) {
        stack.getOrCreateTag().putString("card", card.getID());
    }
    public static @Nullable Card getCard(ItemStack stack) {
        String card = stack.getOrCreateTag().getString("card");
        if(card.equals("")) {
            return null;
        } else {
            return Card.getFromID(card);
        }
    }
    public static boolean hasCard(ItemStack stack, @Nullable Card card) {
        return getCard(stack) == card;
    }
    public static void setReversed(ItemStack stack, boolean reversed) {
        stack.getOrCreateTag().putBoolean("reversed", reversed);
    }
    public static boolean isReversed(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("reversed");
    }
    public static void setAttuned(ItemStack stack, boolean attuned) {
        stack.getOrCreateTag().putBoolean("attuned", attuned);
    }
    public static boolean isAttuned(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("attuned");
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return !hasCard(stack, null) && isAttuned(stack);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(hasCard(stack, null)) {
            tooltip.add(new StringTextComponent("???").withStyle(TextFormatting.YELLOW, TextFormatting.ITALIC));
        } else {
            if(isAttuned(stack)) {
                tooltip.add(new TranslationTextComponent("item.cartomancy.card.attuned").withStyle(TextFormatting.YELLOW, TextFormatting.ITALIC));
            }
             /*tooltip.add(new TranslationTextComponent("item.cartomancy.card." + getCard(stack).getID() + ".description").mergeStyle(TextFormatting.YELLOW, TextFormatting.ITALIC));
             if(isReversed(stack)) {
                 tooltip.add(new TranslationTextComponent("item.cartomancy.card.reversed").mergeStyle(TextFormatting.LIGHT_PURPLE, TextFormatting.ITALIC));
                 tooltip.add(new TranslationTextComponent("item.cartomancy.card." + getCard(stack).getID() + ".reversed").mergeStyle(TextFormatting.LIGHT_PURPLE, TextFormatting.ITALIC));
             } else {
                 tooltip.add(new TranslationTextComponent("item.cartomancy.card.upright").mergeStyle(TextFormatting.WHITE, TextFormatting.ITALIC));
                 tooltip.add(new TranslationTextComponent("item.cartomancy.card." + getCard(stack).getID() + ".upright").mergeStyle(TextFormatting.WHITE, TextFormatting.ITALIC));
             }*/
        }

        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if(hasCard(playerIn.getItemInHand(handIn), null)) {
            playerIn.getItemInHand(handIn).shrink(1); // TODO shrink in creative mode too
            ItemStack newCard = new ItemStack(ItemsCartomancy.CARD.get());
            putCard(newCard, Card.drawRandom());
            setReversed(newCard, new Random().nextBoolean());
            setAttuned(newCard, true);
            if(playerIn.getItemInHand(handIn).isEmpty()) {
                playerIn.setItemSlot(handIn == Hand.MAIN_HAND ? EquipmentSlotType.MAINHAND : EquipmentSlotType.OFFHAND, newCard);
            } else {
                if(!playerIn.addItem(newCard) && !worldIn.isClientSide) {
                    ItemEntity item = new ItemEntity(worldIn, playerIn.getX(), playerIn.getY() + playerIn.getEyeHeight() - 0.3f, playerIn.getZ(), newCard);
                    item.moveTo(playerIn.position());
                    worldIn.addFreshEntity(item);
                }
            }

            // TODO fix accidental throw

            return ActionResult.consume(playerIn.getItemInHand(handIn));
        } else {
            if(!worldIn.isClientSide && getCard(playerIn.getItemInHand(handIn)) instanceof EntityCard) {
                CardEntity card = EntityTypeCartomancy.CARD.get().create(worldIn);
                card.setPos(playerIn.getX(), playerIn.getY() + playerIn.getEyeHeight(), playerIn.getZ());
                card.setCardType(getCard(playerIn.getItemInHand(handIn)));
                card.setDeltaMovement(playerIn.getLookAngle().x() * 0.5f, playerIn.getLookAngle().y() * 0.5f, playerIn.getLookAngle().z() * 0.5f);
                card.setThrown();
                card.setAttuned(isAttuned(playerIn.getItemInHand(handIn)));
                if(playerIn instanceof ServerPlayerEntity) {
                    card.setCaster((ServerPlayerEntity) playerIn);
                }
                worldIn.addFreshEntity(card);
                if(!playerIn.isCreative()) {
                    playerIn.getItemInHand(handIn).shrink(1);
                }

                return ActionResult.success(playerIn.getItemInHand(handIn));
            }

            return ActionResult.pass(playerIn.getItemInHand(handIn));
        }
    }
    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        if(this.allowdedIn(group)) {
            List<Card> cards = new ArrayList<>(Card.ID_MAP.values());
            cards.sort((o1, o2) -> (int) (o1.getNumber() - o2.getNumber()));
            for(Card card : cards) {
                ItemStack stack = new ItemStack(ItemsCartomancy.CARD.get());
                putCard(stack, card);
                setAttuned(stack, true);
                items.add(stack);
            }
            items.add(new ItemStack(ItemsCartomancy.CARD.get()));
        } // TODO fix not showing up in search
    }
    @Override
    public int getItemStackLimit(ItemStack stack) {
        //return hasCard(stack, null) ? 64 : 1;
        return 16;
    }
    @Override
    public ITextComponent getName(ItemStack stack) {
        if(hasCard(stack, null)) {
            return super.getName(stack);
        } else {
            return new TranslationTextComponent("item.cartomancy.card." + getCard(stack).getID()).withStyle(TextFormatting.YELLOW);
        }
    }
}