package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    //@Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformatonEmail() {
        long size = taskRepository.count();
        // long size = (long)20;

        String appendix = "task";
        if (size > 1) {
            appendix.join("s");
        }

        simpleEmailService.send(new Mail(
                adminConfig.getAdminmail(),
                SUBJECT,
                "Currently in database you got: " + size + appendix)
        );
    }
}
