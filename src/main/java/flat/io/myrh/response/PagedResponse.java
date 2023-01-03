package flat.io.myrh.response;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public class PagedResponse extends Response{

    private List data;
    private HashMap pagination = new HashMap<>();
    public PagedResponse(String message, Integer status, Page page) {
        super(message, status);
        this.data = page.getContent();
        setPagination(page);
    }

    private void setPagination(Page page) {
        this.pagination.put("empty",page.isEmpty());
        this.pagination.put("first",page.isFirst());
        this.pagination.put("last",page.isLast());
        this.pagination.put("number",page.getNumber());
        this.pagination.put("numberOfElements",page.getNumberOfElements());
        this.pagination.put("totalElements",page.getTotalElements());
        this.pagination.put("totalPages",page.getTotalPages());
        this.pagination.put("pageable",page.getPageable());
        this.pagination.put("size",page.getSize());
        this.pagination.put("sort",page.getSort());
    }

    public PagedResponse(String message, Page page) {
        super(message);
        this.data = page.getContent();
        setPagination(page);
    }

    public PagedResponse(Page page) {
        this.data = page.getContent();
        setPagination(page);
    }

    public PagedResponse() {
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public HashMap getPagination() {
        return pagination;
    }
}
