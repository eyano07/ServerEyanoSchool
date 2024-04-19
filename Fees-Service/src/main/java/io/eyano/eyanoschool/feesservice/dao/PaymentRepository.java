package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The PaymentRepository interface is a repository that extends JpaRepository
 * for CRUD methods
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 * @see Payment
 */
public interface PaymentRepository  extends JpaRepository<Payment, Long> {
    /**
     * This method is used to find Payment by id
     * @return Optional of Payment
     */
    Optional<Payment> findByIdAndRemoveIsFalse(Long id);
    /**
     * This method is used to find Payment that are removed by id
     * @return Optional of Payment
     */
    Optional<Payment> findByIdAndRemoveIsTrue(Long id);
    /**
     * This method is used to find all Payment
     * @return List of Payment
     */
    List<Payment> findByRemoveIsFalse();
    /**
     * This method is used to find all Payment that are removed
     * @return List of Payment
     */
    List<Payment> findByRemoveIsTrue();

    /**
     * This method is used to find all Payment per page
     * @return List of Payment
     */
    Page<Payment> findByRemoveIsFalse(Pageable pageable);
    //---------------------------------------------------------------------------------

    /**
     * This method is used to find all Payment by date
     * @return List of Payment
     */
    List<Payment> findByDateAndRemoveIsFalse(LocalDate date);
    /**
     * This method is used to find all Payment by date per page
     * @return List of Payment
     */
    Page<Payment> findByDateAndRemoveIsFalse(LocalDate date, Pageable pageable);
    /**
     * This method is used to find all Payment by id Pupil and id SchoolYear
     * @return List of Payment
     */
    List<Payment> findByIdPupilAndFeesIdSchoolYearAndRemoveIsFalse(Long idPupil, Long idSchoolYear);
    /**
     * This method is used to find all Payment by id User and id SchoolYear
     * @return List of Payment
     */
    List<Payment> findByIdUserAndFeesIdSchoolYearAndRemoveIsFalse(Long idUser, Long idSchoolYear);
    /**
     * This method is used to find all Payment by id PaymentSystem and id SchoolYear per page
     * @return List of Payment
     */
    Page<Payment> findByIdUserAndFeesIdSchoolYearAndRemoveIsFalse(Long idUser, Long idSchoolYear,Pageable pageable);
    /**
     * This method is used to find all Payment by id PaymentSystem and id SchoolYear
     * @return List of Payment
     */
    List<Payment> findByIdPaymentSystemAndFeesIdSchoolYearAndRemoveIsFalse(Long idPaymentSystem, Long idSchoolYear);
    /**
     * This method is used to find all Payment by id PaymentSystem and id SchoolYear per page
     * @return List of Payment
     */
    Page<Payment> findByIdPaymentSystemAndFeesIdSchoolYearAndRemoveIsFalse(Long idPaymentSystem, Long idSchoolYear,Pageable pageable);
    /**
     * This method is used to find all Payment that are removed by date
     * @return List of Payment
     */
    List<Payment> findByDateAndRemoveIsTrue(LocalDate date);
    /**
     * This method is used to find all Payment by id User and date
     * @return List of Payment
     */
    List<Payment> findByIdUserAndDateAndRemoveIsFalse(Long idUser, LocalDate date);
}
