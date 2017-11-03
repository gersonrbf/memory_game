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

public class NewGame implements ActionListener{

    private GameGUI ggui;
    
    public NewGame(GameGUI gameGUI) 
    {
        this.ggui=gameGUI;
    }    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {    
        /*
        DimesionSelectDialog dsd = new DimesionSelectDialog(ggui, true);
        dsd.setVisible(true);
        if(!dsd.getResult())
            return;
        
        GameLogic gameLogic = dsd.getGameLogic();
        */
        BoardGUI.setPause(true);
        GameLogic gameLogic = DimesionSelectDialog.getGameLogicFromDialog(ggui);
        if(gameLogic==null)
        {
            BoardGUI.setPause(false);            
            return;
        }
        ScoreBoard.loadScoreBoard(ggui, gameLogic.getBoard().getTupleDimension());
        ggui.newgame(gameLogic);
    }       
}