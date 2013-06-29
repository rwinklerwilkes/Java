/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author Rich
 */

public class Card {
    private int rank;
    private String suit;
    private static String[] cardNames = {"Ace","Two","Three","Four","Five","Six","Seven",
    "Eight","Nine","Ten","Jack", "Queen", "King"};
    public Card(int rank, String suit) throws InvalidCardException 
    {
        if(rank>=13||rank<0)
        {
            throw new InvalidCardException("Rank out of bounds");
        }
        else
        {
           this.rank = rank; 
        }
        switch(suit)
        {
            case "hearts":
                this.suit = "Hearts";
                break;
            case "spades":
                this.suit = "Spades";
                break;
            case "clubs":
                this.suit = "Clubs";
                break;
            case "diamonds":
                this.suit = "Diamonds";
                break;
            default:
                throw new InvalidCardException("Invalid suit specified");
        } 
    }
    /*If the card is a jack, queen, or king, the card is worth 10 points.
     * If the card is an ace (rank 0), the card is worth 11 points.
     * Otherwise, the card is worth 1 more than it was stored as (since I began
     * counting the cards at 1 instead of 2
     */
    public int getPointValue()
    {
        if(this.rank==10||this.rank==11||this.rank==12)
        {
            return 10;
        }
        if(this.rank==0)
        {
            return 11;
        }
        else
        {
            return this.rank + 1;
        }
    }
    public String toString()
    {
        String printRank = cardNames[this.rank];
        return printRank + " of " + this.suit;
    }
    public class InvalidCardException extends Exception
    {
        InvalidCardException()
        {
            super();
        }
        InvalidCardException(String message)
        {
            super(message);
        }
        InvalidCardException(String message, Throwable cause)
        {
            super(message, cause);
        }
        InvalidCardException(Throwable cause)
        {
            super(cause);
        }
    }
}
