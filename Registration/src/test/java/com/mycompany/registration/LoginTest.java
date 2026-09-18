/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    public LoginTest() {
    }
    
    @Test
    public static void testUsernameCorrect() {
        //if the user name has an underscore and 5 characters then it should return true.
        Login login = new Login("Ski_4", "+27631984907", "P@ssw0rd");
        assertEquals(true, login.checkUsername());
    }
    
    @Test
    public static void testUsernameIncorrect() {
        //Ski_4 has no underscore and is too long the return false
        Login login = new Login("Ski_4", "passsword", "+27631984907");
        assertEquals(true, login.checkPassword());
    }
    
    @Test
    public void testPasswordCorrect() {
        //P@sswor0d has capital letter, number and a special character return true.
        Login login = new Login("Ski_4", "P@ssw0rd", "+27631984907");
        assertEquals(true, login.checkPassword());
    }
    
    @Test
    public void testPasswordIncorrect() {
      //password has no capital letter, no number, no special charecter should return false
      Login login = new Login("Ski_4", "password", "+27631984907");
      assertEquals(false, login.checkPassword());
    
    }
    @Test
    public void testCellNumberCorrect() {
        //+27631984907 has no capital latter, no number, no special character should return true
        Login login = new Login("Ski_4", "P@ssw0rd", "+27631984907");
        assertEquals(true, login.checkCellPhoneNumber());
    }
  
    @Test
    public void testCellNumberIncorrect() {
     //0631984907 has no international code or no +. should return false.
     Login login = new Login("Ski_4", "P@ssw0rd", "0631984907");
     assertEquals(false, login.checkCellPhoneNumber());
   
    }
    
    @Test
    public void testLoginSuccessful() {
        //correct username and password entered should return true.
        Login login = new Login("Ski_4", "P@ssw0rd", "+27631984907");
        assertEquals(true, login.loginUser("Ski_4", "P@ssw0rd"));
  
        
  }
    
   @Test
   public void testLoginFailed() {
       //wrong password entered should return false.
       Login login = new Login("Ski_4", "P@ssw0rd", "+27631984907");
       assertEquals(false, login.loginUser("Ski_4", "P#ssword"));
   }
   @Test
   public void testLoginFailed2() {
       //wrong Username return false
       Login login = new Login("Ski_4", "P@ssw0rd", "+27631984907");
       assertEquals(false, login.loginUser("Ski_8", "P#ssword"));
   }
    
}
