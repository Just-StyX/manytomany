package jsl.manytomany.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Saving an author")
    public void saveAuthor() throws Exception {
        var authorJson = """
                {
                  "age":24,
                  "genre":"fiction",
                  "name":"Jon Doe"
                }
                """;
        ResultActions resultActions = mockMvc.perform(post("/api/author")
                .contentType(MediaType.APPLICATION_JSON).content(authorJson));
        resultActions.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Add a book")
    public void addBook() throws Exception {
        var bookJson = """
                {
                  "isbn":"09876XCV034",
                  "title":"The blue arrow"
                }
                """;
        var authorId = "339174cf-5577-4d30-93c9-7556820bdb58";
        ResultActions resultActions = mockMvc.perform(post("/api/author/{authorId}", authorId)
                .contentType(MediaType.APPLICATION_JSON).content(bookJson));
        resultActions.andExpect(status().isAccepted());
    }
}
