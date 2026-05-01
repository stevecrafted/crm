package site.easy.to.build.crm.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findByCustomerId(int customerId);

    public Customer findByCustomerLoginInfo_Id(int profileId);

    public List<Customer> findByUserId(int userId);

    public Customer findByEmail(String email);

    public List<Customer> findAll();

    public List<Customer> findByUserIdOrderByCreatedAtDesc(int userId, Pageable pageable);

    long countByUserId(int userId);

    @Query(value = """
                SELECT c.name AS customerName,
                       COALESCE(leadSum, 0) + COALESCE(ticketSum, 0) AS totalExpense
                FROM customer c
                LEFT JOIN (SELECT customer_id, SUM(expense) AS leadSum FROM trigger_lead GROUP BY customer_id) l
                    ON l.customer_id = c.customer_id
                LEFT JOIN (SELECT customer_id, SUM(expense) AS ticketSum FROM trigger_ticket GROUP BY customer_id) t
                    ON t.customer_id = c.customer_id
                ORDER BY totalExpense DESC
                LIMIT :top
            """, nativeQuery = true)
    List<Object[]> findTopSpendingCustomers(@Param("top") int top);
}
