package com.devloper.joker.mybatisplus.cascade.mapper.user;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devloper.joker.mybatisplus.cascade.core.QuerySupport;
import com.devloper.joker.mybatisplus.cascade.domain.user.User;
import com.devloper.joker.mybatisplus.cascade.domain.user.UserRoleVO;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.List;

//可直接在这里定义方法列表,默认只有在类上加注解才会支持方法
@QuerySupport()
public interface UserMapper extends BaseMapper<User> {

    String JOIN_SQL = "SELECT user.*, role.name as role_name, role.create_time as role_create_time FROM user as user LEFT JOIN role as role ON user.role_id = role.id";

    @QuerySupport
    @Select("SELECT user.*, role.name as role_name, role.create_time as role_create_time FROM user as user LEFT JOIN role as role ON user.role_id = role.id")
    List<UserRoleVO> findUserWithRoleByVoWithQueryList(@Param(Constants.WRAPPER) Wrapper<UserRoleVO> wrapper);

    @QuerySupport
    @Select(JOIN_SQL)
    List<User> selectListByCustom(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    /**
     * 使用mybatis-plus自定义分页查询,使用resultMap将结果解析到关联对象中
     *
     * @param page 必须为第一个参数
     * @param wrapper
     * @return
     */
    //类型优先级 (若未存在注解,当类型与mapper泛型不一致时使用其本身) @ResultType > @ResultMap > @Table(在类型一致时应用存在的resultMap)
    @ResultMap("userCascadeResult")
    @QuerySupport
    @Select({"<script>", JOIN_SQL, "</script>"})
    Page<User> selectPageByCustom(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);


    @ResultMap("userNotCascadeResult")
    @QuerySupport
    @Select({"<script>", "SELECT * FROM user", "</script>"})
    Page<User> selectPageByCustomWithAssociation(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

    @QuerySupport
    Page<User> selectPageByCustomWithXml(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

    @QuerySupport
    Page<User> selectPageByCustomWithXmlAndBind(Page<User> page, @Param("roleNameText") String roleNameText, @Param("roleCreateTimeText") String roleCreateTimeText, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

    @QuerySupport
    Page<User> selectPageByCustomWithXmlAndInclude(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

    @QuerySupport
    Page<User> selectPageByCustomWithXmlAndIncludeAndBind(Page<User> page, @Param("text") String roleText, @Param(Constants.WRAPPER) Wrapper<User> wrapper);


    @QuerySupport
    @Select({JOIN_SQL, "WHERE user.id = #{id}"})
    User selectCascadeById(Serializable id);


    @ResultMap("userCascadeResult")
    @Select({JOIN_SQL, "WHERE user.id = #{id}"})
    User selectCascadeById2(Serializable id);

    @QuerySupport
    @Select({"${text}"})
    List<User> selectByText(@Param("text") String text, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

}
