package DataSourceUtil;

import jdk.internal.org.jline.terminal.TerminalBuilder;

import javax.crypto.*;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    static SecretKey skey = null;
    private static Connection con=null;
    public static Connection getDBConnection() throws Exception, NoSuchPaddingException, NoSuchAlgorithmException {
        String descryptedPassword = null;
        if(con!=null){
            return con;
        }else{
                String props[]=getDBProperties();
                //decrypting the passsword.
                    Cipher descipher = Cipher.getInstance("DES");
                    byte[] encrypted = props[3].getBytes();
                    descipher.init(Cipher.DECRYPT_MODE,skey);
                    byte[] decrypted = descipher.doFinal(encrypted);
                     descryptedPassword = new String(decrypted);
           // System.out.println(descryptedPassword);

            try {
                Class.forName(props[0]);
                con=DriverManager.getConnection(props[1],props[2],descryptedPassword);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }

    private static String[] getDBProperties(){
        String[] props=null;
        try {
            FileReader propFile = new FileReader("src/main/resources/application.properties");
            Properties properties = new Properties();
            properties.load(propFile);
            props=new String[4];
            props[0]=(String)properties.get("datasource.driver");
            props[1]=(String)properties.get("datasource.url");
            props[2]=(String)properties.get("datasource.username");
            String pass=(String)properties.get("datasource.pass");

            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
             skey = keyGenerator.generateKey();
            Cipher descipher = Cipher.getInstance("DES");
            byte[] text = pass.getBytes();
            descipher.init(Cipher.ENCRYPT_MODE,skey);
            byte[] encrypted = descipher.doFinal(text);
            String encryptedPassword = new String(encrypted);
            props[3] = encryptedPassword;
        }catch (IOException ex){
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return props;
    }
}
