import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    private GreenfootImage img1 = new GreenfootImage("bomb1.png");
    private GreenfootImage img2 = new GreenfootImage("bomb2.png");
    private GreenfootImage img3 = new GreenfootImage("bomb3.png");
    private int frame = 0;
    int fireRadius = 1;
    boolean continueL = true;
    boolean continueR = true;
    boolean continueU = true;
    boolean continueD = true;
    
    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        tick();
        //test(); Don't worry about test(), it's basically identical to pls() in the Fire class
        spawnFire();
    }
    
    private void tick()
    {
        if(frame == 0){
            setImage(img1);
        }
        else if(frame == 20){
            setImage(img2);
        }
        else if(frame == 40){
            setImage(img3);
        }
        else if(frame == 60){
            setImage(img2);
        }
        else if(frame == 80){
            setImage(img1);
        }
        else if(frame == 100){
            setImage(img2);
        }
        else if(frame == 120){
            setImage(img3);
        }
        else if(frame == 140){
            setImage(img2);
        }
        else if(frame == 160){
            setImage(img1);
        }
        frame++;
    }
    
    private void spawnFire()
    {
        if(frame == 180){
            Fire fireA = new Fire();
            getWorld().addObject(fireA, getX(), getY());
        }
    }
    
    private void test()
    {
        Fire fireCore = new Fire();
        Fire fireBodyL = new Fire();
        Fire fireBodyR = new Fire();
        Fire fireBodyU = new Fire();
        Fire fireBodyD = new Fire();
        Fire fireEndL = new Fire();
        Fire fireEndR = new Fire();
        Fire fireEndU = new Fire();
        Fire fireEndD = new Fire();
        fireBodyL.setImage("fire1bh.png");
        fireBodyR.setImage("fire1bh.png");
        fireBodyU.setImage("fire1bv.png");
        fireBodyD.setImage("fire1bv.png");
        fireEndL.setImage("fire1cl.png");
        fireEndR.setImage("fire1cr.png");
        fireEndU.setImage("fire1cu.png");
        fireEndD.setImage("fire1cd.png");
        if(frame == 180){
            getWorld().addObject(fireCore, getX(), getY());
            for(int count = 1; count <= fireRadius; count++){
                if(continueD == true){
                    getWorld().addObject(fireBodyD, getX(), getY() + (32 * count));
                }
                if(continueU == true){
                    getWorld().addObject(fireBodyU, getX(), getY() - (32 * count));
                }
                if(continueR == true){
                    getWorld().addObject(fireBodyR, getX() + (32 * count), getY());
                }
                if(continueL == true){
                    getWorld().addObject(fireBodyL, getX() - (32 * count), getY());
                }
            }
            getWorld().addObject(fireEndD, getX(), getY() + ((32 * fireRadius) + 32));
            getWorld().addObject(fireEndU, getX(), getY() - ((32 * fireRadius) + 32));
            getWorld().addObject(fireEndR, getX() + ((32 * fireRadius) + 32), getY());
            getWorld().addObject(fireEndL, getX() - ((32 * fireRadius) + 32), getY());
        }
    }
}
