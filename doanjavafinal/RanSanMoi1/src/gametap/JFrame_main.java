/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametap;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author nhona
 */
public class JFrame_main extends JFrame{
        public static final int X = 700;      
        public static final int Y = 500; 
        
        public Jpanel_game game;
        public static ArrayList<Player> player_list ;
        public JFrame_main( int speed,String level){ 
        setSize(X,Y);
      
        ReadData();
       // player_list = new ArrayList<>();
        game = new Jpanel_game();
        game.setSpeed(speed);
        game.setLevel(level);
        add(game);
        this.addKeyListener(new handler());
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                WriteData();
                if(Jpanel_game.isPlaying == true && Jpanel_game.isOver == false){
//                    JOptionPane.showMessageDialog(game,"BẠN ĐÃ BỊ XỬ THUA!");
//                    String name = JOptionPane.showInputDialog("NHẬP VÀO TÊN CỦA BẠN!!!");
//                    JFrame_main.player_list.add(new Player(name, Jpanel_game.diem, Jpanel_game.level));
                    game.ran.reset();
                    Jpanel_game.isPlaying = false;
                    Jpanel_game.isOver = true;   
                }      
                game.ran.reset();
                Jpanel_game.isPlaying = false;
                Jpanel_game.isOver = true;   
               
            }
        
        });
        setVisible(true);
    }
    
    
     public class handler implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
             if(e.getKeyCode() == KeyEvent.VK_SPACE){
                   Jpanel_game.isPlaying = !Jpanel_game.isPlaying;
                if( Jpanel_game.isOver) {
                    Jpanel_game.isOver = false;      
                    game.ran. reset();
                    Jpanel_game.isPlaying = true;
                }
                
             }  
             if(e.getKeyCode() == KeyEvent.VK_UP)
                game.ran.setDirec(Ran.GO_UP);
            if(e.getKeyCode() == KeyEvent.VK_DOWN)
                game.ran.setDirec(Ran.GO_DOWN);
            if(e.getKeyCode() == KeyEvent.VK_LEFT)
                game.ran.setDirec(Ran.GO_LEFT);
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                 game.ran.setDirec(Ran.GO_RIGHT);
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
 }
     
     public static void WriteData() {
            FileOutputStream out = null;
            ObjectOutput Obj = null;
                    try {
                        out = new FileOutputStream("src\\img\\player.DAT");
                        Obj = new ObjectOutputStream(out);
                        Obj.writeObject(player_list);
                     
                    } catch (IOException e) {
                        //TODO: handle exception
                        
                    } finally {
                        if(out != null) {
                            try {
                                out.close();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        if(Obj != null) {
                            try {
                                Obj.close();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                    }
         
     }
     
     public static void ReadData(){
                FileInputStream in = null;
                ObjectInputStream Objin = null;
                try {
                    in = new FileInputStream("src\\img\\player.DAT");
                    Objin = new ObjectInputStream(in);
                    player_list = (ArrayList<Player>) Objin.readObject();
                     
                } catch (Exception e) {
                    //TODO: handle exception
                   
                } finally {
                    if(in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if(Objin != null) {
                        try {
                            Objin.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
     }
  
}
