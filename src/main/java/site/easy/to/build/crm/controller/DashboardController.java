package site.easy.to.build.crm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.cron.ContractExpirationChecker;
import site.easy.to.build.crm.dto.CustomerBudgetDto;
import site.easy.to.build.crm.dto.CustomerContratDto;
import site.easy.to.build.crm.dto.CustomerExpenseDto;
import site.easy.to.build.crm.dto.ListTicketDto;
import site.easy.to.build.crm.dto.frontDashboardDto;
import site.easy.to.build.crm.entity.*;
import site.easy.to.build.crm.entity.settings.ContractEmailSettings;
import site.easy.to.build.crm.google.model.drive.GoogleDriveFolder;
import site.easy.to.build.crm.google.model.gmail.Attachment;
import site.easy.to.build.crm.google.service.acess.GoogleAccessService;
import site.easy.to.build.crm.google.service.drive.GoogleDriveApiService;
import site.easy.to.build.crm.google.service.gmail.GoogleGmailApiService;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.contract.ContractService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.drive.GoogleDriveFileService;
import site.easy.to.build.crm.service.file.FileService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.settings.ContractEmailSettingsService;
import site.easy.to.build.crm.service.ticket.TicketService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final ContractService contractService;

    private final AuthenticationUtils authenticationUtils;

    private final UserService userService;

    private final BudgetService budgetService;
    private final CustomerService customerService;
    private final TicketService ticketService;
    private final LeadService leadService;

    private final GoogleDriveApiService googleDriveApiService;
    private final FileUtil fileUtil;
    private final FileService fileService;
    private final GoogleDriveFileService googleDriveFileService;
    private final ContractExpirationChecker contractExpirationChecker;
    private final EntityManager entityManager;
    private final ContractEmailSettingsService contractEmailSettingsService;
    private final GoogleGmailApiService googleGmailApiService;

    @Autowired
    public DashboardController(ContractService contractService, AuthenticationUtils authenticationUtils,
            UserService userService, TicketService ticketService, BudgetService budgetService,
            CustomerService customerService, LeadService leadService, GoogleDriveApiService googleDriveApiService,
            FileUtil fileUtil, FileService fileService, GoogleDriveFileService googleDriveFileService,
            ContractExpirationChecker contractExpirationChecker, EntityManager entityManager,
            ContractEmailSettingsService contractEmailSettingsService,
            GoogleGmailApiService googleGmailApiService) {
        this.ticketService = ticketService;
        this.contractService = contractService;
        this.authenticationUtils = authenticationUtils;
        this.userService = userService;
        this.customerService = customerService;
        this.leadService = leadService;
        this.googleDriveApiService = googleDriveApiService;
        this.fileUtil = fileUtil;
        this.fileService = fileService;
        this.googleDriveFileService = googleDriveFileService;
        this.contractExpirationChecker = contractExpirationChecker;
        this.entityManager = entityManager;
        this.contractEmailSettingsService = contractEmailSettingsService;
        this.googleGmailApiService = googleGmailApiService;
        this.budgetService = budgetService;
    }

    @GetMapping("/")
    public frontDashboardDto dashboard(Model model) {
        long clientCount = userService.countAllUsers();
        long ticketCount = ticketService.countAll();
        long leadCount = leadService.countAll();

        List<CustomerExpenseDto> listCustomerExpenseDtos = customerService.getTopSpenders(5);
        List<CustomerBudgetDto> ListCustomerBudgetDto = budgetService.getListCustomerBudgetDto();
        List<CustomerContratDto> listCustomerContratDtos = contractService.getListCustomerContratDto();

        return new frontDashboardDto(clientCount, ticketCount, leadCount, listCustomerExpenseDtos,
                ListCustomerBudgetDto, listCustomerContratDtos);
    }

    @PostMapping("/update_tickets")
    public List<Ticket> UpdateTicketDto(@RequestParam(defaultValue = "5") int top) {
        return ticketService.getRecenTickets(top);
    }

    @GetMapping("/tickets")
    public List<Ticket> ListTicketDto(@RequestParam(defaultValue = "5") int top) {
        return ticketService.getRecenTickets(top);
    }

    @GetMapping("/leads")
    public List<Lead> ListLeadDto(@RequestParam(defaultValue = "5") int top) {
        return leadService.getRecenLeads(top);
    }

    @GetMapping("/customers/top-spenders")
    public List<CustomerExpenseDto> getTopSpenders(@RequestParam(defaultValue = "5") int top) {
        return customerService.getTopSpenders(top);
    }

}