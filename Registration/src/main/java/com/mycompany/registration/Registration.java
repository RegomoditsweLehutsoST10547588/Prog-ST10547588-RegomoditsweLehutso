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
     

/**
 *
 * @author Student
 */

 

        Scanner input = new Scanner(System.in);

        System.out.println("REGISTRATION ");

        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Enter Username: ");
        String username = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        System.out.print("Enter Phone Number (+27): ");
        String phone = input.nextLine();

        Login login = new Login();
           
          //Create a variable that will store whether the login was a success
        boolean validUsername = login.checkUserName(username);
        boolean validPassword = login.checkPasswordComplexity(password);
        boolean validPhone = login.checkCellPhoneNumber(phone);

        
        if (validUsername) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted please ensure that your username contains an underscore and is no more than five characters in length.");
        }


        if (validPassword) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }

        if (validPhone) {
            System.out.println("Phone number successfully captured.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
        }


        if (validUsername && validPassword && validPhone) {
            System.out.println("Registration successful.");
            System.out.println("LOGIN ");
            System.out.print("Enter Username: ");
            String enteredUsername = input.nextLine();
            System.out.print("Enter Password: ");
            String enteredPassword = input.nextLine();
            
            boolean loginStatus = login.loginUser(username, password,enteredUsername,enteredPassword);

           if(loginStatus){
               System.out.println("Welcome to Quickchat");
               System.out.println("option 1) Send Messages");
               System.out.println("option 2) Show recently messages - Coming Soon");
               System.out.println("option 3) Quit");
               
               int controllerv =Integer.parseInt(input.nextLine());
               
               switch (controllerv){
                
                   case 1 :
                       System.out.println("How many messages would you like to send? ");
                       
                       int total = Integer.parseInt(input.nextLine());
                       
                       
                       for (int i = 1; i <= total; i++) {
                           System.out.println("");
                           System.out.println("========= MESSAGE "+ i+ "=========");
                           System.out.println("Enter recipient number: ");
                           String recipient = input.nextLine();
                           System.out.println("Enter message: ");
                           String messageText = input.nextLine();
                           
                           if (messageText.length() > 250){
                               System.out.println("Please enter a message of less than 250 characters.");
                             i--;
                               continue;
                           }
                           Message msg = new Message (i,recipient,messageText);
                           
                           if (msg.checkMessageID()) {
                               System.out.println("Message ID successfully created.");
                               
                           }
                           System.out.println( msg.checkRecipientCell());
                           System.out.println("Message Hash: " + msg.createMessageHash());
                            System.out.println( msg.sentMessage());
                           System.out.println( msg.printMessages());
                       }
                       
                       System.out.println("Total messages sent: " + Message.returnTotalMessages());
                       
                       break;
                       
                   case 2:
              
                       System.out.println("Coming soon");
                       break;
                       
                   case 3:
                       
                       System.out.println("Goodbye.");
                       System.exit(0);
                       break;
                       
                   default :
                      
                       System.out.println("wrong input entered please");

               }
               
           }
           }else{
            System.out.println("Registration failed");
            }
        input.close();
        
    }
}
                   
                             
                    
                    
                        
                    
     
                   
                
            
        
       
    

