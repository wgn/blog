package com.zhuani21.blog.auto.bean;

import javax.validation.constraints.Size;

public class Code {
    private Integer id;
   
    @Size(min=1,max=20,message="{code.type.msg}")
    private String type;
   
    @Size(min=1,max=20,message="{code.code.msg}")
    private String code;
    
    @Size(max=100,message="{code.name.msg}")
    private String name;

    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}