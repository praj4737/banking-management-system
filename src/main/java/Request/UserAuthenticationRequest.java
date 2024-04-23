package Request;

public class UserAuthenticationRequest {
    private String empId;
    private  char[] password;

    public String getEmpId() {
        return empId;
    }

    public char[] getPassword() {
        return password;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
