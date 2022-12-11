package JOP2022_soup_02;


import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class soupOperation {
    
    public String soupTest() throws IOException{
        Mboard a=new Mboard();
        Document doc_a=null, doc_b=null;
        String str="爬蟲02"+"<br>", url_a="https://www.tnet.org.tw/Member/List/99999?page=2", url_b="";
        int j=1, k=1;
        for(int i=1; i<=16; i++){
            url_a="https://www.tnet.org.tw/Member/List/99999?page="+Integer.toString(i);
            doc_a = Jsoup.connect(url_a).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36").cookie("auth", "token").get();
            Elements bElements=doc_a.getElementsByClass("row member-list");
            for(Element b:bElements){
                
                str += Integer.toString(j++)+": ";
                str += a.textMessage(b.getElementsByClass("member-list__title").text());
                str += a.textMessage(b.getElementsByClass("member-list__link").text())+"&nbsp";
                if(a.textMessage(b.getElementsByClass("member-list__link").text()).isEmpty()) {
                    str += "<br>";
                    continue;
                }

                url_b=b.getElementsByClass("member-list__link").text();
                //if(j==7) 
                //    break;
                try{
                    doc_b = Jsoup.connect(url_b).get();
                    str += doc_b.text().contains("減碳")?"「有」減碳字眼:"+Integer.toString(k++):"沒有減碳相關";
                }catch(Exception e){
                    str += "Error: "+e+"<br>";
                }                
                str += "<br>";
            }
        }
        str +="「有」減碳相關的公司共 "+Integer.toString(k);
        return str;
    }


    
}
