/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import javax.swing.JMenuBar;

/**
 *
 * @author gerson
 */
public class GameMenuBar extends JMenuBar {

    private GameMenu gameMenu = null;

    /*    
    public GameMenuBar(GameGUI gameGUI) {
        this.gameMenu = new GameMenu("Game",gameGUI); 
    }
    */
    
    public void addGameMenu(GameMenu gameMenu){
        this.gameMenu = gameMenu;
        this.add(gameMenu);
    }
    
    public GameMenu getGameMenu()
    {
        return this.gameMenu;
    }
}
