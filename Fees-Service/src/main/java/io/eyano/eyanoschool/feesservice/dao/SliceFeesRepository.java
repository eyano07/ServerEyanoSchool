package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.SliceFees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * The SliceFeesRepository interface is a repository that extends JpaRepository
 * for CRUD methods
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 * @see SliceFees
 */
public interface SliceFeesRepository extends JpaRepository<SliceFees, Long> {
    /**
     * This method is used to find SliceFees by id
     * @return Optional of SliceFees
     */
    Optional<SliceFees> findByIdAndRemoveIsFalse(Long id);
    /**
     * This method is used to find SliceFees that are removed by id
     * @return Optional of SliceFees
     */
    Optional<SliceFees> findByIdAndRemoveIsTrue(Long id);
    /**
     * This method is used to find all SliceFees
     * @return List of SliceFees
     */
    List<SliceFees> findSliceFeesByRemoveIsFalse();
    /**
     * This method is used to find all SliceFees that are removed
     * @return List of SliceFees
     */
    List<SliceFees> findSliceFeesByRemoveIsTrue();
    //---------------------------------------------------------------------------------------
    /**
     * This method is used to find all SliceFees by designation (tag)
     * @return List of SliceFees
     */
    List<SliceFees> findSliceFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(String tag);
    /**
     * This method is used to find all SliceFees by designation (tag) that are removed
     * @return List of SliceFees
     */
    List<SliceFees> findSliceFeesByDesignationIgnoreCaseContainsAndRemoveIsFalse(String tag);
    /**
     * This method is used to find all SliceFees by designation (tag) per page
     * @return List of SliceFees
     */
    Page<SliceFees> findSliceFeesByDesignationIgnoreCaseContainsAndRemoveIsFalse(String tag, Pageable pageable);
    /**
     * This method is used to find all SliceFees by designation (tag) that are removed per page
     * @return List of SliceFees
     */
    Page<SliceFees> findSliceFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(String tag, Pageable pageable);


}
