package com.ownerkaka.testjdk.mybatis.mapper;

import com.ownerkaka.testjdk.mybatis.domain.User;

/**
 * @author akun
 * @since 2019-07-13
 */
public interface UserMapper {

    User getByUsername(String username);

    User getById(Integer id);

    boolean updateUsername(Long id, String username);

}
