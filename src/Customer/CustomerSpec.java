package Customer;

import java.util.HashMap;
import java.util.Map;

public class CustomerSpec {
    private HashMap<String, String> properties = new HashMap<>();

    public CustomerSpec(String firstName, String lastName, String email, String nationalId, Gender gender, City city, Country country) {
        this.properties.put("firstName", firstName);
        this.properties.put("lastName", lastName);
        this.properties.put("email", email);
        this.properties.put("nationalId", nationalId);
        this.properties.put("gender", gender != null ? gender.toString() : null);
        this.properties.put("city", city != null ? city.toString() : null);
        this.properties.put("country", country != null ? country.toString() : null);
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public boolean matches(CustomerSpec otherSpec) {
        for (String propertyName : this.getProperties().keySet()) {
            if (otherSpec.getProperties().get(propertyName) != null) {
                if (!properties.get(propertyName).equals(otherSpec.getProperties().get(propertyName)))
                    return false;
            }
        }
        return true;
    }

    public void replaceProperty(String key, Map<String, String> newValues) {
        this.getProperties().replace(key, newValues.get(key));
    }
}
