package by.belstu.losik.audiospot.repository.integration;

import by.belstu.losik.audiospot.entity.Artist;
import by.belstu.losik.audiospot.entity.AudioTrack;
import by.belstu.losik.audiospot.entity.Comment;
import by.belstu.losik.audiospot.entity.Genre;
import by.belstu.losik.audiospot.entity.Role;
import by.belstu.losik.audiospot.entity.User;
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
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles("test")
@Transactional
public class CommentRepositoryIT {
    private static final Long EXPECTED_ID = 42L;
    private static final String EXPECTED_TEXT = "I liked it";
    private static final LocalDateTime EXPECTED_DATE_TIME = LocalDateTime.of(2019, 4, 6, 21, 52);
    private static final String EXPECTED_CHANGED_TEXT = "It's awesome!";

    private User TEST_USER = new User(42L, "John", "Doe", "testLogin",
            "testPass", Role.CLIENT, true, 2.75f);

    private Artist TEST_ARTIST = new Artist(42L, "John Doe", "Canada");


    private AudioTrack TEST_TRACK = new AudioTrack(42L, "Test", "localhost:test.mp3",
            Genre.ROCK, BigDecimal.valueOf(3.22), null, TEST_ARTIST, Collections.emptyList(), Collections.emptyList());

    private Comment EXPECTED_STORED_COMMENT = new Comment(EXPECTED_ID, EXPECTED_TEXT, EXPECTED_DATE_TIME,TEST_USER, TEST_TRACK);

    private Comment EXPECTED_UPDATED_COMMENT = new Comment(EXPECTED_ID, EXPECTED_CHANGED_TEXT, EXPECTED_DATE_TIME,TEST_USER, TEST_TRACK);

    @Autowired
    private Repository<Comment> commentRepository;

    @Test
    public void shouldFindComment() {
        findAndAssertComment(EXPECTED_ID, EXPECTED_STORED_COMMENT);
    }

    @Test
    public void shouldSaveComment() {
        Comment testComment = new Comment(null, EXPECTED_TEXT, EXPECTED_DATE_TIME,TEST_USER, TEST_TRACK);

        commentRepository.save(testComment);

        findAndAssertComment(testComment.getId(), testComment);
    }

    @Test
    public void shouldUpdateComment() {
        Optional<Comment> CommentOptional = commentRepository.findById(EXPECTED_ID);

        if (!CommentOptional.isPresent()) {
            Assert.fail();
        }

        Comment testComment = CommentOptional.get();
        testComment.setText(EXPECTED_CHANGED_TEXT);
        commentRepository.update(testComment);

        findAndAssertComment(EXPECTED_ID, EXPECTED_UPDATED_COMMENT);
    }

    @Test
    public void shouldDeleteComment() {
        commentRepository.remove(EXPECTED_ID);
        Optional<Comment> CommentOptional = commentRepository.findById(EXPECTED_ID);
        Assert.assertFalse(CommentOptional.isPresent());
    }

    private void findAndAssertComment(Long id, Comment expectedComment) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        Assert.assertTrue(commentOptional.isPresent());

        Assert.assertEquals(expectedComment, commentOptional.get());
    }
}
