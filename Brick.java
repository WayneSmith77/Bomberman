import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Brick here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Brick extends Actor
{
    private int frame = 0; // Frames determine animation speed and when certain modules execute.
    private GreenfootImage brick1 = new GreenfootImage("brick1.png"); // Initialising brick images.
    private GreenfootImage brick2 = new GreenfootImage("brick2.png"); // ^^^
    private GreenfootImage brick3 = new GreenfootImage("brick3.png"); // ^^^
    private GreenfootImage brick4 = new GreenfootImage("brick4.png"); // ^^^
    private GreenfootImage brick5 = new GreenfootImage("brick5.png"); // ^^^
    private GreenfootImage brick6 = new GreenfootImage("brick6.png"); // ^^^
    private GreenfootImage brick7 = new GreenfootImage("brick7.png"); // ^^^
    private boolean touched = false; // Used to check if brick has been hit by fire, if true then rest of animation continues.
    
    /**
     * Act - do whatever the Brick wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        disintegrate();
    } 
    
    /**
     * Animation of brick breaking after being hit by fire.
     */
    public void disintegrate()
    {
        if(isTouching(Fire.class)){
            if(frame == 0){
                setImage(brick2);
            }
            touched = true;
            frame++;
        }
        else if(touched == true){
            if(frame == 3){
                setImage(brick3);
            }
            else if(frame == 6){
                setImage(brick4);
            }
            else if(frame == 9){
                setImage(brick5);
            }
            else if(frame == 12){
                setImage(brick6);
            }
            else if(frame == 15){
                setImage(brick7);
            }
            else if(frame == 18){
                getWorld().removeObject(this);
            }
            frame++;
        }
    }
}
