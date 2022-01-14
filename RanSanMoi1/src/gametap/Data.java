/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametap;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author nhona
 */
public class Data {   
    
    public static Image bg;
    public static BufferedImage sprite;// buffer cho phep lấy các hình nhỏ trong 1 hình lớn
    public static Image imgHead;
    public static Image imgBody;
  
     public static Image imgHead_GoLeft;
    public static Image imgHead_GoRight;
    public static Image imgHead_GoUp;
    public static Image imgHead_GoDown;
    
     public static Image imgWorm;
    public static Image imgWorm2;
     public static Image imgWorm3;
    
    public static Animation HeadGoup;
    public static Animation HeadGoDown;
    public static Animation HeadGoRight;
    public static Animation HeadGoLeft;
    
     public static Animation Worm;
    
    
    
    public static void loadImg() {
        try {
            sprite  = ImageIO.read(new File("src/img/sprite1.png"));
            
            imgHead = sprite.getSubimage(2, 3, 30, 30);
             imgHead_GoLeft = sprite.getSubimage(75, 3, 30, 30);
            imgHead_GoRight = sprite.getSubimage(110, 3, 30, 30);
             imgHead_GoUp = sprite.getSubimage(145, 3, 30, 30);
             imgHead_GoDown = sprite.getSubimage(39, 3, 30, 30);
             
            imgBody = ImageIO.read(new File("src/img/thanran.png"));
            bg = ImageIO.read( new File("src\\img\\test.jpg"));
            
             imgWorm = sprite.getSubimage(1, 40, 25 , 25);
             imgWorm2 = sprite.getSubimage(32, 40, 25 , 25);
             imgWorm3 = sprite.getSubimage(63, 40, 25 , 25);
        } catch (IOException ex) {
        }
    }
    
    public static void loadAllAnimation(){
        HeadGoup = new Animation();
        HeadGoup.addImage(imgHead);
        HeadGoup.addImage(imgHead_GoUp);
        
        HeadGoDown = new Animation();
        HeadGoDown.addImage(imgHead);
        HeadGoDown.addImage(imgHead_GoDown);
        
        HeadGoLeft = new Animation();
        HeadGoLeft.addImage(imgHead);
        HeadGoLeft.addImage(imgHead_GoLeft);
        
        HeadGoRight = new Animation();
        HeadGoRight.addImage(imgHead);
        HeadGoRight.addImage(imgHead_GoRight);
        
        Worm = new Animation();
        Worm.addImage(imgWorm);
        Worm.addImage(imgWorm2);
        Worm.addImage(imgWorm3);
        Worm.addImage(imgWorm2);
    }
}
