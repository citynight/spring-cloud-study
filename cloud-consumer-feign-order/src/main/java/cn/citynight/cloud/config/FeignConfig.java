package cn.citynight.cloud.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer feignRetryer() {
        return Retryer.NEVER_RETRY;// 不重试
//        最大请求次数为 3（1+2），初始间隔时间为 100ms，重试间最大间隔时间为 1s
//        return new Retryer.Default(100, 1, 3);
    }
}
