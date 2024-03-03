package model.DAO;

import model.Entity.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface BaseDAO<K, T extends Entity> {

    List<T> findAllDolls() throws DaoException;
    T findCheapToy() throws DaoException;
    T findExpensiveToy() throws DaoException;
    double findAveragePriceCountry(String country) throws DaoException;
    List<T> findAllMoldova() throws DaoException;
    List<T> findAllPriceAge(int v1, int v2, int pret) throws DaoException;
    int countToysSold() throws DaoException;
    List<T> findPopularToys() throws DaoException;
    boolean create(T t) throws DaoException;

    default void close(Statement statement){
        try{
            if(statement!=null) statement.close();
        } catch (SQLException e){throw new RuntimeException(e);}
    }

    default void close(Connection connection){
        try{
            if(connection!=null) connection.close();
        } catch (SQLException e){throw new RuntimeException(e);}
    }
}