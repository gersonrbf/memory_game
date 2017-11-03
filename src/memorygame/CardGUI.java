/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author gerson
 */
public class CardGUI extends JButton {
    
    //private final int id;
    //private static boolean second;
    //private static int previousCardId;
    private static GameLogic gl = null;
    private Card card = null;
    private static Icon cardBack=null;
    private Icon cardFace = null;
    
    
    public CardGUI(int id)
    {
        super(Integer.toString(id));
        //this.id = id;
    }
    
    
    public CardGUI(Card card, Icon cardFace)
    {
        super(CardGUI.cardBack);
        this.card = card;
        //this.id = id;
        this.cardFace = cardFace;
    }    
     
    /*
    public CardGUI(int id, Icon icon)
    {
        super(icon);
        this.id = id;
    }
    */
    
    public static void setGameLogic(GameLogic gl)
    {
        CardGUI.gl = gl;        
    }    
    
    public static void setCardBack(Icon icon)
    {
        CardGUI.cardBack = icon;
    }
    
    public void flipCard()
    {
        if(this.card.getFolded())
            this.setIcon(this.cardFace);
        else
            this.setIcon(CardGUI.cardBack);                
        this.card.flipCard();
    }
    
    public Card getCard()
    {
        return this.card;
    }
    
}
