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
public class MemoryGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //int a[][]=new int[2][3];
        //System.out.printf("lines:%d columns:%d\n",a.length,a[0].length);
        
        
        //CLI cli = new CLI(gl);        
        //cli.printBoard(true);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //GameBoardUI gui = new GameBoardUI("Memory Game",gl);
                //GameGUI gui = new GameGUI("Memory Game",new GameLogic(6,13));
                GameGUI gui = new GameGUI("Memory Game");
            }
        });
        
    }
    
}
