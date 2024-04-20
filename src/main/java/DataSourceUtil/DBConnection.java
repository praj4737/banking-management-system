package DataSourceUtil;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection con=null;
    public static Connection getDBConnection(){
        if(con!=null){
            return con;
        }else{
                String props[]=getDBProperties();
            try {
                Class.forName(props[0]);
                con=DriverManager.getConnection(props[1],props[2],props[3]);
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
            props[3]=(String)properties.get("datasource.pass");
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return props;
    }
}
