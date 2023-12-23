package com.example.hellofx;

import java.util.HashMap;

public class Login{
    HashMap<String,String> idPassword = new HashMap<>();

    public Login(HashMap<String, String> idPassword) {
        this.idPassword = idPassword;
    }

    public HashMap<String, String> getIdPassword() {
        return idPassword;
    }

    public void setIdPassword(HashMap<String, String> idPassword) {
        this.idPassword = idPassword;
    }
    public boolean validateIDPassword(HashMap<String,String> list,String id,String password){
        return validateUserName(list,id) && validatePassword(password);
    }
    public boolean validateUserName(HashMap<String,String> list,String userName){
        boolean isValid = false;
        if(!userName.contains(" ") && !list.containsKey(userName))
            isValid = true;
        return isValid;
    }
    public boolean validatePassword(String password){
        boolean isValid = false;
        if(password.length() >= 8)
            isValid = true;
        return isValid;
    }
    public boolean verifyUserNamePassword(String username,String password){
        return idPassword.containsKey(username) && idPassword.get(username).equals(password);
    }
}
