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
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Order {
    private static final long serialVersionUID = -2575420337998768374L;

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotNull
    private LocalDate orderDate;

    @NotNull
    private boolean paid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;
}
