package dao.custom;

import java.util.ArrayList;

import dao.SuperDao;
import entity.BookReturnEntity;

public interface BookReturnDao extends SuperDao{

    public String save(BookReturnEntity entity) throws Exception;

    public ArrayList<BookReturnEntity> getAll()throws Exception;
}
