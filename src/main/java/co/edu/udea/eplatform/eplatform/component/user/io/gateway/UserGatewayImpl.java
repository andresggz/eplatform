package co.edu.udea.eplatform.eplatform.component.user.io.gateway;

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

    private final UserRepository userRepository;

    @Override
    public User save(@NotNull User userToCreate) {
        logger.debug("Begin save: userToCreate = {}", userToCreate);

        final User userToBeCreated =
                userToCreate.toBuilder().createDate(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .state(true)
                        .build();

        final User userCreated = userRepository.save(userToBeCreated);


        logger.debug("End save: userCreated = {}", userCreated);
        return userCreated;
    }
}
