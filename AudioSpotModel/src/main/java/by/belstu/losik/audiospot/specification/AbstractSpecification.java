package by.belstu.losik.audiospot.specification;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@code AbstractSpecification} is a class that all concrete specification need to inherit
 *
 * @param <T> A class for which search by criteria is used
 */

@Getter
@AllArgsConstructor
public abstract class AbstractSpecification<T> implements Specification<T> {
    private String fieldName;
    private Object value;

    @Override
    public Specification<T> and(Specification<T> other) {
        return new AndSpecification<>(this, other);
    }

}
