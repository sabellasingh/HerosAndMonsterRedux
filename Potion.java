public class Potion{
    int health;
    String type;
    
    public Potion(String type){
        if(type.equals("half")){
            health = 50;
        }else if(type.equals("full")){
            health = 100;
        }else if(type.equals("phoenix")){
            health = 100;
        }
    }
    
    public int getHealth(){
        return health;
    }
}