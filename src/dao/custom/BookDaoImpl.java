package dao.custom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.DBConnection;
import entity.BookEntity;

public class BookDaoImpl implements BookDao {

    @Override
    public String save(BookEntity Entity) throws Exception {
        String sql = "INSERT INTO book VALUES(?,?,?,?,?,?)";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, Entity.getBook_Id());
        statement.setString(2, Entity.getBook_Name());
        statement.setString(3, Entity.getLanguage());
        statement.setString(4, Entity.getAuthor());
        statement.setInt(5, Entity.getBook_Count());
        statement.setString(6, Entity.getCategory_Id());
        int value = statement.executeUpdate();
        return value > 0 ? "Save Successfully !!" : "Save Fail !!";
    }

    @Override
    public String update(BookEntity Entity) throws Exception {
        String sql="UPDATE Book SET Book_Name=?,Language=?,Author=?,Book_Count=?,Category_Id=? WHERE Book_Id=?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, Entity.getBook_Name());
        statement.setString(2, Entity.getLanguage());
        statement.setString(3, Entity.getAuthor());
        statement.setInt(4, Entity.getBook_Count());
        statement.setString(5, Entity.getCategory_Id());
        statement.setString(6, Entity.getBook_Id());
        int value = statement.executeUpdate();
        return value > 0 ? "Update Successfully !!" : "Update Fail !!";
    }

    @Override
    public String delete(String BookId) throws Exception {
       String sql="DELETE FROM Book WHERE Book_Id=?";
       PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
       statement.setString(1, BookId);
       int value = statement.executeUpdate();
        return value > 0 ? "Delete Successfully !!" : "Delete Fail !!";
    }

    @Override
    public ArrayList<BookEntity> getAll() throws Exception {
        String sql = "SELECT * FROM Book";
        ArrayList<BookEntity> EntityArray = new ArrayList<>();
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet srt = statement.executeQuery();
        while (srt.next()) {
            BookEntity entity = new BookEntity(srt.getString("Book_Id"), srt.getString("Book_Name"),
                    srt.getString("Language"), srt.getString("Author"), srt.getInt("Book_Count"),
                    srt.getString("Category_Id"));
            EntityArray.add(entity);
        }
        return EntityArray;
    }

    @Override
    public BookEntity get(String BookId) throws Exception {
        String sql = "SELECT * FROM Book WHERE Book_Id=?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, BookId);
        ResultSet srt = statement.executeQuery();
        if (srt.next()) {
            BookEntity entity = new BookEntity(srt.getString("Book_Id"), srt.getString("Book_Name"),
                    srt.getString("Language"), srt.getString("Author"), srt.getInt("Book_Count"),
                    srt.getString("Category_Id"));
            return entity;
        }
        return null;
    }

}
