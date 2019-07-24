package cn.pomit.jpa.mapper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.pomit.jpa.mapper.domain.UserInfoUnion;
import cn.pomit.jpamapper.core.mapper.CrudMapper;

@Mapper
public interface UserInfoUnionDao extends CrudMapper<UserInfoUnion, String> {
	List<UserInfoUnion> findByMobile(String mobile);
}