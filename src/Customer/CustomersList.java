package Customer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CustomersList {
    public List<Customer> customers = new LinkedList<Customer>();

    public void addCustomer(String address, float salary, CustomerSpec customerSpec) {
        customers.add(new Customer(address, salary, customerSpec));
    }

    public List<Customer> search(CustomerSpec searchSpec) {
        List<Customer> matchingCustomers = new LinkedList<Customer>();
        for (Customer customer : customers)
            if (customer.getSpec().matches(searchSpec))
                matchingCustomers.add(customer);

        return matchingCustomers;
    }

    public Customer FindById(int customerId) {
        for (Customer customer : customers)
            if (customer.getCustomerId() == customerId)
                return customer;
        return null;
    }

    public void editCustomer(Customer customer, Map<String, String> newValues) {
        for (String key : newValues.keySet()) {
            CustomerSpec spec = customer.getSpec();

            if (newValues.get(key) == null)
                continue;

            switch (key) {
                case "address":
                    customer.setAddress(newValues.get(key));
                    break;
                case "salary":
                    customer.setSalary(Float.parseFloat(newValues.get(key)));
                    break;
                case "firstName":
                    spec.replaceProperty("firstName", newValues);
                    break;
                case "lastName":
                    spec.replaceProperty("lastName", newValues);
                    break;
                case "email":
                    spec.replaceProperty("email", newValues);
                    break;
                case "nationalId":
                    spec.replaceProperty("nationalId", newValues);
                    break;
                case "gender":
                    spec.replaceProperty("gender", newValues);
                    break;
                case "city":
                    spec.replaceProperty("city", newValues);
                    break;
                case "country":
                    spec.replaceProperty("country", newValues);
                    break;
            }
        }
    }
}
