package DataSourceUtil;

import javax.crypto.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Properties;

public class DBConnection {
    private static Connection con=null;
    public static Connection getDBConnection(){
        if(con!=null){
            return con;
        }else{
             String props[]=new DBConnection().getDBProperties();
             String password=props[3];
             if(password!=null) {
                 try {
                     Class.forName(props[0]);
                     con = DriverManager.getConnection(props[1], props[2], props[3]);
                 } catch (ClassNotFoundException e) {
                     throw new RuntimeException(e);
                 } catch (SQLException e) {
                     throw new RuntimeException(e);
                 }
             }else{
                 System.out.println("Unable to fetch or decode the pass key");
             }
        }
        return con;
    }

    private String[] getDBProperties(){
        String[] props=null;
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(in);
            props=new String[4];
            props[0]=(String)properties.get("datasource.driver");
            props[1]=(String)properties.get("datasource.url");
            props[2]=(String)properties.get("datasource.username");
            props[3]=(String)properties.get("datasource.pass");
            System.out.println(props[0]);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return props;
    }
    private static String getDecryptedKey(String encyKey){
        String decryptedKey=null;
        try{
            FileInputStream fileIn=new FileInputStream("src/main/resources/sckey.ser");
            ObjectInputStream inStream=new ObjectInputStream(fileIn);
            SecretKey scKey=(SecretKey) inStream.readObject();
            Cipher desCipher=Cipher.getInstance("DES");
            desCipher.init(Cipher.DECRYPT_MODE, scKey);
            String keys= Files.readString(Paths.get("src/main/resources/keyFile"), StandardCharsets.UTF_8);
            byte[] deText= desCipher.doFinal(Base64.getDecoder().decode(encyKey));
            decryptedKey=new String(deText);
        }catch (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return decryptedKey;
    }
}
