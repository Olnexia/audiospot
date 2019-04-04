package by.belstu.losik.audiospot.springconfig;

import by.belstu.losik.audiospot.entity.Album;
import by.belstu.losik.audiospot.entity.Artist;
import by.belstu.losik.audiospot.entity.AudioSet;
import by.belstu.losik.audiospot.entity.AudioTrack;
import by.belstu.losik.audiospot.entity.Comment;
import by.belstu.losik.audiospot.entity.Order;
import by.belstu.losik.audiospot.entity.User;
import by.belstu.losik.audiospot.repository.GenericRepository;
import by.belstu.losik.audiospot.repository.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("by.belstu.losik.audiospot")
public class AppConfig {

    @Bean
    Repository<Album> albumRepository() {
        return new GenericRepository<>(Album.class);
    }

    @Bean
    Repository<Artist> artistRepository() {
        return new GenericRepository<>(Artist.class);
    }

    @Bean
    Repository<AudioSet> audioSetRepository() {
        return new GenericRepository<>(AudioSet.class);
    }

    @Bean
    Repository<AudioTrack> audioTrackRepository() {
        return new GenericRepository<>(AudioTrack.class);
    }

    @Bean
    Repository<Comment> commentRepository() {
        return new GenericRepository<>(Comment.class);
    }

    @Bean
    Repository<Order> orderRepository() {
        return new GenericRepository<>(Order.class);
    }

    @Bean
    Repository<User> userRepository() {
        return new GenericRepository<>(User.class);
    }
}
