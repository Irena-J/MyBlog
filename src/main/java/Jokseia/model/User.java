package Jokseia.model;

import java.util.Date;

public class User {

    private Integer id;

    private String name;

    //使用的是java.util.Date
    private Date createTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreateTime() {
        return createTime;
    }


}
