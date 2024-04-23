package business;

import DAO.UserAuthenticationDAO;
import Request.UserAuthenticationRequest;
import Response.UserLoginResponse;
import contants.Constants;
import contants.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginBusiness {
    public void validateUser(UserAuthenticationRequest request, UserLoginResponse response, HttpServletRequest servletRequest){
        if(request!=null && request.getEmpId()!=null && request.getEmpId().trim().length()>0 &&
                request.getPassword()!=null && request.getPassword().length>0){
            if(!UserAuthenticationDAO.isUserExist(request.getEmpId())){
                response.setMessage(Constants.USER_NOT_FOUND);
                response.setStatus(Constants.STATUS_CODE_FAILED);
                response.setErrorCode(ErrorCode.USER_NOT_FOUND);
            }else{
                if(UserAuthenticationDAO.validateUserCreds(request)){
                    HttpSession session=servletRequest.getSession();
                    session.setAttribute("loggedIn",true);
                    session.setAttribute("empid",request.getEmpId());
                    response.setMessage(Constants.LOGGED_IN_SUCCESSFULLY);
                    response.setStatus(Constants.STATUS_CODE_SUCCESS);
                    response.setErrorCode(null);
                }else{
                    response.setMessage(Constants.INCORRECT_PASSWORD_SUPPLIED);
                    response.setStatus(Constants.STATUS_CODE_FATAL);
                    response.setErrorCode(ErrorCode.INCORRECT_PASSWORD_SUPPLIED);
                }
            }
        }else{
            response.setMessage(Constants.INVALID_REQUEST);
            response.setStatus(Constants.STATUS_CODE_FAILED);
            response.setErrorCode(ErrorCode.INVALID_USER_AUTH_REQUEST);
        }

    }
}
