package by.belstu.losik.audiospot.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * {@code Specification} interface encapsulates {@code Predicate} that can
 * be used by {@code FindByCriteria} method of {@code Repository} interface
 *
 * @param <T> A class for which search by criteria is used
 */

public interface Specification<T> {

    Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder);

    /**
     * @param other A specification to be combined with this
     * @return New specification (combination of this and other)
     */
    Specification<T> and(Specification<T> other);

}
