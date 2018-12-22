package com.epam.audiospot.repository;

import com.epam.audiospot.builder.ArtistBuilder;
import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.connection.ConnectionWrapper;
import com.epam.audiospot.entity.Artist;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.repository.specification.Specification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistRepository extends AbstractRepository<Artist> {
    private static final String TABLE_NAME = "artist";

    public ArtistRepository(ConnectionWrapper connection){
        super(connection);
    }

    @Override
    public Builder<Artist> getBuilder() {
        return new ArtistBuilder();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Map<String, Object> getFields(Artist artist) {
        Map<String,Object> fields = new HashMap <>();
        fields.put(Artist.ID_LABEL,artist.getId());
        fields.put(Artist.NAME_LABEL,artist.getName());
        fields.put(Artist.COUNTRY_LABEL,artist.getCountry());
        return fields;
    }
}
