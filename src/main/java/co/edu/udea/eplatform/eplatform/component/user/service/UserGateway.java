package co.edu.udea.eplatform.eplatform.component.user.service;

import co.edu.udea.eplatform.eplatform.component.user.model.User;

import javax.validation.constraints.NotNull;

public interface UserGateway {

    User save(@NotNull User userToCreate);

    User findById(@NotNull Long id);

    User update(@NotNull User userToUpdate);
}
