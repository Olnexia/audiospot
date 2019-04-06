package by.belstu.losik.audiospot.repository.integration;

import by.belstu.losik.audiospot.entity.Album;
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
public class AlbumRepositoryIT {
    private static final Long EXPECTED_ID = 42L;
    private static final String EXPECTED_TITLE = "Cool test album";
    private static final String EXPECTED_PHOTO = "testPhoto.jpg";
    private static final int EXPECTED_RELEASE_YEAR = 2019;
    private static final String EXPECTED_CHANGED_TITLE = "Not so cool test album";

    private Artist TEST_ARTIST = new Artist(EXPECTED_ID, "John Doe", "Canada");

    private Album EXPECTED_STORED_ALBUM = new Album(EXPECTED_ID, EXPECTED_TITLE, EXPECTED_PHOTO,
            EXPECTED_RELEASE_YEAR, TEST_ARTIST);

    private Album EXPECTED_UPDATED_ALBUM = new Album(EXPECTED_ID, EXPECTED_CHANGED_TITLE,
            EXPECTED_PHOTO, EXPECTED_RELEASE_YEAR, TEST_ARTIST);


    @Autowired
    private Repository<Album> albumRepository;

    @Test
    public void shouldFindAlbum() {
        findAndAssertAlbum(EXPECTED_ID, EXPECTED_STORED_ALBUM);
    }

    @Test
    public void shouldSaveAlbum() {
        Album testAlbum = new Album(null, EXPECTED_TITLE, EXPECTED_PHOTO, EXPECTED_RELEASE_YEAR, TEST_ARTIST);

        albumRepository.save(testAlbum);

        findAndAssertAlbum(testAlbum.getId(), testAlbum);
    }

    @Test
    public void shouldUpdateAlbum() {
        Optional<Album> albumOptional = albumRepository.findById(EXPECTED_ID);

        if (!albumOptional.isPresent()) {
            Assert.fail();
        }

        Album testAlbum = albumOptional.get();
        testAlbum.setTitle(EXPECTED_CHANGED_TITLE);
        albumRepository.update(testAlbum);

        findAndAssertAlbum(EXPECTED_ID, EXPECTED_UPDATED_ALBUM);
    }

    @Test
    public void shouldDeleteAlbum() {
        albumRepository.remove(EXPECTED_ID);
        Optional<Album> albumOptional = albumRepository.findById(EXPECTED_ID);
        Assert.assertFalse(albumOptional.isPresent());
    }

    private void findAndAssertAlbum(Long id, Album expectedAlbum) {
        Optional<Album> albumOptional = albumRepository.findById(id);
        Assert.assertTrue(albumOptional.isPresent());

        Assert.assertEquals(expectedAlbum, albumOptional.get());
    }
}
