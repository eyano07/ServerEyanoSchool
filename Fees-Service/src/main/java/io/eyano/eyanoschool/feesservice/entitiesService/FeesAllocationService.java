package io.eyano.eyanoschool.feesservice.entitiesService;

import io.eyano.eyanoschool.feesservice.dao.FeesAllocationRepository;
import io.eyano.eyanoschool.feesservice.dtos.FeesAllocationDto;
import io.eyano.eyanoschool.feesservice.entities.FeesAllocation;
import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.mappers.FeesAllocationMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * FeesAllocationService is a class that implements the CrudService interface and has generic type FeesAllocationDto and Long
 * @author : Pascal Tshingila
 * @since : 2021-04-19
 * @version : 1.0
 * @see CrudService
 * @see FeesAllocationDto
 * @see FeesAllocation
 */
@Service @Transactional
@AllArgsConstructor @Slf4j
public class FeesAllocationService implements CrudService<FeesAllocationDto, Long> {
    FeesAllocationRepository feesAllocationRepository;
    FeesAllocationMapper mapper;

    /**
     * This method saves an entity in the database
     * @param entity : the entity to save
     * @return the entity saved
     */
    @Override
    public FeesAllocationDto save(FeesAllocationDto entity){
        log.info("execution of the method:save(FeesAllocationDto entity) : {"+entity+"}") ;

        FeesAllocation feesAllocation = mapper.dtoFromEntity(entity);
        FeesAllocationDto feesAllocationDto = mapper.entityFromDTO(
                feesAllocationRepository.save(feesAllocation)
        );
        log.info("the creation of the entity : {"+ feesAllocationDto.toString()+"}");
        return feesAllocationDto ;
    }

    /**
     * This method updates an entity in the database
     * @param entity : the entity to update
     * @return the entity updated
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdIsNullException : if the id is null
     */
    @Override
    public FeesAllocationDto update(FeesAllocationDto entity) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method:update(FeesAllocationDto entity) : {"+entity+"}" ) ;

        //Star of the verification of the id ------------------------------------
        if(entity.getId()==null){
            throw new IdIsNullException("The id FeesAllocation is null");
        }
        feesAllocationRepository.findByIdAndRemoveIsFalse(entity.getId()).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesAllocationDto feesAllocationDto = save(entity);
        log.info("entity update : {" +feesAllocationDto+"}");
        return feesAllocationDto;
    }

    /**
     * This method deletes an entity in the database
     *
     * @return true if the entity is deleted
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdIsNullException   : if the id is null
     */
    @Override
    public boolean remove(FeesAllocationDto feesAllocationDto)  throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:remove(FeesAllocationDto entity) {"+feesAllocationDto+"}") ;

        //Star of the verification of the id ------------------------------------
        if (feesAllocationDto.getId()==null){
            throw new IdIsNullException("The id FeesAllocation is null");
        }
        FeesAllocation feesAllocation = feesAllocationRepository.findByIdAndRemoveIsFalse(feesAllocationDto.getId()).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesAllocationDto result = mapper.entityFromDTO(feesAllocation);
        result.setRemove(true);

        boolean remove = update(result).isRemove();
        log.info("the entity : {"+ feesAllocationDto+"} is deleted in the database");
        return remove;
    }

    /**
     * This method deletes an entity in the database
     * @param id : the id of the entity to delete
     * @return boolean : true if the entity is deleted
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdIsNullException : if the id is null
     */
    @Override
    public boolean removeById(Long id) throws IdNotFoundException, IdIsNullException{
        log.info("execution of the method:removeById(Long id)") ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id FeesAllocation is null");
        }
        FeesAllocation feesAllocation = feesAllocationRepository.findByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesAllocationDto feesAllocationDto = mapper.entityFromDTO(feesAllocation);
        feesAllocationDto.setRemove(true);

        boolean remove = update(feesAllocationDto).isRemove();
        log.info("the entity : {"+ feesAllocationDto+"} is deleted in the database from id");
        return remove;
    }

    /**
     * This method restores an entity in the database
     * @param id : the id of the entity to restore
     * @return boolean : true if the entity is restored
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdIsNullException : if the id is null
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:restore(Long id) : {"+id+"}") ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id is null");
        }
        FeesAllocation feesAllocation = feesAllocationRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesAllocationDto feesAllocationDto = mapper.entityFromDTO(feesAllocation);
        feesAllocationDto.setRemove(false);

        update(feesAllocationDto);
        log.info("the entity : {"+ feesAllocationDto+"} is restored in the database from id");
        return true;
    }

    /**
     * This method checks if an entity exists in the database
     * @param id : the id of the entity to check
     * @return boolean : true if the entity exists
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdIsNullException : if the id is null
     */
    @Override
    public FeesAllocationDto isExist(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:isExist(Long id) : {"+id+"}") ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id FeesAllocation is null");
        }
        FeesAllocation feesAllocation = feesAllocationRepository.findById(id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesAllocationDto feesAllocationDto = mapper.entityFromDTO(feesAllocation);
        log.info("the entity : {"+ feesAllocationDto+"} exists in the database from id");
        return feesAllocationDto;
    }

    /**
     * This method checks if an entity is deleted in the database
     * @param id : the id of the entity to check
     * @return boolean : true if the entity is deleted
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdIsNullException : if the id is null
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:isRemove(Long id) :{"+id+"}") ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id FeesAllocation is null");
        }
        FeesAllocation feesAllocation = feesAllocationRepository.findById(id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        boolean remove = feesAllocation.isRemove();
        log.info("the remove attribute of the entity {"+ feesAllocation+"} is : "+feesAllocation.isRemove());
        return remove;
    }

    /**
        * This method finds an entity in the database
        * @param id : the id of the entity to find
        * @return FeesAllocationDto : the entity found
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdIsNullException : if the id is null
     */
    @Override
    public FeesAllocationDto findById(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:findById(Long id) : {"+id+"}") ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id is null");
        }
        FeesAllocation feesAllocation = feesAllocationRepository.findByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesAllocationDto feesAllocationDto = mapper.entityFromDTO(feesAllocation);
        log.info("end of method execution:findById(Long id) Entity : "+feesAllocationDto) ;
        return feesAllocationDto;
    }
    /**
        * This method finds removed entity in the database
        * @param id : the id of the entity removed
        * @return FeesAllocationDto : the entity found
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdIsNullException : if the id is null
     */
    @Override
    public FeesAllocationDto findRemoveById(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:findRemoveById(Long id) : {"+id+"}") ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id FeesAllocation is null");
        }
        FeesAllocation feesAllocation = feesAllocationRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesAllocationDto feesAllocationDto = mapper.entityFromDTO(feesAllocation);
        log.info("end of method execution:findRemoveById() Entity : "+feesAllocationDto) ;
        return feesAllocationDto;
    }

    /**
     * This method finds an entity in the database
     * @return List<FeesAllocationDto> : the list of entities found
     */
    @Override
    public List<FeesAllocationDto> findAll() {
        log.info("execution of the method:findAll()") ;

        List<FeesAllocationDto> feesAllocationDtoList = mapper.entitiesFromDtos(
                feesAllocationRepository.findByRemoveIsFalse()
        );
        log.info("end of method execution:findAll()") ;
        return feesAllocationDtoList;
    }
    /**
     * This method finds all entities deleted in the database
     * @return List<FeesAllocationDto> : the list of entities removed found
     */
    @Override
    public List<FeesAllocationDto> findAllRemove(){
        log.info("execution of the method:findAllRemove()") ;

        List<FeesAllocationDto> feesAllocationDtoList = mapper.entitiesFromDtos(
                feesAllocationRepository.findByRemoveIsTrue()
        );
        log.info("end of method execution:findAllRemove()") ;
        return feesAllocationDtoList;
    }

    //##############################################################
    //###                                                        ###
    //###    The following methods are specific to the entity    ###
    //###                                                        ###
    //##############################################################


    /**
        * This method finds all entities in the database
        * @param tag : designation of the entity
        * @param idSchoolYear : the id of the school year
        * @return List<FeesAllocationDto> : the list of entities found
        * @throws IdIsNullException : if the id school year is null
     */
    public List<FeesAllocationDto> findByRemoveFalseAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method:findByRemoveFalseAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear)") ;

        //Star of the verification of the id school year------------------------------------
        if(idSchoolYear==null){
            throw new IdIsNullException("The id school year is null");
        }
        //todo: add test for school year id is exist
        //End of the verification of the id school year------------------------------------

        List<FeesAllocationDto> feesAllocationDtoList = mapper.entitiesFromDtos(
                feesAllocationRepository.findByRemoveFalseAndDesignationIgnoreCaseContainsAndIdSchoolYear(tag, idSchoolYear));

        log.info("end of method execution:findByRemoveFalseAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear)") ;
        return feesAllocationDtoList;
    }
    /**
        * This method finds all entities deleted in the database
        * @param tag : designation of the entity
        * @param idSchoolYear : the id of the school year
        * @return List<FeesAllocationDto> : the list of entities found
        * @throws IdIsNullException : if the id school year is null
     */
    public List<FeesAllocationDto> findByRemoveTrueAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear) throws IdIsNullException {
        log.info("execution of the method:findByRemoveTrueAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear)") ;

        //Star of the verification of the id school year------------------------------------
        if(idSchoolYear==null){
            throw new IdIsNullException("The id school year is null");
        }
        //todo: add test for school year id is exist
        //End of the verification of the id school year------------------------------------

        List<FeesAllocationDto> byRemoveTrueAndDesignationContainsAndIdSchoolYear = mapper.entitiesFromDtos(
                feesAllocationRepository.findByRemoveTrueAndDesignationIgnoreCaseContainsAndIdSchoolYear(tag, idSchoolYear)
        );
        log.info("end of method execution:findByRemoveTrueAndDesignationContainsAndIdSchoolYear(String tag, Long idSchoolYear)") ;
        return byRemoveTrueAndDesignationContainsAndIdSchoolYear;
    }

    /**
        * This method finds all entities deleted in the database
        * @param tag : designation of the entity
        * @param idSchoolYear : the id of the school year
        * @param idTypeFees : the id of the type fees
        * @return List<FeesAllocationDto> : the list of entities found
        * @throws IdIsNullException : if the id school year is null
     */
    public List<FeesAllocationDto> findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees) throws IdIsNullException {
        log.info("execution of the method:findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees)") ;

        //Star of the verification of the id school year------------------------------------
        if(idSchoolYear==null){
            throw new IdIsNullException("The id school year is null");
        }
        //todo: add test for school year id is exist
        //End of the verification of the id school year------------------------------------


        List<FeesAllocationDto> feesAllocationDtoList = mapper.entitiesFromDtos(
               feesAllocationRepository.findByRemoveTrueAndDesignationIgnoreCaseContainsAndIdSchoolYearAndTypeFeesId(tag, idSchoolYear, idTypeFees)
        );
        log.info("end of method execution:findByRemoveTrueAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees)") ;
        return feesAllocationDtoList;
    }
    /**
        * This method finds all entities in the database
        * @param tag : designation of the entity
        * @param idSchoolYear : the id of the school year
        * @param idTypeFees : the id of the type fees
        * @return List<FeesAllocationDto> : the list of entities found
        * @throws IdIsNullException : if the id school year or id type fees is null
     */
    public List<FeesAllocationDto> findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees) throws IdIsNullException {
        log.info("execution of the method:findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees)") ;

        //Star of the verification of the id school year and id type fees------------------------------------
        if(idSchoolYear==null){
            throw new IdIsNullException("The id School Year is null");
        }else if(idTypeFees==null){
            throw new IdIsNullException("The id type fees is null");
        }
        //todo: add test for school year id is exist and type fees id is exist
        //End of the verification of the id school year and id type fees------------------------------------

        List<FeesAllocationDto> byRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId = mapper.entitiesFromDtos(
                feesAllocationRepository.findByRemoveFalseAndDesignationIgnoreCaseContainsAndIdSchoolYearAndTypeFeesId(tag, idSchoolYear, idTypeFees)
        );
        log.info("end of method execution:findByRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId(String tag, Long idSchoolYear, Long idTypeFees)") ;
        return byRemoveFalseAndDesignationContainsAndIdSchoolYearAndTypeFeesId;
    }
    /**
        * This method finds an entity deleted in the database
        * @param id : the id of the entity
        * @return FeesAllocationDto : the entity found
        * @throws IdNotFoundException : if the entity is not found
     */
    public FeesAllocationDto findByIdAndRemoveIsTrue(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:findByIdAndRemoveIsTrue(Long aLong)") ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id is null");
        }
        //End of the verification of the id ------------------------------------

        FeesAllocationDto byIdAndRemoveIsTrue = mapper.entityFromDTO(
                feesAllocationRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new)
        );
        log.info("end of method execution:findByIdAndRemoveIsTrue(Long aLong)") ;
        return byIdAndRemoveIsTrue;
    }


}
