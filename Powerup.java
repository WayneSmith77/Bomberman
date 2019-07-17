import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Powerup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Powerup extends Actor
{
    boolean effect;
    /**
     * Act - do whatever the Powerup wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Powerup(boolean type) {
        type = effect;
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
    
    public boolean returnAttribute() {
        return effect;
    }        
}
