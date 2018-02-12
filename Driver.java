import java.io.*;
import java.util.*;
import java.util.ArrayList;
public class Driver{
    public static void main(String args[]){
        Object[][] map = new Object[15][15];
        Hero hero = new Hero();
        Object[] setup = new Object[10];
        TownPeople t1 = new TownPeople(1);
        TownPeople t2 = new TownPeople(2);
        TownPeople t3 = new TownPeople(3);
        TownPeople t4 = new TownPeople(4);
        TownPeople t5 = new TownPeople(5);
        TownPeople t6 = new TownPeople(6);
        int objectsPlaced = 0;
        int randCol;
        int randRow;
        Scanner userIn;
        String input;
        String mvmnt;
        
        map[hero.getRow()][hero.getCol()] = hero;
        setup[0] = t1;
        setup[1] = t2;
        setup[2] = t3;
        setup[3] = t4;
        setup[4] = t5;
        setup[5] = t6;
        while(objectsPlaced != setup.length){
            randCol = (int)(Math.random() * 15); 
            randRow = (int)(Math.random() * 15); 
            if(map[randRow][randCol] == null){
                map[randRow][randCol] = setup[objectsPlaced];
                objectsPlaced +=1;
            }
        }
        printMap(map, setup, hero);
        printInventory(hero);
        while((hero.getBossesKilled() != 4) && ((hero.getHealth() > 0) || (hero.getNumPhoenixPotions() > 0))){
            userIn = new Scanner(System.in);
            System.out.println("Enter direction: ");
            input = userIn.nextLine();
            mvmnt = checkMovement(hero, input, map, setup);
            if(mvmnt.equals("") == false){
                move(hero, mvmnt, map);
            }else{
                System.out.println("Invalid Direction!");
                while(mvmnt.equals("") == true){
                    userIn = new Scanner(System.in);
                    System.out.println("Enter direction: ");
                    input = userIn.nextLine();
                    mvmnt = checkMovement(hero, input, map, setup);
                }
                if(mvmnt.equals("z") == false){
                    move(hero, mvmnt, map);
                }
            }
            if(hero.getHealth() <= 0 && hero.getNumPhoenixPotions() > 0){
                hero.setHealth(100);
                hero.setNumPhoenixPotions(hero.getNumPhoenixPotions() - 1);
            }
            updateNumBossesKilled(hero, setup);
            removeDeadBosses(setup, map);
            if(hero.getMapReceived() == false){
                printMap(map, setup, hero);
            }else{
                printGivenMap(map, setup, hero);
            }
            printInventory(hero);
        }
        if((hero.getHealth() <= 0) && (hero.getBossesKilled() != 4)){
            System.out.println("You have failed to kill all the monsters.");
        }else{
            System.out.println("You have killed all the monsters!");
        }
    }
    
    public static void removeDeadBosses(Object[] setup, Object[][] map){
        BossMonster currBoss;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] != null){
                        if(map[i][j] == setup[6]){
                        currBoss = (BossMonster)map[i][j];
                        if(currBoss.getHealth() <= 0){
                            map[i][j] = null;
                        }
                    }else if(map[i][j] == setup[7]){
                        currBoss = (BossMonster)map[i][j];
                        if(currBoss.getHealth() <= 0){
                            map[i][j] = null;
                        }
                    }else if(map[i][j] == setup[8]){
                        currBoss = (BossMonster)map[i][j];
                        if(currBoss.getHealth() <= 0){
                            map[i][j] = null;
                        }
                    }else if(map[i][j] == setup[9]){
                        currBoss = (BossMonster)map[i][j];
                        if(currBoss.getHealth() <= 0){
                            map[i][j] = null;
                        }
                    }
                }
            }
        }
    }
    
    public static void updateNumBossesKilled(Hero hero, Object[] setup){
        BossMonster currBoss;
        for(int i = 6; i < setup.length; i++){
            if(setup[i]!= null){
                currBoss = (BossMonster)setup[i];
                if((currBoss.getHealth() <= 0) && (currBoss.getHealth() != -100)){
                    hero.addBossKilled();
                    currBoss.setHealth(-100);
                }
            }
        }
    }
    
    public static void printMap(Object[][] map, Object[] setup, Hero hero){
        
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] == hero){
                    System.out.print("H\t");
                }else if((hero.getCol() - j == 0) && ((hero.getRow()- i == 1)) && (hero.getRow() > 0) && (((map[hero.getRow() - 1][hero.getCol()] == (setup[0]))) || (map[hero.getRow() - 1][hero.getCol()] == (setup[1])) || (map[hero.getRow() - 1][hero.getCol()] == (setup[2])) || (map[hero.getRow() - 1][hero.getCol()] == (setup[3])) || (map[hero.getRow() - 1][hero.getCol()] == (setup[4])) || (map[hero.getRow()  - 1][hero.getCol()] == (setup[5])))){
                    System.out.print("F\t");
                }else if((hero.getCol() - j == 0) && ((hero.getRow() - i == 2)) && (hero.getRow() > 1) && (((map[hero.getRow() - 2][hero.getCol()] == (setup[0]))) || (map[hero.getRow() - 2][hero.getCol()] == (setup[1])) || (map[hero.getRow() - 2][hero.getCol()] == (setup[2])) || (map[hero.getRow() - 2][hero.getCol()] == (setup[3])) || (map[hero.getRow() - 2][hero.getCol()] == (setup[4])) || (map[hero.getRow() - 2][hero.getCol()] == (setup[5])))){
                    System.out.print("F\t");
                }else if((hero.getCol() - j == 0) && ((hero.getRow() - i == -2)) && (hero.getRow() < 13) && (((map[hero.getRow() + 2][hero.getCol()] == (setup[0]))) || (map[hero.getRow() + 2][hero.getCol()] == (setup[1])) || (map[hero.getRow() + 2][hero.getCol()] == (setup[2])) || (map[hero.getRow() + 2][hero.getCol()] == (setup[3])) || (map[hero.getRow() + 2][hero.getCol()] == (setup[4])) || (map[hero.getRow() + 2][hero.getCol()] == (setup[5])))){
                    System.out.print("F\t");
                }else if((hero.getCol() - j == 0) && ((hero.getRow() - i == -1)) && (hero.getRow() < 14) && (((map[hero.getRow() + 1][hero.getCol()] == (setup[0]))) || (map[hero.getRow() + 1][hero.getCol()] == (setup[1])) || (map[hero.getRow() + 1][hero.getCol()] == (setup[2])) || (map[hero.getRow() + 1][hero.getCol()] == (setup[3])) || (map[hero.getRow() + 1][hero.getCol()] == (setup[4])) || (map[hero.getRow() + 1][hero.getCol()] == (setup[5])))){
                    System.out.print("F\t");
                }else if((hero.getRow() - i == 0) && ((hero.getCol() - j == 1)) && (hero.getCol() > 0) && (((map[hero.getRow()][hero.getCol() - 1] == (setup[0]))) || (map[hero.getRow()][hero.getCol() - 1] == (setup[1])) || (map[hero.getRow()][hero.getCol() - 1] == (setup[2])) || (map[hero.getRow()][hero.getCol() - 1] == (setup[3])) || (map[hero.getRow()][hero.getCol() - 1] == (setup[4])) || (map[hero.getRow()][hero.getCol() - 1] == (setup[5])))){
                    System.out.print("F\t");
                }else if((hero.getRow() - i == 0) && ((hero.getCol() - j == 2)) && (hero.getCol() > 1) && (((map[hero.getRow()][hero.getCol() - 2] == (setup[0]))) || (map[hero.getRow()][hero.getCol() - 2] == (setup[1])) || (map[hero.getRow()][hero.getCol() - 2] == (setup[2])) || (map[hero.getRow()][hero.getCol() - 2] == (setup[3])) || (map[hero.getRow()][hero.getCol() - 2] == (setup[4])) || (map[hero.getRow()][hero.getCol() - 2] == (setup[5])))){
                    System.out.print("F\t");
                }else if((hero.getRow() - i == 0) && ((hero.getCol() - j == -2)) && (hero.getCol() < 13) && (((map[hero.getRow()][hero.getCol() + 2] == (setup[0]))) || (map[hero.getRow()][hero.getCol() + 2] == (setup[1])) || (map[hero.getRow()][hero.getCol() + 2] == (setup[2])) || (map[hero.getRow()][hero.getCol() + 2] == (setup[3])) || (map[hero.getRow()][hero.getCol() + 2] == (setup[4])) || (map[hero.getRow()][hero.getCol() + 2] == (setup[5])))){
                    System.out.print("F\t");
                }else if((hero.getRow() - i == 0) && ((hero.getCol() - j == -1)) && (hero.getCol() < 14) && (((map[hero.getRow()][hero.getCol() + 1] == (setup[0]))) || (map[hero.getRow()][hero.getCol() + 1] == (setup[1])) || (map[hero.getRow()][hero.getCol() + 1] == (setup[2])) || (map[hero.getRow()][hero.getCol() + 1] == (setup[3])) || (map[hero.getRow()][hero.getCol() + 1] == (setup[4])) || (map[hero.getRow()][hero.getCol() + 1] == (setup[5])))){
                    System.out.print("F\t");
                }else if((map[i][j] != null) && ((map[i][j] == setup[6]) || (map[i][j] == setup[7]) || (map[i][j] == setup[8]) || (map[i][j] == setup[9]))){
                    System.out.print("B" + Integer.toString((hero.getBossesKilled() + 1)) + "\t");
                }else{
                    System.out.print(".\t");
                }
            }
            System.out.println("\n");
        }
    }
    
    public static void printGivenMap(Object[][] map, Object[] setup, Hero hero){
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(map[i][j] == hero){
                    System.out.print("H\t");
                }else if((map[i][j] == setup[0]) || (map[i][j] == setup[1]) || (map[i][j] == setup[2]) || (map[i][j] == setup[3]) || (map[i][j] == setup[4]) || (map[i][j] == setup[5])){
                    System.out.print("F\t");
                }else if(map[i][j] != null){
                    System.out.print("B" + Integer.toString((hero.getBossesKilled() + 1)) + "\t");
                }else{
                    System.out.print(".\t");
                }
            }
            System.out.println("\n");
        }
    }
    
    public static void printInventory(Hero hero){
        int numHides = hero.getCurrency();
        int potions;
        int health = hero.getHealth();
        Weapon weapon = hero.getWeapon();
        int numPotions = 0;
        
        System.out.println("Health: " + health + "\tWeapon: " + weapon.getType() + "\tCurrency: $" + numHides + "\tArmor: " + hero.hasArmor() + "\tBombs: " + hero.getNumBombs());
        System.out.println("Half Potions: " + hero.getNumHalfPotions() + "\t\tFull Potions: " + hero.getNumFullPotions() + "\t\tPhoenix Potions: " + hero.getNumPhoenixPotions());
    } 
    
    public static String checkMovement(Hero hero, String input, Object[][] map, Object[] setup){
        if((input.toLowerCase().equals("w") || input.toLowerCase().equals("north")) && (hero.getRow() - 1 >= 0)){
            if(map[hero.getRow() - 1][hero.getCol()] != null){
                if((map[hero.getRow() - 1][hero.getCol()] != setup[6]) && (map[hero.getRow() - 1][hero.getCol()] != setup[7]) && (map[hero.getRow() - 1][hero.getCol()] != setup[8]) && (map[hero.getRow() - 1][hero.getCol()] != setup[9])){
                    interact(map[hero.getRow() - 1][hero.getCol()], hero, map, setup);
                }else{
                    fight(hero, (Monster)(map[hero.getRow() - 1][hero.getCol()]));
                }
                return "z";
            }else if(map[hero.getRow() - 1][hero.getCol()] == null){
                smallMonsterAttack(hero);
            }
            return "w";
        }else if((input.toLowerCase().equals("s") || input.toLowerCase().equals("south")) && (hero.getRow() +1 <= 14)){
            if(map[hero.getRow() + 1][hero.getCol()] != null){
                if((map[hero.getRow() + 1][hero.getCol()] != setup[6]) && (map[hero.getRow() + 1][hero.getCol()] != setup[7]) && (map[hero.getRow() + 1][hero.getCol()] != setup[8]) && (map[hero.getRow() + 1][hero.getCol()] != setup[9])){
                    interact(map[hero.getRow() + 1][hero.getCol()], hero, map, setup);
                }else{
                    fight(hero, (Monster)(map[hero.getRow() + 1][hero.getCol()]));
                }
                return "z";
            }else if(map[hero.getRow() + 1][hero.getCol()] == null){
                smallMonsterAttack(hero);
            }
            return "s";
        }else if((input.toLowerCase().equals("d") || input.toLowerCase().equals("east")) && (hero.getCol() + 1 <= 14)){
            if(map[hero.getRow()][hero.getCol() + 1] != null){
                if((map[hero.getRow()][hero.getCol() + 1] != setup[6]) && (map[hero.getRow()][hero.getCol() + 1] != setup[7]) && (map[hero.getRow()][hero.getCol() + 1] != setup[8]) && (map[hero.getRow()][hero.getCol() + 1] != setup[9])){
                    interact(map[hero.getRow()][hero.getCol() + 1], hero, map, setup);
                }else{
                    fight(hero, (Monster)(map[hero.getRow()][hero.getCol() + 1]));
                }
                return "z";
            }else if(map[hero.getRow()][hero.getCol()  + 1] == null){
                smallMonsterAttack(hero);
            }
            return "d";
        }else if((input.toLowerCase().equals("a") || input.toLowerCase().equals("west")) && (hero.getCol() - 1 >= 0)){
            if(map[hero.getRow()][hero.getCol() - 1] != null){
                if((map[hero.getRow()][hero.getCol() - 1] != setup[6]) && (map[hero.getRow()][hero.getCol() - 1] != setup[7]) && (map[hero.getRow()][hero.getCol() - 1] != setup[8]) && (map[hero.getRow()][hero.getCol() - 1] != setup[9])){
                    interact(map[hero.getRow()][hero.getCol() - 1], hero, map, setup);
                }else{
                    fight(hero, (Monster)(map[hero.getRow()][hero.getCol() - 1]));
                }
                return "z";
            }else if(map[hero.getRow() ][hero.getCol() - 1] == null){
                smallMonsterAttack(hero);
            }
            return "a";
        }else{
            return "";
        }
    }
    
    public static void move(Hero hero, String mvmnt, Object[][] map){
        if(mvmnt.equals("d")){
            map[hero.getRow()][hero.getCol()] = null;
            hero.setCol(hero.getCol() + 1);
            map[hero.getRow()][hero.getCol()] = hero;
        }else if(mvmnt.equals("a")){
            map[hero.getRow()][hero.getCol()] = null;
            hero.setCol(hero.getCol() - 1);
            map[hero.getRow()][hero.getCol()] = hero;
        }else if(mvmnt.equals("w")){
            map[hero.getRow()][hero.getCol()] = null;
            hero.setRow(hero.getRow() - 1);
            map[hero.getRow()][hero.getCol()] = hero;
        }else if(mvmnt.equals("s")){
            map[hero.getRow()][hero.getCol()] = null;
            hero.setRow(hero.getRow() + 1);
            map[hero.getRow()][hero.getCol()] = hero;
        }
    }
    
    public static void interact(Object obj, Hero hero, Object[][] map, Object[] setup){
        TownPeople person = (TownPeople)obj;
        int num = person.getPersonNum();
        Scanner userIn;
        String input;
        int numinput;
        BossMonster boss;
        int currItems = hero.getNumHalfPotions() + hero.getNumFullPotions() + hero.getNumPhoenixPotions() + hero.getNumBombs();
        int itemsAllowed = hero.getInventoryLength();
        
        if(num == 1){
            System.out.println("Here is a map to the rest of the farmers!");
            hero.setMapReceived(true);
        }else if(num == 2){
            System.out.println("I see you want to challenge the first boss.");
            if((hero.getBossesKilled() == 0) && (setup[6] == null)){
                System.out.println("Let me give you directions to him. \nBe warned, you must have bought a satchel and filled it with potions to beat him.\nOtherwise you stand no chance.");
                createBoss(hero.getBossesKilled(), map, hero, setup);
            }else if(hero.getBossesKilled() > 0){
                System.out.println("Thank you for vanquishing the first beast. See another farmer for the next boss.");
            }else{
                System.out.println("I have already given you directions to the boss, go defeat it!");
            }
        }else if(num == 3){
            System.out.println("I see you want to challenge the second boss.");
            if((hero.getBossesKilled() == 1) && (setup[7] == null)){
                System.out.println("Let me give you directions to him. \nBe warned, you must have slain the first boss and gain the short sword to beat him.\nOtherwise you stand no chance.");
                createBoss(hero.getBossesKilled(), map, hero, setup);
            }else if(hero.getBossesKilled() > 1){
                System.out.println("Thank you for vanquishing the second beast. See another farmer for the next boss.");
            }else if(hero.getBossesKilled() < 1){
                System.out.println("You are not ready to challenge this monster! Please kill the first boss to gain directions!");
            }else{
                System.out.println("I have already given you directions to the boss, go defeat it!");
            }
        }else if(num == 4){
            System.out.println("I see you want to challenge the third boss.");
            if((hero.getBossesKilled() == 2) && (setup[8] == null)){
                System.out.println("Let me give you directions to him. \nBe warned, you must have slain the previous bosses and gain the long sword to beat him.\nOtherwise you stand no chance.");
                createBoss(hero.getBossesKilled(), map, hero, setup);
            }else if(hero.getBossesKilled() > 2){
                System.out.println("Thank you for vanquishing the third beast. See another farmer for the next boss.");
            }else if(hero.getBossesKilled() < 2){
                System.out.println("You are not ready to challenge this monster! Please kill the previous bosses to gain directions!");
            }else{
                System.out.println("I have already given you directions to the boss, go defeat it!");
            }
        }else if(num == 5){
            System.out.println("I see you want to challenge the final boss.");
            if((hero.getBossesKilled() == 3) && (setup[9] == null)){
                System.out.println("Let me give you directions to him. \nBe warned, you must have slain the previous bosses and gain the sandals, breastplate, battle axe, and three phoenix downs to beat him.\nOtherwise you stand no chance.");
                createBoss(hero.getBossesKilled(), map, hero, setup);
            }else if(hero.getBossesKilled() > 3){
                System.out.println("Thank you for vanquishing the final beast!");
            }else if(hero.getBossesKilled() < 3){
                System.out.println("You are not ready to challenge this monster! Please kill the previous bosses to gain directions!");
            }else{
                System.out.println("I have already given you directions to the final boss, go defeat it!");
            }
        }else if(num == 6){
            System.out.println("I see you have come to buy from my store!");
            System.out.println("What would you like to buy? Pick Half Potion, Full Potion, Bomb, Breastplate, Satchel, Sandals.");
            userIn = new Scanner(System.in);
            input = userIn.nextLine();
            if(input.toLowerCase().equals("half potion")){
                System.out.println("Half Potions cost 5 gold. How many would you like?");
                userIn = new Scanner(System.in);
                numinput = userIn.nextInt();
                if((currItems + numinput) > itemsAllowed){
                    System.out.println("You do not have enough space to buy this!");
                }else if(hero.getCurrency() >= (5 * numinput)){
                    hero.setNumHalfPotions(numinput + hero.getNumHalfPotions());
                    hero.setCurrency(hero.getCurrency() - (5 * numinput));
                }else if(hero.getCurrency() < (5 * numinput)){
                    System.out.println("You do not have enough to buy this yet!");
                }
            }else if(input.toLowerCase().equals("full potion")){
                System.out.println("Full Potions cost 10 gold. How many would you like?");
                userIn = new Scanner(System.in);
                numinput = userIn.nextInt();
                if((currItems + numinput) > itemsAllowed){
                    System.out.println("You do not have enough space to buy this!");
                }else if(hero.getCurrency() >= (10 * numinput)){
                    hero.setNumFullPotions(numinput + hero.getNumFullPotions());
                    hero.setCurrency(hero.getCurrency() - (10 * numinput));
                }else if(hero.getCurrency() < (10 * numinput)){
                    System.out.println("You do not have enough to buy this yet!");
                }
            }else if(input.toLowerCase().equals("bomb")){
                System.out.println("Bombs cost 20 gold. How many would you like?");
                userIn = new Scanner(System.in);
                numinput = userIn.nextInt();
                if((currItems + numinput) > itemsAllowed){
                    System.out.println("You do not have enough space to buy this!");
                }else if(hero.getCurrency() >= (20 * numinput)){
                    hero.setNumBombs(numinput + hero.getNumBombs());
                    hero.setCurrency(hero.getCurrency() - (20 * numinput));
                }else if(hero.getCurrency() < (20 * numinput)){
                    System.out.println("You do not have enough to buy this amount yet!");
                }
            }else if(input.toLowerCase().equals("breastplate")){
                System.out.println("Breastplate costs 20 gold. You may only get one. Enter 0 if you do not want this at this time, else enter 1. ");
                userIn = new Scanner(System.in);
                numinput = userIn.nextInt();
                if(numinput == 1 && hero.getCurrency() >= 20){
                    hero.putArmorOn();
                    hero.setCurrency(hero.getCurrency() - 20);
                }else if(hero.getCurrency() < 20){
                    System.out.println("You do not have enough to buy this yet!");
                }
            }else if(input.toLowerCase().equals("satchel")){
                System.out.println("Satchel costs 20 gold. You may only get one to hold six items. Enter 0 if you do not want this at this time, else enter 1. ");
                userIn = new Scanner(System.in);
                numinput = userIn.nextInt();
                if(numinput == 1 && hero.getCurrency() >= 20){
                    hero.setInventoryLength(6);
                    hero.setCurrency(hero.getCurrency() - 20);
                }else if(hero.getCurrency() < 20){
                    System.out.println("You do not have enough to buy this yet!");
                }
            }else if(input.toLowerCase().equals("sandals")){
                System.out.println("Sandals costs 20 gold. You may only get one. Enter 0 if you do not want this at this time, else enter 1. ");
                userIn = new Scanner(System.in);
                numinput = userIn.nextInt();
                if(numinput == 1 && hero.getCurrency() >= 20){
                    hero.putSandalsOn();
                    hero.setCurrency(hero.getCurrency() - 20);
                }else if(hero.getCurrency() < 20){
                    System.out.println("You do not have enough to buy this yet!");
                }
            }
        }
    }
    
    public static void createBoss(int bossesKilled, Object[][] map, Hero hero, Object[] setup){
        int bossNum = hero.getBossesKilled() + 1;
        BossMonster boss = new BossMonster(0,0,0,0,0,0, 0);
        int row = (int)(Math.random() * 14);
        int col = (int)(Math.random() * 14);
        int speed;
        int attack;
        int health = 100;
        int direc = 3;
        
        while(map[row][col] != null){
            row = (int)(Math.random() * 14);
            col = (int)(Math.random() * 14);
        }
        
        if(bossNum == 1){
            attack =  hero.getWeapon().getAverageStrength() - 5;
            if((hero.getInventoryLength() != 6) || (hero.getNumFullPotions() != 6)){
                attack = 100;
            }
            boss = new BossMonster(row, col, 0, attack, health, direc, bossNum);
            map[row][col] = boss;
            setup[6] = boss;
        }else if(bossNum == 2){
            attack =  hero.getWeapon().getAverageStrength() - 4;
            if(hero.getWeapon().getType().equals("short sword") == false){
                attack = 100;
            }
            boss = new BossMonster(row, col, 0, attack, health, direc, bossNum);
            map[row][col] = boss;
            setup[7] = boss;
        }
        else if(bossNum == 3){
            attack =  hero.getWeapon().getAverageStrength() - 3;
            if(hero.getWeapon().getType().equals("long sword") == false){
                attack = 100;
            }
            boss = new BossMonster(row, col, 0, attack, health, direc, bossNum);
            map[row][col] = boss;
            setup[8] = boss;
        }
        else if(bossNum == 4){
            attack =  hero.getWeapon().getAverageStrength() - 2;
            if((hero.getWeapon().getType().equals("battle axe") == false) || (hero.getSandalsOn() == false) || (hero.hasArmor() == false) || (hero.getNumPhoenixPotions() < 3)){
                attack = 100;
            }
            boss = new BossMonster(row, col, 0, attack, health, direc, bossNum);
            map[row][col] = boss;
            setup[9] = boss;
        }
        
    }
    
    public static void smallMonsterAttack(Hero hero){
        int probability = (int)(Math.random() * 9 + 1);
        SmallMonster smallMonster;
        int monsterSpeed;
        int monsterAttack;
        int monsterDirec = 0;
        int randNum;
        
        if((probability == 1) || (probability == 2)){
            if(hero.hasArmor() == true){
                monsterSpeed = (int)(Math.random() * 1 + 2);
            }else{
                monsterSpeed = (int)(Math.random() * 2);
            }
            randNum = (int)(Math.random() * 4);
            if(randNum == 0){
                monsterDirec = -1;
            }else if(randNum == 1){
                monsterDirec = 1;
            }else if(randNum == 2){
                monsterDirec = 2;
            }else if(randNum == 3){
                monsterDirec = -2;
            }
            monsterAttack = hero.getWeapon().getAverageStrength() - 20;
            if(monsterAttack <= 0){
                monsterAttack = 5;
            }
            smallMonster = new SmallMonster(monsterSpeed, monsterAttack, 100, monsterDirec);
            fight(hero, smallMonster);
        }
    }
    
    public static void fight(Hero hero, Monster object){
        Scanner input;
        String choice;
        Monster monster = null;
        int probability;
        boolean run = false;
        BossMonster boss = null;
        int numAttacks = 1;
        int currAttack;
        int bossNum;
        
        
        if(hero.getSandalsOn() == true){
            numAttacks = 2;
        }
        
        if(object.getInital().equals("M")){
            monster = (SmallMonster)object;
        }else if(object.getInital().equals("B")){
            monster = (BossMonster)object;
            boss  = (BossMonster)object;
            monster = boss;
        }

        if(object.getInital().equals("M")){
            System.out.println("You have encountered a small monster!");
        }else if(object.getInital().equals("B")){
            System.out.println("You have encountered the Boss!");
        }  
        
        while((monster.getHealth() > 0) && (hero.getHealth() > 0)){
            System.out.println("\n\nEnter an action (run, attack, potion, bomb): ");
            input = new Scanner(System.in);
            choice = input.nextLine().toLowerCase();
            
            if((choice.equals("attack") == false) && (choice.equals("run") == false) && (choice.equals("bomb") == false) && (choice.equals("potion") == false)){
                while((choice.equals("attack") == false) && (choice.equals("run") == false)){
                    System.out.println("Enter a valid choice!");
                    System.out.println("\n\nEnter an action (run,attack, potion, bomb): ");
                    input = new Scanner(System.in);
                    choice = input.nextLine().toLowerCase();
                    System.out.println(choice);
                }
            }
            
            
            if((choice.equals("potion"))){
                input = new Scanner(System.in);
                System.out.println("Which Type?");
                choice = input.nextLine().toLowerCase();
                if(choice.toLowerCase().equals("half potion") || choice.toLowerCase().equals("half")){
                    if(hero.getNumHalfPotions() > 0){
                        hero.setHealth(hero.getHealth() + 50);
                        if(hero.getHealth() > 100){
                            hero.setHealth(100);
                        }
                        hero.setNumHalfPotions(hero.getNumHalfPotions() - 1);
                    }else{
                        System.out.println("No half potions available. Turn lost.");
                    } 
                }else if(choice.toLowerCase().equals("full potion") || choice.toLowerCase().equals("full")){
                    if(hero.getNumFullPotions() > 0){
                        hero.setHealth(100);
                        hero.setNumFullPotions(hero.getNumFullPotions() - 1);
                    }else{
                        System.out.println("No half potions available. Turn lost.");
                    } 
                }
            }else if((choice.equals("bomb"))){
                if(hero.getNumBombs() > 0){
                    monster.setHealth(0);
                    hero.setNumBombs(hero.getNumBombs() - 1);
                }else{
                    System.out.println("No bombs available. Turn lost.");
                }   
            }else if(choice.equals("run")){
                if((hero.getDirection() == monster.getDirection()) || (hero.getSandalsOn() == true)){
                    System.out.println("You have successfully run from a monster!");
                    return;
                }else if((hero.getDirection() * -1 == monster.getDirection()) || (monster.getDirection() == 3)){
                    System.out.println("Monster is too fast, cannot run!");
                }else{
                    probability = (int)(Math.random() * 1 );
                    if(probability == 0){
                        System.out.println("You have successfully run from a monster!");
                        return;
                    }else{
                        System.out.println("Monster is too fast, cannot run!");
                    }
                }
            }else if(choice.equals("attack")){
                for(int i = 0; i < numAttacks; i++){
                    if(monster.getSpeed() == 2 ||   monster.getSpeed() == 3){
                        System.out.println("Hero attack missed!");
                    }else{
                        hero.useArmor(monster);
                        monster.setHealth(monster.getHealth() - hero.getAttack());
                        System.out.println("The hero attacks!");
                        if(monster.getHealth() < 0){
                            monster.setHealth(0);
                        }
                        System.out.println("Monster health goes down to " + monster.getHealth() + "/100");
                    }
                }
            }
            
            if(run != true){
               if(hero.getHealth() <= 0){
                   hero.setHealth(0);
                   System.out.println("Hero has died.");
                   if(hero.getNumPhoenixPotions() >= 1){
                       hero.setHealth(100);
                       System.out.println("Miraculously, you had a phoenix potion that brought you back to life!");
                       hero.setRow(14);
                       hero.setCol(0);
                       return;
                    }
               }else if(monster.getHealth() > 0){
                   System.out.println("The monster attacks!");
                   currAttack = monster.getAttack() + ((int)(Math.random() * 4 - 2));
                   if(currAttack <= 0){
                       hero.setHealth(hero.getHealth() + currAttack);
                   }else{
                       hero.setHealth(hero.getHealth() - currAttack);
                   }
                   System.out.println("Hero’s energy goes down to " + hero.getHealth() + "/100");
               }
            }
        }
        
        if(monster.getHealth() <= 0){
            System.out.println("Monster is slain!");
            if(monster.getInital().equals("M")){
                hero.setCurrency(hero.getCurrency() + 10);
            }else if(monster.getInital().equals("B")){
                hero.setCurrency(hero.getCurrency() + 30);
                if(hero.getBossesKilled() == 0){
                    hero.setWeapon("short sword", 20, 40);
                }else if(hero.getBossesKilled() == 1){
                    hero.setWeapon("long sword", 30, 50);
                    hero.setNumPhoenixPotions(1);
                }else if(hero.getBossesKilled() == 2){
                    hero.setWeapon("battle axe", 40, 60);
                    hero.setNumPhoenixPotions(hero.getNumPhoenixPotions() + 2);
                }
            }
        }else if(hero.getHealth() <= 0){
            System.out.println("Hero is slain!");
        }
    }
}