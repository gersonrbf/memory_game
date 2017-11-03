/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author gerson
 */
public class GameBoardUI extends JFrame{

    //private GameLogic gameLogic;
    private JPanel gPanel;
    private CardGUI[][] gButtons;
    
    private class TupleJPanelGridBagConstraints
    {
        private JPanel p;
        private GridBagConstraints gbc;
        
        public TupleJPanelGridBagConstraints(){}
        public TupleJPanelGridBagConstraints(JPanel panel, GridBagConstraints constraints){ p=panel; gbc=constraints;}
        public void setPanel(JPanel panel){p=panel;}
        public void setConstraints(GridBagConstraints constraints){gbc=constraints;}
        public JPanel getPanel(){return p;}
        public GridBagConstraints getConstraints(){return gbc;}
    }
    
    public GameBoardUI (String title, GameLogic gameLogic)
    {
        super(title);
        //this.gameLogic = gameLogic;
        fillFrame(gPanel,gButtons,gameLogic);
    }    
        
    private void fillFrame(JPanel panel, CardGUI[][] buttons, GameLogic gameLogic)
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createContentPanel(panel,buttons,gameLogic);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        this.setPreferredSize(new Dimension(width,height));
        pack();
        this.setVisible(true);
    }
    
    private void createContentPanel(JPanel panel, CardGUI[][] buttons, GameLogic gameLogic)
    {
        int lines=10;
        int columns=10;
        TupleJPanelGridBagConstraints tuple=createPanel(lines,columns);
        panel = tuple.getPanel();
        GridBagConstraints constraints = tuple.getConstraints();
        createButtons(panel,constraints,lines,columns,gameLogic);
    }
    
    private TupleJPanelGridBagConstraints createPanel(int columns, int lines)
    {
        int hgap=10;
        int vgap=10;
        JPanel empty;
        JScrollPane scrollpane;
        TupleJPanelGridBagConstraints tuple;
        
        GridBagLayout layout = new GridBagLayout();//(columns, lines, hgap, vgap);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        empty = new JPanel();
        empty.setLayout(layout);
        scrollpane = new JScrollPane(empty);
        this.getContentPane().add(scrollpane,BorderLayout.CENTER);
        tuple = new TupleJPanelGridBagConstraints(empty,gridConstraints);        
        return tuple;
    }
    
    private JButton[][] createButtons(JPanel panel, GridBagConstraints constraints, int columns, int lines, GameLogic gameLogic)
    {
        int size = 64;
        ImageIcon cardback = new ImageIcon("images/cardback.jpg");
        CardGUI[][] buttons = new CardGUI[lines][columns];
        int margin = 10;
        //int posX = margin;
        //int posY = margin;
        CardGUI.setCardBack(cardback);
        for(int l = 0; l<lines;l++)
        {
            for(int c = 0; c<columns; c++)
            {
                buttons[l][c] = new CardGUI(l*columns+c+1);
                buttons[l][c].setPreferredSize(new Dimension(size, size));
                constraints.fill = GridBagConstraints.NONE;
                constraints.gridx = c;
                constraints.gridy = l;
                //constraints.weightx = 0;
                //constraints.weighty = margin;
                //buttons[l][c].setSize(size,size);
                //buttons[l][c].setLocation(posX,posY);
                panel.add(buttons[l][c],constraints);
                //posX = posX + size + margin;
                
                
            }
            //posX=10;
            //posY=posY+size+margin;
        }
        
        return buttons;
    }
    
}
