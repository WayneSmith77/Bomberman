import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Powerup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Powerup extends Actor
{
    private boolean effect; // Boolean represents type of powerup. True = bomb powerup, False = fire range/radius powerup.

    /**
     * Constructor with parameter to control which type of powerup is created in the world.
     */
    public Powerup(boolean type) {
        effect = type;
    }
    
    /**
     * Act - do whatever the Powerup wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(isTouching(Player.class)){
            getWorld().removeObject(this); // Removes powerup from world once collected by Player.
        }
    }    
    
    /**
     * Used in Player class to identify which powerup type was collected, then applying effect.
     */
    public boolean returnAttribute() {
        return effect;
    }        
}