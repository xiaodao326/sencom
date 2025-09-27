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
//兴趣圈相关pojo
public class IntCircle {
    private Integer id;
    @Pattern(regexp = "^\\S{1,10}$")
    private String name;
    @NotEmpty
    private String avatar;
    @NotEmpty
    private String introduce;
    @JsonIgnore
    private Integer createUser;
    @NotNull
    private Integer categoryId;
    private String address;
    private Double longitude;
    private Double latitude;
    private LocalDateTime createTime;
    @JsonIgnore
    private LocalDateTime updateTime;
    private Host host =  new Host();
}
