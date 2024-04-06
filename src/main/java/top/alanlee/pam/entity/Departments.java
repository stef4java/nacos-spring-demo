package top.alanlee.pam.entity;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Departments {

    private static final Log log = LogFactory.getLog(Departments.class);

    public static ArrayList<Department> departments = new ArrayList<>();

    private List<String> departmentList;

    @NacosValue(value = "#{'${enterprise.department-list}'.split(';')}", autoRefreshed = true)
    public void setDepartmentList(List<String> departmentList) {
        this.departmentList = departmentList;
        log.info("配置变更时，重新解析并更新departmentList: " + departmentList);
        refreshDepartments();
    }

    private void refreshDepartments() {
        departments.clear();
        departmentList.stream().forEach(p -> {
            String[] strings = p.split("-");
            if (strings.length < 3) {
                log.error("部门格式错误: " + p + ", 需要至少包含3个'-'分隔的部分.");
                return; // 跳过格式不正确的项
            }
            Department d = new Department();
            d.setId(strings[0]);
            d.setCode(strings[1]);
            d.setName(strings[2]);
            departments.add(d);
        });
    }

}
