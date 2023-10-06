import java.util.HashMap;
import java.util.Scanner;

public class BlackJack {

    public static void start() {
        int[][][] cards = BlackJack.startingCards();
        hitStandMenu(cards);
    }

    public static void hitStandMenu(int[][][] startingCards) {

        int[][] remainingCards = startingCards()[2];
        int[][] cards = startingCards()[0];
        int[][] dealerCards = startingCards()[1];
        for (int i = 0; i < cards.length ; i++) {
            System.out.println(cards[i][0]);
        }
        boolean exit = true;

        while (exit){
            System.out.println("What are you gonna do? \n1 - hit\n2 - Stand");
            Scanner object = new Scanner(System.in);
            int choice = object.nextInt();
            switch (choice) {
                case 1:
                    int[][][] result = givingCards(remainingCards, cards);
                    remainingCards = result[1];
                    cards = result[0];
                    for (int i = 0; i < cards.length ; i++) {
                        System.out.println(cards[i][0]);
                    }
                    System.out.println("your cards value : " + score(cards));
                    break;
                case 2:
                    int[][][] dealerhand = stand(remainingCards,dealerCards);
                    remainingCards = dealerhand[1];
                    System.out.println("dealer cards value : " + score(dealerhand[0]));
                    result(score(dealerhand[0]),score(cards));
                    exit=false;
                    break;
            }
        }
    }

    public static int[][][] startingCards() {

        int[][] remainingCards = CardUtils.drawNCards()[0];
        int[][] dealerHand = new int[0][2];
        int[][] playerHand = new int[0][2];

        int[][][] playersFirstCards = givingCards(remainingCards, playerHand);
        int[][] playerHand1 = playersFirstCards[0];
        int[][] remainingCards1 = playersFirstCards[1];

        int[][][] dealersFirstCards = givingCards(remainingCards1, dealerHand);
        int[][] dealerHand1 = dealersFirstCards[0];
        int[][] remainingCards2 = dealersFirstCards[1];

        int[][][] playersSecondCards = givingCards(remainingCards2, playerHand1);
        int[][] playerHand2 = playersSecondCards[0];
        int[][] remainingCards3 = playersSecondCards[1];

        int[][][] dealersSecondCards = givingCards(remainingCards3, dealerHand1);
        int[][] dealerHand2 = dealersSecondCards[0];
        int[][] remainingCards4 = dealersSecondCards[1];

        int[][][] result = new int[3][][];
        result[0] = playerHand2;
        result[1] = dealerHand2;
        result[2] = remainingCards4;

        return result;
    }

    public static int[][][] givingCards(int[][] cards, int[][] hand) {

        int[][] remainingCards = new int[cards.length - 1][2];
        int[][] newHand = new int[hand.length + 1][2];

        for (int i = 0; i < remainingCards.length; i++) {
            remainingCards[i][0] = cards[i][0];
            remainingCards[i][1] = cards[i][1];
        }

        for (int i = 0; i < hand.length; i++) {
            newHand[i] = hand[i];
        }

        newHand[hand.length][0] = cards[cards.length - 1][0];
        newHand[hand.length][1] = cards[cards.length - 1][1];

        int[][][] result = new int[2][][];
        result[0] = newHand;
        result[1] = remainingCards;

        return result;
    }

    public static int score(int[][] hand) {
        int cardsValue = 0;
        for (int i = 0; i < hand.length; i++) {
            if (cardsValue <= 21 && hand[i][0] == 1) {
                cardsValue += 11;
                if (cardsValue == 21) {
                    System.out.println("BLACKJACK");
                }
            } else {
                cardsValue += Math.min(hand[i][0], 10);
            }

        }
        return cardsValue;
    }


    public static int[][][] stand(int[][] remainingCards,int[][] dealer) {

        int[][][] newCards = new int[2][][];
        newCards[0]=dealer;
        newCards[1]=remainingCards;
        while(score(dealer)<17){
            newCards = givingCards(remainingCards, dealer);
            dealer=newCards[0];
            remainingCards = newCards[1];

        }
        for (int i = 0; i < newCards[0].length; i++) {
            System.out.println(newCards[0][i][0]);
        }
        return newCards;
    }

    public static void result(int dealer, int player) {
        if(player >21){
            System.out.println("YOU LOST!");
        } else if (dealer > 21) {
            System.out.println("YOU WIN!");
        } else if (dealer > player) {
            System.out.println("YOU LOST!");
        } else if (dealer < player) {
            System.out.println("YOU WIN!");
        }else
            System.out.println("DRAW!");
    }
}
