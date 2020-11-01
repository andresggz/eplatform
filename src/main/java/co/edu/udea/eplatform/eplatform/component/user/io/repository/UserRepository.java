package co.edu.udea.eplatform.eplatform.component.user.io.repository;

import co.edu.udea.eplatform.eplatform.component.user.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
