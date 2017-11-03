/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

/**
 *
 * @author gerson
 */
public class Card {
  
    private int id = -1;
    private boolean folded = true;

    public Card()
    {
        
    }
    
    public Card(int id) {
        this.id = id;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id=id;
    }
    
    public boolean getFolded()
    {
        return this.folded;
    }
    
    public void setFolded(boolean folded)
    {
        this.folded = folded;
    }
    
    public void flipCard()
    {
        this.folded=!this.folded;
    }
    
}

