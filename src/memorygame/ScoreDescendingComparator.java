/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.util.Comparator;

/**
 *
 * @author gerson
 */
public class ScoreDescendingComparator implements Comparator<Score> {

    @Override
    public int compare(Score score1, Score score2) {
        return score2.getPoints()-score1.getPoints();
    }
    
}
