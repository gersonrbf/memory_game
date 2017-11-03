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
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author gerson
 */
public class JMenuBarCreator {

    private GameMenuBar gMenuBar = null;
    //private final JMenu gMenu;
    //private GameGUI gameGUI;
    //private JMenuItem newgame;
    //private JCheckBoxMenuItem pause;
    //private JMenuItem scoreboard;
    //private JMenuItem quit;
    
    /*
    public JMenuItem getNewGame()
    {
        return this.newgame;
    }
    */

    public JMenuBarCreator(GameGUI gui)
    {    
        GameMenu gm = new GameMenu("Game", gui);
        this.gMenuBar = new GameMenuBar();        
               
        //add to menubar
        this.gMenuBar.addGameMenu(gm);
        
    }

    public GameMenuBar getGameMenuBar() {
        return this.gMenuBar;
    }

/*    
    private class Quitting implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            BoardGUI.setPause(true);
            if(JOptionPane.showConfirmDialog(gMenu, "Are you sure that do you want to exit?", "Quit Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
                System.exit(0);
            BoardGUI.setPause(false);                        
        }
    
    }
    
    private class Pausing implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            BoardGUI.setPause(!BoardGUI.getPause());
        }
        
    }
    
    private class StartGame implements ActionListener{

        private GameGUI ggui;
        
        public StartGame(GameGUI gamegui) {
            this.ggui = gamegui;
        }        
        
        @Override
        public void actionPerformed(ActionEvent e) {
            ggui.fillFrame(ggui.getGameLogic());
        }
        
    }
 
    private class NG implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            gameGUI.newgame();
        }
        
    }
*/  
}
