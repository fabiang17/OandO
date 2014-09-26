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
    private ActionListenerCommand buCommand;
    
    public Conjunto(int intQuantity) {
        x=new int[intQuantity];
        Random r=new Random(System.currentTimeMillis());
        for (int i = 0; i < x.length; i++) {
            x[i]=r.nextInt(200);            
        }
  
    }
    
    public void ordenarBurbuja(){
        BubbleClass burbuja = new BubbleClass();
        buCommand.Execute();
    }
    
    public void ordenarQuickSort(){
        
      
    }
    
    

    public void ordenamientoSort()
    {
       
      
        
    }
    
    int[] getX() {
        return x;
    }
    
}
