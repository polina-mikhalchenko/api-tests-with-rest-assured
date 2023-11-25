package model;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
    public static User.Builder newEntity() {return new User().new Builder();}
    public String getUsername() {return username;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public String getPhone() {return phone;}
    public int getUserStatus() {return  userStatus;}
    public class Builder {
        private Builder() {}
        public User.Builder withUsername (String username) {User.this.username = username; return this;}
        public User.Builder withFirstName (String firstName) {User.this.firstName = firstName; return this;}
        public User.Builder withLastName (String lastName) {User.this.lastName = lastName; return this;}
        public User.Builder withEmail (String email) {User.this.email = email; return this;}
        public User.Builder withPassword (String password) {User.this.password = password; return this;}
        public User.Builder withPhone (String phone) {User.this.phone = phone; return this;}
        public User.Builder withUserStatus (int userStatus) {User.this.userStatus = userStatus; return this;}
        public User build() {return User.this;}
    }
}
