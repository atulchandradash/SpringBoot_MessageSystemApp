package com.example.backend.Entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sentmessage
 */
@TableName(value ="sentmessage")
@Data
public class Sentmessage implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer messageid;

    /**
     * 
     */
    private Integer senderid;

    /**
     * 
     */
    private String message;

    /**
     * 
     */
    private Integer userid;

    /**
     * 
     */
    private LocalDateTime createat;

    /**
     * 
     */
    private LocalDateTime updateat;

    /**
     *
     */
    private Users usersinfo;




}