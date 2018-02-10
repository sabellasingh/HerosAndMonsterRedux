public abstract class Monster implements Character{
    private int row;
    private int col;
    private int speed;
    private int attack;
    private int health;
    private String direction;
    
    public Monster(){
        row = 0;
        col = 0;
        speed = 0;
        attack = 0;
        health = 0;
        direction = "";
    }
    
    public abstract String getInital();
    
    public abstract int getSpeed();
    
    public abstract int getRow();
    
    public abstract int getCol();
    
    public abstract int getHealth();
    
    public abstract int getAttack();
    
    public abstract int getDirection();

    public abstract void setRow(int num);
    
    public abstract void setCol(int num);
    
    public abstract void setHealth(int health);
    
    public abstract void setAttack(int num);
    
    public abstract void setDirection(int direc);
}