
import greenfoot.*;
/**
 * This class defines a crab. Crabs live on the beach.
 */
public class Player extends Actor
{
    //Angle determines the rotation of the player object.
    int angle;
    //Setting the amount of lives for the player.
    int lives;
    int speed;
    int timer;
    int timeset;
    boolean bombTouching = false;
    int range;
    public Player(int speed) {
        //Initialisation of variables.
        int angle = 0;
        this.speed = speed;
        int lives = 3;
        int timer = 0;
        int range = 1;
    }
    
    public void act()
    {
        move();
        loseLife();
        bombPlace();
        time();
        powerUp();
    }        
    
    public void powerUp() {
        if (isTouching(Powerup.class)) {
            Powerup powerup = (Powerup) getOneIntersectingObject(Powerup.class);
            if (powerup.returnAttribute() == true) {
                speed += 5;
            }    
            else {
                range += 2;
            }
        }    
    }    
        
    public void loseLife() {
        if (isTouching(Fire.class)) {
            lives = lives - 1;
        }
        if (lives == 0) {
            Greenfoot.stop();
        }    
    }

    public void move() {
        int remainderX = getX()%32-16;
        int remainderY = getY()%32-16;
        if (Greenfoot.isKeyDown("up")) {
            turn(angle-90);
            angle = 90;
            move(speed);
            if (Math.abs(remainderX) < 10) {
                setLocation(getX()-remainderX, getY());
            }
            if (isTouching(Brick.class) || isTouching(Block.class) || (isTouching(Bomb.class) && bombTouching == false)) {
                move(-speed);
            }
        }    
        else if (Greenfoot.isKeyDown("down")) {
            turn(angle-270);
            angle = 270;
            move(speed);
            if (Math.abs(remainderX) < 10) {
                setLocation(getX()-remainderX, getY());
            }
            if (isTouching(Brick.class) || isTouching(Block.class) || (isTouching(Bomb.class) && bombTouching == false)) {
                move(-speed);
            }
        }   
        else if (Greenfoot.isKeyDown("right")) {
            turn(angle);
            angle = 0;
            move(speed);
            if (Math.abs(remainderY) < 10) {
                setLocation(getX(), getY()-remainderY);
            }
            if (isTouching(Brick.class) || isTouching(Block.class) || (isTouching(Bomb.class) && bombTouching == false)) {
                move(-speed);
            }
        } 
        else if (Greenfoot.isKeyDown("left")) {
            turn(angle-180);
            angle = 180;
            move(speed);
            if (Math.abs(remainderY) < 10) {
                setLocation(getX(), getY()-remainderY);
            }
            if (isTouching(Brick.class) || isTouching(Block.class) || (isTouching(Bomb.class) && bombTouching == false)) {
                move(-speed);
            }
        } 
    }
    
    public void bombPlace() {
        if (Greenfoot.isKeyDown("space")) {
           if(timer == 0) {
               int remainderX = getX()%32-16;
               int remainderY = getY()%32-16;
               getWorld().addObject(new Bomb(range), getX()-remainderX, getY()-remainderY);    
               bombTouching = true;
               timer = 50;
           }    
        }
    }
    
    public void time() {
        if(timer != 0) {
            timer -= 1;
        }
        if(!isTouching(Bomb.class)){
           bombTouching = false;
        }
    }    
}