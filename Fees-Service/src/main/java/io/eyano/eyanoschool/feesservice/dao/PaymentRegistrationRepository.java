package io.eyano.eyanoschool.feesservice.dao;

import io.eyano.eyanoschool.feesservice.entities.PaymentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
/**
 * The PaymentRegistrationRepository interface is a repository that extends JpaRepository
 * for CRUD methods
 * @version 1.0
 * @since 1.0
 * @author : Pascal Tshingila
 * @see PaymentRegistration
 */
public interface PaymentRegistrationRepository extends JpaRepository<PaymentRegistration, Long> {
    /**
     * This method is used to find PaymentRegistration that are removed by id
     * @return Optional of PaymentRegistration
     */
    Optional<PaymentRegistration> findByIdAndRemoveIsTrue(Long id);
    /**
     * This method is used to find PaymentRegistration by id
     * @return Optional of PaymentRegistration
     */
    Optional<PaymentRegistration> findByIdAndRemoveIsFalse(Long id);
    /**
     * This method is used to find all PaymentRegistration
     * @return Optional of PaymentRegistration
     */
    List<PaymentRegistration> findByRemoveIsFalse();
    /**
     * This method is used to find all PaymentRegistration that are removed
     * @return List of PaymentRegistration
     */
    List<PaymentRegistration> findByRemoveIsTrue();
    //---------------------------------------------------------------------------------
    /**
     * This method is used to find all PaymentRegistration by id Candidate
     * @return List of PaymentRegistration
     */
    public List<PaymentRegistration> findByRemoveIsFalseAndIdCandidate(Long idCandidate);
    /**
     * This method is used to find all PaymentRegistration that are removed by id Candidate
     * @return List of PaymentRegistration
     */
    public List<PaymentRegistration> findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear(Long idCandidate, Long idSchoolYear);
    /**
     * This method is used to find all PaymentRegistration that are removed by id Candidate
     * @return List of PaymentRegistration
     */
    public List<PaymentRegistration> findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear(Long idCandidate, Long idSchoolYear);
    /**
     * This method is used to find all PaymentRegistration by id User
     * @return List of PaymentRegistration
     */
    public List<PaymentRegistration> findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear(Long idUser, Long idSchoolYear);
    /**
     * This method is used to find all PaymentRegistration by id User
     * @return List of PaymentRegistration
     */
    public List<PaymentRegistration> findByRemoveIsFalseAndDate(LocalDate date);
    /**
     * This method is used to find all PaymentRegistration by id User
     * @return List of PaymentRegistration
     */
    public List<PaymentRegistration> findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear(Long idPaymentSystem, Long idSchoolYear);
}
