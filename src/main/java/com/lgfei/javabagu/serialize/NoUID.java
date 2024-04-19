package com.lgfei.javabagu.serialize;

import java.io.Serializable;
import java.util.Date;

public class NoUID implements Serializable {
    private Integer id;

    private String name;

    private Boolean flag;

    private Date time;

    private String temp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "NoUID{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", flag=" + flag +
                ", time=" + time +
                '}';
    }
}
