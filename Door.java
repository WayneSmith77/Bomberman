import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Door here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Door extends Actor
{
    public static int doorX; // Static variable for Player to access door's coordinates.
    public static int doorY; // Static variable for Player to access door's coordinates.
    
    /**
     * Act - do whatever the Door wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        doorX = getX(); // Door's X position.
        doorY = getY(); // Door's Y position.
    }    
}
