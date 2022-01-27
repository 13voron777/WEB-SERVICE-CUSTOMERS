import entity.*;
import helpers.*;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        insertData();

        String userName = "";
        String password = "";
        System.out.print("Enter username: ");
        userName = scanner.next();
        System.out.print("Enter password: ");
        password = scanner.next();

        System.out.println("Success! Hello, user " + userName + "!");
        /*int choose = 1;
        while (true) {
            if (choose == 0) {
                break;
            }
        }*/


        /*UserHelper uh = new UserHelper();
        IncidentHelper ih = new IncidentHelper();
        ProfileHelper ph = new ProfileHelper();
        ServiceHelper sh = new ServiceHelper();*/
        //Profile profile = new Profile(1, "test", "testov", "test@test.com", "+48777777777", "77777");
        //Incident incident = new Incident(1, "Test", false, "Test", user);
        //Service service = new Service(1, "help", true, 12.5, 5);
    }

    public static void inputForAdmins() {

    }

    public static void inputForUser() {

    }

    public static void insertData() {
        UserRoleHelper ush = new UserRoleHelper();
        UserRole user = new UserRole(1, "USER", "An ordinary user");
        UserRole admin = new UserRole(2, "ADMIN", "Administrator");
        UserRole superAdmin = new UserRole(3, "SUPER_ADMIN", "Super administrator, owner");

        ush.addUserRole(user);
        ush.addUserRole(admin);
        ush.addUserRole(superAdmin);
    }
}
