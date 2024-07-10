package dao;

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
        MEMBERS, BOOK_CATEGORY, BOOKS;
    }

    public SuperDao getDaoType(DaoType type) {
        switch (type) {
            case MEMBERS:
                return new MemberDaoImpl();

            case BOOK_CATEGORY:
                return null;

            case BOOKS:
                return null;

            default:
                return null;
        }
    }
}
