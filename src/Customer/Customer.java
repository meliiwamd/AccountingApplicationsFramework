package Customer;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private int customerId;
    private String address;
    private float salary;
    private CustomerSpec spec;

    public Customer(String address, float salary, CustomerSpec customerSpec) {
        this.customerId = counter.incrementAndGet();
        this.address = address;
        this.salary = salary;
        this.spec = customerSpec;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getSalary() {
        return salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerSpec getSpec() {
        return spec;
    }
}
