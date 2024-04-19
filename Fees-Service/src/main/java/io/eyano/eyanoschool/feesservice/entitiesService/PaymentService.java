package io.eyano.eyanoschool.feesservice.entitiesService;

import io.eyano.eyanoschool.feesservice.dao.PaymentRepository;
import io.eyano.eyanoschool.feesservice.dtos.PaymentDto;
import io.eyano.eyanoschool.feesservice.entities.Payment;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.mappers.PaymentMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
    * PaymentService is a service that allows you to manage payments
    * @author : Pascal Tshingila
    * @since : 02/02/2021
    * @version : 1.0
 */
@Service @Transactional
@AllArgsConstructor @Slf4j
public class PaymentService implements CrudService<PaymentDto,Long> {
    PaymentRepository paymentRepository;
    PaymentMapper mapper;

    /**
     * implementation of the methode save from the CrudService interface for saving a paymentDto
     * @param entity : the paymentDto to save
     * @return PaymentDto : the paymentDto saved
     */
    @Override
    public PaymentDto save(PaymentDto entity) {
        log.info("execution of the method:save(PaymentDto entity)");
        Payment payment = mapper.dtoFromEntity(entity);
        PaymentDto paymentDto = mapper.entityFromDTO(paymentRepository.save(payment));
        log.info("the creation of the entity : {" + paymentDto + "}");
        return paymentDto;
    }

    /**
     * implementation of the methode update from the CrudService interface for updating a paymentDto
     * @param entity : the paymentDto to update
     * @return PaymentDto : the paymentDto updated
     * @throws IdNotFoundException : if the paymentDto is not found
     */
    @Override
    public PaymentDto update(PaymentDto entity) throws IdNotFoundException {
        log.info("execution of the method:update(PaymentDto entity)");
        paymentRepository.findById(entity.getId()).orElseThrow(IdNotFoundException::new);
        PaymentDto paymentDto = save(entity);
        log.info("entity update : {" + paymentDto + "}");
        return paymentDto;
    }

    /**
     * implementation of the methode findById from the CrudService interface for getting a paymentDto by id
     * @param id : the id of the paymentDto
     * @return PaymentDto : the paymentDto found
     * @throws IdNotFoundException : if the paymentDto is not found
     */
    public PaymentDto findById(Long id) throws IdNotFoundException {
        log.info("execution of the method:findById(Long id)");
        Payment payment = paymentRepository.findById(id).orElseThrow(IdNotFoundException::new);
        PaymentDto paymentDto = mapper.entityFromDTO(payment);
        log.info("the entity : {" + paymentDto + "} is found in the database");
        return paymentDto;
    }

    /**
     * implementation of the methode remove from the CrudService interface for deleting a paymentDto
     * @param entity : the paymentDto to delete
     * @return boolean : true if the paymentDto is deleted
     * @throws IdNotFoundException : if the paymentDto is not found
     */
    @Override
    public boolean remove(PaymentDto entity) throws IdNotFoundException {
        log.info("execution of the method:remove(PaymentDto entity)");
        Payment payment = paymentRepository.findById(entity.getId()).orElseThrow(IdNotFoundException::new);
        PaymentDto paymentDto = mapper.entityFromDTO(payment);
        paymentDto.setRemove(true);
        boolean remove = update(paymentDto).isRemove();
        log.info("the entity : {" + paymentDto + "} is deleted in the database");
        return remove;
    }

    /**
     * implementation of the methode removeById from the CrudService interface for deleting a paymentDto by id
     * @param id : the id of the paymentDto to delete
     * @return boolean : true if the paymentDto is deleted
     * @throws IdNotFoundException : if the paymentDto is not found
     */

    public boolean removeById(Long id) throws IdNotFoundException {
        log.info("execution of the method:removeById(Long id)");
        Payment payment = paymentRepository.findById(id).orElseThrow(IdNotFoundException::new);
        PaymentDto paymentDto = mapper.entityFromDTO(payment);
        paymentDto.setRemove(true);
        boolean remove = update(paymentDto).isRemove();
        log.info("the entity : {" + paymentDto + "} is deleted in the database from id");
        return remove;
    }

    /**
     * implementation of the methode restore from the CrudService interface for restoring a paymentDto
     * @param id : the id of the paymentDto to restore
     * @return boolean : true if the paymentDto is restored
     * @throws IdNotFoundException : if the paymentDto is not found
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException {
        log.info("execution of the method:restore(Long id)");
        Payment payment = paymentRepository.findById(id).orElseThrow(IdNotFoundException::new);
        PaymentDto paymentDto = mapper.entityFromDTO(payment);
        paymentDto.setRemove(false);
        update(paymentDto);
        log.info("the entity : {" + paymentDto + "} is restored in the database");
        return true;
    }

    /**
     * implementation of the methode isExist from the CrudService interface for checking if a paymentDto exists
     * @param id : the id of the paymentDto to check
     * @return boolean : true if the paymentDto exists
     * @throws IdNotFoundException : if the paymentDto is not found
     */
    @Override
    public boolean isExist(Long id) throws IdNotFoundException {
        log.info("execution of the method:isExist(Long id)");
        Payment payment = paymentRepository.findById(id).orElseThrow(IdNotFoundException::new);
        log.info("the entity : {" + payment + "} exists in the database");
        return true;
    }

    /**
     * implementation of the methode isRemove from the CrudService interface for checking if a paymentDto is deleted
     * @param id : the id of the paymentDto to check
     * @return boolean : true if the paymentDto is deleted
     * @throws IdNotFoundException : if the paymentDto is not found
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException {
        log.info("execution of the method:isRemove(Long id)");
        Payment payment = paymentRepository.findById(id).orElseThrow(IdNotFoundException::new);
        boolean remove = payment.isRemove();
        log.info("the remove attribute of the entity {" + payment + "} is : " + remove);
        return remove;
    }

    @Override
    public List<PaymentDto> findAll() {
        log.info("execution of the method:findAll()");
        List<PaymentDto> paymentDtos = mapper.entitiesFromDtos(paymentRepository.findByRemoveIsFalse());
        log.info("end of method execution:findAll()");
        return paymentDtos;
    }

    /**
     * implementation of the methode findAllPage for getting all paymentDtos with pagination
     * @param page : the number of the page
     * @param size : the size of the page
     * @return Map<String, Object> : the map of all paymentDtos with pagination
     */
    public Map<String, Object> findAllPage(int page, int size) {
        log.info("execution of the method:findAllPage(Pageable pageable)");
        Page<Payment> payments = paymentRepository.findByRemoveIsFalse(PageRequest.of(page, size));
        Map<String, Object> map = mapper.entitiesFromDtosPage(payments);
        log.info("end of method execution:findAllPage(Pageable pageable)");
        return map;
    }

    /**
     * implementation of the methode findByDate for getting all paymentDtos by date
     * @param date : the date of the paymentDtos
     * @return List<Payment> : the list of all paymentDtos by date
     */
    public List<PaymentDto> findByDate(LocalDate date) {
        log.info("execution of the method:findByDate(LocalDate date)");
        List<PaymentDto> paymentDtos = mapper.entitiesFromDtos(paymentRepository.findByDateAndRemoveIsFalse(date));
        log.info("end of method execution:findByDate(LocalDate date)");
        return paymentDtos;
    }

    /**
     * implementation of the methode findByDate for getting all paymentDtos by date with pagination
     * @param date : the date of the paymentDtos
     * @param page : the number of the page
     * @param size : the size of the page
     * @return Map<String, Object> : the map of all paymentDtos by date with pagination
     */
    public Map<String, Object> findByDate(LocalDate date, int page, int size) {
        log.info("execution of the method:findByDate(LocalDate date, Pageable pageable)");
        Page<Payment> payments = paymentRepository.findByDateAndRemoveIsFalse(date, PageRequest.of(page, size));
        Map<String, Object> map = mapper.entitiesFromDtosPage(payments);
        log.info("end of method execution:findByDate(LocalDate date, Pageable pageable)");
        return map;
    }

    /**
     * implementation of the methode findByIdPupilAndFeesIdSchoolYear for getting all paymentDtos by idPupil and idSchoolYear
     * @param idPupil : the id of the pupil
     * @param idSchoolYear : the id of the schoolYear
     * @return List<PaymentDto> : the list of all paymentDtos by idPupil and idSchoolYear
     */
    public List<PaymentDto> findByIdPupilAndFeesIdSchoolYear(Long idPupil, Long idSchoolYear) {
        log.info("execution of the method:findByIdPupilAndFeesIdSchoolYear(Long idPupil, Long idSchoolYear)");
        List<PaymentDto> paymentDtos = mapper.entitiesFromDtos(paymentRepository.findByIdPupilAndFeesIdSchoolYearAndRemoveIsFalse(idPupil, idSchoolYear));
        log.info("end of method execution:findByIdPupilAndFeesIdSchoolYear(Long idPupil, Long idSchoolYear)");
        return paymentDtos;
    }

    /**
     * implementation of the methode findByIdUserAndFeesIdSchoolYear for getting all paymentDtos by idUser and idSchoolYear
     * @param idUser : the id of the user
     * @param idSchoolYear : the id of the schoolYear
     * @return List<PaymentDto> : the list of all paymentDtos by idUser and idSchoolYear
     */
    public List<PaymentDto> findByIdUserAndFeesIdSchoolYear(Long idUser, Long idSchoolYear) {
        log.info("execution of the method:findByIdUserAndFeesIdSchoolYear(Long idUser, Long idSchoolYear)");
        List<PaymentDto> paymentDtos = mapper.entitiesFromDtos(paymentRepository.findByIdUserAndFeesIdSchoolYearAndRemoveIsFalse(idUser, idSchoolYear));
        log.info("end of method execution:findByIdUserAndFeesIdSchoolYear(Long idUser, Long idSchoolYear)");
        return paymentDtos;
    }

    /**
     * implementation of the methode findByIdUserAndFeesIdSchoolYear for getting all paymentDtos by idUser and idSchoolYear with pagination
     * @param idUser : the id of the user
     * @param idSchoolYear : the id of the schoolYear
     * @param page : the number of the page
     * @param size : the size of the page
     * @return Map<String, Object> : the map of all paymentDtos by idUser and idSchoolYear with pagination
     */
    public Map<String, Object> findByIdUserAndFeesIdSchoolYear(Long idUser, Long idSchoolYear, int page, int size) {
        log.info("execution of the method:findByIdUserAndFeesIdSchoolYear(Long idUser, Long idSchoolYear, Pageable pageable)");
        Page<Payment> payments = paymentRepository.findByIdUserAndFeesIdSchoolYearAndRemoveIsFalse(idUser, idSchoolYear, PageRequest.of(page, size));
        Map<String, Object> map = mapper.entitiesFromDtosPage(payments);
        log.info("end of method execution:findByIdUserAndFeesIdSchoolYear(Long idUser, Long idSchoolYear, Pageable pageable)");
        return map;
    }

    /**
     * implementation of the methode findByDateAndRemoveIsTrue for getting all paymentDtos by date
     * @param date : the date of the paymentDtos
     * @return List<Payment> : the list of all paymentDtos by date
     */
    public List<PaymentDto> findByDateAndRemoveIsTrue(LocalDate date) {
        log.info("execution of the method:findByDateAndRemoveIsTrue(LocalDate date)");
        List<PaymentDto> payments = mapper.entitiesFromDtos(paymentRepository.findByDateAndRemoveIsTrue(date));
        log.info("end of method execution:findByDateAndRemoveIsTrue(LocalDate date)");
        return payments;

    }

    /**
     * implementation of the methode findByIdUserAndDate for getting all paymentDtos by idUser and date
     * @param idUser : the id of the user
     * @param date : the date of the paymentDtos
     * @return List<PaymentDto> : the list of all paymentDtos by idUser and date
     */
    public List<PaymentDto> findByIdUserAndDate(Long idUser, LocalDate date) {
        log.info("execution of the method:findByIdUserAndDate(Long idUser, LocalDate date)");
        List<PaymentDto> paymentDtos = mapper.entitiesFromDtos(paymentRepository.findByIdUserAndDateAndRemoveIsFalse(idUser, date));
        log.info("end of method execution:findByIdUserAndDate(Long idUser, LocalDate date)");
        return paymentDtos;
    }

    /**
     * implementation of the methode findByIdBankAndFeesIdSchoolYear for getting all paymentDtos by idBank and idSchoolYear
     * @param idPaymentSystem : the id of the PaymentSystem
     * @param idSchoolYear : the id of the schoolYear
     * @return List<PaymentDto> : the list of all paymentDtos by idBank and idSchoolYear
     */
    public List<PaymentDto> findByIdPaymentSystemAndFeesIdSchoolYear(Long idPaymentSystem, Long idSchoolYear) {
        log.info("execution of the method:findByIdPaymentSystemAndFeesIdSchoolYear(Long idBank, Long idSchoolYear)");
        List<PaymentDto> paymentDtos = mapper.entitiesFromDtos(paymentRepository.findByIdPaymentSystemAndFeesIdSchoolYearAndRemoveIsFalse(idPaymentSystem, idSchoolYear));
        log.info("end of method execution:findByIdPaymentSystemAndFeesIdSchoolYear(Long idBank, Long idSchoolYear)");
        return paymentDtos;
    }

    /**
     * implementation of the methode findByIdBankAndFeesIdSchoolYear for getting all paymentDtos by idBank and idSchoolYear with pagination
     * @param idPaymentSystem : the id of the PaymentSystem
     * @param idSchoolYear : the id of the schoolYear
     * @param page : the number of the page
     * @param size : the size of the page
     * @return Map<String, Object> : the map of all paymentDtos by idBank and idSchoolYear with pagination
     */
    public Map<String, Object> findByIdPaymentSystemAndFeesIdSchoolYear(Long idPaymentSystem, Long idSchoolYear, int page, int size) {
        log.info("execution of the method:findByIdPaymentSystemAndFeesIdSchoolYear(Long idPaymentSystem, Long idSchoolYear, Pageable pageable)");
        Page<Payment> payments = paymentRepository.findByIdPaymentSystemAndFeesIdSchoolYearAndRemoveIsFalse(idPaymentSystem, idSchoolYear, PageRequest.of(page, size));
        Map<String, Object> map = mapper.entitiesFromDtosPage(payments);
        log.info("end of method execution:findByIdPaymentSystemAndFeesIdSchoolYear(Long idPaymentSystem, Long idSchoolYear, Pageable pageable)");
        return map;
    }
    /**
     * implementation of the methode findAllByRemoveIsFalse for getting all paymentDtos that are not deleted
     * @return List<PaymentDto> : the list of all paymentDtos that are not deleted
     */
    public List<PaymentDto> findAllByRemoveIsFalse() {
        log.info("execution of the method:findAllByRemoveIsFalse()");
        List<PaymentDto> paymentDtos = mapper.entitiesFromDtos(paymentRepository.findAllByRemoveIsFalse());
        log.info("end of method execution:findAllByRemoveIsFalse()");
        return paymentDtos;
    }

}
