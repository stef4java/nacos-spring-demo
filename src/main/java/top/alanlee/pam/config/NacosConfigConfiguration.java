package top.alanlee.pam.config;

import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * Nacos配置中心
 */
@Configuration
@PropertySource("classpath:nacos-config-${spring.profiles.active:dev}.properties")
@NacosPropertySource(dataId = "${nacos.data.id}", groupId = "${nacos.group.id}", type = ConfigType.YAML, autoRefreshed = true)
//@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "${nacos.server-addr}", namespace = "${nacos.namespace}"))
@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "${nacos.server-addr}"))
public class NacosConfigConfiguration {

}