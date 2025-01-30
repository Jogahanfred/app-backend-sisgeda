package pe.mil.fap.configuration.auditor;

import java.util.Optional;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import pe.mil.fap.service.auditor.inf.IAuditorService;

public class AuditorAwareImpl implements AuditorAware<Long> {

	@Autowired
	private IAuditorService auditorService;
	
	@Override
	public Optional<Long> getCurrentAuditor() {
		try {
			return Optional.of(auditorService.getCurrentUser());
		} catch (Exception e) {
			throw new NotImplementedException("Error: Current Auditor");
		} 
	}

}
