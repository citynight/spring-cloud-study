package cn.citynight.cloud.controller;

import cn.citynight.cloud.entities.Pay;
import cn.citynight.cloud.entities.PayDTO;
import cn.citynight.cloud.service.PayService;
import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping("/pay/add")
    public String addPay(@RequestBody Pay pay)
    {
        log.info("addPay:{}", pay);
        int result = payService.add(pay);
        return "成功插入记录，返回值：" + result;
    }

    @DeleteMapping("/pay/del/{id}")
    public String deletePay(@PathVariable("id") Integer id)
    {
        log.info("deletePay:{}", id);
        int result = payService.delete(id);
        return "成功删除记录，返回值：" + result;
    }


    @PutMapping("/pay/update")
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
    public Pay getById(@PathVariable("id") Integer id)
    {
        log.info("getById:{}", id);
        Pay pay = payService.getById(id);
        return pay;
    }

}
