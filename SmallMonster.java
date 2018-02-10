public class SmallMonster extends Monster{
    private int row;
    private int col;
    private int speed;
    private int attack;
    private int health;
    private int direction;
    private String initial;
    
    public SmallMonster(int speed, int attck, int hlth, int direc){
        super();
        row = 0;
        col = 0;
        this.speed = speed;
        attack = attck;
        health = 100;
        direction = direc;
        initial = "M";
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public String getInital(){
        return initial;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getAttack(){
        int attackRange;
        
        attackRange = (int)((Math.random() * 10) - 5);
        return attack + attackRange;
    }
    
    public int getDirection(){
        return direction;
    }

    public void setRow(int num){
        row = num;
    }
    
    public void setCol(int num){
        col = num;
    }
    
    public void setHealth(int health){
        this.health = health;
    }
    
    public void setDirection(int direc){
        direction = direc;
    }
    
    public void setAttack(int num){
        attack = num;
    }
}