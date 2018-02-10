import java.util.*;
public class Hero implements Character{
    private int col; 
    private int row;
    private int health;
    private Weapon weapon;
    private int currency;
    private int direction;
    private boolean mapReceived;
    private String initial;
    private boolean armor;
    private int inventoryLength;
    private int numHalfPotions;
    private int numBombs;
    private int numfullPotions; 
    private int numPhoenixPotions;
    private int bossesKilled;
    private boolean sandals;
    
    public Hero(){
        col = 0;
        row = 14;
        health = 100;
        weapon = new Weapon("Dagger", 10, 30);
        direction = 1;
        mapReceived = false;
        initial = "H";
        armor = false;
        inventoryLength = 2;
        numHalfPotions = 1;
        numfullPotions = 1;
        numPhoenixPotions = 0;
        numBombs = 0;
        bossesKilled = 0;
        sandals = false;
    }
    
    public void putSandalsOn(){
        sandals = true;
    }
    
    public boolean getSandalsOn(){
        return sandals;
    }
    
    public void addBossKilled(){
        bossesKilled = bossesKilled + 1;
    }
    
    public int getBossesKilled(){
        return bossesKilled;
    }
    
    public void setNumBombs(int num){
        numBombs = num;
    }
    
    public void setNumHalfPotions(int num){
        numHalfPotions = num;
    }
    
    public int getNumHalfPotions(){
        return numHalfPotions;
    }
    
    public void setNumFullPotions(int num){
        numfullPotions = num;
    }
    
    public int getNumFullPotions(){
        return numfullPotions;
    }
    
    public void setNumPhoenixPotions(int num){
        numPhoenixPotions = num;
    }
    
    public int getNumPhoenixPotions(){
        return numPhoenixPotions;
    }
    
    public int getNumBombs(){
        return numBombs;
    }
    
    public int getInventoryLength(){
        return inventoryLength;
    }
    
    public void setInventoryLength(int length){
        inventoryLength = length;
    }
    
    public void useArmor(Monster monster){
        if(armor == true){
            monster.setAttack(monster.getAttack() - 10);
        }
    }
    
    public void putArmorOn(){
        armor = true;
    }
    
    public boolean hasArmor(){
        return armor;
    }
    
    public String getInital(){
        return initial;
    }
    
    public boolean getMapReceived(){
        return mapReceived;
    }
    
    public void setMapReceived(boolean hasMap){
        mapReceived = hasMap;
    }
    
    public int getCol(){
        return col;
    }
    
    public int getRow(){
        return row;
    }
    
    public void setCol(int num){
        col = num;
    }
    
    public void setRow(int num){
        row = num;
    }
    
    public int getHealth(){
        return health;
    }
    
    public void setHealth(int newHealth){
        health = newHealth;
    }
    
    public int getAttack(){
        return weapon.getStrength();
    }
    
    public Weapon getWeapon(){
        return weapon;
    }
    
    public void setWeapon(String type, int bottomRange, int topRange){
        weapon = new Weapon(type, bottomRange, topRange);
    }
    
    public int setCurrency(int value){
        currency = value;
        return currency;
    }
    
    public int getCurrency(){
        return currency;
    }
    
    public int getDirection(){
        return direction;
    }
    
    public void setDirection(int newdirec){
        direction = newdirec;
    }
    
}