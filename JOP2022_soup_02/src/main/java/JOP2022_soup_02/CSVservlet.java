/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JOP2022_soup_02;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author USER
 */
public class CSVservlet extends HttpServlet {

    String str="";
    String va="104";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String frontFileName     = request.getParameter("fileName"),
               fieldNo           = request.getParameter("fieldNo");
        String searchValue       = request.getParameter("searchValue");



        ServletContext context=request.getServletContext();
        
        CSV_browse a=null;
        CSV_soup   b=null;

        
        switch(request.getParameter("actionID")){
            case "getChartData":
                str= va;
//                str="100, 27, 133, 19, 23, 76, 42, 58, 45,66,33";
                outputSlides(request, response);   
                break;
            case "showFields":
                a=new CSV_browse(context.getRealPath("/"), frontFileName);
                a.setReadBuffer();
                str=a.showFields();
                a.close();
                htmlStandardView(request, response);
                break;
            case "browse":
                a=new CSV_browse(context.getRealPath("/"), frontFileName);
                a.setReadBuffer();
                str = a.htmlForBrowse();
                a.close();
                htmlStandardView(request, response);
                break;
            case "soupList":
                a=new CSV_browse(context.getRealPath("/"), frontFileName);
                a.setReadBuffer();
                str=a.fieldsList();
                a.close();
                htmlStandardView(request, response);
                break;
            case "soupAction":
                b=new CSV_soup(context.getRealPath("/"), frontFileName, fieldNo, searchValue);
                b.setReadBuffer();
                str=b.soupSearch();
                va = b.va;
                b.close();
                htmlStandardView(request, response);
                return;
                
            default:
                break;
                    
        }
        
        
        
        
    }

    
    public void  htmlStandardView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<link rel=\"stylesheet\" href=\"home.css\" />");
            out.println("</head>");
            out.println("<body>");
            out.println(str);
            out.println("</body>");
            out.println("</html>");
        }
    }

    public void  outputSlides(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            out.println(str);

        }                
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
