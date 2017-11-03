/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author gerson
 */
public class BoardGUI implements ActionListener {

// <editor-fold defaultstate="collapsed" desc=" Variables ">
    private GameLogic m_gameLogic;
    private CardGUI[][] m_buttons;
    private CardGUI m_firstCard = null;
    private CardGUI m_secondCard = null;
    //private String imagesPathName = "images/64x64/";
    //private String cardsBack = "images/64x64/cardback.jpg";
    //private int preferredCardSize = 64;
    /*
    private static final String c_configPathName = "config/cardsIconNames";
    private int m_preferredCardSize = 100;
    private static final String c_imagesPathName = "images/100x100/";
    private String m_cardsBack = c_imagesPathName + "cardback.jpg";
    */
    private int m_preferredCardSize = 100;
//    private static final String c_configPathName = "alphabet_version/config/cardsIconNames";
//    private static final String c_imagesPathName = "alphabet_version/images/jpg_files/100x100/";
    private static final String c_configPathName = "alphabet_version/config/cardsIconNames";
    private static final String c_imagesPathName = "alphabet_version/images/jpg_files/100x100/";
    private String m_cardsBack = c_imagesPathName + "cardback.jpg";
    private static ImageTime m_imageTime = null;
    //
    private static Clock m_clock = null;
    private int m_sleepTime = 500;
    private static boolean m_isSleeping = false;
    private static int c_countPairsFound = 0;
    private static int c_countTotalMoves = 0;
    private static boolean m_firstMove = true;
    private static final int m_oneSecond = 100;
    private static boolean m_paused = false;
    private JLabel m_clockLabel = null;
    private static boolean m_gameOver = false;
    private MapImagesToIDs m_mapImgIDs = null;
    private Timer m_clockTimer = null;
    private Timer m_timer = null;

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" Constructors ">
    public BoardGUI(GameLogic gameLogic) {
        this.m_gameLogic = gameLogic;
        if (BoardGUI.m_imageTime == null) {
            BoardGUI.m_imageTime = new ImageTime();
        }
        if (BoardGUI.m_clock == null) {
            BoardGUI.m_clock = new Clock(BoardGUI.m_oneSecond);
        }
        
        this.m_mapImgIDs = new MapImagesToIDs();
        
    }

// </editor-fold>

    
    public void createButtons()
    {        
        int size = this.m_preferredCardSize;
        ImageIcon cardback = new ImageIcon(this.m_cardsBack);
        int columns = this.m_gameLogic.getBoard().getNumbersOfColumns();
        int lines = this.m_gameLogic.getBoard().getNumberOfLines();        
        this.m_buttons = new CardGUI[lines][columns];
        int nPairs = this.m_gameLogic.getBoard().getNumbersOfElements()/2;
        HashMap<Integer,ImageIcon> icons = this.m_mapImgIDs.getMap(this.c_configPathName,this.c_imagesPathName,nPairs);
        CardGUI.setCardBack(cardback);
        Board board = this.m_gameLogic.getBoard();
        for(int l = 0; l<lines;l++)
        {
            for(int c = 0; c<columns; c++)
            {
                Card card = board.getCard(l, c);
                this.m_buttons[l][c] = new CardGUI(card,icons.get(card.getId()));
                this.m_buttons[l][c].setPreferredSize(new Dimension(size, size));
                this.m_buttons[l][c].addActionListener(this);
            }
        }
        
    }    
    
    public void addButtonsToPanel(JPanel panel, GridBagConstraints constraints)
    {
        int columns = this.m_gameLogic.getBoard().getNumbersOfColumns();
        int lines = this.m_gameLogic.getBoard().getNumberOfLines();
        
        for(int l = 0; l<lines;l++)
        {
            for(int c = 0; c<columns; c++)
            {
                constraints.fill = GridBagConstraints.NONE;
                constraints.gridx = c;
                constraints.gridy = l+1;
                panel.add(m_buttons[l][c],constraints);                
            }
        }        
    }
    
    void removeButtonsToPanel(JPanel panel) {
        int columns = this.m_gameLogic.getBoard().getNumbersOfColumns();
        int lines = this.m_gameLogic.getBoard().getNumberOfLines();
        
        for(int l = 0; l<lines;l++)        
            for(int c = 0; c<columns; c++)
            {
                m_buttons[l][c].removeActionListener(this);
                panel.remove(m_buttons[l][c]);
            }                
                    
    }    
    
    public JLabel addClockLabel(JPanel p) {

        
        GridBagConstraints gbc = new GridBagConstraints();
        //JLabel clock = new JLabel("<html>Score<br/>Time:00:00:00<br/>Total move:0<br/>Pairs Found:0</html>");
        JLabel clock = new JLabel("<html>Time:00:00:00<br/>Total move:0</html>");
        JPanel clockPanel = new JPanel();
        clockPanel.setLayout(new GridBagLayout());
        clockPanel.add(clock);
        //clockPanel.setBackground(Color.red);
        
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 30;
        gbc.weightx = 0.0;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth = 100;
        p.add(clockPanel,gbc);
        
        return clock;
    }    
    
    public void resetClock()
    {
        BoardGUI.m_clock.resetClock();
    }
    
    public void resetClockLabel()
    {
        BoardGUI.m_clock.resetLabel();
    }
        
// <editor-fold defaultstate="collapsed" desc=" Set Methods ">
    public static void setSleepingStatus(boolean status) {
        BoardGUI.m_isSleeping = status;
    }
    
    public void setClockLabel(JLabel clock) {
        this.m_clockLabel = clock;
    }
    
    public void setGameLogic(GameLogic gl) {
        this.m_gameLogic = gl;
    }
    
    public static void setPause(boolean status) {
        BoardGUI.m_paused = status; //To change body of generated methods, choose Tools | Templates.
    }

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc=" Get Methods">
    public static int getTotalMoves() {
        return BoardGUI.c_countTotalMoves;
    }
    
    public static int getPairsFound() {
        return BoardGUI.c_countPairsFound;
    }
    
    public static boolean getPause() {
        return BoardGUI.m_paused;
    }

    public static boolean getGameOver() {
        return BoardGUI.m_gameOver;
    }
    
    public static boolean getFirstMove()
    {
        return BoardGUI.m_firstMove;
    }
    
    public CardGUI[][] getButtons()
    {
        return this.m_buttons;
    }
    
    public static String getConfigPathName()
    {
        return c_configPathName;
    }
    
    public static String getImagesPathName()
    {
        return c_imagesPathName;
    }
    
// </editor-fold>        
        
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(BoardGUI.m_paused||BoardGUI.m_gameOver)
            return;
        
        if(BoardGUI.m_firstMove)
        {
            BoardGUI.m_firstMove=false;
            if(this.m_clockTimer==null){
                BoardGUI.m_clock.setLabel(m_clockLabel);            
                this.m_clockTimer = new Timer(BoardGUI.m_oneSecond,BoardGUI.m_clock);
                m_clockTimer.setRepeats(true);                
                m_clockTimer.start();
            }
        }
        if(BoardGUI.m_isSleeping)
            return;
        CardGUI cardGUI = (CardGUI) e.getSource();
        if(m_firstCard==null){
            BoardGUI.c_countTotalMoves++;
            m_firstCard = cardGUI;
            cardGUI.flipCard();
        }
        else
        {
            m_secondCard = cardGUI;
            cardGUI.flipCard();            
            if(m_firstCard!=m_secondCard)
            {
                BoardGUI.c_countTotalMoves++;
                if(this.m_gameLogic.checkCards(this.m_firstCard.getCard().getId(),this.m_secondCard.getCard().getId()))            
                {                            
                    BoardGUI.c_countPairsFound++;  
                    m_firstCard.setEnabled(false);
                    m_secondCard.setEnabled(false);

                }            
                else            
                {                 
                    BoardGUI.m_isSleeping=true;                
                    BoardGUI.m_imageTime.setFirstCard(m_firstCard);                
                    BoardGUI.m_imageTime.setSecondCard(m_secondCard);        
                    if(this.m_timer==null){                    
                        this.m_timer = new Timer(this.m_sleepTime,BoardGUI.m_imageTime);                         
                        this.m_timer.setRepeats(false);
                    }
                    this.m_timer.start();

                }                
            }
            m_firstCard=null;            
            m_secondCard=null;

            if(BoardGUI.c_countPairsFound==(this.m_gameLogic.getBoard().getNumbersOfElements())/2.0)
            {
                BoardGUI.m_gameOver = true;
                int points = ScoreBoard.calculateScore(BoardGUI.m_clock.getTime(),BoardGUI.getTotalMoves(),m_gameLogic.getBoard().getNumbersOfElements());
                int idx = ScoreBoard.checkScoreBoard(points,BoardGUI.m_clock.getTime(),BoardGUI.getTotalMoves());
                //System.out.println("Points:"+points);
                if(idx>=0)
                    ScoreBoard.addScore(idx,new Score(BoardGUI.m_clock.getTime(),BoardGUI.getTotalMoves(),points));
                //else
                //    System.out.println("ELSE");
                //System.out.println(score.getScore(m_gameLogic.getBoard().getNumbersOfElements()));
            }
        }

    }    

    public void resetBoardGUI()
    {
        BoardGUI.m_firstMove = true;
        this.m_firstCard = null;
        this.m_secondCard = null;
        BoardGUI.m_gameOver = false;
        BoardGUI.m_paused = false;
        this.resetClock();
        this.resetClockLabel();
        BoardGUI.c_countPairsFound=0;
        BoardGUI.c_countTotalMoves=0;                        
    }
    public void newGame(JPanel gPanel, GridBagConstraints constraints, GameLogic gameLogic) {        
        BoardGUI.m_firstMove = true;
        BoardGUI.m_gameOver = false;
        this.resetClock();
        this.resetClockLabel();
        BoardGUI.c_countPairsFound=0;
        BoardGUI.c_countTotalMoves=0;        
        this.removeButtonsToPanel(gPanel);        
        this.setGameLogic(gameLogic);
        this.createButtons();
        this.addButtonsToPanel(gPanel, constraints);        
    }

}
