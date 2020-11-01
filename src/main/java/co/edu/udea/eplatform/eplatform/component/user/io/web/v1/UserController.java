package co.edu.udea.eplatform.eplatform.component.user.io.web.v1;

import co.edu.udea.eplatform.eplatform.component.user.io.web.v1.model.UserSaveRequest;
import co.edu.udea.eplatform.eplatform.component.user.io.web.v1.model.UserSaveResponse;
import co.edu.udea.eplatform.eplatform.component.user.model.User;
import co.edu.udea.eplatform.eplatform.component.user.service.UserService;
import co.edu.udea.eplatform.eplatform.component.user.service.model.UserSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @NotNull @RequestBody UserSaveRequest userToCreate){
        logger.debug("Begin create: userToCreate = {}", userToCreate);

        UserSaveCmd userToCreateCmd = UserSaveRequest.toModel(userToCreate);

        User userCreated = userService.create(userToCreateCmd);

        URI location = fromUriString("/api/v1/users").path("/{id}")
                .buildAndExpand(userCreated.getId()).toUri();

        logger.debug("End create: userCreated = {}", userCreated);
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserSaveResponse> findById(@Valid @PathVariable("id") Long id){
        logger.debug("Begin findById = {}", id);

        User userFound = userService.findById(id);

        logger.debug("End findById = {}", userFound);
        return ResponseEntity.ok(UserSaveResponse.fromModel(userFound));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserSaveResponse> update(@Valid @RequestBody @NotNull UserSaveRequest userToUpdate,
                                                   @Valid @PathVariable("id") @NotNull Long id){
        logger.debug("Begin update: id = {}, userToUpdate = {}", id, userToUpdate);

        UserSaveCmd userToUpdateCmd = UserSaveRequest.toModel(userToUpdate);

        User userUpdated = userService.update(id, userToUpdateCmd);

        logger.debug("End update: userUpdated = {}", userUpdated);
        return ResponseEntity.ok(UserSaveResponse.fromModel(userUpdated));
    }
}
