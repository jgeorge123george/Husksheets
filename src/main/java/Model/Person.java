package Model;

public class Person {
  private int personId;
  private String username;
  private String password;

  // Getters and setters
  public int getPersonId() {
    return personId;
  }

  public void setPersonId(int personId) {
    this.personId = personId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "Person{" +
            "personId=" + personId +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}

