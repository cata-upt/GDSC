import java.util.ArrayList;
abstract class Team{
    protected Member leader;
    protected ArrayList<Member> members;
    protected int maxNumberOfMembers;
    protected String name;
    public Team(String name,int MaxNumberOfMembers){
        maxNumberOfMembers=MaxNumberOfMembers;
        this.name=name;
        members=new ArrayList<Member>(maxNumberOfMembers);
    }

    public boolean addMember(Member m){
        if(members.size()<maxNumberOfMembers){
            members.add(m);
            return true;
        }
        return false;
    }

    public Member removeMember(Member m){
        for(int i=0;i<members.size();i++){
            if(members.get(i).equals(m)){
                members.remove(i);
                return m;
            }
        }
        return null;
    }

    public boolean setLeader(Member newLeader){
        if(newLeader.getExperience()>=5) {
            leader = newLeader;
            return true;
        }
        return false;
    }

    public String toString(){
        String s="";
        if(leader!=null)
        s= "Lider echipa: "+leader.toString();
        if(members!=null && members.size()>0) {
            s += "Membri: ";
            for (int i = 0; i < members.size(); i++) {
                s += members.get(i).toString() + " ";
            }
        }
        return s;
    }
    public void setName(Member m, String newName){
        if(m.equals(leader)){
            this.name=newName;
            System.out.println("Name set!");
        }else{
            System.out.println("You are not the leader!");
        }
    }

    public void setMaxNumberOfMembers(Member m, int newMaxNumberOfMembers){
        if(m.equals(leader)){
            this.maxNumberOfMembers=newMaxNumberOfMembers;
            System.out.println("The maximum number of members set!");
        }else{
            System.out.println("You are not the leader!");
        }
    }
    public String getName(){
        return name;
    }

    public int getMaxNumberOfMembers(){
        return maxNumberOfMembers;
    }

    public abstract double getCost();
}

class DevTeam extends Team{

    public DevTeam(String name,int MaxNumberOfMembers){
        super(name,MaxNumberOfMembers);
    }

    public double getCost(){
        double cost=0.0;
        if(leader!=null) cost+=(double) 2500+leader.getExperience()*250;
        if(members!=null)
        for(int i=0;i<members.size();i++){
            cost+=(double) 1500;
            int experience=members.get(i).getExperience();
            if(experience>=2 && experience<=5)cost+= (double)(0.25*1500);
            else if(experience>5)cost+=(double) (0.5*1500);
        }
        return cost;
    }

}

class HR extends Team{


    public HR(String name,int MaxNumberOfMembers){
        super(name,MaxNumberOfMembers);
    }

    public double getCost(){
        double cost=0.0;
        if(leader!=null) cost+=(double) 1350+leader.getExperience()*300;
        if(members!=null)
        for(int i=0;i<members.size();i++){
            cost+=(double) 1000;
            int experience=members.get(i).getExperience();
            if(experience>=2 && experience<=5)cost+=(double) 0.25*1000;
            else if(experience>5)cost+=(double) 0.5*1000;
        }
        return cost;
    }
}

class Member{
    private int experience,age,salary;
    private String name,surname;

    public Member(String name,String surname,int age, int experience, int salary){
        this.name=name;
        this.surname=surname;
        this.age=age;
        this.experience=experience;
        this.salary=salary;
    }

    public int getExperience(){
        return experience;
    }

    public String toString(){
        return name+" "+surname+":"+age+", "+experience+", "+salary+"\n";
    }

    public boolean equals(Object o){
        if(o instanceof Member){
            Member tmp=(Member)o;
            if(tmp.getExperience()==this.getExperience())return true;
        }
        return false;
    }

}

class Main{
    public static void main(String[] args) {
        DevTeam DT=new DevTeam("DEVTEAM1",10);
        HR HR=new HR("HR1",5);
        Member m1=new Member("Ion","Ion",23,2,1500);
        Member m2=new Member("Andrei","Andrei",60,45,3500);
        Member m3=new Member("Popescu","Alina",45,25,2300);
        Member m4=new Member("Ionescu","Ioana",35,10,2500);
        Member m5=new Member("Popescu","Dani",26,5,2800);
        Member m6=new Member("Marin","Ana",40,23,3200);

        Member m7=new Member("Dan","Mara",30,8,2320);
        Member m8=new Member("John","Maria",31,9,2380);
        Member m9=new Member("Puiu","Andra",35,10,3000);
        Member m10=new Member("Tanu","Dan",20,1,2000);
        Member m11=new Member("Rosu","Claudiu",21,2,3500);
        Member m12=new Member("Dan","Dan",30,6,4200);
        System.out.println(DT.setLeader(m1));
        System.out.println(DT.setLeader(m4));

        DT.addMember(m1);
        DT.addMember(m2);
        DT.addMember(m3);
        DT.addMember(m5);
        DT.addMember(m6);
        System.out.println(DT);
        System.out.println(DT.getCost());

        HR.setLeader(m7);

        HR.addMember(m8);
        HR.addMember(m9);
        HR.addMember(m10);
        HR.addMember(m11);
        HR.addMember(m12);

        System.out.println(HR);
        System.out.println(HR.getCost());
        System.out.println(HR.removeMember(m8));
        System.out.println(HR);
        HR.setName(m8,"HASER");
        HR.setName(m7,"HASER");
        System.out.println(HR.getName());
    }
}