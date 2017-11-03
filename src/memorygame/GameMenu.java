/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author gerson
 */
public class GameMenu extends JMenu {

    private JMenuItem newGameMenuItem = null;
    private JCheckBoxMenuItem pauseCheckBoxMenuItem = null;
    private JMenuItem scoreBoardMenuItem = null;
    private JMenuItem quitMenuItem = null;
    
    public GameMenu(String title, GameGUI gameGUI) {
        super(title);
        fillMenu(gameGUI);
    }
    
    private void fillMenu(GameGUI ggui)
    {        
        //new game
        this.newGameMenuItem = new JMenuItem("New Game");
        this.newGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        this.newGameMenuItem.addActionListener(new StartingGame(ggui));
        this.add(this.newGameMenuItem);        
        
        //pause
        this.pauseCheckBoxMenuItem = new JCheckBoxMenuItem("Pause",false);
        this.pauseCheckBoxMenuItem.setAccelerator(KeyStroke.getKeyStroke("PAUSE"));
        this.pauseCheckBoxMenuItem.addActionListener(new Pausing());
        this.add(this.pauseCheckBoxMenuItem);
        
        //scoreboard
        this.scoreBoardMenuItem = new JMenuItem("ScoreBoard");
        this.scoreBoardMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
        this.scoreBoardMenuItem.addActionListener(new OpenScoreBoard(ggui));
        this.add(this.scoreBoardMenuItem);
        
        //quit
        this.quitMenuItem = new JMenuItem("Quit");
        this.quitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        this.quitMenuItem.addActionListener(new Quitting(ggui));
        this.add(this.quitMenuItem);

    }
    
    public JMenuItem getNewGameMenuItem()
    {
        return this.newGameMenuItem;
    }
}
