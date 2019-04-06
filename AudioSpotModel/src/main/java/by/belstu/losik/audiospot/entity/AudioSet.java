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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name="audioset")
@Data
@AllArgsConstructor
@NoArgsConstructor

@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class AudioSet {
    private static final long serialVersionUID = 7455159949452216440L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotNull
    @Size(max = 45)
    private String title;

    @NotNull
    @Size(max = 200)
    private String description;

    @Size(max = 42)
    private String photo;

    @ManyToMany
    @JoinTable(
            name = "track_at_audioset",
            joinColumns = @JoinColumn(name = "audiosetId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "audiotrackId", referencedColumnName = "id")
    )
    @ToString.Exclude
    private List<AudioTrack> tracks;
}
