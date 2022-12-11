package JOP2022_soup_02;

public class Mboard {
    
    static String message="";
    static int i=1;
    boolean foundLast=false;
    String textSpace=" ", htmlSpace="&nbsp";
    String textLeftLess="<", textRightGreat=">", htmlLeftLess = "&lt;" , htmlRightGreat ="&gt;" ;
    String htmlLeftToken="%#($*", htmlRightToken="%#)$*";
    String lines[]=null;
    String A="", B="";
    public String addMessage(String message){
        if(!message.isEmpty()){
            if(i!=1) this.message +="<br>";
            this.message +="<span style='color:red; background-color:yellow';> message "+Integer.toString(i++)+": "+"</span><br>";
            while(message.length() != 0){
                message=message.trim();
                if(message.contains(htmlLeftToken) &&(message.contains(htmlRightToken))){
                    A=message.substring(0, message.indexOf(htmlLeftToken));
                    this.message += textMessage(A);
                    B=message.substring(message.indexOf(htmlLeftToken)+5, message.indexOf(htmlRightToken));
                    this.message += B+"<br>";
                    message=message.substring(message.indexOf(htmlRightToken)+5, message.length());
                }else{
                    this.message +=textMessage(message);
                    foundLast=true;
                }
                if(foundLast) break;
            }
        }
        
        return this.message;
    }
    
    public String textMessage(String message){
        String updateString="";
        if(!message.isEmpty()){
            lines=message.split("\n");
            for(String k:lines){
                k=k.replaceAll(textSpace,htmlSpace);
                k=k.replaceAll(textLeftLess, htmlLeftLess);
                k=k.replaceAll(textRightGreat,htmlRightGreat);                
                updateString += k+"&nbsp";
            }   
        }        
        return updateString;        
    }
    
}
