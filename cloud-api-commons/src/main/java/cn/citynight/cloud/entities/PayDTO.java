package cn.citynight.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 支付流水DTO
 * 一般而言，调用者不应该获悉服务提供者的 entity 资源并知道表结构关系，所以服务提供方给出的接口文档都应为 DTO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayDTO {
    private Integer id;
    // 支付流水号
    private String payNo;
    // 订单号
    private String orderNo;
    // 用户id
    private Integer userId;
    // 交易金额
    private BigDecimal amount;

}
