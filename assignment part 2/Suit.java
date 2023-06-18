import java.util.Vector;
import java.util.List;

public class Suit {
    List<String> mySuit;

    
    public Suit() {
        mySuit = new Vector<>();
        mySuit.add("c");
        mySuit.add("d");
        mySuit.add("s");
        mySuit.add("h");
    }

    public List<String> getCardSuit() {
        return mySuit;
    }
}
