package Customer;

public class CustomerSpec {
    private String firstName;
    private String lastName;
    private String email;
    private String nationalId;
    private Gender gender;
    private City city;
    private Country country;

    public CustomerSpec(String firstName, String lastName, String email, String nationalId, Gender gender, City city, Country country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationalId = nationalId;
        this.gender = gender;
        this.city = city;
        this.country = country;
    }

    public boolean matches(CustomerSpec otherCustomer) {
        return true;
    }
}
