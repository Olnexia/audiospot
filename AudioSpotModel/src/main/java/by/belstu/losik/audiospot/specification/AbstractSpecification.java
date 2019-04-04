package by.belstu.losik.audiospot.specification;

/**
 * {@code AbstractSpecification} is a class that all concrete specification need to inherit
 *
 * @param <T> A class for which search by criteria is used
 */

public abstract class AbstractSpecification<T> implements Specification<T> {
    private Object value;

    public AbstractSpecification(Object value) {
        this.value = value;
    }

    @Override
    public Specification<T> and(Specification<T> other) {
        return new AndSpecification<>(this, other);
    }

    public Object getValue() {
        return value;
    }
}
