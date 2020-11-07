package co.edu.udea.eplatform.component.career.service;

import co.edu.udea.eplatform.component.career.model.Career;
import co.edu.udea.eplatform.component.career.service.model.CareerQuerySearchCmd;
import co.edu.udea.eplatform.component.career.service.model.CareerSaveCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface CareerService {

    Career create(@NotNull CareerSaveCmd roadmapToCreateCmd);

    Career findById(@NotNull Long id);

    Page<Career> findByParameters(@NotNull CareerQuerySearchCmd queryCriteria, @NotNull Pageable pageable);
}
