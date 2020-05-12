package com.example.startsstock.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.startsstock.Service.Admin;
import com.example.startsstock.Service.IStock_priceService;
import com.example.startsstock.entity.AdminVO;
import com.example.startsstock.entity.ImportSummary;
import com.example.startsstock.entity.Stock_price;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/company")
public class AdminController {
	
	@Autowired
	private Admin admin;
    @Autowired
    private IStock_priceService iStock_priceService;

	private int code;
    private String msg;
	
	@CrossOrigin
    @RequestMapping(value="/add", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addCompany(@RequestBody AdminVO admin) {
	  Map<String, Object> map = new HashMap<>(2);
	  AdminVO companyInfo = this.admin.getCompanyDetails(admin.getCompanyname());
      if (companyInfo == null) {
    	  admin.setId(admin.getId());
    	  admin.setCompanyname(admin.getCompanyname());
    	  admin.setTurnover(admin.getTurnover());
    	  admin.setCeo(admin.getCeo());
    	  admin.setBod(admin.getBod());
    	  admin.setStockexchange(admin.getStockexchange());
    	  admin.setSector(admin.getSector());
    	  admin.setBrief(admin.getBrief());
    	  admin.setStockcode(admin.getStockcode());    	 
    	  this.admin.save(admin);
    	  map.put("userinfo", companyInfo);
      }
      this.code = 200;
      this.msg = "ok";
      map.put("code", this.code);
      map.put("msg", this.msg);
      return map;
    }
	
	@CrossOrigin
    @RequestMapping(value="/get/{id}", method=RequestMethod.GET)
    @ResponseBody
	public Map<String, Object> getCompanyDetail(@RequestBody AdminVO admin) {
		Map<String, Object> map = new HashMap<>(2);		
		AdminVO companyInfo = this.admin.getCompanyDetails(admin.getCompanyname());
		map.put("userinfo", companyInfo);
	    this.code = 200;
	    this.msg = "ok";
	    map.put("code", this.code);
	    map.put("msg", this.msg);
	    return map;
	}
	
	@PostMapping("/stockprice/import")
    public String processImport(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model){
        String contentType = file.getContentType();

        String fileName = file.getOriginalFilename();

        if (file.isEmpty()) {

            return "import file can't be blank！";

        }

        ImportSummary importSummary=new ImportSummary();

        String strStock_exchange;
        String strCompanyCode;
        String strFromDate;
        String strToDate;

        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");

        try {
            XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream());

            XSSFSheet sheet = wb.getSheetAt(0);
            List<Stock_price> importDatas = new ArrayList<>();

            XSSFRow row = null;

            row = sheet.getRow(1);

            strStock_exchange=String.valueOf(row.getCell(1).getStringCellValue());
            strCompanyCode=String.valueOf(row.getCell(0).getStringCellValue());
            strFromDate=String.valueOf(dateFormat.format(row.getCell(3).getDateCellValue()));
            strToDate=strFromDate;
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {


                row = sheet.getRow(i);

                Stock_price data = new Stock_price();

                data.setCompany_code(String.valueOf(row.getCell(0).getStringCellValue()));
                data.setStock_exhange(String.valueOf(row.getCell(1).getStringCellValue()));
                data.setCurrent_price(String.valueOf(row.getCell(2).getNumericCellValue()));
                String tempDate=String.valueOf(dateFormat.format(row.getCell(3).getDateCellValue()));
                String tempTime=String.valueOf(row.getCell(4).getStringCellValue());
                data.setDatetimestamp( tempDate+ " "+tempTime);



                if (tempDate.compareTo(strToDate)>0 ){
                    strToDate=tempDate;
                }

                if (tempDate.compareTo(strFromDate)<0 ){
                    strFromDate=tempDate;
                }

                iStock_priceService.save(data);

            }

            AdminVO adminVO = admin.getCompanyStockPrice(strCompanyCode);
            if (adminVO==null){
                importSummary.setCompanyName("Not Exist");
            }else{
                importSummary.setCompanyName(adminVO.getCompanyname());
            }

            importSummary.setImportCount(sheet.getPhysicalNumberOfRows()-1);
            importSummary.setStock_exchange(strStock_exchange);
            importSummary.setFromDate(strFromDate);
            importSummary.setToDate(strToDate);

            model.addAttribute("importSummary",importSummary);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return  "admin/importSummary";


    }	
	
	

}
