package com.czkj.rocket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author steven.sheng
 * @Date 2019/6/12/01211:27
 */
@Data

@NoArgsConstructor
@AllArgsConstructor
public class UserContent implements Serializable{
    private static final long serialVersionUID = 237393906695942727L;
    private String name;
    private String addr;
}
