import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Detector
{
    private GreenfootImage bomb1 = new GreenfootImage("bomb1.png"); // Initialising bomb images.
    private GreenfootImage bomb2 = new GreenfootImage("bomb2.png"); // ^^^
    private GreenfootImage bomb3 = new GreenfootImage("bomb3.png"); // ^^^
    private int frame = 0; // Frames determine animation speed and when certain modules execute.
    private int fireRadius = Player.range;
    // Determines fire length from powerup, how many fire body objects are placed (i.e. when fireRadius = 1, total length = 2)
    private boolean continueL = true; // Used in conjunction with 'checkObstacles' module to determine whether each side should keep placing fire.
    private boolean continueR = true; // ^^^
    private boolean continueU = true; // ^^^
    private boolean continueD = true; // ^^^
    private int intersectPos; // Used in 'checkObstacles' module as coordinates to check if there are other objects in the way of fire.
    private int intersectNeg; // Same as above, but for the negative relative coordinates (left and up).
    private GreenfootImage fImg[] = Fire.images; // 'images' array from Fire class.
    
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
            Greenfoot.playSound("explosion.wav"); // Playing bomb explosion sound effect.
            Fire fireCore = new Fire();
            getWorld().addObject(fireCore, getX(), getY()); // Adding fire core on top of bomb.
            fireCore.setImage(fImg[0]);
            for(int count = 1; count <= fireRadius; count++){ // Looping through placement of fire body objects for length of fire radius.
                intersectPos = 32 * count;
                intersectNeg = -32 * count;
                if(continueD == true){ // Down
                    Fire fireBodyD = new Fire();
                    getWorld().addObject(fireBodyD, getX(), getY() + (32 * count)); // Placing fire body.
                    fireBodyD.setImage(fImg[8]);
                    if(checkObstacles(0, intersectPos) == true){ // Checking whether fire intersects any obstacles.
                        continueD = false; // Stops placing fire in down direction.
                    }
                }
                if(continueU == true){ // Up
                    Fire fireBodyU = new Fire();
                    getWorld().addObject(fireBodyU, getX(), getY() - (32 * count)); // Placing fire body.
                    fireBodyU.setImage(fImg[8]);
                    if(checkObstacles(0, intersectNeg) == true){ // Checking whether fire intersects any obstacles.
                        continueU = false; // Stops placing fire in up direction.
                    }
                }
                if(continueR == true){ // Right
                    Fire fireBodyR = new Fire();
                    getWorld().addObject(fireBodyR, getX() + (32 * count), getY()); // Placing fire body.
                    fireBodyR.setImage(fImg[4]);
                    if(checkObstacles(intersectPos, 0) == true){ // Checking whether fire intersects any obstacles.
                        continueR = false; // Stops placing fire in right direction.
                    }
                }
                if(continueL == true){ // Left
                    Fire fireBodyL = new Fire();
                    getWorld().addObject(fireBodyL, getX() - (32 * count), getY()); // Placing fire body.
                    fireBodyL.setImage(fImg[4]);
                    if(checkObstacles(intersectNeg, 0) == true){ // Checking whether fire intersects any obstacles.
                        continueL = false; // Stops placing fire in left direction.
                    }
                }
            }
            if(continueD == true){
                Fire fireEndD = new Fire();
                getWorld().addObject(fireEndD, getX(), getY() + ((32 * fireRadius) + 32)); // Placing fire end.
                fireEndD.setImage(fImg[16]);
            }
            if(continueU == true){
                Fire fireEndU = new Fire();
                getWorld().addObject(fireEndU, getX(), getY() - ((32 * fireRadius) + 32)); // Placing fire end.
                fireEndU.setImage(fImg[12]);
            }
            if(continueR == true){
                Fire fireEndR = new Fire();
                getWorld().addObject(fireEndR, getX() + ((32 * fireRadius) + 32), getY()); // Placing fire end.
                fireEndR.setImage(fImg[20]);
            }
            if(continueL == true){
                Fire fireEndL = new Fire();
                getWorld().addObject(fireEndL, getX() - ((32 * fireRadius) + 32), getY()); // Placing fire end.
                fireEndL.setImage(fImg[24]);
            }
        }
    }
}
