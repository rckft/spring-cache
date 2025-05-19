package dev.rckft.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SumController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SumController.class);
    private final Cache cache;

    public SumController(Cache cache) {
        this.cache = cache;
    }

    @GetMapping("/sum")
    public Double calculateSum(@RequestParam double a, @RequestParam double b) {
        var addends = a > b ? new Addends(b, a) : new Addends(a, b);
        var cached = cache.get(addends);
        if (cached.isPresent()) {
            Double result = cached.get();
            LOGGER.info("Cache hit {}, result {}", addends, result);
            return result;
        }
        LOGGER.info("Cache miss");
        var sum = addends.sum();
        cache.save(addends, sum);
        return sum;
    }
}
