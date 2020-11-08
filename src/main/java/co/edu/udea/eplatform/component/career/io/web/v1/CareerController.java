package co.edu.udea.eplatform.component.career.io.web.v1;

import co.edu.udea.eplatform.component.career.io.web.v1.model.CareerListResponse;
import co.edu.udea.eplatform.component.career.io.web.v1.model.CareerQuerySearchRequest;
import co.edu.udea.eplatform.component.career.io.web.v1.model.CareerSaveRequest;
import co.edu.udea.eplatform.component.career.io.web.v1.model.CareerSaveResponse;
import co.edu.udea.eplatform.component.career.model.Career;
import co.edu.udea.eplatform.component.career.service.CareerService;
import co.edu.udea.eplatform.component.career.service.model.CareerQuerySearchCmd;
import co.edu.udea.eplatform.component.career.service.model.CareerSaveCmd;
import co.edu.udea.eplatform.component.shared.model.ErrorMessage;
import co.edu.udea.eplatform.component.shared.model.ResponsePagination;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping(path = "/api/v1/careers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CareerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CareerService careerService;

    @PostMapping
    @ApiOperation(value = "Create a career", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created."),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> create(@Valid @NotNull @RequestBody CareerSaveRequest careerToCreate){
        logger.debug("Begin create: careerToCreate = {}", careerToCreate);

        CareerSaveCmd careerToCreateCmd = CareerSaveRequest.toModel(careerToCreate);

        Career careerCreated = careerService.create(careerToCreateCmd);

        URI location = fromUriString("/api/v1/careers").path("/{id}")
                .buildAndExpand(careerCreated.getId()).toUri();

        logger.debug("End create: careerCreated = {}", careerCreated);
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Find a career by id.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success.", response = CareerSaveResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)
    })
    public ResponseEntity<CareerSaveResponse> findById(@Valid @PathVariable("id") @NotNull Long id){
        logger.debug("Begin findById: id = {}", id);

        Career careerFound = careerService.findById(id);

        logger.debug("End findById: careerFound = {}", careerFound);
        return ResponseEntity.ok(CareerSaveResponse.fromModel(careerFound));
    }

    @GetMapping
    @ApiOperation(value = "Find careers by parameters.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CareerListResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorMessage.class)

    })
    public ResponsePagination<CareerListResponse> findByParameters(@Valid @NotNull CareerQuerySearchRequest queryCriteria,
                                                                   @PageableDefault(page = 0, size = 15,
                                                                   direction = Sort.Direction.DESC, sort = "id") Pageable pageable){
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        CareerQuerySearchCmd queryCriteriaCmd = CareerQuerySearchRequest.toModel(queryCriteria);

        Page<Career> careersFound = careerService.findByParameters(queryCriteriaCmd, pageable);

        List<CareerListResponse> careersFoundList = careersFound.stream().map(CareerListResponse::fromModel)
                .collect(Collectors.toList());

        logger.debug("End findByParameters: careersFound = {}", careersFound);
        return ResponsePagination.fromObject(careersFoundList, careersFound.getTotalElements(), careersFound.getNumber(),
                careersFoundList.size());
    }
}
