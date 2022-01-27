package entity;

import javax.persistence.*;

@Entity
public class Incident {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String serviceName;

    @Column
    private boolean isActive;

    @Column
    private String problemDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Incident() {
    }

    public Incident(int id, String serviceName, boolean isActive, String problemDescription, User user) {
        this.id = id;
        this.serviceName = serviceName;
        this.isActive = isActive;
        this.problemDescription = problemDescription;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", isActive=" + isActive +
                ", problemDescription='" + problemDescription + '\'' +
                '}';
    }
}
