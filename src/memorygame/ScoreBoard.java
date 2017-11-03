/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author gerson
 */
//Static Class
public class ScoreBoard {

    private static int c_maxScore=100;    
    private static float c_cteTime = 0.75f;
    private static Tuple<Integer,Integer> c_dimension = null;
    private static ArrayList<Score> c_scores = null;
    private static int c_maxNumberOfScores = 0;
    private static ScoreBoardGUI c_sbGUI = null;
    private static ScoreDescendingComparator c_scoreComparator = null;
    private static HashMap<String,ArrayList<Score>> c_serializableMap;
    private static String c_path = "scoreTable.ser";
    
    public static boolean loadScoreBoard(GameGUI gamegui, Tuple<Integer,Integer> dimension)
    {   
        initScoreBoardGUI(gamegui);
        if(dimension == null)
            return false;
        c_sbGUI.cleanScoreTable();
        c_dimension = dimension;

        /*
        ArrayList<Score> scores = new ArrayList<>();
        scores.add(new Score("a",10,123,12));
        scores.add(new Score("b",2,451,314));
        scores.add(new Score("c",14,241,23));
        scores.add(new Score("d",45,523,41));
        */
        c_scores = c_serializableMap.getOrDefault(getKeyForDimension(dimension), new ArrayList<>());
        c_scoreComparator = new ScoreDescendingComparator();
        Collections.sort(c_scores,c_scoreComparator);
        c_sbGUI.FillTable(c_scores);
        
        return true;
    }
    
    /*
    public static boolean saveScoreBoard()
    {
        return false;
    }
    */

    public static int checkScoreBoard(int points, int time, int moves)
    {
        //System.out.println("67");
        if(c_scores == null)
            throw new NullPointerException();
        
        //if(c_scores.size()<c_maxNumberOfScores)
        //    return c_scores.size();
            
        if(c_scores.isEmpty())
            return 0;
        
        for(int i=0;i<c_scores.size();i++)
        {
        //System.out.println(i);            
            if(c_scores.get(i).getPoints()<points)
                return i;
            else if(c_scores.get(i).getPoints()==points)
            {
                if(c_scores.get(i).getMoves()>moves)
                    return i;
                else if(c_scores.get(i).getMoves()==moves)
                {
                    if(c_scores.get(i).getTime()>time)                    
                        return i;
                }
            }
        
        }
        return c_scores.size()==c_maxNumberOfScores?-1:c_scores.size();
    }

    public static void addScore(int index, Score score) {
        //System.out.println("83");
        if(c_scores.size()==c_maxNumberOfScores)
            c_scores.remove(index);
        c_scores.add(index, score);
        c_sbGUI.rewriteRowsBelow(index,score);
        c_serializableMap.put(getKeyForDimension(c_dimension), c_scores);
       //serialize(c_serializableMap);
    }
    
    public static int calculateScore(float timeInSeconds,float moves,int nElements)
    {
        float minMoves = (float) getMinimumMoves(nElements);
        float minTime = minMoves*c_cteTime;
        
        float timeRate = (float) (timeInSeconds<minTime?0.5:(0.5*minTime/timeInSeconds));
        float moveRate = (float) (moves<minMoves?0.5:(0.5*minMoves/moves));
        
        //System.out.printf("time:%f minTime:%f moves:%f minMoves:%f\n",timeInSeconds,minTime,moves,minMoves);
        
        return (int)Math.ceil((timeRate+moveRate)*c_maxScore);
    }
    
    public static ScoreBoardGUI getScoreBoardGUI()
    {
        return c_sbGUI;
    }
    
    //2*numberOfCards(elements)
    private static int getMinimumMoves(int line, int columns)
    {
        return 2*line*columns;
    }
    
    private static int getMinimumMoves(int nElements)
    {
        return 2*nElements;
    }
    
    private static void initScoreBoardGUI(GameGUI gamegui)
    {
        if(c_sbGUI==null)
            c_sbGUI = new ScoreBoardGUI(gamegui, true);
        c_maxNumberOfScores = c_sbGUI.getNumberOfRows();
        //System.out.println(c_maxNumberOfScores);
    }

    public static void serialize(HashMap<String,ArrayList<Score>> map) {
        try
        {         
            FileOutputStream fileOut = new FileOutputStream(c_path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(map);
            out.close();
            fileOut.close();
            //System.out.println("Serialized data");      
        }
        catch(IOException i)
        {                   
            i.printStackTrace();
        }        
    }

    public static HashMap<String, ArrayList<Score>> deserialize() {
        
        HashMap<String, ArrayList<Score>> map = null;
        try
        {
            FileInputStream fileIn = new FileInputStream(c_path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            map = (HashMap<String, ArrayList<Score>>) in.readObject();
            in.close();
            fileIn.close();
            //System.out.println("From file");
        }
        catch (FileNotFoundException notFound)
        {
            //System.out.println("Not Found");
            map = new HashMap<>();
        }
        catch (IOException i)
        {
            i.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException c)
        {
                c.printStackTrace();
            return null;
        }

        /*
        System.out.println("Deserialized Employee...");
        System.out.println("Name: " + e.name);
        System.out.println("Address: " + e.address);
        System.out.println("SSN: " + e.SSN);
        System.out.println("Number: " + e.number);
        */
        
        return map;
    }
    
    public static void setScoreBoardMap(HashMap<String, ArrayList<Score>> map)
    {      
        c_serializableMap = map; //To change body of generated methods, choose Tools | Templates.
    }
    
    private static String getKeyForDimension(Tuple<Integer,Integer> dimension)
    {
        return String.format("[%d,%d]", dimension.getFirstElement(),dimension.getSecondElement());
    }    

    public static HashMap<String, ArrayList<Score>> getScoreBoardMap() {
        return c_serializableMap;
    }
}
