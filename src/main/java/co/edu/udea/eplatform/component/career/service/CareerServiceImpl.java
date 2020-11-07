package co.edu.udea.eplatform.component.career.service;

import co.edu.udea.eplatform.component.career.model.Career;
import co.edu.udea.eplatform.component.career.service.model.CareerQuerySearchCmd;
import co.edu.udea.eplatform.component.career.service.model.CareerSaveCmd;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;


@Service
@Transactional
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CareerGateway careerGateway;

    @Override
    public Career create(@NotNull CareerSaveCmd careerToCreateCmd) {
        logger.debug("Begin create: careerToCreateCmd");

        Career careerToCreate = CareerSaveCmd.toModel(careerToCreateCmd);

        activateOrNot(careerToCreate);

        Career careerCreated = careerGateway.save(careerToCreate);

        logger.debug("End create: careerCreated = {}", careerCreated);
        return careerCreated;
    }

    @Override
    @Transactional(readOnly = true)
    public Career findById(@NotNull Long id) {
        logger.debug("Begin findById = {}", id);

        Career careerFound = careerGateway.findById(id);

        logger.debug("End findById: careerFound = {}", careerFound);
        return careerFound;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Career> findByParameters(@NotNull CareerQuerySearchCmd queryCriteria, @NotNull Pageable pageable) {
        logger.debug("Begin findByParameters: queryCriteria = {}, pageable = {}", queryCriteria, pageable);

        Page<Career> careersFound = careerGateway.findByParameters(queryCriteria, pageable);

        logger.debug("End findByParameters: careersFound = {}", careersFound);
        return careersFound;
    }

    private void activateOrNot(@NotNull Career careerToCreate) {
        careerToCreate.setActive(true);
    }

}
