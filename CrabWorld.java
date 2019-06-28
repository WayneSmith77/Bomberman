import greenfoot.*;  // (Actor, World, Greenfoot, GreenfootImage)

public class CrabWorld extends World
{
    /**
     * Create the crab world (the beach). Our world has a size 
     * of 560x560 cells, where every cell is just 1 pixel.
     */
    public CrabWorld() 
    {
        super(560, 560, 1); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Crab bomberman = new Crab(3);
        addObject(bomberman, 500, 500);
        Lobster lobster = new Lobster();
        addObject(lobster,75,343);
        Lobster lobster2 = new Lobster();
        addObject(lobster2,426,140);
        Lobster lobster3 = new Lobster();
        addObject(lobster3,217,35);
    }
}