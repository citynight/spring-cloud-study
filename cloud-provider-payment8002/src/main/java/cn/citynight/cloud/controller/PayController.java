package cn.citynight.cloud.controller;

import cn.citynight.cloud.entities.Pay;
import cn.citynight.cloud.entities.PayDTO;
import cn.citynight.cloud.resp.ResultData;
import cn.citynight.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法，json做参数")
    public ResultData<String> addPay(@RequestBody Pay pay)
    {
        log.info("addPay:{}", pay);
        int result = payService.add(pay);
        return ResultData.success("成功插入记录，返回值：" + result);
    }

    @DeleteMapping("/pay/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法，id做参数")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id)
    {
        log.info("deletePay:{}", id);
        int result = payService.delete(id);
        return ResultData.success(result);
    }


    @PutMapping("/pay/update")
    @Operation(summary = "修改", description = "修改支付流水方法")
    public ResultData<String> updatePay(@RequestBody PayDTO payDTO)
    {
        log.info("updatePay:{}", payDTO);
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        log.info("updatePay:{}", pay);
        int result = payService.update(pay);
        return ResultData.success("成功更新记录，返回值：" + result);
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "查询", description = "查询支付流水方法，id做参数")
    public ResultData<Pay> getById(@PathVariable("id") Integer id)
    {
        if (id == -4) {
            throw new RuntimeException("故意抛出异常");
        }
        log.info("getById:{}", id);
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/pay/get/info")
    public String getInfoByConsul(@Value("${citynight.info}") String citynight)
    {
        return "当前端口：" + port + "\t，citynight：" + citynight;
    }
}
