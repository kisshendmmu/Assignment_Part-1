package official;

import java.util.Scanner;
import java.util.ArrayList;

public class GameRunner {

    public static void main(String[] args) {

        System.out.println("C = Club");
        System.out.println("D= Diamond");
        System.out.println("H = Heart");
        System.out.println("S = Spade");
        System.out.println("");
        System.out.println("Commands: ");
        System.out.println("   s > Start a new game.");
        System.out.println("   x > Exit the game.");
        System.out.println("   d > Draw cards from deck until a playable card is obtained. If the deck is empty, skip to the next player.");
        System.out.println("card > a card played by the current player.");
        System.out.println("");
        System.out.println("Trick #1");

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();
        //System.out.print("Deck: ");
        //player1.printOut(playingDeck);

        ArrayList<Card> centerCards = new ArrayList<>();


        //Deck center = new Deck();
        Deck player1 = new Deck(); //change 
        Deck player2 = new Deck();
        Deck player3 = new Deck();
        Deck player4 = new Deck();

        //center.draw(playingDeck);
        Card leadCard = playingDeck.getCard(0); // Get the first card in the deck
        playingDeck.removeCard(0); // Remove the first card from the deck
        centerCards.add(leadCard); // Add the lead card to the center cards

        for(int i = 0; i < 7; i++){
            player1.draw(playingDeck);
            player2.draw(playingDeck);
            player3.draw(playingDeck);
            player4.draw(playingDeck);
        }

        System.out.println("Center: " + centerCards + "\n");
        System.out.print("Player1: ");
        player1.printOut();
        System.out.print("Player2: ");
        player2.printOut();
        System.out.print("Player3: ");
        player3.printOut();
        System.out.print("Player4: ");
        player4.printOut();
        playingDeck.printOut();

        // Determine first player based on the lead card
        String firstPlayer = determineFirstPlayer(leadCard);
        System.out.println("First Player: " + firstPlayer + "\n");

        //boolean game = true;
        //trick_count = 1;
       // Scanner scanner = new Scanner(System.in);
        //while(true) {
        //System.out.print("Turn: ");
        //String aString = scanner.nextLine();

        //}

    }
    


    private static String determineFirstPlayer(Card leadCard) {


        String cardValue = leadCard.getValue().toString();
        if (cardValue.equals("A") || cardValue.equals("5") || cardValue.equals("9") || cardValue.equals("K")) {
            return "Player1";
        } else if (cardValue.equals("2") || cardValue.equals("6") || cardValue.equals("10")) {
            return "Player2";
        } else if (cardValue.equals("3") || cardValue.equals("7") || cardValue.equals("J")) {
            return "Player3";
        } else if (cardValue.equals("4") || cardValue.equals("8") || cardValue.equals("Q")) {
            return "Player4";
        } else {
            return "Unknown";
        }
    }
}