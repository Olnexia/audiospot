package by.belstu.losik.audiospot.specification;

import by.belstu.losik.audiospot.exception.NotImplementedMethodException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * {@code AndSpecification} class represents a combination of two specifications
 *
 * @param <T> A class for which search by criteria is used
 */

public class AndSpecification<T> implements Specification<T> {
    private Specification<T> first;
    private Specification<T> second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(
                first.toPredicate(root, criteriaBuilder),
                second.toPredicate(root, criteriaBuilder)
        );
    }

    @Override
    public Specification<T> and(Specification<T> other) {
        throw new NotImplementedMethodException();
    }
}
