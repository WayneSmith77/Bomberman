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
    private GreenfootImage firePower = new GreenfootImage("firepower.png");
    private GreenfootImage bombPower = new GreenfootImage("bombpower.png");
    private int fPowerPlace = 2;
    private int bPowerPlace = 2;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 992x416 cells with a cell size of 1x1 pixels.
        super(992, 455, 1);
        setPaintOrder(Block.class, Player.class, Brick.class, Fire.class, Bomb.class, Powerup.class);
        prepareBlocks();
        preparePowerup();
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
}
