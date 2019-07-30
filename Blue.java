import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Blue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Blue extends Enemy
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
        int remainderX = getX()%64-48;
        int remainderY = getY()%64-48;
        
        if(checkObstacles(0, intersectNeg) == false){ // checking one spot left
            
            if (remainderX == 0 && remainderY == 0) {
                if (Greenfoot.getRandomNumber(100) < 30){
                    turn(angle-90);
                    angle = 90;

                }               
            }
        }
        else if(checkObstacles(0, intersectPos) == false){ // checking one spot left
            
            if (remainderX == 0 && remainderY == 0) {
                if (Greenfoot.getRandomNumber(100) < 35){
                    turn(angle-270);
                    angle = 270;
                    
                }               
            }
        }
        
        else if(checkObstacles(intersectNeg, 0) == false){ // checking one spot left
            
            if (remainderX == 0 && remainderY == 0) {
                if (Greenfoot.getRandomNumber(100) < 40){
                    turn(angle);
                    angle = 0;
                
                }               
            }
        }
        else if(checkObstacles(intersectPos, 0) == false){ // checking one spot left  
            if (remainderX == 0 && remainderY == 0) {
                if (Greenfoot.getRandomNumber(100) < 45){
                    turn(angle-180);
                    angle = 180;
                
                }               
            }
        }
        
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
            Player.score += 200;
            getWorld().showText("Score: " + Player.score, 930, 436);
            getWorld().removeObject(this);
        }
    }
}
