package pe.mil.fap.entity;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import pe.mil.fap.configuration.auditor.AuditorConfig;
import pe.mil.fap.utils.enums.DayOfWeekEnum;

@Table(name = "TBL_WORKING_HOURS")
@Entity(name = "WorkingHoursEntity")
public class WorkingHoursEntity extends AuditorConfig<Long> {

	@Id
	@Column(name = "ID_WORKING_HOURS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long idWorkingHours;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idSchedule", nullable = false, foreignKey = @ForeignKey(name = "FK_SCHEDULE_WORKING"))
	private ScheduleEntity schedule;

	@NotNull(message = "El día no debe ser vacio")
	@Column(name = "NO_DAY", nullable = false)
	@Enumerated(EnumType.STRING)
	private DayOfWeekEnum noDay;

	@NotNull(message = "El horario de ingreso no debe ser vacio")
	@Column(name = "FE_START_WORK", nullable = false)
	private LocalTime feStartWork;

	@NotNull(message = "El horario de salida no debe ser vacio")
	@Column(name = "FE_END_WORK", nullable = true)
	private LocalTime feEndWork;

	public WorkingHoursEntity() {
		super();
	}

	public WorkingHoursEntity(Long idWorkingHours, ScheduleEntity schedule,
			@NotNull(message = "El día no debe ser vacio") DayOfWeekEnum noDay,
			@NotNull(message = "El horario de ingreso no debe ser vacio") LocalTime feStartWork,
			@NotNull(message = "El horario de salida no debe ser vacio") LocalTime feEndWork) {
		super();
		this.idWorkingHours = idWorkingHours;
		this.schedule = schedule;
		this.noDay = noDay;
		this.feStartWork = feStartWork;
		this.feEndWork = feEndWork;
	}

	public boolean validSchedule() {
		return !feEndWork.isBefore(feStartWork);
	}

	public Long getIdWorkingHours() {
		return idWorkingHours;
	}

	public void setIdWorkingHours(Long idWorkingHours) {
		this.idWorkingHours = idWorkingHours;
	}

	public DayOfWeekEnum getNoDay() {
		return noDay;
	}

	public void setNoDay(DayOfWeekEnum noDay) {
		this.noDay = noDay;
	}

	public LocalTime getFeStartWork() {
		return feStartWork;
	}

	public void setFeStartWork(LocalTime feStartWork) {
		this.feStartWork = feStartWork;
	}

	public LocalTime getFeEndWork() {
		return feEndWork;
	}

	public void setFeEndWork(LocalTime feEndWork) {
		this.feEndWork = feEndWork;
	}

	public ScheduleEntity getSchedule() {
		return schedule;
	}

	public void setSchedule(ScheduleEntity schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return "WorkingHoursEntity [idWorkingHours=" + idWorkingHours + ", schedule=" + schedule + ", noDay=" + noDay
				+ ", feStartWork=" + feStartWork + ", feEndWork=" + feEndWork + "]";
	}

}
