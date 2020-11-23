package co.edu.udea.eplatform.component.roadmap.service;

import co.edu.udea.eplatform.component.course.model.CourseId;
import co.edu.udea.eplatform.component.roadmap.service.model.CourseIdSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseIdServiceImpl implements CourseIdService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CourseIdGateway courseIdGateway;

    @Override
    public CourseId registerCourseId(@NotNull CourseIdSaveCmd courseIdToRegisterCmd) {
        logger.debug("Begin registerCourseId: courseIdRegisteredCmd = {}", courseIdToRegisterCmd);

        CourseId courseIdToRegister = CourseIdSaveCmd.toModel(courseIdToRegisterCmd);

        CourseId courseIdRegistered = courseIdGateway.register(courseIdToRegister);

        logger.debug("End registerCourseId = {}", courseIdRegistered);
        return courseIdRegistered;
    }

    @Override
    @Transactional(readOnly = true)
    public CourseId findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        CourseId courseIdFound = courseIdGateway.findById(id);

        logger.debug("End findById: courseIdFound = {}", courseIdFound);
        return courseIdFound;
    }
}
