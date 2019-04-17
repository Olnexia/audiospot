package by.belstu.losik.audiospot.repository;

import by.belstu.losik.audiospot.specification.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@code GenericRepository} class contains {@code Repository} interface methods implementations
 * that can be applied to each {@code T} class. This class connects to data
 * storage by {@code Hibernate} as JPA provider with {@code EntityManager} class instance.
 *
 * @see Repository
 */

public class GenericRepository<T> implements Repository<T> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> entityClass;

    public GenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    @Override
    public void save(T item) {
        entityManager.persist(item);
    }

    @Override
    public Optional<T> findById(Long id) {
        T searchResult = entityManager.find(entityClass, id);
        return Optional.ofNullable(searchResult);
    }

    @Transactional
    @Override
    public void update(T item) {
        entityManager.merge(item);
    }

    @Transactional
    @Override
    public void remove(Long id) {
        Optional<T> optionalItem = findById(id);
        optionalItem.ifPresent(t -> entityManager.remove(t));
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> query = entityManager.createQuery(createQuery());
        return query.getResultList();
    }

    @Override
    public List<T> findPage(int page, int size) {
        TypedQuery<T> query = entityManager.createQuery(createQuery());
        query.setFirstResult(getFirstResult(page,size));
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public List<T> findByCriteria(Specification<T> specification) {
        TypedQuery<T> query = prepareCriteriaQuery(specification);
        return query.getResultList();
    }

    @Override
    public List<T> findByCriteria(Specification<T> specification, int pageNumber, int pageSize) {
        TypedQuery<T> query = prepareCriteriaQuery(specification);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    private int getFirstResult(int page, int size){
        return (page - 1) * size;
    }

    private CriteriaQuery<T> createQuery(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        return criteriaBuilder.createQuery(entityClass);
    }

    private TypedQuery<T> prepareCriteriaQuery(Specification<T> specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);

        Predicate predicate = specification.toPredicate(root, criteriaBuilder);
        CriteriaQuery<T> select = criteriaQuery.select(root).where(predicate);
        return entityManager.createQuery(select);
    }

    @Override
    public Optional<T> findSingleResult(Specification<T> specification) {
        List<T> fullSearchResult = findByCriteria(specification);
        return fullSearchResult.isEmpty()
                ? Optional.empty()
                : Optional.of(fullSearchResult.get(0));
    }

    @Override
    public Long countSpecified(Specification<T> specification) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityClass);

        criteriaQuery.select(criteriaBuilder.countDistinct(root));
        criteriaQuery.where(specification.toPredicate(root, criteriaBuilder));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public <M> M getSingleValue(String nativeQuery, Map<String, Object> parameters, Class<M> mClass) {
        Query query = entityManager.createNativeQuery(nativeQuery);
        parameters.forEach(query::setParameter);
        return mClass.cast(query.getSingleResult());
    }
}