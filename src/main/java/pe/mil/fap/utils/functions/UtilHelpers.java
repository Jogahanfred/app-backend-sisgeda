package pe.mil.fap.utils.functions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public final class UtilHelpers {
	
	public static Pageable pageRequest(int start, int length) {
		try {
			Pageable pageable = PageRequest.of(start / length, length);
			return pageable;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getCurrentDateFormatted() { 
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return today.format(formatter);
    }
}
