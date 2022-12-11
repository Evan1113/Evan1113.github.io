package JOP2022_soup_02;

import static JOP2022_soup_02.CSV.UTF8_BOM;
import java.io.IOException;


public class CSV_browse extends CSV {

    public CSV_browse(String realPathOfservlet, String frontFileName) {
        super(realPathOfservlet, frontFileName);
    }
    
    public String htmlForBrowse() throws IOException{
        String str="";

        String tempString="";
        String strReadline="";
        String[] y=null;
        
        str ="<table width='100%' style='table-layout:fixed;'>";
        int i=0,    // row index
            j=0;    // col index
        
        while ((strReadline=bufferReader.readLine()) != null) { // data row
            y=strReadline.split(token);
            tempString="";
            
            if(i==0) {  //the operation of the first row
                for(String k:y) { 
                    tempString +="<td style='background-color:yellow; color:red; ' >"+k+"</td> ";
                    j++;
                }
                tempString ="<tr>"+tempString+"</tr>";
                for(int k=0; k<j; k++) {        // the width setting of columns
                    if(k==(j-1)) tempString ="<col width='4%' />"+tempString;
                    else tempString ="<col width='24%' />"+tempString;
                }
                str=str+tempString; // set column width
            }
            else {
                for(int k=0; k<j; k++) 
                    if(k >= y.length) tempString +="<td>"+""+"</td> "; 
                    else tempString +="<td>"+y[k]+"</td> ";
                str +="<tr>"+tempString+"</tr>";
            }
            
            
            i++; 
        }           
        str += "</table>"; 
        return str;
    }
    public String showFields() throws IOException{
        String str="";
        String strReadline=null;
        String[] y=null;
        
        str ="";
        int i=1;
        while ((strReadline=bufferReader.readLine()) != null) { // data row
            y=strReadline.split(token);

            for(String k:y) {
                str +="Field "+Integer.toString(i)+" :"+htmlSpace+htmlSpace+k+"<br> ";
                i++;
            }           
            break;
        }
        return str;
   }
    

}
