package site.easy.to.build.crm.service.budget;

import site.easy.to.build.crm.entity.MontantAvailablePerCustomer;

import java.util.List;

public interface MontantAvailablePerCustomerService {
    public List<MontantAvailablePerCustomer> findAll();

    public MontantAvailablePerCustomer findById(int id);

    public MontantAvailablePerCustomer findByUsername(String username);
}