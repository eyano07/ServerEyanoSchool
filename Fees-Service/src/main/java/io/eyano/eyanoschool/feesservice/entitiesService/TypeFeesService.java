package io.eyano.eyanoschool.feesservice.entitiesService;

import io.eyano.eyanoschool.feesservice.dao.TypeFeesRepository;
import io.eyano.eyanoschool.feesservice.dtos.TypeFeesDto;
import io.eyano.eyanoschool.feesservice.entities.TypeFees;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.mappers.TypeFeesMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * TypeFeesService is a class that implements the CrudService interface
 * @version : 1.0
 * @author : paschal Tshingila
 * @since : 2021-04-19
 */
@Service @Transactional
@AllArgsConstructor
@Slf4j
public class TypeFeesService implements CrudService<TypeFeesDto, Long> {
    TypeFeesRepository typeFeesRepository;
    TypeFeesMapper mapper;

    /**
        * This method saves an entity in the database
        * @param entity : the entity to save
        * @return the entity saved
     */
    @Override
    public TypeFeesDto save(TypeFeesDto entity) {
        log.info("execution of the method:save(TypeFeesDto entity) : {"+ entity+"}");
        TypeFees typeFees = mapper.dtoFromEntity(entity);

        TypeFeesDto typeFeesDto = mapper.entityFromDTO(
                typeFeesRepository.save(typeFees)
        );
        log.info("the creation of the entity : {"+ typeFeesDto+"}");
        return typeFeesDto;
    }

    /**
        * This method updates an entity in the database
        * @param entity : the entity to update
        * @return the entity updated
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public TypeFeesDto update(TypeFeesDto entity) throws IdNotFoundException {
        log.info("execution of the method:update(TypeFeesDto entity) : {"+ entity+"}");

        //star oft the verification of the entity existence and verification of the entity is null -----------------------
        if(entity == null){
            throw new IdNotFoundException("the entity type fees is null");
        }
        typeFeesRepository.findByIdAndRemoveIsFalse(entity.getId()).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification of the entity is null -----------------------

        TypeFeesDto typeFeesDto = save(entity);
        log.info("entity update : {" +typeFeesDto+"}");
        return typeFeesDto;
    }

    /**
        * This method deletes an entity in the database
        * @param entity : the entity to delete
        * @return boolean : the entity deleted
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean remove(TypeFeesDto entity) throws IdNotFoundException {
        log.info("execution of the method:remove(TypeFeesDto entity) : {"+ entity+"}");

        //star oft the verification of the entity existence and verification of the entity is null -----------------------
        if(entity == null){
            throw new IdNotFoundException("the entity type fees is null");
        }
        TypeFees typeFees = typeFeesRepository.findByIdAndRemoveIsFalse(entity.getId()).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification of the entity is null -----------------------

        TypeFeesDto typeFeesDto = mapper.entityFromDTO(typeFees);
        typeFeesDto.setRemove(true);

        boolean remove = update(typeFeesDto).isRemove();
        log.info("the entity : {"+ typeFeesDto+"} is deleted in the database");
        return remove;
    }

    /**
        * This method deletes an entity in the database
        * @param id : the entity to delete
        * @return boolean : the entity deleted
        * @throws IdNotFoundException : if the entity is not found
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean removeById(Long id) throws IdNotFoundException {
        log.info("execution of the method:remove(Long id) : {"+ id+"}");

        //star oft the verification of the entity existence and verification of the entity is null -----------------------
        if(id == null){
            throw new IdNotFoundException("the entity type fees is null");
        }
        TypeFees typeFees = typeFeesRepository.findByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification of the entity is null -----------------------

        TypeFeesDto typeFeesDto = mapper.entityFromDTO(typeFees);
        typeFeesDto.setRemove(true);

        boolean remove = update(typeFeesDto).isRemove();
        log.info("the entity : {"+ typeFeesDto+"} is deleted in the database");
        return remove;
    }

    /**
     * This method restores an entity in the database
     * @param id : the entity to restore
     * @return boolean : the entity restored
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException {
        log.info("execution of the method:restore(Long id) : {"+ id+"}");

        //star oft the verification of the entity existence and verification of the entity is null -----------------------
        if(id == null){
            throw new IdNotFoundException("the entity type fees is null");
        }
        TypeFees typeFees = typeFeesRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification of the entity is null -----------------------

        TypeFeesDto typeFeesDto = mapper.entityFromDTO(typeFees);
        typeFeesDto.setRemove(false);

        update(typeFeesDto);
        log.info("the entity : {"+ typeFeesDto+"} is restored in the database");
        return true;
    }

    /**
     * This method checks if an entity exists in the database
     * @param id : the entity to check
     * @return boolean : true if the entity exists
     * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public TypeFeesDto isExist(Long id) throws IdNotFoundException {
        log.info("execution of the method:isExist(Long id) : {"+ id+"}"); ;

        //star oft the verification of the entity existence and verification of the entity is null -----------------------
        if(id == null){
            throw new IdNotFoundException("the entity type fees is null");
        }
        TypeFees typeFees = typeFeesRepository.findById(id).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification of the entity is null -----------------------

        TypeFeesDto typeFeesDto = mapper.entityFromDTO(typeFees);

        log.info("the entity : {"+ typeFeesDto+"} exists in the database");
        return typeFeesDto;
    }

    /**
     * This method checks if an entity is deleted in the database
     * @param id : the entity to check
     * @return boolean : true if the entity is deleted
     * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException {
        log.info("execution of the method:isRemove(Long id) : {"+ id+"}");

        //star oft the verification of the entity existence and verification of the entity is null -----------------------
        if(id == null){
            throw new IdNotFoundException("the entity type fees is null");
        }
        TypeFees typeFees = typeFeesRepository.findById(id).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification of the entity is null -----------------------

        boolean remove = typeFees.isRemove();
        log.info("the remove attribute of the entity {"+ typeFees+"} is : "+remove);
        return remove;
    }

    /**
        * This method finds an entity in the database
        * @param id : the entity to find
        * @return the entity found
        * @throws IdNotFoundException : if the entity is not found
     */
    public TypeFeesDto findById(Long id)throws IdNotFoundException {
        log.info("execution of the method:findById(Long) : {"+ id+"}") ;

        //star oft the verification of the entity existence and verification of the entity is null -----------------------
        if(id == null){
            throw new IdNotFoundException("the entity type fees is null");
        }
        TypeFees typeFees = typeFeesRepository.findByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification of the entity is null -----------------------

        TypeFeesDto typeFeesDto = mapper.entityFromDTO(typeFees);
        log.info("end of method execution:findById(Long) : {"+ typeFeesDto+"}");
        return typeFeesDto;
    }

    /**
     * This method finds an entities in the database
     * @param id : the entity to find
     * @return the entities
     * @throws IdNotFoundException : if the entity is not found
     * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public TypeFeesDto findRemoveById(Long id)throws IdNotFoundException {
        log.info("execution of the method:findRemoveById(Long id ) : {"+ id+"}") ;

        //star oft the verification of the entity existence and verification of the entity is null -----------------------
        if(id == null){
            throw new IdNotFoundException("the entity type fees is null");
        }
        TypeFees typeFees = typeFeesRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new);
        //end of the verification of the entity existence and verification of the entity is null -----------------------

        TypeFeesDto typeFeesDto = mapper.entityFromDTO(typeFees);
        log.info("end of method execution:findByIdAndRemoveIsFalse(Long)") ;
        return typeFeesDto;
    }

    /**
     * This method finds all entities in the database
     * @return List<TypeFeesDto> : the list of entities
     */
    @Override
    public List<TypeFeesDto> findAll() {
        log.info("execution of the method:findAll()") ;

        List<TypeFeesDto> feesDtoList = mapper.entitiesFromDtos(
                typeFeesRepository.findTypeFeesByRemoveIsFalse()
        );
        log.info("end of method execution:findAll()") ;
        return feesDtoList;
    }
    /**
        * This method finds all deleted entities in the database
        * @return deleted entities
     */
    @Override
    public List<TypeFeesDto> findAllRemove() {
        log.info("execution of the method:findAllDelete") ;
        List<TypeFeesDto> typeFeesDtoList = mapper.entitiesFromDtos(
                typeFeesRepository.findTypeFeesByRemoveIsTrue());
        log.info("end of method execution:findAllDelete") ;
        return typeFeesDtoList;
    }

    //##############################################################
    //###                                                        ###
    //###    The following methods are specific to the entity    ###
    //###                                                        ###
    //##############################################################

    /**
        * This method finds all entities deleted in the database
        * @param tag : designation of the entity
        * @return List<TypeFeesDto> : the list of entities
     */
    public List<TypeFeesDto> findTypeFeesByDesignationContainsAndRemoveIsTrue(String tag){
        log.info("execution of the method:findTypeFeesByDesignationContainsAndRemoveIsTrue(String) : {"+ tag+"}") ;

        List<TypeFeesDto> typeFeesDtoList = mapper.entitiesFromDtos(
                typeFeesRepository.findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(tag)
        );
        log.info("end of method execution:findTypeFeesByDesignationContainsAndRemoveIsTrue(String)") ;
        return typeFeesDtoList;
    }

    /**
     * This method finds all entities in the database
     * @param tag : designation of the entity
     * @return List<TypeFeesDto> : the list of entities
     */
    public List<TypeFeesDto> findTypeFeesByDesignationContainsAndRemoveIsFalse(String tag){
        log.info("execution of the method:findTypeFeesByDesignationContainsAndRemoveIsFalse(String) : {"+ tag+"}") ;

        List<TypeFeesDto> typeFeesDtoList = mapper.entitiesFromDtos(
                typeFeesRepository.findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsFalse(tag)
        );
        log.info("end of method execution:findTypeFeesByDesignationContainsAndRemoveIsFalse(String)") ;
        return typeFeesDtoList;
    }

    /**
        * This method finds all entities in the database per page
        * @param tag : designation of the entity
        * @param page : the page number
        * @param size : the size of the page
        * @return Map<String,Object> : the list of entities
     */
    public Map<String,Object> findTypeFeesByDesignationContainsAndRemoveIsFalsePage(String tag, int page, int size ){
        log.info("execution of the method:findTypeFeesByDesignationContainsAndRemoveIsFalsePage(String,int,int) : {"+ tag+","+page+","+size+"}") ;

        Map<String,Object> typeFeesDtoPage = mapper.entitiesFromDtosPage(
                typeFeesRepository.findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsFalse(tag, PageRequest.of(page,size))
        );
        log.info("end of method execution:findTypeFeesByDesignationContainsAndRemoveIsFalsePage(String,int,int)") ;
        return typeFeesDtoPage;
    }

    /**
        * This method finds all entities deleted in the database per page
        * @param tag : designation of the entity
        * @param page : the page number
        * @param size : the size of the page
        * @return Map<String,Object> : the list of entities
     */
    public Map<String,Object> findTypeFeesByDesignationContainsAndRemoveIsTruePage(String tag, int page, int size ){
        log.info("execution of the method:findTypeFeesByDesignationContainsAndRemoveIsTruePage(String,int,int) : {"+ tag+","+page+","+size+"}") ;
        Map<String,Object> typeFeesDtoPage = mapper.entitiesFromDtosPage(
                typeFeesRepository.findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(tag, PageRequest.of(page,size))
        );
        log.info("end of method execution:findTypeFeesByDesignationContainsAndRemoveIsTruePage(String,int,int)") ;
        return typeFeesDtoPage;
    }
}
