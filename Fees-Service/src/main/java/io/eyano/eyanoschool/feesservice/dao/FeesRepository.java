package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.Fees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The FeesRepository interface is a repository that extends JpaRepository
 * for CRUD methods
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 * @see Fees
 */
public interface FeesRepository extends JpaRepository<Fees, Long> {
    /**
     * This method is used to find fee by id
     * @return Optional of Fees
     */
    Optional<Fees> findByIdAndRemoveIsFalse(Long id);
    /**
     * This method is used to find fee that are removed by id
     * @return Optional of Fees
     */
    Optional<Fees> findByIdAndRemoveIsTrue(Long id);
    /**
     * This method is used to find all fee allocations
     * @return List of fees
     */
    List<Fees> findFeesByRemoveIsFalse();
    /**
     * This method is used to find all fee that are removed
     * @return List of fees
     */
    List<Fees> findFeesByRemoveIsTrue();

    //---------------------------------------------------------------------------------------

    /**
     * Find all fees that are not removed by id Class and id SchoolYear
     * @return List of fees
     */
    List<Fees> findByIdClassFessAndIdSchoolYearAndRemoveIsFalse(long idClass, long idSchoolYear);
    /**
     * Find all fees that are not removed by id Class and id SchoolYear and Designation (tag)
     * @return List of fees
     */
    List<Fees> findByDesignationIgnoreCaseContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear(String tag,long idClass, long idSchoolYear);
    /**
     * Find all fees that are removed by id Class and id SchoolYear and id Type Fees
     * @return List of fees
     */
    List<Fees> findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue(long idClass, long idSchoolYear,long idTypeFees);
    /**
     * Find all fees that are not removed by id Class and id SchoolYear and id Designation
     * @return List of fees
     */
    List<Fees> findByIdSchoolYearAndRemoveIsFalseAndDesignationIgnoreCaseContains(Long idSchoolYear, String designation);
    /**
     * Find all fees that are not removed by id School year and Designation per page
     * @return List of fees
     */
    Page<Fees> findByIdSchoolYearAndRemoveIsFalseAndDesignationIgnoreCaseContains(Long idSchoolYear,String tag, Pageable pageable);
    /**
     * Find all fees that are not removed by id Class and id School Year and id Type Fees
     * @return List of fees
     */
    List<Fees> findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess(long idClass,Long idSchoolYear, Long idTypeFees);
    /**
     * Find all fees that are not removed by Designation (tag) per page
     * @return List of fees
     */
    Page<Fees> findFeesByRemoveIsFalseAndDesignationIgnoreCaseContains(String tag, Pageable pageable);
    /**
     * Find all fees that are removed by id School year and Designation (tag) per page
     * @return List of fees
     */
    Page<Fees> findFeesByRemoveIsTrueAndDesignationIgnoreCaseContains(String tag, Pageable pageable);


}
