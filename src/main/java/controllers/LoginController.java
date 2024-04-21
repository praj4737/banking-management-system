package controllers;

import DataSourceUtil.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Properties;

public class LoginController{


    public static void main(String[] args) throws Exception {

        //Connection con= DBConnection.getDBConnection();
        System.out.println(DBConnection.getDBConnection());
        System.out.println(DBConnection.getDBConnection());
        System.out.println(DBConnection.getDBConnection());
        System.out.println(DBConnection.getDBConnection());
        System.out.println(DBConnection.getDBConnection());
        String plainText="Computer@123";
        System.out.println(plainText);
        try{
        //Encrypt
        KeyGenerator keyGenerator=KeyGenerator.getInstance("DES");
        SecretKey scKey=keyGenerator.generateKey();
        //initialize descipher
        Cipher desCipher=Cipher.getInstance("DES");
        byte[] text=plainText.getBytes();
        desCipher.init(Cipher.ENCRYPT_MODE, scKey);
        byte[] enText= desCipher.doFinal(text);
        String cipherText=new String(enText);
        System.out.println(cipherText);

        //decr
        desCipher.init(Cipher.DECRYPT_MODE, scKey);
        byte[] deText= desCipher.doFinal(enText);
        String dePlaintext=new String(deText);
        System.out.println(dePlaintext);
    }catch (Exception e) {
        e.printStackTrace();
    }

    }
}
