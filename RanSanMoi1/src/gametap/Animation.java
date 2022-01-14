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
//    public Image[] image;
//    public int n; // so luong hinh
    
    ArrayList images; 
    public int currentImage;
    
    
    public Animation(){
       images= new ArrayList<Image>();
        currentImage=0;
    }
    
    public void addImage(Image img){
//        Image[] ar = image;
//        image = new Image[n+1];
//        
//        for(int i=0;i<n;i++) image[i]=ar[i];
//        image[n]=img;
//        n++;
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
