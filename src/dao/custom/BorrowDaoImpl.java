package dao.custom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.DBConnection;
import entity.BorrowEntity;

public class BorrowDaoImpl implements BorrowDao {

    @Override
    public String save(BorrowEntity entity) throws Exception {

        String sql = "INSERT INTO Borrow VALUES(?,?,?,?,?)";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, entity.getBorrow_Id());
        statement.setString(2, entity.getMember_Id());
        statement.setString(3, entity.getBook_Id());
        statement.setString(4, entity.getDueDate());
        statement.setString(5, entity.getBorrow_Date());
        int value = statement.executeUpdate();
        return value > 0 ? "Successfully Save !!" : "Save Fail !!";
    }

    @Override
    public ArrayList<BorrowEntity> getAll() throws Exception {
        ArrayList<BorrowEntity> entityArray = new ArrayList<>();
        String sql = "SELECT * FROM Borrow";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet srt = statement.executeQuery();
        while (srt.next()) {
            BorrowEntity dataEntity = new BorrowEntity(srt.getString("Borrow_Id"), srt.getString("Member_Id"), srt.getString("Book_Id"), srt.getString("DueDate"), srt.getString("Borrow_Date"));
            entityArray.add(dataEntity);
        }
        return entityArray;
    }

    @Override
    public BorrowEntity get(String BorrowId) throws Exception {
        String sql="SELECT * FROM Borrow WHERE Borrow_Id=?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1,BorrowId);
        ResultSet srt=statement.executeQuery();
        if(srt.next()){
            BorrowEntity entity=new BorrowEntity(srt.getString("Borrow_Id"), srt.getString("Member_Id"), srt.getString("Book_Id"), srt.getString("DueDate"), srt.getString("Borrow_Date"));
            return entity;
        }
        return null;
    }

}
