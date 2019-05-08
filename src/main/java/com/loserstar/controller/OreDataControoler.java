/**
 * author: loserStar
 * date: 2019年4月18日下午4:39:44
 * email:362527240@qq.com
 * github:https://github.com/xinxin321198
 * remarks:
 */
package com.loserstar.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jfinal.plugin.activerecord.Record;
import com.loserstar.service.RecordService;
import com.loserstar.service.SubService;
import com.loserstar.utils.db.jfinal.base.imp.WhereHelper;
import com.loserstar.utils.db.jfinal.vo.VResult;
import com.loserstar.utils.excel.LoserStarExcelUtils;
import com.loserstar.utils.json.LoserStarJsonUtil;
import com.loserstar.utils.string.LoserStarStringUtils;

/**
 * author: loserStar
 * date: 2019年4月18日下午4:39:44
 * remarks:
 */
@Controller
@RequestMapping(value="/oreData")
public class OreDataControoler extends BaseController {
	
	@Autowired
	private RecordService recordService;
	@Autowired
	private SubService subService;
	
	@RequestMapping("purchased")
	public String purchased() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/oreData/purchased";
	}
	
	@RequestMapping("unpurchased")
	public String unpurchased() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/oreData/unpurchased";
	}
	
	
	@RequestMapping("readerExcel")
	@ResponseBody
	public Map<String, List<Map<String, String>>> readerExcel(HttpServletRequest request,HttpServletResponse response,@RequestParam Map<String, String> params){
		Map<String, List<Map<String, String>>>  excelMap  =null;
		try {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iterator = multiRequest.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile multipartFile = multiRequest.getFile(iterator.next().toString());
				excelMap= LoserStarExcelUtils.readExcelToMap(multipartFile.getInputStream(), multipartFile.getOriginalFilename(), 0, 2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excelMap;
	}
	
	@RequestMapping("save")
	@ResponseBody
	public VResult save(@RequestBody Map<String, Object> saveData) {
		VResult result = new VResult();
		try {
			System.out.println(LoserStarJsonUtil.toJsonDeep(saveData));
			String no  = LoserStarStringUtils.toString(saveData.get("no"));
			String type  = LoserStarStringUtils.toString(saveData.get("type"));
			String remarks  = LoserStarStringUtils.toString(saveData.get("remarks"));
			List<Record> list = ToRecordList(saveData.get("list"));
			
			//保存主表
			String id = UUID.randomUUID().toString();
			Record record = new Record();
			record.set("id", id);
			record.set("no", no);
			record.set("type", type);
			record.set("remarks", remarks);
			//保存子表
			for (Record sub : list) {
				sub.set("id", UUID.randomUUID().toString());
				sub.set("p_id", id);
				sub.set("no", no);
				sub.set("type", type);
			}
			boolean flag1 = recordService.insert(record);
			System.out.println(LoserStarJsonUtil.toJsonDeep(list));
			int[] flag2= subService.batchInsert(list);
			if(flag1&&flag2.length>0) {
				result.ok("保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("statisticsList")
	public String statisticsList() {
		try {
			List<Record> list = recordService.getList(new WhereHelper().addStrOrder("order by no desc"));
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/oreData/statisticsList";
	}
	
	/**
	 * 统计
	 * @param no
	 * @return
	 */
	@RequestMapping("statistics")
	public String statistics(@RequestParam String no) {
		try {
			List<Record> list = subService.getList(new WhereHelper().addStrWhere("and no='"+no+"'"));
			request.setAttribute("list", list);
			
			
			for (Record record : list) {
				//干量=湿量*水份%
				double ganliang =(record.getDouble("wet_count")*(record.getDouble("water_count")/100));
				record.set("ganliang", ganliang);
				//平均水份=湿量*水份
				double avgWater = record.getDouble("wet_count")*record.getDouble("water_count");
				record.set("avgWater", avgWater);
				//平均Fe = 湿量*Fe
				double avgFe =  record.getDouble("wet_count")*record.getDouble("fe");
				record.set("avgFe", avgFe);
				//平均S = 湿量*S
				double avgS =  record.getDouble("wet_count")*record.getDouble("s");
				record.set("avgS", avgS);
				//平均P = 湿量*P
				double avgP =  record.getDouble("wet_count")*record.getDouble("p");
				record.set("avgP", avgP);
				
				//平均Pb = 湿量*Pb
				double avgPb =  record.getDouble("wet_count")*record.getDouble("pb");
				record.set("avgPb", avgPb);
				//平均As = 湿量*As
				double avgAs =  record.getDouble("wet_count")*record.getDouble("as_");
				record.set("avgAs", avgAs);
				//平均Sio2 = 湿量*Sio2
				double avgSio2 =  record.getDouble("wet_count")*record.getDouble("sio2");
				record.set("avgSio2", avgSio2);
				//平均Tio2 = 湿量*Tio2
				double avgTio2 =  record.getDouble("wet_count")*record.getDouble("tio2");
				record.set("avgTio2", avgTio2);
				//平均Zn = 湿量*Zn
				double avgZn =  record.getDouble("wet_count")*record.getDouble("zn");
				record.set("avgZn", avgZn);
				//平均Cu = 湿量*Cu
				double avgCu =  record.getDouble("wet_count")*record.getDouble("cu");
				record.set("avgCu", avgCu);
				//平均Sn = 湿量*Sn
				double avgSn =  record.getDouble("wet_count")*record.getDouble("sn");
				record.set("avgSn", avgSn);
				//平均k20 = 湿量*k20
				double avgk20 =  record.getDouble("wet_count")*record.getDouble("k20");
				record.set("avgk20", avgk20);
				//平均Na20 = 湿量*Na20
				double avgNa20 =  record.getDouble("wet_count")*record.getDouble("na20");
				record.set("avgNa20", avgNa20);
				//平均单价 = 湿量*单价
				double avgPrice =  record.getDouble("wet_count")*record.getDouble("price");
				record.set("avgPrice", avgPrice);
				
			}
			
			double sumGanliang = 0;//干量（总和）
			double sum_wet_count = 0;//湿量（总和）
			double sum_avgWater = 0;//平均水份（总和）
			double sum_avgFe = 0;//平均Fe（总和）
			double sum_avgS = 0;//平均S（总和）
			double sum_avgP = 0;//平均P（总和）
			double sum_avgPb = 0;//平均Pb（总和）
			double sum_avgAs = 0;//平均As（总和）
			double sum_avgSio2 = 0;//平均Sio2（总和）
			double sum_avgTio2 = 0;//平均Tio2（总和）
			double sum_avgZn = 0;//平均Zn（总和）
			double sum_avgCu = 0;//平均Cu（总和）
			double sum_avgSn = 0;//平均Sn（总和）
			double sum_avgK20 = 0;//平均K20（总和）
			double sum_avgNa20 = 0;//平均Na20（总和）
			double sum_avgPrice = 0;//平均单价（总和）
			
			double synthetical_water_count = 0;//综合水份
			double synthetical_Fe = 0;//综合Fe
			double synthetical_S = 0;//综合S
			double synthetical_P = 0;//综合P
			double synthetical_Pb = 0;//综合Pb
			double synthetical_As = 0;//综合As
			double synthetical_Sio2 = 0;//综合Sio2
			double synthetical_Tio2 = 0;//综合Tio2
			double synthetical_Zn = 0;//综合Zn
			double synthetical_Cu = 0;//综合Cu
			double synthetical_Sn = 0;//综合Sn
			double synthetical_K20 = 0;//综合K20
			double synthetical_Na20 = 0;//综合Na20
			double synthetical_price = 0;//综合单价
			
			//计算总和
			for (Record record2 : list) {
				sumGanliang +=  record2.getDouble("ganliang");
				sum_wet_count += record2.getDouble("wet_count");
				sum_avgWater += record2.getDouble("avgWater");
				sum_avgFe += record2.getDouble("avgFe");
				 sum_avgS += record2.getDouble("avgS");
				 sum_avgP +=record2.getDouble("avgP");
				 sum_avgPb += record2.getDouble("avgPb");
				 sum_avgAs +=record2.getDouble("avgAs");
				 sum_avgSio2 +=record2.getDouble("avgSio2");
				 sum_avgTio2 +=record2.getDouble("avgTio2");
				 sum_avgZn +=record2.getDouble("avgZn");
				 sum_avgCu += record2.getDouble("avgCu");
				 sum_avgSn +=record2.getDouble("avgSn");
				 sum_avgK20 +=record2.getDouble("avgK20");
				 sum_avgNa20 += record2.getDouble("avgNa20");
				 sum_avgPrice +=record2.getDouble("avgPrice"); 
			}
			
			//计算综合
			synthetical_water_count = sum_avgWater/sum_wet_count;
			synthetical_Fe = sum_avgFe/sum_wet_count;
			synthetical_S = sum_avgS/sum_wet_count;
			synthetical_P = sum_avgP/sum_wet_count;
			synthetical_Pb = sum_avgPb/sum_wet_count;
			synthetical_As = sum_avgAs/sum_wet_count;
			synthetical_Sio2 = sum_avgSio2/sum_wet_count;
			synthetical_Tio2 = sum_avgTio2/sum_wet_count;
			synthetical_Zn = sum_avgZn/sum_wet_count;
			synthetical_Cu = sum_avgCu/sum_wet_count;
			synthetical_Sn = sum_avgSn/sum_wet_count;
			synthetical_K20 = sum_avgK20/sum_wet_count;
			synthetical_Na20 = sum_avgNa20/sum_wet_count;
			synthetical_price = sum_avgPrice/sum_wet_count;
			
			
			
			request.setAttribute("sumGanliang",sumGanliang );
			request.setAttribute("sum_wet_count", sum_wet_count);
			request.setAttribute("sum_avgWater",sum_avgWater );
			request.setAttribute("sum_avgFe",sum_avgFe );
			request.setAttribute("sum_avgS", sum_avgS);
			request.setAttribute("sum_avgP",sum_avgP );
			request.setAttribute("sum_avgPb",sum_avgPb );
			request.setAttribute("sum_avgAs",sum_avgAs );
			request.setAttribute("sum_avgSio2",sum_avgSio2 );
			request.setAttribute("sum_avgTio2", sum_avgTio2);
			request.setAttribute("sum_avgZn",sum_avgZn );
			request.setAttribute("sum_avgCu",sum_avgCu );
			request.setAttribute("sum_avgSn",sum_avgSn );
			request.setAttribute("sum_avgK20", sum_avgK20);
			request.setAttribute("sum_avgNa20", sum_avgNa20);
			request.setAttribute("sum_avgPrice",sum_avgPrice );
			request.setAttribute("synthetical_water_count", synthetical_water_count);
			request.setAttribute("synthetical_Fe", synthetical_Fe);
			request.setAttribute("synthetical_S",synthetical_S );
			request.setAttribute("synthetical_P", synthetical_P);
			request.setAttribute("synthetical_Pb",synthetical_Pb );
			request.setAttribute("synthetical_As", synthetical_As);
			request.setAttribute("synthetical_Sio2", synthetical_Sio2);
			request.setAttribute("synthetical_Tio2",synthetical_Tio2 );
			request.setAttribute("synthetical_Zn", synthetical_Zn);
			request.setAttribute("synthetical_Cu", synthetical_Cu);
			request.setAttribute("synthetical_Sn",synthetical_Sn );
			request.setAttribute("synthetical_K20",synthetical_K20 );
			request.setAttribute("synthetical_Na20",synthetical_Na20 );
			request.setAttribute("synthetical_price",synthetical_price );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/oreData/statistics";
	}
}
