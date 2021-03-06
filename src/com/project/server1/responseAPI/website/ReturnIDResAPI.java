package com.project.server1.responseAPI.website;

import com.project.server1.utils.ConstantClass;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ReturnIDResAPI")
public class ReturnIDResAPI extends HttpServlet {

    public void sendResponse(HttpServletResponse response,long productID) throws IOException {
        JSONObject jsonObject = new JSONObject();
        if (productID==-1)
            jsonObject.put("statusCode", ConstantClass.FAILED);
        else {
            jsonObject.put("statusCode",ConstantClass.SUCCESSFUL);
            jsonObject.put("productID", productID);
        }

        System.out.println("JSON:"+jsonObject.toString());
        PrintWriter writer = response.getWriter();
        writer.println(jsonObject.toString());
    }
}
