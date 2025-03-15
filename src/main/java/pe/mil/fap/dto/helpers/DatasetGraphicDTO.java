package pe.mil.fap.dto.helpers;

import java.io.Serializable;
import java.util.Map; 

public class DatasetGraphicDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String type;
	private String label;
	private String backgroundColor;
	private Long[] data;
	private Integer barThickness;
    private Map<String, Integer> borderRadius; 

	public DatasetGraphicDTO() {
		super();
	}

	public DatasetGraphicDTO(String type, String label, String backgroundColor, Long[] data,
			Integer barThickness, Map<String, Integer> borderRadius) {
		super();
		this.type = type;
		this.label = label;
		this.backgroundColor = backgroundColor;
		this.data = data;
		this.barThickness = barThickness;
		this.borderRadius = borderRadius;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Long[] getData() {
		return data;
	}

	public void setData(Long[] data) {
		this.data = data;
	}

	public Integer getBarThickness() {
		return barThickness;
	}

	public void setBarThickness(Integer barThickness) {
		this.barThickness = barThickness;
	}

	public Map<String, Integer> getBorderRadius() {
		return borderRadius;
	}

	public void setBorderRadius(Map<String, Integer> borderRadius) {
		this.borderRadius = borderRadius;
	}

}
