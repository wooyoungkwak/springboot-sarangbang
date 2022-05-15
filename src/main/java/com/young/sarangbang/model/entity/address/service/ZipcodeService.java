package com.young.sarangbang.model.entity.address.service;

import com.young.sarangbang.model.entity.address.domain.Zipcode;

import java.util.List;

/**
 * Date : 2022-03-27
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public interface ZipcodeService {

    public List<Zipcode> getByZipcode(String siDoEng, String zipcode);

    public List<Zipcode> getByRawDongName(String siDoEng, String rawDongName);

    public List<Zipcode> getByRoadNameHan(String siDoEng, String roadNameHan);

}
