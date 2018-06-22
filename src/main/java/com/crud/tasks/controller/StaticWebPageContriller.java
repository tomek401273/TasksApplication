package com.crud.tasks.controller;

import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageContriller {
    @Autowired
    private CompanyConfig companyConfig;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("variable", "My Thyemleaf variable");
        model.put("one", 1);
        model.put("two", 2);
        model.put("companyConfig",companyConfig);
        return "index";
    }
}
