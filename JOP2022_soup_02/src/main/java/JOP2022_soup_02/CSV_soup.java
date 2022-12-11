package JOP2022_soup_02;

import java.io.IOException;
import java.net.ConnectException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class CSV_soup extends CSV {
    int fieldNo=-1;
    String SearchValue="";
    int i=0, k=0, errorCount=0;
    String va = "";
    public CSV_soup(String realPathOfservlet, String frontFileName, String fieldNo, String searchValue) {
        super(realPathOfservlet, frontFileName);
        this.fieldNo=Integer.parseInt(fieldNo);
        this.SearchValue=searchValue;
    }
    
    public String soupSearch() throws IOException ,ConnectException{
        String strReadline="", str="", ioErrorStr="";
        long startTime = System.nanoTime();

        String[] y=null;
//        String searchValue  =  request.getParameter("searchValue");
        
        Document doc_a=null, doc_b=null;  
        
        
        while ((strReadline=bufferReader.readLine()) != null) { // data row
            if(i==0) {i++; continue;}
            y=strReadline.split(token);
            try{doc_b = Jsoup.connect(y[fieldNo]).header("Accept-Encoding", "gzip, deflate").userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36").get();}
            catch(Exception e){ ioErrorStr += "Error on item "+String.valueOf(i)+"has message "+e +"<br>"; errorCount++; continue;}
            
            str += y[0]+":   "+y[fieldNo-1]+"，  ";
            try{ str += doc_b.text().contains(SearchValue)?"「有」"+SearchValue+"字眼:"+Integer.toString((k++)+1):"沒有"+SearchValue+"相關"; }
            catch(Exception e){str += "Error: "+e+"<br>";}
            str += "<br>";
//            if(i>=10) break;
            i++;
            
        }    
        long endTime = System.nanoTime();
        long counter = (endTime - startTime)/1000000000;
        long minute = (0 + ((counter % 3600) / 60));
        long second = (0 + (counter % 60));
         
        str =   "爬蟲搜尋時間:" + minute + "分" + second + "秒<br>" + 
                str +"<br>"+" 爬蟲探索的連結總共有 "+String.valueOf(i-1)+
                "<br>"+"具 "+SearchValue+" 的連結有 "+Integer.toString(k)+
                "<br> total error count is "+ Integer.toString(errorCount)+"<br><br>";
        str += ioErrorStr;
        

        va = "104," + Integer.toString(k) + "," + Integer.toString(i - k - 1) + "," +  Integer.toString(errorCount);
        
        return str;
    }
    

}
