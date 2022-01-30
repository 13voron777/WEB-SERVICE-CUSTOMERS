package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String serviceName;

    @Column
    private boolean isActive;

    @Column
    private double serviceMonthPrice;

    @Column
    private int customerId;

    @ManyToMany(mappedBy = "services")
    private List<User> users = new ArrayList<>();

    public Service() {
    }

    public Service(String serviceName, boolean isActive, double serviceMonthPrice, int customerId) {
        this.serviceName = serviceName;
        this.isActive = isActive;
        this.serviceMonthPrice = serviceMonthPrice;
        this.customerId = customerId;
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

    public double getServiceMonthPrice() {
        return serviceMonthPrice;
    }

    public void setServiceMonthPrice(double serviceMonthPrice) {
        this.serviceMonthPrice = serviceMonthPrice;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", isActive=" + isActive +
                ", serviceMonthPrice=" + serviceMonthPrice +
                ", customerId=" + customerId +
                '}';
    }
}
