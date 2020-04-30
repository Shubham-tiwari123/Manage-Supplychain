package com.project.client.requestAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "URLMapperReqAPI", urlPatterns = {
        "/dashboard",
        "/prepare_block",
        "/prepare_block2",
        "/prepare_block4",
        "/prepare_block3",
        "/page_not_found",
        "/error_page",
        "/logout",

        "/connect_device",
        "/get_keys",
        "/get_blockId",
        "/send_block",
        "/send_block2",
        "/send_block3",
        "/send_block4",
        "/login"
})

public class URLMapperReqAPI extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
            if(loginStatus.equals("true")) {
                System.out.println("iff2");
                request.getRequestDispatcher("/prepare_block.jsp").forward(request, response);
            }
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/prepare_block2")) {
            if(loginStatus.equals("true"))
                request.getRequestDispatcher("/prepare_block2.jsp").forward(request, response);
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/prepare_block3")) {
            if(loginStatus.equals("true"))
                request.getRequestDispatcher("/prepare_block3.jsp").forward(request, response);
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/prepare_block4")) {
            if(loginStatus.equals("true"))
                request.getRequestDispatcher("/prepare_block4.jsp").forward(request, response);
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/logout")) {
            cookie = new Cookie("loginStatus","false");
            response.addCookie(cookie);
            response.sendRedirect("/");
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/get_keys")) {
            GetPublicKeysReqAPI reqAPI = new GetPublicKeysReqAPI();
            reqAPI.doGet(request,response);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if((request.getRequestURL().toString()).equals("http://localhost:8080/connect_device")) {
            ConnectToServerReqAPI reqAPI = new ConnectToServerReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/get_blockId")) {
            GetBlockIDReqAPI reqAPI = new GetBlockIDReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/send_block")) {
            SendBlockReqAPI reqAPI = new SendBlockReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/send_block2")) {
            SendBlock2ReqAPI reqAPI = new SendBlock2ReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/send_block3")) {
            SendBlock3ReqAPI reqAPI = new SendBlock3ReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/send_block4")) {
            SendBlock4ReqAPI reqAPI = new SendBlock4ReqAPI();
            reqAPI.doPost(request,response);
        }
        else if((request.getRequestURL().toString()).equals("http://localhost:8080/login")) {
            LoginReqAPI reqAPI = new LoginReqAPI();
            reqAPI.doPost(request,response);
        }
    }
}
