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
import javax.servlet.http.HttpSession;

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
        int priceMax=-1, priceMin=-1, areaMax=-1, areaMin=-1, distance=-1;
        String type = null;
        String[] estateType;
         HttpSession session = request.getSession(true);
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
        
        String nr = request.getParameter("budget");
        if (nr != null) {
            String b = "cena" + nr + "min";
            String attr = (String) session.getAttribute(b);
            priceMin = Integer.valueOf(attr);
            b = "cena" + nr + "max";
            attr = (String) session.getAttribute(b);
            priceMax = Integer.valueOf(attr);
        }
        
        nr = request.getParameter("area");
        if (nr != null) {
            String b = "metraz" + nr + "min";
            String attr = (String) session.getAttribute(b);
            areaMin = Integer.valueOf(attr);
            b = "metraz" + nr + "max";
            attr = (String) session.getAttribute(b);
            areaMax = Integer.valueOf(attr);
        }
        
                        nr = request.getParameter("req_distanceFromCenter");

            if (nr != null) {
                distance = Integer.valueOf(nr);
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
               priceMin, 
                priceMax, 
                distanceToSchool, 
                distance,
                type, 
                areaMin, 
                areaMax, 
                garage, 
                secured, 
                playground, 
                elevator,
                isMoney,
                selectedDistanceToDowntown,
                request.getParameter("snorm"), 
                request.getParameter("tnorm")
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
