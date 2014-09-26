/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.histograma;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author f211
 */
public class Histograma extends JPanel implements Observer {
    private static Object frame;
    Conjunto c;
    public Histograma(Conjunto c) {
        this.c=c;
        this.setSize(new Dimension(600,600));
        this.setMinimumSize(new Dimension(600,600));
        this.setPreferredSize(new Dimension(600,600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        boolean control=false;
        int xi=10;
        int i=0;
        for(int y:c.getX()){
            if(i%2==0)
                g.setColor(Color.RED);
            else
                g.setColor(Color.GREEN);
            g.drawLine(xi,10, xi,10+y);
            xi+=5;
            i++;
            
            g.drawString(Arrays.toString(c.getX()), 50, 350);
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        this.updateUI();
    }
    
    
    
    
    public static void main(String[] args) {
        final JFrame f=new JFrame("Histograma");
        int intQuantity = 0;
        Object result = JOptionPane.showInputDialog(frame, "Ingrese la cantidad de números:");
        intQuantity =Integer.parseInt(result.toString());
        final Conjunto c=new Conjunto(intQuantity);
        Histograma h=new Histograma(c);
        c.addObserver(h);
        f.getContentPane().add(h);
        JPanel contentCenter = new JPanel(new GridBagLayout());
        JButton  button1 = new JButton ("Organizar por burbuja");
        JButton  button2 = new JButton ("Organizar por QuickSort");
        JButton  button3 = new JButton ("Organizar por ShowSort");
        
            
        System.out.println(intQuantity);
        f.add(contentCenter,BorderLayout.SOUTH);
        contentCenter.add(button1);
        contentCenter.add(button2);
        contentCenter.add(button3);
        f.pack();
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final Thread t=new Thread(new Runnable() {

            @Override
            public void run() {
                   f.setVisible(true);
            }
        });
        
        t.start();
        
        final Thread orden=new Thread(new Runnable() {

            @Override
            public void run() {
                   c.ordenarBurbuja();
//                   JTextField text;
//                text = new JTextField(Arrays.toString(c.getX()));
//                   f.add(text);
            }
        });
        
        final Thread ordenQuick=new Thread(new Runnable() {

            @Override
            public void run() {
                   c.ordenarQuickSort();
            }
        });
        
         final Thread ordenSort=new Thread(new Runnable() {

            @Override
            public void run() {
                   c.ordenamientoSort();
            }
        });
        
        //Listener Button1
        button1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
             orden.start();
         }          
        }); 
        
        //Listener Button2
        button2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
             ordenQuick.start();
         }          
        }); 
        
        
        //Listener Button3
        button3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
             ordenSort.start();
         }          
        }); 
        
        try {
            Thread.currentThread().join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Histograma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}