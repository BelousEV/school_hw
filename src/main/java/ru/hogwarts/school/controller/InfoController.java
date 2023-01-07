package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.InfoService;

@RestController
@RequestMapping("/info")

public class InfoController {
    private final InfoService infoService;


    @Value("${server.port}")
    private int port;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/port")
    public int getPort() {
        return port;
    }

    @GetMapping("/testParallelStream")
    public void testParallelStream() {

    }
}



