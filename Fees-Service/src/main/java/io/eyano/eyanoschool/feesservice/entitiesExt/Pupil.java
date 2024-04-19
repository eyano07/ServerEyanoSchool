package io.eyano.eyanoschool.feesservice.entitiesExt;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
/**
 * This is a class which represents an external entity coming from the Teaching-Service module
 * @author : Pascal
 * @since : 02/02/2021
 * @version : 1.0
 */
@Data @Builder @ToString
public class Pupil {

    private String registration;
    private String phone;
    private String name;
    private String lastname;
    private String firstname;
    private LocalDate birthDate;
    private String tutorPhone;
    private char gender;

}
