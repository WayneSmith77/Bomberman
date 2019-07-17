
import greenfoot.*;

public class Player extends Actor
{
    //Angle determines the rotation of the player object.
    int angle;
    //Setting the amount of lives for the player.
    int lives = 3;
    int speed;
    int timer;
    int timeset;
    boolean bombTouching = false;
    int range;
    boolean contact = false;
    int bombs = 1;
    int bombset = 1;
    int frame = 0;
    
    private GreenfootImage down1 = new GreenfootImage("tile004.png");
    private GreenfootImage down2 = new GreenfootImage("tile003.png");
    private GreenfootImage down3 = new GreenfootImage("tile005.png");
    
    private GreenfootImage up1 = new GreenfootImage("tile018.png");
    private GreenfootImage up2 = new GreenfootImage("tile017.png");
    private GreenfootImage up3 = new GreenfootImage("tile019.png");
    
    private GreenfootImage left1 = new GreenfootImage("tile001.png");
    private GreenfootImage left2 = new GreenfootImage("tile000.png");
    private GreenfootImage left3 = new GreenfootImage("tile002.png");
    
    private GreenfootImage right1 = new GreenfootImage("tile015.png");
    private GreenfootImage right2 = new GreenfootImage("tile014.png");
    private GreenfootImage right3 = new GreenfootImage("tile016.png");
    
    private GreenfootImage dead1 = new GreenfootImage("tile028.png");
    private GreenfootImage dead2 = new GreenfootImage("tile029.png");
    private GreenfootImage dead3 = new GreenfootImage("tile030.png");
    private GreenfootImage dead4 = new GreenfootImage("tile031.png");
    private GreenfootImage dead5 = new GreenfootImage("tile032.png");
    private GreenfootImage dead6 = new GreenfootImage("tile033.png");
    private GreenfootImage dead7 = new GreenfootImage("tile034.png");
    
    
    public Player(int speed) {
        //Initialisation of variables.
        int angle = 0;
        this.speed = speed;
        int timer = 0;
        int range = 1;
        setImage(right1);
    }
    
    public void act()
    {
        move();
        loseLife();
        bombPlace();
        time();
        powerUp();
        animate();
    } 
    
    public void gameend() {
        frame = 0;
        turn(angle);
    }    
    
    public void animate() {
        if (lives != 0) {
            if (Greenfoot.isKeyDown("up")) {
                if (frame <= 10) {
                    setImage(up1);
                }
                else if (frame <= 20) {
                    setImage(up2);
                } 
                else if (frame <= 30) {
                    setImage(up1);
                } 
                else if (frame <= 40) {
                    setImage(up3);
                } 
                frame += 1;
            }         
            else if (Greenfoot.isKeyDown("down")) {
                if (frame <= 10) {
                    setImage(down1);
                }
                else if (frame <= 20) {
                    setImage(down2);
                } 
                else if (frame <= 30) {
                    setImage(down1);
                } 
                else if (frame <= 40) {
                    setImage(down3);
                } 
                frame += 1;
            }  
            else if (Greenfoot.isKeyDown("right")) {
                if (frame <= 10) {
                    setImage(right1);
                }
                else if (frame <= 20) {
                    setImage(right2);
                } 
                else if (frame <= 30) {
                    setImage(right1);
                } 
                else if (frame <= 40) {
                    setImage(right3);
                } 
                frame += 1;
            } 
            else if (Greenfoot.isKeyDown("left")) {
                if (frame <= 10) {
                    setImage(left1);
                }
                else if (frame <= 20) {
                    setImage(left2);
                } 
                else if (frame <= 30) {
                    setImage(left1);
                } 
                else if (frame <= 40) {
                    setImage(left3);
                } 
                frame += 1;
            } 
            if (frame == 40) {
                frame = 0;
            }    
        }  
    }    
    
    public void powerUp() {
        if (isTouching(Powerup.class)) {
            Powerup powerup = (Powerup) getOneIntersectingObject(Powerup.class);
            if (powerup.returnAttribute() == true) {
                bombset += 1;
            }    
            else {
                range += 2;
            }
        }    
    }    
        
    public void loseLife() {
        if (isTouching(Fire.class)) {
            if (lives != 0) {
                lives -=1; 
                if (lives == 0) {
                    gameend();
                }    
            }    
        }  
        if (lives == 0) {
            if (frame <= 3) {
                setImage(dead1);
            }
            else if (frame <= 6) {
                setImage(dead2);
            }
            else if (frame <= 9) {
                setImage(dead3);
            }    
            else if (frame <= 12) {
                setImage(dead4);
            }
            else if (frame <= 15) {
                setImage(dead5);
            }
            else if (frame <= 18) {
                setImage(dead6);
            }    
            else if (frame <= 21) {
                setImage(dead7);
            }    
            else {
                Greenfoot.stop();
            }    
            frame += 1;
        }    
    }

    public void move() {
        int remainderX = getX()%32-16;
        int remainderY = getY()%32-16;
        if (lives != 0) {
            if (Greenfoot.isKeyDown("up")) {
                turn(angle-90);
                angle = 90;
                move(speed);
                if (Math.abs(remainderX) < 10) {
                    setLocation(getX()-remainderX, getY());
                }
                if (contact() == true) {
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
                if (contact() == true) {
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
                if (contact() == true) {
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
                if (contact() == true) {
                    move(-speed);
                }
            } 
            contact = false;
        }
    }    
    
    public void bombPlace() {
        if (Greenfoot.isKeyDown("space")) {
           if(timer == 0) {
               int remainderX = getX()%32-16;
               int remainderY = getY()%32-16;
               getWorld().addObject(new Bomb(range), getX()-remainderX, getY()-remainderY);    
               bombTouching = true;
               bombs -= 1;
               if (bombs == 0) {
                   bombs = bombset;
                   timer = 50;
               }   
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
    
    public boolean contact() {
        if (isTouching(Brick.class) || isTouching(Block.class) || (isTouching(Bomb.class) && bombTouching == false)) {
            contact = true;
        }    
        return contact;
    }       
}