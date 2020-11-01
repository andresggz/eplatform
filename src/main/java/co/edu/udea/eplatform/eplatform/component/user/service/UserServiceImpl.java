package co.edu.udea.eplatform.eplatform.component.user.service;

import co.edu.udea.eplatform.eplatform.component.user.model.User;
import co.edu.udea.eplatform.eplatform.component.user.service.model.UserSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserGateway userGateway;


    @Override
    public User create(@NotNull UserSaveCmd userToCreateCmd) {
        logger.debug("Begin create: userToCreateCmd = {}", userToCreateCmd);

        User userToCreate = UserSaveCmd.toModel(userToCreateCmd);

        userToCreate.setPassword(userToCreateCmd.getPassword() + "hash");

        User userCreated = userGateway.save(userToCreate);

        logger.debug("End create: userCreated = {}", userCreated);

        return userCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(@NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);

        final User userFound = userGateway.findById(id);

        logger.debug("End findById: userFound = {}", userFound);
        return userFound;
    }


}
