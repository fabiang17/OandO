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
public class SortClass extends Observable implements ActionListenerCommand{
    
    public int x[];
    
    public void SortClass(int y[])
    {
        this.x = y;
    }
   
    @Override
    public void Execute() {
        quick_srt(x,0,x.length-1);
    }
    
    public  void quick_srt(int array[],int low, int n){
      Boolean b=true;
      int lo = low;
      int hi = n;
      if (lo >= n) {
          return;
      }
      int mid = array[(lo + hi) / 2];
      while (lo < hi) {
          while (lo<hi && array[lo] < mid) {
              lo++;
          }
          while (lo<hi && array[hi] > mid) {
              hi--;
          }
          if (lo < hi) {
              int T = array[lo];
              array[lo] = array[hi];
              array[hi] = T;
              this.setChanged();
              this.notifyObservers(T);
          }
          synchronized(b){
                    try {
                        b.wait(5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
      }
      if (hi < lo) {
          int T = hi;
          hi = lo;
          lo = T;
          this.setChanged();
          this.notifyObservers(T);
      }
      synchronized(b){
                    try {
                        b.wait(5);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Conjunto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
      quick_srt(array, low, lo);
      quick_srt(array, lo == low ? lo+1 : lo, n);
   }
    
    public int [] getX()
    {
        return this.x;
    }
    
}
