/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crashthejet.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Filippo-TheAppExpert
 */
public class Tank extends Sprite {

    private final Image image;

    public Tank(int x, int y, int speed) {
        super(x, y, speed);
        this.image = new ImageIcon(getClass().getResource("/crashthejet/game/asset/images/tank.png")).getImage();
    }

    @Override
    protected void draw(Graphics2D g2D) {
        g2D.drawImage(this.image, getX(), getY(), null);
    }

    public void shoot() {

    }

    public void moveLeft() {
        if (getX() < 0) {
            return;
        }
        incSpeed();
        setX(getX() - getSpeed());

        System.out.println("moveLeft");
    }

    public void moveRight() {
        if (getX() > GAME__WIDTH - 45) {
            return;
        }
        incSpeed();

        setX(getX() + getSpeed());
    }

    private void incSpeed() {
        if (getSpeed() < AUTO_SPEED) {
            setSpeed(getSpeed() + 1);
        }
    }

    void resetSpeed() {
        setSpeed(0);
    }

    public Rectangle getBound() {
        return new Rectangle(getX(), getY(), this.image.getWidth(null), this.image.getHeight(null));
    }
}
