/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author gerson
 */
public class MapImagesToIDs {            
    
    private ArrayList<String> imagesNames=new ArrayList<>();
    
    /*
    public int numberOfFigures(String configPathName, String imagePathName)
    {
        if(this.imagesNames.isEmpty())
            this.imagesNames = readImagesFile(configPathName, imagePathName);
        return this.imagesNames.size();
    }
    */
    
    public HashMap<Integer,ImageIcon> getMap(String configPathName, String imagePathName, int nPairs){
        
        ReadImagesFile rif = new ReadImagesFile(configPathName, imagePathName);
        
        if(this.imagesNames.isEmpty())
            this.imagesNames = rif.getNames();
        
        if(this.imagesNames.size()<nPairs)                       
            System.err.println("not enought cards figures");        
                
   

        return createMapRandomized(this.imagesNames, nPairs);
    }

/*    
    private ArrayList<String> readImagesFile(String configPathName, String imagePathName)
    {
        ArrayList<String> names = new ArrayList();
                
        Path file = Paths.get(configPathName);
        try (InputStream in = Files.newInputStream(file);
                BufferedReader reader = 
                new BufferedReader(new InputStreamReader(in))) 
        {    
            String line = null;    
            while ((line = reader.readLine()) != null)
            {
                names.add(imagePathName+line);
                //System.out.println(line);
            }

        } catch (IOException x) {    
            System.err.println(x);
        }
        
        return names;
    }
*/

    private HashMap<Integer,ImageIcon> createMapOrdered(ArrayList<String> names, int n)
    {
        HashMap<Integer,ImageIcon> idsIcons = new HashMap();
        for (int i = 0; i < n; i++) {
            idsIcons.put(i, new ImageIcon(names.get(i)));
        }
        return idsIcons;
    }
    
    private HashMap<Integer,ImageIcon> createMapRandomized(ArrayList<String> cardsNames, int n)
    {   
        ArrayList<String> names = new ArrayList<>();
        HashMap<Integer,ImageIcon> idsIcons = new HashMap();
        Random randomGenerator = new Random(System.currentTimeMillis());
        int numberOfNames = 0;
        String name = "";

        
        for(int i = 0; i < cardsNames.size();i++)
            names.add(cardsNames.get(i));        
            
        for (int i = 0; i < n; i++) {
            numberOfNames = names.size();
            int randomIdx = randomGenerator.nextInt(numberOfNames);
            name = names.remove(randomIdx);
            idsIcons.put(i, new ImageIcon(name));
        }
        return idsIcons;        
    }

    
}
