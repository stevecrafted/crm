package site.easy.to.build.crm.service.customer;

import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import site.easy.to.build.crm.dto.CustomerExpenseDto;
import site.easy.to.build.crm.entity.Customer;

import java.util.List;

public interface CustomerService {

    public Customer findByCustomerId(int customerId);

    public Customer findByProfileId(int profileId);

    public List<Customer> findByUserId(int userId);

    public Customer findByEmail(String email);

    public List<Customer> findAll();

    public Customer save(Customer customer);

    public void delete(Customer customer);

    public List<Customer> getRecentCustomers(int userId, int limit);

    long countByUserId(int userId);

    public List<CustomerExpenseDto> getTopSpenders(int top);
}
