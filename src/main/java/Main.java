import entity.*;
import helpers.*;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UserHelper uh = new UserHelper();

        if (uh.getAllUsers().size() == 0) {
            WebService.insertData();
        }

        User user;
        String userName;
        String password;
        while (true) {
            System.out.print("Enter username: ");
            userName = scanner.next();
            System.out.print("Enter password: ");
            password = scanner.next();
            user = uh.checkUserPassword(userName, password);
            if (user != null) {
                System.out.println("Welcome!");
                break;
            }
            System.out.println("Wrong username or password! Try again!\n");
        }

        switch (user.getUserRole().getRole_name()) {
            case "ADMIN", "SUPER_ADMIN" -> WebService.inputForAdmins(user);
            case "USER" -> WebService.inputForUsers(user);
        }
    }
}
