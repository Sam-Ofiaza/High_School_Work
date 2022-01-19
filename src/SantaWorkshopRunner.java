import java.util.Stack;
import java.util.Map;
import java.util.LinkedHashMap; //LinkedHashMap used to maintain insertion order
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SantaWorkshopRunner
{
    public static void main(String[] args){
        ArrayList<Child> children = new ArrayList<Child>();
        children.add(new Child("Marcie", 12, 0, "578 Main St"));
        children.add(new Child("Linus", 7, 0, "205 Main St"));
        children.add(new Child("Lucy", 10, 0, "136 Main St"));
        children.add(new Child("Woodstock", 5, 0, "237 Main St"));
        children.add(new Child("Sally", 12, 0, "297 Main St"));

        LinkedHashMap<Child, ArrayList<String>> niceList = new LinkedHashMap<Child, ArrayList<String>>();
        LinkedHashMap<Child, ArrayList<String>> naughtyList = new LinkedHashMap<Child, ArrayList<String>>();
        LinkedHashMap<Child, Stack<String>> presents = new LinkedHashMap<Child, Stack<String>>();
        PriorityQueue<Child> route = new PriorityQueue<Child>();

        for(Child x : children){
            niceList.put(x, new ArrayList<String>());
            naughtyList.put(x, new ArrayList<String>());
            presents.put(x, new Stack<String>());
            route.add(x);
        }

        boolean stop = false;
        while(!stop){
            System.out.print("Santa's Workshop Software v0.1\n1 - print list of children being monitored\n2 - record a nice action\n3 - record a naughty action\n4 - update nice/naughty state\n5 - print the Christmas Eve route\n6 - exit\n\nEnter menu selection: ");
            Scanner input = new Scanner(System.in);
            String entry = input.next();
            String desc = ""; //additional reference to store input descriptions
            while(!isNumber(entry) || (Integer.parseInt(entry) < 1 || Integer.parseInt(entry) > 6)){
                System.out.print("\nPlease enter a valid number: ");
                entry = input.next();
            }
            switch(Integer.parseInt(entry)){
                case 1: //print children
                    System.out.println("\n" + printChildren(children)); break;
                case 2: //record nice
                    System.out.print("\n" + printChildren(children) + "\nPick child: ");
                    entry = input.next();
                    while(!isNumber(entry) || (Integer.parseInt(entry) < 0 || Integer.parseInt(entry) > children.size())){
                        System.out.print("\nPlease enter a valid number: ");
                        entry = input.next();
                    }
                    System.out.print("\nEnter a description: ");
                    input.nextLine();
                    desc = input.nextLine();
                    niceList.get(children.get(Integer.parseInt(entry))).add(desc);
                    System.out.println("\n\nNice List:\n" + printMap(niceList));
                    break;
                case 3: //record naughty
                    System.out.print("\n" + printChildren(children) + "\nPick child: ");
                    entry = input.next();
                    while(!isNumber(entry) || (Integer.parseInt(entry) < 0 || Integer.parseInt(entry) > children.size())){
                        System.out.print("\nPlease enter a valid number: ");
                        entry = input.next();
                    }
                    System.out.print("\nEnter a description: ");
                    input.nextLine();
                    desc = input.nextLine();
                    naughtyList.get(children.get(Integer.parseInt(entry))).add(desc);
                    System.out.println("\n\nNaughty List:\n" + printMap(naughtyList));
                    break;
                case 4: //update nice/naughty state
                    for(Child x : children){
                        x.setNiceLvl(niceList.get(x).size() - naughtyList.get(x).size());
                        if(x.getNiceLvl() <= 0){ //child is naughty
                            Stack<String> badStack = presents.get(x);
                            int coal = 0;
                            if(badStack.isEmpty()) //child has nothing
                                badStack.push("lump of coal");
                            else if(!badStack.peek().equals("lump of coal")) //first item checked is a present
                                badStack.pop();
                            else{ //first item is coal
                                while(!badStack.isEmpty() && badStack.peek().equals("lump of coal")){
                                    badStack.pop();
                                    coal++;
                                } //remove all coals until empty or present reached
                                if(badStack.isEmpty()) //if empty
                                    for(int i = 0; i < coal+1; i++)
                                        badStack.push("lump of coal");
                                else{ //if present found
                                    System.out.println(badStack.pop() + " was removed from " + x.getName() + "'s presents");
                                    for(int i = 0; i < coal; i++)
                                        badStack.push("lump of coal");
                                }
                            }
                        }
                        else //child is nice
                            presents.get(x).push(getRandPresent());
                    }
                    System.out.println("\n" + printPresents(presents));
                    break;
                case 5: //print Christmas Eve route
                    System.out.println("\n" + printRoute(route, presents));
                    break;
                case 6: //exit
                    stop = true; System.out.println("\nThank you for using Santa's Workshop Software");
                    break;
            }
        }
    }
    public static String printChildren(ArrayList<Child> listy){
        String output = "";
        for(int i = 0; i < listy.size(); i++)
            output += i + " " + listy.get(i) + "\n";
        return output;
    }
    public static String printMap(LinkedHashMap<Child,ArrayList<String>> map){
        String output = "";
        for(Child x : map.keySet())
            output += x.getName() + " " + map.get(x) + "\n";
        return output;
    }
    public static String printPresents(LinkedHashMap<Child,Stack<String>> map){
        String output = "";
        for(Child x : map.keySet())
            output += x.getName() + " " + map.get(x) + "\n";
        return output;
    }
    public static String printRoute(PriorityQueue<Child> pq, LinkedHashMap<Child,Stack<String>> map){
        String output = "";
        for(Child x : pq){
            int presents = 0;
            int coal = 0;
            Stack<String> pile = map.get(x);
            while(!pile.isEmpty()){
                String stuff = pile.pop();
                switch(stuff){
                    case "lump of coal": coal++; break;
                    default: presents++; break;
                }
            }
            output += x.getName() + " " + x.getAddress() + ", " + presents + " present(s) and " + coal + " lump(s) of coal\n";
        }
        return output;
    }
    public static boolean isNumber(String s){ //misc. helper
        for(int i = 0; i < s.length(); i++)
            if(Character.isDigit(s.charAt(i)) == false)
                return false;
        return true;
    }
    public static String getRandPresent(){ //misc. helper
        String[] gifts = {"atomic bomb", "action figure", "doll", "chocolate", "candy", "stuffed animal", "watch", "new clothes", "gift card", "video game", "camera", "board game", "drum set", "scooter", "lego set", "toy car"};
        return gifts[(int)(Math.random()*gifts.length)];
    }
}

/*
record nice
record naughty
update nice/naughty state
print christmas eve route
exit

child data:
age
nice lvl (nice-naughty lists)
address

weekly present/coal based on cumulative lists

eve route is pQueue based on nice lvl
*/