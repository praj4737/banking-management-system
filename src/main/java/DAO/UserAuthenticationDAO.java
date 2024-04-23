package DAO;

import Request.UserAuthenticationRequest;
import Response.UserLoginResponse;
import contants.Constants;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthenticationDAO {
    public static boolean isUserExist(String empId)  {
        String query="select * from emptable where emp_id="+empId+";";

        try {
            ResultSet rd = QueryExecutorDAO.executeQuery(query);
            if (rd != null && rd.next()) {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean validateUserCreds(UserAuthenticationRequest request){
        String query="select * from empcreds where emp_id="+request.getEmpId()
                +" and password='"+String.valueOf(request.getPassword())+"';";
        try {
            ResultSet rd = QueryExecutorDAO.executeQuery(query);
            if (rd != null && rd.next()) {
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
