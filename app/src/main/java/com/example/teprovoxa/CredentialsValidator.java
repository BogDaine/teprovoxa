package com.example.teprovoxa;

public class CredentialsValidator {
    static boolean passwordValid(String pwd)
    {
        if(pwd.indexOf(' ') >= 0)
            return false;
        if(pwd.length()< 3)
            return false;
        return true;
    }
    static boolean usernameValid(String usr){
        if(usr.length()<3)
            return false;
        if(usr.indexOf(' ') >= 0)
            return false;
        return true;
    }
}
