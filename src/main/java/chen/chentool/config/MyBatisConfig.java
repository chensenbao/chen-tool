package chen.chentool.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description:
 * @author: sen
 */
@Configuration
@MapperScan(value = "chen.chentool.trainTicket.dao")
public class MyBatisConfig {

    @Value("${spring.datasource.type}")
    private String type;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DruidDataSource dataSource(){
        Connection connection;
        try {
        connection= DriverManager.getConnection(url,userName,password);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("连接失败");
        }
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDbType(type);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setInitialSize(5);
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxActive(200);
        druidDataSource.setMaxWait(30000);
        druidDataSource.setTimeBetweenConnectErrorMillis(30000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(30000);
        druidDataSource.setMinEvictableIdleTimeMillis(30000);
        druidDataSource.setValidationQuery("SELECT 1 FROM DUAL");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(true);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return druidDataSource;
    }
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:/mapper/**/*.xml"));
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setCallSettersOnNulls(true);
        bean.setConfiguration(config);
        return bean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
