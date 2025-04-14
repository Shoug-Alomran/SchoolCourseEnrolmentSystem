
package schoolcourseenrolmentsystem;

import java.util.List;

public abstract class User<T> {
    // Attributes
    private String name, id, password, email, phoneNumber;
    private String role, address;

    // Constructor
    public User(String name, String id, String password, String email, String phoneNumber, String role,
            String address) {
        setName(name);
        setId(id);
        setPassword(password);
        setPhoneNumber(phoneNumber);
        setRole(role);
        setAddress(address);
    }

    // Setters & Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Name cannot be null or empty.");
            return;
        } 
            this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            System.out.println("ID cannot be null or empty.");
            return;
        } 
            this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            System.out.println("Email cannot be null or empty.");
            return;
        } 
            this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
       // research on how to ensure that phone numbers follow the KSA pattern
        if (phoneNumber.equals("temp")) {
        this.phoneNumber = phoneNumber;
        return;
        }
        if (phoneNumber.length() < 10) {
        System.out.println("Length must be > 10.");
        return;
        } else if (phoneNumber.length() > 10) {
        System.out.println("Length must be < 10.");
        return;
        }
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.isEmpty()) {
        System.out.println("Role can't be null or empty.");
        return;
        } else {
        this.role = role;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
        System.out.println("Address can't be null or empty.");
        return;
        }
        this.address = address;
    }

    // Methods
    public abstract T login(List<T> list, String id, String password);

    public abstract String logout(T user);
}
