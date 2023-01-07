package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class InfoService {
    private static final Logger LOG = LoggerFactory.getLogger(InfoService.class);

    int sum = Stream.iterate(1, a -> a +1)
            .limit(1_000_000)
            .reduce(0, (a, b) -> a + b );
}
