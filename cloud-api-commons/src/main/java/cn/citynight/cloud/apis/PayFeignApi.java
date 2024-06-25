package cn.citynight.cloud.apis;

import cn.citynight.cloud.entities.PayDTO;
import cn.citynight.cloud.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cloud-payment-service")
public interface PayFeignApi {
    /**
     * 新增支付流水记录
     * */
    @PostMapping(value = "/pay/add")
    public ResultData addPay(@RequestBody PayDTO payDTO);

    /**
     * 根据id查询支付流水记录
     * */
    @GetMapping(value = "/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id);

    /**
     * 测试负载均衡
     * */
    @GetMapping(value = "/pay/get/info")
    public String mylb();

    @GetMapping(value = "/pay/circuit/{id}")
    public String myCircuitBreaker(@PathVariable("id") Integer id);
}
