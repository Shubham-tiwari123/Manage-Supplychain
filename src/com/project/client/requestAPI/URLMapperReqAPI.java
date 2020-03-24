package com.project.client.requestAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "URLMapperReqAPI", urlPatterns = {"/dashboard","/prepare_block","/prepare_block2",
        "/prepare_block4","/prepare_block3","/page_not_found","/error_page","/logout"})

public class URLMapperReqAPI extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("path:"+request.getHeader("referer"));
        System.out.println("link:"+request.getRequestURL().toString());
        String referrerPath = request.getHeader("referer");
        Cookie cookie = null;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        String loginStatus = "false";
        if(cookies!=null){
            for (Cookie value : cookies) {
                cookie = value;
                if (cookie.getName().equals("loginStatus")) {
                    loginStatus = cookie.getValue();
                    break;
                }
            }
        }

        if((request.getRequestURL().toString()).equals("http://localhost:8080/dashboard")) {
            if(loginStatus.equals("true"))
                request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/prepare_block")) {
            if(loginStatus.equals("true"))
                request.getRequestDispatcher("/prepareblock.jsp").forward(request, response);
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/prepare_block2")) {
            if(loginStatus.equals("true"))
                request.getRequestDispatcher("/prepareblock2.jsp").forward(request, response);
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/prepare_block3")) {
            if(loginStatus.equals("true"))
                request.getRequestDispatcher("/prepareblock3.jsp").forward(request, response);
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/prepare_block4")) {
            if(loginStatus.equals("true"))
                request.getRequestDispatcher("/prepareblock4.jsp").forward(request, response);
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/logout")) {
            cookie = new Cookie("loginStatus","false");
            response.addCookie(cookie);
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/error_page")) {
            System.out.println("client API sdsdsd");
            /*request.getRequestDispatcher("/errorpage.jsp").forward(request, response);*/
            response.sendRedirect("/page_not_found");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/page_not_found")) {
            request.getRequestDispatcher("/errorpage.jsp").forward(request, response);
        }
    }
}
