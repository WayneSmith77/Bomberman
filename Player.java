
import greenfoot.*;

public class Player extends Actor
{
    //Setting the amount of lives for the player.
    int lives = 3;
    //Setting the speed of the player.
    int speed = 4;
    //Showing the player isn't touching a bomb which he has placed.
    boolean bombTouching = false;
    //Showing the player isn't touching any objects.
    boolean contact = false;
    //Setting the amount of bombs which are yet to be placed.
    int bombs = 1;
    //Setting the amount of bombs which can be placed at a time.
    int bombset = 1;
    //Inisialising the frame for animation purposes.
    int frame = 1;
    //Setting the angle of the player.
    int angle = 0;
    //Initialising the cooldown for placing another bomb.
    int cooldown = 0;
    //Setting the range of the bomb.
    int range = 1;
    
    //Setting downwards movement animation tiles.
    private GreenfootImage down1 = new GreenfootImage("tile004.png");
    private GreenfootImage down2 = new GreenfootImage("tile003.png");
    private GreenfootImage down3 = new GreenfootImage("tile005.png");
    
    //Setting upwards movement animation tiles.
    private GreenfootImage up1 = new GreenfootImage("tile018.png");
    private GreenfootImage up2 = new GreenfootImage("tile017.png");
    private GreenfootImage up3 = new GreenfootImage("tile019.png");
    
    //Setting leftward movement animation tiles.
    private GreenfootImage left1 = new GreenfootImage("tile001.png");
    private GreenfootImage left2 = new GreenfootImage("tile000.png");
    private GreenfootImage left3 = new GreenfootImage("tile002.png");
    
    //Setting rightward movement animation tiles.
    private GreenfootImage right1 = new GreenfootImage("tile015.png");
    private GreenfootImage right2 = new GreenfootImage("tile014.png");
    private GreenfootImage right3 = new GreenfootImage("tile016.png");
    
    //Setting the death animation tiles.
    private GreenfootImage dead1 = new GreenfootImage("tile028.png");
    private GreenfootImage dead2 = new GreenfootImage("tile029.png");
    private GreenfootImage dead3 = new GreenfootImage("tile030.png");
    private GreenfootImage dead4 = new GreenfootImage("tile031.png");
    private GreenfootImage dead5 = new GreenfootImage("tile032.png");
    private GreenfootImage dead6 = new GreenfootImage("tile033.png");
    private GreenfootImage dead7 = new GreenfootImage("tile034.png");
    
    
    public Player() {
        //Setting the player's starting image.
        setImage(right1);
    }    
    
    public void act()
    {
        //The movement method for the player.
        move();
        //The life loss method for the player.
        loseLife();
        //The bomb placement method for the player.
        bombPlace();
        //The time checker on the bomb.
        bombCountdown();
        //The method which gives the player different abilities based on retrieved power-ups.
        powerUp();
        //The animation method.
        animate();
    }
    
    public void move() {
        //Finding the amount of distance the player is from the centre of each 'grid square'.
        int remainderX = getX()%32-16;
        int remainderY = getY()%32-16;
        //Checking whether the player is currently alive.
        if (frame > 0) {
            //Checking which key was pressed.
            if (Greenfoot.isKeyDown("up")) {
                //Turning the player.
                turn(angle-90);
                //Setting the new angle.
                angle = 90;
                //Moving the new player.
                move(speed);
                //Checking whether the player is close to the centre of one of the grid squares.
                if (Math.abs(remainderX) < 18) {
                    //Setting the player to the centre of the grid square.
                    setLocation(getX()-remainderX, getY());
                }
                //Checking whether the player is contacting a solid object.
                if (contact() == true) {
                    //Moving the player back.
                    move(-speed);
                }
            }    
            //Repeating for the downwards movement.
            else if (Greenfoot.isKeyDown("down")) {
                turn(angle-270);
                angle = 270;
                move(speed);
                if (Math.abs(remainderX) < 18) {
                    setLocation(getX()-remainderX, getY());
                }
                if (contact() == true) {
                    move(-speed);
                }
            }   
            //Repeating for the rightward movement.
            else if (Greenfoot.isKeyDown("right")) {
                turn(angle);
                angle = 0;
                move(speed);
                if (Math.abs(remainderY) < 18) {
                    setLocation(getX(), getY()-remainderY);
                }
                if (contact() == true) {
                    move(-speed);
                }
            } 
            //Repeating for the leftward movement.
            else if (Greenfoot.isKeyDown("left")) {
                turn(angle-180);
                angle = 180;
                move(speed);
                if (Math.abs(remainderY) < 18) {
                    setLocation(getX(), getY()-remainderY);
                }
                if (contact() == true) {
                    move(-speed);
                }
            } 
            //Resetting the contact variable.
            contact = false;
        }
    }    
    
    public void bombPlace() {
        //Checking whether the player has decided to place a bomb.
        if (Greenfoot.isKeyDown("space")) {
            //Checking whether a bomb has already been placed here.
            if(getOneObjectAtOffset(0, 0, Bomb.class) == null) {
                //If the player is currently alive.
                if (frame > 0) {
                    //Checking whether enough time has passed since the last bomb was placed.
                    if(cooldown == 0) {
                        //Checking how far the player is from the centre of the grid square.
                        int remainderX = getX()%32-16;
                        int remainderY = getY()%32-16;
                        //Placing the bomb in the centre of the grid square.
                        getWorld().addObject(new Bomb(range), getX()-remainderX, getY()-remainderY);    
                        //Storing that the player is touching a bomb (and still needs to move)
                        bombTouching = true;
                        //Removing the amount of bombs yet to be placed before cooldown starts.
                        bombs -= 1;
                        //Checking whether all bombs available have been placed.
                        if (bombs == 0) {
                            //Resetting the amount of bombs able to be placed in the time span.
                            bombs = bombset;
                            //Setting the cooldown period.
                            cooldown = 180;
                        }
                    }    
                }    
            }
        }    
    }
    
    public void bombCountdown() {
        //Checking whether the cooldown period is in process.
        if(cooldown != 0) {
            //Lowering the cooldown timer.
            cooldown -= 1;
        }
        //Checking whether the player is not touching a bomb (since placing one).
        if(!isTouching(Bomb.class)){
           //Resetting the variable determining whether the player is in a placed bomb.
           bombTouching = false;
        }
    }    
    
    public boolean contact() {
        //Checking whether he is touching any objects (and hasn't just placed a bomb).
        if (isTouching(Brick.class) || isTouching(Block.class) || (isTouching(Bomb.class) && bombTouching == false)) {
            contact = true;
        }    
        //Returning this fact as a boolean for the movement method.
        return contact;
    }       
   
    public void animate() {
        //Checking the player is currently alive.
        if (frame > 0) {
            //Checking whether the player is moving up.
            if (Greenfoot.isKeyDown("up")) {
                //Checking which stage of the movement animation to set.
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
                //Increasing the frame while movement is in process.
                frame += 1;
            }  
            //Repeating this process for downwards movement.
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
            //Repeating this process for rightwards movement.
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
            //Repeating this process for leftwards movement.
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
            //Checking whether the animation sequence has reached its end.
            if (frame == 40) {
                //Resetting the animation sequence.
                frame = 1;
            }    
        }  
    }    
    
    public void powerUp() {
        //Checking whether the player is in contact with a power-up.
        if (isTouching(Powerup.class)) {
            //Storing the contacted power-up as an object.
            Powerup powerup = (Powerup) getOneIntersectingObject(Powerup.class);
            //Getting the attribute of the power-up.
            if (powerup.returnAttribute() == true) {
                //Increasing the amount of bombs able to be placed.
                bombset += 1;
                bombs += 1;
            }    
            else {
                //Increasing the range of bombs placed.
                range += 2;
            }
        }    
    }    
        
    public void loseLife() {
        //Checking whether the player is touching an enemy.
        if (isTouching(Fire.class)) {
            //Checking whether the player is alive.
            if (frame > 0) {
                //Removing a life.
                lives -=1; 
                //Setting in process the death animation sequence.
                frame = -22;
                //Resetting the player orientation.
                turn(angle);
                angle = 0;
            }    
        }  
        //Checking whether the player has lost all their lives.
        if (lives == 0) {
            //Ending the game.
            Greenfoot.stop();
        }
        //Checking whether the death sequence is in process.
        if (frame <= 0) {
            //Progressing the death sequence.
            frame += 1;
            //Displaying images depending on how far through the death animation is.
            if (frame <= -19) {
                setImage(dead1);
            }
            else if (frame <= -16) {
                setImage(dead2);
            }
            else if (frame <= -13) {
                setImage(dead3);
            }    
            else if (frame <= -10) {
                setImage(dead4);
            }
            else if (frame <= -7) {
                setImage(dead5);
            }
            else if (frame <= -4) {
                setImage(dead6);
            }    
            else if (frame < 1) {
                setImage(dead7);
            }    
            //Checking whether the death sequence has ended.
            else {
                //Resetting the player location and image.
                setLocation(48, 48);
                setImage(right1);
            }    
        }    
    }
}    

    