import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private int random; // Variable storage for randomly generated numbers.
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
    private GreenfootImage firePower = new GreenfootImage("firepower.png"); // Initialising fire powerup image.
    private GreenfootImage bombPower = new GreenfootImage("bombpower.png"); // Initialising bomb powerup image.
    private int fPowerPlace = 2; // Used to track how many fire powerups left are needed to be placed into the world.
    private int bPowerPlace = 2; // Used to track how many bomb powerups left are needed to be placed into the world.
    private boolean doorPlace = false; // Used to track if the door has been placed in the world yet.
    private int bluePlace = 3; // Used to track how many blue enemies left are needed to be placed into the world.
    private int pinkPlace = 3; // Used to track how many pink enemies left are needed to be placed into the world.
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 992x455 cells with a cell size of 1x1 pixels.
        super(992, 455, 1);
        // Setting the paint order of classes.
        setPaintOrder(Block.class, Player.class, Brick.class, Enemy.class, Fire.class, Bomb.class, Powerup.class, Door.class);
        prepareBlocks();
        preparePowerup();
        prepareDoor();
        prepareEnemies();
        // Creating the player and placing it in the top left space in the grid.
        Player player = new Player();
        addObject(player, 32 + 16, 32 + 16);
        // Initialising Lives and Score text at bottom of the screen.
        showText("Lives: 3", 60, 436);
        showText("Score: 0", 930, 436);
    }
   
    /**
     * Places bricks and blocks into the world to create grid pattern.
     */
    private void prepareBlocks()
    {
        for(int row = 0; row < 13; row++){ // Looping through the grid array.
            if(row == 0 || row == 12){ // First row and last row set to all solid blocks 
                for(int column = 0; column < 31; column++){ // Looping through the grid array.
                    setLocation[row][column] = 1;
                }
            }
            else if(row % 2 == 0){ // Every second row has a space-block-space pattern.
                for(int column = 0; column < 31; column+=2){
                    setLocation[row][column] = 1;
                    if(column != 0){
                        setLocation[row][column - 1] = 0;
                    }
                }
            }
            else if(row % 2 != 0){ // Other rows have a block in the first and tile/column, with the rest of the tiles being open spaces.
                setLocation[row][0] = 1;
                setLocation[row][30] = 1;
                for(int column = 1; column < 30; column++){
                    setLocation[row][column] = 0;
                }
            }
        }
        setLocation[1][1] = 4; // Creating a small spawn area for the player so they do not trapped in with no space for bombs.
        setLocation[1][2] = 4; // ^^^
        setLocation[2][1] = 4; // ^^^
        for(int row = 0; row < 13; row++){
            for(int column = 0; column < 31; column++){
                if(setLocation[row][column] == 1){
                    Block block = new Block();
                    addObject(block, 32 * column + 16, 32 * row + 16); // Placing blocks.
                }
                else if(setLocation[row][column] == 0){
                    random = Greenfoot.getRandomNumber(3); // 1 in 3 chance of bricks.
                    if(random == 0){
                        Brick brick = new Brick();
                        addObject(brick, 32 * column + 16, 32 * row + 16); // Placing bricks.
                        setLocation[row][column] = 2; // Setting current tile to show it is occupied by a brick.
                    }
                }
            }
        }
    }
    
    /**
     * Places 2 of the fire and bomb powerup into the world underneath bricks.
     */
    private void preparePowerup()
    {
        while(bPowerPlace != 0){ // Checking whether all bomb powerups have been placed yet.
            for(int row = 0; row < 13; row++){
                for(int column = 0; column < 31; column++){
                    if(setLocation[row][column] == 2){
                        random = Greenfoot.getRandomNumber(30); // 1 in 30 chance for bricks to have a powerup placed behind.
                        if(random == 0 && bPowerPlace != 0){
                            Powerup powerupB = new Powerup(true); // Passing parameter to specify powerup type.
                            addObject(powerupB, 32 * column + 16, 32 * row + 16); // Placing powerup.
                            powerupB.setImage(bombPower); // Setting powerup image.
                            setLocation[row][column] = 3; // Setting current tile to show a powerup is present.
                            bPowerPlace -= 1;
                        }
                    }
                }
            }
        }
        while(fPowerPlace != 0){ // Checking whether all fire powerups have been placed yet.
            for(int row = 0; row < 13; row++){
                for(int column = 0; column < 31; column++){
                    if(setLocation[row][column] == 2){
                        random = Greenfoot.getRandomNumber(30); // 1 in 30 chance for bricks to have a powerup placed behind.
                        if(random == 0 && fPowerPlace != 0){
                            Powerup powerupF = new Powerup(false); // Passing parameter to specify powerup type.
                            addObject(powerupF, 32 * column + 16, 32 * row + 16); // Placing powerup.
                            powerupF.setImage(firePower); // Setting powerup image.
                            setLocation[row][column] = 3; // Setting current tile to show a powerup is present.
                            fPowerPlace -= 1;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Places door into the world beneath a brick.
     */
    private void prepareDoor(){
        while(doorPlace == false){ // Checking whether door has been placed yet.
            for(int row = 0; row < 13; row++){
                for(int column = 0; column < 31; column++){
                    if(setLocation[row][column] == 2){
                        random = Greenfoot.getRandomNumber(50); // 1 in 50 chance for bricks to have door placed behind.
                        if(random == 0 && doorPlace == false){
                            Door door = new Door();
                            addObject(door, 32 * column + 16, 32 * row + 16); // Placing door.
                            setLocation[row][column] = 5; // Setting current tile to show the door is placed here.
                            doorPlace = true;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Places enemies into the world.
     */
    private void prepareEnemies()
    {
        while(pinkPlace != 0){ // Checking whether all pink enemies have been placed yet.
            for(int row = 0; row < 13; row++){
                for(int column = 0; column < 31; column++){
                    // Checking if tile is open and adjacent tiles are open to allow space for the enemy to start moving.
                    if(setLocation[row][column] == 0 && setLocation[row][column - 1] == 0 && setLocation[row][column + 1] == 0){
                        random = Greenfoot.getRandomNumber(30); // 1 in 30 chance for an enemy to be placed in an open space.
                        if(random == 0 && pinkPlace != 0){
                            Pink pink = new Pink();
                            addObject(pink, 32 * column + 16, 32 * row + 16); // Placing enemy.
                            setLocation[row][column] = 6; // Setting current tile to show enemy is placed here.
                            pinkPlace -= 1;
                        }
                    }
                }
            }
        }
        while(bluePlace != 0){ // Checking whether all blue enemies have been placed yet.
            for(int row = 0; row < 13; row++){
                for(int column = 0; column < 31; column++){
                    // Checking if tile is open and adjacent tiles are open to allow space for the enemy to start moving.
                    if(setLocation[row][column] == 0 && setLocation[row][column - 1] == 0 && setLocation[row][column + 1] == 0){
                        random = Greenfoot.getRandomNumber(30); // 1 in 30 chance for an enemy to be placed in an open space.
                        if(random == 0 && bluePlace != 0){
                            Blue blue = new Blue();
                            addObject(blue, 32 * column + 16, 32 * row + 16); // Placing enemy.
                            setLocation[row][column] = 6; // Setting current tile to show enemy is placed here.
                            bluePlace -= 1;
                        }
                    }
                }
            }
        }
    }
}
