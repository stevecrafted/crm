package site.easy.to.build.crm.controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.CustomerLoginInfo;
import site.easy.to.build.crm.entity.settings.ContractEmailSettings;
import site.easy.to.build.crm.entity.settings.EmailSettings;
import site.easy.to.build.crm.entity.settings.LeadEmailSettings;
import site.easy.to.build.crm.entity.settings.TicketEmailSettings;
import site.easy.to.build.crm.service.customer.CustomerLoginInfoService;
import site.easy.to.build.crm.service.settings.ContractEmailSettingsService;
import site.easy.to.build.crm.service.settings.LeadEmailSettingsService;
import site.easy.to.build.crm.service.settings.TicketEmailSettingsService;
import site.easy.to.build.crm.util.AuthenticationUtils;
import site.easy.to.build.crm.util.DatabaseUtil;
import site.easy.to.build.crm.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@RestController
@RequestMapping("/database/info_connection")
public class DatabaseController {
    
    public static class DatabaseInfoDto {
        String urlDatabase;
        String username;
        String password;

        public DatabaseInfoDto(String url, String username, String password) {
            this.urlDatabase = url;
            this.username = username;
            this.password = password;
        }

        public String getUrlDatabase() {return urlDatabase;};
        public String getUsername() {return username;}
        public String getPassword() {return password;}
 
    }

    @GetMapping("/")
    public DatabaseInfoDto showDatabaseInfo() {
        return new DatabaseInfoDto("jdbc:mysql://localhost:3306/crm?createDatabaseIfNotExist=true", "root", "steve");
    }
    
}
