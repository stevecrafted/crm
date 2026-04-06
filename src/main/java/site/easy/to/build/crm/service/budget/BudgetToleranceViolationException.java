package site.easy.to.build.crm.service.budget;

public class BudgetToleranceViolationException extends RuntimeException {

    public BudgetToleranceViolationException(String message) {
        super(message);
    }

    public BudgetToleranceViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}