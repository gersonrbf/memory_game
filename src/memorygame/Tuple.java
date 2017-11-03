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
 * @param <T1>
 * @param <T2>
 */
public class Tuple<T1,T2> implements Serializable{
    private T1 m_firstElement;
    private T2 m_secondElement;

    public Tuple(){}

    public Tuple(T1 firstElement, T2 secondElement)
    {
        m_firstElement = firstElement;
        m_secondElement = secondElement;
    }
    
    public T1 getFirstElement()
    {
        return m_firstElement;
    }
    
    public T2 getSecondElement()
    {
        return m_secondElement;
    }
    
    public void setFirstElement(T1 firstElement)
    {
        m_firstElement = firstElement;
    }
    
    public void setSecondElement(T2 secondElement)
    {
        m_secondElement = secondElement;
    }
    
}
