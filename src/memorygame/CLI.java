/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.util.Scanner;

/**
 *
 * @author gerson
 */
public class CLI {
    
    GameLogic gl;
    
    public CLI(GameLogic gameLogic)
    {
        this.gl=gameLogic;
        run();
    }
    
    private void run()
    {
        Scanner scanner = new Scanner(System.in);
        boolean playing=true;
        int a,b,c,d,count=0;
        ClassesOutput<Boolean> co;
        
        printBoard(true);
        
        do
        {
            System.out.printf("Please choose index of two cards:\n");
            a = scanner.nextInt();
            b = scanner.nextInt();
            c = scanner.nextInt();
            d = scanner.nextInt();

            //deveria checar antes de buscar o card
            Card card1 = this.gl.getBoard().getCard(a, b);        
            Card card2 = this.gl.getBoard().getCard(c, d);
            
            co = gl.checkCards(a, b, c, d);
            printBoard(card1,card2);
            
            //System.out.printf(co.getErrorNumber(),co.getOutput());
            
            if((co.getErrorNumber()==0)&&(co.getOutput()==true)&&card1.getFolded()&&card2.getFolded())
            {
                count++;
                System.out.println("count:"+count);
                this.gl.getBoard().getCard(a, b).setFolded(false);
                this.gl.getBoard().getCard(c, d).setFolded(false);
            }
            
            printBoard(false);
            
            if(count==this.gl.getBoard().getNumberOfLines()*this.gl.getBoard().getNumbersOfColumns()/2)
                playing=false;
                
        }while(playing);
    }
    
    public void printBoard(boolean cheat)
    {
        //System.out.printf("%d,%d\n", this.board.getNumberOfLines(),this.board.getNumbersOfColumns());
        for(int i=0; i<this.gl.getBoard().getNumberOfLines(); i++)
        {
            for(int j=0; j<this.gl.getBoard().getNumbersOfColumns(); j++)
            {
                Card c = this.gl.getBoard().getCard(i, j);
                if(c.getFolded()&& !cheat)
                    System.out.printf("### ");
                else
                    System.out.printf("%03d ",this.gl.getBoard().getCard(i, j).getId());
            }
            System.out.print("\n");
        }
        
            System.out.print("===================\n");        
    }

    public void printBoard(Card card1, Card card2)
    {
        //System.out.printf("%d,%d\n", this.board.getNumberOfLines(),this.board.getNumbersOfColumns());
        
        boolean foldCard1 = card1.getFolded();
        boolean foldCard2 = card2.getFolded();
        
        if(foldCard1)
            card1.setFolded(false);        
        if(foldCard2)        
            card2.setFolded(false);
        
        for(int i=0; i<this.gl.getBoard().getNumberOfLines(); i++)
        {
            for(int j=0; j<this.gl.getBoard().getNumbersOfColumns(); j++)
            {
                Card card = this.gl.getBoard().getCard(i, j);
                if(card.getFolded())
                    System.out.printf("### ");
                else
                    System.out.printf("%03d ",this.gl.getBoard().getCard(i, j).getId());
            }
            System.out.print("\n");
        }
                
        if(foldCard1)
            card1.setFolded(true);
        if(foldCard2)
            card2.setFolded(true);
        System.out.print("===================\n");
    }
    
}
