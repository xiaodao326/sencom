package com.itxiaodao.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itxiaodao.service.LikeService;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//活动相关pojo
public class Activity {
    private Integer id;
    @Pattern(regexp = "^\\S{1,20}$")
    private String title;
    private String introduce;
    @NotNull(message = "时间不能为空")
    @Future(message = "时间必须是未来的")
    private LocalDateTime time;
    @NotEmpty
    private String location;
    @NotNull
    private Integer intcircleId;
    @JsonIgnore
    private Integer createUser;
    private LocalDateTime createTime;
    @JsonIgnore
    private LocalDateTime updateTime;
    private Host host =  new Host();
    private Integer signupTotal; //报名人数
    private List<User> signupUsers; //报名用户列表
}
