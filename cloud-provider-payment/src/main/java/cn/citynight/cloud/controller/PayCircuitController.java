package cn.citynight.cloud.controller;

import cn.hutool.core.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class PayCircuitController {
    private static final Logger log = LoggerFactory.getLogger(PayCircuitController.class);

    @GetMapping("/pay/circuit/{id}")
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        log.info("myCircuitBreaker  payment CircuitBreaker" + id);
        if (id == -4) {
            throw new RuntimeException("id 不能为负数");
        }
        if (id== 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello, circuit! inputId: " + id + "\t" + IdUtil.simpleUUID();
    }
}
