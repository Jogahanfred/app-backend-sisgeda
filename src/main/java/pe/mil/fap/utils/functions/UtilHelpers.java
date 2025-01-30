package pe.mil.fap.utils.functions;

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
}
