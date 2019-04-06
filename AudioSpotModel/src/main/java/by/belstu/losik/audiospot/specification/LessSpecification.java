package by.belstu.losik.audiospot.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class LessSpecification<T> extends AbstractSpecification<T> {

    public LessSpecification(String fieldName, Number value) {
        super(fieldName, value);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.lt(root.get(getFieldName()), (Number) getValue());
    }
}