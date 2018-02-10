public interface Character{
    int getRow();
    
    int getCol();
    
    int getHealth();

    void setRow(int row);
    
    void setCol(int col);
    
    void setHealth(int health);
    
    int getAttack();
    
    int getDirection();
    
    void setDirection(int newdirec);
    
    String getInital();
}