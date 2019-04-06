package by.belstu.losik.audiospot.repository.integration;

import by.belstu.losik.audiospot.entity.Artist;
import by.belstu.losik.audiospot.repository.Repository;
import by.belstu.losik.audiospot.springconfig.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles("test")
@Transactional
public class ArtistRepositoryIT {
    private static final Long EXPECTED_ID = 42L;
    private static final String EXPECTED_NAME = "John Doe";
    private static final String EXPECTED_COUNTRY = "Canada";
    private static final String EXPECTED_CHANGED_COUNTRY = "Belarus";

    private Artist EXPECTED_STORED_ARTIST = new Artist(EXPECTED_ID, EXPECTED_NAME, EXPECTED_COUNTRY);

    private Artist EXPECTED_UPDATED_ARTIST = new Artist(EXPECTED_ID, EXPECTED_NAME, EXPECTED_CHANGED_COUNTRY);

    @Autowired
    private Repository<Artist> artistRepository;

    @Test
    public void shouldFindArtist() {
        findAndAssertArtist(EXPECTED_ID, EXPECTED_STORED_ARTIST);
    }

    @Test
    public void shouldSaveArtist() {
        Artist testArtist = new Artist(null, EXPECTED_NAME, EXPECTED_COUNTRY);

        artistRepository.save(testArtist);

        findAndAssertArtist(testArtist.getId(), testArtist);
    }

    @Test
    public void shouldUpdateArtist() {
        Optional<Artist> ArtistOptional = artistRepository.findById(EXPECTED_ID);

        if (!ArtistOptional.isPresent()) {
            Assert.fail();
        }

        Artist testArtist = ArtistOptional.get();
        testArtist.setCountry(EXPECTED_CHANGED_COUNTRY);
        artistRepository.update(testArtist);

        findAndAssertArtist(EXPECTED_ID, EXPECTED_UPDATED_ARTIST);
    }

    @Test
    public void shouldDeleteArtist() {
        artistRepository.remove(EXPECTED_ID);
        Optional<Artist> ArtistOptional = artistRepository.findById(EXPECTED_ID);
        Assert.assertFalse(ArtistOptional.isPresent());
    }

    private void findAndAssertArtist(Long id, Artist expectedArtist) {
        Optional<Artist> artistOptional = artistRepository.findById(id);
        Assert.assertTrue(artistOptional.isPresent());

        Assert.assertEquals(expectedArtist, artistOptional.get());
    }
}
