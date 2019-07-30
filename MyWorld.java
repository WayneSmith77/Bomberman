import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private int random;
    private int[][] setLocation = new int[13][31]; // 2D array representing the 31x13 arena grid.
    /**
     * 0 = empty available space for brick/enemy
     * 1 = solid block
     * 2 = breakable brick
     * 3 = breakable brick with powerup underneath
     * 4 = spawn area, always empty
     * 5 = breakable brick with door underneath
     * 6 = empty space occupied by enemy
     */
    private GreenfootImage firePower = new GreenfootImage("firepower.png");
    private GreenfootImage bombPower = new GreenfootImage("bombpower.png");
    private int fPowerPlace = 2;
    private int bPowerPlace = 2;
    private boolean doorPlace = false;
    private int bluePlace = 3;
    private int pinkPlace = 3;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 992x416 cells with a cell size of 1x1 pixels.
        super(992, 455, 1);
        setPaintOrder(Block.class, Player.class, Brick.class, Enemy.class, Fire.class, Bomb.class, Powerup.class, Door.class);
        prepareBlocks();
        preparePowerup();
        prepareDoor();
        prepareEnemies();
        Player player = new Player();
        addObject(player, 32 + 16, 32 + 16);
        showText("Lives: 3", 60, 436);
        showText("Score: 0", 930, 436);
    }
   
    private void prepareBlocks()
    {
        for(int row = 0; row < 13; row++){
            if(row == 0 || row == 12){
                for(int column = 0; column < 31; column++){
                    setLocation[row][column] = 1;
                }
            }
            else if(row % 2 == 0){
                for(int column = 0; column < 31; column+=2){
                    setLocation[row][column] = 1;
                    if(column != 0){
                        setLocation[row][column - 1] = 0;
                    }
                }
            }
            else if(row % 2 != 0){
                setLocation[row][0] = 1;
                setLocation[row][30] = 1;
                for(int column = 1; column < 30; column++){
                    setLocation[row][column] = 0;
                }
            }
        }
        setLocation[1][1] = 4;
        setLocation[1][2] = 4;
        setLocation[2][1] = 4;
        for(int row = 0; row < 13; row++){
            for(int column = 0; column < 31; column++){
                if(setLocation[row][column] == 1){
                    Block block = new Block();
                    addObject(block, 32 * column + 16, 32 * row + 16);
                }
                else if(setLocation[row][column] == 0){
                    random = Greenfoot.getRandomNumber(3);
                    if(random == 0){
                        Brick brick = new Brick();
                        addObject(brick, 32 * column + 16, 32 * row + 16);
                        setLocation[row][column] = 2;
                    }
                }
            }
        }
    }
    
    private void preparePowerup()
    {
        while(bPowerPlace != 0){
            for(int row = 0; row < 13; row++){
                for(int column = 0; column < 31; column++){
                    if(setLocation[row][column] == 2){
                        random = Greenfoot.getRandomNumber(30);
                        if(random == 0 && bPowerPlace != 0){
                            Powerup powerupB = new Powerup(true);
                            addObject(powerupB, 32 * column + 16, 32 * row + 16);
                            powerupB.setImage(bombPower);
                            setLocation[row][column] = 3;
                            bPowerPlace -= 1;
                        }
                    }
                }
            }
        }
        while(fPowerPlace != 0){
            for(int row = 0; row < 13; row++){
                for(int column = 0; column < 31; column++){
                    if(setLocation[row][column] == 2){
                        random = Greenfoot.getRandomNumber(30);
                        if(random == 0 && fPowerPlace != 0){
                            Powerup powerupF = new Powerup(false);
                            addObject(powerupF, 32 * column + 16, 32 * row + 16);
                            powerupF.setImage(firePower);
                            setLocation[row][column] = 3;
                            fPowerPlace -= 1;
                        }
                    }
                }
            }
        }
    }
    
    private void prepareDoor(){
        while(doorPlace == false){
            for(int row = 0; row < 13; row++){
                for(int column = 0; column < 31; column++){
                    if(setLocation[row][column] == 2){
                        random = Greenfoot.getRandomNumber(50);
                        if(random == 0 && doorPlace == false){
                            Door door = new Door();
                            addObject(door, 32 * column + 16, 32 * row + 16);
                            setLocation[row][column] = 5;
                            doorPlace = true;
                        }
                    }
                }
            }
        }
    }
    
    private void prepareEnemies()
    {
        while(pinkPlace != 0){
            for(int row = 0; row < 13; row++){
                for(int column = 0; column < 31; column++){
                    if(setLocation[row][column] == 0 && setLocation[row][column - 1] == 0 && setLocation[row][column + 1] == 0){
                        random = Greenfoot.getRandomNumber(30);
                        if(random == 0 && pinkPlace != 0){
                            Pink pink = new Pink();
                            addObject(pink, 32 * column + 16, 32 * row + 16);
                            setLocation[row][column] = 6;
                            pinkPlace -= 1;
                        }
                    }
                }
            }
        }
        while(bluePlace != 0){
            for(int row = 0; row < 13; row++){
                for(int column = 0; column < 31; column++){
                    if(setLocation[row][column] == 0 && setLocation[row][column - 1] == 0 && setLocation[row][column + 1] == 0){
                        random = Greenfoot.getRandomNumber(30);
                        if(random == 0 && bluePlace != 0){
                            Blue blue = new Blue();
                            addObject(blue, 32 * column + 16, 32 * row + 16);
                            setLocation[row][column] = 6;
                            bluePlace -= 1;
                        }
                    }
                }
            }
        }
    }
}
