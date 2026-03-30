package org.example.devops_demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DashboardController.class)
class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void rootRedirectsToDashboard() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"));
    }

    @Test
    void dashboardRouteReturns200() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"));
    }

    @Test
    void dashboardExposesAppName() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(model().attribute("appName", "DevOps Demo"));
    }

    @Test
    void dashboardExposesStats() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(model().attributeExists("totalBuilds"))
                .andExpect(model().attributeExists("successBuilds"))
                .andExpect(model().attributeExists("failedBuilds"))
                .andExpect(model().attributeExists("deployments"))
                .andExpect(model().attributeExists("successRate"));
    }

    @Test
    void dashboardExposesRecentActivity() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(model().attributeExists("recentActivity"));
    }
}
