/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 *
 * @author gerson
 */
public class Clock implements ActionListener{

    private int m_timeElapsedSeconds = 0;
    private JLabel m_label = null;
    private boolean m_maxTimeReached = false;
    private int m_tempTime = 0;
    private int m_divisionFactor = 1;

    public Clock(int factor) {
        this.m_divisionFactor = 1000/factor;
    }        
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(!(isPaused()||m_maxTimeReached||isGameOver()||isFirstMove()))
        {
            m_tempTime++;
            if(m_tempTime==m_divisionFactor)
            {
            this.m_tempTime=0;
            this.m_timeElapsedSeconds++;
            }
        }
        
        
        //if(!(isPaused()||maxTimeReached||isGameOver()||isFirstMove()))
        //    this.timeElapsedSeconds++;
            
        if(m_label!=null)
        {
            String time; 
                    
            if(this.m_timeElapsedSeconds==Integer.MAX_VALUE)
            {
                this.m_maxTimeReached = true;
                time = "Max Time Reached";
            }   
            else            
                time = convertTime(this.m_timeElapsedSeconds);
            
            String text = String.format("<html>Time:%S<br/>Total move:%d</html>",time,BoardGUI.getTotalMoves());
            m_label.setText(text);
        }
    }
    
    public void setLabel(JLabel label){
        this.m_label= label;
    }
    
    public boolean isPaused()
    {
        return BoardGUI.getPause();
    }
    
    public boolean isGameOver()
    {
        return BoardGUI.getGameOver();
    }
    
    public boolean isFirstMove()
    {
        return BoardGUI.getFirstMove();
    }

    public void resetClock(){
        this.m_tempTime = 0;
        this.m_timeElapsedSeconds = 0;
        this.m_maxTimeReached = false;   
    }
    
    public void resetLabel(){
        if(m_label!=null)
            m_label.setText("<html>Time:00:00:00<br/>Total move:0</html>");
    }
    
    public int getTime()
    {
        return m_timeElapsedSeconds;
    }
    
    public static String convertTime(int timeElapsedSeconds) {

        int remainder = 0;        
        int hours = timeElapsedSeconds/3600;        
        remainder = timeElapsedSeconds%3600;        
        int minutes= remainder/60;        
        int seconds = remainder%60;        
        return String.format("%02d:%02d:%02d",hours,minutes,seconds);
    }
    
}
