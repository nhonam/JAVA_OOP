/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametap;

import java.io.Serializable;

/**
 *
 * @author nhona
 */
public class Player implements Serializable{
    
    private String name;
    private String level;
    private int diem;

    public Player(String name, int diem, String level) {
        this.name = name;
        this.level = level;
        this.diem = diem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    @Override
    public String toString() {
        return  "name=" + name + ", level=" + level + ", diem=" + diem + '}';
    }
    
    
    
}
