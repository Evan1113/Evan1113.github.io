/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JOP2022_soup_02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author eugene
 */
public class CSV {
    String beckendFileName="", frontFileName="";
    File filePointer =null;       
    BufferedReader bufferReader=null;
    static final String UTF8_BOM = "\uFEFF";
    static final String token=",", htmlSpace="&nbsp";
    
    public CSV(String realPathOfservlet, String frontFileName){
        this.frontFileName=frontFileName;
        
        String str="", projectDirectory="", projectName="";        
        String[] y=null;
        String  fileDirectory="";
                
        y=realPathOfservlet.split(".war");
        realPathOfservlet=y[0];
        y=realPathOfservlet.split("\\\\");
        projectName=y[y.length-1];
        
        y=realPathOfservlet.split(projectName);
        projectDirectory=y[0];          
        fileDirectory=projectDirectory+projectName+"\\src\\main\\java\\"+projectName+"\\Data\\";
        beckendFileName=fileDirectory+frontFileName;        
    }
    
    public void setReadBuffer() throws FileNotFoundException, UnsupportedEncodingException{
        filePointer = new File(beckendFileName);
        InputStreamReader readDevice = new InputStreamReader (new FileInputStream(filePointer),"UTF-8"); // UTF-8 must be declared in Java
        bufferReader=new BufferedReader(readDevice);
    }
    
    public void close() throws IOException{
        bufferReader.close();
        if(filePointer.exists() && filePointer.isFile()) filePointer=null; // close file           
    }
    
    public String fieldsList() throws IOException{
        String str="";
        String strReadline=null;
        String[] y=null;
        
        
        str ="<select name='selectionFields' id='selectionFields' style='width: 120px;'>";
        int i=0;
        while ((strReadline=bufferReader.readLine()) != null) { // data row
            if (strReadline.startsWith(UTF8_BOM)) strReadline = strReadline.substring(1);  // remove UTF8_BOM
            y=strReadline.split(token);

            for(String k:y) {
                str +="<option value='"+Integer.toString(i)+"'>"+k+"</option>";
                i++;
            }           
            break;
        }
        return str+"</select>";
   }        
    
}
