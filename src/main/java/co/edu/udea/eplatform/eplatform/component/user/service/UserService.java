package co.edu.udea.eplatform.eplatform.component.user.service;

import co.edu.udea.eplatform.eplatform.component.user.model.User;
import co.edu.udea.eplatform.eplatform.component.user.service.model.UserSaveCmd;

import javax.validation.constraints.NotNull;

public interface UserService {

    User create(@NotNull UserSaveCmd userToCreateCmd);

    User findById(@NotNull Long id);

    User update(@NotNull Long id, @NotNull UserSaveCmd userToUpdateCmd);

    void deleteById(@NotNull Long id);
}
