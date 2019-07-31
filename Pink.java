import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pink here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pink extends Enemy
{
    private int intersectPos = 32;
    private int intersectNeg = -32;
    private int angle = 0;
    private int speed = 1;
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
        death(100);
    }    
    
    private void move()
    {
        if (contact() == true) {
            turn(180);
            angle = 180;
            move(speed);
         }
        move(speed);
    }
}
