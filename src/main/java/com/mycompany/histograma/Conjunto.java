/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.histograma;

import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author f211
 */
public class Conjunto extends Observable{
    int x[];

    public Conjunto(int intQuantity) {
        x=new int[intQuantity];
        Random r=new Random(System.currentTimeMillis());
        for (int i = 0; i < x.length; i++) {
            x[i]=r.nextInt(200);            
        }
    }
    
    public void ordenarBurbuja(){
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
    
    public void ordenarQuickSort(){
        Boolean b=true;
        int i = x[0], j = x[x.length];
        int tmp;
        int pivot = x[(x[0] + x[x.length]) / 2];
        while (i <= j) {
            while (x[i] < pivot)
                  i++;
            while (x[j] > pivot)
                  j--;
            if (i <= j) {
                  tmp = x[i];
                  x[i] = x[j];
                  x[j] = tmp;
                  this.setChanged();
                  this.notifyObservers(tmp);
                  i++;
                  j--;
            }
            synchronized(b){
                    try {
                        b.wait(5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        };
    }

    int[] getX() {
        return x;
    }
    
}
