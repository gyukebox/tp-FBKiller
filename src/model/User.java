package model;

public class User {
    private String id;
    private String username;
    private String password;
    private String profilePicture;
    private String phoneNumber;
    private char gender;

    public User(String id, String username, String password, String profilePicture, String phoneNumber, char gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
