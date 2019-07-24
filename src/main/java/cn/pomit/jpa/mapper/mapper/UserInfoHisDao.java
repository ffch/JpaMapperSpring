package cn.pomit.jpa.mapper.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.pomit.jpa.mapper.domain.UserInfoHis;
import cn.pomit.jpamapper.core.mapper.SimpleShardingMapper;

@Mapper
public interface UserInfoHisDao extends SimpleShardingMapper<UserInfoHis, String> {
	
}