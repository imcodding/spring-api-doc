package com.api.doc.controller;

import com.api.doc.dto.Board;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
class BoardControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    void getBoardList() throws Exception {
        this.mockMvc.perform(get("/api/v1/board/list").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                ;
    }

    @Test
    void getBoard() throws Exception {
        this.mockMvc.perform(get("/api/v1/board/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(
                        document(
                                "test",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        PayloadDocumentation.fieldWithPath("boardId").description("????????? ???????????????."),
                                        PayloadDocumentation.fieldWithPath("title").description("????????? ???????????????."),
                                        PayloadDocumentation.fieldWithPath("content").description("????????? ???????????????."),
                                        PayloadDocumentation.fieldWithPath("userId").description("??????????????????.")
                                )

                        )
                )
        ;
    }

    @Test
    void addBoard() throws Exception {
        HashMap<String, Object> board = new HashMap<>();
        board.put("title", "????????? ???????????????.");
        board.put("content", "????????? ???????????????.");
        board.put("userId", "?????????1");

        JSONObject jsonObject = new JSONObject(board);
        String jsonString = jsonObject.toString();

        this.mockMvc.perform(post("/api/v1/board").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(

                        document("{method-name}",
                            requestFields(
                                    fieldWithPath("title").description("????????? ???????????????."),
                                    fieldWithPath("content").description("????????? ???????????????."),
                                    fieldWithPath("userId").description("??????????????????")
                            )
                        )
                )
        ;
    }
}