package com.epam.audiospot.repository;

import by.belstu.losik.audiospot.entity.*;
import com.epam.audiospot.repository.utils.StatementBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StatementBuilderTest {
    private static final String TABLE_NAME = "ordered_track";
    private static final Long TEST_ID = 42L;
    private static final String DELETE_EXPECTED_QUERY = "DELETE FROM ordered_track WHERE ordered_track_id=42;";
    private static final String INSERT_EXPECTED_QUERY = "INSERT INTO ordered_track (ordered_track_id,audio_order_id," +
            "audiotrack_id) VALUES(?,?,?);";
    private static final String UPDATE_EXPECTED_QUERY = "UPDATE ordered_track SET audiotrack_id=?,audio_order_id=?" +
            ",ordered_track_id=? WHERE ordered_track_id=42;";
    private StatementBuilder builder = new StatementBuilder(TABLE_NAME);

    @Test
    public void shouldBuildDeleteStatement() {
        //given
        //when
        String actualQuery = builder.buildDeleteQuery(TEST_ID);
        //then
        Assert.assertEquals(DELETE_EXPECTED_QUERY, actualQuery);
    }

    @Test
    public void shouldBuildInsertStatement() {
        //given
        Map <String, Object> fields = new LinkedHashMap <>();
        fields.put(OrderedTrack.ID_LABEL, 42L);
        fields.put(OrderedTrack.ORDER_ID_LABEL, 21L);
        fields.put(OrderedTrack.AUDIOTRACK_LABEL, 44L);

        //when
        String actualQuery = builder.buildInsertQuery(fields.entrySet());
        //then
        Assert.assertEquals(INSERT_EXPECTED_QUERY, actualQuery);
    }

    @Test
    public void shouldUpdateStatement() {
        //given
        Map <String, Object> fields = new HashMap <>();
        fields.put(OrderedTrack.ID_LABEL, 42L);
        fields.put(OrderedTrack.ORDER_ID_LABEL, 21L);
        fields.put(OrderedTrack.AUDIOTRACK_LABEL, 44L);
        //when
        String actualQuery = builder.buildUpdateQuery(TEST_ID, fields);
        //then
        Assert.assertEquals(UPDATE_EXPECTED_QUERY, actualQuery);
    }
}
