package service.custom;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.custom.BookCategoryDao;
import dto.BookCategoryDto;
import entity.BookCategoryEntity;

public class BookCategoryServiceImpl implements BookCategoryService {

    private BookCategoryDao dao = (BookCategoryDao) DaoFactory.getInstance()
            .getDaoType(DaoFactory.DaoType.BOOK_CATEGORY);

    @Override
    public String Save(BookCategoryDto BookCategory) throws Exception {
        BookCategoryEntity entity = new BookCategoryEntity(BookCategory.getCategory_Id(),
                BookCategory.getCategory_Name());
        String resp = dao.Save(entity);
        return resp;
    }

    @Override
    public String Delete(String BookCategory_Id) throws Exception {
        return dao.Delete(BookCategory_Id);
    }

    @Override
    public String Update(BookCategoryDto BookCategory) throws Exception {
        BookCategoryEntity entity = new BookCategoryEntity(BookCategory.getCategory_Id(),
                BookCategory.getCategory_Name());
        return dao.Update(entity);

    }

    @Override
    public ArrayList<BookCategoryDto> getAll() throws Exception {
        ArrayList<BookCategoryDto> arrayDto = new ArrayList<>();
        ArrayList<BookCategoryEntity> arrayEntity = dao.getAll();
        for (BookCategoryEntity data : arrayEntity) {
            BookCategoryDto dto = new BookCategoryDto(data.getCategory_Id(), data.getCategory_Name());
            arrayDto.add(dto);
        }
        return arrayDto;
    }

    @Override
    public BookCategoryDto get(String BookCategory_Id) throws Exception {
        BookCategoryEntity entity = dao.get(BookCategory_Id);
        BookCategoryDto dto = new BookCategoryDto(entity.getCategory_Id(), entity.getCategory_Name());
        return dto;
    }

}
