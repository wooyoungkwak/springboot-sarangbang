package com.young.sarangbang.jpa;

import com.young.sarangbang.SarangbangApplicationTests;
import com.young.sarangbang.model.dto.home.domain.DtoEstateAgency;
import com.young.sarangbang.model.entity.address.domain.Zipcode;
import com.young.sarangbang.model.entity.address.enums.ZipcodeHan;
import com.young.sarangbang.model.entity.address.service.ZipcodeService;
import com.young.sarangbang.model.entity.banginfo.domain.BangInfo;
import com.young.sarangbang.model.entity.banginfo.service.BangInfoService;
import com.young.sarangbang.model.entity.estateagency.domain.EstateAgency;
import com.young.sarangbang.model.entity.estateagency.service.EstateAgencyService;
import com.young.sarangbang.model.entity.login.domain.User;
import com.young.sarangbang.model.entity.login.service.UserService;
import com.young.sarangbang.model.dto.home.domain.DtoBangInfo;
import com.young.sarangbang.model.dto.home.service.DtoBangInfoService;
import com.young.sarangbang.model.entity.stock.domain.StockInfo;
import com.young.sarangbang.model.mapper.StockMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.jxls.common.Context;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Date : 2022-03-12
 * Author : zilet
 * Project : sarangbang
 * Description :
 */

@RunWith(SpringRunner.class)
@ActiveProfiles(value = "debug")
@SpringBootTest(classes = SarangbangApplicationTests.class)
public class userTest {

    @Autowired
    UserService userService;

    @Autowired
    BangInfoService bangInfoService;

    @Autowired
    DtoBangInfoService dtoBangInfoService;

    @Autowired
    ZipcodeService zipcodeService;

    @Autowired
    StockMapper stockMapper;

    @Value("${file.path}")
    String filePath;

    @Value("${file.refPath}")
    String refPath;

    @Value("${file.excelPath}")
    String excelPath;

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    public void testQueryForUser(){
        User user = userService.get(2);

        assertEquals(2, user.getUserSeq());
        assertEquals("young", user.getUsername());
        assertEquals("qwer1234", user.getPassword());
        assertEquals("kwak", user.getFirstName());
        assertEquals("wooyoung", user.getLastName());

    }

    @Test
    public void testQueryForBangInfo(){
        try {
            BangInfo bangInfo = bangInfoService.get(Integer.valueOf(1));

            assertEquals(1, bangInfo.getBangInfoSeq());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDtoForVoBangInfo() {
        try{
            DtoBangInfo dtoBangInfo = dtoBangInfoService.get(Integer.valueOf(1));

            assertEquals(1, dtoBangInfo.getBangInfoSeq());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProcedure() {
        List<Zipcode> zipcodes = zipcodeService.getByZipcode(ZipcodeHan.Daegu.toString(), "41000");
        assertEquals("팔공산로", zipcodes.get(0).getRoadNameHan());
    }

    @Test
    public void testMybatis() {
        List<StockInfo> stockInfoList = stockMapper.getsStockInfos();
        assertNotNull(stockInfoList);

        if (stockInfoList != null ) {
            for(StockInfo stockInfo : stockInfoList ) {
                System.out.println("stockInfo.getStockInfoSeq = " + stockInfo.getStockInfoSeq());
                assertNotNull(stockInfo.getStockInfoSeq());
            }
        }
    }

    @Test
    public void testJxlsExcel() throws Exception {
//        commonJxls();
//        gridJxls();

        DtoEstateAgency estateAgency = new DtoEstateAgency();
    }

    public void commonJxls() throws Exception {
        List<DtoEstateAgency> estateAgencies = Lists.newArrayList();

        for( int i=0; i<10; i++){
            DtoEstateAgency estateAgency = new DtoEstateAgency();
            estateAgency.setEstateAgencySeq(i);
            estateAgency.setAddressSeq(i);
            estateAgency.setAgencyName("agencyName" + i);
            estateAgency.setAgencyPhone("agencyPhone" + i);
            estateAgency.setName("Name" + i);
            estateAgency.setPhone("Phone" + i);
            estateAgency.setAddress("Address" + i);
            estateAgency.setIsFavorites(Boolean.TRUE);

            estateAgencies.add(estateAgency);
        }

        String name = "sample.xlsx";
        Resource resource = resourceLoader.getResource(refPath + name);
        InputStream inputStream = resource.getInputStream();

        String fileName = Paths.get(filePath, name).toString();
        File file = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Context context = new Context();
        context.putVar("estateAgencies", estateAgencies);
        JxlsHelper.getInstance().processTemplate(inputStream, fileOutputStream, context);
    }

    public void gridJxls() throws Exception{
        List<DtoEstateAgency> estateAgencies = Lists.newArrayList();

        for( int i=0; i<10; i++){
            DtoEstateAgency estateAgency = new DtoEstateAgency();
            estateAgency.setEstateAgencySeq(i);
            estateAgency.setAddressSeq(i);
            estateAgency.setAgencyName("agencyName" + i);
            estateAgency.setAgencyPhone("agencyPhone" + i);
            estateAgency.setName("Name" + i);
            estateAgency.setPhone("Phone" + i);
            estateAgency.setAddress("Address" + i);
            estateAgency.setIsFavorites(Boolean.TRUE);

            estateAgencies.add(estateAgency);
        }

        List<String> headers = Lists.newArrayList();
        headers.add("순번");
        headers.add("주소순번");
        headers.add("부동산주소");
        headers.add("부동산전화");
        headers.add("부동산이름");
        headers.add("중계자이름");
        headers.add("중계자번호");
        headers.add("북마크여부");

        String estateAgencyProperties = "estateAgencySeq,addressSeq,address,phone,name,agencyName,agencyPhone";
//      estateAgencyProperties = "estateAgencySeq,addressSeq,address,phone,name,agencyName,agencyPhone,isFavorites";
//      estateAgencyProperties = new DtoEstateAgency().toString();

                String name = "sample2.xlsx";
        Resource resource = resourceLoader.getResource(refPath + name);
        InputStream inputStream = resource.getInputStream();

        String fileName = Paths.get(filePath, name).toString();
        File file = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        Context context = new Context();
        context.putVar("headers", headers);
        context.putVar("data", estateAgencies);

        JxlsHelper jxlsHelper = JxlsHelper.getInstance().processGridTemplate(inputStream, fileOutputStream, context, estateAgencyProperties);
    }

}
