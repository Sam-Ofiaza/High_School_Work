import java.util.Scanner;

public class YahtzeeRunner
{
    public static void main(String[] args){
        Yahtzee test = new Yahtzee();
        Scanner keyboard = new Scanner(System.in);
        while(!test.allSpotsFilled()){
            test.updateAll();
            System.out.println(test);
            test.roll();
            System.out.println(test.printRoll());
            for(int i = 0; i < 3; i++){
                System.out.print("do you want to roll<r> or score<s>? ");
                String choice = keyboard.next();
                while(!(choice.equals("r") || choice.equals("s") || choice.equals("c"))){
                    System.out.print("\nplease enter a valid letter: ");
                    choice = keyboard.next();
                }
                if(choice.equals("r")){
                    System.out.print("which dice do you want to re-roll (enter 0 thru 4 separated by spaces): ");
                    keyboard.nextLine();
                    String str = keyboard.nextLine();
                    String[] list1 = str.split(" ");
                    int[] list2 = new int[list1.length];
                    for(int j = 0; j < list1.length; j++)
                        list2[j] = Integer.parseInt(list1[j]);
                    test.reroll(list2);
                    System.out.println("\n" + test.printRoll());
                }
                else if(choice.equals("s"))
                    i = 3; //would this be an appropriate place to use break?
                else if(choice.equals("c")){
                    System.out.print("set roll to: ");
                    keyboard.nextLine();
                    String str = keyboard.nextLine();
                    String[] list1 = str.split(" ");
                    int[] list2 = new int[list1.length];
                    for(int j = 0; j < list1.length; j++)
                        list2[j] = Integer.parseInt(list1[j]);
                    test.setroll(list2);
                    System.out.println("\n" + test.printRoll());
                }
            }
            System.out.print("enter a score category -> ones<1>, twos<2>, threes<3>, fours<4>, fives<5>, sixes<6>\n3kind<3k>, 4kind<4k>, fhouse<f>, sm str<s>, lg str<l>, chance<c>, yahtzee<y>: ");
            String category = keyboard.next();
            while(test.alreadyUsed(category)){
                System.out.print("\nthat category was already used - type in another: ");
                category = keyboard.next();
            }
            switch(category){
                case "1": test.ones(); break;
                case "2": test.twos(); break;
                case "3": test.threes(); break;
                case "4": test.fours(); break;
                case "5": test.fives(); break;
                case "6": test.sixes(); break;
                case "3k": test.threeKind(); break;
                case "4k": test.fourKind(); break;
                case "f": test.fhouse(); break;
                case "s": test.smstr(); break;
                case "l": test.lgstr(); break; //"L", not "one"
                case "c": test.chance(); break;
                case "y": test.yahtzee(); break;
            }
        }

        System.out.println("\n" + test + "\nThank you for playing!");
    }
}