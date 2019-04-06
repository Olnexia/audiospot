package by.belstu.losik.audiospot.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class EqualsSpecification<T> extends AbstractSpecification<T> {

    public EqualsSpecification(String fieldName, Object value) {
        super(fieldName, value);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get(getFieldName()), getValue());
    }
}
