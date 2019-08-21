package com.ownerkaka.testjdk.mybatis.mapper;

import com.ownerkaka.testjdk.mybatis.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author akun
 * @since 2019-07-13
 */
public interface UserMapper {

    User getByUsername(String username);

    User getById(Integer id);

    boolean updateUsername(Long id, String username);

    /**
     * ${}用来字符替换
     * #{} 用作PreparedStatement的参数占位符，会被解析为 ?
     *
     * @param columnName
     * @param value
     * @return
     */
    @Select("select * from ownerkaka_user where ${columnName} = #{value} ORDER BY uid DESC LIMIT 1")
    User getByColumn(@Param("columnName") String columnName, @Param("value") String value);

    User findByUsername(String username, String email, String password);

    List<User> findByIdList(List<Integer> userIdList);

    boolean updateUser(User user);

    boolean createUser(User user);

    boolean removeUser(int id);
}
