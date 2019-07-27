package com.chendehe.dao;

import com.chendehe.po.UserPO;
import com.chendehe.vo.Page;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 包含注解方式和 provider 方式的使用样例.
 */
@Mapper
public interface UserDao extends BaseDao<UserDao> {

  @Select("select * from t_user limit #{page.currentPage}, #{page.pageSize}")
//  @SelectProvider(value = UserDaoProvider.class, method = "listByPage")
  List<UserPO> listByPage(@Param("page") Page page);

  @Select("select * from t_user where id = #{id}")
//  @SelectProvider(value = UserDaoProvider.class, method = "getById")
  Optional<UserPO> getById(String id);

  @Insert("insert into t_user(id, name, gender, birthday, address, create_time, update_time) "
      + "values(#{id}, #{name}, #{gender}, #{birthday}, #{address}, #{createTime}, #{updateTime})")
//  @InsertProvider(value = UserDaoProvider.class, method = "save")
  void save(UserPO user);

  @Insert("<script> "
      + "insert into t_user(id, name, gender, birthday, address, create_time, update_time) values "
      + "<foreach collection='users' index='index' item='user' separator=','> "
      + "(#{user.id}, #{user.name}, #{user.gender}, #{user.birthday}, #{user.address}, #{user.createTime}, #{user.updateTime})"
      + "</foreach> "
      + "</script>")
//  @InsertProvider(value = UserDaoProvider.class, method = "saveBatch")
  void saveBatch(@Param("users") List<UserPO> users);

  @Update("update t_user set name = #{name}, gender = #{gender}, birthday = #{birthday}, "
      + "address = #{address}, update_time = #{updateTime} where id = #{id}")
//  @UpdateProvider(value = UserDaoProvider.class, method = "update")
  void update(UserPO user);

  @Delete("delete from t_user where id = #{id}")
//  @DeleteProvider(value = UserDaoProvider.class, method = "remove")
  void remove(String id);

  @Delete("<script>"
      + "delete from t_user where id in "
      + "<foreach collection='ids' index='index' item='id' open='(' separator=',' close=')'>"
      + "#{id}"
      + "</foreach>"
      + "</script>")
//  @DeleteProvider(value = UserDaoProvider.class, method = "removeByIds")
  void removeByIds(@Param("ids") Set<String> ids);

  @Select("select count(*) from t_user")
//  @SelectProvider(value = UserDaoProvider.class, method = "count")
  int count();
}