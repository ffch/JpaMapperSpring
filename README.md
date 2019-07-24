## JpaMapperSpring简介

JpaMapper的Spring demo项目。

## [Gitee-JpaMapper](https://gitee.com/ffch/JpaMapper)
## [Github-JpaMapper](https://github.com/ffch/jpa-mapper/)
## [JpaMapper官方地址](https://www.pomit.cn/jpa-mapper)

## 主要功能

JpaMapper是尽量按照JPA hibernate的书写风格，对mybatis进行封装，是CRUD操作更加简单易用，免于不断写sql。

该项目是JpaMapper的Spring项目。

## 注意事项

Spring项目使用JpaMapper，需要配置JpaMapper的Mapper扫描绑定sql。因此需要手动config。

而且，Spring在service生成的时候才生成mapper，并添加到SqlSessionFactory中，我们需要将MapperScanner的操作调整到service之后。所以有两种方案：

### 方案一

如下所示，将MapperScanner作为bean生成，需要使用@Order将MapperScanner生成顺序调整到最低，以便在生成mapper的所有bean之后再初始化。

```java
package cn.pomit.jpa.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import cn.pomit.jpamapper.core.MapperScanner;
import cn.pomit.jpamapper.core.mapper.register.MappedStatementRegister;

/**
 * Mapper 配置
 *
 * @author cff
 */
@Configuration
// @DependsOn("userInfoService")
public class JpaMapperConfig {

	@Bean
	@Order
	public MapperScanner mapperScanner(List<SqlSessionFactory> sqlSessionFactoryList) throws SQLException {

		MapperScanner mapperScanner = new MapperScanner();
		for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
			org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
			MapperRegistry mapperRegistry = configuration.getMapperRegistry();
			List<Class<?>> mappers = new ArrayList<>(mapperRegistry.getMappers());
			MappedStatementRegister mappedStatementRegister = new MappedStatementRegister(configuration);
			mappedStatementRegister.addMappers(mappers);
			mapperScanner.addMappedStatementRegister(mappedStatementRegister);
		}
		mapperScanner.scanAndRegisterJpaMethod();

		return mapperScanner;

	}
}
```

### 方案二

将JpaMapperConfig的bean生成时间使用@DependsOn("userInfoService")调整顺序到userInfoService之后。

```java
package cn.pomit.jpa.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import cn.pomit.jpamapper.core.MapperScanner;
import cn.pomit.jpamapper.core.mapper.register.MappedStatementRegister;

/**
 * Mapper 配置
 *
 * @author cff
 */
@Component
@DependsOn("userInfoService")
public class JpaMapperConfig {
	
	@Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;
    
    @PostConstruct
    public void addPageInterceptor() {
    	try{
	        MapperScanner mapperScanner = new MapperScanner();
	        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
	        	org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
	        	MapperRegistry mapperRegistry = configuration.getMapperRegistry();
	        	List<Class<?>> mappers = new ArrayList<>(mapperRegistry.getMappers());
	        	MappedStatementRegister mappedStatementRegister = new MappedStatementRegister(configuration);
	        	mappedStatementRegister.addMappers(mappers);
	        	mapperScanner.addMappedStatementRegister(mappedStatementRegister);
	        }
	        
	        mapperScanner.scanAndRegisterJpaMethod();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}

```

## 作者信息
      
   作者博客：https://blog.csdn.net/feiyangtianyao
  
  个人网站：https://www.pomit.cn
 
   作者邮箱： fufeixiaoyu@163.com

## License
Apache License V2

