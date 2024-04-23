package DAO;

import DataSourceUtil.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecutorDAO {
    public static ResultSet executeQuery(String query){
        Connection con= DBConnection.getDBConnection();
        if(con!=null){
            try {
                Statement st=con.createStatement();
                ResultSet rd=st.executeQuery(query);
                return rd;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
