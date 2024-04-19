package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.FeesAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * The FeesAllocationRepository interface is a repository that extends JpaRepository
 * for CRUD methods
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 * @see FeesAllocation
 */
public interface FeesAllocationRepository  extends JpaRepository<FeesAllocation, Long> {
    /**
     * This method is used to find fee allocations by id
     * @return Optional of Fees allocations
     */
    Optional<FeesAllocation> findByIdAndRemoveIsFalse(Long id);
    /**
     * This method is used to find fee allocations that are removed by id
     * @return Optional of Fees allocations
     */
    Optional<FeesAllocation> findByIdAndRemoveIsTrue(Long id);

    /**
     * This method is used to find all fee allocations
     * @return List of Fees allocations
     */
    List<FeesAllocation> findByRemoveIsFalse();
    /**
     * This method is used to find all fee allocations
     * @return List of Fees allocations
     */
    List<FeesAllocation> findByRemoveIsTrue();
//-----------------------------------------------------------------------------------------------------------------
    /**
     * This method is used to find all fee allocations by tag and school year
     * @return List of Fees allocations
     */
    List<FeesAllocation> findByRemoveFalseAndDesignationIgnoreCaseContainsAndIdSchoolYear(String tag, Long idSchoolYear);
    /**
     * This method is used to find all fee allocations by tag and school year that are removed
     * @return List of Fees allocations
     */
    List<FeesAllocation> findByRemoveTrueAndDesignationIgnoreCaseContainsAndIdSchoolYear(String tag, Long idSchoolYear);

    /**
     * This method is used to find all fee allocations by tag, school year and id type of fees
     * @return List of Fees allocations
     */
    List<FeesAllocation> findByRemoveFalseAndDesignationIgnoreCaseContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees);
    /**
     * This method is used to find all fee allocations by tag, school year and id type of fees that are removed
     * @return List of Fees allocations
     */
    List<FeesAllocation> findByRemoveTrueAndDesignationIgnoreCaseContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees);

}
