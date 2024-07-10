package dao.custom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.DBConnection;
import entity.MemberEntity;

public class MemberDaoImpl implements MemberDao {

    @Override
    public String save(MemberEntity entity) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Members VALUES(?,?,?,?,?,?)";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, entity.getMember_Id());
        statement.setString(2, entity.getFirstName());
        statement.setString(3, entity.getLastName());
        statement.setString(4, entity.getAddress());
        statement.setInt(5, entity.getAge());
        statement.setString(6, entity.getTelephone());

        int value = statement.executeUpdate();
        return value > 0 ? "Register Successfully !!" : "Register Fail !!";
    }

    @Override
    public String update(MemberEntity entity) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Members SET FirstName=?,LastName=?,Address=?,Age=?,Telephone=? WHERE Member_Id = ?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        statement.setString(3, entity.getAddress());
        statement.setInt(4, entity.getAge());
        statement.setString(5, entity.getTelephone());
        statement.setString(6, entity.getMember_Id());
        int value = statement.executeUpdate();
        return value > 0 ? "Update Successfully !!" : "Update Fail !!";

    }

    @Override
    public String Delete(String Member_Id) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Members  WHERE Member_Id = ?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, Member_Id);
        int value = statement.executeUpdate();
        return value > 0 ? "Delete Successfully !!" : "Delete Fail !!";

    }

    @Override
    public ArrayList<MemberEntity> getAll() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Members";
        ArrayList<MemberEntity> array = new ArrayList<>();
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet srt = statement.executeQuery();
        while (srt.next()) {
            MemberEntity entity = new MemberEntity(srt.getString("Member_Id"), srt.getString("FirstName"),
                    srt.getString("LastName"), srt.getString("Address"), srt.getInt("Age"), srt.getString("Telephone"));
            array.add(entity);
        }
        return array;
    }

    @Override
    public MemberEntity get(String Member_Id) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Members WHERE Member_Id = ?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, Member_Id);
        ResultSet srt = statement.executeQuery();
        if (srt.next()) {
            MemberEntity entity = new MemberEntity(srt.getString("Member_Id"), srt.getString("FirstName"),
                    srt.getString("LastName"), srt.getString("Address"), srt.getInt("Age"), srt.getString("Telephone"));
            return entity;
        }
        return null;
    }

}
