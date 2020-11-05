package co.edu.udea.eplatform.component.course.service;

import co.edu.udea.eplatform.component.course.model.Course;
import co.edu.udea.eplatform.component.course.service.model.CourseSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import static java.util.Objects.nonNull;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CourseGateway courseGateway;

    @Override
    public Course create(@NotNull CourseSaveCmd courseToCreateCmd) {
        logger.debug("Begin create: courseToCreateCmd");

        Course courseToCreate = CourseSaveCmd.toModel(courseToCreateCmd);

        activateOrNot(courseToCreate, courseToCreateCmd);

        Course courseCreated = courseGateway.save(courseToCreate);

        logger.debug("End create: courseCreated = {}", courseCreated);
        return courseCreated;
    }

    private void activateOrNot(@NotNull Course courseToCreate,
                                     @NotNull CourseSaveCmd courseToCreateCmd) {
        courseToCreate.setActive(
                nonNull(courseToCreateCmd.getReleaseDate()));
    }
}
