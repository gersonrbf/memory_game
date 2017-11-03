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

/**
 *
 * @author gerson
 */
public class ReadImagesFile {

    private String m_configPathName = "";
    private String m_imagePathName = "";

    public ReadImagesFile(String configPathName, String imagePathName) {
        m_configPathName = configPathName;
        m_imagePathName = imagePathName;
    }

    public ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList();

        Path file = Paths.get(m_configPathName);
        try (InputStream in = Files.newInputStream(file);
                BufferedReader reader
                = new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                names.add(m_imagePathName + line);
                //System.out.println(line);
            }

        } catch (IOException x) {
            System.err.println(x);
        }

        return names;
    }

}
