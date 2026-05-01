package site.easy.to.build.crm.dto;

import java.util.List;

public class frontDashboardDto {
    private long clientCount;
    private long ticketCount;
    private long leadCount;
    private List<CustomerExpenseDto> listCustomerExpenseDtos;
    private List<CustomerBudgetDto> ListCustomerBudgetDtos;
    private List<CustomerContratDto> listCustomerContratDtos;

    // Constructeur sans argument
    public frontDashboardDto() {
    }

    // Constructeur avec tous les champs
    public frontDashboardDto(long clientCount, long ticketCount, long leadCount,
                             List<CustomerExpenseDto> listCustomerExpenseDtos,
                             List<CustomerBudgetDto> ListCustomerBudgetDtos,
                             List<CustomerContratDto> listCustomerContratDtos) {
        this.clientCount = clientCount;
        this.ticketCount = ticketCount;
        this.leadCount = leadCount;
        this.listCustomerExpenseDtos = listCustomerExpenseDtos;
        this.ListCustomerBudgetDtos = ListCustomerBudgetDtos;
        this.listCustomerContratDtos = listCustomerContratDtos;
    }

    // Getters et Setters
    public long getClientCount() {
        return clientCount;
    }

    public void setClientCount(long clientCount) {
        this.clientCount = clientCount;
    }

    public long getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(long ticketCount) {
        this.ticketCount = ticketCount;
    }

    public long getLeadCount() {
        return leadCount;
    }

    public void setLeadCount(long leadCount) {
        this.leadCount = leadCount;
    }

    public List<CustomerExpenseDto> getlistCustomerExpenseDtos() {
        return listCustomerExpenseDtos;
    }

    public void setlistCustomerExpenseDtos(List<CustomerExpenseDto> listCustomerExpenseDtos) {
        this.listCustomerExpenseDtos = listCustomerExpenseDtos;
    }

    public List<CustomerBudgetDto> getListCustomerBudgetDtos() {
        return ListCustomerBudgetDtos;
    }

    public void setListCustomerBudgetDtos(List<CustomerBudgetDto> ListCustomerBudgetDtos) {
        this.ListCustomerBudgetDtos = ListCustomerBudgetDtos;
    }

    public List<CustomerContratDto> getListCustomerContratDtos() {
        return listCustomerContratDtos;
    }

    public void setListCustomerContratDtos(List<CustomerContratDto> listCustomerContratDtos) {
        this.listCustomerContratDtos = listCustomerContratDtos;
    }
}