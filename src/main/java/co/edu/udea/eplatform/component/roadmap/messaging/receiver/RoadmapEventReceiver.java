package co.edu.udea.eplatform.component.roadmap.messaging.receiver;

import co.edu.udea.eplatform.component.course.io.messaging.publisher.model.CourseCreatedEvent;
import co.edu.udea.eplatform.component.roadmap.service.CourseIdRoadmapService;
import co.edu.udea.eplatform.component.roadmap.service.model.CourseIdSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class RoadmapEventReceiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CourseIdRoadmapService courseIdRoadmapService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @EventListener
    void handleCourseCreatedEvent(CourseCreatedEvent event){
        logger.debug("Begin handleCourseCreatedEvent: event = {}", event);

        CourseIdSaveCmd courseIdToRegisterCmd = CourseIdSaveCmd.builder()
                .id(event.getId()).name(event.getName()).description(event.getDescription())
                .active(event.getActive()).iconId(event.getIconId()).level(event.getLevel())
                .build();

        courseIdRoadmapService.registerCourseId(courseIdToRegisterCmd);

        logger.debug("End handleCourseCreatedEvent");
    }
}
