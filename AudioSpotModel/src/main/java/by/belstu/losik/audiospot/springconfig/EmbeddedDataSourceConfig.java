package by.belstu.losik.audiospot.springconfig;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Class {@code EmbeddedDataSourceConfig} is configuration class which provides datasource
 * for repository testing with an in-memory embedded database
 *
 * @see com.epam.javalab.travelagency.springconfig.HikariDataSourceConfig
 */

@Configuration
public class EmbeddedDataSourceConfig {

    private boolean dbStarted = false;
    private EmbeddedPostgres postgres;

    @Bean
    @Profile("test")
    public DataSource dataSource() throws IOException {
        if (!dbStarted) {
            startDb();
        }
        return postgres.getPostgresDatabase();
    }

    /**
     * This methods need to start in-memory embedded database and migrate to actual scheme
     *
     * @throws IOException when temporary db files can not be created
     */
    private void startDb() throws IOException {
        postgres = EmbeddedPostgres.start();
        DataSource postgresDb = postgres.getPostgresDatabase();
        Flyway.configure().mixed(true).dataSource(postgresDb).load().migrate();
        dbStarted = true;
    }
}