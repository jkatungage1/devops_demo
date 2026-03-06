package org.example.devops_demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class DashboardController {

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("appName", "DevOps Demo");
        model.addAttribute("lastUpdated",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")));

        model.addAttribute("totalBuilds", 142);
        model.addAttribute("successBuilds", 128);
        model.addAttribute("failedBuilds", 14);
        model.addAttribute("deployments", 37);
        model.addAttribute("successRate", 90);

        model.addAttribute("recentActivity", List.of(
                new ActivityItem("Build #142", "SUCCESS", "main", "2 min ago"),
                new ActivityItem("Deploy to staging", "SUCCESS", "staging", "15 min ago"),
                new ActivityItem("Build #141", "FAILED", "feature/login", "1 hour ago"),
                new ActivityItem("Deploy to prod", "SUCCESS", "main", "3 hours ago"),
                new ActivityItem("Build #140", "SUCCESS", "main", "5 hours ago")
        ));

        return "dashboard";
    }

    public record ActivityItem(String name, String status, String branch, String time) {}
}
