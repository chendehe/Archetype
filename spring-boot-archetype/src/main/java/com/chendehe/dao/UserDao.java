package com.chendehe.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.chendehe.dao.provider.UserDaoProvider;
import com.chendehe.po.UserPO;
import com.chendehe.vo.Page;

/**
 * 包含注解方式和 provider 方式的使用样例.
 * 
 * @author CDH
 * @since 2019/7/27 16:10
 */
@Mapper
public interface UserDao extends BaseDao<UserDao> {

    /**
     * 如果使用 Provider 方式：
     * @see UserDaoProvider#listByPage()
     *
     * @param page 分页
     * @return 结果集
     */
    @Select("select * from t_user limit #{page.currentPage}, #{page.pageSize}")
    List<UserPO> listByPage(@Param("page") Page page);

    /**
     * 如果使用 Provider 方式：
     * @see UserDaoProvider#getById()
     *
     * @param id 用户 id
     * @return 用户
     */
    @Select("select * from t_user where id = #{id}")
    Optional<UserPO> getById(String id);

    /**
     * 如果使用 Provider 方式：
     * @see UserDaoProvider#save()
     *
     * @param user 用户
     */
    @Insert("insert into t_user(id, name, gender, birthday, address, create_time, update_time) "
        + "values(#{id}, #{name}, #{gender}, #{birthday}, #{address}, #{createTime}, #{updateTime})")
    void save(UserPO user);

    /**
     * 如果使用 Provider 方式：
     * @see UserDaoProvider#saveBatch(Map)
     *
     * @param users 用户列表
     */
    @Insert("<script> " + "insert into t_user(id, name, gender, birthday, address, create_time, update_time) values "
        + "<foreach collection='users' index='index' item='user' separator=','> "
        + "(#{user.id}, #{user.name}, #{user.gender}, #{user.birthday}, #{user.address}, #{user.createTime}, #{user.updateTime})"
        + "</foreach> " + "</script>")
    void saveBatch(@Param("users") List<UserPO> users);

    /**
     * 如果使用 Provider 方式：
     * @see UserDaoProvider#update()
     *
     * @param user 用户
     */
    @Update("update t_user set name = #{name}, gender = #{gender}, birthday = #{birthday}, "
        + "address = #{address}, update_time = #{updateTime} where id = #{id}")
    void update(UserPO user);

    /**
     * 如果使用 Provider 方式：
     * @see UserDaoProvider#remove()
     *
     * @param id 用户 id
     */
    @Delete("delete from t_user where id = #{id}")
    void remove(String id);

    /**
     * 如果使用 Provider 方式：
     * @see UserDaoProvider#removeByIds(Map)
     *
     * @param ids id 集合
     */
    @Delete("<script>" + "delete from t_user where id in "
        + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>" + "#{id}" + "</foreach>"
        + "</script>")
    void removeByIds(@Param("ids") Set<String> ids);

    /**
     * 如果使用 Provider 方式：
     * @see UserDaoProvider#count()
     *
     * @return 数量
     */
    @Select("select count(*) from t_user")
    int count();
}