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
public class User{
    private static final long serialVersionUID = 646513000665422990L;

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    private Long id;

    private String name;

    private String surname;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private Role role;

    @NotNull
    private boolean active;

    private double discount;
}
