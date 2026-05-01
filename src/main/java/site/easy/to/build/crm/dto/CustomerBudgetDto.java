package site.easy.to.build.crm.dto;

import java.math.BigDecimal;

public record CustomerBudgetDto(String customerName, BigDecimal totalExpense) {}