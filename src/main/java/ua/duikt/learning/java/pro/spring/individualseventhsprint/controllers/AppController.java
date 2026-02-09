package ua.duikt.learning.java.pro.spring.individualseventhsprint.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mykyta Sirobaba on 20.01.2026.
 * email mykyta.sirobaba@gmail.com
 */
@RestController
public class AppController {

    @GetMapping("/public/info")
    public String publicInfo() {
        return "This is public info available to everyone.";
    }

    @GetMapping("/private/data")
    public String privateData() {
        return "This is private data for authenticated users.";
    }

    @GetMapping("/admin/settings")
    public String adminSettings() {
        return "This is sensitive admin settings.";
    }
}
