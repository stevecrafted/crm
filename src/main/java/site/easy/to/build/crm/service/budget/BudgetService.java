package site.easy.to.build.crm.service.budget;

import site.easy.to.build.crm.dto.CustomerBudgetDto;
import site.easy.to.build.crm.entity.Budget;

import java.util.List;

public interface BudgetService {
    public Budget findById(int id);

    public List<Budget> findAll();

    public Budget save(Budget budget);

    public void delete(Budget budget);

    public List<CustomerBudgetDto> getListCustomerBudgetDto();
}