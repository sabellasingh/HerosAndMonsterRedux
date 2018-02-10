public class BossMonster extends Monster{
    private int row;
    private int col;
    private int speed;
    private int attack;
    private int health;
    private int direction;
    private int bossNum;
    private String initial;
    
    public BossMonster(int r, int c, int speed, int attck, int hlth, int direc, int bossNum){
        super();
        row = r;
        col = c;
        this.speed = speed;
        attack = attck;
        health = hlth;
        direction = direc;
        initial = "B";
    }
    
    public int getBossNum(){
        return bossNum;
    }
    
    public String getInital(){
        return initial;
    }
    
    public int getSpeed(){
        return speed;
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
        return attack;
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