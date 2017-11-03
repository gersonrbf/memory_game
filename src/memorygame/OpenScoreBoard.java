/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author gerson
 */
public class OpenScoreBoard implements ActionListener {

    private GameGUI m_gameGUI;
    
    public OpenScoreBoard(GameGUI gameGUI) {
        m_gameGUI = gameGUI;
    }  

    @Override
    public void actionPerformed(ActionEvent e) {
        BoardGUI.setPause(true);
        //m_gameGUI.pack();
        ScoreBoard.getScoreBoardGUI().setLocationRelativeTo(m_gameGUI);
        ScoreBoard.getScoreBoardGUI().setVisible(true);        
        BoardGUI.setPause(false);
    }

    
}
