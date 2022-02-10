 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametap;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author nhona
 */
public class Animation {
    
    ArrayList images; 
    public int currentImage;
      
    public Animation(){
       images= new ArrayList<Image>();
        currentImage=0;
    }
    
    public void addImage(Image img){    
          images.add(img);
    }
    
    public void update(){
        currentImage++;
        if(currentImage >= images.size()) currentImage =0;
    }
    
    public Image getCurrentImage(){
        return (Image) images.get(currentImage);
    }
}
