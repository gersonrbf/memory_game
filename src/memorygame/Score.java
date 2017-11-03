/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.io.Serializable;

/**
 *
 * @author gerson
 */
public class Score implements Serializable/*Comparable<Score>*/ {

    
// <editor-fold defaultstate="collapsed" desc=" Fields ">
    private int m_time = 1;
    private int m_moves = 1;
    private String m_name = "";
    private int m_points = 0;
    private boolean m_hasName = false;
// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc=" Constructors ">
    public Score(String name, int time, int moves, int points) {
        m_time = time;
        m_moves = moves;
        m_name = name;
        m_points = points;
        m_hasName = true;
    }
    
    public Score(int time, int moves, int points) {
        m_time = time;
        m_moves = moves;
        m_points = points;
    }
// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc=" Get Methods ">

    public String getName()
    {
        return m_name;
    }
    
    public int getTime()
    {
        return m_time;
    }
    
    public int getMoves()
    {
        return m_moves;
    }

    public int getPoints()
    {
        return m_points;
    }
// </editor-fold>        
    
// <editor-fold defaultstate="collapsed" desc=" Set Methods">
    public void setName(String name) {
        m_name = name;
        m_hasName = true;
    }

// </editor-fold>

    /*
    @Override
    public int compareTo(Score otherScore) {
        return this.getScore()-otherScore.getScore();
    }
    */
    
}