/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import javax.swing.JTable;

/**
 *
 * @author gerson
 */
public class CustomTable extends JTable
{
    
    public CustomTableModel getCustomModel()
    {
        return (CustomTableModel) this.getModel();
    }

    
}
