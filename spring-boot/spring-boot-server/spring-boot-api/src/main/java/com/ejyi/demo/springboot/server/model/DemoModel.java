/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: 输入对象示例
 * Version: 1.0
 * Date: 18-5-8 下午6:13
 * LastModified: 18-5-8 下午6:13
 */

package com.ejyi.demo.springboot.server.model;


import java.util.Date;

public class DemoModel {

    private Long		 id; //自增主键
    private String       code; //编号

    private Double score; // 得分

    private Byte         status;  //记录状态,0有效,1无效
    private Date         createTime;  //创建时间
    private Date updateTime; //更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

