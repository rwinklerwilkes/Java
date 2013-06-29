/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;
import java.util.ArrayList;

/**
 *
 * @author Rich
 */
public class Player {
    /* MAX_MONEY is the most money a player can have at once, this isn't
     * a high-stakes Blackjack game
     */
    private final int MAX_MONEY = 10000;
    private String name;
    /* computer tracks whether the player is the dealer or not */
    private boolean computer;
    /* money tracks how much money the player has at the moment */
    private int money;
    /* hand is the current hand the player has in the current round
     * of the game
     */
    private ArrayList<Card> hand = new ArrayList<Card>();
    public Player(String name)
    {
        this.name = name;
        this.computer = false;
        this.money = 100;
    }
    public Player(String name, int money)
    {
        this.name = name;
        this.computer = false;
        this.money = money;
    }
    public Player(String name, boolean computer)
    {
        this.name = name;
        this.computer = computer;
    }
    public String toString()
    {
        return "Name: " + this.name + ", Money: $" + this.money;
    }
    public String getName()
    {
        return this.name;
    }
    public int getMoney()
    {
        if(this.computer)
        {
            return MAX_MONEY;
        }
        /* If they have too much money, reduce how much they have to be
         * under the maximum limit.  Consider it a casino tax.
         */
        else if(this.money> this.MAX_MONEY)
        {
            System.out.println("You have too much money for this game!");
            System.out.println("Lowering your money to $10000");
            this.money = this.MAX_MONEY;
            return this.money;
        }
        else
        {
            return this.money;            
        }
    }
    public void addMoney(int n)
    {
        this.money += n;
    }
    public void removeMoney(int n)
    {
        this.money -= n;
    }
    public boolean isDealer()
    {
        return computer;
    }
    public boolean hasMoney()
    {
        if(this.computer||this.money>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /* Gives the player the next card in the deck */
    public Card hit(Deck d)
    {
        Card next = d.getNextCard();
        hand.add(next);
        return next;
    }
    /* Checks to see whether the player has busted or not */
    public boolean checkBust()
    {
        if(this.handScore() > 21)
        {
            return true;
        }
        return false;
    }
    /* Prints out a list of the cards in the player's hand */
    public void showHand()
    {
        for(Card c: hand)
        {
            System.out.println(c);
        }
    }
    /* Just shows the top card in the player's hand. Used for the dealer
     * since the player can only ever see their top card.
     */
    public Card showTop()
    {
        if(!hand.isEmpty())
        {
            return hand.get(0);
        }
        else
        {
            return null;
        }
    }
    /* Calculates the score of the hand the player currently is holding */
    public int handScore()
    {
        int sum = 0;
        for(Card c: hand)
        {
            sum+=c.getPointValue();
        }
        return sum;
    }
    /* Figures out who won the hand */
    public static int decideWinner(Player one, Player two)
    {
        //0 means a tie, 1 means Player one won, 2 means Player two won
        if(one.checkBust()&&!one.isDealer())
        {
            return 2;
        }
        if(two.checkBust()&&!two.isDealer())
        {
            return 1;
        }
        else if(one.checkBust())
        {
            return 2;
        }
        else if(two.checkBust())
        {
            return 1;
        }
        else if(one.handScore()>two.handScore())
        {
            return 1;
        }
        else if(two.handScore()>one.handScore())
        {
            return 2;
        }
        else
        {
            return 0;
        }
    }
    /* Discards all of the cards in the player's hand to get ready for
     * the next round.
     */
    public void resetHand()
    {
        hand.clear();
    }
}
