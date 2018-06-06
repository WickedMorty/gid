package rest.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan
@EnableJpaRepositories(
        entityManagerFactoryRef = "bitrixEntityManagerFactory",
        transactionManagerRef = "bitrixTransactionManager",
        basePackages = { "bitrix.repository" }
)
public class BgnskDBConfig {
    @Bean(name = "bitrixDataSource")
    @ConfigurationProperties(prefix = "bitrix.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "bitrixEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    bitrixEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("bitrixDataSource") DataSource dataSource
    ) {
        return
                builder
                        .dataSource(dataSource)
                        .packages("bitrix.entity")
                        .persistenceUnit("bitrix")
                        .build();
    }
    @Bean(name = "bitrixTransactionManager")
    public PlatformTransactionManager bitrixTransactionManager(
            @Qualifier("bitrixEntityManagerFactory") EntityManagerFactory
                    bitrixEntityManagerFactory
    ) {
        return new JpaTransactionManager(bitrixEntityManagerFactory);
    }
}
