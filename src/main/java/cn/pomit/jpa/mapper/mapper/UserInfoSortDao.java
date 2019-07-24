package cn.pomit.jpa.mapper.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.pomit.jpa.mapper.domain.UserInfo;
import cn.pomit.jpamapper.core.domain.page.Page;
import cn.pomit.jpamapper.core.domain.page.Pageable;
import cn.pomit.jpamapper.core.domain.page.Sort;
import cn.pomit.jpamapper.core.mapper.PagingAndSortingMapper;


@Mapper
public interface UserInfoSortDao extends PagingAndSortingMapper<UserInfo, String> {
	Page<UserInfo> pageByPasswd(String passwd, Pageable pageable);
	
	List<UserInfo> sortByPasswd(String passwd, Sort sort);
}