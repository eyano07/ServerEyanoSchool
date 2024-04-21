package io.eyano.eyanoschool.feesservice.entitiesService;

import io.eyano.eyanoschool.feesservice.dao.FeesRepository;
import io.eyano.eyanoschool.feesservice.dao.SliceFeesRepository;
import io.eyano.eyanoschool.feesservice.dao.TypeFeesRepository;
import io.eyano.eyanoschool.feesservice.dtos.FeesDto;
import io.eyano.eyanoschool.feesservice.entities.Fees;
import io.eyano.eyanoschool.feesservice.entities.FeesAllocation;
import io.eyano.eyanoschool.feesservice.entities.SliceFees;
import io.eyano.eyanoschool.feesservice.entities.TypeFees;
import io.eyano.eyanoschool.feesservice.exceptions.IdIsNullException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundException;
import io.eyano.eyanoschool.feesservice.exceptions.IdNotFoundParamException;
import io.eyano.eyanoschool.feesservice.mappers.FeesMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * FeesService is a class that implements the CrudService interface and has generic type FeesDto and Long
 * @author : Pascal Tshingila
 * @since : 02/02/2021
 * @version : 1.0
 * @see CrudService
 * @see FeesDto
 * @see Fees
 */
@Service @Transactional
@AllArgsConstructor
@Slf4j
public class FeesService implements CrudService<FeesDto, Long> {
    FeesMapper mapper;
    FeesRepository feesRepository;
    SliceFeesRepository sliceFeesRepository;
    TypeFeesRepository typeFeesRepository;

    /**
        * implementation of methode save from CrudService interface for saving FeesDto entity
        * @param entity : FeesDto
        * @return : the FeesDto saved
        * @throws IdNotFoundException (if the entity Slice and TypeFees is not found)
        * @throws IdIsNullException (if the entity Slice and TypeFees is null)
     */
    @Override
    public FeesDto save(FeesDto entity) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:save(FeesDto entity) : {"+entity+"}");
        Fees fees = mapper.dtoFromEntity(entity);

        // star check if the sliceFees and typeFees exist
        if(entity.getSliceFees().getId() == null) throw new IdIsNullException("The id SliceFees is null");
        sliceFeesRepository.findById(fees.getSliceFees().getId()).orElseThrow(
                () -> new IdNotFoundException("SliceFees with id " + fees.getSliceFees().getId() + " not found"));
        if(entity.getTypeFees().getId()==null) throw new IdIsNullException("The id TypeFees is null");
        typeFeesRepository.findById(fees.getTypeFees().getId()).orElseThrow(
                () -> new IdNotFoundException("TypeFees with id " + fees.getTypeFees().getId() + " not found"));
        // end check if the sliceFees and typeFees exist

        FeesDto feesDto = mapper.entityFromDTO(feesRepository.save(fees));
        log.info("the creation of the entity : {"+ feesDto+"}");
        return feesDto;
    }
    /**
        * implementation of methode update from CrudService interface for updating FeesDto entity
        * @param entity : the entity to update
        * @return the entity updated
        * @throws IdNotFoundException (if the entity is not found)
        * @throws IdIsNullException (if the entity id is null)
     */
    @Override
    public FeesDto update(FeesDto entity) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:update(FeesDto entity) : {"+entity+"}" );

        //Star of the verification of the id ------------------------------------
        if(entity.getId()==null){
            throw new IdIsNullException("The id Fees is null");
        }
        feesRepository.findByIdAndRemoveIsFalse(entity.getId()).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesDto feesDto = save(entity);
        log.info("entity update : {" +feesDto+"}");
        return feesDto;
    }
    /**
        * implementation of methode remove from CrudService interface for removing FeesDto entity
        * @param feesDto : FeesDto
        * @throws IdNotFoundException (if the entity is not found)
        * @throws IdIsNullException (if the entity id is null)
        * @return : boolean (true if the entity is removed)
     */
    @Override
    public boolean remove(FeesDto feesDto) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:remove(FeesDto entity) : {"+feesDto+"}");

        //Star of the verification of the id ------------------------------------
        if(feesDto.getId()==null){
            throw new IdIsNullException("The id Fees is null");
        }
        Fees fees = feesRepository.findByIdAndRemoveIsFalse(feesDto.getId()).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesDto result = mapper.entityFromDTO(fees);
        result.setRemove(true);

        boolean remove = update(result).isRemove();
        log.info("the entity : {"+ feesDto+"} is deleted in the database");
        return remove;
    }
    /**
        * implementation of methode removeById from CrudService interface for removing FeesDto entity by id
        * @param id : Long
        * @throws IdNotFoundException (if the entity is not found)
        * @throws IdIsNullException (if the entity id is null)
        * @return : boolean
     */
    @Override
    public boolean removeById(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:removeById(Long id) : {"+id+"}") ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id fees is null");
        }
        Fees fees = feesRepository.findByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesDto feesDto = mapper.entityFromDTO(fees);
        feesDto.setRemove(true);

        boolean remove = update(feesDto).isRemove();
        log.info("the entity : {"+ feesDto+"} is deleted in the database from id");
        return remove;
    }
    /**
        * implementation of methode restore from CrudService interface for restoring FeesDto entity
        * @param id : Long
        * @throws IdNotFoundException (if the entity is not found)
        * @throws IdIsNullException (if the entity id is null)
        * @return : boolean
     */
    @Override
    public boolean restore(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:restore(Long id) : {"+id+"}") ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id fees is null");
        }
        Fees fees = feesRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesDto feesDto = mapper.entityFromDTO(fees);
        feesDto.setRemove(false);

        update(feesDto);
        log.info("the entity : {"+ feesDto+"} is restored in the database from id");
        return true;
    }

    /**
        * implementation of methode isExist from CrudService interface for checking if FeesDto entity exist
        * @param id : Long
        * @throws IdNotFoundException (if the entity is not found)
        * @throws IdIsNullException (if the entity id is null)
        * @return : boolean
     */
    @Override
    public FeesDto isExist(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:isExist(Long id) : {"+id+"}") ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id fees is null");
        }
        Fees fees = feesRepository.findById(id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesDto feesDto = mapper.entityFromDTO(fees);
        log.info("the entity : {"+ feesDto+"} exists in the database");
        return feesDto;
    }
    /**
        * implementation of methode isRemove from CrudService interface for checking if FeesDto entity is removed
        * @param id : Long
        * @throws IdNotFoundException (if the entity is not found)
        * @throws IdIsNullException (if the entity id is null)
        * @return : boolean
     */
    @Override
    public boolean isRemove(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:isRemove(Long id) : {"+id+"}") ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id fees is null");
        }
        Fees fees = feesRepository.findById(id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        boolean remove = fees.isRemove();
        log.info("the remove attribute of the entity {"+ fees+"} is : "+fees.isRemove());
        return remove;
    }
    /**
        * implementation of methode findById from CrudService interface for finding FeesDto entity by id
        * @param id : Long
        * @throws IdNotFoundException (if the entity is not found)
        * @throws IdIsNullException (if the entity id is null)
        * @return : FeesDto
     */
    public FeesDto findById(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:findById(Long id) : {"+id+"}") ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id fees is null");
        }
        Fees fees = feesRepository.findByIdAndRemoveIsFalse(id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesDto feesDto = mapper.entityFromDTO(fees);
        log.info("end of method execution:findById() Entity : "+feesDto) ;
        return feesDto;
    }
    /**
        * implementation of methode findRemoveById for finding FeesDto entity by id and remove attribute is true
        * @param id : Long
        * @throws IdNotFoundException (if the entity is not found)
        * @throws IdIsNullException (if the entity id is null)
        * @return : FeesDto
     */
    @Override
    public FeesDto findRemoveById(Long id) throws IdNotFoundException, IdIsNullException {
        log.info("execution of the method:findRemoveById(Long id) : {"+id+"}" ) ;

        //Star of the verification of the id ------------------------------------
        if(id==null){
            throw new IdIsNullException("The id fees is null");
        }
        Fees fees = feesRepository.findByIdAndRemoveIsTrue(id).orElseThrow(IdNotFoundException::new);
        //End of the verification of the id ------------------------------------

        FeesDto feesDto = mapper.entityFromDTO(fees);
        log.info("end of method execution:findRemoveById() Entity : "+feesDto) ;
        return feesDto;
    }

    /**
     * implementation of methode findAll from CrudService interface for finding all FeesDto entity
     * @return List<FeesDto>
     */
    @Override
    public List<FeesDto> findAll() {
        log.info("execution of the method:findAll()") ;
        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(
                feesRepository.findFeesByRemoveIsFalse()
        );
        log.info("end of method execution:findAll()") ;
        return feesDtoList;
    }

    /**
     * This method finds all entities deleted in the database
     * @return List<FeesDto> : the list of entities removed found
     */
    public List<FeesDto> findAllRemove() {
        log.info("execution of the method:findAllRemove()") ;
        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(
                feesRepository.findFeesByRemoveIsTrue()
        );
        log.info("end of method execution:findAllRemove") ;
        return feesDtoList;
    }

    //##############################################################
    //###                                                        ###
    //###    The following methods are specific to the entity    ###
    //###                                                        ###
    //##############################################################

    /**
        * implementation of methode findByIdClassFessAndIdSchoolYear for finding FeesDto entity by idClass and idSchoolYear
        * @param idClass : Long
        * @param idSchoolYear : Long
        * @return : List<FeesDto>
        * @throws IdIsNullException (if the entity idClass and idSchoolYear is null)
     */
    public List<FeesDto>  findByIdClassFessAndIdSchoolYear(Long idClass, Long idSchoolYear) throws IdIsNullException {
        log.info("execution of the method:findByIdClassFessAndIdSchoolYear(Long, Long)") ;

        //Star of the verification of the id school year and idClass------------------------------------
        if(idSchoolYear==null){
            throw new IdIsNullException("The id school year is null");
        }
        if(idClass==null){
            throw new IdIsNullException("The id class is null");
        }
        //todo: add test for school year id and class id is exist
        //End of the verification of the id school year and idClass------------------------------------

        List<Fees> feesList = feesRepository.findByIdClassFessAndIdSchoolYearAndRemoveIsFalse(idClass, idSchoolYear);

        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(feesList);
        log.info("end of method execution:findByIdClassFessAndIdSchoolYear() : "+feesDtoList) ;
        return feesDtoList;
    }
   /**
        * implementation of methode findByIdClassFessAndIdSchoolYearAndSliceFeesId for finding FeesDto entity by idClass, idSchoolYear and typeFeesId
        * @param idClass : Long
        * @param idSchoolYear : Long
        * @param sliceFeesId : Long
        * @throws IdIsNullException (if the entity idClass, idSchoolYear and sliceFeesId is null)
        * @return : List<FeesDto>
     */
    public  List<FeesDto>  findByIdClassFessAndIdSchoolYearAndSliceFeesId(Long idClass, Long idSchoolYear,Long sliceFeesId) throws IdIsNullException {
        log.info("execution of the method:findByIdClassFessAndIdSchoolYearAndSliceFeesId(Long, Long,Long)") ;

        //Star of the verification of the id school year, idClass and sliceFeesId------------------------------------
        if(idSchoolYear==null){
            throw new IdIsNullException("The id school year is null");
        }
        if(idClass==null){
            throw new IdIsNullException("The id class is null");
        }
        if(sliceFeesId==null){
            throw new IdIsNullException("The id slice fees is null");
        }
        //todo: add test for school year id, class id and slice id is exist
        //End of the verification of the id school year, idClass and sliceFeesId------------------------------------

        List<Fees> feesList= feesRepository.findByIdClassFessAndIdSchoolYearAndSliceFeesIdAndRemoveIsFalse(idClass, idSchoolYear,sliceFeesId);

        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(feesList);
        log.info("end of method execution:findByIdClassFessAndIdSchoolYearAndSliceFeesId() Entity : "+feesDtoList) ;
        return feesDtoList;
    }

    /**
        * implementation of methode findFeesByRemoveIsFalseAndDesignationContains for finding all FeesDto entity per page
        * @param page : int
        * @param size : int
        * @return Map<String,Object> : detail page
     */
    public Map<String,Object> findFeesByRemoveIsFalseAndDesignationContains(String tag, int page, int size){
        log.info("execution of the method:findFeesByRemoveIsFalseAndDesignationContains(int, int)") ;
        Map<String,Object> feesPage = mapper.entitiesFromDtoPage(feesRepository.findFeesByRemoveIsFalseAndDesignationIgnoreCaseContains(tag,PageRequest.of(page, size)));
        log.info("end of method execution:findFeesByRemoveIsFalseAndDesignationContains() : "+feesPage) ;
        return feesPage;
    }
    /**
        * implementation of methode findFeesByRemoveIsTrueAndDesignationContains for finding all FeesDto entity that are removed and contain tag
        * @param page : int
        * @param size : int
        * @param tag : String
        * @return Map<String,Object> : detail page
     */
    public Map<String,Object> findFeesByRemoveIsTrueAndDesignationContains(String tag, int page, int size){
        log.info("execution of the method:findFeesByRemoveIsTrueAndDesignationContains(String,int, int)") ;
        Map<String,Object> feesPage = mapper.entitiesFromDtoPage(feesRepository.findFeesByRemoveIsTrueAndDesignationIgnoreCaseContains(tag,PageRequest.of(page, size)));
        log.info("end of method execution:findFeesByRemoveIsTrueAndDesignationContains() : "+feesPage) ;
        return feesPage;
    }
    /**
        * implementation of methode findByIdSchoolYearAndRemoveIsFalseAndDesignationContains for finding all FeesDto entity by idSchoolYear and designation
        * @param idSchoolYear : Long
        * @param designation : String
        * @throws IdIsNullException (if the id school year is null)
        * @return List<FeesDto>
     */
    public List<FeesDto> findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(Long idSchoolYear, String designation) throws IdIsNullException {
        log.info("execution of the method:findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(Long, String)") ;

        //Star of the verification of the id school year------------------------------------
        if(idSchoolYear==null){
            throw new IdIsNullException("The id school year is null");
        }
        //todo: add test for school year id is exist
        //End of the verification of the id school year------------------------------------
;
        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(
                feesRepository.findByIdSchoolYearAndRemoveIsFalseAndDesignationIgnoreCaseContains(idSchoolYear, designation)
        );
        log.info("end of method execution:findByIdSchoolYearAndRemoveIsFalseAndDesignationContains() : "+feesDtoList) ;
        return feesDtoList;
    }
    /**
        * implementation of methode findByIdSchoolYearAndRemoveIsFalseAndDesignationContains for finding all FeesDto entity by idSchoolYear and designation per page
        * @param idSchoolYear : Long
        * @param designation : String
        * @param page : int
        * @param size : int
        * @return Map<String,Object> : detail page
        * @throws IdIsNullException (if the id school year is null)
     */
    public Map<String,Object> findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(Long idSchoolYear, String designation, int page, int size) throws IdIsNullException {
        log.info("execution of the method:findByIdSchoolYearAndRemoveIsFalseAndDesignationContains(Long, String, int, int)") ;

        //Star of the verification of the id school year------------------------------------
        if(idSchoolYear==null){
            throw new IdIsNullException("The id school year is null");
        }
        //todo: add test for school year id is exist
        //End of the verification of the id school year------------------------------------

        Map<String,Object> feesPage = mapper.entitiesFromDtoPage(
                feesRepository.findByIdSchoolYearAndRemoveIsFalseAndDesignationIgnoreCaseContains(idSchoolYear, designation, PageRequest.of(page, size))
        );
        log.info("end of method execution:findByIdSchoolYearAndRemoveIsFalseAndDesignationContains() : "+feesPage) ;
        return feesPage;
    }
    /**
        * implementation of methode findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess for finding all FeesDto entity by idSchoolYear, idTypeFees and idClass
        * @param idClass : Long
        * @param idSchoolYear : Long
        * @param idTypeFees : Long
        * @return List<FeesDto>
        * @throws IdIsNullException (if the id school year, idClass and idTypeFees is null)
     */
    public List<FeesDto> findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess(Long idClass,Long idSchoolYear, Long idTypeFees) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method:findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess(long, long, long)") ;

        //Star of the verification of the id school year, idClass and idTypeFees------------------------------------
        if(idSchoolYear==null){
            throw new IdIsNullException("The id school year is null");
        }
        if(idClass==null){
            throw new IdIsNullException("The id class is null");
        }
        if(idTypeFees==null){
            throw new IdIsNullException("The id slice fees is null");
        }
        typeFeesRepository.findByIdAndRemoveIsFalse(idTypeFees).orElseThrow(
                () -> new IdNotFoundException("TypeFees with id " + idTypeFees + " not found"));
        //todo: add test for school year id and class id is exist
        //End of the verification of the id school year, idClass and idTypeFees------------------------------------

        List<Fees> feesList = feesRepository.findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess(idSchoolYear, idTypeFees, idClass);
        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(feesList);
        log.info("end of method execution:findByIdSchoolYearAndTypeFeesIdAndRemoveIsFalseAndIdClassFess() : "+feesDtoList) ;
        return feesDtoList;
    }

    /**
        * implementation of methode findAllDelete for finding all FeesDto entity that are removed
        * @param designation : String
        * @param idClass : Long
        * @param idSchoolYear : Long
        * @return List<FeesDto>
        * @throws IdIsNullException (if the id school year and idClass is null)
     */
    public List<FeesDto> findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear(String designation,Long idClass, Long idSchoolYear) throws IdIsNullException {
        log.info("execution of the method:findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear(String,long, long)") ;

        //Star of the verification of the id school year and idClass------------------------------------
        if(idSchoolYear==null){
            throw new IdIsNullException("The id school year is null");
        }
        if(idClass==null) {
            throw new IdIsNullException("The id class is null");
        }
        //todo: add test for school year id and class id is exist
        //End of the verification of the id school year and idClass------------------------------------

        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(
                feesRepository.findByDesignationIgnoreCaseContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear(designation, idClass, idSchoolYear)
        );
        log.info("end of method execution:findByDesignationContainsAndRemoveIsFalseAndIdClassFessAndIdSchoolYear : " + feesDtoList);
        return feesDtoList;
    }

    /**
        * implementation of methode findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue for finding all FeesDto entity by idClass, idSchoolYear and typeFeesId
        * @param idClass : Long
        * @param idSchoolYear : Long
        * @param idTypeFees : Long
        * @return List<FeesDto>
        * @throws IdIsNullException (if the id school year, idClass and idTypeFees is null)
     */
    public List<FeesDto> findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue(Long idClass, Long idSchoolYear,Long idTypeFees) throws IdIsNullException, IdNotFoundException {
        log.info("execution of the method:findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue(long, long, long)") ;

        //Star of the verification of the id school year, idClass and idTypeFees------------------------------------
        if(idSchoolYear==null){
            throw new IdIsNullException("The id school year is null");
        }
        if(idClass==null){
            throw new IdIsNullException("The id class is null");
        }
        if(idTypeFees==null){
            throw new IdIsNullException("The id slice fees is null");
        }
        typeFeesRepository.findByIdAndRemoveIsFalse(idTypeFees).orElseThrow(
                () -> new IdNotFoundException("TypeFees with id " + idTypeFees + " not found"));
        //todo: add test for school year id and class id is exist
        //End of the verification of the id school year, idClass and idTypeFees------------------------------------

        List<FeesDto> feesDtoList = mapper.entitiesFromDtos(
                feesRepository.findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue(idClass, idSchoolYear, idTypeFees)
        );
        log.info("end of method execution:findByIdClassFessAndIdSchoolYearAndTypeFeesIdAndRemoveIsTrue : " + feesDtoList); ;
        return feesDtoList;
    }


}
