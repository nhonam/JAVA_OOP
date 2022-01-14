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
        public static int Y = 500; 
        
        public Jpanel_game game;
        public static ArrayList<Player> player = new ArrayList<>();;
    public JFrame_main(int map, int speed,String level){ 
        setSize(X,Y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       // player = 
        ReadData();
       
        game = new Jpanel_game();
        game.setMap(map);
        game.setSpeed(speed);
        game.setLevel(level);
        add(game);
        this.addKeyListener(new handler());
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                WriteData();
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
                    
                    game.ran. resetGame();
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
                        out = new FileOutputStream("C:\\Program Files\\NetBeans-12.4\\NetBeansProjects\\RanSanMoi1\\src\\img\\player.txt");
                        Obj = new ObjectOutputStream(out);
                        Obj.writeObject(player);
                       System.err.println("save to file sucessfull!");
                    } catch (IOException e) {
                        //TODO: handle exception
                        System.out.println("lôi ghi file");
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
                    in = new FileInputStream("C:\\Program Files\\NetBeans-12.4\\NetBeansProjects\\RanSanMoi1\\src\\img\\player.txt");
                    Objin = new ObjectInputStream(in);
                    player = (ArrayList<Player>) Objin.readObject();
                      System.err.println("read to file sucessfull!");
                } catch (Exception e) {
                    //TODO: handle exception
                    System.err.println("loi doc file");
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
