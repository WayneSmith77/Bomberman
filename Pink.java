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
    
    int angle = 0;
    int speed = 1;
    boolean contact;
    int frame = 1;
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
        death();
    }    
    
    private void move()
    {
        if (contact() == true) {
               turn(180);
                angle = 180;
                move(speed);
                contact = false;
         }
        move(speed);
    }
    
    public boolean contact() {
        //Checking whether he is touching any objects.
        if (isTouching(Brick.class) || isTouching(Block.class) || (isTouching(Bomb.class))) {
            contact = true;
        }    
        return contact;
    }       
   
    public void death(){
        if(isTouching (Fire.class)){
            Player.score += 100;
            getWorld().showText("Score: " + Player.score, 930, 436);
            getWorld().removeObject(this);
        }
    } 
}
