package io.github.fallOut015.cartomancy.item.card;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.IDataSerializer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class Card {
    public static final Map<String, Card> ID_MAP;
    static {
        ID_MAP = new HashMap<>();
    }

    enum Arcana {
        MAJOR, MINOR
    }
    enum Suit {
        WANDS, PENTACLES, CUPS, SWORDS
    }

    final String id;
    final float number;
    final Arcana arcana;
    final int rank;
    final @Nullable
    Suit suit;
    final ResourceLocation texture;

    Card(final String id, final float number) {
        this.id = id;
        this.number = number;
        this.arcana = Arcana.MAJOR;
        this.rank = 0;
        this.suit = null;
        this.texture = new ResourceLocation("cartomancy", "textures/item/" + id + ".png");

        ID_MAP.put(this.id, this);
    }
    Card(final String id, final float number, final int rank, final Suit suit) {
        this.id = id;
        this.number = number;
        this.arcana = Arcana.MINOR;
        this.rank = rank;
        this.suit = suit;
        this.texture = new ResourceLocation("cartomancy", "textures/item/" + id + ".png");

        ID_MAP.put(this.id, this);
    }

    public final String getID() {
        return this.id;
    }
    public final float getNumber() {
        return this.number;
    }
    public final Arcana getArcana() {
        return this.arcana;
    }
    public final int getRank() {
        return this.rank;
    }
    public final @Nullable Suit getSuit() {
        return this.suit;
    }
    public final ResourceLocation getTexture() {
        return this.texture;
    }

    public static Card getFromID(final String id) {
        return ID_MAP.get(id);
    }
    public static Card drawRandom() {
        return ID_MAP.values().toArray(new Card [] {})[new Random().nextInt(ID_MAP.values().size())];
    }

    public static final IDataSerializer<Optional<Card>> OPTIONAL_CARD = new IDataSerializer<Optional<Card>>() {
        public void write(PacketBuffer buf, Optional<Card> value) {
            if(value.isPresent()) {
                buf.writeUtf(value.get().getID());
            } else {
                buf.writeUtf("");
            }
        }

        public Optional<Card> read(PacketBuffer buf) {
            String card = buf.readUtf();
            if(card != "") {
                return Optional.of(Card.getFromID(buf.readUtf()));
            } else {
                return Optional.empty();
            }
        }

        public Optional<Card> copy(Optional<Card> value) {
            if(value.isPresent()) {
                return Optional.of(value.get());
            } else {
                return Optional.empty();
            }
        }
    };

    static {
        DataSerializers.registerSerializer(OPTIONAL_CARD); // ForgeRegistries.DATA_SERIALIZERS ?
    }
}