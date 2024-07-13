package service.custom;

import java.util.ArrayList;

import dto.BookDto;
import service.SuperService;

public interface BookService extends SuperService {

    public String save(BookDto dto) throws Exception;

    public String update(BookDto dto) throws Exception;

    public String delete(String BookId) throws Exception;

    public ArrayList<BookDto> getAll() throws Exception;

    public BookDto get(String BookId) throws Exception;
}
