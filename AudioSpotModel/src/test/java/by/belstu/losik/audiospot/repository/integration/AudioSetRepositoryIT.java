package by.belstu.losik.audiospot.repository.integration;

import by.belstu.losik.audiospot.entity.AudioSet;
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

import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles("test")
@Transactional
public class AudioSetRepositoryIT {
    private static final Long EXPECTED_ID = 42L;
    private static final String EXPECTED_TITLE = "Cool test audioSet";
    private static final String EXPECTED_DESCRIPTION = "Really cool audioSet";
    private static final String EXPECTED_PHOTO = "testPhoto.jpg";
    private static final String EXPECTED_CHANGED_DESCRIPTION = "Not really cool audioSet anymore";

    private AudioSet EXPECTED_STORED_AUDIOSET = new AudioSet(EXPECTED_ID, EXPECTED_TITLE, EXPECTED_DESCRIPTION,
            EXPECTED_PHOTO, Collections.emptyList());

    private AudioSet EXPECTED_UPDATED_AUDIOSET = new AudioSet(EXPECTED_ID, EXPECTED_TITLE, EXPECTED_CHANGED_DESCRIPTION,
            EXPECTED_PHOTO, Collections.emptyList());

    @Autowired
    private Repository<AudioSet> audioSetRepository;

    @Test
    public void shouldFindAudioSet() {
        findAndAssertAudioSet(EXPECTED_ID, EXPECTED_STORED_AUDIOSET);
    }

    @Test
    public void shouldSaveAudioSet() {
        AudioSet testAudioSet = new AudioSet(null, EXPECTED_TITLE, EXPECTED_DESCRIPTION,
                EXPECTED_PHOTO, Collections.emptyList());

        audioSetRepository.save(testAudioSet);

        findAndAssertAudioSet(testAudioSet.getId(), testAudioSet);
    }

    @Test
    public void shouldUpdateAudioSet() {
        Optional<AudioSet> AudioSetOptional = audioSetRepository.findById(EXPECTED_ID);

        if (!AudioSetOptional.isPresent()) {
            Assert.fail();
        }

        AudioSet testAudioSet = AudioSetOptional.get();
        testAudioSet.setDescription(EXPECTED_CHANGED_DESCRIPTION);
        audioSetRepository.update(testAudioSet);

        findAndAssertAudioSet(EXPECTED_ID, EXPECTED_UPDATED_AUDIOSET);
    }

    @Test
    public void shouldDeleteAudioSet() {
        audioSetRepository.remove(EXPECTED_ID);
        Optional<AudioSet> AudioSetOptional = audioSetRepository.findById(EXPECTED_ID);
        Assert.assertFalse(AudioSetOptional.isPresent());
    }

    private void findAndAssertAudioSet(Long id, AudioSet expectedAudioSet) {
        Optional<AudioSet> audioSetOptional = audioSetRepository.findById(id);
        Assert.assertTrue(audioSetOptional.isPresent());

        Assert.assertEquals(expectedAudioSet, audioSetOptional.get());
    }
}