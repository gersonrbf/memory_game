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
public class StartingGame implements ActionListener{

    private GameGUI ggui = null;
        
    public StartingGame(GameGUI gamegui) {
        this.ggui = gamegui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
        DimesionSelectDialog dsd = new DimesionSelectDialog(ggui, true);
        dsd.setVisible(true);
        if(!dsd.getResult())
            return;
        GameLogic gameLogic = dsd.getGameLogic();
        */

        GameLogic gameLogic = DimesionSelectDialog.getGameLogicFromDialog(ggui);
        if(gameLogic==null)
            return;
        ScoreBoard.loadScoreBoard(ggui, gameLogic.getBoard().getTupleDimension());
        ggui.fillFrame(gameLogic);
    }
}
