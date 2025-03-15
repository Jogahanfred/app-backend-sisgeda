package pe.mil.fap.service.general.inf;

import java.util.List;
import java.util.Optional;

import pe.mil.fap.dto.helpers.DatasetGraphicDTO;
import pe.mil.fap.dto.helpers.DatasetNumberEntriesPerPersonDTO;
import pe.mil.fap.dto.helpers.GraphicPatternsDTO;
import pe.mil.fap.dto.helpers.StatsWidgetDTO;
import pe.mil.fap.service.exception.ServiceException;

public interface GraphicService {

	List<GraphicPatternsDTO> findGraphicPatternsByMonthAndYear(String month, String year) throws ServiceException;
	List<DatasetGraphicDTO> findDatasetGraphic() throws ServiceException;
	List<DatasetNumberEntriesPerPersonDTO> findReportNumberEntriesPerPersonDTO(String month) throws ServiceException;
	Optional<StatsWidgetDTO> findStatsWidgets() throws ServiceException;
}
