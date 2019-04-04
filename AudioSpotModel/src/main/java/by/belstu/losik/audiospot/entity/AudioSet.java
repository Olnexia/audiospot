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
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class AudioSet {
    private static final long serialVersionUID = 7455159949452216440L;

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;
}
