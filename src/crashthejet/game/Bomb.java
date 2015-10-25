/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crashthejet.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Filippo-TheAppExpert
 */
public class Bomb extends Sprite {

    public Bomb(int x, int y, int speed) {
        super(x, y, speed);
    }

    @Override
    protected void draw(Graphics2D g2D) {
        g2D.setColor(Color.MAGENTA);
        g2D.fillOval(getX(), getY(), 15, 15);
    }

    public void update() {
        setY(getY() + getSpeed());
    }

    public Rectangle getBound() {
        return new Rectangle(getX(), getY(), 15, 15);
    }
}
