package cn.citynight.cloud.controller;

import cn.citynight.cloud.apis.PayFeignApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderCircuitController {
    private static final Logger log = LoggerFactory.getLogger(OrderCircuitController.class);
    @Resource
    private PayFeignApi payFeignApi;
    @GetMapping(value = "/feign/pay/circuit/{id}")
    @CircuitBreaker(name="cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id") Integer id) {
        log.info("myCircuitBreaker----, id:{}", id);
        return payFeignApi.myCircuitBreaker(id);
    }

    // myCircuitFallback 就是服务降级后的兜底处理方法
    // 严格来说 myCircuitBreaker 有什么参数这里也要接收对应的参数
    public String myCircuitFallback(Integer id,Throwable t) {
        return "myCircuitFallback, 系统繁忙，请稍后再试---";
    }
}
