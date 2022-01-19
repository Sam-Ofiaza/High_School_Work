public class Child implements Comparable<Child>
{
    private String name;
    private int age;
    private int niceLvl;
    private String address;

    public Child(){
        name = "No name";
        age = 0;
        niceLvl = 0;
        address = "No address";
    }
    public Child(String na, int ag, int ni, String ad){
        name = na;
        age = ag;
        niceLvl = ni;
        address = ad;
    }
    public String getName(){return name;}
    public int getAge(){return age;}
    public int getNiceLvl(){return niceLvl;}
    public String getAddress(){return address;}
    public void setName(String n){name = n;}
    public void setAge(int a){age = a;}
    public void setNiceLvl(int n){niceLvl = n;}
    public void setAddress(String a){address = a;}
    public int compareTo(Child other){
        return this.niceLvl - other.getNiceLvl();
    }
    public String toString(){
        return name + " (" + age + " years old) is nice level = " + niceLvl + ", " + address;
    }
}