/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.histograma;

import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import org.codehaus.groovy.tools.shell.Command;

/**
 *
 * @author f212
 */
public class BubbleClass extends Observable,JButton implements ActionListenerCommand{
    public int x[];
    public void BubbleClass(int y[])
    {
        this.x = y;
    }
    
    @Override
    public void Execute() {
        Boolean b=true;
        for (int i = 0; i < x.length; i++) {
            for (int j = i+1; j < x.length; j++) {
                if(x[i]>x[j]){
                    int tmp=x[i];
                    x[i]=x[j];
                    x[j]=tmp;                    
                    this.setChanged();
                    this.notifyObservers(tmp);
                }
                synchronized(b){
                    try {
                        b.wait(5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    public int [] getX()
    {
        return this.x;
    }
}
