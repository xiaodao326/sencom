package com.itxiaodao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//文章相关pojo
public class Article {
    private Integer id;
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;
    @NotEmpty
    private String content;
    private String coverImg;
    @JsonIgnore
    private Integer createUser;
    @NotNull
    private Integer intcircleId;
    private LocalDateTime createTime;
    @JsonIgnore
    private LocalDateTime updateTime;
    private Integer like;
    private Integer comment;
    private Host host =  new Host();
}
