package com.young.sarangbang.model.entity.stock.enums;

import com.young.sarangbang.model.entity.stock.handler.StockTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.ibatis.type.MappedTypes;

/**
 * Date : 2022-03-17
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Getter
@AllArgsConstructor
public enum StockType {
    HOUR("시간"),

    DAY("일"),

    MONTH("월"),

    YEAR("년");

    private String value;

    @MappedTypes(StockType.class)
    public static class TypeHandler extends StockTypeHandler<StockType> {
        public TypeHandler() {
            super(StockType.class);
        }
    }

}
