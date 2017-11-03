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
public class ImageTime implements ActionListener{
        private CardGUI firstCard = null;
        private CardGUI secondCard = null;
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            this.firstCard.flipCard();                
            this.secondCard.flipCard();            
            BoardGUI.setSleepingStatus(false);
        }
        
        public void setFirstCard(CardGUI card)
        {
            this.firstCard = card;
        }
                
        public void setSecondCard(CardGUI card)
        {
            this.secondCard = card;
        }
    }
