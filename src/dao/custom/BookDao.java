package dao.custom;

import java.util.ArrayList;

import dao.SuperDao;
import dto.BookDto;
import entity.BookEntity;

public interface BookDao extends SuperDao {

    public String save(BookEntity Entity) throws Exception;

    public String update(BookEntity Entity) throws Exception;

    public String delete(String BookId) throws Exception;

    public ArrayList<BookEntity> getAll() throws Exception;

    public BookEntity get(String BookId) throws Exception;
}
