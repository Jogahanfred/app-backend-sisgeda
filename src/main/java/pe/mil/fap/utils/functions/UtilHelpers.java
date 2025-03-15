package pe.mil.fap.utils.functions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public final class UtilHelpers {

	public static final int NUMBER_OF_MONTHS = 12;

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

	public static String getCurrentYear() {
		LocalDate today = LocalDate.now();
		return String.valueOf(today.getYear());
	}


	public static String getCurrentMonth() {
	    LocalDate today = LocalDate.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
	    return today.format(formatter);
	}
	
	public static String getMonth(Integer month) {
		try {
			switch (month) {
			case 1: {
				return "Enero";
			}
			case 2: {
				return "Febrero";
			}
			case 3: {
				return "Marzo";
			}
			case 4: {
				return "Abril";
			}
			case 5: {
				return "Mayo";
			}
			case 6: {
				return "Junio";
			}
			case 7: {
				return "Julio";
			}
			case 8: {
				return "Agosto";
			}
			case 9: {
				return "Setiembre";
			}
			case 10: {
				return "Octubre";
			}
			case 11: {
				return "Noviembre";
			}
			case 12: {
				return "Diciembre";
			}
			default:
				throw new IllegalArgumentException("Error: " + month);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
