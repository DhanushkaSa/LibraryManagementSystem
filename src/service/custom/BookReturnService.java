package service.custom;

import dto.BookReturnDto;
import service.SuperService;

public interface BookReturnService extends SuperService{

    public String save(BookReturnDto dto)throws Exception;
}
