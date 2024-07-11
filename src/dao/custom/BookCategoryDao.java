package dao.custom;

import java.util.ArrayList;

import dao.SuperDao;

import entity.BookCategoryEntity;

public interface BookCategoryDao extends SuperDao {

    public String Save(BookCategoryEntity BookCategory) throws Exception;

    public String Delete(String BookCategory_Id) throws Exception;

    public String Update(BookCategoryEntity BookCategory) throws Exception;

    public ArrayList<BookCategoryEntity> getAll() throws Exception;

    public BookCategoryEntity get(String BookCategory_Id) throws Exception;

}
