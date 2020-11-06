package co.edu.udea.eplatform.component.roadmap.io.web.v1;

import co.edu.udea.eplatform.component.roadmap.io.web.v1.model.RoadmapListResponse;
import co.edu.udea.eplatform.component.roadmap.io.web.v1.model.RoadmapQuerySearchRequest;
import co.edu.udea.eplatform.component.roadmap.io.web.v1.model.RoadmapSaveRequest;
import co.edu.udea.eplatform.component.roadmap.io.web.v1.model.RoadmapSaveResponse;
import co.edu.udea.eplatform.component.roadmap.model.Roadmap;
import co.edu.udea.eplatform.component.roadmap.service.RoadmapService;
import co.edu.udea.eplatform.component.roadmap.service.model.RoadmapQuerySearchCmd;
import co.edu.udea.eplatform.component.roadmap.service.model.RoadmapSaveCmd;
import co.edu.udea.eplatform.component.shared.model.ResponsePagination;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping(path = "/api/v1/roadmaps", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RoadmapController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RoadmapService roadmapService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@Valid @NotNull @RequestBody RoadmapSaveRequest roadmapToCreate){
        logger.debug("Begin create: roadmapToCreate = {}", roadmapToCreate);

        RoadmapSaveCmd roadmapToCreateCmd = RoadmapSaveRequest.toModel(roadmapToCreate);

        Roadmap roadmapCreated = roadmapService.create(roadmapToCreateCmd);

        URI location = fromUriString("/api/v1/roadmaps").path("/{id}")
                .buildAndExpand(roadmapCreated.getId()).toUri();

        logger.debug("End create: roadmapCreated = {}", roadmapCreated);
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RoadmapSaveResponse> findById(@Valid @PathVariable("id") @NotNull Long id){
        logger.debug("Begin findById: id = {}", id);

        Roadmap roadmapFound = roadmapService.findById(id);

        logger.debug("End findById: roadmapFound = {}", roadmapFound);
        return ResponseEntity.ok(RoadmapSaveResponse.fromModel(roadmapFound));
    }

    @GetMapping
    public ResponsePagination<RoadmapListResponse> findByParameters(@Valid @NotNull RoadmapQuerySearchRequest queryCriteria,
                                                                    @PageableDefault(page = 0, size = 15,
                                                                   direction = Sort.Direction.DESC, sort = "id") Pageable pageable){
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        RoadmapQuerySearchCmd queryCriteriaCmd = RoadmapQuerySearchRequest.toModel(queryCriteria);

        Page<Roadmap> roadmapsFound = roadmapService.findByParameters(queryCriteriaCmd, pageable);

        List<RoadmapListResponse> roadmapsFoundList = roadmapsFound.stream().map(RoadmapListResponse::fromModel)
                .collect(Collectors.toList());

        logger.debug("End findByParameters: roadmapsFound = {}", roadmapsFound);
        return ResponsePagination.fromObject(roadmapsFoundList, roadmapsFound.getTotalElements(), roadmapsFound.getNumber(),
                roadmapsFoundList.size());
    }
}