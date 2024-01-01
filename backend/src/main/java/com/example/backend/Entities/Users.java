package com.example.backend.Entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.example.backend.Enum.Enums;
import lombok.Data;

/**
 * 
 * @TableName users
 */
@TableName(value ="users")
@Data
public class Users implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer userid;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String userpassword;

    /**
     * 
     */
    private Enums.departmentName departmentname;

    /**
     * 
     */
    private LocalDateTime createat;

    /**
     * 
     */
    private LocalDateTime updateat;




}