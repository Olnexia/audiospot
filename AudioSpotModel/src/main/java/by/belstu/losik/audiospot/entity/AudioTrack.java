package by.belstu.losik.audiospot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class AudioTrack {
    private static final long serialVersionUID = 1656277176990370384L;

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private Genre genre;

    @NotNull
    private BigDecimal price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    @NotNull
    private Artist artist;
}
