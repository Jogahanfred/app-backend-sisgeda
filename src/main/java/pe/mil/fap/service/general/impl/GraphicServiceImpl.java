package pe.mil.fap.service.general.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.mil.fap.dto.helpers.DatasetGraphicDTO;
import pe.mil.fap.dto.helpers.DatasetNumberEntriesPerPersonDTO;
import pe.mil.fap.dto.helpers.GraphicPatternsDTO;
import pe.mil.fap.dto.helpers.ReportNumberEntriesPerPersonDTO;
import pe.mil.fap.dto.helpers.StatsWidgetDTO;
import pe.mil.fap.repository.GraphicRepository;
import pe.mil.fap.repository.SegmentVisitorRepository;
import pe.mil.fap.repository.VisitRepository;
import pe.mil.fap.repository.VisitorRepository;
import pe.mil.fap.service.exception.ServiceException;
import pe.mil.fap.service.general.inf.GraphicService;
import pe.mil.fap.service.general.inf.VisitorService;
import pe.mil.fap.service.processes.inf.VisitService;
import pe.mil.fap.utils.constants.MessageConstants;
import pe.mil.fap.utils.functions.UtilHelpers;

@Service
public class GraphicServiceImpl implements GraphicService {

	@Autowired
	private GraphicRepository repo;
	
	@Autowired
	private VisitorService visitorService;
	
	@Autowired
	private VisitRepository visitRepo;
	
	@Autowired
	private SegmentVisitorRepository segmentVisitorRepo;
	
	@Autowired
	private VisitorRepository visitorRepo;

	@Override
	public List<GraphicPatternsDTO> findGraphicPatternsByMonthAndYear(String month, String year)
			throws ServiceException {
		try {
			return repo.findGraphicPatternsByMonthAndYear(month, year);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<DatasetGraphicDTO> findDatasetGraphic() throws ServiceException {
		try {
			Map<String, Integer> borderRadius = new HashMap<>();
			borderRadius.put("topLeft", 0);
			borderRadius.put("topRight", 0);
			borderRadius.put("bottomLeft", 0);
			borderRadius.put("bottomRight", 0);

			List<DatasetGraphicDTO> dataset = new ArrayList<>();
			for (int i = 0; i < LocalDate.now().getMonthValue(); i++) {
				int month = i + 1;
				DatasetGraphicDTO data = new DatasetGraphicDTO();
				data.setType("bar");
				data.setLabel(UtilHelpers.getMonth(month));
				data.setBackgroundColor(switch (month) {
				case 1: {
					yield "#ffc788";
				}
				case 2: {
					yield "#ffe593";
				}
				case 3: {
					yield "#a6ffa4";
				}
				case 4: {
					yield "#e996b6";
				}
				case 5: {
					yield "#9cfddd";
				}
				case 6: {
					yield "#a2beff";
				}
				case 7: {
					yield "#9df2ff";
				}
				case 8: {
					yield "#ffa1be";
				}
				case 9: {
					yield "#fffd97";
				}
				case 10: {
					yield "#adee9c";
				}
				case 11: {
					yield "#bffcf8";
				}
				case 12: {
					yield "#99d9bb";
				}
				default:
					throw new IllegalArgumentException("Error Color Month");
				});
				data.setData(this.dataDatasetGraphic(switch (month) {
				case 1: {
					yield "01";
				}
				case 2: {
					yield "02";
				}
				case 3: {
					yield "03";
				}
				case 4: {
					yield "04";
				}
				case 5: {
					yield "05";
				}
				case 6: {
					yield "06";
				}
				case 7: {
					yield "07";
				}
				case 8: {
					yield "08";
				}
				case 9: {
					yield "09";
				}
				case 10: {
					yield "10";
				}
				case 11: {
					yield "11";
				}
				case 12: {
					yield "12";
				}
				default:
					throw new IllegalArgumentException("");
				}));
				data.setBarThickness(32);

				if (month == LocalDate.now().getMonthValue()) { 
					Map<String, Integer> borderRadiusCustom = new HashMap<>();
					borderRadiusCustom.put("topLeft", 8);
					borderRadiusCustom.put("topRight", 8);
					borderRadiusCustom.put("bottomLeft", 0);
					borderRadiusCustom.put("bottomRight", 0);
					data.setBorderRadius(borderRadiusCustom);
				} else {
					data.setBorderRadius(borderRadius);
				}
				dataset.add(data);
			}
			return dataset;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@SuppressWarnings("unused")
	private Long[] dataDatasetGraphic(String month) throws ServiceException {
		try { 
			List<GraphicPatternsDTO> data = findGraphicPatternsByMonthAndYear(month, UtilHelpers.getCurrentYear());
			return data.stream().map(GraphicPatternsDTO::getNuCount).toArray(Long[]::new);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public List<DatasetNumberEntriesPerPersonDTO> findReportNumberEntriesPerPersonDTO(String month) throws ServiceException {
		try { 
			List<ReportNumberEntriesPerPersonDTO> dataReport = repo.findReportNumberEntriesPerPersonDTO(month, UtilHelpers.getCurrentYear());
			List<DatasetNumberEntriesPerPersonDTO> dataset = dataReport.stream().map(data -> {
				DatasetNumberEntriesPerPersonDTO dataTemporal = new DatasetNumberEntriesPerPersonDTO();
				dataTemporal.setVisitor(visitorService.findByID(data.getIdVisitor().longValue()).get());
				dataTemporal.setNuCount(data.getNuCount().intValue());
				return dataTemporal;
			}).collect(Collectors.toList());
			return dataset;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

	@Override
	public Optional<StatsWidgetDTO> findStatsWidgets() throws ServiceException {
		try {  
			StatsWidgetDTO stats = new StatsWidgetDTO();
			stats.setTotalVisits(visitRepo.counterVisits());
			stats.setTotalPendingVisits(visitRepo.counterVisitsPending());
			stats.setVisitorsDetained(segmentVisitorRepo.counterVisitorsDetentions());
			stats.setVisitorsDetainedLastMonth(segmentVisitorRepo.counterVisitorsDetentionsLastMonth(UtilHelpers.getCurrentMonth()));
			stats.setTotalVisitors(visitorRepo.counterVisitors());
			stats.setTotalForeignVisitors(visitorRepo.counterForeignVisitor());
			stats.setTotalUnexpectedVisits(0);
			return Optional.of(stats);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new ServiceException(MessageConstants.ERROR_IN_SERVICE_SERVER);
		}
	}

}
