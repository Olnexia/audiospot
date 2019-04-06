package by.belstu.losik.audiospot.repository.integration;

import by.belstu.losik.audiospot.entity.Artist;
import by.belstu.losik.audiospot.entity.AudioTrack;
import by.belstu.losik.audiospot.entity.Genre;
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

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles("test")
@Transactional
public class AudioTrackRepositoryIT {
    private static final Long EXPECTED_ID = 42L;
    private static final String EXPECTED_TITLE = "Test";
    private static final String EXPECTED_PATH = "localhost:test.mp3";
    private static final BigDecimal EXPECTED_PRICE = BigDecimal.valueOf(3.22);
    private static final BigDecimal EXPECTED_CHANGED_PRICE = BigDecimal.valueOf(4.22);
    private static final Genre EXPECTED_GENRE = Genre.ROCK;

    private Artist TEST_ARTIST = new Artist(42L, "John Doe", "Canada");

    private AudioTrack EXPECTED_STORED_TRACK = new AudioTrack(EXPECTED_ID, EXPECTED_TITLE, EXPECTED_PATH, EXPECTED_GENRE,
            EXPECTED_PRICE, null, TEST_ARTIST, Collections.emptyList(), Collections.emptyList());

    private AudioTrack EXPECTED_UPDATED_TRACK = new AudioTrack(EXPECTED_ID, EXPECTED_TITLE, EXPECTED_PATH, EXPECTED_GENRE,
            EXPECTED_CHANGED_PRICE, null, TEST_ARTIST, Collections.emptyList(), Collections.emptyList());

    @Autowired
    private Repository<AudioTrack> audioTrackRepository;

    @Test
    public void shouldFindAudioTrack() {
        findAndAssertAudioTrack(EXPECTED_ID, EXPECTED_STORED_TRACK);
    }

    @Test
    public void shouldSaveAudioTrack() {
        AudioTrack testAudioTrack = new AudioTrack(null, EXPECTED_TITLE, EXPECTED_PATH, EXPECTED_GENRE,
                EXPECTED_PRICE, null, TEST_ARTIST, Collections.emptyList(), Collections.emptyList());

        audioTrackRepository.save(testAudioTrack);

        findAndAssertAudioTrack(testAudioTrack.getId(), testAudioTrack);
    }

    @Test
    public void shouldUpdateAudioTrack() {
        Optional<AudioTrack> AudioTrackOptional = audioTrackRepository.findById(EXPECTED_ID);

        if (!AudioTrackOptional.isPresent()) {
            Assert.fail();
        }

        AudioTrack testAudioTrack = AudioTrackOptional.get();
        testAudioTrack.setPrice(EXPECTED_CHANGED_PRICE);
        audioTrackRepository.update(testAudioTrack);

        findAndAssertAudioTrack(EXPECTED_ID, EXPECTED_UPDATED_TRACK);
    }

    @Test
    public void shouldDeleteAudioTrack() {
        audioTrackRepository.remove(EXPECTED_ID);
        Optional<AudioTrack> AudioTrackOptional = audioTrackRepository.findById(EXPECTED_ID);
        Assert.assertFalse(AudioTrackOptional.isPresent());
    }

    private void findAndAssertAudioTrack(Long id, AudioTrack expectedAudioTrack) {
        Optional<AudioTrack> audioTrackOptional = audioTrackRepository.findById(id);
        Assert.assertTrue(audioTrackOptional.isPresent());

        Assert.assertEquals(expectedAudioTrack, audioTrackOptional.get());
    }
}
