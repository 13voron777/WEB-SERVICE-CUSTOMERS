import entity.*;
import helpers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static List<String> queries = new ArrayList<>();

    static {
        queries.add("1: create incident");
        queries.add("2: subscribe service by id");
        queries.add("3: unsubscribe service by id");
        queries.add("4: fetch all users");
        queries.add("5: fetch all incidents");
        queries.add("6: fetch all active incidents");
        queries.add("7: fetch user by id");
        queries.add("8: add user");
        queries.add("9: update user by id");
        queries.add("10: delete user by id");
        queries.add("11: close incident");
    }

    public static void main(String[] args) {
        insertData();
        UserHelper uh = new UserHelper();

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
                System.out.println("Welcome!\n");
                break;
            }
            System.out.println("Wrong username or password! Try again!\n");
        }

        switch (user.getUserRole().getRole_name()) {
            case "ADMIN", "SUPER_ADMIN" -> inputForAdmins();
            case "USER" -> inputForUsers();
        }
    }

    public static void inputForAdmins() {
        while (true) {
            System.out.println("Queries list:");
            for (int i = 0; i < queries.size(); i++) {
                System.out.println(queries.get(i));
            }
            System.out.println("0: exit");
            System.out.print("\nEnter query number: ");
            int queryNumber = scanner.nextInt();
            switch (queryNumber) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    System.out.println("In develop");
                    break;
                case 0:
                    System.out.println("Quit ");
                    break;
                default:
                    System.out.println("Wrong input! Try again!");
                    break;
            }
            if (queryNumber == 0) {
                break;
            }
        }
    }

    public static void inputForUsers() {
        while (true) {
            System.out.println("Queries list:");
            for (int i = 0; i < 3; i++) {
                System.out.println(queries.get(i));
            }
            System.out.println("0: exit");
            System.out.print("\nEnter query number: ");
            int queryNumber = scanner.nextInt();
            switch (queryNumber) {
                case 1:
                case 2:
                case 3:
                    System.out.println("In develop");
                    break;
                default:
                    System.out.println("Wrong input! Try again!");
                    break;
            }
            if (queryNumber == 0) {
                break;
            }
        }
    }

    public static void insertData() {
        UserRoleHelper ush = new UserRoleHelper();
        UserHelper uh = new UserHelper();
        ProfileHelper ph = new ProfileHelper();

        UserRole user = new UserRole(1, "USER", "An ordinary user");
        UserRole admin = new UserRole(2, "ADMIN", "Administrator");
        UserRole superAdmin = new UserRole(3, "SUPER_ADMIN", "Super administrator, owner");
        Profile profile1 = new Profile(1, "supadmin", "superable", "supadmin@mail.com", "+380555555555", "423243124");
        Profile profile2 = new Profile(2, "admin", "adminable", "admin@mail.com", "+49777777666", "32423421");
        Profile profile3 = new Profile(3, "test", "testov", "test@mail.com", "+49777777777", "4361246");
        User user1 = new User(1, "supadmin", "supadmin", superAdmin, profile1);
        User user2 = new User(2, "admin", "admin", admin, profile2);
        User user3 = new User(3, "test", "test", user, profile3);

        ush.addUserRole(user);
        ush.addUserRole(admin);
        ush.addUserRole(superAdmin);
        ph.addProfile(profile1);
        ph.addProfile(profile2);
        ph.addProfile(profile3);
        uh.addUser(user1);
        uh.addUser(user2);
        uh.addUser(user3);


        /*IncidentHelper ih = new IncidentHelper();
        ServiceHelper sh = new ServiceHelper();*/
        //Incident incident = new Incident(1, "Test", false, "Test", user);
        //Service service = new Service(1, "help", true, 12.5, 5);
    }
}
