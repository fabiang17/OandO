/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.histograma;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lgutierrez
 */
public class ShellClass extends Observable implements ActionListenerCommand{
    public int x[];
    
    public void ShellClass(int y[])
    {
        this.x = y;
    }

    @Override
    public void Execute() {
         Boolean b=true;
        int increment = x.length / 2;
	while (increment > 0) {
		for (int i = increment; i < x.length; i++) {
			int j = i;
			int temp = x[i];
			while (j >= increment && x[j - increment] > temp) {
				x[j] = x[j - increment];
				j = j - increment;
                                this.setChanged();
                                this.notifyObservers(temp);
			}
			x[j] = temp;
                         
		}
		if (increment == 2) {
			increment = 1;
		} else {
			increment *= (5.0 / 11);
		}
	}
        
        synchronized(b){
                    try {
                        b.wait(5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }
    
    public int [] getX()
    {
        return this.x;
    }
}
