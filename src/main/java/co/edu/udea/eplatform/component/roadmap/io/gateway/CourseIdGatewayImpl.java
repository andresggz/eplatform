package co.edu.udea.eplatform.component.roadmap.io.gateway;

import co.edu.udea.eplatform.component.roadmap.io.repository.CourseIdRepository;
import co.edu.udea.eplatform.component.roadmap.model.CourseId;
import co.edu.udea.eplatform.component.roadmap.service.CourseIdGateway;
import co.edu.udea.eplatform.component.shared.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class CourseIdGatewayImpl implements CourseIdGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String RESOURCE_NOT_FOUND = "CourseId not found.";

    private final CourseIdRepository courseIdRepository;

    @Override
    public CourseId register(@NotNull CourseId courseIdToRegister) {
        logger.debug("Begin register: courseIdToRegister = {}", courseIdToRegister);

        final CourseId courseIdToBeRegistered =
                courseIdToRegister.toBuilder().createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CourseId courseIdRegistered = courseIdRepository.save(courseIdToBeRegistered);

        logger.debug("End register: courseIdRegistered = {}", courseIdRegistered);
        return courseIdRegistered;
    }

    @Override
    public CourseId findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        CourseId courseIdFound = courseIdRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(RESOURCE_NOT_FOUND));

        logger.debug("End findById: courseIdFound = {}", courseIdFound);
        return courseIdFound;
    }
}
