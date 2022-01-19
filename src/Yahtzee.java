import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class Yahtzee
{
    private HashMap<String,Integer> scoreCard;
    private int sum; //sum of individual numbers
    private int bonus; //35 bonus points when sum >= 63
    private int total;
    private int[] roll; //to store roll vals
    private String[] shortenedNames; //scoreCard key vals
    private String[] fullNames;
    public Yahtzee(){
        scoreCard = new HashMap<String,Integer>();
        sum = 0;
        bonus = 0;
        total = 0;
        roll = new int[5];
        shortenedNames = new String[] {"1", "2", "3", "4", "5", "6", "3k", "4k", "f", "s", "l", "y", "c"};
        fullNames = new String[] {"ones", "twos", "threes", "fours", "fives", "sixes", "3kind", "4kind", "fhouse", "sm str", "lg str", "yahtzee", "chance"};

        for(String x : shortenedNames)
            scoreCard.put(x, -1);
    }

    //must check if category is available in runner
    public void ones(){
        int points = 0;
        for(Integer x : roll)
            if(x == 1)
                points += x;
        scoreCard.replace("1", points);
    }
    public void twos(){
        int points = 0;
        for(Integer x : roll)
            if(x == 2)
                points += x;
        scoreCard.replace("2", points);
    }
    public void threes(){
        int points = 0;
        for(Integer x : roll)
            if(x == 3)
                points += x;
        scoreCard.replace("3", points);
    }
    public void fours(){
        int points = 0;
        for(Integer x : roll)
            if(x == 4)
                points += x;
        scoreCard.replace("4", points);
    }
    public void fives(){
        int points = 0;
        for(Integer x : roll)
            if(x == 5)
                points += x;
        scoreCard.replace("5", points);
    }
    public void sixes(){
        int points = 0;
        for(Integer x : roll)
            if(x == 6)
                points += x;
        scoreCard.replace("6", points);
    }
    public void threeKind(){
        int[] temp = new int[5];
        for(int i = 0; i < roll.length; i++)
            temp[i] = roll[i];
        Arrays.sort(temp);

        int[] scenario1 = Arrays.copyOfRange(temp, 0, 3); // {1, 1, 1, 2, 3}
        int[] scenario2 = Arrays.copyOfRange(temp, 1, 4); // {1, 2, 2, 2, 3}
        int[] scenario3 = Arrays.copyOfRange(temp, 2, 5); // {1, 2, 3, 3, 3}
        if(containsOnlyRepeatingVal(scenario1) || containsOnlyRepeatingVal(scenario2) || containsOnlyRepeatingVal(scenario3)){
            int points = 0;
            for(Integer x : roll)
                points += x;
            scoreCard.replace("3k", points);
        }

        scoreCard.replace("3k", -1, 0);
        
        /*
        int points = 0;
        int count = 0;
        for(int i = 0; i < roll.length; i++){
            count = 0;
            for(int j = 0; j < roll.length; j++){
                if(roll[j] == roll[i])
                    count++;
            }
            if(count >= 3)
                for(Integer x : roll)
                    points += x;
        }
        scoreCard.replace("3kind", points);
        */
    }
    public void fourKind(){
        int[] temp = new int[5];
        for(int i = 0; i < roll.length; i++)
            temp[i] = roll[i];
        Arrays.sort(temp);

        int[] scenario1 = Arrays.copyOfRange(temp, 0, 4); // {1, 1, 1, 1, 2}
        int[] scenario2 = Arrays.copyOfRange(temp, 1, 5); // {1, 2, 2, 2, 2}
        if(containsOnlyRepeatingVal(scenario1) || containsOnlyRepeatingVal(scenario2)){
            int points = 0;
            for(Integer x : roll)
                points += x;
            scoreCard.replace("4k", points);
        }

        scoreCard.replace("4k", -1, 0);
        
        /*
        int points = 0;
        int count = 0;
        for(int i = 0; i < roll.length; i++){
            count = 0;
            for(int j = 0; j < roll.length; j++){
                if(roll[j] == roll[i])
                    count++;
            }
            if(count >= 4)
                for(Integer x : roll)
                    points += x;
        }
        scoreCard.replace("4kind", points);
        */
    }
    public void fhouse(){
        int[] temp = new int[5];
        for(int i = 0; i < roll.length; i++)
            temp[i] = roll[i];
        Arrays.sort(temp);

        //scenario1: {1, 1, 1, 2, 2}
        int[] triple1 = Arrays.copyOfRange(temp, 0, 3);
        int[] pair1 = Arrays.copyOfRange(temp, 3, 5);
        if(triple1[0] != pair1[0] && containsOnlyRepeatingVal(triple1) && containsOnlyRepeatingVal(pair1))
            scoreCard.replace("f", 25);

        //scenario2: {1, 1, 2, 2, 2}
        int[] triple2 = Arrays.copyOfRange(temp, 0, 2);
        int[] pair2 = Arrays.copyOfRange(temp, 2, 5);
        if(triple2[0] != pair2[0] && containsOnlyRepeatingVal(triple2) && containsOnlyRepeatingVal(pair2))
            scoreCard.replace("f", 25);

        scoreCard.replace("f", -1, 0);
        
        /* absolute cancer
        int[] count3 = new int[2]; //1st spot is the val, 2nd spot is count
        int[] count2 = new int[2];
        for(int i = 0; i < roll.length; i++){ //find val with count of 3
            count3[0] = roll[i];
            for(int j = 0; j < roll.length; j++){ //count instances of val
                if(roll[j] == roll[i])
                    count3[1]++;
            }
            if(count3[1] == 3){ //when you find it
                for(int k = 0; k < roll.length; k++){ //re-check roll for other val
                    if(roll[k] != count3[0]){ //when you find it
                        count2[0] = roll[k];
                        for(int m = 0; m < roll.length; m++) //count instances of other val
                            if(roll[m] == roll[k])
                                count2[1]++;
                        if(count2[1] == 2) 
                            scoreCard.replace("fhouse", 25);
                    }
                }
            }
        }
        */
    }
    public void smstr(){
        int[] temp = new int[5];
        for(int i = 0; i < roll.length; i++)
            temp[i] = roll[i];
        Arrays.sort(temp);

        int[][] solutions = {{1, 2, 3, 4},
                {2, 3, 4, 5},
                {3, 4, 5, 6}};

        int[] scenario1 = Arrays.copyOfRange(temp, 0, 4); // increasing starting from 0
        for(int r = 0; r < solutions.length; r++)
            if(Arrays.equals(scenario1, solutions[r]))
                scoreCard.replace("s", 30);

        int[] scenario2 = Arrays.copyOfRange(temp, 1, 5); // increasing starting from 1
        for(int r = 0; r < solutions.length; r++)
            if(Arrays.equals(scenario2, solutions[r]))
                scoreCard.replace("s", 30);

        scoreCard.replace("s", -1, 0);
    }
    public void lgstr(){
        int[] temp = new int[5];
        for(int i = 0; i < roll.length; i++)
            temp[i] = roll[i];
        Arrays.sort(temp);

        int[][] solutions = {{1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6}};

        int[] scenario1 = Arrays.copyOfRange(temp, 0, 5); // all increasing

        for(int r = 0; r < solutions.length; r++)
            if(Arrays.equals(scenario1, solutions[r]))
                scoreCard.replace("l", 40);

        scoreCard.replace("l", -1, 0);
    }
    public void yahtzee(){
        if(containsOnlyRepeatingVal(roll))
            scoreCard.replace("y", 50);
        scoreCard.replace("y", -1, 0);
    }
    public void chance(){
        int points = 0;
        for(Integer x : roll)
            points += x;
        scoreCard.replace("c", points);
    }

    //helper methods
    public void roll(){
        for(int i = 0; i < roll.length; i++)
            roll[i] = rand(1, 6);
    }
    public void reroll(int[] spots){
        for(int i = 0; i < spots.length; i++)
            roll[spots[i]] = rand(1, 6);
    }
    public void setroll(int[] spots){ //cheat c:
        for(int i = 0; i < spots.length; i++)
            roll[i] = spots[i];
    }
    public int rand(int min, int max){
        return (int)(Math.random()*((max-min)+1))+min;
    }
    public boolean containsOnlyRepeatingVal(int[] list){
        int val = list[0];
        for(Integer x : list)
            if(x != val)
                return false;
        return true;
    }
    public boolean alreadyUsed(String cat){ //cat = category
        if(scoreCard.get(cat) == -1)
            return false;
        return true;
    }
    public void updateAll(){ //updates sum, bonus, and total
        int individualCategories = 0; //sum
        for(int i = 0; i < 6; i++)
            if(scoreCard.get(shortenedNames[i]) != -1)
                individualCategories += scoreCard.get(shortenedNames[i]);
        sum = individualCategories;

        if(sum >= 63)
            bonus = 35;

        int grandTotal = sum + bonus; //total
        for(int i = 6; i < 13; i++)
            if(scoreCard.get(shortenedNames[i]) != -1)
                grandTotal += scoreCard.get(shortenedNames[i]);
        total = grandTotal;
    }
    public boolean allSpotsFilled(){
        for(String x : scoreCard.keySet())
            if(scoreCard.get(x) == -1)
                return false;
        return true;
    }
    public String printRoll(){
        String str = "you rolled [";
        for(int i = 0; i < roll.length; i++)
            if(i == roll.length-1)
                str += roll[i] + "]";
            else
                str += roll[i] + ", ";
        return str;
    }
    public String toString(){
        String str = "\n\n";
        for(int i = 0; i < 13; i++){
            str += fullNames[i];

            //formatting spaces
            int spacesLeft = (8-fullNames[i].length());
            for(int j = 0; j < spacesLeft; j++)
                str += " ";

            str += scoreCard.get(shortenedNames[i]) + "\n";

            //formatting to include sum, bonus, and total
            switch(shortenedNames[i]){
                case "6": updateAll(); str += "-----\nsum     " + sum + "\nbonus   " + bonus + "\n-----\n"; break;
                case "c": str += "-----\ntotal   " + total + "\n"; break;
            }
        }
        return str;
    }
}