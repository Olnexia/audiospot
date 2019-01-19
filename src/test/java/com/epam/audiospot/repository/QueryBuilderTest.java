package com.epam.audiospot.repository;

import com.epam.audiospot.entity.*;
import com.epam.audiospot.repository.utils.QueryBuilder;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class QueryBuilderTest {
    private static final String TABLE_NAME = "ordered_track";
    private static final String DELETE_EXPECTED_QUERY = "DELETE FROM ordered_track WHERE ordered_track_id='42';";
    private static final String INSERT_EXPECTED_QUERY = "INSERT INTO ordered_track (audiotrack_id,audio_order_id," +
                                                        "ordered_track_id) VALUES('44','21','42');";
    private static final String UPDATE_EXPECTED_QUERY = "UPDATE ordered_track SET audiotrack_id='44',audio_order_id=" +
                                                        "'21',ordered_track_id='42' WHERE ordered_track_id=42;";
    private QueryBuilder<OrderedTrack> builder = new QueryBuilder <>(TABLE_NAME);

    @Test
    public void shouldBuildDeleteQuery(){
        //given
        OrderedTrack testOrderedTrack = new OrderedTrack(42L,21L,44L);
        //when
        String actualQuery = builder.buildDeleteQuery(testOrderedTrack);
        //then
        Assert.assertEquals(DELETE_EXPECTED_QUERY,actualQuery);
    }

    @Test
    public void shouldBuildInsertQuery(){
        //given
        Map<String,Object> fields = new HashMap<>();
        fields.put(OrderedTrack.ID_LABEL,42L);
        fields.put(OrderedTrack.ORDER_ID_LABEL,21L);
        fields.put(OrderedTrack.AUDIOTRACK_LABEL,44L);
        //when
        String actualQuery = builder.buildInsertQuery(fields);
        //then
        Assert.assertEquals(INSERT_EXPECTED_QUERY,actualQuery);
    }

    @Test
    public void shouldUpdateQuery(){
        //given
        OrderedTrack testOrderedTrack = new OrderedTrack(42L,21L,44L);
        Map<String,Object> fields = new HashMap<>();
        fields.put(OrderedTrack.ID_LABEL,42L);
        fields.put(OrderedTrack.ORDER_ID_LABEL,21L);
        fields.put(OrderedTrack.AUDIOTRACK_LABEL,44L);
        //when
        String actualQuery = builder.buildUpdateQuery(testOrderedTrack,fields);
        //then
        Assert.assertEquals(UPDATE_EXPECTED_QUERY,actualQuery);
    }
}
