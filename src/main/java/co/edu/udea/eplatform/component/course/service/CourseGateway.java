package co.edu.udea.eplatform.component.course.service;

import co.edu.udea.eplatform.component.course.model.Course;

import javax.validation.constraints.NotNull;

public interface CourseGateway {

    Course save(@NotNull Course courseToCreate);
}
