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
