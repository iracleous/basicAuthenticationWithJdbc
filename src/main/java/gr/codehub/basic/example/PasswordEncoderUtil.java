package gr.codehub.basic.example;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode("admin@123");//bob's password
        System.out.println(encoded);
        encoded = encoder.encode("admin@123");//sara's password
        System.out.println(encoded);
    }
}
