package com.magalu.desafio.normalizacao.controller;

import java.nio.file.Paths;
import java.util.stream.Stream;

import com.magalu.desafio.normalizacao.exception.StorageFileNotFoundException;
import com.magalu.desafio.normalizacao.service.StorageService;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class FileUploadControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StorageService storageService;

    @Test
    public void shouldListAllFiles() throws Exception {
        given(this.storageService.loadAll())
                .willReturn(Stream.of(Paths.get("data_1.txt"), Paths.get("data_2.txt")));

        this.mvc.perform(get("/upload/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("files",
                        Matchers.contains("http://localhost/upload/files/data_1.txt",
                                "http://localhost/upload/files/data_2.txt")));
    }

    @Test
    public void shouldSaveUploadedFile() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
                "text/plain", "Spring Framework".getBytes());
        this.mvc.perform(multipart("/upload/").file(multipartFile))
                .andExpect(status().isFound());

        then(this.storageService).should().store(multipartFile);
    }

    @Test
    public void should404WhenMissingFile() throws Exception {
        given(this.storageService.loadAsResource("test.txt"))
                .willThrow(StorageFileNotFoundException.class);

        this.mvc.perform(get("/upload/files/test.txt")).andExpect(status().isNotFound());
    }

}
