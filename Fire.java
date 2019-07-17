import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo
import java.util.List;
        
/**
 * Write a description of class Fire here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fire extends Actor
{
    private int currentImg; // Used to refer to the index of the initial fire image.
    private int frame = 0; // Frames determine animation speed and when certain modules execute.
    public static GreenfootImage[] images = { // Static array of all images, used in fire animation and to set initial images in Bomb class.
                                        new GreenfootImage("fire1a.png"),
                                        new GreenfootImage("fire2a.png"),
                                        new GreenfootImage("fire3a.png"),
                                        new GreenfootImage("fire4a.png"),
                                        new GreenfootImage("fire1bh.png"),
                                        new GreenfootImage("fire2bh.png"),
                                        new GreenfootImage("fire3bh.png"),
                                        new GreenfootImage("fire4bh.png"),
                                        new GreenfootImage("fire1bv.png"),
                                        new GreenfootImage("fire2bv.png"),
                                        new GreenfootImage("fire3bv.png"),
                                        new GreenfootImage("fire4bv.png"),
                                        new GreenfootImage("fire1cu.png"),
                                        new GreenfootImage("fire2cu.png"),
                                        new GreenfootImage("fire3cu.png"),
                                        new GreenfootImage("fire4cu.png"),
                                        new GreenfootImage("fire1cd.png"),
                                        new GreenfootImage("fire2cd.png"),
                                        new GreenfootImage("fire3cd.png"),
                                        new GreenfootImage("fire4cd.png"),
                                        new GreenfootImage("fire1cr.png"),
                                        new GreenfootImage("fire2cr.png"),
                                        new GreenfootImage("fire3cr.png"),
                                        new GreenfootImage("fire4cr.png"),
                                        new GreenfootImage("fire1cl.png"),
                                        new GreenfootImage("fire2cl.png"),
                                        new GreenfootImage("fire3cl.png"),
                                        new GreenfootImage("fire4cl.png"),
                                    };
                                    
    /**
     * Act - do whatever the Fire wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        fireEnd();
        arraySearch();
        animation();
    }
        
    /**
     * Removes the end pieces of fire if it is overlapping with other bricks or bombs.
     */
    private void fireEnd()
    {
        if (getWorld() != null){
            for(Object obj : getIntersectingObjects(Bomb.class)){
                if(obj instanceof Bomb && getImage() != images[0] && getImage() != images[1] && getImage() != images[2] && getImage() != images[3]){
                    getWorld().removeObject(this);
                }
            }
        }
        if(isTouching(Brick.class)){
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Searches the 'images' array to find the current image's index (currentImg) to be used in 'animation' module.
     */
    private void arraySearch()
    {
        if(frame == 0){
            for(int count = 0; count < 28; count++){
                if(images[count] == getImage()){
                    currentImg = count;
                }
            }
        }
    }
    
    /**
     * Animation of fire using the ordered 'images' array, and removes fire afterwards. 
     */
    private void animation()
    {
        if(frame == 4){
            setImage(images[currentImg + 1]);
        }
        else if(frame == 8){
            setImage(images[currentImg + 2]);
        }
        else if(frame == 12){
            setImage(images[currentImg + 3]);
        }
        else if(frame == 16){
            setImage(images[currentImg + 2]);
        }
        else if(frame == 20){
            setImage(images[currentImg + 1]);
        }
        else if(frame == 24){
            setImage(images[currentImg]);
        }
        else if(frame == 28){
            getWorld().removeObject(this);
        }
        frame++;
    }
}
