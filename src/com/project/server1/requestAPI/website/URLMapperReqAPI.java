package com.project.server1.requestAPI.website;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "URLMapperReqAPI",urlPatterns = {
        "/dashboard",
        "/team",
        "/database",
        "/sign_up",
        "/sign_in",
        "/detail_view",
        "/page_not_found",
        "/error_page",
        "/logout",

        "/first_block",
        "/second_block",
        "/third_block",
        "/fourth_block",
        "/admin_login",
        "/connect_server",
        "/request_productID",
        "/product_info",                //SendProductChainReqAPI

})

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
                if (cookie.getName().equals("adminLoginStatus")) {
                    loginStatus = cookie.getValue();
                    break;
                }
            }
        }
        if((request.getRequestURL().toString()).equals("http://localhost:8081/dashboard")) {
            if(loginStatus.equals("true"))
                request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/team")) {
            request.getRequestDispatcher("/team.jsp").forward(request, response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/database")) {
            if(loginStatus.equals("true"))
                request.getRequestDispatcher("/database.jsp").forward(request, response);
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/sign_up")) {
            request.getRequestDispatcher("/").forward(request, response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/sign_in")) {
            request.getRequestDispatcher("/").forward(request, response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/detail_view")) {
            if(loginStatus.equals("false"))
                response.sendRedirect("/");
            else if(referrerPath==null)
                response.sendRedirect("/database");
            else
                request.getRequestDispatcher("/detailview.jsp").forward(request, response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/error_page")) {
            response.sendRedirect("/page_not_found");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/page_not_found")) {
            request.getRequestDispatcher("/errorpage.jsp").forward(request, response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/logout")) {
            cookie = new Cookie("adminLoginStatus","false");
            response.addCookie(cookie);
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if((request.getRequestURL().toString()).equals("http://localhost:8081/first_block")) {
            AcceptFirstBlockReqAPI reqAPI = new AcceptFirstBlockReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/second_block")) {
            AcceptSecondBlockReqAPI reqAPI = new AcceptSecondBlockReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/third_block")) {
            AcceptThirdBlockReqAPI reqAPI = new AcceptThirdBlockReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/fourth_block")) {
            AcceptFourthBlockReqAPI reqAPI = new AcceptFourthBlockReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/admin_login")) {
            AdminLoginReqAPI reqAPI = new AdminLoginReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/connect_server")) {
            ConnectDeviceReqAPI reqAPI = new ConnectDeviceReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/request_productID")) {
            ReturnIDReqAPI reqAPI = new ReturnIDReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8081/product_info")) {
            SendProductChainReqAPI reqAPI = new SendProductChainReqAPI();
            reqAPI.doPost(request,response);
        }
    }
}
