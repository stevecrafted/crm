package site.easy.to.build.crm.service.budget;

import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.MontantAvailablePerCustomer;
import site.easy.to.build.crm.repository.MontantAvailablePerCustomerRepository;

import java.util.List;

@Service
public class MontantAvailablePerCustomerServiceImpl implements MontantAvailablePerCustomerService {

    private final MontantAvailablePerCustomerRepository montantAvailablePerCustomerRepository;

    public MontantAvailablePerCustomerServiceImpl(MontantAvailablePerCustomerRepository montantAvailablePerCustomerRepository) {
        this.montantAvailablePerCustomerRepository = montantAvailablePerCustomerRepository;
    }

    @Override
    public List<MontantAvailablePerCustomer> findAll() {
        return montantAvailablePerCustomerRepository.findAll();
    }

    @Override
    public MontantAvailablePerCustomer findById(int id) {
        return montantAvailablePerCustomerRepository.findById(id);
    }

    @Override
    public MontantAvailablePerCustomer findByUsername(String username) {
        return montantAvailablePerCustomerRepository.findByUsername(username);
    }
}