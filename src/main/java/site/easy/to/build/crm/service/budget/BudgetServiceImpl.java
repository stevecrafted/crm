package site.easy.to.build.crm.service.budget;

import org.springframework.stereotype.Service;

import site.easy.to.build.crm.dto.CustomerBudgetDto;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.repository.BudgetRepository;
import site.easy.to.build.crm.repository.CustomerRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository budgetRepository;
    private final CustomerRepository customerRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository, CustomerRepository customerRepository) {
        this.budgetRepository = budgetRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Budget findById(int id) {
        return budgetRepository.findById(id);
    }

    @Override
    public List<Budget> findAll() {
        return budgetRepository.findAll();
    }

    @Override
    public Budget save(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public void delete(Budget budget) {
        budgetRepository.delete(budget);
    }

    @Override
    public List<CustomerBudgetDto> getListCustomerBudgetDto() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerBudgetDto> listCustomerBudgetDtos = new ArrayList<>();

        for (Customer customer : customers) {
            List<Budget> CustomerBudget = budgetRepository.findByCustomerLoginInfoCustomerCustomerId(customer.getCustomerId());
            BigDecimal customerSumBudget = CustomerBudget.stream()
                .map(Budget::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            
            CustomerBudgetDto customerBudgetDto = new CustomerBudgetDto(customer.getName(), customerSumBudget);
            listCustomerBudgetDtos.add(customerBudgetDto);
        }

        return listCustomerBudgetDtos;
    }

}