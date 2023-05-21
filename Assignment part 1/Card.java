public class Card {
    private Suit suit; //the suit of the cards
    private Value value;

    public Card(Suit suit, Value value) { //constructor
        this.value = value;
        this.suit = suit;
    }

    public Suit getSuit() { //checking value of card
        return this.suit;
    }

    public Value getValue() { //checking value of card
        return this.value;
    }

    public String toString() { //print out everything in String
        return this.suit.toString() + this.value.toString();
    }

    public String getNumber() {
        return null;
    }

    public int getRank() {
        return 0;
    }

    public Object getName() {
        return null;
    }

}