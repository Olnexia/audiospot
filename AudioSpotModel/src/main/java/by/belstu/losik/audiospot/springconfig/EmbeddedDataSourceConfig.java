package by.belstu.losik.audiospot.springconfig;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

import java.util.concurrent.TimeUnit;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScripts;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_latest;

/**
 * Class {@code EmbeddedDataSourceConfig} is configuration class which provides datasource
 * for repository testing with an in-memory embedded database
 */

@Configuration
public class EmbeddedDataSourceConfig {

    private boolean dbStarted = false;
    private EmbeddedMysql mySqlDb;

    @Profile("test")
    @Bean
    @DependsOn("embeddedMySql")
    public DataSource dataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("andrey");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3360/AudioSpot");
        return dataSource;
    }

    @Bean(destroyMethod = "stop")
    public EmbeddedMysql embeddedMySql() {
        if (!dbStarted) {
            MysqldConfig config = aMysqldConfig(v5_7_latest)
                    .withPort(3360)
                    .withUser("andrey", "")
                    .withTimeout(80, TimeUnit.SECONDS)
                    .build();
            mySqlDb = anEmbeddedMysql(config)
                    .addSchema("AudioSpot", classPathScripts("db/testDbInit.sql"))
                    .start();
            dbStarted = true;
        }
        return mySqlDb;
    }
}