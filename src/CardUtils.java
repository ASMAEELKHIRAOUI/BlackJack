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
            colorCode = "\u001B[31m";
        } else {
            colorCode = "\u001B[35m";
        }

        String resetCode = "\u001B[0m";

        System.out.println(colorCode + cardName + resetCode);
    }

    public static int[][][] pickCardByIndex(int[][] deck, int index) {
        if (index < 0 || index >= deck.length) {
            System.out.println("Invalid index. Index must be between 0 and " + (deck.length - 1));
            return new int[0][2][0];
        }

        int[][] pickedCard = new int[1][2];
        pickedCard[0] = deck[index];

        int[][] remainingCards = new int[deck.length - 1][2];
        int remainingIndex = 0;

        for (int i = 0; i < deck.length; i++) {
            if (i != index) {
                remainingCards[remainingIndex] = deck[i];
                remainingIndex++;
            }
        }

        int[][][] result = new int[2][][];
        result[0] = pickedCard;
        result[1] = remainingCards;

        return result;
    }

}
