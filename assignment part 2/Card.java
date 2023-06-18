public class Card {
    private final String name;
    private String suit; //the suit of the cards
    private String value;
    final int rank;
    final int merit;

    public Card(String suit, String value, int rank, int merit) { //constructor
        this.name = suit.toString() + value.toString();
        this.value = value;
        this.suit = suit;
        this.rank = rank;
        this.merit = merit;

    }

    public String getSuit() { //checking value of card
        return this.suit;
    }

    public String getValue() { //checking value of card
        return this.value;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public int getMerit() {
        return merit;
    }

    public String toString() { //print out everything in String
        return this.suit.toString() + this.value.toString();
    }

}