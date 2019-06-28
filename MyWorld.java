import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(992, 416, 1);
        prepare();
        Player player = new Player();
        addObject(player, 32 + 16, 32 + 16);
    }
    
    private void prepare()
    {
        int[][] setLocation = new int[13][31];
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
        setLocation[1][1] = 2;
        setLocation[1][2] = 2;
        setLocation[2][1] = 2;
        for(int row = 0; row < 13; row++){
            for(int column = 0; column < 31; column++){
                if(setLocation[row][column] == 1){
                    Block block = new Block();
                    addObject(block, 32 * column + 16, 32 * row + 16);
                }
                else if(setLocation[row][column] == 0){
                    int random = Greenfoot.getRandomNumber(3);
                    if(random == 0){
                        Brick brick = new Brick();
                        addObject(brick, 32 * column + 16, 32 * row + 16);
                    }
                }
            }
        }
    }
}
