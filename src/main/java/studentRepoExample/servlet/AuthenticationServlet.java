package studentRepoExample.servlet;//package com.siit.studentRepoExample.servlet;
//
//import java.io.IOException;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//@WebServlet("/world/player.jsp?")
//public class AuthenticationServlet extends HttpServlet {
//
//
//    private static final long serialVersionUID = 1L;
//
//    // Simulate a simple username and password for demonstration purposes
//    private final String validUsername = "admin";
//    private final String validPassword = "password";
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        if (isValidUser(username, password)) {
//            request.getSession().setAttribute("username", username);
//            response.sendRedirect("welcome"); // Redirect to a welcome page after successful login
//        } else {
//            response.sendRedirect("login?error=true"); // Redirect back to login page with an error parameter
//        }
//    }
//
//    private boolean isValidUser(String username, String password) {
//        // Replace this with proper authentication logic (e.g., database lookup)
//        return username.equals(validUsername) && password.equals(validPassword);
//    }
//}
//
