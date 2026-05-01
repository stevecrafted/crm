package site.easy.to.build.crm.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
    public Budget findById(int id);

    @Query("SELECT COALESCE(SUM(b.amount), 0) from Budget b")
    public BigDecimal getSumAmount();

    public List<Budget>  findByCustomerLoginInfoCustomerCustomerId(Integer customerId);

}