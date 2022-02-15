/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametap;

import static gametap.Jpanel_game.bg;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author nhona
 */
public class Ran implements Runnable{
    
    private int x [];
    private int y [] ;
    private int leng=3;
    Thread thread;
    public static int GO_UP=1;
    public static int GO_DOWN=-1;
    public static int GO_LEFT=2;
    public static int GO_RIGHT=-2;
    
    int direc = Ran.GO_DOWN; // hướng di chuyển của rắn
    
    public void setDirec(int di){
        if(this.direc != -di) // để con rắn không di chuyển hướng ngược lại
            this.direc = di;
    }
    
    public Ran(){
        x = new int[20];
        y= new int[20];
        x[0]=5;
        y[0]=4;
        x[1]=5;
        y[1]=3;
        x[2]=5;
        y[2]=2;
      
    }
    
    public void reset(){
        x = new int[20];
        y= new int[20];
       for(int i=0;i<20;i++){
             for(int j=0;j<20;j++)
             {
                 bg[i][j]=0;
             }
         }
        x[0]=5;
        y[0]=4;
        x[1]=5;
        y[1]=3;
        x[2]=5;
        y[2]=2;
        leng=3;
         this.direc=Ran.GO_DOWN;
        Jpanel_game.diem =0;
    }
    

       //check xem moi co bi trung vao than ran hoặc trùng vào vật cản không
    public boolean checkToaDo(int x1, int y1){
        for(int i=0;i< leng;i++){
            if(x[i] == x1 && y[i] == y1) return true;
        }
        
        for(int i=0;i<20;i++)
            for(int j=0;j<20;j++){
                if(Jpanel_game.bg[x1][y1] ==3)
                  return true;
            }
        return false;
    }
    
    
    public Point ToaDoMoi(){
        Random r = new Random();
        int x,y;
        do{
            x = r.nextInt(19);
            y= r.nextInt(19);
        }while(checkToaDo(x, y));
        
        return new Point(x,y);
        
    }
    
    void update(int speed){

        Data.HeadGoup.update();
        Data.HeadGoDown.update();
        Data.HeadGoLeft.update();
        Data.HeadGoRight.update();
           
        //rắn ăn mồi
        if(Jpanel_game.bg[x[0]][y[0]] == 2 ){
           Jpanel_game.diem =  Jpanel_game.diem + 10; 
           leng++;
           Jpanel_game.bg[x[0]][y[0]] = 0;
           //random mồi tiếp theo
           Jpanel_game.bg[ToaDoMoi().x][ToaDoMoi().y]=2;
            
        }
        //trúng chứng ngại vật hoặc cắn trúng thân sẽ thua
        for(int i=1;i<leng;i++){//
            if(x[0]==x[i] && y[0]==y[i]  ){
                Jpanel_game.isPlaying = false;
                Jpanel_game.isOver = true; 
                String name = JOptionPane.showInputDialog("NHẬP VÀO TÊN CỦA BẠN!!!");
                JFrame_main.player_list.add(new Player(name, Jpanel_game.diem, Jpanel_game.level)); 
                reset();
              
            
             }
         }
        
        //thân rắn chạy theo đầu
        for(int i = leng -1 ; i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        
        // đầu rắn rẽ hướng
        if(direc == Ran.GO_DOWN) y[0]++;
        if(direc == Ran.GO_UP) y[0]--;
        if(direc == Ran.GO_LEFT) x[0]--;
        if(direc == Ran.GO_RIGHT) x[0]++;
        
        //chay qua hướng đối diện
         if(x[0] < 0) x[0]=19;
         if(x[0]>19) x[0]=0;
         if(y[0] < 0) y[0]=19;
         if(y[0]>19) y[0]=0;
            
        try {
            thread.sleep(speed);
        } catch (InterruptedException ex) {
           
        }
    }
    
    public void veRan(Graphics g){
        g.setColor(Color.red);
        for(int i=1;i<leng;i++){
            g.drawImage(Data.imgBody, x[i]*20-5, y[i]*20-5, null); // vẽ thân
       
         // hiệu ứng của đầu rắn
        if(this.direc == Ran.GO_UP) g.drawImage(Data.HeadGoup.getCurrentImage(), x[0]*20-3, y[0]*20-6, null);
        else if(this.direc == Ran.GO_DOWN) g.drawImage(Data.HeadGoDown.getCurrentImage(), x[0]*20-3, y[0]*20-6, null);
        else if(this.direc == Ran.GO_LEFT) g.drawImage(Data.HeadGoLeft.getCurrentImage(), x[0]*20-6, y[0]*20-3 , null);
        else if(this.direc == Ran.GO_RIGHT) g.drawImage(Data.HeadGoRight.getCurrentImage(), x[0]*20-6, y[0]*20-3, null);
        }
    }
    

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
