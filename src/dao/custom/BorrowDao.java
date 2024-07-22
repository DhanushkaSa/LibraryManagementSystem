package dao.custom;

import java.util.ArrayList;

import dao.SuperDao;
import entity.BorrowEntity;

public interface BorrowDao extends SuperDao {

    public String save(BorrowEntity entity) throws Exception;

    public ArrayList<BorrowEntity> getAll() throws Exception;

    public BorrowEntity get(String BorrowId) throws Exception;

    public String delete(String BorrowId)throws Exception;
}
