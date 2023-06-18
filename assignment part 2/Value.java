import java.util.Vector;
import java.util.List;

public class Value {
    List<String> myValue;

    public Value() {
        myValue = new Vector<>();
        myValue.add("A");
        myValue.add("2");
        myValue.add("3");
        myValue.add("P");
        myValue.add("5");
        myValue.add("6");
        myValue.add("7");
        myValue.add("8");
        myValue.add("9");
        myValue.add("X");
        myValue.add("K");
        myValue.add("Q");
        myValue.add("J");
    }

    public List<String> getCardValue() {
        return myValue;
    }
}
