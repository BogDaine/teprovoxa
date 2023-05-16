package com.example.teprovoxa.utils;

public class CredentialsValidator {
    public static boolean passwordValid(String pwd)
    {
        if(pwd.indexOf(' ') >= 0)
            return false;
        if(pwd.length()< 3)
            return false;
        return true;
    }
    public static boolean usernameValid(String usr){
        if(usr.length()<3)
            return false;
        if(usr.indexOf(' ') >= 0)
            return false;
        return true;
    }
}
