package pe.mil.fap.service.auditor.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import pe.mil.fap.service.auditor.inf.IAuditorService;

@Service
public class AuditorServiceImpl implements IAuditorService {

	@Override
	public Long getCurrentUser() throws Exception {
		/*
		 * Authentication authentication =
		 * SecurityContextHolder.getContext().getAuthentication();
		 * 
		 * if (authentication == null || !authentication.isAuthenticated()) { return
		 * null; }
		 * 
		 * return Optional.of(authentication.getName().toUpperCase());
		 */
		Long currentUser = Long.valueOf(LocalDateTime.now().getMinute());
		return currentUser;
	}

}
