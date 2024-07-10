package service.custom;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoFactory;
import dao.DaoFactory.DaoType;
import dao.custom.MemberDao;
import dao.custom.MemberDaoImpl;
import dto.MemberDto;
import entity.MemberEntity;

public class MemberServiceImpl implements MemberService {

    private MemberDao dao = (MemberDao) DaoFactory.getInstance().getDaoType(DaoFactory.DaoType.MEMBERS);

    @Override
    public String save(MemberDto dto) throws ClassNotFoundException, SQLException {
        MemberEntity entity = new MemberEntity(dto.getMember_Id(), dto.getFirstName(), dto.getLastName(),
                dto.getAddress(), dto.getAge(), dto.getTelephone());
        return dao.save(entity);
    }

    @Override
    public String update(MemberDto dto) throws ClassNotFoundException, SQLException {
        MemberEntity entity = new MemberEntity(dto.getMember_Id(), dto.getFirstName(), dto.getLastName(),
                dto.getAddress(), dto.getAge(), dto.getTelephone());
        return dao.update(entity);
    }

    @Override
    public String Delete(String Member_Id) throws ClassNotFoundException, SQLException {
        return dao.Delete(Member_Id);
    }

    @Override
    public ArrayList<MemberDto> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<MemberEntity> arrayEntities = dao.getAll();
        ArrayList<MemberDto> arrayDtos = new ArrayList<>();
        for (MemberEntity data : arrayEntities) {
            MemberDto dto = new MemberDto(data.getMember_Id(), data.getFirstName(), data.getLastName(),
                    data.getAddress(), data.getAge(), data.getTelephone());
            arrayDtos.add(dto);
        }
        return arrayDtos;
    }

    @Override
    public MemberDto get(String Member_Id) throws ClassNotFoundException, SQLException {
        MemberEntity entity = dao.get(Member_Id);
        MemberDto dto = new MemberDto(entity.getMember_Id(), entity.getFirstName(), entity.getLastName(),
                entity.getAddress(), entity.getAge(), entity.getTelephone());
        return dto;
    }

}
