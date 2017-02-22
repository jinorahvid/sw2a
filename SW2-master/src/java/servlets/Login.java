/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dto.Dto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Lenovo
 */
public class Login extends HttpServlet {

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
        String user = request.getParameter("user");
        String psw = request.getParameter("psw");
        Dto d=new Dto();
        String rpta=d.loginStudent(user, psw);
        JSONObject o=new JSONObject();
        try{
            JSONParser p=new JSONParser();
            o=(JSONObject)p.parse(rpta);
        }catch(Exception e){
            System.out.println(e);
        }
        String nombre=o.get("nombre").toString();
        String id=o.get("id").toString();
        String seccion=o.get("seccion").toString();
        
        Cookie cookie1 = new Cookie("nombre", nombre);
        cookie1.setMaxAge(24 * 60 * 60);
        Cookie cookie2 = new Cookie("seccion", seccion);
        cookie2.setMaxAge(24 * 60 * 60);
        Cookie cookie3 = new Cookie("id", id);
        cookie3.setMaxAge(24 * 60 * 60);
        System.out.println(cookie1.getName()+"-"+cookie2.getName()+"-"+cookie3.getName());
        System.out.println(cookie1.getValue()+"-"+cookie2.getValue()+"-"+cookie3.getValue());
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.print(nombre);     
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
