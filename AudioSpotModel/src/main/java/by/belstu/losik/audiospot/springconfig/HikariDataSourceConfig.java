package by.belstu.losik.audiospot.springconfig;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Class {@code HikariDataSourceConfig} is configuration class which provides beans for
 * access by Hikari connection pool to corresponding database
 */

@Configuration
@PropertySource("classpath:config.properties")
public class HikariDataSourceConfig {
    private static final String URL_PROP = "jdbcUrl";
    private static final String USER_PROP = "user";
    private static final String PASS_PROP = "password";
    private static final String DRIVER_PROP = "driver";

    private final Environment env;

    @Autowired
    public HikariDataSourceConfig(Environment env) {
        this.env = env;
    }

    @Bean(destroyMethod = "close")
    @Profile("prod")
    public HikariDataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty(URL_PROP));
        config.setUsername(env.getProperty(USER_PROP));
        config.setPassword(env.getProperty(PASS_PROP));
        config.setDriverClassName(env.getProperty(DRIVER_PROP));
        return new HikariDataSource(config);
    }
}
