/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crashthejet.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Filippo-TheAppExpert
 */
public class GamePanel extends Canvas implements Runnable {

    private Thread gameThread;
    private final Sprite background = new Background(0, 0, 0);
    private final Tank tank = new Tank(GAME__WIDTH / 2 - 23, GAME__HEIGHT - 55, 0);
    private boolean isRunning;
    private final ArrayList<Bullet> bullets = new ArrayList<>();
    private final ArrayList<Jet> jets = new ArrayList<>();
    private final ArrayList<Bomb> bombs = new ArrayList<>();
    private final int jetPositions[];
    private int currentPosition;
    private int life = 3, score;

    public GamePanel() {
        this.jetPositions = new int[]{100, 180, 240, 320};
        setPreferredSize(new Dimension(GAME__WIDTH, GAME__HEIGHT));
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (gameThread == null) {
            gameThread = new Thread(GamePanel.this);
        }
        gameThread.start();
    }

    @Override
    protected void onKeyUp(KeyEvent event) {
        tank.resetSpeed();
    }

    @Override
    protected void onKeyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            bullets.add(new Bullet(tank.getX() + 17, tank.getY(), getRandomSpeed()));
            //tank.shoot();
        } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            tank.moveLeft();
        } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            tank.moveRight();
        } else {

        }
    }

    @Override
    protected void onDraw(Graphics2D g2D) {
        g2D.setColor(Color.red);
        background.draw(g2D);
        tank.draw(g2D);

        for (Jet jet : jets) {
            jet.draw(g2D);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g2D);
        }

        for (Bomb bomb : bombs) {
            bomb.draw(g2D);
        }

        g2D.setColor(Color.white);
        g2D.drawString("Life: " + life, 10, 30);
        g2D.drawString("Score: " + score, GAME__WIDTH - 50, 30);
    }

    @Override
    public void run() {
        init();
        while (isRunning) {

            long startTime = System.currentTimeMillis();

            updateGame();
            renderGame();

            long endTime = System.currentTimeMillis() - startTime;
            long waitTime = (MILLISECOND / FPS) - endTime / MILLISECOND;

            try {
                Thread.sleep(waitTime);
            } catch (Exception e) {
            }
        }
    }

    private void init() {
        isRunning = true;
    }

    private void updateGame() {

        //checkCollision();
        if (jets.size() < 5) {
            jets.add(new Jet(GAME__WIDTH, getJetYPosition(), getRandomSpeed()));
            currentPosition++;
        }

        for (int i = 0; i < jets.size(); i++) {
            Jet jet = jets.get(i);
            jet.update();

            for (int j = 0; j < bullets.size(); j++) {
                Bullet bullet = bullets.get(j);
                if (jet.getBound().intersects(bullet.getBound())) {
                    jets.remove(jet);
                    score++;
                }
            }
            if (jet.getX() == new Random().nextInt(500) || jet.getX() == new Random().nextInt(250)) {
                bombs.add(new Bomb(jet.getX() + 40, jet.getY() + 15, getRandomSpeed()));
            }

            if (jet.getX() < -90) {
                jets.remove(jet);
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();
            if (bullet.getY() < 0) {
                bullets.remove(bullet);
            }
        }

        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            bomb.update();

            if (tank.getBound().intersects(bomb.getBound())) {
                life--;
                if (life > 0) {
                    bombs.remove(bomb);
                } else if(life == 0) {
                    isRunning = false;
                }
            }

            if (bomb.getY() < 0) {
                bombs.remove(bomb);
            }
        }
    }

    private void renderGame() {
        repaint();
    }

    private int getRandomSpeed() {
        return new Random().nextInt(5) + 1;
    }

    private int getJetYPosition() {
        if (currentPosition >= jetPositions.length) {
            currentPosition = 0;
        }
        return jetPositions[currentPosition];
    }
}
