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
        log.info("execution of the method:save(TypeFeesDto entity)");
        TypeFees typeFees = mapper.dtoFromEntity(entity);
        TypeFeesDto typeFeesDto = mapper.entityFromDTO(typeFeesRepository.save(typeFees));
        log.info("the creation of the entity : {"+ typeFeesDto+"}");
        return typeFeesDto;
    }

    /**
        * This method updates an entity in the database
        * @param entity : the entity to update
        * @return the entity updated
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public TypeFeesDto update(TypeFeesDto entity) throws IdNotFoundException {
        log.info("execution of the method:update(TypeFeesDto entity)");
        typeFeesRepository.findById(entity.getId()).orElseThrow(IdNotFoundException::new);
        TypeFeesDto typeFeesDtoSave = save(entity);
        log.info("entity update : {" +typeFeesDtoSave+"}");
        return typeFeesDtoSave;
    }

    /**
        * This method deletes an entity in the database
        * @param entity : the entity to delete
        * @return boolean : the entity deleted
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean remove(TypeFeesDto entity) throws IdNotFoundException {
        log.info("execution of the method:remove(TypeFeesDto entity)");
        TypeFees typeFees = typeFeesRepository.findByIdAndRemoveIsFalse(entity.getId()).orElseThrow(IdNotFoundException::new);
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
     */
    @Override
    public boolean removeById(Long id) throws IdNotFoundException {
        log.info("execution of the method:removeById(Long id)") ;
        TypeFees typeFees = typeFeesRepository.findByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new);
        TypeFeesDto typeFeesDto = mapper.entityFromDTO(typeFees);
        typeFeesDto.setRemove(true);
        boolean remove = update(typeFeesDto).isRemove();
        log.info("the entity : {"+ typeFeesDto+"} is deleted in the database from id");
        return remove;
    }

    /**
        * This method finds an entity in the database
        * @param id : the entity to find
        * @return the entity found
        * @throws IdNotFoundException : if the entity is not found
     */
    public TypeFeesDto findById(Long id)throws IdNotFoundException {
        log.info("execution of the method:findById(Long)") ;
        TypeFeesDto typeFeesDto = mapper.entityFromDTO(typeFeesRepository.findByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new));
        log.info("end of method execution:findById(Long)") ;
        return typeFeesDto;
    }

    /**
        * This method finds an entities deleted in the database
        * @param id : the entity to find
        * @return the entities deleted
        * @throws IdNotFoundException : if the entity is not found
     */
    public TypeFeesDto findByIdAndRemoveIsTrue(Long id)throws IdNotFoundException {
        log.info("execution of the method:findByIdAndRemoveIsTrue(Long)") ;
        TypeFeesDto typeFeesDto = mapper.entityFromDTO(typeFeesRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new));
        log.info("end of method execution:findByIdAndRemoveIsTrue(Long)") ;
        return typeFeesDto;
    }


    /**
        * This method finds an entities in the database
        * @param id : the entity to find
        * @return the entities
        * @throws IdNotFoundException : if the entity is not found
     */
    public TypeFeesDto findByIdAndRemoveIsFalse(Long id)throws IdNotFoundException {
        log.info("execution of the method:findByIdAndRemoveIsFalse(Long)") ;
        TypeFeesDto typeFeesDto = mapper.entityFromDTO(typeFeesRepository.findByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new));
        log.info("end of method execution:findByIdAndRemoveIsFalse(Long)") ;
        return typeFeesDto;
    }

    /**
        * This method restores an entity in the database
        * @param id : the entity to restore
        * @return boolean : the entity restored
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException {
        log.info("execution of the method:restore(Long id)") ;
        TypeFees typeFees = typeFeesRepository.findById(id).orElseThrow(IdNotFoundException::new);
        TypeFeesDto typeFeesDto = mapper.entityFromDTO(typeFees);
        typeFeesDto.setRemove(false);
        boolean remove = update(typeFeesDto).isRemove();
        log.info("the entity : {"+ typeFeesDto+"} is restored in the database");
        return remove;
    }

    /**
        * This method checks if an entity exists in the database
        * @param id : the entity to check
        * @return boolean : true if the entity exists
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean isExist(Long id) throws IdNotFoundException {
        log.info("execution of the method:isExist(Long id)") ;
        TypeFees typeFees = typeFeesRepository.findById(id).orElseThrow(IdNotFoundException::new);
        log.info("the entity : {"+ typeFees+"} exists in the database");
        return true;
    }

    /**
        * This method finds all deleted entities in the database
        * @return deleted entities
     */
    public List<TypeFeesDto> findAllDelete() {
        log.info("execution of the method:findAllDelete") ;
        List<TypeFeesDto> typeFeesDtoList = mapper.entitiesFromDtos(typeFeesRepository.findTypeFeesByRemoveIsTrue());
        log.info("end of method execution:findAllDelete") ;
        return typeFeesDtoList;
    }

    /**
        * This method checks if an entity is deleted in the database
        * @param id : the entity to check
        * @return boolean : true if the entity is deleted
        * @throws IdNotFoundException : if the entity is not found
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException {
        log.info("execution of the method:isRemove(Long id)") ;
        TypeFees typeFees = typeFeesRepository.findById(id).orElseThrow(IdNotFoundException::new);
        boolean remove = typeFees.isRemove();
        log.info("the remove attribute of the entity {"+ typeFees+"} is : "+remove);
        return remove;
    }

    /**
        * This method finds all entities in the database
        * @return List<TypeFeesDto> : the list of entities
     */
    @Override
    public List<TypeFeesDto> findAll() {
        log.info("execution of the method:findAll()") ;
        List<TypeFeesDto> feesDtoList = mapper.entitiesFromDtos(typeFeesRepository.findTypeFeesByRemoveIsFalse());
        log.info("end of method execution:findAll()") ;
        return feesDtoList;
    }
    /**
        * This method finds all entities deleted in the database
        * @param tag : designation of the entity
        * @return List<TypeFeesDto> : the list of entities
     */
    public List<TypeFeesDto> findTypeFeesByDesignationContainsAndRemoveIsTrue(String tag){
        log.info("execution of the method:findTypeFeesByDesignationContainsAndRemoveIsTrue(String)") ;
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
        log.info("execution of the method:findTypeFeesByDesignationContainsAndRemoveIsFalse(String)") ;
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
        log.info("execution of the method:findTypeFeesByDesignationContainsAndRemoveIsFalsePage(String,int,int)") ;
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
        log.info("execution of the method:findTypeFeesByDesignationContainsAndRemoveIsTruePage(String,int,int)") ;
        Map<String,Object> typeFeesDtoPage = mapper.entitiesFromDtosPage(
                typeFeesRepository.findTypeFeesByDesignationIgnoreCaseContainsAndRemoveIsTrue(tag, PageRequest.of(page,size))
        );
        log.info("end of method execution:findTypeFeesByDesignationContainsAndRemoveIsTruePage(String,int,int)") ;
        return typeFeesDtoPage;
    }
}
