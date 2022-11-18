package be.avivaCode.MyNatureNotebookWorldwide.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    //TODO REMOVE THIS FROM END PRODUCT! Only exists to help access encrypted pws of dummy data
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("123");

        System.out.println(encodedPassword);
    }
}
