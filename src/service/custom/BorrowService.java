package service.custom;


import java.util.ArrayList;

import dto.BorrowDto;
import service.SuperService;

public interface BorrowService extends SuperService{

    public String placeBorrow(BorrowDto borrowDto)throws Exception;

    public ArrayList<BorrowDto> getAll()throws Exception;
}
