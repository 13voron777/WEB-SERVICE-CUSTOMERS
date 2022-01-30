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
        UserHelper uh = new UserHelper();

        if (uh.getAllUsers().size() == 0) {
            insertData();
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
            case "ADMIN", "SUPER_ADMIN" -> inputForAdmins(user);
            case "USER" -> inputForUsers(user);
        }
    }

    public static void insertData() {
        UserRoleHelper ush = new UserRoleHelper();
        UserHelper uh = new UserHelper();
        ProfileHelper ph = new ProfileHelper();
        ServiceHelper sh = new ServiceHelper();

        UserRole user = new UserRole("USER", "An ordinary user");
        UserRole admin = new UserRole("ADMIN", "Administrator");
        UserRole superAdmin = new UserRole("SUPER_ADMIN", "Super administrator, owner");
        Profile profile1 = new Profile("supadmin", "superable", "supadmin@mail.com", "+380555555555", "423243124");
        Profile profile2 = new Profile("admin", "adminable", "admin@mail.com", "+49777777666", "32423421");
        User user1 = new User("supadmin", "supadmin", superAdmin, profile1);
        User user2 = new User("admin", "admin", admin, profile2);
        Service service1 = new Service("Repair computer", true, 12.5, 1);
        Service service2 = new Service("Repair Windows", true, 20.7, 2);

        ush.addUserRole(user);
        ush.addUserRole(admin);
        ush.addUserRole(superAdmin);
        ph.addProfile(profile1);
        ph.addProfile(profile2);
        uh.addUser(user1);
        uh.addUser(user2);
        sh.addService(service1);
        sh.addService(service2);
    }

    public static void inputForAdmins(User user) {
        IncidentHelper ih = new IncidentHelper();
        ProfileHelper ph = new ProfileHelper();
        UserHelper uh = new UserHelper();

        while (true) {
            System.out.println("\nQueries list:");
            for (String query : queries) {
                System.out.println(query);
            }
            System.out.println("0: exit");
            System.out.print("\nEnter query number: ");
            int queryNumber = scanner.nextInt();

            switch (queryNumber) {
                case 1:
                    createIncident(ih, user);
                    break;
                case 2:
                    System.out.print("Enter id service to subscribe: ");
                    int idServiceSub = scanner.nextInt();
                    uh.subscribeService(user.getId(), idServiceSub);
                    break;
                case 3:
                    System.out.print("Enter id service to unsubscribe: ");
                    int idServiceUnsub = scanner.nextInt();
                    uh.unsubscribeService(user.getId(), idServiceUnsub);
                    break;
                case 4:
                    List<User> users = uh.getAllUsers();
                    for (User elemUser : users) {
                        System.out.println(elemUser);
                    }
                    break;
                case 5:
                    List<Incident> incidents = ih.getAllIncidents();
                    for (Incident elemIncident : incidents) {
                        System.out.println(elemIncident);
                    }
                    break;
                case 6:
                    List<Incident> incidentsActive = ih.getAllActiveIncidents();
                    for (Incident elemIncident : incidentsActive) {
                        System.out.println(elemIncident);
                    }
                    break;
                case 7:
                    System.out.print("Enter id user to show: ");
                    int idUser = scanner.nextInt();
                    System.out.println(uh.getUserById(idUser));
                    break;
                case 8:
                    User newUser = new User();
                    Profile newProfile = new Profile();
                    fillProfile(newProfile);
                    fillUser(newUser, newProfile);
                    ph.addProfile(newProfile);
                    uh.addUser(newUser);
                    System.out.println("New user created!");
                    break;
                case 9:
                    System.out.print("Enter id user to update: ");
                    int idUpd = scanner.nextInt();
                    User updateUser = uh.getUserById(idUpd);
                    Profile updateProfile = ph.getProfileById(updateUser.getProfile().getId());
                    fillProfile(updateProfile);
                    fillUser(updateUser, updateProfile);
                    ph.updateProfile(updateProfile);
                    uh.updateUser(updateUser);
                    System.out.println("User data updated!");
                    break;
                case 10:
                    System.out.print("Enter id user to delete: ");
                    uh.deleteUserById(scanner.nextInt());
                    break;
                case 11:
                    System.out.println("Enter id incident to close: ");
                    int idIncident = scanner.nextInt();
                    ih.closeIncident(idIncident);
                    System.out.println("Incident " + idIncident + " closed!");
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

    public static void inputForUsers(User user) {
        IncidentHelper ih = new IncidentHelper();
        UserHelper uh = new UserHelper();

        while (true) {
            System.out.println("\nQueries list:");
            for (int i = 0; i < 3; i++) {
                System.out.println(queries.get(i));
            }
            System.out.println("0: exit");
            System.out.print("\nEnter query number: ");
            int queryNumber = scanner.nextInt();

            switch (queryNumber) {
                case 1:
                    createIncident(ih, user);
                    break;
                case 2:
                    System.out.print("Enter id service to subscribe: ");
                    int idServiceSub = scanner.nextInt();
                    uh.subscribeService(user.getId(), idServiceSub);
                    break;
                case 3:
                    System.out.print("Enter id service to unsubscribe: ");
                    int idServiceUnsub = scanner.nextInt();
                    uh.unsubscribeService(user.getId(), idServiceUnsub);
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

    public static void createIncident(IncidentHelper ih, User user) {
        Incident incident = new Incident();
        System.out.print("Enter the name of incident: ");
        incident.setServiceName(scanner.next());
        scanner.nextLine();
        System.out.print("Enter the description of the incident: ");
        incident.setProblemDescription(scanner.nextLine());
        incident.setActive(true);
        incident.setUser(user);
        ih.addIncident(incident);
        System.out.println("Incident created!");
    }

    public static void fillProfile(Profile profile) {
        System.out.print("Enter first name: ");
        profile.setFirstName(scanner.next());
        System.out.print("Enter lastName: ");
        profile.setLastName(scanner.next());
        System.out.print("Enter email: ");
        profile.setEmail(scanner.next());
        System.out.print("Enter phone number: ");
        profile.setPhoneNumber(scanner.next());
        System.out.print("Enter postal code: ");
        profile.setPostalCode(scanner.next());
    }

    public static void fillUser(User user, Profile profile) {
        System.out.print("Enter username: ");
        user.setUser_name(scanner.next());
        System.out.print("Enter password: ");
        user.setPassword(scanner.next());
        System.out.print("Choose user role (USER, ADMIN, SUPER_ADMIN): ");
        UserRoleHelper urh = new UserRoleHelper();
        user.setUserRole(urh.getUserRole(scanner.next()));
        user.setProfile(profile);
    }
}
