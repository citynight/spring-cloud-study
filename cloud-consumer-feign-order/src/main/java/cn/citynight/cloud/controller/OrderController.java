package cn.citynight.cloud.controller;

import cn.citynight.cloud.apis.PayFeignApi;
import cn.citynight.cloud.entities.PayDTO;
import cn.citynight.cloud.resp.ResultData;
import cn.citynight.cloud.resp.ReturnCodeEnum;
import cn.hutool.core.date.DateUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping(value = "/feign/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        log.info("第一步：模拟本地 addOrder 新增订单成功（省略 sql 操作），第二步: 再开启 addPay 支付微服务远程调用");
        ResultData resultData = payFeignApi.addPay(payDTO);
        return resultData;
    }

    @GetMapping(value = "/feign/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id) {
        log.info("-----支付微服务远程调用，按照 id 查询订单支付流水信息");

        ResultData payInfo = null;
        try {
            // 打印开始时间
            log.info("-----调用开始------" + DateUtil.now());
            payInfo = payFeignApi.getPayInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("调用结束------" + DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "调用失败");
        }
        return payInfo;
    }
    @GetMapping(value = "/feign/pay/mylb")
    public String mylb() {
        return payFeignApi.mylb();
    }

}
