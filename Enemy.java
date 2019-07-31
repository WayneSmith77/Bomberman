import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Detector
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }
    
    public boolean contact() {
        //Checking whether he is touching any objects.
        if (isTouching(Brick.class) || isTouching(Block.class) || (isTouching(Bomb.class))) {
            return true;
        }    
        return false;
    }     
    
    public void death(int score){
        if(isTouching (Fire.class)){
            Player.score += score;
            getWorld().showText("Score: " + Player.score, 930, 436);
            getWorld().removeObject(this);
        }
    }
}
