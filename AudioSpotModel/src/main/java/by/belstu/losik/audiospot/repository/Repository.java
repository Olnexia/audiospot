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

    <M> M getSingleValue(String nativeQuery, Map<String, Object> parameters, Class<M> mClass);

    /**
     * This method can be used to get a number of entities that match the search criteria
     * @param specification the search criteria
     * @return a number of specified entities
     */
    Long countSpecified(Specification<T> specification);
}
