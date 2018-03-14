package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can menanage your tasks");
        functionality.add("Previous connection with Trello Account");
        functionality.add("Application allow sending tasks to trello ");



        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://tomek401273.github.io/index.html");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config",adminConfig);
        context.setVariable("application_functionality", functionality);
        return  templateEngine.process("mail/created-trello-card-mail", context);
    }
}
