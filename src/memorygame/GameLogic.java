/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author gerson
 */
public class GameLogic
{

    private Board m_board;
    
    
    public static Tuple<GameLogic,String> getGameLogic(int numberOfLines, int numberOfColumns)
    {
        String message = "";
        GameLogic gameLogic = null;
        int nElements = numberOfLines*numberOfColumns;
        if(!CheckParity.isEven(nElements))
        {
            message = "At least one of the dimensions must be even";
            return new Tuple<>(gameLogic, message);
        }

        
        if(numberOfLines<0||numberOfColumns<0)
        {
            message = "Both numbers must be positive";
            return new Tuple<>(gameLogic, message);
        }
        
        Board board = new Board(numberOfLines, numberOfColumns);
        generateRandomCardPositions(board, numberOfLines, numberOfColumns);        
        gameLogic = new GameLogic(board);
        return new Tuple<>(gameLogic, message);        
    }
    
    private GameLogic(Board board)
    {
        m_board = board;
    }
    
    /*
    public GameLogic(int numberOfLines, int numberOfColumns) {
        //check if i>0 and j>0
        this.board = new Board(numberOfLines, numberOfColumns);                
        ClassesOutput errormsg = generateRandomCardPositions(this.board,numberOfLines,numberOfColumns);
        if(errormsg.getErrorNumber()!=0)
        {
            errormsg.print();
            return;
        }
            
    }
    */
    
    private static void generateRandomCardPositions(Board board, int numberOfLines, int numberOfColumns) {
        Random randomGenerator = new Random(System.currentTimeMillis());
        int numberOfElements = numberOfLines*numberOfColumns;
        ArrayList<Integer> ids = new ArrayList<>();
        
        numberOfElements/=2;
        
        for(int i=0; i<numberOfElements;i++)
        {            
            ids.add(i);
            ids.add(i);
        }
                
        for(int i=0; i<numberOfLines; i++)
        {
            for(int j=0; j<numberOfColumns; j++)
            {
                int randomIdx = randomGenerator.nextInt(ids.size());
                board.getCard(i, j).setId(ids.get(randomIdx));
                ids.remove(randomIdx);
            }
        }
        //return new ClassesOutput();
    }
    
    public ClassesOutput checkCards(int c1i, int c1j, int c2i, int c2j)
    {
        if(c1i>=this.m_board.getNumberOfLines()&&c1j>=this.m_board.getNumbersOfColumns()&&
                c2i>=this.m_board.getNumberOfLines()&&c2j>=this.m_board.getNumbersOfColumns())
            return new ClassesOutput<>(-1,"index out of range");
        return new ClassesOutput<>(this.m_board.getCard(c1i,c1j).getId()==this.m_board.getCard(c2i,c2j).getId());
    }

    public boolean checkCards(int id1, int id2) {
        return id1==id2;
    }
    
    public Board getBoard() {
        return this.m_board;
    }        
    
}
