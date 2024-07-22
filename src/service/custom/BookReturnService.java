package service.custom;

import java.util.ArrayList;

import dto.BookReturnDto;
import service.SuperService;

public interface BookReturnService extends SuperService{

    public String save(BookReturnDto dto,String BorrowId)throws Exception;

    public ArrayList<BookReturnDto> getAll()throws Exception;
}
