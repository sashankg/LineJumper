/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linejumper;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author Sashank
 */
public class LineJumper extends JFrame{
    LJView l;
    /**
     * @param args the command line arguments
     */
    public LineJumper()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setResizable(false);
        setTitle("Line Jumper");
        setLocationRelativeTo(null);
        setUndecorated(true);

        l = new LJView(new LJModel(), this);
        add(l);
        
        setVisible(true);        
        l.run(10);
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        new LineJumper();
    }
    
}
