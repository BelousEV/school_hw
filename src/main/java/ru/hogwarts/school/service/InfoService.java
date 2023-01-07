package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.stream.Stream;

@Service
public class InfoService {
    private static final Logger LOG = LoggerFactory.getLogger(InfoService.class);

    public void testParallelStream(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("plain stream");
        long sum = Stream.iterate(1L, a -> a +1)
                .limit(1_000_000L)
                .reduce(0L, (a, b) -> a + b );
        stopWatch.stop();
        LOG.info("Calculated value is {}; {}", sum, stopWatch.prettyPrint());
        stopWatch.start("parallel stream");
         sum = Stream.iterate(1L, a -> a +1)
                .limit(1_000_000L)
                .reduce(0L, (a, b) -> a + b );
        stopWatch.stop();
        LOG.info("Calculated value is {}; {}", sum, stopWatch.prettyPrint());


    }



//    int sum = Stream.iterate(1, a -› a +1) .
//            limit(1_000_000) .parallel()
//            .reduce(0, (a, b) -› a + b );
}
