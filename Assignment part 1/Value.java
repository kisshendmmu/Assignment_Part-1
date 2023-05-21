public enum Value {//enum is used for constance 
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("X"),
    JACK("J"), 
    KING("K"), 
    QUEEN("Q"), 
    ACE("A");

    private String symbol;
    
    Value(String symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return symbol;
    }
}