package top.alanlee.pam.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.annotation.NacosProperties;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import top.alanlee.pam.utils.NetworkUtil;

import javax.annotation.PostConstruct;

/**
 * Nacos注册中心
 */
@Configuration
@PropertySource("classpath:nacos-config-${spring.profiles.active}.properties")
@NacosPropertySource(dataId = "${nacos.data.id}", groupId = "${nacos.group.id}", type = ConfigType.YAML, autoRefreshed = true)
//@EnableNacosDiscovery(globalProperties = @NacosProperties(serverAddr = "${nacos.server-addr}", namespace = "${nacos.namespace}"))
@EnableNacosDiscovery(globalProperties = @NacosProperties(serverAddr = "${nacos.server-addr}"))
@Slf4j
public class NacosDiscoveryConfiguration {
    @NacosInjected
    private NamingService namingService;

    @Value("${service.name}")
    private String serviceName;

    @Value("${server.port}")
    private Integer serverPort;

    @Value("${server.group-name}")
    private String serverGroupName;

    @Value(value = "${spring.profiles.active}")
    private String env;

    @PostConstruct
    public void registerInstance() throws Exception {
        Instance instance = new Instance();
        instance.setIp(NetworkUtil.getLocalIp());
        instance.setPort(serverPort);
        log.info("当前环境 spring.profiles.active: {}", env);
        log.info("向nacos注册的服务实例信息: {}", instance);
        //服务元信息，可扩展
//        Map<String, String> metadata = new HashMap<>();
//        metadata.put("VERSION", version);
//        instance.setMetadata(metadata);
        // 通过Nacos Open API 向 Nacos Server 注册一个名称为`serviceName`的服务
        namingService.registerInstance(serviceName, serverGroupName, instance);
    }
}


