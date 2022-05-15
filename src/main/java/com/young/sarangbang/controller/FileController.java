package com.young.sarangbang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.young.sarangbang.model.dto.home.domain.DtoFileInfo;
import com.young.sarangbang.model.dto.home.service.DtoFileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Date : 2022-05-06
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@RequestMapping(value = "/files")
@Controller
public class FileController {

    @Value("${file.path}")
    String storePath;

    @Value("${file.excelPath}")
    String excelPath;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DtoFileInfoService dtoFileInfoService;

    @RequestMapping(value = "/fileDelete", method = RequestMethod.POST)
    @ResponseBody
    public JsonNode fileDelete(HttpServletRequest request, @RequestBody String body) throws JsonProcessingException {

        JsonNode requestJson = objectMapper.readTree(body);
        HashMap<String, Object> requestMap = objectMapper.convertValue(requestJson, HashMap.class);

        /* DB 업데이트 */
        Integer fileInfoSeq = Integer.parseInt((String) requestMap.get("fileInfoSeq"));
        boolean result = dtoFileInfoService.modify(fileInfoSeq, Boolean.TRUE);

        HashMap<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("ret", result);
        String jsonStr = objectMapper.writeValueAsString(resultMap);
        return objectMapper.readTree(jsonStr);
    }

    @RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
    @ResponseBody
    public Object fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> parameterMap = Maps.newHashMap();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            parameterMap.put(name, value);
        }
        try {
            if (parameterMap.get("fileType").equals("excel")) {
                downloadByExcel(request, response, parameterMap);
            } else {
                download(request, response, parameterMap);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ResponseBody
    public JsonNode fileUpload(HttpServletRequest request) throws Exception {

        HashMap<String, Object> parameterMap = Maps.newHashMap();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            parameterMap.put(name, value);
        }

        HashMap<String, Object> resultMap = Maps.newHashMap();
        String result = "";
        try {
            String path = storePath;
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                result = "fail";
                String reason = "not exist file";
                resultMap.put("reason", reason);
            } else {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                Iterator<String> keys = multipartRequest.getFileNames();

                List<String> fileNames = Lists.newArrayList();

                while (keys.hasNext()) {
                    MultipartFile multipartFile = multipartRequest.getFile(keys.next());

                    String fileName = multipartFile.getOriginalFilename();
                    String filePath = Paths.get(path, fileName).toString();
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
                    fileOutputStream.write(multipartFile.getBytes());
                    fileOutputStream.flush();
                    fileNames.add(fileName);
                }

                /* DB 저장 */

                if (fileNames.size() == 1) {
                    DtoFileInfo dtoFileInfo = new DtoFileInfo();
                    dtoFileInfo.setDescription((String) parameterMap.get("description"));
                    dtoFileInfo.setFileName(fileNames.get(0));
                    dtoFileInfo.setRegDt(LocalDateTime.now());
                    dtoFileInfo.setDelYN(Boolean.FALSE);

                    dtoFileInfo = dtoFileInfoService.add(dtoFileInfo);
                    resultMap.put("fileInfo", dtoFileInfo);
//                } else if (fileNames.size() > 1) {
//                    List<DtoFileInfo> dtoFileInfos = Lists.newArrayList();
//
//                    for (String fileName : fileNames) {
//                        DtoFileInfo dtoFileInfo = new DtoFileInfo();
//                        dtoFileInfo.setDescription((String) parameterMap.get("description"));
//                        dtoFileInfo.setFileName(fileName);
//                        dtoFileInfo.setRegDt(LocalDateTime.now());
//                        dtoFileInfo.setDelYN(Boolean.FALSE);
//
//                        dtoFileInfos.add(dtoFileInfo);
//                    }
//
//                    resultMap.put("fileInfos", dtoFileInfos);
                }

                result = "success";
            }

            resultMap.put("result", result);

            String jsonStr;
            JsonNode jsonNode;

            jsonStr = objectMapper.writeValueAsString(resultMap);
            jsonNode = objectMapper.readTree(jsonStr);

            return jsonNode;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage(), e);
        }
    }

    public void download(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> parameterMap) throws Exception {

        String fileName = (String) parameterMap.get("fileName");
        String filePath = Paths.get(storePath, fileName).toString();
        File file = new File(filePath);

        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        String mimeType = Files.probeContentType(Paths.get(file.getAbsolutePath()));
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Length", "" + file.length());

        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());

        try {
            byte[] buffer = new byte[8192];
            int read = 0;
            while ((read = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, read);
            }
            bufferedOutputStream.flush();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (bufferedInputStream != null) bufferedInputStream.close();
            if (bufferedInputStream != null) bufferedOutputStream.close();
        }

    }

    public void downloadByExcel(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> parameterMap) throws Exception {

        String fileName = (String) parameterMap.get("fileName");
        String filePath = Paths.get(excelPath, fileName).toString();
        File file = new File(filePath);

        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        String mimeType = "application/x-msexcel";

        response.setContentType(mimeType);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Length", "" + file.length());


        bufferedInputStream = new BufferedInputStream(new FileInputStream(file));  // << excel file read inputsream 으로 변경
        bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());

        try {
            byte[] buffer = new byte[8192];
            int read = 0;

            while ((read = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, read);
            }
            bufferedOutputStream.flush();
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            if (bufferedInputStream != null) bufferedInputStream.close();
            if (bufferedInputStream != null) bufferedOutputStream.close();
        }
    }

}
