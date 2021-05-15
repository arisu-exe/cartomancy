package io.github.fallOut015.cartomancy.item.card;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class Cards {
    public static Card THE_FOOL;
    public static Card THE_MAGICIAN;
    public static Card THE_HIGH_PRIESTESS;
    public static Card THE_EMPRESS;
    public static Card THE_EMPEROR;
    public static Card THE_HIEROPHANT;
    public static Card THE_LOVERS;
    public static Card THE_CHARIOT;
    public static Card STRENGTH;
    public static Card THE_HERMIT;
    public static Card WHEEL_OF_FORTUNE;
    public static Card JUSTICE;
    public static Card THE_HANGED_MAN;
    public static Card DEATH;
    public static Card TEMPERANCE;
    public static Card THE_DEVIL;
    public static Card THE_TOWER;
    public static Card THE_STAR;
    public static Card THE_MOON;
    public static Card THE_SUN;
    public static Card JUDGEMENT;
    public static Card THE_WORLD;

    public static Card ACE_OF_WANDS;
    public static Card TWO_OF_WANDS;
    public static Card THREE_OF_WANDS;
    public static Card FOUR_OF_WANDS;
    public static Card FIVE_OF_WANDS;
    public static Card SIX_OF_WANDS;
    public static Card SEVEN_OF_WANDS;
    public static Card EIGHT_OF_WANDS;
    public static Card NINE_OF_WANDS;
    public static Card TEN_OF_WANDS;
    public static Card PAGE_OF_WANDS;
    public static Card KNIGHT_OF_WANDS;
    public static Card QUEEN_OF_WANDS;
    public static Card KING_OF_WANDS;

    public static Card ACE_OF_PENTACLES;
    public static Card TWO_OF_PENTACLES;
    public static Card THREE_OF_PENTACLES;
    public static Card FOUR_OF_PENTACLES;
    public static Card FIVE_OF_PENTACLES;
    public static Card SIX_OF_PENTACLES;
    public static Card SEVEN_OF_PENTACLES;
    public static Card EIGHT_OF_PENTACLES;
    public static Card NINE_OF_PENTACLES;
    public static Card TEN_OF_PENTACLES;
    public static Card PAGE_OF_PENTACLES;
    public static Card KNIGHT_OF_PENTACLES;
    public static Card QUEEN_OF_PENTACLES;
    public static Card KING_OF_PENTACLES;

    public static Card ACE_OF_CUPS;
    public static Card TWO_OF_CUPS;
    public static Card THREE_OF_CUPS;
    public static Card FOUR_OF_CUPS;
    public static Card FIVE_OF_CUPS;
    public static Card SIX_OF_CUPS;
    public static Card SEVEN_OF_CUPS;
    public static Card EIGHT_OF_CUPS;
    public static Card NINE_OF_CUPS;
    public static Card TEN_OF_CUPS;
    public static Card PAGE_OF_CUPS;
    public static Card KNIGHT_OF_CUPS;
    public static Card QUEEN_OF_CUPS;
    public static Card KING_OF_CUPS;

    public static Card ACE_OF_SWORDS;
    public static Card TWO_OF_SWORDS;
    public static Card THREE_OF_SWORDS;
    public static Card FOUR_OF_SWORDS;
    public static Card FIVE_OF_SWORDS;
    public static Card SIX_OF_SWORDS;
    public static Card SEVEN_OF_SWORDS;
    public static Card EIGHT_OF_SWORDS;
    public static Card NINE_OF_SWORDS;
    public static Card TEN_OF_SWORDS;
    public static Card PAGE_OF_SWORDS;
    public static Card KNIGHT_OF_SWORDS;
    public static Card QUEEN_OF_SWORDS;
    public static Card KING_OF_SWORDS;

    public static void setup(final FMLCommonSetupEvent event) {
        THE_FOOL = new EntityCard("the_fool", 0);
        THE_MAGICIAN = new EntityCard("the_magician", 1);
        THE_HIGH_PRIESTESS = new EntityCard("the_high_priestess", 2); // sanctuary
        THE_EMPRESS = new EntityCard("the_empress", 3); // faeries
        THE_EMPEROR = new EntityCard("the_emperor", 4); // darkness aura
        THE_HIEROPHANT = new EntityCard("the_hierophant", 5);
        THE_LOVERS = new EntityCard("the_lovers", 6);
        THE_CHARIOT = new TheChariotCard("the_chariot", 7); // warp
        STRENGTH = new EntityCard("strength", 8); // enhance
        THE_HERMIT = new EntityCard("the_hermit", 9); // abyss
        WHEEL_OF_FORTUNE = new EntityCard("wheel_of_fortune", 10); // chance
        JUSTICE = new EntityCard("justice", 11); // eruption
        THE_HANGED_MAN = new EntityCard("the_hanged_man", 12); // ensnare
        DEATH = new EntityCard("death", 13); // undead army
        TEMPERANCE  = new EntityCard("temperance", 14); // whirlwind
        THE_DEVIL = new EntityCard("the_devil", 15); // hellfire
        THE_TOWER = new TheTowerCard("the_tower", 16); // tempest
        THE_STAR = new EntityCard("the_star", 17); // starfall
        THE_MOON = new EntityCard("the_moon", 18); // moon beam
        THE_SUN = new TheSunCard("the_sun", 19); // sun beam
        JUDGEMENT = new JudgementCard("judgement", 20); // divine arrows
        THE_WORLD = new EntityCard("the_world", 21); // gravity

        ACE_OF_WANDS = new EntityCard("ace_of_wands", 22, 1, Card.Suit.WANDS); // valorant blades
        TWO_OF_WANDS = new EntityCard("two_of_wands", 23, 2, Card.Suit.WANDS);
        THREE_OF_WANDS = new EntityCard("three_of_wands", 24, 3, Card.Suit.WANDS);
        FOUR_OF_WANDS = new EntityCard("four_of_wands", 25, 4, Card.Suit.WANDS);
        FIVE_OF_WANDS = new EntityCard("five_of_wands", 26, 5, Card.Suit.WANDS);
        SIX_OF_WANDS = new EntityCard("six_of_wands", 27, 6, Card.Suit.WANDS);
        SEVEN_OF_WANDS = new EntityCard("seven_of_wands", 28, 7, Card.Suit.WANDS);
        EIGHT_OF_WANDS = new EntityCard("eight_of_wands", 29, 8, Card.Suit.WANDS);
        NINE_OF_WANDS = new EntityCard("nine_of_wands", 30, 9, Card.Suit.WANDS);
        TEN_OF_WANDS = new EntityCard("ten_of_wands", 31, 10, Card.Suit.WANDS);
        PAGE_OF_WANDS = new EntityCard("page_of_wands", 32, 11, Card.Suit.WANDS);
        KNIGHT_OF_WANDS = new EntityCard("knight_of_wands", 33, 12, Card.Suit.WANDS);
        QUEEN_OF_WANDS = new EntityCard("queen_of_wands", 34, 13, Card.Suit.WANDS);
        KING_OF_WANDS = new EntityCard("king_of_wands", 35, 14, Card.Suit.WANDS);

        ACE_OF_PENTACLES = new EntityCard("ace_of_pentacles", 36, 1, Card.Suit.PENTACLES);
        TWO_OF_PENTACLES = new EntityCard("two_of_pentacles", 37, 2, Card.Suit.PENTACLES);
        THREE_OF_PENTACLES = new EntityCard("three_of_pentacles", 38, 3, Card.Suit.PENTACLES);
        FOUR_OF_PENTACLES = new EntityCard("four_of_pentacles", 39, 4, Card.Suit.PENTACLES);
        FIVE_OF_PENTACLES = new EntityCard("five_of_pentacles", 40, 5, Card.Suit.PENTACLES);
        SIX_OF_PENTACLES = new EntityCard("six_of_pentacles", 41, 6, Card.Suit.PENTACLES);
        SEVEN_OF_PENTACLES = new EntityCard("seven_of_pentacles", 42, 7, Card.Suit.PENTACLES);
        EIGHT_OF_PENTACLES = new EntityCard("eight_of_pentacles", 43, 8, Card.Suit.PENTACLES);
        NINE_OF_PENTACLES = new EntityCard("nine_of_pentacles", 44, 9, Card.Suit.PENTACLES);
        TEN_OF_PENTACLES = new EntityCard("ten_of_pentacles", 45, 10, Card.Suit.PENTACLES);
        PAGE_OF_PENTACLES = new EntityCard("page_of_pentacles", 46, 11, Card.Suit.PENTACLES);
        KNIGHT_OF_PENTACLES = new EntityCard("knight_of_pentacles", 47, 12, Card.Suit.PENTACLES);
        QUEEN_OF_PENTACLES = new EntityCard("queen_of_pentacles", 48, 13, Card.Suit.PENTACLES);
        KING_OF_PENTACLES = new EntityCard("king_of_pentacles", 49, 14, Card.Suit.PENTACLES);

        ACE_OF_CUPS = new EntityCard("ace_of_cups", 50, 1, Card.Suit.CUPS);
        TWO_OF_CUPS = new EntityCard("two_of_cups", 51, 2, Card.Suit.CUPS);
        THREE_OF_CUPS = new EntityCard("three_of_cups", 52, 3, Card.Suit.CUPS);
        FOUR_OF_CUPS = new EntityCard("four_of_cups", 53, 4, Card.Suit.CUPS);
        FIVE_OF_CUPS = new EntityCard("five_of_cups", 54, 5, Card.Suit.CUPS);
        SIX_OF_CUPS = new EntityCard("six_of_cups", 55, 6, Card.Suit.CUPS);
        SEVEN_OF_CUPS = new EntityCard("seven_of_cups", 56, 7, Card.Suit.CUPS);
        EIGHT_OF_CUPS = new EntityCard("eight_of_cups", 57, 8, Card.Suit.CUPS);
        NINE_OF_CUPS = new EntityCard("nine_of_cups", 58, 9, Card.Suit.CUPS);
        TEN_OF_CUPS = new EntityCard("ten_of_cups", 59, 10, Card.Suit.CUPS);
        PAGE_OF_CUPS = new EntityCard("page_of_cups", 60, 11, Card.Suit.CUPS);
        KNIGHT_OF_CUPS = new EntityCard("knight_of_cups", 61, 12, Card.Suit.CUPS);
        QUEEN_OF_CUPS = new EntityCard("queen_of_cups", 62, 13, Card.Suit.CUPS);
        KING_OF_CUPS = new EntityCard("king_of_cups", 63, 14, Card.Suit.CUPS);

        ACE_OF_SWORDS = new EntityCard("ace_of_swords", 64, 1, Card.Suit.SWORDS);
        TWO_OF_SWORDS = new EntityCard("two_of_swords", 65, 2, Card.Suit.SWORDS);
        THREE_OF_SWORDS = new EntityCard("three_of_swords", 66, 3, Card.Suit.SWORDS);
        FOUR_OF_SWORDS = new EntityCard("four_of_swords", 67, 4, Card.Suit.SWORDS);
        FIVE_OF_SWORDS = new EntityCard("five_of_swords", 68, 5, Card.Suit.SWORDS);
        SIX_OF_SWORDS = new EntityCard("six_of_swords", 69, 6, Card.Suit.SWORDS);
        SEVEN_OF_SWORDS = new EntityCard("seven_of_swords", 70, 7, Card.Suit.SWORDS);
        EIGHT_OF_SWORDS = new EntityCard("eight_of_swords", 71, 8, Card.Suit.SWORDS);
        NINE_OF_SWORDS = new EntityCard("nine_of_swords", 72, 9, Card.Suit.SWORDS);
        TEN_OF_SWORDS = new EntityCard("ten_of_swords", 73, 10, Card.Suit.SWORDS);
        PAGE_OF_SWORDS = new EntityCard("page_of_swords", 74, 11, Card.Suit.SWORDS);
        KNIGHT_OF_SWORDS = new EntityCard("knight_of_swords", 75, 12, Card.Suit.SWORDS);
        QUEEN_OF_SWORDS = new EntityCard("queen_of_swords", 76, 13, Card.Suit.SWORDS);
        KING_OF_SWORDS = new EntityCard("king_of_swords", 77, 14, Card.Suit.SWORDS);
    }
}