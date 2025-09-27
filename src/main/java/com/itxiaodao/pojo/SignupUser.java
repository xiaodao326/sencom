package com.itxiaodao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupUser {
    private Integer id;
    private String nickname;
    private String avatar;
    private String phone;
}
