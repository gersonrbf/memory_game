/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 *
 * @author gerson
 */
public class GameGUI extends JFrame{

    private JPanel m_gPanel = null;    
    private BoardGUI m_boardGUI = null;
    private GridBagConstraints m_gbc = null;
    //private GameLogic gameLogic = null;
    private JMenuBarCreator m_mbc = null;
   
    /*
    public GameLogic getGameLogic()
    {
        return this.gameLogic;
    }
    */
    
    /*
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
    */
    
    /*
    public GameGUI (String title, GameLogic gameLogic)
    {
        super(title);
        this.gameLogic=gameLogic;
        createEmpty();

        //this.boardGUI = fillFrame(gameLogic);
    }
    */

    public GameGUI (String title)
    {
        super(title);        
        HashMap<String, ArrayList<Score>> map = ScoreBoard.deserialize();
        //printMap(map);
        ScoreBoard.setScoreBoardMap(map);
        ScoreBoard.loadScoreBoard(this, null);
        createEmpty();
    }
    
    private void createEmpty()
    {
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                //System.out.println("Tried to serialize");
                ScoreBoard.serialize(ScoreBoard.getScoreBoardMap());
                e.getWindow().dispose();
                System.exit(0);
            }
        });
        this.m_mbc = new JMenuBarCreator(this);
        this.setJMenuBar(this.m_mbc.getGameMenuBar());
        createPanel();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        this.setPreferredSize(new Dimension(width,height));
        pack();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        this.setVisible(true);       
    }
        
    public void fillFrame(GameLogic gameLogic)
    {
        /*
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Menu bar
        JMenuBarCreator mbc = new JMenuBarCreator(this);
        this.setJMenuBar(mbc.getJMenuBar());
        //
        */

        BoardGUI boardgui = new BoardGUI(gameLogic);        
        boardgui.createButtons();
        //createPanel();
        boardgui.addButtonsToPanel(m_gPanel, this.m_gbc);
        JLabel clock = boardgui.addClockLabel(m_gPanel);
        boardgui.setClockLabel(clock);
        /*
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        this.setPreferredSize(new Dimension(width,height));
        this.pack();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        this.setVisible(true);
        */
        this.m_boardGUI = boardgui;
        JMenuItem mi = this.m_mbc.getGameMenuBar().getGameMenu().getNewGameMenuItem();
        ActionListener[] als = mi.getActionListeners();
        for(int i = 0; i<als.length;i++)
            mi.removeActionListener(als[i]);
        mi.addActionListener(new NewGame(this));
        this.validate();
        this.repaint();
        //this.pack();
    }
        
    private void createPanel()
    {
        JScrollPane scrollpane;
        GridBagLayout layout = new GridBagLayout();
        this.m_gbc = new GridBagConstraints();
        this.m_gPanel = new JPanel();
        this.m_gPanel.setLayout(layout);
        scrollpane = new JScrollPane(this.m_gPanel);
        this.getContentPane().add(scrollpane,BorderLayout.CENTER);
    }
    
    public void newgame(GameLogic gameLogic) {
        m_boardGUI.resetBoardGUI();
        m_boardGUI.removeButtonsToPanel(m_gPanel);
        m_boardGUI.setGameLogic(gameLogic);
        m_boardGUI.createButtons();
        m_boardGUI.addButtonsToPanel(m_gPanel, m_gbc);
        this.validate();
        this.repaint();
        //this.pack();
    }    

    private void printMap(HashMap<String, ArrayList<Score>> map) {
        System.out.println("Map");
        for (Map.Entry<String, ArrayList<Score>> entry : map.entrySet()) {
            String key = entry.getKey();
            ArrayList<Score> value = entry.getValue();
            System.out.printf("key %s\n", key);
            for(Score s:value)
                System.out.printf("Score> Name:%s Time:%d Moves:%d Points:%d\n\n", s.getName(),s.getTime(),s.getMoves(),s.getPoints());
        }
        
    }
}
