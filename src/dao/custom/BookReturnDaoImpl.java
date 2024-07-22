package dao.custom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.DBConnection;
import entity.BookReturnEntity;

public class BookReturnDaoImpl implements BookReturnDao {

    @Override
    public String save(BookReturnEntity entity) throws Exception {

        String sql = "INSERT INTO bookReturn VALUES (?,?,?)";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, entity.getReturn_Id());
        statement.setString(2, entity.getReturn_Date());
        statement.setDouble(3, entity.getFine());
       

        int value = statement.executeUpdate();
        return value > 0 ? "Save Successfully !!!" : "Save Fail !!!";

    }

    @Override
    public ArrayList<BookReturnEntity> getAll() throws Exception {

        ArrayList<BookReturnEntity> array = new ArrayList<>();
        String sql = "SELECT * FROM bookReturn";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet srt = statement.executeQuery();
        while (srt.next()) {
            BookReturnEntity entity = new BookReturnEntity(srt.getString(1), srt.getString(2), srt.getDouble(3));
            array.add(entity);
        }
        return array;
    }

}
