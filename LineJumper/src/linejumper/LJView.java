/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linejumper;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Sashank
 */
public class LJView extends JPanel implements MouseListener{
    
    private LJModel model;
    private int x;
    private LJBlank[] blanks;
    private JFrame ref;
    private boolean ai;
    private int fSize;
    public LJView(LJModel m, JFrame j)
    {
        ref = j;
        model = m;
        x = 100;
        setBackground(new Color(52, 73, 94));    
        addMouseListener(this);
        blanks = new LJBlank[9];
        for (int i = 0; i < blanks.length; i++)
        {
             blanks[i] = new LJBlank(i, model);
        }
        fSize = 20;
    }
    public void run(int dt)
    {
        while (true) {
            Point m = MouseInfo.getPointerInfo().getLocation();
            if (!anyMoving() && !model.didWin()) {
                if (ai) {
                    ArrayList<String> s = new ArrayList<>();
                    for (char c : model.getPositions()) {
                        s.add(c + "");
                    }

                    AI a = new AI(s);
                    blanks[a.solution().remove(1)].clicked();
                }
                else if (Math.abs(4 - (((m.x - ref.getLocation().x) - 170) / 51)) <= 4) {
                    blanks[((m.x - ref.getLocation().x) - 170) / 51].hovering();
                    for (LJBlank l : blanks) {
                        if (l != blanks[((m.x - ref.getLocation().x) - 170) / 51]) {
                            l.notHovering();
                        }
                    }
                } else {
                    for (LJBlank l : blanks) {
                        l.notHovering();
                    }
                }
            }

            if (m.x - /*ref.getLocation().x*/0 > 650) {
                fSize = 25;
            } else {
                fSize = 20;
            }

            for (LJBlank b : blanks) {
                if (b.isSwitching()) {
                    b.updatePath();
                }
            }

            try {
                Thread.sleep(10);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            repaint();
        }
    }
    
    public void paint(Graphics g)
    {
        
        g.setColor(Color.WHITE);

        g.drawLine(10, 10, 40, 40);
        g.drawLine(40, 10, 10, 40);

        
        g.setFont(new Font("Futura", 20, fSize));
        g.drawString("Auto Run", 670-(fSize-20), 400-(fSize/2)+10);
        g.setFont(new Font("Futura", 4, 20));
        g.drawString("Move all the reds to the left, and all the blues to the right", 150, 600);
        g.drawString("You can only move a color that is at most 2 spots away from the blank", 90, 700);

        if (model.didWin())
        {
            g.drawString("GAME OVER", 330, 200);
        }
        
        for (int i = 0; i < 9; i++) {
            if (model.getPositions()[i] == 'T') {
                g.setColor(new Color(52, 152, 219));
            } else if (model.getPositions()[i] == 'F') {
                g.setColor(new Color(231, 76, 60));
            } else {
                g.setColor(new Color(44, 62, 80));
            }
            if (blanks[i].isSwitching()) {
                if (model.getBlank() <= i) {
                    g.fillArc(170 + (model.getBlank() * 51), 401 - ((i + 1) * 51 - model.getBlank() * 51) / 2, (i + 1) * 51 - model.getBlank() * 51, (i + 1) * 51 - model.getBlank() * 51, blanks[i].start, blanks[i].stop);
                    g.setColor(new Color(44, 62, 80));

                } else {
                    g.fillArc(170 + (i * 51), 401 - ((model.getBlank() + 1) * 51 - i * 51) / 2, (model.getBlank() + 1) * 51 - i * 51, (model.getBlank() + 1) * 51 - i * 51, blanks[i].start, blanks[i].stop);
                }
            }
        }
        
        
        for (LJBlank b : blanks) {
            b.paint(g);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point m = e.getLocationOnScreen();
        if(!anyMoving() && !ai)
        {
            if (Math.abs(4-(((m.x - ref.getLocation().x)-170)/51)) <= 4)
            {
                blanks[((m.x - ref.getLocation().x)-170)/51].clicked();
            }
        }
        
        if (m.x - ref.getLocation().x > 650)
        {
            ai = !ai;
        }
        
        if (m.x - ref.getLocation().x < 40 && m.y - ref.getLocation().y < 40) 
        {
            //ref.dispatchEvent(new WindowEvent(ref, WindowEvent.WINDOW_CLOSING));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    private boolean anyMoving(){
        for (LJBlank b : blanks)
        {
            if (b.isSwitching())
            {
                return true;
            }
        }
        return false;
    }
}
