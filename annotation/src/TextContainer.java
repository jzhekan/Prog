/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author IVoitsekhovskyi
 */

@AnnotationSave(nameMethod = "saveInfo")
public class TextContainer {
    String info;
    
    public TextContainer(String s){
        this.info= s;
    }
    
    private void saveInfo(String passSave) throws Exception{
       Path path = Paths.get(passSave);  
       if(!(Files.exists(path)))
          path = Files.createFile(path);
       
           File f = path.toFile();
           FileWriter fw = new FileWriter(f);
           fw.write(info);
           fw.flush();
           fw.close();       
        
    }
        
}

 @AnnotationSave(passSave = "D:\\Test\\test.txt", nameMethod = "saveText")
 class TextContainer2 {
    String info;
    
    public TextContainer2(String s){
        this.info= s;
    }
    
    public void saveText(String passSave) throws Exception{
        Path path = Paths.get(passSave);  
       if(!(Files.exists(path)))
          path = Files.createFile(path);
       
           File f = path.toFile();
           FileWriter fw = new FileWriter(f);
           fw.write(info);
           fw.flush();
           fw.close();           
        
    }
        
}
