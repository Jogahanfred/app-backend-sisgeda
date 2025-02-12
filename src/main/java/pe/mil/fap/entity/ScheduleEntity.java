package pe.mil.fap.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import pe.mil.fap.configuration.auditor.AuditorConfig;
import pe.mil.fap.utils.enums.DayOfWeekEnum;

@Table(name = "TBL_SCHEDULE")
@Entity(name = "ScheduleEntity")
public class ScheduleEntity extends AuditorConfig<Long> {

	@Id
	@Column(name = "ID_SCHEDULE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idSchedule;

	@NotEmpty(message = "El nombre no debe ser vacio")
	@Size(min = 2, max = 50, message = "Nombre: mínimo {min} - máximo {max}")
	@Column(name = "NO_NAME", nullable = false, length = 50)
	private String noName;

	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<WorkingHoursEntity> workingHours = new ArrayList<>();

	public ScheduleEntity() {
		super();
	}

	public ScheduleEntity(Long idSchedule,
			@NotEmpty(message = "El nombre no debe ser vacio") @Size(min = 2, max = 50, message = "Nombre: mínimo {min} - máximo {max}") String noName,
			List<WorkingHoursEntity> workingHours) {
		super();
		this.idSchedule = idSchedule;
		this.noName = noName;
		this.workingHours = workingHours;
	}

	public boolean isNameDayDuplicate() {
		Set<DayOfWeekEnum> uniqueNames = new HashSet<>(); 
		for (WorkingHoursEntity workingHour : workingHours) {
			if (!uniqueNames.add(workingHour.getNoDay())) { 
				return true; 
			}
		}
		return false;
	}

	public Long getIdSchedule() {
		return idSchedule;
	}

	public void setIdSchedule(Long idSchedule) {
		this.idSchedule = idSchedule;
	}

	public String getNoName() {
		return noName;
	}

	public void setNoName(String noName) {
		this.noName = noName;
	}

	public List<WorkingHoursEntity> getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(List<WorkingHoursEntity> workingHours) {
		this.workingHours = workingHours;
	}

}
