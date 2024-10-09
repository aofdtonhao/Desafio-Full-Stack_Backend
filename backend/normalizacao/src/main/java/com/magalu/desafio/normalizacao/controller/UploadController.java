package com.magalu.desafio.normalizacao.controller;

import com.magalu.desafio.normalizacao.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class UploadController {

    //private final StorageService storageService;

    //@Autowired
    //public UploadController(StorageService storageService);

    @PostMapping("/upload")
    public String upload(@RequestParam("arquivo") MultipartFile arquivo,
                                 RedirectAttributes redirectAttributes) {
        //storageService.store(arquivo);
        redirectAttributes.addFlashAttribute("message",
                "Upload com sucesso: " + arquivo.getOriginalFilename() + "!");

        return "redirect:/"; // TODO
    }

}
