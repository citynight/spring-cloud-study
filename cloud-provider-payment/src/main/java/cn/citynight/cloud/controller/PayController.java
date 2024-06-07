package cn.citynight.cloud.controller;

import cn.citynight.cloud.entities.Pay;
import cn.citynight.cloud.entities.PayDTO;
import cn.citynight.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Tag(name = "支付微服务模块", description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("/pay/add")
    @Operation(summary = "新增", description = "新增支付流水方法，json做参数")
    public String addPay(@RequestBody Pay pay)
    {
        log.info("addPay:{}", pay);
        int result = payService.add(pay);
        return "成功插入记录，返回值：" + result;
    }

    @DeleteMapping("/pay/del/{id}")
    @Operation(summary = "删除", description = "删除支付流水方法，id做参数")
    public String deletePay(@PathVariable("id") Integer id)
    {
        log.info("deletePay:{}", id);
        int result = payService.delete(id);
        return "成功删除记录，返回值：" + result;
    }


    @PutMapping("/pay/update")
    @Operation(summary = "修改", description = "修改支付流水方法")
    public String updatePay(@RequestBody PayDTO payDTO)
    {
        log.info("updatePay:{}", payDTO);
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        log.info("updatePay:{}", pay);
        int result = payService.update(pay);
        return "成功更新记录，返回值：" + result;
    }

    @GetMapping("/pay/get/{id}")
    @Operation(summary = "查询", description = "查询支付流水方法，id做参数")
    public Pay getById(@PathVariable("id") Integer id)
    {
        log.info("getById:{}", id);
        Pay pay = payService.getById(id);
        return pay;
    }
}
