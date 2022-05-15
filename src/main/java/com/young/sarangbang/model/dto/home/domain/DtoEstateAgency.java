package com.young.sarangbang.model.dto.home.domain;

import com.young.sarangbang.model.entity.estateagency.domain.EstateAgency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date : 2022-03-10
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Setter
@Getter
@AllArgsConstructor
public class DtoEstateAgency extends EstateAgency {

    @Override
    public String toString(){
        String allField = "";
        List<String> names = Arrays.stream(this.getClass().getDeclaredFields()).map(field -> field.getName()).collect(Collectors.toList());

        int size = names.size();
        for ( int i=0; i < size; i++){
            if ( i == (size-1) ){
                allField += names.get(i);
            } else {
                allField += names.get(i) + ',';
            }
        }

        return allField;
    }

}
