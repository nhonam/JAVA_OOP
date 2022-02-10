/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametap;

import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Thread.sleep;
import java.util.Collections;
import javax.swing.JPanel;


/**
 *
 * @author nhona
 */

//0 nền, 1 thân rắn,2 mồi, 3 vật cản
public class Jpanel_game extends JPanel implements Runnable{
    
    Ran ran; 
    private Thread thread; // tạo ra luồng để sử dụng sleep
    private int map ;
    private int speed;
    static int [][] bg = new int[20][20];
    static boolean isPlaying = true ;
    static boolean isOver = false;
    static String level;
    static int diem = 0;
    
    
  
     public Jpanel_game(){  
        
        thread = new Thread(this);
        ran = new Ran(); 
        Data.loadImg();
        Data.loadAllAnimation();
        //random con mồi đầu tiên
        bg[ran.ToaDoMoi().x][ran.ToaDoMoi().y]=2;
        thread.start();
    }  
     
    public void  resetBg(){
         for(int i=0;i<20;i++){
             for(int j=0;i<20;j++)
             {
                 bg[i][j]=0;
             }
         }
     }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
     
     
    public void setBg(int i, int j, int num){
        bg[i][j] = num;
    }
    public int getBg(int i, int j){
        return bg[i][j];
    }

    public int getMap(){
        return map;
    }    
    
    public int getSpeed(){
        return speed;
    }
     
    public void setMap(int map){
        this.map = map;
    }
    
    public void setSpeed(int speed){
        this.speed = speed;
    }
   
    
    public void run(){
       
        while(true){
            
            if(isPlaying){
                Data.Worm.update();// hiệu ứng mồi
                ran.update(speed); 
                repaint();
            }

            try {
                sleep(20);
            } catch (InterruptedException ex) {
            }
            
        }
    }
     public void paintBg1(Graphics g){
        g.drawImage(Data.bg,0,0, null);
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++){
                //mồi
                if(bg[i][j] == 2){
                    g.drawImage(Data.Worm.getCurrentImage(),i*20+1,j*20+1, null);
                }
                //vẽ vật cản
                
                if(bg[i][j]==3){
                    g.setColor(Color.black);
                    g.fillRect(i*20+1,j*20+1, 18, 18);
                    g.setColor(Color.yellow);
                }
                  
            }
    }
     
       public void paintBg2(Graphics g){
        g.drawImage(Data.bg,0,0, null);
        
        for(int i=5;i<=14;i++){
                    bg[0][i]=3;
                    bg[19][i]=3;
                    bg[i][0]=3;
                    bg[i][19]=3;
                }
        
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++){
                //mồi
                if(bg[i][j] == 2){
                    g.drawImage(Data.Worm.getCurrentImage(),i*20+1,j*20+1, null);
                }
                //vẽ vật cản
 
                if(bg[i][j]==3){
                    g.setColor(Color.yellow);
                    g.fillRect(i*20+1,j*20+1, 18, 18);
                  //  g.setColor(Color.yellow);
                }
                  
            }
    }
      
         public void paintBg3(Graphics g){
             
         for(int i=0;i<=19;i++){
                    bg[0][i]=3;
                    bg[19][i]=3;
                    bg[i][0]=3;
                    bg[i][19]=3;
                }
        g.drawImage(Data.bg,0,0, null);
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++){
                //mồi
                if(bg[i][j] == 2){
                    g.drawImage(Data.Worm.getCurrentImage(),i*20+1,j*20+1, null);
                }
                //vẽ vật cản
              
                if(bg[i][j]==3){
                    g.setColor(Color.BLUE);
                    g.fillRect(i*20+1,j*20+1, 18, 18);
                   // g.setColor(Color.yellow);
                }
                  
            }
    }
    
    public void paintBg(Graphics g){
       if(map == 1){
           paintBg1(g);
       }
        if(map == 2) {
            paintBg2(g);
        }
       if (map == 3){
           paintBg3(g);
       }
    }
    public void paintKhung(Graphics g){
        g.setColor(Color.ORANGE);
        g.drawRect(0, 0, 410, 410);
        g.drawRect(1, 1, 411, 411);
        g.drawRect(0, 0, 650, 410);
        g.drawRect(1, 1, 651, 411);
        
    }
    
    public void ListPlayer(Graphics g){
         g.setColor(Color.white);
         Collections.sort(JFrame_main.player_list, (Player o1, Player o2) -> {
             // TODO Auto-generated method stub
             if(o1.getDiem() < o2.getDiem()) return -1;
             return 1;
         });
         g.setFont(g.getFont().deriveFont(15.0f));
         int len = JFrame_main.player_list.size();
        for(int i=0;i< len;i++){
            if(JFrame_main.player_list.get(i).getLevel().equalsIgnoreCase(level))
            {
                g.drawString(JFrame_main.player_list.get(i).toString(), 420, 150+i*10);
            }
        }
        
    }
    public void paint(Graphics g){
        paintBg(g);
        ran.veRan(g);
        paintKhung(g);
        if(!isPlaying){
                g.setColor(Color.white);
                g.setFont(g.getFont().deriveFont(18.0f));
                g.drawString("Ấn SPACE để tiếp tục chơi!", 80, 450);
            }
        if(isOver){
             g.setColor(Color.white);
             g.setFont(g.getFont().deriveFont(28.0f));
             g.drawString("BẠN ĐÃ THUA!", 50, 200);
        } 
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(410, 1, 240, 80);
         g.setColor(Color.red);
            g.setFont(g.getFont().deriveFont(28.0f));
            g.drawString("LEVEL: " + level , 450, 35);
            g.setColor(Color.MAGENTA);
            g.setFont(g.getFont().deriveFont(20.0f));
            g.drawString("SCORE: " + diem, 470, 65);
            
          ListPlayer(g);
    }
       
}
  

