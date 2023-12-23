package SerializeAndDeserializePojoClass;

public class UserDetails
{
  private String name;
  private String username;
  private String emailid;

  private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public UserDetails(String name, String username, String emailid, Address address) {
        this.name = name;
        this.username = username;
        this.emailid = emailid;
        this.address = address;
    }
}
