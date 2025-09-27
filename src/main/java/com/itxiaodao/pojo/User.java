package com.itxiaodao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//用户相关pojo
public class User {
    @NotNull
    private Integer id;//主键ID
    private String account;//账号
    @JsonIgnore
    private String password;//密码
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称
    private String phone;//手机号
    private String avatar;//用户头像地址
    private String gender;//性别
    private String address;//地址
    private String birth;//生日
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
    private List<Tag> tags;//用户标签
}
