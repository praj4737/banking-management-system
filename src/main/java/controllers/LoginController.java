package controllers;

import DataSourceUtil.DBConnection;
import Request.UserAuthenticationRequest;
import Response.UserLoginResponse;
import business.LoginBusiness;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.util.Properties;
@WebServlet("/login")
public class LoginController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserAuthenticationRequest request=new UserAuthenticationRequest();
        //request.setEmpId(req.getParameter("empid"));
        //request.setPassword(req.getParameter("secrets").toCharArray());
        request.setEmpId("31001");
        request.setPassword("pass".toCharArray());
        System.out.println(Paths.get("").toAbsolutePath());
        UserLoginResponse userLoginResponse =new UserLoginResponse();
        LoginBusiness loginBusiness=new LoginBusiness();
        loginBusiness.validateUser(request,userLoginResponse,req);
        resp.getWriter().println(userLoginResponse.getMessage());
        resp.getWriter().println(userLoginResponse.getStatus());
        resp.getWriter().println(userLoginResponse.getErrorCode());




    }
}
