/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crashthejet.game;

import static crashthejet.game.Helper.FPS;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Filippo-TheAppExpert
 */
public class Jet extends Sprite {

    private final Image image;

    public Jet(int x, int y, int speed) {
        super(x, y, speed);
        this.image = new ImageIcon(getClass().getResource("/crashthejet/game/asset/images/jet.png")).getImage();
    }

    @Override
    protected void draw(Graphics2D g2D) {
        g2D.drawImage(this.image, getX(), getY(), null);
    }

    public void update() {
        setX(getX() - getSpeed());
    }

    boolean dropDownBomb() {
        if (GAME__WIDTH % 150 == 0) {
            return true;
        }
        return false;
    }

    public Rectangle getBound() {
        return new Rectangle(getX(), getY(), this.image.getWidth(null), this.image.getHeight(null));
    }
}
