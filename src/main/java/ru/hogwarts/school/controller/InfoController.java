package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoPortService;


@RestController
public class InfoController {
    @Autowired
    private InfoPortService portService;
    @GetMapping("/getPort")
    public Integer getPort(){
        return portService.getPort();
    }
}
