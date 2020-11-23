package co.edu.udea.eplatform.component.roadmap.service;

import co.edu.udea.eplatform.component.course.model.CourseId;
import co.edu.udea.eplatform.component.roadmap.service.model.CourseIdSaveCmd;

import javax.validation.constraints.NotNull;

public interface CourseIdService {

    CourseId registerCourseId(@NotNull CourseIdSaveCmd courseIdToRegisterCmd);

    CourseId findById(@NotNull Long id);
}
