package com.young.sarangbang.controller.home;

import com.google.common.collect.Lists;
import com.young.sarangbang.controller.ExtendsController;
import com.young.sarangbang.exception.SarangbangException;
import com.young.sarangbang.model.dto.home.domain.DtoFileInfo;
import com.young.sarangbang.model.dto.home.service.DtoFileInfoService;
import com.young.sarangbang.model.dto.login.domain.DtoUser;
import com.young.sarangbang.model.dto.login.service.DtoUserService;
import com.young.sarangbang.model.entity.address.domain.Zipcode;
import com.young.sarangbang.model.entity.address.service.ZipcodeService;
import com.young.sarangbang.model.entity.stock.enums.StockType;
import com.young.sarangbang.model.dto.home.domain.DtoBangInfo;
import com.young.sarangbang.model.dto.home.service.DtoBangInfoService;
import com.young.sarangbang.model.dto.home.domain.DtoEstateAgency;
import com.young.sarangbang.model.dto.home.service.DtoEstateAgencyService;
import com.young.sarangbang.model.vo.home.domain.VoStockInfo;
import com.young.sarangbang.model.vo.home.service.VoStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Date : 2022-03-06
 * Author : zilet
 * Project : sarangbang
 * Description :
 */
@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController extends ExtendsController {

    private final DtoBangInfoService dtoBangInfoService;
    private final DtoEstateAgencyService dtoEstateAgencyService;
    private final DtoUserService dtoUserService;
    private final VoStockService voStockService;
    private final ZipcodeService zipcodeService;
    private final DtoFileInfoService dtoFileInfoService;

    /** GET */

    @RequestMapping("/")
    public RedirectView home_() {
        return new RedirectView("/home");
    }

    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest request) throws SarangbangException {

        DtoUser dtoUser = null;
        try {
            dtoUser = dtoUserService.get(request.getUserPrincipal().getName());
        } catch (Exception e) {
            throw new SarangbangException(e);
        }

        model.addAttribute("user", dtoUser);

        List<DtoBangInfo> dtoBangInfoList = dtoBangInfoService.getsAllByIsFavorites();

        if (dtoBangInfoList != null) {
            model.addAttribute("bangInfos", dtoBangInfoList);
        }

        List<DtoEstateAgency> dtoEstateAgencyList = dtoEstateAgencyService.getsAllByIsFavorites();

        if (dtoEstateAgencyList != null) {
            model.addAttribute("estateAgencies", dtoEstateAgencyList);
        }

        List<VoStockInfo> voStockInfos = voStockService.getsByFirstStockCompanyAndDayType();
        if (voStockInfos != null) {
            model.addAttribute("voStockInfos", voStockInfos);
        } else {
            log.info(" **************** voStockInfos is null ");
        }

        return getPath("/dashboard");
    }


    @GetMapping("/tpms")
    public String tpms(Model model, HttpServletRequest request) throws SarangbangException {
        return getPath("/tpms");
    }


    @GetMapping("/files/document")
    public String document(Model model, HttpServletRequest request) throws SarangbangException {
        model.addAttribute("fileInfos", dtoFileInfoService.gets());
        return getPath("/files/document");
    }

    @GetMapping("/files/businesscard")
    public String businesscard(Model model, HttpServletRequest request) throws SarangbangException {
        return getPath("/files/businesscard");
    }


    @GetMapping("/music/classic")
    public String classic(Model model, HttpServletRequest request) throws SarangbangException {
        return getPath("/music/classic");
    }

    @GetMapping("/music/pop")
    public String pop(Model model, HttpServletRequest request) throws SarangbangException {
        return getPath("/music/pop");
    }

    @GetMapping("/mobile/hybrid")
    public String hybrid(Model model, HttpServletRequest request) throws SarangbangException {
        return getPath("/mobile/hybrid");
    }
    @GetMapping("/mobile/fragment")
    public String fragment(Model model, HttpServletRequest request) throws SarangbangException {
        return getPath("/mobile/fragment");
    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public String stock(Model model, HttpServletRequest request) {
        model.addAttribute("stockCompanies", voStockService.gets());
        model.addAttribute("username", request.getUserPrincipal().getName());
        return getPath("/stock");
    }

    @RequestMapping("/bangInfo")
    public String bangInfo(Model model, HttpServletRequest request) {
        model.addAttribute("username", request.getUserPrincipal().getName());

        List<DtoBangInfo> dtoBangInfoList = dtoBangInfoService.getsAll();

        if (dtoBangInfoList != null) {
            model.addAttribute("bangInfos", dtoBangInfoList);
        }

        return getPath("/bangInfo");
    }

    @RequestMapping("/estateAgency")
    public String estateAgency(Model model, HttpServletRequest request) {
        List<DtoEstateAgency> dtoEstateAgencyList = dtoEstateAgencyService.getsAll();

        if (dtoEstateAgencyList != null) {
            model.addAttribute("estateAgencies", dtoEstateAgencyList);
        }

        return getPath("/estateAgency");
    }


    @RequestMapping("/setting")
    public String settings(Model model, HttpServletRequest request) {
        DtoUser dtoUser = dtoUserService.get(request.getUserPrincipal().getName());
        model.addAttribute("user", dtoUser);
        return getPath("/setting");
    }


    /** POST */

    @PostMapping("/bangInfo/isFavorites")
    @ResponseBody
    public Boolean bangInfo_isFavorites(@RequestBody Map<String, String> data) {
        if (data.get("bangInfoSeq") == null || data.get("bangInfoSeq").length() ==0 ) {
            return Boolean.FALSE;
        }

        if (data.get("isFavorites") == null || data.get("isFavorites").length() ==0 ) {
            return Boolean.FALSE;
        }

        try {
            dtoBangInfoService.setIsFavorites(
                    Integer.valueOf(data.get("bangInfoSeq")),
                    Boolean.valueOf(data.get("isFavorites")));
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @PostMapping("/estateAgency/isFavorites")
    @ResponseBody
    public Boolean estateAgency(@RequestBody Map<String, String> data) {
        Integer estateAgencySeq = Integer.valueOf(data.get("estateAgencySeq"));
        Boolean isFavorites = Boolean.valueOf(data.get("isFavorites"));
        try {
            dtoEstateAgencyService.setIsFavorites(estateAgencySeq, isFavorites);
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @PostMapping(value = "/stockInfo")
    @ResponseBody
    public List<VoStockInfo> stockInfo(HttpServletRequest request, @RequestBody Map<String, String> data) {

        if (data.get("stockType") == null || data.get("stockType").length() ==0) {
            data.put("stockType", "DAY");
        }

        StockType stockType = StockType.valueOf(data.get("stockType"));
        List<VoStockInfo> voStockInfos = voStockService.gets(data.get("alias"), stockType);
        if (voStockInfos == null) {
            voStockInfos = Lists.newArrayList();
        }

        return voStockInfos;
    }

    // siDo + rawDongName 으로 검색
    @PostMapping(value = "/rawDongName")
    @ResponseBody
    public List<Zipcode> getAddress(HttpServletRequest request, @RequestBody Map<String, String> data) {
        List<Zipcode> zipcodes = zipcodeService.getByRawDongName(data.get("siDoEng"), data.get("rawDongName"));
        return zipcodes;
    }

}
