package com.example.hellofx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

public class  Admin {

//    admin:
    private static final String adminID = "admin";
    private static final String adminPassword = "12345678";

    private Admin(){

    }

    public static boolean returnAdminObject(String username,String password){
        if(username.equals(adminID) && password.equals(adminPassword))
            return true;
        else
            return false;
    }



}

