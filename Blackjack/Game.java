/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Rich
 */
public class Game {
    public Game()
    {
        File f = new File("blackjackdata.txt");
        Deck d = new Deck();
        boolean play = true;
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String playerName = s.nextLine();
        int playerMoney = 100;
        boolean playerFound = false;
        int playerFileIndex = -1;
        /* This try/catch block opens up the blackjack data file and checks
         * to see if the player has played before.  If they have, it gets
         * their money total to use for this game (if they have any), or resets
         * their total to $100.  It also keeps track of where the player's name
         * was in the file, so that when they finish playing it doesn't have
         * to look through again to find that line - it can just overwrite
         * the correct line with the new data.
         */
        try
        {
            Scanner input = new Scanner(f);
            while(input.hasNextLine())
            {
                String line = input.nextLine();
                String[] data = line.split(",");
                String name = data[0];
                int money = Integer.parseInt(data[1]);
                if(playerName.equals(name))
                {
                    System.out.println("Welcome back, " + name);
                    if(money<=0)
                    {
                        System.out.print("Since you were broke at the end of the last game,");
                        System.out.println(" you now have $100 again");
                        playerMoney = 100;
                    }
                    else
                    {
                        playerMoney = money;
                    }
                    playerFound = true;
                }
                playerFileIndex++;
            }
            input.close();
        }
        /* If the file doesn't exist, create it; it will get filled at the end
         * of the current game
         */
        catch(FileNotFoundException fnfe)
        {
            try
            {
                f.createNewFile();
            }
            catch(IOException ioe)
            {
                System.out.println("Cannot create file to save player data");
            }
        }
        Player p;
        if(playerFound)
        {
            p = new Player(playerName, playerMoney);
        }
        else
        {
            System.out.println("Welcome, first-time player " + playerName);
            p = new Player(playerName);
        }
        Player dealer = new Player("Dealer", true);
        /* Keep playing until the player is tired of it */
        while(play)
        {
            int bet = 0;
            System.out.println("You have $" + p.getMoney());
            System.out.println("How much would you like to bet?");
            /* The player has to enter a valid bet, otherwise the game
             * will make a bet for them (if what they enter isn't a number)
             */
            try
            {
                while((bet=Integer.parseInt(s.nextLine()))>p.getMoney())
                {
                    System.out.println("Invalid bet, enter a bet which is less than the amount of money you have");
                }
            }
            catch(NumberFormatException nfe)
            {
                System.out.println("You've entered a bet which is not valid");
                bet = p.getMoney()-1;
                System.out.println("So, your new bet is: " + bet);
            }
            /* pStand and cStand track whether the player or computer is done
             * taking cards yet
             */
            boolean pStand = false, cStand = false;
            /* The initial cards are dealt */
            dealer.hit(d);
            dealer.hit(d);
            p.hit(d);
            p.hit(d);
            /* Keep playing until both have finished pulling cards */
            while(!pStand||!cStand)
            {
                /* Only execute this code if the player has not taken a stand
                 * yet. If they have, then skip it.
                 */
                if(!pStand)
                {
                    System.out.println("Dealer has " + dealer.showTop() + " showing.");
                    System.out.println("Your hand: ");
                    p.showHand();
                    System.out.println("You have " + p.handScore() + " points.");
                    boolean hFlag = true;
                    while(hFlag)
                    {
                        System.out.println("Hit (h) or stand (s)?");
                        String move = s.nextLine();
                        if(move.equalsIgnoreCase("HIT")||move.equalsIgnoreCase("H"))
                        {
                            System.out.println("You draw: " + p.hit(d));
                            hFlag = false;
                        }
                        else if(move.equalsIgnoreCase("STAND")||move.equalsIgnoreCase("S"))
                        {
                            pStand=true;
                            hFlag = false;
                        }
                        else
                        {
                            System.out.println("Invalid choice, please type H or S");
                        }
                    }
                    /* Check if they've busted, and don't bother letting 
                     * either the player or computer hit anymore - the player
                     * automatically loses if they bust
                     */
                    if(p.checkBust())
                    {
                        System.out.println("You've busted!");
                        break;
                    }
                }
                /* The dealer HAS to keep hitting until they have over 
                 * 17 points
                 */
                if(dealer.handScore()<17)
                {
                    dealer.hit(d);
                }
                else
                {
                    cStand = true;
                }
            }
            /* Figure out who the winner is, and divvy up the money */
            int winner = Player.decideWinner(p,dealer);
            System.out.println("You have " + p.handScore() + " points.");
            System.out.println("Dealer has " + dealer.handScore() + " points.");
            if(winner==0)
            {
                System.out.println("It's a tie! You win back your bet");
            }
            else if(winner==1)
            {
                System.out.println("You've won! You win $" + (bet*2));
                p.addMoney(bet);
            }
            else
            {
                System.out.println("You've lost! You lost $" + bet);
                p.removeMoney(bet);
            }
            /* If they want to play again, reset the hands of both the 
             * dealer and the player in preparation for the next round.
             * Otherwise, set the flag variable to false.
             */
            System.out.println("Would you like to play again? Y/N");
            String answer = s.nextLine();
            if(answer.equalsIgnoreCase("N"))
            {
                play = false;
            }
            else
            {
                p.resetHand();
                dealer.resetHand();
            }
        }
        /* Output the data into the file in the correct location */
        System.out.println("Thanks for playing, " + p.getName());
        System.out.println("Saving your money total for next time...");
        outputFile(p,playerFound,playerFileIndex,f);
        s.close();
    }
    /* This method uses the Apache Commons IO library in order to simplify
     * entering the new data into our file. It reads the old data, creates
     * the correct string, replaces the right line (if the player was found)
     * or adds a new line at the end (if the player wasn't found). Then, it
     * rewrites the file.
     */
    private static void outputFile(Player player, boolean pf, int pi, File f)
    {
        try
        {
            List<String> lines = FileUtils.readLines(f);
            String add = player.getName() + "," + player.getMoney();
            if(pf)
            {

                lines.set(pi,add);
            }
            else
            {
                lines.add(add);
            }
            FileUtils.writeLines(f,lines);
        }
        catch(IOException e)
        {
            System.out.println("Error writing to file");
        }
    }
}
