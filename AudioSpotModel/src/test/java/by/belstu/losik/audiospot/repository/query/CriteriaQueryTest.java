package by.belstu.losik.audiospot.repository.query;

import by.belstu.losik.audiospot.entity.Artist;
import by.belstu.losik.audiospot.entity.AudioTrack;
import by.belstu.losik.audiospot.entity.Genre;
import by.belstu.losik.audiospot.repository.Repository;
import by.belstu.losik.audiospot.specification.EqualsSpecification;
import by.belstu.losik.audiospot.specification.LessSpecification;
import by.belstu.losik.audiospot.specification.Specification;
import by.belstu.losik.audiospot.springconfig.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles("test")
@Transactional
public class CriteriaQueryTest {

    private static final Long EXPECTED_ID = 42L;
    private static final String EXPECTED_TITLE = "Test";
    private static final String EXPECTED_PATH = "localhost:test.mp3";
    private static final BigDecimal EXPECTED_PRICE = BigDecimal.valueOf(3.22);
    private static final BigDecimal EXPECTED_UPPER_BOUND = BigDecimal.valueOf(5);
    private static final Genre EXPECTED_GENRE = Genre.ROCK;

    private Artist TEST_ARTIST = new Artist(42L, "John Doe", "Canada");

    private AudioTrack EXPECTED_STORED_TRACK = new AudioTrack(EXPECTED_ID, EXPECTED_TITLE, EXPECTED_PATH, EXPECTED_GENRE,
            EXPECTED_PRICE, null, TEST_ARTIST, Collections.emptyList(), Collections.emptyList());

    @Autowired
    private Repository<AudioTrack> audioTrackRepository;

    @Test
    public void shouldFindBySingleCriteria() {
        Specification<AudioTrack> titleEqualsCriteria = new EqualsSpecification<>("title", EXPECTED_TITLE);

        List<AudioTrack> actualTracks = audioTrackRepository.findByCriteria(titleEqualsCriteria);

        Assert.assertEquals(1, actualTracks.size());
        AudioTrack actualTrack = actualTracks.get(0);
        Assert.assertEquals(EXPECTED_STORED_TRACK, actualTrack);
    }

    @Test
    public void shouldFindByMultipleCriteria() {
        Specification<AudioTrack> titleEqualsCriteria = new EqualsSpecification<>("title", EXPECTED_TITLE);
        Specification<AudioTrack> priceEqualsCriteria = new LessSpecification<>("price", EXPECTED_UPPER_BOUND);

        Specification<AudioTrack> compositeCriteria = titleEqualsCriteria.and(priceEqualsCriteria);

        List<AudioTrack> actualTracks = audioTrackRepository.findByCriteria(compositeCriteria);

        Assert.assertEquals(1, actualTracks.size());
        AudioTrack actualTrack = actualTracks.get(0);
        Assert.assertEquals(EXPECTED_STORED_TRACK, actualTrack);
    }
}
