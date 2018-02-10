public class TownPeople{
    private String output;
    private int designatedPerson;
    private String initial;
    
    public TownPeople(){
        output = "";
        designatedPerson = 0;
        initial = "F";
    }
    
    public TownPeople(int num){
        output = "";
        designatedPerson = num;
        initial = "F";
    }
    
    public String getInital(){
        return initial;
    }
    
    public int getPersonNum(){
        return designatedPerson;
    }
}