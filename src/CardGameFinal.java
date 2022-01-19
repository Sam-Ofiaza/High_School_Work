public class CardGameFinal
{
    private String name1 = "";
    private String name2 = "";
    private String name3 = "";
    private String[] player = new String[5];
    private String[] computer = new String[5];
    private int pWins = 0;
    private int cWins = 0;
    private String pCard = "";
    private String cCard = "";

    public CardGameFinal()
    {
        name1 = "ROCK";
        name2 = "PAPER";
        name3 = "SCISSORS";
        pWins = 0;
        cWins = 0;
        pCard = "";
        cCard = "";
        setHand();
    }
    public CardGameFinal(String n1, String n2, String n3, int pw, int cw, String pc, String cc)
    {
        name1 = n1;
        name2 = n2;
        name3 = n3;
        pWins = pw;
        cWins = cw;
        pCard = pc;
        cCard = cc;
        setHand();
    }
    public void setNames(String n1, String n2, String n3)
    {
        name1 = n1;
        name2 = n2;
        name3 = n3;
    }
    public void setPWins(int pw)
    {
        pWins = pw;
    }
    public void setCWins(int cw)
    {
        cWins = cw;
    }
    public String getName1()
    {
        return name1;
    }
    public String getName2()
    {
        return name2;
    }
    public String getName3()
    {
        return name3;
    }
    public int getPWins()
    {
        return pWins;
    }
    public int getCWins()
    {
        return cWins;
    }
    public String getPCard(int x)
    {
        return player[x];
    }
    public String getCCard(int x)
    {
        return computer[x];
    }
    public String toString()
    {
        return getHand() + "\n\nWhich card do you want to play: ";
    }
    public void setHand()
    {
        for(int a = 0; a < player.length; a++)
        {
            int x = (int)(Math.random() * 3 + 1);
            if(x == 1)
                player[a] = name1;
            if(x == 2)
                player[a] = name2;
            if(x == 3)
                player[a] = name3;
        }
        for(int b = 0; b < computer.length; b++)
        {
            int x = (int)(Math.random() * 3 + 1);
            if(x == 1)
                computer[b] = name1;
            if(x == 2)
                computer[b] = name2;
            if(x == 3)
                computer[b] = name3;
        }
    }
    public String getHand()
    {
        return "Player hand: \n0 " + player[0] + "\n1 " + player[1] + "\n2 " + player[2]
                + "\n3 " + player[3] + "\n4 " + player[4] + "\n\nComputer hand: \n0 " + computer[0] +
                "\n1 " + computer[1] + "\n2 " + computer[2] + "\n3 " + computer[3] + "\n4 " +
                computer[4];
    }
    public void emptyHand(int x, int y)
    {
        player[x] = "empty";
        computer[y] = "empty";
    }
}