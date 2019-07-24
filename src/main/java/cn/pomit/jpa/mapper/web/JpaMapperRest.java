package cn.pomit.jpa.mapper.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.pomit.jpa.mapper.domain.UserInfo;
import cn.pomit.jpa.mapper.domain.UserInfoHis;
import cn.pomit.jpa.mapper.domain.UserInfoUnion;
import cn.pomit.jpa.mapper.service.UserInfoService;
import cn.pomit.jpamapper.core.domain.page.Page;

@RestController
@RequestMapping("/jpamapper")
public class JpaMapperRest {

	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/all")
	public List<UserInfo> selectAll() {
		return userInfoService.selectAll();
	}
	
	@RequestMapping("/user")
	public UserInfo getUserInfoByUserName() {
		return userInfoService.getUserInfoByUserName("cff");
	}

	@RequestMapping("/findByMobile")
	public List<UserInfo> findByMobile() {
		return userInfoService.findByMobile("3242");
	}

	@RequestMapping("/findByMobileSharding")
	public Collection<UserInfoHis> findByMobileSharding() {
		return userInfoService.findByMobileSharding("3242");
	}

	@RequestMapping("/findByPage")
	public Page<UserInfo> findByPage() {
		return userInfoService.findByPage("123455");
	}

	@RequestMapping("/findUnion")
	public List<UserInfoUnion> findUnion() {
		return userInfoService.findUnion("3242");
	}
}
