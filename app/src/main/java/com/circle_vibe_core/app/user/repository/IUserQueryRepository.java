import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.circle_vibe_core.app.shared.dtos.PaginationParamsDto;
import com.circle_vibe_core.app.user.dto.UserByTokenParams;
import com.circle_vibe_core.app.user.dto.UserFilterParams;
import com.circle_vibe_core.app.user.entity.User;

public interface IUserQueryRepository {
    User getByToken(UserByTokenParams params);
    boolean existsByFilter(UserFilterParams params);
    User getByFilter(UserFilterParams params);
    List<User> getAll(UserFilterParams params);
    User getById(Long id);
	Long count(UserFilterParams params);
	Page<User> getAllPaginated(PaginationParamsDto paginationDto, UserFilterParams params);
}