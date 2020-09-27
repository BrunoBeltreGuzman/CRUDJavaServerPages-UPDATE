/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Usuarios;
import ModeloDAO.UsuariosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author diosl
 */
public class Controlador extends HttpServlet {

    private final String pageListar = "Pages/PageListar.jsp";
    private final String pageAgregar = "Pages/PageAgregar.jsp";
    private final String pageEditar = "Pages/PageEditar.jsp";
    private final String pageNotFont = "Pages/PageNotFont.jsp";

    private String acceso = "";

    Usuarios usuarios = new Usuarios();

    UsuariosDAO usuariosDAO = new UsuariosDAO();

    private String id;

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("listar")) {
            listar(request, response);
        }

        if (action.equalsIgnoreCase("accesoRegistrar")) {
            accesoRegistrar(request, response);
        }

        if (action.equalsIgnoreCase("registrar")) {
            registrar(request, response);
        }

        if (action.equalsIgnoreCase("accesoEditar")) {
            accesoEditar(request, response);
        }

        if (action.equalsIgnoreCase("editar")) {
            editar(request, response);
        }

        if (action.equalsIgnoreCase("eliminar")) {
            eliminar(request, response);
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
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

    private void listar(HttpServletRequest request, HttpServletResponse response) {
        acceso = pageListar;
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response) {

        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String email = request.getParameter("txtEmail");
        String user = request.getParameter("txtUser");
        String passWord = request.getParameter("txtPassWord");

        usuarios.setNombre(nombre);
        usuarios.setApellido(apellido);
        usuarios.setEmail(email);
        usuarios.setUser(user);
        usuarios.setPassWord(passWord);

        boolean result = usuariosDAO.registrar(usuarios);

        if (result == true) {
            acceso = pageListar;
        } else {
            acceso = pageNotFont;
        }

    }

    private void accesoRegistrar(HttpServletRequest request, HttpServletResponse response) {
        acceso = pageAgregar;
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) {
        id = request.getParameter("txtId");
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String email = request.getParameter("txtEmail");
        String user = request.getParameter("txtUser");
        String passWord = request.getParameter("txtPassWord");

        usuarios.setNombre(nombre);
        usuarios.setApellido(apellido);
        usuarios.setEmail(email);
        usuarios.setUser(user);
        usuarios.setPassWord(passWord);
        usuarios.setId(id);

        boolean result = usuariosDAO.editar(usuarios);

        if (result == true) {
            acceso = pageListar;
        } else {
            acceso = pageNotFont;
        }
    }

    private void accesoEditar(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("idUsuario", request.getParameter("txtId"));
        acceso = pageEditar;
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) {
        id = request.getParameter("txtId");
        usuarios.setId(id);

        boolean result = usuariosDAO.eliminar(id);

        if (result == true) {
            acceso = pageListar;
        } else {
            acceso = pageNotFont;
        }
    }
}
