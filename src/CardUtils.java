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

    public static String show(int[][] pickedCards) {
        StringBuilder cardInfo = new StringBuilder();

        for (int[] card : pickedCards) {
            int value = card[0];
            int suit = card[1];
            printColoredCard(getCardName(value, suit), suit);
        }
        return cardInfo.toString();
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
        for(int i = 0; i < pickedCards.length; i++) {
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


    public static int[][][] pickRandCard(int[][] pickedCards, int[][] remainingCards) {
        Random random = new Random();
        int randomNumber = random.nextInt(remainingCards.length);
        return pickCardByIndex(remainingCards, randomNumber, pickedCards);
    }

    public static int[][] mixCards() {
        int[][] pickedCards = new int[0][2];
        int[][] remainingCards = createDeckOfCards(1, 1);

        while (remainingCards.length > 0) {
            int[][][] result = pickRandCard(pickedCards, remainingCards);
            pickedCards = result[0];
            remainingCards = result[1];
        }

        return pickedCards;
    }

    public static int[][][] drawNCards() {
        int[][] deck=CardUtils.mixCards();

        Random random = new Random();
        int cardIndex = random.nextInt(11) + 20;

        int[][] drawnCards = new int[cardIndex + 1][2];

        // Copy the cards from deck to drawnCards
        for (int i = 0; i <= cardIndex; i++) {
            drawnCards[i] = deck[i];
        }

        // Calculate the size of the updatedDeck
        int updatedDeckSize = deck.length - (cardIndex + 1);
        int[][] updatedDeck = new int[updatedDeckSize][2];

        // Copy the remaining cards from deck to updatedDeck
        for (int i = cardIndex + 1, j = 0; i < deck.length; i++, j++) {
            updatedDeck[j] = deck[i];
        }

        int[][][] result = new int[2][][];
        result[0] = drawnCards;
        result[1] = updatedDeck;

        return result;
    }

    public static int[][] discardCards() {
        int[][][] cards = drawNCards();
        int [][] drawnCards = cards[0];
        int [][] remainingCards = cards[1];
        int[][] deck = new int[52][2];

        // Copy drawnCards into combinedCards
        for (int i = 0; i < drawnCards.length; i++) {
            deck[i] = drawnCards[i];
        }

        // Copy remainingCards into combinedCards starting after the drawnCards
        for (int i = 0; i < remainingCards.length; i++) {
            deck[i + drawnCards.length] = remainingCards[i];
        }

        return deck;
    }
}
