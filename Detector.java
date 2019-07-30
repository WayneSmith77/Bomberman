import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Checker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Detector extends Actor
{
    /**
     * Act - do whatever the Checker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    /**
     * Module used in Bomb's 'spawnFire()' to determine whether there is an object intersecting with fire, and to stop placing fire if there is.
     * Return value of boolean returns true if there is an object in the way. Parameters specify which direction/coordinates to check.
     * Each check is located where the last fire object was placed.
     * 
     * Also used in Enemy classes to check if there are open tiles to move into.
     */
    public boolean checkObstacles(int intersectX, int intersectY)
    {
        for(Object obj : getObjectsAtOffset(intersectX, intersectY, null)){
            if(obj instanceof Brick || obj instanceof Block || obj instanceof Bomb){
                return true;
            }
        }
        return false;
    }
}
