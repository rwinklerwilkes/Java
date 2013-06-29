/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;
import java.util.Random;

/**
 *
 * @author Rich
 */
public class Deck {
    private Card[] cards;
    /* This variable keeps track of where the top of the deck is
     * Probably could've (should've?) used a stack here and just
     * checked for when the stack is empty, but this works as well
     */
    private int deckPosition;
    public Deck()
    {
        /*The constructor for the deck adds all of the cards to the new deck
         * and then shuffles the deck
         */
        try
        {
            cards = new Card[52];
            for(int i = 0; i<4; i++)
            {
                String curDeck;
                switch(i)
                {
                    case 0:
                        curDeck = "hearts";
                        break;
                    case 1:
                        curDeck = "spades";
                        break;
                    case 2:
                        curDeck = "clubs";
                        break;
                    case 3:
                        curDeck = "diamonds";
                        break;
                    default:
                        curDeck = "error";
                }
                for(int j = 0; j<13; j++)
                {
                    cards[i*13+j] = new Card(j, curDeck);
                }
            }
            this.shuffle();
        }
        /*Since the deck is the one creating the cards, I wanted the deck
         * to handle any exceptions that arise from their creation
         */
        catch(Card.InvalidCardException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
    private void shuffle()
    {
        /*Generates a random integer between 0 and the variable cardsLength
         * which is initialized to 52.  Every time it gets a number, it takes
         * the card at that position from the cards array, and puts it into
         * the cardCopy array.  Then, it switches the removed card with the 
         * card at the end of the cards array, and lowers cardsLength by 1,
         * which lowers the cards we can take from the cards array.  This
         * ensures we will get a random, unique card from our cards array each
         * time we pull one.
         */
        int cardsLength = cards.length;
        Random r = new Random();
        Card[] cardCopy = new Card[52];
        for(int i = 0; i<cardCopy.length; i++)
        {
            int nextCard = r.nextInt(cardsLength);
            cardCopy[i] = cards[nextCard];
            Card temp = cards[cardsLength-1];
            cards[cardsLength-1] = cards[nextCard];
            cards[nextCard] = temp;
            cardsLength--;
        }
        cards = cardCopy;
        /* Lastly, since the deck was shuffled, we will start at the top */
        deckPosition = 0;
    }
    public Card getNextCard()
    {
        /* If we haven't taken all of the cards already, just get the next card
         * and add one to the position.  Otherwise, shuffle the deck, and start
         * from the beginning again
         */
        if(deckPosition<52)
        {
            return cards[deckPosition++];            
        }
        else
        {
            System.out.println("\nShuffling\n");
            this.shuffle();
            return cards[deckPosition++];
        }
    }
}
