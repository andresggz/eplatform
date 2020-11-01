package co.edu.udea.eplatform.eplatform.component.user.io.gateway;

import co.edu.udea.eplatform.eplatform.component.shared.web.exception.ResourceNotFoundException;
import co.edu.udea.eplatform.eplatform.component.user.io.repository.UserRepository;
import co.edu.udea.eplatform.eplatform.component.user.model.User;
import co.edu.udea.eplatform.eplatform.component.user.service.UserGateway;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String RESOURCE_NOT_FOUND = "User not found";

    private final UserRepository userRepository;

    @Override
    public User save(@NotNull User userToCreate) {
        logger.debug("Begin save: userToCreate = {}", userToCreate);

        final User userToBeCreated =
                userToCreate.toBuilder().createDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .state(true)
                        .build();

        final User userCreated = userRepository.save(userToBeCreated);


        logger.debug("End save: userCreated = {}", userCreated);
        return userCreated;
    }

    @Override
    public User findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        final User userFound = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));

        logger.debug("End findById: userFound = {}", userFound);
        return userFound;
    }

    @Override
    public User update(@NotNull User userToUpdate) {
        logger.debug("Begin update: userToUpdate = {}", userToUpdate);

        final User userToBeUpdated =
                userToUpdate.toBuilder().updateDate(LocalDateTime.now()).build();

        final User userUpdated = userRepository.save(userToBeUpdated);

        logger.debug("End update = userUpdated = {}", userUpdated);
        return userUpdated;
    }

    @Override
    public void deleteById(@NotNull Long id) {
        logger.debug("Begin findById = {}", id);

        findById(id);
        userRepository.deleteById(id);

        logger.debug("End deleteById");
    }
}
