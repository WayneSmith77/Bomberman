import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    private GreenfootImage bomb1 = new GreenfootImage("bomb1.png"); // Initialising bomb images.
    private GreenfootImage bomb2 = new GreenfootImage("bomb2.png"); // ^^^
    private GreenfootImage bomb3 = new GreenfootImage("bomb3.png"); // ^^^
    private int frame = 0; // Frames determine animation speed and when certain modules execute.
    public int fireRadius = 0; // Determines fire length from powerup, how many fire body objects are placed (i.e. when fireRadius = 1, total length = 2)
    private boolean continueL = true; // Used in conjunction with 'stopFire' module to determine whether each side should keep placing fire.
    private boolean continueR = true; // ^^^
    private boolean continueU = true; // ^^^
    private boolean continueD = true; // ^^^
    private int intersectPos; // Used in 'stopFire' module as coordinates to check if there are other objects in the way of fire.
    private int intersectNeg; // Same as above, but for the negative relative coordinates (left and up).
    private GreenfootImage array[] = Fire.images; // 'images' array from Fire class.
    
    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        bombTick();
        spawnFire();
    }
    
    /**
     * Animates the bomb's ticking before it explodes.
     * Bomb being removed after fire finishes breaking blocks is also included.
     */
    private void bombTick()
    {
        if(frame == 0){
            setImage(bomb1);
        }
        else if(frame == 15){
            setImage(bomb2);
        }
        else if(frame == 30){
            setImage(bomb3);
        }
        else if(frame == 45){
            setImage(bomb2);
        }
        else if(frame == 60){
            setImage(bomb1);
        }
        else if(frame == 75){
            setImage(bomb2);
        }
        else if(frame == 90){
            setImage(bomb3);
        }
        else if(frame == 105){
            setImage(bomb2);
        }
        else if(frame == 120){
            setImage(bomb1);
        }
        else if(frame == 163){
            getWorld().removeObject(this); // Frames synced with fire's frames so both are removed at the same time.
        }
        frame++;
    }

    /**
     * Main module for placing fire in cross pattern around bomb.
     */
    private void spawnFire()
    {
        if(frame == 135){
            Fire fireCore = new Fire();
            getWorld().addObject(fireCore, getX(), getY());
            fireCore.setImage(array[0]);
            for(int count = 1; count <= fireRadius; count++){
                intersectPos = 32 * count;
                intersectNeg = -32 * count;
                if(continueD == true){
                    Fire fireBodyD = new Fire();
                    getWorld().addObject(fireBodyD, getX(), getY() + (32 * count));
                    fireBodyD.setImage(array[8]);
                    if(stopFire(0, intersectPos) == true){
                        continueD = false;
                    }
                }
                if(continueU == true){
                    Fire fireBodyU = new Fire();
                    getWorld().addObject(fireBodyU, getX(), getY() - (32 * count));
                    fireBodyU.setImage(array[8]);
                    if(stopFire(0, intersectNeg) == true){
                        continueU = false;
                    }
                }
                if(continueR == true){
                    Fire fireBodyR = new Fire();
                    getWorld().addObject(fireBodyR, getX() + (32 * count), getY());
                    fireBodyR.setImage(array[4]);
                    if(stopFire(intersectPos, 0) == true){
                        continueR = false;
                    }
                }
                if(continueL == true){
                    Fire fireBodyL = new Fire();
                    getWorld().addObject(fireBodyL, getX() - (32 * count), getY());
                    fireBodyL.setImage(array[4]);
                    if(stopFire(intersectNeg, 0) == true){
                        continueL = false;
                    }
                }
            }
            if(continueD == true){
                Fire fireEndD = new Fire();
                getWorld().addObject(fireEndD, getX(), getY() + ((32 * fireRadius) + 32));
                fireEndD.setImage(array[16]);
            }
            if(continueU == true){
                Fire fireEndU = new Fire();
                getWorld().addObject(fireEndU, getX(), getY() - ((32 * fireRadius) + 32));
                fireEndU.setImage(array[12]);
            }
            if(continueR == true){
                Fire fireEndR = new Fire();
                getWorld().addObject(fireEndR, getX() + ((32 * fireRadius) + 32), getY());
                fireEndR.setImage(array[20]);
            }
            if(continueL == true){
                Fire fireEndL = new Fire();
                getWorld().addObject(fireEndL, getX() - ((32 * fireRadius) + 32), getY());
                fireEndL.setImage(array[24]);
            }
        }
    }
    
    /**
     * Module used in 'spawnFire()' to determine whether there is an object intersecting with fire, and to stop placing fire if there is.
     * Return value of boolean returns true if there is an object in the way. Parameters specify which direction/coordinates to check.
     * Each check is located where the last fire object was placed.
     */
    private boolean stopFire(int intersectX, int intersectY)
    {
        for(Object obj : getObjectsAtOffset(intersectX, intersectY, null)){
            if(obj instanceof Brick || obj instanceof Block || obj instanceof Bomb){
                return true;
            }
        }
        return false;
    }
}
