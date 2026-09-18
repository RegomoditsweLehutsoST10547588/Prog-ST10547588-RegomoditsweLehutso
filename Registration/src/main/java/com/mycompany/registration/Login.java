/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registration;

/**
 *
 * @author Student
 */
public class Login {
    // This stores the variables from the user registration ad login
    private String Username;
    private String Password;
    private String Cellnumber;
    
    public Login(String Username, String Password, String Cellnumber) {
        this.Username = Username;
        this.Cellnumber = Cellnumber;
        this.Password = Password;
        
    }
    
    public boolean checkUsername(){
        //check if the username has an underscore
        boolean Hasunderscore = Username.contains("_");
        // check if the username ha five or less characters
        boolean ValidLength = Username.length() <= 5;
        return Hasunderscore && ValidLength;
    }
    
    public boolean checkPassword() {
        //check if password is at les eight characters long
        if (Password.length() < 8) {
            return false;
        }
        //trying to see which of the requirements are met
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        // check through each of the characters one by one
        for (int i = 0; i < Password.length(); i++) {
            char c = Password.charAt(i);
            
         // check if the character is uppercase
         if (Character.isUpperCase(c)) {
             hasUpper = true;
         }
         //check if character is a number
         if(Character.isDigit(c)){
             hasDigit = true;
         }
         // check if character is a special character
         if (!Character.isLetterOrDigit(c)){
             hasSpecial = true;
             
         }
        }
        // returning true only if all the requirements are met
        return hasUpper && hasDigit && hasSpecial;
        
            
        }
    public boolean checkCellPhoneNumber() {
        //check if the cell phone number starts with a plus sign
        boolean hasPlus = Cellnumber.startsWith("+27");
        //check if the rest of the number is digits only and correct length
        boolean validFormat = Cellnumber.matches("\\+27\\d{9}");
        
        //return true only if noth conditions are met
        return hasPlus && validFormat;
        
    }
    public String registerUser() {
        //check if username if incorrect
        if (!checkUsername()) {
            return "Username is not correctly formatted, please ensure that username contains an underscore and is no more than fice charaters long";
            
        }
        //check if the password is incorrect
        if (!checkPassword()) {
            return "Password is not correctly formatted, please ensure password contains at least eight characters, a capital letter,a number and a special character";
        }
        //check if the cell phone number is incorrect
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain an international code";
        } else {
            return "Username or password incorrect,please try again";
        }
            
        }
    public boolean loginUser(String enteredUsername, String enteredPassword){
        return Username.equals(enteredUsername) && Password.equals(enteredPassword);
    }
    public String returnLoginStatus(String enteredUsername, String enteredPassword){
        if (Username.equals(enteredUsername) && Password.equals(enteredPassword)) {
            return "welcome " + Username + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
          
         
        
    
}
