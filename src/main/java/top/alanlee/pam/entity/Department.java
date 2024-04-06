package top.alanlee.pam.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Department implements Serializable {
    private String id;
    private String code;
    private String name;
}
