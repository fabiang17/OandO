/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.histograma;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author f211
 */
public class Conjunto extends Observable{
    int x[];
    private ActionListenerCommand buCommand;
    
    public Conjunto(int intQuantity) {
        x=new int[intQuantity];
        Random r=new Random(System.currentTimeMillis());
        for (int i = 0; i < x.length; i++) {
            x[i]=r.nextInt(200);            
        }
    }
    
    public void setCommand(ActionListenerCommand command)
    {
        this.buCommand = command;
    }
    
    public void Ordenar()
    {
       buCommand.Execute();
       this.setChanged();
       this.notifyObservers(this.x);
    }
    
    int[] getX() {
        return x;
    }

    public void update(Observable o, Object arg) {
       this.setChanged();
       this.notifyObservers(this.x);
    }
}
