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
public class Board {
    private Card[][] cards;
    private int numberOfLines = 0;
    private int numberOfColumns = 0;
    
    public Board(int numberOfLines, int numberOfColumns) {
        this.numberOfLines = numberOfLines;
        this.numberOfColumns = numberOfColumns;
        this.cards = new Card[numberOfLines][numberOfColumns];
        for(int i=0; i < numberOfLines; i++)
            for (int j = 0; j < numberOfColumns; j++) 
                this.cards[i][j]=new Card();
            
    }
    
    public void setCard(int positionX, int positionY, Card card)
    {
        this.cards[positionX][positionY] = card;
    }
    
    public Card getCard(int positionX, int positionY)
    {
        return this.cards[positionX][positionY];
    }
    
    public int getNumberOfLines()
    {
        return this.numberOfLines;
    }
    
    public int getNumbersOfColumns()
    {
        return this.numberOfColumns;
    }
    
    public int getNumbersOfElements()
    {
        return this.numberOfColumns*this.numberOfLines;
    }

    public Tuple<Integer, Integer> getTupleDimension() {
        return new Tuple<>(numberOfLines, numberOfColumns); //To change body of generated methods, choose Tools | Templates.
    }
}
