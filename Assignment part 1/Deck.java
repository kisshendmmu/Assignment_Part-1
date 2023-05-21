import java.util.Collections;
import java.util.ArrayList;

public class Deck {
    //instance variable 
    private ArrayList<Card> cards;
    
    //constructor
    public Deck() {
        this.cards = new ArrayList<Card>();
        
    }

    public void createFullDeck() {
        //generate cards
        for(Suit cardSuit: Suit.values()) {
            for(Value cardValue: Value.values()) {
                //add a new card 
                this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }
    
    public void shuffle() {
        //ArrayList<Card> tmpDeck = new ArrayList<Card>(); //putting the cards into a temporary deck
        //Randomize
        //Random random = new Random();
        //int randomCardIndex = 0; //initial variable
        //int originalSize = this.cards.size(); //size of deck
        Collections.shuffle(this.cards);
        System.out.println("Deck: " + this.cards+"\n");
    }

    public void printOut() {
        System.out.println(this.cards+"\n");
    }

    public void removeCard(int i) {
        this.cards.remove(i);
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public void addCard(Card addCard) {
        this.cards.add(addCard);
    }

    public void draw(Deck comingFrom) {//drawing cards
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public Card[] getDeck() {
        return null;
    }

    public ArrayList<Card> getCards() {
        return null;
    }

    public void remove(Card playedCard) {
    }

    public Card get() {
        return null;
    }

    public int size() {
        return 0;
    }

    

    // Other methods of the Deck class

}