package com.cry.qa.response;

import com.cry.qa.domain.User;
import com.cry.qa.request.AddUserReq;
import com.cry.qa.request.LoginRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dozer.DozerBeanMapper;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 从Redis缓存中读取用户信息, 返回用户视图封装类
 */
@ApiModel(value = "com.cry.qa.response.UserViewModel", description = "用户视图封装类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserViewModel implements Serializable {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "VIP等级")
    private Integer rmb;

    @ApiModelProperty(value = "question条数")
    private Integer questionCnt;

    @ApiModelProperty(value = "answer条数")
    private Integer answerCnt;

    @ApiModelProperty(value = "头像")
    private String pic;

    @ApiModelProperty(value = "用户权限")
    private Integer auth;

    @ApiModelProperty(value = "经验值")
    private Integer experience;

    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    /**
     * 创建一个Bean转换工具: DozerBeanMapper
     */
    private static DozerBeanMapper mapper = new DozerBeanMapper();

    /**
     * 普通对象转换, 比如: User -> UserViewModel
     *
     * @param user
     * @return
     */
    public static UserViewModel userFrom(User user) {
        UserViewModel vm = mapper.map(user, UserViewModel.class);
        return vm;
    }

    /**
     * 普通对象转换, 比如: AddUserReq -> User
     *
     * @param req
     * @return
     */
    public static  User userFrom(AddUserReq req) {
        User vm = mapper.map(req, User.class);
        return vm;
    }

    /**
     * 普通对象转换, 比如: LoginRequest -> User
     *
     * @param req
     * @return
     */
    public static  User userFrom(LoginRequest req) {
        User vm = mapper.map(req, User.class);
        return vm;
    }

    /**
     * List转换, 比如: List<User> -> List<UserViewModel>
     *
     * @param users
     * @return
     */
    static public List<UserViewModel> userListFrom(List<User> users) {
        List<UserViewModel> vms = new ArrayList<UserViewModel>();
        for (User user : users) {
            UserViewModel vm = new UserViewModel();
            mapper.map(user, UserViewModel.class);
            vms.add(mapper.map(user, UserViewModel.class));
        }
        return vms;
    }

}

