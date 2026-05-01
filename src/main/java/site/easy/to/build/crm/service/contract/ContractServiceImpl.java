package site.easy.to.build.crm.service.contract;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import site.easy.to.build.crm.dto.CustomerBudgetDto; 
import site.easy.to.build.crm.dto.CustomerContratDto;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Contract;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.repository.ContractRepository;
import site.easy.to.build.crm.repository.CustomerRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;

    public ContractServiceImpl(ContractRepository contractRepository, CustomerRepository customerRepository) {
        this.contractRepository = contractRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Contract findByContractId(int id) {
        return contractRepository.findByContractId(id);
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public List<Contract> getCustomerContracts(int customerId) {
        return contractRepository.findByCustomerCustomerId(customerId);
    }

    @Override
    public List<Contract> getEmployeeCreatedContracts(int userId) {
        return contractRepository.findByUserId(userId);
    }

    @Override
    public Contract save(Contract contract) {
        contractRepository.save(contract);
        return contract;
    }

    @Override
    public void delete(Contract contract) {
        contractRepository.delete(contract);
    }

    @Override
    public List<Contract> getRecentContracts(int userId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return contractRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable);
    }

    @Override
    public List<Contract> getRecentCustomerContracts(int customerId, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return contractRepository.findByCustomerCustomerIdOrderByCreatedAtDesc(customerId, pageable);
    }

    @Override
    public long countByCustomerId(int customerId) {
        return contractRepository.countByCustomerCustomerId(customerId);
    }

    @Override
    public long countByUserId(int userId) {
        return contractRepository.countByUserId(userId);
    }

    @Override
    public void deleteAllByCustomer(Customer customer) {
        contractRepository.deleteAllByCustomer(customer);
    }

    @Override
    public long countAll() {
        return this.findAll().size();
    }

    @Override
    public List<CustomerContratDto> getListCustomerContratDto() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerContratDto> listCustomerContratDtos = new ArrayList<>();

        for (Customer customer : customers) {
            List<Contract> CustomerContract = contractRepository
                    .findByCustomerCustomerId(customer.getCustomerId());
            BigDecimal customerSumContract = CustomerContract.stream()
                    .map(Contract::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            CustomerContratDto customerContratDto = new CustomerContratDto(customer.getName(), customerSumContract);
            listCustomerContratDtos.add(customerContratDto);
        }

        return listCustomerContratDtos;
    }

}
