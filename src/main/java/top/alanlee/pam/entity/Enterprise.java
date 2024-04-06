package top.alanlee.pam.entity;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Setter
@Getter
@ToString
@Component
@NacosConfigurationProperties(dataId = "spring-nacos-demo-config.yaml", autoRefreshed = true)
public class Enterprise implements Serializable {
    @NacosValue(value ="${enterprise.name}", autoRefreshed = true)
    private String companyName;
    @NacosValue(value = "${enterprise.website}", autoRefreshed = true)
    private String website;
}
