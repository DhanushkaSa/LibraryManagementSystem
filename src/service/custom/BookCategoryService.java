package service.custom;

import java.util.ArrayList;

import dto.BookCategoryDto;
import service.SuperService;

public interface BookCategoryService extends SuperService {

    public String Save(BookCategoryDto BookCategory) throws Exception;

    public String Delete(String BookCategory_Id) throws Exception;

    public String Update(BookCategoryDto BookCategory) throws Exception;

    public ArrayList<BookCategoryDto> getAll() throws Exception;

    public BookCategoryDto get(String BookCategory_Id) throws Exception;

}
