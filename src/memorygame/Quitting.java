/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author gerson
 */
public class Quitting implements ActionListener{

    private GameGUI gameGUI = null;
    
    public Quitting(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {    
        BoardGUI.setPause(true);
        if(JOptionPane.showConfirmDialog(gameGUI, "Are you sure that do you want to exit?", "Quit Confirmation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)        
            gameGUI.dispatchEvent(new WindowEvent(gameGUI,WindowEvent.WINDOW_CLOSING));            
        BoardGUI.setPause(false);
    }
}
