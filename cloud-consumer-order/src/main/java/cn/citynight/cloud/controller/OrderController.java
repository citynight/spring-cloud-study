package cn.citynight.cloud.controller;

import cn.citynight.cloud.entities.PayDTO;
import cn.citynight.cloud.resp.ResultData;
import cn.citynight.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class OrderController {
//    public static final String PaymentSrv_URL = "http://localhost:8001"; // 先写死，硬编码
    public static final String PaymentSrv_URL = "http://cloud-payment-service"; // 微服务注册中心上的微服务名称


    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/pay/add")
    public ResultData addOrder(PayDTO payDTO) {
        System.out.println("payDTO = " + payDTO);
        return restTemplate.postForObject(PaymentSrv_URL + "/pay/add", payDTO, ResultData.class);
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/" + id, ResultData.class, id);
    }

    @GetMapping("/consumer/pay/del/{id}")
    public ResultData deleteOrder(@PathVariable("id") Integer id) {
        log.info("删除id = " + id);
        try {
            restTemplate.delete(PaymentSrv_URL + "/pay/del/" + id);
            return ResultData.success("删除成功");
        } catch (Exception e) {
            log.error("删除失败");
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "删除失败");
        }

    }

    @GetMapping("/consumer/pay/update")
    public ResultData updateOrder(PayDTO payDTO) {
        log.info("修改payDTO = " + payDTO);
        try {
            restTemplate.put(PaymentSrv_URL + "/pay/update", payDTO);
            return ResultData.success("修改成功");
        } catch (Exception e) {
            log.error("修改失败");
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "修改失败");
        }
    }


    @GetMapping("/consumer/pay/get/info")
    public String getInfoByConsul() {
        return restTemplate.getForObject(PaymentSrv_URL + "/pay/get/info", String.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/discovery")
    public String discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*****element: " + service);
        }
        log.info("=========================");
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return instances.get(0).getServiceId() + "\t" + instances.get(0).getHost() + "\t" + instances.get(0).getPort() + "\t" + instances.get(0).getUri();
    }
}
