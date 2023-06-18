import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.Random;
import java.util.List;
import java.util.Collections;

public class GameRunner {

    private static Vector<Player> turn;
    private static Player player1;
    private static Player player2;
    private static Player player3;
    private static Player player4;
    private static Vector<Card> center;
    private ArrayList<Card> deck;
    public int noPlayer;
    private static int trick_count;
    


    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean run = true;
        while(run) {
            new GameRunner().run();
        }
    }
        
        public GameRunner() {
            center = new Vector<>();
            turn = null;
            trick_count = 1;
            player1 = new Player("Player1");
            player2 = new Player("Player2");
            player3 = new Player("Player3");
            player4 = new Player("Player4");
        
            
        }
        public void run() {
            Scanner scanner = new Scanner(System.in);
    
            while (true) {
                // game setup
                deck = FullDeck();
                

                player1.initialHands(dealCards()); //deals cards to players
                player2.initialHands(dealCards());
                player3.initialHands(dealCards());
                player4.initialHands(dealCards());
                center.add(deck.get(0));
                deck.remove(0);


                // determine first player
                turn = determineFirstTurn(center.get(0));
    
                while (true) {
                    // each player placing cards on the center
                    for (Player currentPlayer : turn) {
    
                        displayGameInfo(currentPlayer);
                        String userInput;
    
                        label:
                        while (true) {
                            System.out.print("> ");
                            userInput = scanner.next();
    
                            switch (userInput) {
                                case "s":
                                saveGame(currentPlayer);
                                case "l":
                                loadGame(currentPlayer);
                                break label;
                                case "x":
                                    System.exit(0);
                                case "r":
                                    resetGame();
                                case "d":
                                    while (deck.size() > 0) {
                                        String result = currentPlayer.drawCard(center, deck);
                                        if (result.equals("success")) {
                                            break label;
                                        } else if (result.equals("error")) {
                                            break;
                                        }
                                    }
                                    break;
                                default:
                                    if (currentPlayer.playCard(userInput, center)) {
                                        break label;
                                    } else {
                                        System.out.println("Invalid input, please enter again.\n");
                                    }
                                    break;
                            }
                        }
                    }
    
                    // check winner
                    if (checkEmptyHands()) {
                        player1.calculateScore();
                        player2.calculateScore();
                        player3.calculateScore();
                        player4.calculateScore();
                        if (checkGameOver()) {
                            checkWinner();
                            System.out.println("Game Over.");
                            System.out.println("Press any key to continue...");
                            scanner.nextLine();
                            return;
                        }
                        else {
                            trick_count = 1;
                            center.clear();
                            break;
                        }
                    }
                    else {
                        turn = decideNextTurn();
                        trick_count++;
                        center.clear();
                    }
                }
            }
        }

    private boolean checkEmptyHands() {
        Player[] players = {player1, player2, player3, player4};
        for(Player player: players) {
            if(player.getHands().size() == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean checkGameOver() {
        Player[] players = {player1, player2, player3, player4};
        for(Player player: players) {
            if(player.getScore() >= 100) {
                return true;
            }
        }
        return false;
    }

    private void checkWinner() {
        Player winner = null;
        Player[] players = {player1, player2, player3, player4};
        for(Player player: players) {
            if(winner == null) {
                winner = player;
            }
            else {
                if(winner.getScore() > player.getScore()) {
                    winner = player;
                }
            }
        }
        System.out.println(winner + " wins the game.\n");
    }


    private void displayGameInfo(Player currentPlayer) {
        System.out.println("At the beginning of a game. The first lead card determines the first player.");
        System.out.println("C = Club");
        System.out.println("D = Diamond");
        System.out.println("H = Heart");
        System.out.println("S = Spade");
        System.out.println("");
        System.out.println("Commands: ");
        System.out.println("   s > Start a new game.");
        System.out.println("   x > Exit the game.");
        System.out.println("   d > Draw cards from deck until a playable card is obtained. If the deck is empty, skip to the next player.");
        System.out.println("card > a card played by the current player.");
        System.out.println("");
        System.out.println("\nTrick: " + trick_count);
        System.out.println("Player 1: " + player1.getHands());
        System.out.println("Player 2: " + player2.getHands());
        System.out.println("Player 3: " + player3.getHands());
        System.out.println("Player 4: " + player4.getHands());
        System.out.println("Center: " + center);
        System.out.println("Deck: " + deck);
        System.out.print("Score: " + player1 + " = " + player1.getScore());
        System.out.print(" | " + player2 + " = " + player2.getScore());
        System.out.print(" | " + player3 + " = " + player3.getScore());
        System.out.println(" | " + player4 + " = " + player4.getScore());
        System.out.println("Turn: " + currentPlayer);
    }



    private Vector<Player> determineFirstTurn(Card card) {

        String centerCard = card.toString();
        Vector<Player> firstTurn = new Vector<>();

        if (centerCard.contains("A") || centerCard.contains("5") || centerCard.contains("9") || centerCard.contains("K")) {
            firstTurn.add(player1);
            firstTurn.add(player2);
            firstTurn.add(player3);
            firstTurn.add(player4);
        }
        else if (centerCard.contains("2") || centerCard.contains("6") || centerCard.contains("10")) {
            firstTurn.add(player2);
            firstTurn.add(player3);
            firstTurn.add(player4);
            firstTurn.add(player1);
        }
        else if (centerCard.contains("3") || centerCard.contains("7") || centerCard.contains("J")) {
            firstTurn.add(player3);
            firstTurn.add(player4);
            firstTurn.add(player1);
            firstTurn.add(player2);      
        }
        else if (centerCard.equals("4") || centerCard.equals("8") || centerCard.equals("Q")) {
            firstTurn.add(player4);
            firstTurn.add(player1);
            firstTurn.add(player2);
            firstTurn.add(player3);
        }
        return firstTurn;
    }

    private Vector<Player> decideNextTurn() {
        Card highestRankCard = null;
        Vector<Player> nextTurn = new Vector<>();
        Player[] players = {player1, player2, player3, player4};

        for(Player player: players) {
            if(player.getLastPlayedCard() != null) {
                if(highestRankCard == null) {
                    highestRankCard = player.getLastPlayedCard();
                }
                else {
                    if(highestRankCard.getRank() > player.getLastPlayedCard().getRank()) {
                        highestRankCard = player.getLastPlayedCard();
                    }
                }
            }
        }

        

        if(highestRankCard == player1.getLastPlayedCard()) {
            nextTurn.add(player1);
            nextTurn.add(player2);
            nextTurn.add(player3);
            nextTurn.add(player4);

            System.out.println("\n*** " + player1 + " wins Trick "+trick_count+"***");
        }

        else if(highestRankCard == player2.getLastPlayedCard()) {
            nextTurn.add(player2);
            nextTurn.add(player3);
            nextTurn.add(player4);
            nextTurn.add(player1);

            System.out.println("\n*** " + player2 + " wins Trick "+trick_count+"***");
        }

        else if(highestRankCard == player3.getLastPlayedCard()) {
            nextTurn.add(player3);
            nextTurn.add(player4);
            nextTurn.add(player1);
            nextTurn.add(player2);

            System.out.println("\n*** " + player3 + " wins Trick "+trick_count+"***");
        }

       else if(highestRankCard == player4.getLastPlayedCard()) {
            nextTurn.add(player4);
            nextTurn.add(player1);
            nextTurn.add(player2);
            nextTurn.add(player3);

            System.out.println("\n*** " + player4 + " wins Trick "+trick_count+"***");
        }

        player1.initialiseLastPlayedCard();
        player2.initialiseLastPlayedCard();
        player3.initialiseLastPlayedCard();
        player4.initialiseLastPlayedCard();

        return nextTurn;
    }

    private ArrayList<Card> FullDeck() {
        ArrayList<Card> cards = new ArrayList<>();
    
        Suit suit = new Suit();
        List<String> retrievedSuit = suit.getCardSuit();

        Value value = new Value();
        List<String> retrievedValue = value.getCardValue();

        for(String cardsuit: retrievedSuit ) {
            for (String cardValue : retrievedValue) {
                if (cardValue.equals("A")) {
                    cards.add(new Card(cardValue, cardsuit, 1, 1));
                } else {
                    cards.add(new Card(cardValue, cardsuit, 10, 10));
                }
            }

        }
        Collections.shuffle(cards);
        return cards;
    }

    private Vector<Card> dealCards() {
        Vector<Card> initialHands = new Vector<>();
        for(int i = 0; i < 7; i++) {
            initialHands.add(deck.get(0));
            deck.remove(0);
        }
        return initialHands;
    }

    private void saveGame(Player currentPlayer) {
        try {
            String SAVE_FILE_PATH = "saved_game.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE_PATH));
            writer.write(trick_count + "\n");
            writer.write(player1.getHands() + "\n");
            writer.write(player2.getHands() + "\n");
            writer.write(player3.getHands() + "\n");
            writer.write(player4.getHands() + "\n");
            writer.write(center + "\n");
            writer.write(deck + "\n");
            writer.write(player1.getScore() + "\n");
            writer.write(player2.getScore() + "\n");
            writer.write(player3.getScore() + "\n");
            writer.write(player4.getScore() + "\n");
            writer.write(String.valueOf(currentPlayer) + "\n");
            writer.close();
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game.");
            e.printStackTrace();
        }
    }

    

    private void loadGame(Player currentPlayer ){
        List<String> lines = new ArrayList<>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("saved_game.txt"));
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            } 
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Saved game do not exit");
            e.printStackTrace(); //printStackTrace() is very useful in diagnosing exceptions.
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game.");
            e.printStackTrace(); //printStackTrace() is very useful in diagnosing exceptions.
        } 


        player1.getHands().clear();
        player2.getHands().clear();
        player3.getHands().clear();
        player4.getHands().clear();
        center.clear();
        deck.clear();

        trick_count = Integer.parseInt(lines.get(0));

        String[] Player1Cards = lines.get(1).replace("[","").replace("]","").split(", ");
        for (String cardString : Player1Cards) {
        String[] cardInfo = cardString.split("");
            ArrayList<String> value = new ArrayList<>();
            List<String> suit = new ArrayList<>();
            value.add(cardInfo[0]);
            suit.add(cardInfo[1]);
            for(String cardsuit: suit) {
                for (String cardValue : value){
                    if (cardValue.equals("A")){
                        player1.getHands().add(new Card(cardValue, cardsuit, 1, 1));
                    } else {
                        player1.getHands().add(new Card(cardValue, cardsuit, 10, 10));
                    }
                }
            }
        }

        String[] Player2Cards = lines.get(2).replace("[","").replace("]","").split(", ");
        for (String cardString : Player2Cards) {
        String[] cardInfo = cardString.split("");
            ArrayList<String> value = new ArrayList<>();
            List<String> suit = new ArrayList<>();
            value.add(cardInfo[0]);
            suit.add(cardInfo[1]);
            for(String cardsuit: suit) {
                for (String cardValue : value){
                    if (cardValue.equals("A")){
                        player2.getHands().add(new Card(cardValue, cardsuit, 1, 1));
                    } else {
                        player2.getHands().add(new Card(cardValue, cardsuit, 10, 10));
                    }
                }
            }
        }

        String[] Player3Cards = lines.get(3).replace("[","").replace("]","").split(", ");
        for (String cardString : Player3Cards) {
        String[] cardInfo = cardString.split("");
            ArrayList<String> value = new ArrayList<>();
            List<String> suit = new ArrayList<>();
            value.add(cardInfo[0]);
            suit.add(cardInfo[1]);
            for(String cardsuit: suit) {
                for (String cardValue : value){
                    if (cardValue.equals("A")){
                        player3.getHands().add(new Card(cardValue, cardsuit, 1, 1));
                    } else {
                        player3.getHands().add(new Card(cardValue, cardsuit, 10, 10));
                    }
                }
            }
        }

        String[] Player4Cards = lines.get(4).replace("[","").replace("]","").split(", ");
        for (String cardString : Player4Cards) {
        String[] cardInfo = cardString.split("");
            ArrayList<String> value = new ArrayList<>();
            List<String> suit = new ArrayList<>();
            value.add(cardInfo[0]);
            suit.add(cardInfo[1]);
            for(String cardsuit: suit) {
                for (String cardValue : value){
                    if (cardValue.equals("A")){
                        player4.getHands().add(new Card(cardValue, cardsuit, 1, 1));
                    } else {
                        player4.getHands().add(new Card(cardValue, cardsuit, 10, 10));
                    }
                }
            }
        }

        String[] centerCards = lines.get(5).replace("[","").replace("]","").split(", ");
        for (String cardString : centerCards) {
        String[] cardInfo = cardString.split("");
            ArrayList<String> value = new ArrayList<>();
            List<String> suit = new ArrayList<>();
            value.add(cardInfo[0]);
            suit.add(cardInfo[1]);
            for(String cardsuit: suit) {
                for (String cardValue : value){
                    if (cardValue.equals("A")){
                        center.add(new Card(cardValue, cardsuit, 1, 1));
                    } else {
                        center.add(new Card(cardValue, cardsuit, 10, 10));
                    }
                }
            }
        }

        String[] deckCards = lines.get(6).replace("[","").replace("]","").split(", ");
        for (String cardString : deckCards) {
        String[] cardInfo = cardString.split("");
            ArrayList<String> value = new ArrayList<>();
            List<String> suit = new ArrayList<>();
            value.add(cardInfo[0]);
            suit.add(cardInfo[1]);
            for(String cardsuit: suit) {
                for (String cardValue : value){
                    if (cardValue.equals("A")){
                        deck.add(new Card(cardValue, cardsuit, 1, 1));
                    } else {
                        deck.add(new Card(cardValue, cardsuit, 10, 10));
                    }
                }
            }
        }

        if(lines.get(11) == "Player1") {
            currentPlayer = player1;
        }else if(lines.get(11) == "Player2") {
            currentPlayer = player2;
        }else if(lines.get(11) == "Player3") {
            currentPlayer = player3;
        }else if(lines.get(11) == "Player4") {
            currentPlayer = player4;
        }


    }
    

    public void resetGame() {
        boolean run = true;
        while (run) {
            new GameRunner().run();

        }
    }
}
