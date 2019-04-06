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
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity(name="audio_order")
@Data
@AllArgsConstructor
@NoArgsConstructor

@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Order {
    private static final long serialVersionUID = -2575420337998768374L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private boolean paid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    @NotNull
    private User user;

    @ManyToMany
    @JoinTable(
            name = "ordered_track",
            joinColumns = @JoinColumn(name = "audioOrderId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "audiotrackId", referencedColumnName = "id")
    )
    @ToString.Exclude
    private List<AudioTrack> tracks;
}
