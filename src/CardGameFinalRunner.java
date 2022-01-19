import java.util.Scanner;

public class CardGameFinalRunner
{
    public static void main(String[] args)
    {
        CardGameFinal fun = new CardGameFinal();
        Scanner keyboard = new Scanner(System.in);
        int input = 0;
        int randomCard = 0;
        String p = "";
        String c = "";
        boolean play = true;

        while(!play == false)
        {
            while(!isHandEmpty(fun.getPCard(0),fun.getPCard(1),fun.getPCard(2),fun.getPCard(3),fun.getPCard(4)))
            {
                System.out.print(fun.toString());
                input = keyboard.nextInt();
                if(fun.getPCard(input) == "empty")
                {
                    while(fun.getPCard(input) == "empty")
                    {
                        System.out.print("Please pick an unused card: ");
                        input = keyboard.nextInt();
                    }
                }
                randomCard = getRandom();
                if(fun.getCCard(randomCard) == "empty")
                {
                    while(fun.getCCard(randomCard) == "empty")
                    {
                        randomCard = getRandom();
                    }
                }
                p = fun.getPCard(input);
                c = fun.getCCard(randomCard);
                if(p == c)
                {
                    System.out.println("\nPlayer shows: " + p + ", Computer shows: " + c + " -- You Tied\n" +
                            "Your score: " + fun.getPWins() + "  --  Computer's score: " + fun.getCWins() + "\n");
                }
                else if(((p == "ROCK") && (c == "SCISSORS")) || ((p == "PAPER") && (c == "ROCK"))
                        || ((p == "SCISSORS") && (c == "PAPER")))
                {
                    fun.setPWins(fun.getPWins() + 1);
                    System.out.println("\nPlayer shows: " + p + ", Computer shows: " + c + " -- You Won\n" +
                            "Your score: " + fun.getPWins() + "  --  Computer's score: " + fun.getCWins() + "\n");
                }
                else
                {
                    fun.setCWins(fun.getCWins() + 1);
                    System.out.println("\nPlayer shows: " + p + ", Computer shows: " + c + " -- You Lost\n" +
                            "Your score: " + fun.getPWins() + "  --  Computer's score: " + fun.getCWins() + "\n");
                }
                fun.emptyHand(input,randomCard);
            }
            System.out.print("Play again? (yes = 0, no = 1): ");
            input = keyboard.nextInt();
            if(input == 1)
            {
                System.out.println("Thanks for playing!");
                play = false;
            }
            else if(input == 0)
            {
                fun.setHand();
                play = true;
            }
        }
    }
    public static boolean isHandEmpty(String a, String b, String c, String d, String e)
    {
        if((a == "empty") && (b == "empty") && (c == "empty") && (d == "empty") && (e == "empty"))
            return true;
        else
            return false;
    }
    public static int getRandom()
    {
        return (int)(Math. random()*(5));
    }
}