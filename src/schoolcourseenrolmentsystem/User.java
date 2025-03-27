
package schoolcourseenrolmentsystem;

public abstract class User {
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
            throw new IllegalArgumentException("Name cannot be null or empty.");
        } else {
            this.name = name;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty.");
        } else {
            this.id = id;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        } else {
            this.email = email;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() < 10) {
            throw new IllegalArgumentException("Length must be >10.");
        } else {
            this.phoneNumber = phoneNumber;
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("Role can't be null or empty.");
        } else {
            this.role = role;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address can't be null or empty.");
        } else {
            this.address = address;
        }
    }

    // Methods
    public abstract void login();

    public abstract void logout();
}
