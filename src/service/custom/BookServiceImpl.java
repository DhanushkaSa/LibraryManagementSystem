package service.custom;

import java.time.DayOfWeek;
import java.util.ArrayList;

import dao.DaoFactory;
import dao.custom.BookDao;
import dto.BookDto;
import entity.BookEntity;

public class BookServiceImpl implements BookService {

    private BookDao dao = (BookDao) DaoFactory.getInstance().getDaoType(DaoFactory.DaoType.BOOKS);

    @Override
    public String save(BookDto dto) throws Exception {
        BookEntity Entity = new BookEntity(dto.getBook_Id(), dto.getBook_Name(), dto.getLanguage(), dto.getAuthor(),
                dto.getBook_Count(), dto.getCategory_Id());
        return dao.save(Entity);
    }

    @Override
    public String update(BookDto dto) throws Exception {
        BookEntity Entity=new BookEntity(dto.getBook_Id(), dto.getBook_Name(), dto.getLanguage(), dto.getAuthor(), dto.getBook_Count(), dto.getCategory_Id());
        return dao.update(Entity);
    }

    @Override
    public String delete(String BookId) throws Exception {
        return dao.delete(BookId);
    }

    @Override
    public ArrayList<BookDto> getAll() throws Exception {
        ArrayList<BookEntity> EntityArray = dao.getAll();
        ArrayList<BookDto> DtoArray = new ArrayList<>();
        for (BookEntity data : EntityArray) {
            BookDto dto = new BookDto(data.getBook_Id(), data.getBook_Name(), data.getLanguage(), data.getAuthor(),
                    data.getBook_Count(), data.getCategory_Id());
            DtoArray.add(dto);
        }
        return DtoArray;
    }

    @Override
    public BookDto get(String BookId) throws Exception {
        BookEntity entity = dao.get(BookId);
        BookDto dto = new BookDto(entity.getBook_Id(), entity.getBook_Name(), entity.getLanguage(), entity.getAuthor(),
                entity.getBook_Count(), entity.getCategory_Id());
        return dto;
    }

}
