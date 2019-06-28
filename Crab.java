
import greenfoot.*;

/**
 * This class defines a crab. Crabs live on the beach.
 */
public class Crab extends Actor
{
    //Angle determines the rotation of the player object.
    int angle;
    //Delay prevents the player from moving immediately across the screen.
    int delay;
    //Ends create barriers around the edges of the screen.
    int leftEnd;
    int rightEnd;
    int topEnd;
    int bottomEnd;
    //Victory stores the y value of the top row in the game.
    int victory;
    //Setting the amount of lives for the player.
    int lives;
    int speed;
    int timer;
    int timeset;
    boolean bombTouching = false;
    public Crab(int speed) {
        //Initialisation of variables.
        int angle = 0;
        this.speed = speed;
        int leftEnd = 25;
        int rightEnd = 295;
        int topEnd = 18;
        int bottomEnd = 558;
        int victory = 40;
        int lives = 3;
        int timer = 0;
    }
    
    public void act()
    {
        move();
        loseLife();
        bombPlace();
        check();
        time();
        tightFit();
    }        
        
    public void loseLife() {
        //if (isTouching(Lobster.class)) {
        //    Greenfoot.playSound("hi zammit.wav");
        //}
    }
    
    public void move() {
        if (Greenfoot.isKeyDown("up")) {
            turn(angle-90);
            angle = 90;
            move(speed);
            if (isTouching(Lobster.class) && bombTouching == false) {
                move(-speed);
            }
        }    
        if (Greenfoot.isKeyDown("down")) {
            turn(angle-270);
            angle = 270;
            move(speed);
            if (isTouching(Lobster.class) && bombTouching == false) {
                move(-speed);
            }
        }   
        if (Greenfoot.isKeyDown("right")) {
            turn(angle);
            angle = 0;
            move(speed);
            if (isTouching(Lobster.class) && bombTouching == false) {
                move(-speed);
            }
        } 
        if (Greenfoot.isKeyDown("left")) {
            turn(angle-180);
            angle = 180;
            move(speed);
            if (isTouching(Lobster.class) && bombTouching == false) {
                move(-speed);
            }
        } 
    }
    
    public void tightFit() {
        int remainderX = getX()%32-16;
        int remainderY = getY()%32-16;
        if(Math.abs(remainderY) < 5 && Greenfoot.isKeyDown("left")) {
            
        } 
    }
    //992, 416
    
    public void bombPlace() {
       if (Greenfoot.isKeyDown("space")) {
           if(timer == 0) {
               int remainderX = getX()%32-16;
               int remainderY = getY()%32-16;
               getWorld().addObject(new Lobster(), getX()-remainderX, getY()-remainderY);    
               bombTouching = true;
               timer = 50;
           }    
       }
    }
    
    public void time() {
        if(timer != 0) {
            timer -= 1;
        }    
    }    
    
    public void check() {
       if(!isTouching(Lobster.class)){
           bombTouching = false;
       }
    }   
}