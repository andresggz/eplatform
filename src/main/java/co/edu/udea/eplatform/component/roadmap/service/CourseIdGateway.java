package co.edu.udea.eplatform.component.roadmap.service;

import co.edu.udea.eplatform.component.roadmap.model.CourseId;

import javax.validation.constraints.NotNull;

public interface CourseIdGateway {

    CourseId register(@NotNull CourseId courseIdToRegister);

    CourseId findById(@NotNull Long id);
}
