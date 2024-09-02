package views;

import dao.UserDAO;
import model.User;
import services.GenerateOTP;
import services.SendGeneratedOTP;
import services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {

    public void welcomeScreen() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("welcome to the app");
        System.out.println("Press 1 to login\nPress 2 to signUp \nPress 0 to Exit");
        int choice = 0;
        try {

            choice = Integer.parseInt(br.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        switch (choice) {
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
        }

    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Email : ");
        String email = sc.nextLine();
        try {
            if (UserDAO.isExists(email)) {
                String genOtp = GenerateOTP.getOTP();
                System.out.println(genOtp);
                SendGeneratedOTP.sendOtp( email , genOtp);
                System.out.println("Enter the otp");
                String otp = sc.nextLine();
                if (otp.equals(genOtp)) {
                    String name = null;
                    try{
                       name =  UserDAO.fetchUserName(email);
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }



                    new UserView(name, email).home();
                } else {
                    System.out.println("Wrong OTP...");
                }

            } else {
                System.out.println("User Not Found...");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Name : ");
        String name = sc.nextLine();
        System.out.print("Enter Email : ");
        String email = sc.nextLine();
        String genOtp = GenerateOTP.getOTP();
        System.out.println(genOtp);
        System.out.println("Enter the otp");
        String otp = sc.nextLine();
        if (otp.equals(genOtp)) {
            User user = new User(name, email);
            int response = UserService.saveUser(user);
            switch (response) {
                case 0 -> System.out.println("User already exists...");
                case 1 -> System.out.println("User Registered...");

            }
        } else {
            System.out.println("Wrong OTP...");
        }


    }

}
