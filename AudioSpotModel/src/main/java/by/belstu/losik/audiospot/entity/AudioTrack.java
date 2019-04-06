package by.belstu.losik.audiospot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity(name="audiotrack")
@Data
@AllArgsConstructor
@NoArgsConstructor

@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class AudioTrack {
    private static final long serialVersionUID = 1656277176990370384L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotNull
    @Size(max = 42)
    private String title;

    @NotNull
    @Size(max = 42)
    private String path;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "albumId")
    private Album album;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artistId")
    @NotNull
    private Artist artist;

    @ManyToMany(mappedBy = "tracks")
    @ToString.Exclude
    private List<AudioSet> audioSets;

    @ManyToMany(mappedBy = "tracks")
    @ToString.Exclude
    private List<Order> orders;
}
