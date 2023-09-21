import java.util.ArrayList;
import java.util.List;

public class CardUtils {
    public static List<int[]> createDeckOfCards() {
        List<int[]> deckOfCards = new ArrayList<int[]>();

        for (int suit = 1; suit <= 4; suit++) {
            for (int value = 1; value <= 13; value++) {
                deckOfCards.add(new int[]{value, suit});
            }
        }

        for (int[] card : deckOfCards) {
            int value = card[0];
            int suit = card[1];
            printColoredCard(getCardName(value, suit), suit);
        }

        return deckOfCards;
    }

    public static String getCardName(int value, int suit) {
        String[] suits = {"♥️", "♦️", "♣️", "♠️"};
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        return values[value - 1] + suits[suit - 1];
    }

    public static void printColoredCard(String cardName, int suit) {
        String colorCode;
        if (suit == 1 || suit == 2) {
            colorCode = "\u001B[31m";
        } else {
            colorCode = "\u001B[35m";
        }

        String resetCode = "\u001B[0m";

        System.out.println(colorCode + cardName + resetCode);
    }
}
