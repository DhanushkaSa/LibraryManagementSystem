package dao;

import java.sql.SQLException;

import dao.custom.BookCategoryDaoImpl;
import dao.custom.BookDaoImpl;
import dao.custom.BookReturnDaoImpl;
import dao.custom.BorrowDaoImpl;
import dao.custom.MemberDaoImpl;

public class DaoFactory {

    private static DaoFactory daoFactory;

    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public enum DaoType {
        MEMBERS, BOOK_CATEGORY, BOOKS,BORROW,BOOK_RETURN;
    }

    public SuperDao getDaoType(DaoType type) {
        switch (type) {
            case MEMBERS:
                return new MemberDaoImpl();

            case BOOK_CATEGORY:
                return new BookCategoryDaoImpl();

            case BOOKS:
                return new BookDaoImpl();

            case BORROW:
                 return new BorrowDaoImpl();

            case BOOK_RETURN:
                 return new BookReturnDaoImpl();

            default:
                return null;
        }
    }
}
