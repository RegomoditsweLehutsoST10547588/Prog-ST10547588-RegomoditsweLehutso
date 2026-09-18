/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registration;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Registration {

    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        boolean registered = false;
        
        // we make sure to ask for any registore input until it is successful
        while(!registered) {
            System.out.println("Enter username that contains an underscore with no more than five characters long: ");
            String username = scanner.nextLine();
            
            System.out.println("Enter a password with at least eight characters long containing a capital later, a number and a special character: ");
            String password = scanner.nextLine();
            
            System.out.println("Enter a South African cell phone number: ");
            String cellnumber = scanner.nextLine();
            
            Login user = new Login(username, password, cellnumber);
            String registrationResults = user.registerUser();
            System.out.println(registrationResults);
            
            
            if (registrationResults.equals("Username successfully captured. Password successfully captured. Cell phone number successfully added.")) {
                registered = true;
                
                boolean loggedIn = false;
                
                // making sure that it keeps on asking the user login until it is successful
                while (!loggedIn) {
                    
                    System.out.println("Now please log in.");
                    
                    System.out.println("Enter username: ");
                    String enteredUsername = scanner.nextLine();
                    
                    System.out.println("Enter password:");
                    String enteredPassword = scanner.nextLine();
                    
                    String loginResult = user.returnLoginStatus(enteredUsername, enteredPassword);
                    System.out.println(loginResult);
                    
                    // make sure we stop the loop if the user login was successful
                    if (loginResult.equals("Welcome " + username + "it is great to see you again.")) {
                        loggedIn = true;
                    }
                   
                }
            }
        }
       
    }
}
