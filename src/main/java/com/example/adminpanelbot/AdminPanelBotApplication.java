package com.example.adminpanelbot;

import com.example.adminpanelbot.model.Node;
import com.example.adminpanelbot.services.NodeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class AdminPanelBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminPanelBotApplication.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean(NodeService nodeService) {
        return (args) -> {
            //Установка главного меню (корень иерархического меню) если его еще нет (при первом запуске)
            nodeService.createRootNode();
        };
    }
}
