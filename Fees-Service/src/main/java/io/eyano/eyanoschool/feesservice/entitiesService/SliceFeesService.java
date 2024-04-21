package io.eyano.eyanoschool.feesservice.entitiesService;

import io.eyano.eyanoschool.feesservice.dao.SliceFeesRepository;
import io.eyano.eyanoschool.feesservice.dtos.PaymentDto;
import io.eyano.eyanoschool.feesservice.dtos.SliceFeesDto;
import io.eyano.eyanoschool.feesservice.entities.SliceFees;
import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.mappers.SliceFeesMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
/**
    * SliceService is a class that implements the CrudService interface
    * @author : Pascal Tshingila
    * @version : 1.0
    * @version : 1.0
 */

@Service @Transactional
@AllArgsConstructor @Slf4j
public class SliceFeesService implements CrudService<SliceFeesDto, Long> {
    SliceFeesRepository sliceFeesRepository;
    SliceFeesMapper mapper;

    /**
     * This method saves an entity in the database
     * @param entity : the entity to save
     * @return the entity saved
     */
    @Override
    public SliceFeesDto save(SliceFeesDto entity) {
        log.info("execution of the method:save(SliceFeesDto entity) : {"+ entity+"}");
        SliceFees sliceFees = mapper.dtoFromEntity(entity);

        SliceFeesDto sliceFeesDto = mapper.entityFromDTO(
                sliceFeesRepository.save(sliceFees)
        );
        log.info("the creation of the entity : {"+ sliceFeesDto+"}");
        return sliceFeesDto;
    }

    /**
     * This method updates an entity in the database
     * @param entity : the entity to update
     * @return the entity updated
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdNotFoundException : if the entity is null
     */
    @Override
    public SliceFeesDto update(SliceFeesDto entity) throws IdNotFoundException {
        log.info("execution of the method:update(SliceFeesDto entity) : {"+ entity+"}");

        //star of the verification of the entity existence and verification if entity is null ------------------
        if(entity == null){
            throw new IdNotFoundException("entity slice is null");
        }
        sliceFeesRepository.findByIdAndRemoveIsFalse(entity.getId()).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification if entity is null ------------------

        SliceFeesDto sliceFeesDto = save(entity);
        log.info("entity update : {" +sliceFeesDto+"}");
        return sliceFeesDto;
    }
    /**
     * This method deletes an entity in the database
     * @param entity : the entity to delete
     * @return the entity deleted
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdNotFoundException : if the entity is null
     */
    @Override
    public boolean remove(SliceFeesDto entity) throws IdNotFoundException {
        log.info("execution of the method:remove(SliceFeesDto entity) : {"+ entity+"}");

        //star of the verification of the entity existence and verification if entity is null ------------------
        if(entity == null){
            throw new IdNotFoundException("entity is null");
        }
        SliceFees sliceFees = sliceFeesRepository.findByIdAndRemoveIsFalse(entity.getId()).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification if entity is null ------------------

        SliceFeesDto sliceFeesDto = mapper.entityFromDTO(sliceFees);
        sliceFeesDto.setRemove(true);

        boolean remove = update(sliceFeesDto).isRemove();
        log.info("the entity : {"+ sliceFeesDto+"} is deleted in the database");
        return remove;
    }
    /**
     * This method deletes an entity in the database
     * @param id : the entity to delete
     * @return the entity deleted
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdNotFoundException : if the entity is null
     */
    @Override
    public boolean removeById(Long id) throws IdNotFoundException {
        log.info("execution of the method:removeById(Long id) : {"+ id+"}") ;

        //star of the verification of the entity existence and verification if entity is null ------------------
        if(id == null){
            throw new IdNotFoundException("entity is null");
        }
        SliceFees sliceFees = sliceFeesRepository.findByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification if entity is null ------------------

        SliceFeesDto sliceFeesDto = mapper.entityFromDTO(sliceFees);
        sliceFeesDto.setRemove(true);
        boolean remove = update(sliceFeesDto).isRemove();
        log.info("the entity : {"+ sliceFeesDto+"} is deleted in the database from id");
        return remove;
    }
    /**
     * This method restores an entity in the database
     * @param id : the entity to restore
     * @return the entity restored
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdNotFoundException : if the entity is null
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException {
        log.info("execution of the method:restore(Long id) : {"+ id+"}") ;

        //star of the verification of the entity existence and verification if entity is null ------------------
        if(id == null){
            throw new IdNotFoundException("entity is null");
        }
        SliceFees sliceFees = sliceFeesRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification if entity is null ------------------

        SliceFeesDto sliceFeesDto = mapper.entityFromDTO(sliceFees);
        sliceFeesDto.setRemove(false);

        update(sliceFeesDto);
        log.info("the entity : {"+ sliceFeesDto+"} is restored in the database");
        return true;
    }
    /**
     * This method checks if an entity exists in the database
     * @param id : the entity to check
     * @return boolean : true if the entity exists
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdNotFoundException : if the entity is null
     */
    @Override
    public SliceFeesDto isExist(Long id) throws IdNotFoundException {
        log.info("execution of the method:isExist(Long id) : {"+ id+"}") ;

        //star of the verification of the entity existence and verification if entity is null ------------------
        if(id == null){
            throw new IdNotFoundException("entity is null");
        }
        SliceFees sliceFees = sliceFeesRepository.findById(id).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification if entity is null ------------------

        SliceFeesDto sliceFeesDto = mapper.entityFromDTO(sliceFees);

        log.info("the entity : {"+ sliceFees+"} exists in the database");
        return sliceFeesDto;
    }
    /**
     * This method checks if an entity is deleted in the database
     * @param id : the entity to check
     * @return boolean : true if the entity is deleted
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdNotFoundException : if the entity is null
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException {
        log.info("execution of the method:isRemove(Long id) : {"+ id+"}") ;

        //star of the verification of the entity existence and verification if entity is null ------------------
        if(id == null){
            throw new IdNotFoundException("entity is null");
        }
        SliceFees sliceFees = sliceFeesRepository.findById(id).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification if entity is null ------------------

        boolean remove = sliceFees.isRemove();
        log.info("the remove attribute of the entity {"+ sliceFees+"} is : "+remove);
        return remove;
    }

    /**
     * This method finds an entity in the database
     * @param id : the id of the entity to find
     * @return SliceFeesDto : the entity found
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdNotFoundException : if the entity is null
     */
    public SliceFeesDto findById(Long id)throws IdNotFoundException {
        log.info("execution of the method:findById(Long) : {"+ id+"}") ;

        //star of the verification of the entity existence and verification if entity is null ------------------
        if(id == null){
            throw new IdNotFoundException("entity is null");
        }
        SliceFees sliceFees = sliceFeesRepository.findByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification if entity is null ------------------

        SliceFeesDto sliceFeesDto = mapper.entityFromDTO(sliceFees);
        log.info("the entity : {" + sliceFeesDto + "} is found in the database") ;
        return sliceFeesDto;
    }
    /**
     * This method finds an entity deleted in the database
     * @param id : the id of the entity to find
     * @return SliceFeesDto : the entity found
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdNotFoundException : if the entity is null
     */
    @Override
    public SliceFeesDto findRemoveById(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:findById(Long) : {"+ id+"}") ;

        //star of the verification of the entity existence and verification if entity is null ------------------
        if(id == null){
            throw new IdNotFoundException("entity is null");
        }
        SliceFees sliceFees = sliceFeesRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification if entity is null ------------------

        SliceFeesDto sliceFeesDto = mapper.entityFromDTO(sliceFees);
        log.info("the entity : {" + sliceFeesDto + "} is found in the database") ;
        return sliceFeesDto;
    }
    /**
     * This method finds all entities in the database
     * @return List<SliceFeesDto> : the list of entities
     */
    @Override
    public List<SliceFeesDto> findAll() {
        log.info("execution of the method:findAll()") ;

        List<SliceFeesDto> sliceFeesDtoList = mapper.entitiesFromDtos(
                sliceFeesRepository.findSliceFeesByRemoveIsFalse()
        );
        log.info("end of method execution:findAll()") ;
        return sliceFeesDtoList;
    }
    /**
     * This method finds all deleted entities in the database
     * @return List<SliceFeesDto> : the list of deleted entities
     */
    @Override
    public List<SliceFeesDto> findAllRemove() {
        log.info("execution of the method:findAllDelete") ;

        List<SliceFeesDto> sliceFeesDtoList = mapper.entitiesFromDtos(
                sliceFeesRepository.findSliceFeesByRemoveIsTrue()
        );
        log.info("end of method execution:findAllDelete") ;
        return sliceFeesDtoList;
    }

    //##############################################################
    //###                                                        ###
    //###    The following methods are specific to the entity    ###
    //###                                                        ###
    //##############################################################


    /**
     * This method finds an entity deleted in the database
     * @param tag : the designation of the entity to find
     * @return List<SliceFeesDto> : the list of entities found
     */
    public List<SliceFeesDto> findSliceFeesByDesignationContainsAndRemoveIsTrue(String tag){
        log.info("execution of the method:findSliceFeesByDesignationContainsAndRemoveIsTrue(String) : {"+ tag+"}") ;

        List<SliceFeesDto> sliceFeesDtoList = mapper.entitiesFromDtos(
                sliceFeesRepository.findSliceFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(tag)
        );

        log.info("end of method execution:findSliceFeesByDesignationContainsAndRemoveIsTrue(String)") ;
        return sliceFeesDtoList;
    }
    /**
     * This method finds an entity in the database
     * @param tag : the designation of the entity to find
     * @return List<SliceFeesDto> : the list of entities found
     */
    public List<SliceFeesDto> findSliceFeesByDesignationContainsAndRemoveIsFalse(String tag){
        log.info("execution of the method:findSliceFeesByDesignationContainsAndRemoveIsFalse(String) : {"+ tag+"}") ;

        List<SliceFeesDto> sliceFeesDtoList = mapper.entitiesFromDtos(
                sliceFeesRepository.findSliceFeesByDesignationIgnoreCaseContainsAndRemoveIsFalse(tag)
        );

        log.info("end of method execution:findSliceFeesByDesignationContainsAndRemoveIsFalse(String)") ;
        return sliceFeesDtoList;

    }
/**
     * This method finds an entity in the database per page
     * @param tag : the designation of the entity to find
     * @param page : the page number
     * @param size : the size of the page
     * @return Map<String,Object> : the list of entities found
     */
    public Map<String,Object> findSliceFeesByDesignationContainsAndRemoveIsFalsePage(String tag,int page, int size ){
        log.info("execution of the method:findSliceFeesByDesignationContainsAndRemoveIsFalsePage(String,int,int) : {"+ tag+","+page+","+size+"}") ;

        Map<String,Object> sliceFeesDtoPage = mapper.entitiesFromDtosPage(
                sliceFeesRepository.findSliceFeesByDesignationIgnoreCaseContainsAndRemoveIsFalse(tag, PageRequest.of(page,size)
                )
        );
        log.info("end of method execution:findSliceFeesByDesignationContainsAndRemoveIsFalsePage(String,int,int)") ;
        return sliceFeesDtoPage;
    }
    /**
     * This method finds an entity deleted in the database per page
     * @param tag : the designation of the entity to find
     * @param page : the page number
     * @param size : the size of the page
     * @return Map<String,Object> : the list of entities found
     */
    public Map<String,Object> findSliceFeesByDesignationContainsAndRemoveIsTruePage(String tag,int page, int size){
        log.info("execution of the method:findSliceFeesByDesignationContainsAndRemoveIsTruePage(String,int,int) : {"+ tag+","+page+","+size+"}") ;
        Map<String,Object> sliceFeesDtoPage = mapper.entitiesFromDtosPage(
                sliceFeesRepository.findSliceFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(tag, PageRequest.of(page,size)
                )
        );
        log.info("end of method execution:findSliceFeesByDesignationContainsAndRemoveIsTruePage(String,int,int)") ;
        return sliceFeesDtoPage;
    }


}
