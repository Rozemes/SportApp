/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.support;

import java.util.Base64;
import java.util.List;
import javax.ws.rs.core.HttpHeaders;

/**
 *
 * @author daca97002
 */
public class User {

    public static boolean authoricate(HttpHeaders httpHeaders) {
        try {
            List<String> authHeader = httpHeaders.getRequestHeader(HttpHeaders.AUTHORIZATION);
            String header = authHeader.get(0);
            header = header.substring(header.indexOf(" ") + 1);
            byte[] decoded = Base64.getDecoder().decode(header);
            String userPass = new String(decoded);
            System.out.println(userPass);
            //plocka ut anv och lösenord
            String username = userPass.substring(0, userPass.indexOf(":"));
            String password = userPass.substring(userPass.indexOf(":") + 1);
            System.out.println(username);
            System.out.println(password);
            //temp för "test"
            if (username.equals("David") && password.equals("Carlsson")) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
