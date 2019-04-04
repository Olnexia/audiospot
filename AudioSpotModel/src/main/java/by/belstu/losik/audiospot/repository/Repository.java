package by.belstu.losik.audiospot.repository;

import by.belstu.losik.audiospot.specification.Specification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@code Repository} interface provides the ability to work with some data storage
 *
 * @param <T> represents the type that Repository implementation works with
 */
public interface Repository<T> {

    /**
     * @param item Some instance of type {@code T} to be saved
     */
    void save(T item);

    /**
     * @param id Identifier to search item
     * @return {@code Optional} of found item if it exists or {@code Optional.empty()} otherwise
     */
    Optional<T> findById(Long id);

    /**
     * @param item Some instance of type {@code T} to be updated
     */
    void update(T item);

    /**
     * @param id Identifier of instance for removing
     */
    void remove(Long id);

    /**
     * @param specification A class that implements {@code Specification} interface
     *                      and can be used by {@code CriteriaBuilder} for searching items by {@code Predicate}
     * @return List of found items which fit the search criteria
     * @see Specification
     */
    List<T> findByCriteria(Specification<T> specification);

    /**
     * Paginated analog of {@code findByCriteria} method
     * @param pageNumber a number of current page
     * @param pageSize an amount of entities per page
     */
    List<T> findByCriteria(Specification<T> specification, int pageNumber, int pageSize);

    /**
     * An analog of {@code findByCriteria} method that returns just a single value
     * @return {@code Optional.of} value that matches search criteria or {@code Optional.empty} otherwise
     */
    Optional<T> findSingleResult(Specification<T> specification);

    /**
     * @param namedQuery A name of some {@code NamedQuery} or {@code NamedNativeQuery} from entity class
     * @param parameters Map of parameters with names of parameters as keys
     * @return List of found items which fit the namedQuery with search parameters
     */
    List<T> findWithNamedQuery(String namedQuery, Map<String, Object> parameters);

    /**
     * Paginated analog of {@code findWithNamedQuery} method
     * @param pageNumber a number of current page
     * @param pageSize an amount of entities per page
     */
    List<T> findWithNamedQuery(String queryName, Map<String, Object> parameters, int pageNumber, int pageSize);

    /**
     * @param nativeQuery SQL query with ? instead of real parameters
     * @param parameters  List of parameters for substitution
     * @return List of found items which fit the SQL query with search parameters
     */
    List<T> findWithNativeQuery(String nativeQuery, List<Object> parameters);

    /**
     * This method can be used to get single value of type {@code M} as a result of native query
     * @param nativeQuery a query that return a single result
     * @param parameters actual parameters of query
     * @param mClass result's class
     * @return query result
     */
    <M> M getSingleValue(String nativeQuery, Map<String, Object> parameters, Class<M> mClass);

    /**
     * This method can be used to get a number of entities that match the search criteria
     * @param specification the search criteria
     * @return a number of specified entities
     */
    Long countSpecified(Specification<T> specification);
}
