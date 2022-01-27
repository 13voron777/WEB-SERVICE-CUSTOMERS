package entity;

import javax.persistence.*;

@Entity
public class UserRole {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String role_name;

    @Column
    private String role_description;

    @OneToOne(mappedBy = "userRole", fetch = FetchType.EAGER)
    private User user;

    public UserRole() {
    }

    public UserRole(int id, String role_name, String role_description) {
        this.id = id;
        this.role_name = role_name;
        this.role_description = role_description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_description() {
        return role_description;
    }

    public void setRole_description(String role_description) {
        this.role_description = role_description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                ", role_description='" + role_description + '\'' +
                '}';
    }
}
