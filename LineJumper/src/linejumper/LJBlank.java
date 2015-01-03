/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linejumper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Sashank
 */
public class LJBlank extends Component{
    private int i;
    private int height;
    private LJModel model;
    public boolean switching;
    public int start;
    public int stop;
    public LJBlank(int xc, LJModel m)
    {
        model = m;
        i = xc;
        height = 5;
    }
    
    public void hovering()
    {
        if(height < 10 && model.getPositions()[i] != '_')
        {
            height++;
        }
        start = 0;
        stop = 0;
    }
    
    public void notHovering()
    {
        if(height > 5)
        {
            height--;
        }
    }
    
    public void clicked()
    {
        if(Math.abs(model.getBlank()-i) <= 2 && model.getBlank()-i !=0)
        {
            switching = true;
            if (model.getBlank() <= i)
            {
                start = 0;
            }
            else
            {
                start = 180;
                
            }
            height = 0;
        }
        
    }
    
    public boolean isSwitching(){return switching;}
    
    public void updatePath() {
        int rate = 6;
        if (model.getBlank() <= i) {
            if (stop < 180 && start == 0) {
                stop += rate;
            } else if (start < 180) {
                height = 5;
                start += rate;
                stop -= rate;
            } else {
                model.movePiece(i);
                switching = false;
            }

        } else {
            if (start > 0) {
                start -= rate;
                stop += rate;
            } else if (stop > 0) {
                height = 5;
                stop -= rate;
            } else {
                model.movePiece(i);
                switching = false;
            }
        }

    }
    
    public void paint(Graphics g) {
        if (model.getPositions()[i] == 'T') {
            g.setColor(new Color(52, 152, 219));
        } else if (model.getPositions()[i] == 'F') {
            g.setColor(new Color(231, 76, 60));
        } else {
            g.setColor(new Color(44, 62, 80));
        }
        g.fillRect(170 + (i * 51), 400 - (height - 2), 50, height);
    }
   
}
