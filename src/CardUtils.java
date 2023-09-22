public class CardUtils {
    public static int[][] createDeckOfCards(int startingValue, int startingSuit) {
        int[][] deck = new int[52][2];
        int index = 0;

        // Create cards from the starting card to the end
        for (int suit = startingSuit; suit <= 4; suit++) {
            for (int value = (suit == startingSuit ? startingValue : 1); value <= 13; value++) {
                deck[index][0] = value;
                deck[index][1] = suit;
                index++;
            }
        }

        // Create cards from the beginning to the starting card
        for (int suit = 1; suit < startingSuit+1; suit++) {
            for (int value = 1; value < (suit==startingSuit?startingValue:14); value++) {
                deck[index][0] = value;
                deck[index][1] = suit;
                index++;
            }
        }

        return deck;
    }


    public static String getCardName(int value, int suit) {
        return value + "-" + suit;
    }

    public static void show(int startingValue, int startingSuit) {
        int[][] deck = createDeckOfCards(startingValue, startingSuit);

        for (int[] card : deck) {
            int value = card[0];
            int suit = card[1];
            printColoredCard(getCardName(value, suit), suit);
        }
    }

    public static void printColoredCard(String cardName, int suit) {
        String colorCode;
        if (suit == 1 || suit == 2) {
            colorCode = "\u001B[31m"; // Red color for Diamonds and Hearts
        } else {
            colorCode = "\u001B[35m"; // Purple color for Spades and Clubs
        }

        String resetCode = "\u001B[0m";

        System.out.println(colorCode + cardName + resetCode);
    }

}
