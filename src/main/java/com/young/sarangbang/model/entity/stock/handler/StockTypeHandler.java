package com.young.sarangbang.model.entity.stock.handler;

import com.young.sarangbang.model.entity.stock.enums.StockType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Date : 2022-04-25
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public class StockTypeHandler<E extends Enum<E>> implements TypeHandler<StockType> {
    private Class<E> type;

    public StockTypeHandler(Class<E> type){
        this.type = type;
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, StockType stockType, JdbcType jdbcType) throws SQLException {
        ps.setString(i, stockType.getValue());
    }

    @Override
    public StockType getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return getStockType(value);
    }

    @Override
    public StockType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getStockType(rs.getString(columnIndex));
    }

    @Override
    public StockType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getStockType(cs.getString(columnIndex));
    }

    private StockType getStockType(String value) throws TypeException{
        try {
            StockType[] enumConstants = (StockType[]) type.getEnumConstants();
            for ( StockType stockType: enumConstants) {
                if (stockType.getValue() == value) {
                    return stockType;
                }
            }
            return null;
        } catch (Exception e) {
            throw new TypeException("Can't make enum object '" + type + "'", e);
        }
    }
}
