/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils; 

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael Noguera
 */
public class saltTest {
    public static void main(String[] args) throws Exception{
        PasswordUtils test = new PasswordUtils();
        String password = "asdf";
        String salt = "IUK3LIk67YkXwM7tWBdbAA==";
        
        String hashpassword = "moiox7I1tBmYYe+0ajq4S+5TLp7vOu3evnF64/8u/KU=";
        try {
            hashpassword = test.hashPassword(password,salt);
        } catch (Exception ex) {
            Logger.getLogger(saltTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if(test.hashPassword(password, salt).equals(hashpassword)){
            System.out.println("LocalEncryptation was done trought SHA-256");
            
        }else{
            System.out.println("Something went wrong");
        }
        
        System.out.println("Password: "+password);
        System.out.println("salt: "+salt);
        System.out.println("Hashed: "+hashpassword);
    }
}
