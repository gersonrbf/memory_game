/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gerson
 */
public class CustomTableModel extends DefaultTableModel
{
    private boolean[][] m_editabelCells;
    private static final int m_rows = 11;
    private static final int m_columns = 4;
    private Class[] m_types = new Class [] {java.lang.String.class, 
        java.lang.String.class, java.lang.Integer.class, 
        java.lang.Integer.class};
    
    public CustomTableModel()
    {
        super(            
                new Object [][] {
                {null, null, null, null},
                {"", null, null, null},
                {"", null, null, null},
                {"", null, null, null},
                {"", null, null, null},
                {"", null, null, null},
                {"", null, null, null},
                {"", null, null, null},
                {"", null, null, null},
                {"", null, null, null}
                },            
                new String [] {                
                    "Name", "Time", "Moves", "Score"
                }
        );
        this.m_editabelCells = new boolean[m_rows][m_columns];
        setAllCellUneditable();

    }    

/*    
    public CustomTableModel(int row, int columns) {
        super(row,columns);        
        this.m_editabelCells = new boolean[row][columns];        
        for (int i = 0; i < m_editabelCells.length; i++)         
            for (int j = 0; j < m_editabelCells[i].length; j++)                            
                m_editabelCells[i][j]=false;            
    }
*/
    
    @Override
    public boolean isCellEditable(int row, int column)    
    {
        return this.m_editabelCells[row][column]; //To change body of generated methods, choose Tools | Templates.        
    }
        
    @Override
    public Class getColumnClass(int columnIndex) {
        return m_types [columnIndex];
    }
    
    public void setEditable(int row, int columns, boolean value)
    {    
        m_editabelCells[row][columns]= value;
        this.fireTableCellUpdated(row, columns);        
    }    

    void setAllCellUneditable() {
        for (int i = 0; i < m_editabelCells.length; i++) 
            for (int j = 0; j < m_editabelCells[i].length; j++) 
                m_editabelCells[i][j] = false;                    
    }
}
