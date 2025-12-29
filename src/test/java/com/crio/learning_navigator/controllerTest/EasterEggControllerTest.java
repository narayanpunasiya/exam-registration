package com.crio.learning_navigator.controllerTest;

import com.crio.learning_navigator.config.AppConfig;
import com.crio.learning_navigator.controller.EasterEggController;
import com.crio.learning_navigator.service.EasterEggService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EasterEggController.class)
@Import(AppConfig.class)
public class EasterEggControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EasterEggService service;

    @Test
    void hiddenFeature_shouldReturnMessage() throws Exception {
        when(service.getNumberFact(42)).thenReturn("42 is the answer to everything.");

        mockMvc.perform(get("/easter-egg/hidden-feature/42"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Great! You have found the hidden number fact"))
            .andExpect(jsonPath("$.response").value("42 is the answer to everything."));
    }

}
