package pe.mil.fap.service.processes.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.mil.fap.entity.DocumentEntity;
import pe.mil.fap.entity.SquadronEntity;
import pe.mil.fap.entity.VisitEntity;
import pe.mil.fap.entity.VisitorEntity;
import pe.mil.fap.entity.WorkingHoursEntity;
import pe.mil.fap.entity.ScheduleEntity;
import pe.mil.fap.exception.BadRequestException;
import pe.mil.fap.exception.NotFoundException;
import pe.mil.fap.repository.ScheduleRepository;
import pe.mil.fap.service.processes.inf.VisitService;
import pe.mil.fap.service.processes.inf.ScheduleService;
import pe.mil.fap.utils.constants.MessageConstants;
import pe.mil.fap.utils.enums.DayOfWeekEnum;
import pe.mil.fap.utils.enums.PersonalSituationEnum;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository repo;

	@Override
	public List<ScheduleEntity> findAll() throws ServiceException {
		try {
			return repo.findAll();
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<ScheduleEntity> findAllHistory() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScheduleEntity> findByLike(ScheduleEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ScheduleEntity> findByID(Long id) throws ServiceException {
		try {
			return repo.findById(id);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public ScheduleEntity save(ScheduleEntity schedule) throws ServiceException {
		try {
			if(schedule.isNameDayDuplicate()){
				throw new BadRequestException(MessageConstants.INFO_DUPLICATE_DAY);				
			}
			for (WorkingHoursEntity workingHour : schedule.getWorkingHours()) {
				if (!workingHour.validSchedule()) {
					throw new BadRequestException(MessageConstants.INFO_INVALID_START_DATE_WORK);
				}
				workingHour.setSchedule(schedule);
			}
			return repo.save(schedule);
		} catch (Exception exception) {
			exception.printStackTrace();
			if (exception instanceof NotFoundException) {
				throw new NotFoundException(exception.getMessage());
			} else if (exception instanceof BadRequestException) {
				throw new BadRequestException(exception.getMessage());
			} else {
				throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
			}
		}
	}

	@Override
	public Boolean update(ScheduleEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(ScheduleEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
