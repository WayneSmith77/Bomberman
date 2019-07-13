import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
        
/**
 * Write a description of class Fire here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fire extends Actor
{
    public int fireRadius = 1;
    private int fireRadiusU = fireRadius;
    private int fireRadiusL = fireRadius;
    private int fireRadiusR = fireRadius;
    private int fireRadiusD = fireRadius;
    private int frame = 0; // Purpose of frames is mainly for the animation (if I can figure that one out later)
    private int firePosX;
    private int firePosY;
    private int intersectX;
    private int intersectY;
    private boolean stopLoopU = false;
    private boolean stopLoopL = false;
    private boolean stopLoopR = false;
    private boolean stopLoopD = false;
    private boolean fireEndU = true;
    private boolean fireEndL = true;
    private boolean fireEndR = true;
    private boolean fireEndD = true;
    
    /**
     * Act - do whatever the Fire wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //explode();
        //lmao();
        pls();
        // Greenfoot.stop();
    }
    
    public void pls()
    {
        Fire fireBL = new Fire();
        Fire fireBR = new Fire();
        Fire fireBU = new Fire();
        Fire fireBD = new Fire();
        Fire fireCL = new Fire();
        Fire fireCR = new Fire();
        Fire fireCU = new Fire();
        Fire fireCD = new Fire();
        fireBL.setImage("fire1bh.png");
        fireBR.setImage("fire1bh.png");
        fireBU.setImage("fire1bv.png");
        fireBD.setImage("fire1bv.png");
        fireCL.setImage("fire1cl.png");
        fireCR.setImage("fire1cr.png");
        fireCU.setImage("fire1cu.png");
        fireCD.setImage("fire1cd.png");
        if(frame == 2){
            for(int count = 1; count <= fireRadius; count++){
                if(stopLoopD == false){
                    getWorld().addObject(fireBD, getX(), getY() + (32 * count));
                }
                if(stopLoopU == false){
                    getWorld().addObject(fireBU, getX(), getY() - (32 * count));
                }
                if(stopLoopR == false){
                    getWorld().addObject(fireBR, getX() + (32 * count), getY());
                }
                if(stopLoopL == false){
                    getWorld().addObject(fireBL, getX() - (32 * count), getY());
                }
            }
            getWorld().addObject(fireCD, getX(), getY() + ((32 * fireRadius) + 32));
            getWorld().addObject(fireCU, getX(), getY() - ((32 * fireRadius) + 32));
            getWorld().addObject(fireCR, getX() + ((32 * fireRadius) + 32), getY());
            getWorld().addObject(fireCL, getX() - ((32 * fireRadius) + 32), getY());
        }
        frame++;
    }
    
    public void explode() // A variant of pls(), also has the same problem + isTouching(Brick.class) method doesn't work for some reason
    {
        if(frame == 0){
            firePosX = getX();
            firePosY = getY();
            while(fireRadiusD <= fireRadius && stopLoopD == false){
                System.out.println(fireRadiusD);
                Fire fireBD = new Fire();
                fireBD.setImage("fire1bv.png");
                getWorld().addObject(fireBD, firePosX, firePosY + (32 * fireRadiusD));
                if(isTouching(Brick.class)){
                    removeTouching(Brick.class);
                    getWorld().removeObject(fireBD);
                    stopLoopD = true;
                    fireRadiusD = fireRadius + 1;
                    System.out.println(fireRadiusD);
                }
                fireRadiusD++;
                System.out.println(fireRadiusD);
            }
            for(int count = 1; count <= fireRadius; count++){
                Fire fireBU = new Fire();
                fireBU.setImage("fire1bv.png");
                getWorld().addObject(fireBU, firePosX, firePosY - (32 * count));
                // if(isTouching(Brick.class)){
                    // getWorld().removeObject(this);
                    // stopLoopU = true;
                    // count = fireRadius + 1;
                // }
            }
            for(int count = 1; count <= fireRadius; count++){
                Fire fireBR = new Fire();
                fireBR.setImage("fire1bh.png");
                getWorld().addObject(fireBR, firePosX + (32 * count), firePosY);
                // if(isTouching(Brick.class)){
                    // getWorld().removeObject(this);
                    // stopLoopR = true;
                    // count = fireRadius + 1;
                // }
            }
            for(int count = 1; count <= fireRadius; count++){
                Fire fireBL = new Fire();
                fireBL.setImage("fire1bh.png");
                getWorld().addObject(fireBL, firePosX - (32 * count), firePosY);
                // if(isTouching(Brick.class)){
                    // getWorld().removeObject(this);
                    // stopLoopL = true;
                    // count = fireRadius + 1;
                // }
            }
            if(stopLoopD == false){
                Fire fireCD = new Fire();
                fireCD.setImage("fire1cd.png");
                getWorld().addObject(fireCD, firePosX, firePosY + ((32 * fireRadius) + 32));
            }   
            if(stopLoopU == false){
                Fire fireCU = new Fire();
                fireCU.setImage("fire1cu.png");
                getWorld().addObject(fireCU, firePosX, firePosY - ((32 * fireRadius) + 32));
            }
            if(stopLoopR == false){
                Fire fireCR = new Fire();
                fireCR.setImage("fire1cr.png");
                getWorld().addObject(fireCR, firePosX + ((32 * fireRadius) + 32), firePosY);
            }
            if(stopLoopL == false){
                Fire fireCL = new Fire(); 
                fireCL.setImage("fire1cl.png");
                getWorld().addObject(fireCL, firePosX - ((32 * fireRadius) + 32), firePosY);
            }
        }
        frame++;
    }
        
    private void lmao(){ // Don't worry about this for now, it was used for testing purposes
        if(isTouching(Brick.class)){
            removeTouching(Brick.class);
            getWorld().removeObject(this);
        }
    }
}
