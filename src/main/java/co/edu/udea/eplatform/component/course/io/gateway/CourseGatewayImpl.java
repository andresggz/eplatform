package co.edu.udea.eplatform.component.course.io.gateway;

import co.edu.udea.eplatform.component.course.io.repository.CourseRepository;
import co.edu.udea.eplatform.component.course.model.Course;
import co.edu.udea.eplatform.component.course.service.CourseGateway;
import co.edu.udea.eplatform.component.shared.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class CourseGatewayImpl implements CourseGateway {

    private static final String RESOURCE_NOT_FOUND = "Course not found";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CourseRepository courseRepository;

    @Override
    public Course save(@NotNull Course courseToCreate) {
        logger.debug("Begin save: courseToCreate = {}", courseToCreate);

        final Course courseToBeCreated =
                courseToCreate.toBuilder().createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        final Course courseCreated = courseRepository.save(courseToBeCreated);

        logger.debug("End save: courseCreated = {}", courseCreated);
        return courseCreated;
    }

    @Override
    public Course findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        Course courseFound = courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESOURCE_NOT_FOUND));

        logger.debug("End findById: courseFound = {}", courseFound);
        return courseFound;
    }
}
