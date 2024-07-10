package dao.custom;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.SuperDao;
import dto.MemberDto;
import entity.MemberEntity;

public interface MemberDao extends SuperDao {

    public String save(MemberEntity entity) throws ClassNotFoundException, SQLException;

    public String update(MemberEntity entity)throws ClassNotFoundException, SQLException;

    public String Delete(String Member_Id)throws ClassNotFoundException, SQLException;

    public ArrayList<MemberEntity> getAll() throws ClassNotFoundException, SQLException;

    public MemberEntity get(String Member_Id)throws ClassNotFoundException, SQLException;

   

}
