package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.TypeFees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * The TypeFeesRepository interface is a repository that extends JpaRepository
 * for CRUD methods
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 * @see TypeFees
 */
public interface TypeFeesRepository  extends JpaRepository<TypeFees, Long> {
    /**
     * This method is used to find TypeFees by id
     * @return Optional of TypeFees
     */
    Optional<TypeFees> findByIdAndRemoveIsTrue(Long id);
    /**
     * This method is used to find TypeFees that are removed by id
     * @return Optional of TypeFees
     */
    Optional<TypeFees> findByIdAndRemoveIsFalse(Long id);
    /**
     * This method is used to find all TypeFees
     * @return List of TypeFees
     */
    List<TypeFees> findTypeFeesByRemoveIsFalse();
    /**
     * This method is used to find all TypeFees that are removed
     * @return List of TypeFees
     */
    List<TypeFees> findTypeFeesByRemoveIsTrue();
    //---------------------------------------------------------------------------------------
    /**
     * This method is used to find all TypeFees by designation (tag)
     * @return List of TypeFees
     */
    List<TypeFees> findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(String tag);
    /**
     * This method is used to find all TypeFees by designation (tag) that are removed
     * @return List of TypeFees
     */
    List<TypeFees> findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsFalse(String tag);
    /**
     * This method is used to find all TypeFees by designation (tag) per page
     * @return List of TypeFees
     */
    Page<TypeFees> findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsFalse(String tag, Pageable pageable);
    /**
     * This method is used to find all TypeFees by designation (tag) that are removed per page
     * @return List of TypeFees
     */
    Page<TypeFees> findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(String tag, Pageable pageable);

}
