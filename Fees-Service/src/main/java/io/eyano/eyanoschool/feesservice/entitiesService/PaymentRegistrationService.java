package io.eyano.eyanoschool.feesservice.entitiesService;

import io.eyano.eyanoschool.feesservice.dao.FeesRepository;
import io.eyano.eyanoschool.feesservice.dao.PaymentRegistrationRepository;
import io.eyano.eyanoschool.feesservice.dtos.PaymentRegistrationDto;
import io.eyano.eyanoschool.feesservice.entities.PaymentRegistration;
import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.mappers.PaymentRegistrationMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * PaymentRegistrationService is a class that implements the CrudService interface and has generic type PaymentRegistrationDto and Long
 * @version : 1.0
 * @author : Pascal Tshingila
 * @since : 2021-04-19
 *
 */
@Service @Transactional
@AllArgsConstructor @Slf4j
public class PaymentRegistrationService implements CrudService<PaymentRegistrationDto, Long> {
    PaymentRegistrationRepository paymentRegistrationRepository;
    FeesRepository feesRepository;
    PaymentRegistrationMapper mapper;

    /**
        * implementation of methode save from CrudService interface for saving PaymentRegistrationDto entity
        * @param entity : PaymentRegistrationDto entity to save
        * @throws IdIsNullException : if the id Fees, id currency, id candidate, id user and id payment system is null
        * @throws IdNotFoundException : if the id Fees, id currency, id candidate, id user and id payment system is not found
        * @return the entity saved
     */
    @Override
    public PaymentRegistrationDto save(PaymentRegistrationDto entity) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method:save(PaymentRegistrationDto entity) : {"+ entity+"}");
        PaymentRegistration paymentRegistration = mapper.dtoFromEntity(entity);

        //star check if id Payment system, id candidate, id fees, id user, id currency and id fees exist
        if(entity.getFees().getId() == null) throw new IdIsNullException("The id Fees is null");
        feesRepository.findByIdAndRemoveIsFalse(entity.getId()).orElseThrow(IdNotFoundException::new);
        //todo : check if id candidate, id currency, id user and payment system exist
        //end check if id Payment system, id candidate, id fees, id user, id currency and id fees exist

        PaymentRegistrationDto paymentRegistrationDto = mapper.entityFromDTO(
                paymentRegistrationRepository.save(paymentRegistration)
        );
        log.info("the creation of the entity : {"+ paymentRegistrationDto+"}");
        return paymentRegistrationDto;
    }

    /**
        * implementation of methode update from CrudService interface for updating PaymentRegistrationDto entity
        * @param entity : the entity to update
        * @return the entity updated
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdIsNullException : if the id is null
     */
    @Override
    public PaymentRegistrationDto update(PaymentRegistrationDto entity) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:update(PaymentRegistrationDto entity) : {"+ entity+"}") ;

        //Star of the verification of the existence of the entity -----------------------------------------
        if(entity.getId() == null) throw new IdIsNullException("The id paymentRegistration is null");
        paymentRegistrationRepository.findByIdAndRemoveIsFalse(entity.getId()).orElseThrow(IdNotFoundException::new);
        //End of the verification of the existence of the entity -----------------------------------------

        PaymentRegistrationDto paymentRegistrationDto = save(entity);
        log.info("entity update : {" +paymentRegistrationDto.toString()+"}");
        return paymentRegistrationDto;
    }

    /**
        * implementation of methode remove from CrudService interface for removing PaymentRegistrationDto entity
        * @param entity : the entity to delete
        * @return boolean : the entity deleted
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdIsNullException : if the id is null
     */
    @Override
    public boolean remove(PaymentRegistrationDto entity) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:remove(PaymentRegistrationDto entity) : {"+ entity+"}") ;

        //Star of the verification of the existence of the entity -----------------------------------------
        if(entity.getId() == null) throw new IdIsNullException("The id paymentRegistration is null");
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findByIdAndRemoveIsFalse(
                entity.getId()).orElseThrow(IdNotFoundException::new);
        //End of the verification of the existence of the entity -----------------------------------------

        PaymentRegistrationDto paymentRegistrationDto = mapper.entityFromDTO(paymentRegistration);
        paymentRegistrationDto.setRemove(true);

        boolean remove = update(paymentRegistrationDto).isRemove();
        log.info("the entity : {"+ paymentRegistrationDto+"} is deleted in the database");
        return remove;
    }

    /**
        * implementation of methode removeById from CrudService interface for removing PaymentRegistrationDto entity
        * @param id : the id of the entity to delete
        * @return boolean : the entity deleted
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdIsNullException : if the id is null
     */
    @Override
    public boolean removeById(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:removeById(Long id) : {"+ id+"}") ;

        //Star of the verification of the existence of the entity -----------------------------------------
        if(id == null) throw new IdIsNullException("The id paymentRegistration is null");
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findByIdAndRemoveIsFalse(
                id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the existence of the entity -----------------------------------------

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
        * @throws IdIsNullException : if the id is null
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:restore(Long id)") ;

        //Star of the verification of the existence of the entity -----------------------------------------
        if(id == null) throw new IdIsNullException("The id paymentRegistration is null");
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findByIdAndRemoveIsTrue(
                id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the existence of the entity -----------------------------------------

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
        * @throws IdIsNullException : if the id is null
     */
    @Override
    public PaymentRegistrationDto isExist(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:isExist(Long id)") ;

        //Star of the verification of the existence of the entity -----------------------------------------
        if(id == null) throw new IdIsNullException("The id paymentRegistration is null");
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findById(
                id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the existence of the entity -----------------------------------------

        PaymentRegistrationDto paymentRegistrationDto = mapper.entityFromDTO(paymentRegistration);

        log.info("the entity : {"+ paymentRegistration+"} exists in the database");
        return paymentRegistrationDto;
    }

    /**
        * This method checks if an entity is deleted in the database
        * @param id : the id of the entity to find
        * @return boolean : true if the entity is deleted
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdIsNullException : if the id is null
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:isRemove(Long id)") ;

        //Star of the verification of the existence of the entity -----------------------------------------
        if(id == null) throw new IdIsNullException("The id paymentRegistration is null");
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findById(
                id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the existence of the entity -----------------------------------------

        boolean remove = paymentRegistration.isRemove();
        log.info("the remove attribute of the entity {"+ paymentRegistration+"} is : "+paymentRegistration.isRemove());
        return remove;
    }
    /**
        * this method finds an entity in the database
        * @param id : the id of the entity to find
        * @return PaymentRegistrationDto : the entity found
        * @throws IdIsNullException : if the id is null
        * @throws IdNotFoundException : if the id is not found
     */
    @Override
    public PaymentRegistrationDto findById(Long id) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method:findById(Long id) : {"+ id+"}") ;

        //Star of the verification of the existence of the entity -----------------------------------------
        if(id == null) throw new IdIsNullException("The id paymentRegistration is null");
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findByIdAndRemoveIsFalse(
                id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the existence of the entity -------------------------------------------

        PaymentRegistrationDto paymentRegistrationDto = mapper.entityFromDTO(paymentRegistration);
        log.info("the entity : {"+ paymentRegistrationDto+"} is found in the database");
        return paymentRegistrationDto;
    }
    /**
        * this method finds removed an entity in the database
        * @param id : the id of the entity to find
        * @return PaymentRegistrationDto : the entity found
        * @throws IdIsNullException : if the id is null
        * @throws IdNotFoundException : if the id is not found
     */
    @Override
    public PaymentRegistrationDto findRemoveById(Long id) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method:findRemoveById(Long id) : {"+ id+"}") ;

        //Star of the verification of the existence of the entity -----------------------------------------
        if(id == null) throw new IdIsNullException("The id paymentRegistration is null");
        PaymentRegistration paymentRegistration = paymentRegistrationRepository.findByIdAndRemoveIsTrue(
                id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the existence of the entity -------------------------------------------

        PaymentRegistrationDto paymentRegistrationDto = mapper.entityFromDTO(paymentRegistration);
        log.info("the entity : {"+ paymentRegistrationDto+"} is found in the database");
        return paymentRegistrationDto;
    }

    /**
        * this method finds all entities in the database
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    @Override
    public List<PaymentRegistrationDto> findAll() {
        log.info("execution of the method:findAll()") ;

        List<PaymentRegistrationDto> paymentRegistrationDtoList = mapper.entitiesFromDtos(
                paymentRegistrationRepository.findByRemoveIsFalse()
        );
        log.info("end of method execution:findAll()") ;
        return paymentRegistrationDtoList;
    }
    /**
        * this method finds all entities deleted in the database
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    @Override
    public List<PaymentRegistrationDto> findAllRemove(){
        log.info("execution of the method:findAllRemove()") ;

        List<PaymentRegistrationDto> paymentRegistrationDtoList = mapper.entitiesFromDtos(
                paymentRegistrationRepository.findByRemoveIsTrue()
        );
        log.info("end of method execution:findAllRemove()") ;
        return paymentRegistrationDtoList;
    }

    //##############################################################
    //###                                                        ###
    //###    The following methods are specific to the entity    ###
    //###                                                        ###
    //##############################################################


    /**
        * this method finds all entities in the database
        * @param idCandidate : the id of the entity to find
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndIdCandidate(Long idCandidate) {
        log.info("execution of the method:findByRemoveIsFalseAndIdCandidate(Long idCandidate) : {"+ idCandidate+"}") ;

        List<PaymentRegistrationDto> paymentRegistrationDtoList = mapper.entitiesFromDtos(
                paymentRegistrationRepository.findByRemoveIsFalseAndIdCandidate(idCandidate)
        );
        log.info("the list of entities : {"+ paymentRegistrationDtoList.toString()+"} is found in the database");
        return paymentRegistrationDtoList;
    }
    /**
        * this method finds all entities in the database
        * @param idCandidate : the id of the entity to find
        * @param idSchoolYear : the id of the entity to find
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear(Long idCandidate, Long idSchoolYear) {
        log.info("execution of the method:findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear(Long idCandidate, Long idSchoolYear) : {"+ idCandidate+"} , {"+ idSchoolYear+"}")
        ;
        List<PaymentRegistrationDto> paymentRegistrationDtoList = mapper.entitiesFromDtos(
                paymentRegistrationRepository.findByRemoveIsFalseAndIdCandidateAndFeesIdSchoolYear(idCandidate, idSchoolYear)
        );
        log.info("the list of entities : {"+ paymentRegistrationDtoList.toString()+"} is found in the database");
        return paymentRegistrationDtoList;
    }
    /**
        * this method finds all entities in the database
        * @param idCandidate : the id of the entity to find
        * @param idSchoolYear : the id of the entity to find
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    public List<PaymentRegistrationDto> findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear(Long idCandidate, Long idSchoolYear) {
        log.info("execution of the method:findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear(Long idCandidate, Long idSchoolYear) : {"+ idCandidate+"} , {"+ idSchoolYear+"}") ;

        List<PaymentRegistrationDto> paymentRegistrationDtoList = mapper.entitiesFromDtos(
                paymentRegistrationRepository.findByRemoveIsTrueAndIdCandidateAndFeesIdSchoolYear(idCandidate, idSchoolYear)
        );
        log.info("the list of entities : {"+ paymentRegistrationDtoList.toString()+"} is found in the database");
        return paymentRegistrationDtoList;
    }
    /**
        * this method finds all entities in the database
        * @param idUser : the id of the entity to find
        * @param idSchoolYear : the id of the entity to find
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear(Long idUser, Long idSchoolYear) {
        log.info("execution of the method:findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear(Long idUser, Long idSchoolYear) : {"+ idUser+"} , {"+ idSchoolYear+"}") ;

        List<PaymentRegistrationDto> paymentRegistrationDtoList = mapper.entitiesFromDtos(
                paymentRegistrationRepository.findByRemoveIsFalseAndIdUserAndFeesIdSchoolYear(idUser, idSchoolYear)
        );
        log.info("the list of entities : {"+ paymentRegistrationDtoList.toString()+"} is found in the database");
        return paymentRegistrationDtoList;
    }
    /**
        * this method finds all entities in the database
        * @param date : the date of the entity to find
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndDate(LocalDate date) {
        log.info("execution of the method:findByRemoveIsFalseAndDate(String date) : {"+ date+"}") ;

        List<PaymentRegistrationDto> paymentRegistrationDtolist = mapper.entitiesFromDtos(
                paymentRegistrationRepository.findByRemoveIsFalseAndDate(date)
        );
        log.info("the list of entities : {"+ paymentRegistrationDtolist.toString()+"} is found in the database");
        return paymentRegistrationDtolist;
    }
    /**
        * this method finds all entities in the database
        * @param idPaymentSystem : the id of the entity to find
        * @param idSchoolYear : the id of the entity to find
        * @return List<PaymentRegistrationDto> : the list of entities
     */
    public List<PaymentRegistrationDto> findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear(Long idPaymentSystem, Long idSchoolYear) {
        log.info("execution of the method:findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear(Long idPaymentSystem, Long idSchoolYear) : {"+ idPaymentSystem+"} , {"+ idSchoolYear+"}") ;

        List<PaymentRegistrationDto> paymentRegistrationDtoList = mapper.entitiesFromDtos(
                paymentRegistrationRepository.findByRemoveIsFalseAndIdPaymentSystemAndFeesIdSchoolYear(idPaymentSystem, idSchoolYear)
        );
        log.info("the list of entities : {"+ paymentRegistrationDtoList.toString()+"} is found in the database");
        return paymentRegistrationDtoList;
    }
}
