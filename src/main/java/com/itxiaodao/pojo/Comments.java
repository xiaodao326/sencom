package com.itxiaodao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//评论相关pojo
public class Comments {
    private Integer id;
    @Pattern(regexp = "^\\S{1,100}$")
    private String content;
    @NotNull
    private Integer articleId;
    @JsonIgnore
    private Integer createUser;
    private LocalDateTime createTime;
    @JsonIgnore
    private LocalDateTime updateTime;
}
