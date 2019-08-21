package com.ownerkaka.testjdk.mybatis.mapper;

import com.ownerkaka.testjdk.mybatis.domain.Metas;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author akun
 * @since 2019-07-13
 */
@Mapper
public interface MetasMapper {

    @Select("select * from t_metas where mid = #{mid}")
    Metas getByMid(Integer mid);
}
