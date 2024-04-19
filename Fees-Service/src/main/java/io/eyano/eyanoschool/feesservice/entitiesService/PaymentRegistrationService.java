package io.eyano.eyanoschool.feesservice.entitiesService;

import io.eyano.eyanoschool.feesservice.dao.PaymentRegistrationRepository;
import io.eyano.eyanoschool.feesservice.dtos.PaymentRegistrationDto;
import io.eyano.eyanoschool.feesservice.entities.PaymentRegistration;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.mappers.PaymentRegistrationMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * PaymentRegistrationService is a class that implements the CrudService interface
 * @version : 1.0
 * @author : Pascal Tshingila
 * @since : 2021-04-19
 *
 */
@Service @Transactional
@AllArgsConstructor @Slf4j
public class PaymentRegistrationService implements CrudService<PaymentRegistrationDto, Long> {
    PaymentRegistrationRepository paymentRegistrationRepository;
    PaymentRegistrationMapper mapper;

    /**
        * This method saves an entity in the database
        * @param entity : the entity to save
        * @return the entity saved
     */
    @Override
    public PaymentRegistrationDto save(PaymentRegistrationDto entity) {
        log.info("execution of the method:save(PaymentRegistrationDto entity)") ;
        PaymentRegistration paymentRegistration = mapper.dtoFromEntity(entity);
        PaymentRegistration paymentRegistrationSave = paymentRegistrationRepository.save(paymentRegistration);
        PaymentRegistrationDto paymentRegistrationDto = mapper.entityFromDTO(paymentRegistrationSave);
        log.info("the creation of the entity : {"+ paymentRegistrationDto.toString()+"}");
        return paymentRegistrationDto;
    }

    /**
        * This method updates an entity in the database
        * @param entity : the entity to update
        * @return the entity updated
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public PaymentRegistrationDto update(PaymentRegistrationDto entity) throws IdNotFoundException {
        log.info("execution of the method:update(PaymentRegistrationDto entity)") ;
        paymentRegistrationRepository.findById(entity.getId()).orElseThrow(IdNotFoundException::new);
        PaymentRegistrationDto paymentRegistrationDto = save(entity);
        log.info("entity update : {" +paymentRegistrationDto.toString()+"}");
        return paymentRegistrationDto;
    }

    /**
        * This method deletes an entity in the database
        * @param entity : the entity to delete
        * @return boolean : the entity deleted
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean remove(PaymentRegistrationDto entity) throws IdNotFoundException {
        log.info("execution of the method:remove(PaymentRegistrationDto entity)") ;
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findById(
                entity.getId()).orElseThrow(IdNotFoundException::new);
        PaymentRegistrationDto paymentRegistrationDto = mapper.entityFromDTO(paymentRegistration);
        paymentRegistrationDto.setRemove(true);
        boolean remove = update(paymentRegistrationDto).isRemove();
        log.info("the entity : {"+ paymentRegistrationDto+"} is deleted in the database");
        return remove;
    }

    /**
        * This method deletes an entity in the database
        * @param id : the id of the entity to delete
        * @return boolean : the entity deleted
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean removeById(Long id) throws IdNotFoundException {
        log.info("execution of the method:removeById(Long id)") ;
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findById(
                id).orElseThrow(IdNotFoundException::new);
        PaymentRegistrationDto paymentRegistrationDto = mapper.entityFromDTO(paymentRegistration);
        paymentRegistrationDto.setRemove(true);
        boolean remove =update(paymentRegistrationDto).isRemove();
        log.info("the entity : {"+ paymentRegistrationDto+"} is deleted in the database from id");
        return remove;
    }

    /**
        * This method restores an entity in the database
        * @param id : the id of the entity to restore
        * @return boolean : the entity restored
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException {
        log.info("execution of the method:restore(Long id)") ;
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findById(
                id).orElseThrow(IdNotFoundException::new);
        PaymentRegistrationDto paymentRegistrationDto = mapper.entityFromDTO(paymentRegistration);
        paymentRegistrationDto.setRemove(false);
        update(paymentRegistrationDto);
        log.info("the entity : {"+ paymentRegistrationDto+"} is restored in the database");
        return true;
    }

    /**
        * This method checks if an entity exists in the database
        * @param id : the id of the entity to find
        * @return boolean : true if the entity exists
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean isExist(Long id) throws IdNotFoundException {
        log.info("execution of the method:isExist(Long id)") ;
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findById(
                id).orElseThrow(IdNotFoundException::new);
        log.info("the entity : {"+ paymentRegistration.toString()+"} exists in the database");
        return true;
    }

    /**
        * This method checks if an entity is deleted in the database
        * @param id : the id of the entity to find
        * @return boolean : true if the entity is deleted
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException {
        log.info("execution of the method:isRemove(Long id)") ;
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findById(
                id).orElseThrow(IdNotFoundException::new);
        boolean remove = paymentRegistration.isRemove();
        log.info("the remove attribute of the entity {"+ paymentRegistration+"} is : "+paymentRegistration.isRemove());
        return remove;
    }

    /**
        * this method finds all entities in the database
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    @Override
    public List<PaymentRegistrationDto> findAll() {
        log.info("execution of the method:findAll()") ;
        //todo : list remove true
        List<PaymentRegistrationDto> paymentRegistrationDtos = mapper.entitiesFromDtos(paymentRegistrationRepository.findAll());
        log.info("end of method execution:findAll()") ;
        return paymentRegistrationDtos;
    }

    /**
        * this method finds an entity in the database
        * @param id : the id of the entity to find
        * @return PaymentRegistrationDto : the entity found
     */
    public PaymentRegistrationDto findByRemoveFalseAndId(Long id) throws IdNotFoundException {
        log.info("execution of the method:findByRemoveFalseAndId(Long id)") ;
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findByRemoveIsFalseAndId(id).orElseThrow(IdNotFoundException::new);
        PaymentRegistrationDto paymentRegistrationDto = mapper.entityFromDTO(paymentRegistration);
        log.info("the entity : {"+ paymentRegistrationDto.toString()+"} is found in the database");
        return paymentRegistrationDto;
    }
    /**
        * this method finds all entities in the database
        * @param idCandidate : the id of the entity to find
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndIdCandidate(Long idCandidate) {
        log.info("execution of the method:findByRemoveIsFalseAndIdCandidate(Long idCandidate)") ;
        List<PaymentRegistrationDto> paymentRegistrationDtos = mapper.entitiesFromDtos(paymentRegistrationRepository.findByRemoveIsFalseAndIdCandidate(idCandidate));
        log.info("the list of entities : {"+ paymentRegistrationDtos.toString()+"} is found in the database");
        return paymentRegistrationDtos;
    }
    /**
        * this method finds all entities in the database
        * @param idCandidate : the id of the entity to find
        * @param idSchoolYear : the id of the entity to find
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear(Long idCandidate, Long idSchoolYear) {
        log.info("execution of the method:findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear(Long idCandidate, Long idSchoolYear)") ;
        List<PaymentRegistrationDto> paymentRegistrationDtos = mapper.entitiesFromDtos(paymentRegistrationRepository.findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear(idCandidate, idSchoolYear));
        log.info("the list of entities : {"+ paymentRegistrationDtos.toString()+"} is found in the database");
        return paymentRegistrationDtos;
    }
    /**
        * this method finds all entities in the database
        * @param idCandidate : the id of the entity to find
        * @param idSchoolYear : the id of the entity to find
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    public List<PaymentRegistrationDto> findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear(Long idCandidate, Long idSchoolYear) {
        log.info("execution of the method:findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear(Long idCandidate, Long idSchoolYear)") ;
        List<PaymentRegistrationDto> paymentRegistrationDtos = mapper.entitiesFromDtos(paymentRegistrationRepository.findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear(idCandidate, idSchoolYear));
        log.info("the list of entities : {"+ paymentRegistrationDtos.toString()+"} is found in the database");
        return paymentRegistrationDtos;
    }
    /**
        * this method finds all entities in the database
        * @param idUser : the id of the entity to find
        * @param idSchoolYear : the id of the entity to find
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear(Long idUser, Long idSchoolYear) {
        log.info("execution of the method:findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear(Long idUser, Long idSchoolYear)") ;
        List<PaymentRegistrationDto> paymentRegistrationDtos = mapper.entitiesFromDtos(paymentRegistrationRepository.findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear(idUser, idSchoolYear));
        log.info("the list of entities : {"+ paymentRegistrationDtos.toString()+"} is found in the database");
        return paymentRegistrationDtos;
    }
    /**
        * this method finds all entities in the database
        * @param date : the date of the entity to find
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndDate(LocalDate date) {
        log.info("execution of the method:findByRemoveIsFalseAndDate(String date)") ;
        List<PaymentRegistrationDto> paymentRegistrationDtos = mapper.entitiesFromDtos(paymentRegistrationRepository.findByRemoveIsFalseAndDate(date));
        log.info("the list of entities : {"+ paymentRegistrationDtos.toString()+"} is found in the database");
        return paymentRegistrationDtos;
    }
    /**
        * this method finds all entities in the database
        * @param idPaymentSystem : the id of the entity to find
        * @param idSchoolYear : the id of the entity to find
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear(Long idPaymentSystem, Long idSchoolYear) {
        log.info("execution of the method:findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear(Long idPaymentSystem, Long idSchoolYear)") ;
        List<PaymentRegistrationDto> paymentRegistrationDtos = mapper.entitiesFromDtos(paymentRegistrationRepository.findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear(idPaymentSystem, idSchoolYear));
        log.info("the list of entities : {"+ paymentRegistrationDtos.toString()+"} is found in the database");
        return paymentRegistrationDtos;
    }
}
