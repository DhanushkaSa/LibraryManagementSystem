package service.custom;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.MemberDto;
import service.SuperService;

public interface MemberService extends SuperService {

    public String save(MemberDto dto) throws ClassNotFoundException, SQLException;

    public String update(MemberDto dto) throws ClassNotFoundException, SQLException;

    public String Delete(String Member_Id) throws ClassNotFoundException, SQLException;

    public ArrayList<MemberDto> getAll() throws ClassNotFoundException, SQLException;

    public MemberDto get(String Member_Id) throws ClassNotFoundException, SQLException;

    

}
