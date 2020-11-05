package co.edu.udea.eplatform.component.course.service;

import co.edu.udea.eplatform.component.course.model.Course;
import co.edu.udea.eplatform.component.course.service.model.CourseSaveCmd;

import javax.validation.constraints.NotNull;

public interface CourseService {

    Course create(@NotNull CourseSaveCmd courseToCreateCmd);
}
