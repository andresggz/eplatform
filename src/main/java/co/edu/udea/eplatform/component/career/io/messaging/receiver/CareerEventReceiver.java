package co.edu.udea.eplatform.component.career.io.messaging.receiver;

import co.edu.udea.eplatform.component.career.model.RoadmapId;
import co.edu.udea.eplatform.component.career.service.RoadmapIdService;
import co.edu.udea.eplatform.component.career.service.model.RoadmapIdSaveCmd;
import co.edu.udea.eplatform.component.roadmap.messaging.publisher.model.RoadmapCreatedEvent;
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
public class CareerEventReceiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoadmapIdService roadmapIdService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @EventListener
    void handleRoadmapCreatedEvent(RoadmapCreatedEvent event){
        logger.debug("Begin handleRoadmapCreatedEvent: event = {}", event);

        RoadmapIdSaveCmd roadmapIdToRegisterCmd = RoadmapIdSaveCmd.builder()
                .id(event.getId())
                .build();

        RoadmapId roadmapIdRegistered = roadmapIdService.registerRoadmapId(roadmapIdToRegisterCmd);

        logger.debug("End handleRoadmapCreatedEvent: roadmapIdRegistered = {}", roadmapIdRegistered);
    }
}
