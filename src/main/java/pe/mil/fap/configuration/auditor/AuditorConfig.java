package pe.mil.fap.configuration.auditor;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditorConfig<T> {

	@Column(updatable = false, nullable = false)
	@CreatedDate
	protected LocalDateTime feRegisteredAuditor;

	@Column(nullable = false)
	@LastModifiedDate
	protected LocalDateTime feUpdatedAuditor;

	@CreatedBy
	@Column(nullable = false, updatable = false, length = 4)
	protected T noCreatedByAuditor;

	@LastModifiedBy
	@Column(length = 4, nullable = false)
	protected T noUpdatedByAuditor;

	public AuditorConfig() {
		super();
	}

	public AuditorConfig(LocalDateTime feRegisteredAuditor, LocalDateTime feUpdatedAuditor, T noCreatedByAuditor,
			T noUpdatedByAuditor) {
		super();
		this.feRegisteredAuditor = feRegisteredAuditor;
		this.feUpdatedAuditor = feUpdatedAuditor;
		this.noCreatedByAuditor = noCreatedByAuditor;
		this.noUpdatedByAuditor = noUpdatedByAuditor;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getFeRegisteredAuditor() {
		return feRegisteredAuditor;
	}

	public void setFeRegisteredAuditor(LocalDateTime feRegisteredAuditor) {
		this.feRegisteredAuditor = feRegisteredAuditor;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime getFeUpdatedAuditor() {
		return feUpdatedAuditor;
	}

	public void setFeUpdatedAuditor(LocalDateTime feUpdatedAuditor) {
		this.feUpdatedAuditor = feUpdatedAuditor;
	}

	public T getNoCreatedByAuditor() {
		return noCreatedByAuditor;
	}

	public void setNoCreatedByAuditor(T noCreatedByAuditor) {
		this.noCreatedByAuditor = noCreatedByAuditor;
	}

	public T getNoUpdatedByAuditor() {
		return noUpdatedByAuditor;
	}

	public void setNoUpdatedByAuditor(T noUpdatedByAuditor) {
		this.noUpdatedByAuditor = noUpdatedByAuditor;
	}
}
