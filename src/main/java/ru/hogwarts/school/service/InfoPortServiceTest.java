package ru.hogwarts.school.service;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!production")
public class InfoPortServiceTest implements InfoPortService{
    @Override
    public Integer getPort() {
        return 8081;
    }
}
