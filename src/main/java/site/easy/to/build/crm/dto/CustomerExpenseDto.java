package site.easy.to.build.crm.dto;

import java.math.BigDecimal;

public record CustomerExpenseDto(String customerName, BigDecimal totalExpense) {}