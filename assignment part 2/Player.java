import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Player {

    private final String name;
    private Vector<Card> hands;
    private Card lastPlayedCard;
    private int score;
    public Player(String playerName) {
        score = 0;
        this.name = playerName;
        lastPlayedCard = null;
    }

    public void initialHands(Vector<Card> hands) {
        this.hands = hands;
    }

    public boolean playCard(String inputCard, Vector<Card> center) {
        if (center.size() > 0) {
            Card leadCard = center.get(0);
            if ((inputCard.contains(leadCard.getValue().toString())) || (inputCard.contains(leadCard.getSuit().toString()))) {
                for (Card card : hands) {
                    if (inputCard.equals(card.getName())) {
                        center.add(card);
                        hands.remove(card);
                        lastPlayedCard = card;
                        return true;
                    }
                }
            }
        }
        else {
            for (Card card : hands) {
                if (inputCard.equals(card.getName())) {
                    center.add(card);
                    hands.remove(card);
                    lastPlayedCard = card;
                    return true;
                }
            }
        }
        return false;
    }

    public String drawCard(Vector<Card> center, ArrayList<Card> deck) {
        Card cardDrawn;
        if(center.size() > 0) {
            Card leadCard = center.get(0);
            for (Card card : hands) {
                if ((leadCard.getName().contains(card.getValue().toString())) || (leadCard.getName().contains(card.getSuit().toString()))) {
                    System.out.println("You are not allowed to draw cards.\n");
                    return "error";
                }
            }

            cardDrawn = deck.get(0);
            deck.remove(cardDrawn);
            if ((leadCard.getName().contains(cardDrawn.getValue().toString())) || (leadCard.getName().contains(cardDrawn.getSuit().toString()))) {
                center.add(cardDrawn);
                lastPlayedCard = cardDrawn;
                return "success";
            } else {
                hands.add(cardDrawn);
                return "fail";
            }
        }
        else {
            System.out.println("You are not allowed to draw cards.\n");
            return "error";
        }
    }

    public Vector<Card> getHands() {
        return hands;
    }

    
    public void calculateScore() {
        for(Card card: hands) {
            score += card.getMerit();
        }
    }

    public int getScore() {
        return score;
    }

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }

    public void initialiseLastPlayedCard() {
        lastPlayedCard = null;
    }

    @Override
    public String toString() {
        return name;
    }
}
