/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crashthejet;

import crashthejet.game.GamePanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Filippo-TheAppExpert
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JFrame gameFrame = new JFrame("Crash The Jet");
                gameFrame.add(new GamePanel());
                gameFrame.setResizable(false);
                gameFrame.pack();
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setVisible(true);
            }
        });
    }
}
