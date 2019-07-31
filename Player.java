import greenfoot.*;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    //Setting the amount of lives for the player.
    private int lives = 3;
    //Setting the speed of the player.
    private int speed = 2;
    //Showing the player isn't touching a bomb which he has placed.
    private boolean bombTouching = false;
    //Showing the player isn't touching any objects.
    private boolean contact = false;
    //Setting the amount of bombs which can be placed at a time.
    private int bombset = 1;
    //Initialising the frame for animation purposes.
    private int frame = 1;
    //Setting the angle of the player.
    private int angle = 0;
    //Initialising the cooldown for placing another bomb.
    private int cooldown = 0;
    //Initialising the range of the bomb.
    public static int range;
    //Sound counter used to delay sound execution in movement.
    private int soundCounter = 0;
    //Initialising score count shown in bottom left of screen.
    public static int score;
    //Tracks whether there are enemies left in the world.
    private boolean enemiesLeft = true;
    
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
    
    //Initialising background music.
    private GreenfootSound theme = new GreenfootSound("theme.wav");
    //Ensures music is only played once on loop.
    private boolean play = true;
    
    public Player() {
        //Setting the player's starting image.
        setImage(right1);
        //Resetting range value.
        range = 0;
        //Resetting score value.
        score = 0;
    }    
    
    public void act()
    {
        //Plays background music once on loop, at the start of the game.
        if(play == true) {
            theme.playLoop();
            play = false;
        }
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
        //Win method for door.
        win();
    }
    
    private void move() {
        //Finding the amount of distance the player is from the centre of each 'grid square'.
        int remainderX = getX()%64-18;
        int remainderY = getY()%64-18;
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
                if (Math.abs(remainderX) < 10) {
                    //Setting the player to the centre of the grid square.
                    setLocation(getX()-remainderX, getY());
                }
                //Checking whether the player is contacting a solid object.
                if (contact() == true) {
                    //Moving the player back.
                    move(-speed);
                }
                //Movement sounds play after each delay.
                soundCounter("vertical.wav");
            }    
            //Repeating for the downwards movement.
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
                soundCounter("vertical.wav");
            }   
            //Repeating for the rightward movement.
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
                soundCounter("horizontal.wav");
            } 
            //Repeating for the leftward movement.
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
                soundCounter("horizontal.wav");
            } 
            //Resetting the contact variable.
            contact = false;
        }
    }    
    
    private void soundCounter(String sound)
    {
        if (soundCounter == 0) {
            Greenfoot.playSound(sound);
        }
        else if (soundCounter == 16) {
            soundCounter = -1;
        }
        soundCounter++;
    }
    
    private void bombPlace() {
        //Checking whether the player has decided to place a bomb.
        if (Greenfoot.isKeyDown("space")) {
            //Checking whether a bomb has already been placed here.
            if(getOneObjectAtOffset(0, 0, Bomb.class) == null) {
                //If the player is currently alive.
                if(frame > 0) {
                    //Checking whether enough time has passed since the last bomb was placed.
                    if(cooldown == 0 && getWorld().getObjects(Bomb.class).size() < bombset) {
                        //Checking how far the player is from the centre of the grid square.
                        int remainderX = getX()%32-16;
                        int remainderY = getY()%32-16;
                        //Placing the bomb in the centre of the grid square.
                        getWorld().addObject(new Bomb(), getX()-remainderX, getY()-remainderY);
                        //Playing sound for bomb placement.
                        Greenfoot.playSound("bomb.wav");
                        //Storing that the player is touching a bomb (and still needs to move)
                        bombTouching = true;
                        //Setting the cooldown period.
                        cooldown = 15;
                    }
                }
            }
        }    
    }
    
    private void bombCountdown() {
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
    
    private boolean contact() {
        //Checking whether he is touching any objects (and hasn't just placed a bomb).
        if (isTouching(Brick.class) || isTouching(Block.class) || (isTouching(Bomb.class) && bombTouching == false)) {
            contact = true;
        }    
        //Returning this fact as a boolean for the movement method.
        return contact;
    }       
   
    private void animate() {
        //Checking the player is currently alive.
        if (frame > 0) {
            //Checking whether the player is moving up.
            if (Greenfoot.isKeyDown("up")) {
                //Checking which stage of the movement animation to set.
                if (frame <= 5) {
                    setImage(up1);
                }
                else if (frame <= 10) {
                    setImage(up2);
                } 
                else if (frame <= 15) {
                    setImage(up1);
                } 
                else if (frame <= 20) {
                    setImage(up3);
                } 
                //Increasing the frame while movement is in process.
                frame += 1;
            }  
            //Repeating this process for downwards movement.
            else if (Greenfoot.isKeyDown("down")) {
                if (frame <= 5) {
                    setImage(down1);
                }
                else if (frame <= 10) {
                    setImage(down2);
                } 
                else if (frame <= 15) {
                    setImage(down1);
                } 
                else if (frame <= 20) {
                    setImage(down3);
                } 
                frame += 1;
            }  
            //Repeating this process for rightwards movement.
            else if (Greenfoot.isKeyDown("right")) {
                if (frame <= 5) {
                    setImage(right1);
                }
                else if (frame <= 10) {
                    setImage(right2);
                } 
                else if (frame <= 15) {
                    setImage(right1);
                } 
                else if (frame <= 20) {
                    setImage(right3);
                } 
                frame += 1;
            } 
            //Repeating this process for leftwards movement.
            else if (Greenfoot.isKeyDown("left")) {
                if (frame <= 5) {
                    setImage(left1);
                }
                else if (frame <= 10) {
                    setImage(left2);
                } 
                else if (frame <= 15) {
                    setImage(left1);
                } 
                else if (frame <= 20) {
                    setImage(left3);
                } 
                frame += 1;
            } 
            //Checking whether the animation sequence has reached its end.
            if (frame == 20) {
                //Resetting the animation sequence.
                frame = 1;
            }    
        }
        //Checking whether the death sequence is in process.
        if (frame <= 0) {
            //Progressing the death sequence.
            frame += 1;
            //Displaying images depending on how far through the death animation is.
            if (frame <= -60) {
                setImage(dead1);
            }
            else if (frame <= -50) {
                setImage(dead2);
            }
            else if (frame <= -40) {
                setImage(dead3);
            }    
            else if (frame <= -30) {
                setImage(dead4);
            }
            else if (frame <= -20) {
                setImage(dead5);
            }
            else if (frame <= -10) {
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
    
    private void powerUp() {
        //Checking whether the player is in contact with a power-up.
        if (isTouching(Powerup.class)) {
            //Playing powerup sound.
            Greenfoot.playSound("power.wav");
            //Storing the contacted power-up as an object.
            Powerup powerup = (Powerup) getOneIntersectingObject(Powerup.class);
            //Getting the attribute of the power-up.
            if (powerup.returnAttribute() == true) {
                //Increasing the amount of bombs able to be placed.
                bombset += 1;
            }    
            else if (powerup.returnAttribute() == false) {
                //Increasing the range of bombs placed.
                range += 1;
            }
            //Adding 50 to score display.
            score += 50;
            getWorld().showText("Score: " + score, 930, 436);
        }    
    }    
        
    private void loseLife() {
        //Checking whether the player is touching an enemy.
        if (isTouching(Fire.class) || isTouching(Enemy.class)) {
            //Checking whether the player is alive.
            if (frame > 0) {
                //Removing a life.
                lives -=1;
                //Playing death sound.
                Greenfoot.playSound("death.wav");
                //Updating life counter.
                getWorld().showText("Lives: " + lives, 60, 436);
                //Setting in process the death animation sequence.
                frame = -70;
                //Resetting the player orientation.
                turn(angle);
                angle = 0;
            }    
        }
        //Checking whether the player has lost all their lives.
        if (lives == 0 && frame > 0) {
            gameOver();
        }
    }
    
    private void win()
    {
        //Checking for enemies left.
        enemyCheck();
        //Checking if the player is currently standing directly on the door.
        if(getX() == Door.doorX && getY() == Door.doorY && enemiesLeft == false){
            //Displaying congratulations
            getWorld().showText("You Win!", 496, 436);
            //Play win music and stops background music.
            theme.stop();
            Greenfoot.playSound("win.mp3");
            //Ending the game.
            Greenfoot.stop();
        }
    }
    
    private void gameOver()
    {
        //Displaying Game Over.
        getWorld().showText("Game Over!", 496, 436);
        //Play game over music and stops background music.
        theme.stop();
        Greenfoot.playSound("gameover.mp3");
        //Ending the game.
        Greenfoot.stop();
    }
    
    private void enemyCheck()
    {
        //Checking if there are no enemy objects in the world.
        if(getWorld().getObjects(Enemy.class).isEmpty() && enemiesLeft == true){
            enemiesLeft = false;
            Greenfoot.playSound("clear.wav");
        }
    }
}    
    