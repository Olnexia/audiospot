package by.belstu.losik.audiospot.dto;

import by.belstu.losik.audiospot.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackSearchDto {
    private String title;
    private Genre genre;
    private BigDecimal priceLowerBound;
    private BigDecimal priceUpperBound;
    private String album;
    private String artist;
}
