public class Weapon{
    private int bottomRange;
    private int topRange;
    private String type;
    private int strength;
    
    public Weapon(String weaponType, int bottRange, int topRange){
        type = weaponType;
        bottomRange = bottRange;
        this.topRange = topRange;
    }
    
    public int getAverageStrength(){
        return ((topRange + bottomRange)/2);
    }
    
    public int getStrength(){
        strength = (int)((Math.random() * (topRange - bottomRange)) + bottomRange + 1);
        return strength;
    }
    
    public String getType(){
        return type;
    }
    
    public String toString(){
        return type;
    }
    
}