package com.young.sarangbang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.young.sarangbang.encrypt.YoungEncoder;
import com.young.sarangbang.model.entity.banginfo.domain.BangInfo;
import com.young.sarangbang.model.dto.home.domain.DtoBangInfo;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Date : 2022-03-12
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
public class simpleTest {

    @Test
    public void testObjectMapper() {

        /* ObjectMapper Test */

        ObjectMapper objectMapper = new ObjectMapper();

        // 단일 객체 형식 변환
        BangInfo bangInfo = new BangInfo();
        bangInfo.setBangInfoSeq(1);
        bangInfo.setAddress("Address ... ");

        DtoBangInfo dtoBangInfo = (DtoBangInfo) objectMapper.convertValue(bangInfo, DtoBangInfo.class);
        System.out.println("===============================");
        System.out.println("address = " + dtoBangInfo.getAddress());

        // collect 객체 형식 변환 ( List 변환 할때 new TypeReference 를 이용해야 정상 인식 가능함 )
        BangInfo bangInfo2 = new BangInfo();
        bangInfo2.setBangInfoSeq(2);
        bangInfo2.setAddress("Address2 ... ");

        List<BangInfo> bangInfoList = Lists.newArrayList();
        bangInfoList.add(bangInfo);
        bangInfoList.add(bangInfo2);

        List<DtoBangInfo> bangInfos = objectMapper.convertValue(bangInfoList, new TypeReference<List<DtoBangInfo>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        System.out.println("=================================");
        System.out.println("bangInfos size = " + bangInfos.size());

        bangInfos.stream().forEach(b -> {
            System.out.println(b.getBangInfoSeq());
            System.out.println(b.getAddress());
        });
    }

    @Test
    public void testEncrypt() {

        String key = "qwer1234";

        String encryptedKey = YoungEncoder.encrypt(key);
        System.out.println("encrypt key = " + encryptedKey);

        key = "qwer";
        encryptedKey = YoungEncoder.encrypt(key);
        System.out.println("encrypt key = " + encryptedKey);

//        key = "kvYs/Q7sYJ0DYDWar2c0lA==";
        key = "pk8Il1/x+DqaPOdQMsbZbA==";
        String value = YoungEncoder.decrypt("KNET_ENCRYPT_KEY", "KNET_ENCRYPT_IV", key);
        System.out.println("decrypt value = " + value);

    }

    @Test
    public void testStream() {
        // List -> Map 변환
        List<String> temp1 = Lists.newArrayList();

        temp1.add("k");
        temp1.add("w");
        temp1.add("a");

        Map<String, Object> map = temp1.stream().collect(Collectors.toMap(Function.identity(), String::new));
        System.out.println(map.toString());

    }

    public void printForSample() throws RuntimeException {
        throw new RuntimeException("test Exception ...");
    }

    public void log() throws RuntimeException {
        printForSample();
    }

    @Test
    public void textException() {
        log();
    }

    @Test
    public void testConvertFromMapToJonNode(){


        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("a", "va");
        map1.put("b", "vb");

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("c", "vc");
        map2.put("d", "vd");

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(list);
            System.out.println("object to json : " + jsonStr);

            JsonNode json = objectMapper.readTree(jsonStr);
            System.out.println("a = " + json.path("map1").path("a").asText());
            System.out.println("b = " + json.path("map1").path("b").asText());
            System.out.println("c = " + json.path("map2").path("c").asText());
            System.out.println("d = " + json.path("map2").path("d").asText());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
