/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author piotrpaul
 */
@WebServlet(name = "fuzzyLogic", urlPatterns = {"/fuzzyLogic"})
public class fuzzyLogic extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int distanceToDowntown = -1;
        int distanceToSchool  = -1;
        String type = null;
        String[] estateType;
        boolean garage, secured, playground, elevator, selectedDistanceToDowntown, selectedDistanceToSchool, isMoney, isArea;
        
        if (request.getParameter("closeToCenter") != null) {
            if (request.getParameter("req_distanceFromCenter") != null) {
                distanceToDowntown = Integer.parseInt(request.getParameter("req_distanceFromCenter"));
            }
        } 
        
        if (request.getParameter("closeToSchool") != null) {
            if (request.getParameter("req_distanceFromSchool") != null) {
                distanceToSchool = Integer.parseInt(request.getParameter("req_distanceFromSchool"));
            }
        } 
         

        
        if (request.getParameter("estateType") != null) {
            estateType = request.getParameterValues("estateType");
           
            if (estateType.length == 2) {
                type = "both";
            }
            else if (estateType.length == 1) {
                type = estateType[0];
            }
        }
        

        if(request.getParameter("garage") == null) {
            garage = false;  
        } else {
            garage = true;
            request.setAttribute("garaz", true);
        }
        if(request.getParameter("secutityEstate") == null) {
            secured = false;  
        } else {
            secured = true;
            request.setAttribute("ochrona", true);
        }
        if(request.getParameter("playground") == null) {
            playground = false;  
        } else {
            playground = true;
            request.setAttribute("placZabaw", true);
        }
        if(request.getParameter("elevator") == null) {
            elevator = false;  
        } else {
            elevator = true;
            request.setAttribute("winda", true);
        }
        if(request.getParameter("closeToCenter") == null) {
            selectedDistanceToDowntown = false;  
        } else {
            selectedDistanceToDowntown = true;
            request.setAttribute("bliskoCentrum", true);
        }
        if(request.getParameter("closeToSchool") == null) {
            selectedDistanceToSchool = false;  
        } else {
            selectedDistanceToSchool = true;
            request.setAttribute("bliskoSzkoly", true);
        }
        
        String [] strings = request.getParameterValues("group1");
        if (strings[0].equals("koniecznaKasa")) {
            isMoney = true;
        } else {
            isMoney = false;
        }
       
      
	Logika logika = new Logika(
                Integer.parseInt(request.getParameter("minBudget")), 
                Integer.parseInt(request.getParameter("maxBudget")), 
                distanceToSchool, 
                distanceToDowntown, 
                type, 
                Integer.parseInt(request.getParameter("minArea")), 
                Integer.parseInt(request.getParameter("maxArea")), 
                garage, 
                secured, 
                playground, 
                elevator,
                isMoney
              );
	
        List<Rekord> lista = logika.start();
        
        request.setAttribute("resultList", lista);
        request.setAttribute("minArea", Integer.parseInt(request.getParameter("minArea")));
        request.setAttribute("maxArea", Integer.parseInt(request.getParameter("maxArea")));
        request.setAttribute("minCena", Integer.parseInt(request.getParameter("minBudget")));
        request.setAttribute("maxCena", Integer.parseInt(request.getParameter("maxBudget")));
        getServletContext().getRequestDispatcher("/showResultList.jsp").forward(request, response);

    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
