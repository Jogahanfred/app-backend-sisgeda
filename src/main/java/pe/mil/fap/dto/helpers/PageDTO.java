package pe.mil.fap.dto.helpers;
 
import java.util.List;

public class PageDTO<T> {

	private List<T> content;
	private Integer size;
	private Integer numberOfElements;
	private Integer totalPages;
	private Long totalElements;

	public PageDTO() {
		super();
	}

	public PageDTO(List<T> content, Integer size, Integer numberOfElements, Integer totalPages, Long totalElements) {
		super();
		this.content = content;
		this.size = size;
		this.numberOfElements = numberOfElements;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(Integer numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

}
