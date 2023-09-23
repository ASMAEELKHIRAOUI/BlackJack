import java.util.Random;

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

    public static int[][][] pickCardByIndex(int[][] deck, int index, int[][] pickedCards) {
        if (index < 0 || index >= deck.length) {
            pickCardByIndex(deck, index, pickedCards);
        }

        int[][] pickedCard = new int[1][2];
        pickedCard[0] = deck[index];

        // Add the picked card to the pickedCards array
        int[][] updatedPickedCards = new int[pickedCards.length + 1][2];
        for (int i = 0; i < pickedCards.length; i++) {
            updatedPickedCards[i] = pickedCards[i];
        }
        updatedPickedCards[pickedCards.length] = pickedCard[0];

        // Remove the picked card from the deck
        int[][] updatedDeck = new int[deck.length - 1][2];
        int deckIndex = 0;

        for (int i = 0; i < deck.length; i++) {
            if (i != index) {
                updatedDeck[deckIndex] = deck[i];
                deckIndex++;
            }
        }

        int[][][] result = new int[2][][];
        result[0] = updatedPickedCards;
        result[1] = updatedDeck;

        return result;
    }


    public static void pickRandCard(int[][] pickedCards, int[][] remainingCards) {
        Random random = new Random();
        int randomNumber = random.nextInt(51);
        pickCardByIndex(remainingCards, randomNumber, pickedCards);
    }

    public static void mixCards() {
        
    }

}
