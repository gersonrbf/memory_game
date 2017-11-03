/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author gerson
 */
public class Pausing implements ActionListener{
        
    @Override
    public void actionPerformed(ActionEvent e) {
        BoardGUI.setPause(!BoardGUI.getPause());
    }
        
}
