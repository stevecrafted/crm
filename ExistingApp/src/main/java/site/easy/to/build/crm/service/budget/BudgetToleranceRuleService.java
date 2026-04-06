package site.easy.to.build.crm.service.budget;

import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.MontantAvailablePerCustomer;
import site.easy.to.build.crm.entity.Parametre;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.parametre.ParametreService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BudgetToleranceRuleService {

    private static final String TOLERANCE_PARAM_NAME = "depense_tolerance";

    private final MontantAvailablePerCustomerService montantService;
    private final CustomerService customerService;
    private final ParametreService parametreService;

    public BudgetToleranceRuleService(MontantAvailablePerCustomerService montantService,
            CustomerService customerService,
            ParametreService parametreService) {
        this.montantService = montantService;
        this.customerService = customerService;
        this.parametreService = parametreService;
    }

    public boolean isCurrentBudgetBelowTolerance(int customerLoginInfoId) {
        return isBelowTolerance(customerLoginInfoId, BigDecimal.ZERO);
    }

    public boolean canApplyExpenseDelta(int customerLoginInfoId, BigDecimal expenseDelta) {
        return !isBelowTolerance(customerLoginInfoId, normalize(expenseDelta));
    }

    public boolean isToleranceExceededAfterDelta(int customerLoginInfoId, BigDecimal expenseDelta) {
        try {
            return isBelowTolerance(customerLoginInfoId, normalize(expenseDelta));
        } catch (Exception e) {
            System.out.println("Impossible de verifier le seuil de tolerance: " + e.getMessage());
            return false;
        }
    }

    public BigDecimal getToleranceLimit() {
        Parametre toleranceParam = parametreService.findByNom(TOLERANCE_PARAM_NAME);
        return toleranceParam == null ? null : toleranceParam.getValeur();
    }

    public void validateExpenseDeltaOrThrow(int customerLoginInfoId, BigDecimal expenseDelta) {
        try {
            Integer customerId = resolveCustomerIdFromProfileId(customerLoginInfoId);
            if (customerId == null) {
                throw new BudgetToleranceViolationException("Customer introuvable pour profile_id=" + customerLoginInfoId);
            }
            MontantAvailablePerCustomer budgetState = montantService.findById(customerId);
            if (budgetState == null) {
                System.out.println("customer id : " + customerId);
                throw new BudgetToleranceViolationException("Budget introuvable pour le customer.");
            }
            BigDecimal totalBudget = normalize(budgetState.getTotalBudget());
            System.out.println("Montant budget" + totalBudget);
            if (totalBudget.compareTo(BigDecimal.ZERO) <= 0) {
                throw new BudgetToleranceViolationException("Budget total nul ou invalide pour le customer. Budget total : " + totalBudget);
            }

            BigDecimal currentRemaining = normalize(budgetState.getReste());
            BigDecimal projectedRemaining = currentRemaining.subtract(normalize(expenseDelta));
            System.out.println("reste sisa : " + currentRemaining + " projet Sisa raha analana reste : " + currentRemaining + " - " + expenseDelta + " = " + projectedRemaining);
            if (projectedRemaining.compareTo(BigDecimal.ZERO) < 0) {
                throw new BudgetToleranceViolationException("Le budget restant est insuffisant pour cette dépense.");
            }
        } catch (BudgetToleranceViolationException e) {
            throw e;
        } catch (Exception e) {
            throw new BudgetToleranceViolationException(
                    "Impossible de vérifier la tolérance budget: " + e.getMessage(),
                    e
            );
        }
    }

    private boolean isBelowTolerance(int customerLoginInfoId, BigDecimal expenseDelta) {
        Integer customerId = resolveCustomerIdFromProfileId(customerLoginInfoId);
        if (customerId == null) {
            System.out.println("Customer introuvable pour profile_id=" + customerLoginInfoId);
            return false;
        }

        MontantAvailablePerCustomer budgetState = montantService.findById(customerId);
        if (budgetState == null) {
            System.out.println("Budget introuvable pour customerId=" + customerId);
            return false;
        }

        BigDecimal totalBudget = normalize(budgetState.getTotalBudget());
        if (totalBudget.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Budget total nul ou invalide pour customerId=" + customerId);
            return false;
        }

        Parametre toleranceParam = parametreService.findByNom(TOLERANCE_PARAM_NAME);
        if (toleranceParam == null || toleranceParam.getValeur() == null) {
            System.out.println("Parametre manquant: " + TOLERANCE_PARAM_NAME);
            return false;
        }

        BigDecimal toleranceLimit = toleranceParam.getValeur();
        BigDecimal projectedRemaining = normalize(budgetState.getReste()).subtract(expenseDelta);
        BigDecimal remainingPercentage = projectedRemaining
                .multiply(new BigDecimal("100"))
                .divide(totalBudget, 2, RoundingMode.HALF_UP);

        return remainingPercentage.compareTo(toleranceLimit) < 0;
    }

    private BigDecimal normalize(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

    private Integer resolveCustomerIdFromProfileId(int profileId) {
        Customer customer = customerService.findByProfileId(profileId);
        return customer == null ? null : customer.getCustomerId();
    }
}
