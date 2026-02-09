package ua.duikt.learning.java.pro.spring.individualseventhsprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Mykyta Sirobaba on 20.01.2026.
 * email mykyta.sirobaba@gmail.com
 */
@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    // --- PUBLIC ENDPOINT TESTS ---

    @Test
    @DisplayName("Public: Access allowed for Anonymous")
    void publicShouldBeAccessibleAnonymous() throws Exception {
        mockMvc.perform(get("/public/info"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Public: Access allowed for User")
    @WithMockUser(roles = "USER")
    void publicShouldBeAccessibleForUser() throws Exception {
        mockMvc.perform(get("/public/info"))
                .andExpect(status().isOk());
    }

    // --- PRIVATE ENDPOINT TESTS ---

    @Test
    @DisplayName("Private: Access denied for Anonymous (401)")
    void privateShouldBeProtected() throws Exception {
        mockMvc.perform(get("/private/data"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Private: Access allowed for User")
    @WithMockUser(roles = "USER")
    void privateShouldBeAllowedForUser() throws Exception {
        mockMvc.perform(get("/private/data"))
                .andExpect(status().isOk());
    }

    // --- ADMIN ENDPOINT TESTS ---

    @Test
    @DisplayName("Admin: Access denied for User (403)")
    @WithMockUser(roles = "USER")
    void adminShouldBeForbiddenForSimpleUser() throws Exception {
        mockMvc.perform(get("/admin/settings"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Admin: Access allowed for Admin")
    @WithMockUser(roles = "ADMIN")
    void adminShouldBeAllowedForAdmin() throws Exception {
        mockMvc.perform(get("/admin/settings"))
                .andExpect(status().isOk());
    }
}