package cn.pomit.jpa.mapper.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.pomit.jpa.mapper.domain.UserInfo;
import cn.pomit.jpa.mapper.domain.UserInfoHis;
import cn.pomit.jpa.mapper.domain.UserInfoUnion;
import cn.pomit.jpa.mapper.mapper.UserInfoDao;
import cn.pomit.jpa.mapper.mapper.UserInfoHisDao;
import cn.pomit.jpa.mapper.mapper.UserInfoSortDao;
import cn.pomit.jpa.mapper.mapper.UserInfoUnionDao;
import cn.pomit.jpamapper.core.domain.page.Page;
import cn.pomit.jpamapper.core.domain.page.Pageable;

@Service
public class UserInfoService {
	@Autowired
	UserInfoDao userInfoDao;
	@Autowired
	UserInfoHisDao userInfoHisDao;
	@Autowired
	UserInfoSortDao userInfoSortDao;
	@Autowired
	UserInfoUnionDao userInfoUnionDao;

	public List<UserInfo> selectAll() {
		return userInfoDao.selectAll();
	}
	
	public UserInfo getUserInfoByUserName(String userName) {
		return userInfoDao.findOne(userName);
	}

	public List<UserInfo> findByMobile(String mobile) {
		return userInfoDao.findByMobile(mobile);
	}

	public Collection<UserInfoHis> findByMobileSharding(String mobile) {
		UserInfoHis userInfoHis = new UserInfoHis();
		userInfoHis.setMobile(mobile);
		return userInfoHisDao.find(userInfoHis);
	}

	public Page<UserInfo> findByPage(String passwd) {
		Pageable pageable = new Pageable();
		pageable.setPage(1);
		pageable.setSize(5);
		Page<UserInfo> page = userInfoSortDao.pageByPasswd(passwd, pageable);
		return page;
	}

	public List<UserInfoUnion> findUnion(String mobile) {

		return userInfoUnionDao.findByMobile(mobile);
	}
}
