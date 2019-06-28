import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lobster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lobster extends Actor
{
    /**
     * Act - do whatever the Lobster wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //turnatedge();
        //move(2);
        //randomturn();
        //lookForCrab();
    }        
        
    public void lookForCrab() {
        //if (isTouching(Crab.class)) {
        //    removeTouching(Crab.class);
        //    Greenfoot.playSound("au.wav");
        //    Greenfoot.stop();
        //}
    }
    
    public void turnatedge() {
        if (isAtEdge()) {
            turn(180);    
        }
    }
    
    public void randomturn() {
        int turn = Greenfoot.getRandomNumber(61);
        turn = turn - 30;
        int probability = Greenfoot.getRandomNumber(100);
        if (probability > 90) {
            turn(turn);
        }
        if (isTouching(Crab.class)) {
            move(-2);
        }    
    }    
}
