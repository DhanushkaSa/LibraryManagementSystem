package dao.custom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.DBConnection;
import dto.BookCategoryDto;
import entity.BookCategoryEntity;

public class BookCategoryDaoImpl implements BookCategoryDao {

    @Override
    public String Save(BookCategoryEntity BookCategory) throws Exception {
        String sql = "INSERT INTO BookCategory VALUES(?,?)";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, BookCategory.getCategory_Id());
        statement.setString(2, BookCategory.getCategory_Name());
        int value = statement.executeUpdate();
        return value > 0 ? "Save Successfully !!" : "Save Fail !!";

    }

    @Override
    public String Delete(String BookCategory_Id) throws Exception {
        String sql = "DELETE FROM BookCategory WHERE Category_Id = ?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, BookCategory_Id);
        int value = statement.executeUpdate();
        return value > 0 ? "Delete Successfully !!" : "Delete Fail !!";
    }

    @Override
    public String Update(BookCategoryEntity BookCategory) throws Exception {
        String sql="Update bookcategory set Category_Name= ? WHERE Category_Id = ?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, BookCategory.getCategory_Name());
        statement.setString(2, BookCategory.getCategory_Id());
        int value = statement.executeUpdate();
        return value > 0 ? "Update Successfully !!" : "Update Fail !!";
    }

    @Override
    public ArrayList<BookCategoryEntity> getAll() throws Exception {
        String sql = "SELECT * FROM BookCategory";
        ArrayList<BookCategoryEntity> arrayEntity = new ArrayList<>();
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet srt = statement.executeQuery();
        while (srt.next()) {
            BookCategoryEntity entity = new BookCategoryEntity(srt.getString("Category_Id"),
                    srt.getString("Category_Name"));
            arrayEntity.add(entity);
        }
        return arrayEntity;
    }

    @Override
    public BookCategoryEntity get(String BookCategory_Id) throws Exception {
        String sql = "SELECT * FROM BookCategory WHERE Category_Id=?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, BookCategory_Id);
        ResultSet srt = statement.executeQuery();
        if (srt.next()) {
            BookCategoryEntity entity = new BookCategoryEntity(srt.getString("Category_Id"),
                    srt.getString("Category_Name"));
            return entity;
        }
        return null;
    }

}
