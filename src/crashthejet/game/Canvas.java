/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crashthejet.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author Filippo-TheAppExpert
 */
public abstract class Canvas extends JPanel implements Helper, KeyListener {
    
    public Canvas() {
        this.addKeyListener(Canvas.this);
        setDoubleBuffered(true);
        setFocusable(true);
        requestFocus();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        onDraw(g2D);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        onKeyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        onKeyUp(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    protected abstract void onKeyUp(KeyEvent event);
    
    protected abstract void onKeyPressed(KeyEvent event);
    
    protected abstract void onDraw(Graphics2D g2D);
}
