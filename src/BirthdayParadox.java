import java.util.Scanner;
import java.util.ArrayList;

//theoretical probability: for loop from 2 to n multiplying ((365-n)/365) by an accumulative var
// double num = 364.0/365.0;
// for(int i = 2; i < numStudents; i++)
//     num *= (365-i)/365.0;
// 1-num = prob that there is a match

//student matches/num trials = percent matches

public class BirthdayParadox
{
    public static void main(String[] args){
        boolean keepTrying = true;
        int students = 0;
        int trials = 0;
        boolean showAllStudents = true;
        int matches = 0;
        boolean foundMatch = false;
        ArrayList<Integer> bDayMonths = new ArrayList<Integer>();
        ArrayList<Integer> bDayDays = new ArrayList<Integer>();
        Scanner keyboard = new Scanner(System.in);
        while(keepTrying){
            System.out.print("\nHow many students are in the class? ");
            students = keyboard.nextInt();
            System.out.print("\nHow many trials do you want to run? ");
            trials = keyboard.nextInt();
            System.out.print("\nDo you want to show all student birthdays for every trial? (Y/N) ");
            switch(keyboard.next()){
                case "Y": showAllStudents = true; break;
                case "N": showAllStudents = false; break;
            }

            double num = 364.0/365.0;
            for(int i = 2; i < students; i++)
                num *= (365-i)/365.0;
            num = (1.0-num) * 100.0;
            System.out.printf("Theoretical probability of at least one match for " + students + " students: %.3f%%%n", num);

            for(int t = 0; t < trials; t++){
                bDayMonths.clear();
                bDayDays.clear();
                for(int i = 0; i < students; i++){
                    bDayMonths.add((int)(Math.random()*12)+1);
                    switch(bDayMonths.get(0)){
                        case 2: bDayDays.add((int)(Math.random()*29)+1); break;
                        case 4: case 6: case 9: case 11: bDayDays.add((int)(Math.random()*30)+1); break;
                        default: bDayDays.add((int)(Math.random()*31)+1); break;
                    }
                }
                if((t == 0 && trials == 1) || showAllStudents)
                    for(int i = 0; i < students; i++)
                        System.out.println(bDayMonths.get(i) + " " + bDayDays.get(i));
                int monthIndex = 0;
                int dayIndex = 0;
                foundMatch = false;
                for(int i = 0; i < students; i++){
                    monthIndex = bDayMonths.get(i);
                    dayIndex = bDayDays.get(i);
                    for(int j = 0; j < students; j++){
                        if(i == j || foundMatch)
                            break; //ask if this is how break is supposed to be used
                        else if(monthIndex == bDayMonths.get(j) && dayIndex == bDayDays.get(j)){
                            System.out.println("Match found: " + monthIndex + " " + dayIndex + ", students " + j + " and " + i);
                            matches++;
                            foundMatch = true;
                        }
                    }
                    if(foundMatch)
                        break; //ask if this is how break is supposed to be used
                }
            }

            double percent = (((double)matches)/trials) * 100.0;
            System.out.printf("Actual percent of trials with at least one match: %.3f%%", percent);

            System.out.print("\nDo you want to run another test? (Y/N) ");
            if(keyboard.next().equals("N"))
                keepTrying = false;
        }
    }
}