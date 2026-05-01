package site.easy.to.build.crm.service.customer;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import site.easy.to.build.crm.dto.CustomerExpenseDto;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findByCustomerId(int customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    @Override
    public Customer findByProfileId(int profileId) {
        return customerRepository.findByCustomerLoginInfo_Id(profileId);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public List<Customer> findByUserId(int userId) {
        return customerRepository.findByUserId(userId);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public List<Customer> getRecentCustomers(int userId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return customerRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
    }

    @Override
    public long countByUserId(int userId) {
        return customerRepository.countByUserId(userId);
    }
 
    @Override
    public List<CustomerExpenseDto> getTopSpenders(int top) {
        return customerRepository.findTopSpendingCustomers(top)
                .stream()
                .map(row -> {
                    String customerName = row[0] != null ? row[0].toString() : null;
                    BigDecimal totalExpense = row[1] != null
                            ? new BigDecimal(row[1].toString())
                            : BigDecimal.ZERO;
                    return new CustomerExpenseDto(customerName, totalExpense);
                })
                .toList();
    }
}
