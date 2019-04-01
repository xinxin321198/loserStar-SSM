/**
 * author: loserStar
 * date: 2018年4月19日上午8:53:59
 * email:362527240@qq.com
 * github:https://github.com/xinxin321198
 * remarks:
 */
package com.loserstar.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.jfinal.plugin.activerecord.Record;
import com.loserstar.utils.ObjectMapConvert.LoserStarObjMapConvertUtil;
import com.loserstar.utils.file.LoserStarFileUtil;
import com.loserstar.utils.proerties.LoserStarPropertiesUtil;

/**
 * 
 * author: loserStar
 * date: 2019年4月1日下午5:49:33
 * remarks:基础的controller
 */
public class BaseController {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	/**
	 * 初始化servlet三大作用域对象
	 * 
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	/**
	 * 检查参数，null或者空字符串的返回false
	 * @param param
	 * @return
	 */
	protected boolean checkParam(String param) {
		boolean flag = false;
		if (param != null && !param.equals("")) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 获取jfinalrecord对象里的columns，因为里面还包含了一些字段和值的其它数据，所以要剔除掉
	 * @param list
	 * @return
	 */
	protected List<Map<String, Object>> getColumns(List<Record> list) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (Record record : list) {
			resultList.add(record.getColumns());
		}
		return resultList;
	}


	/**
	 * 获取file-service.properties配置文件
	 * 
	 * @return
	 */
	protected Properties getFileProperties() {
		String filePath = getPropertiesPath() + "file-service.properties";
		return LoserStarPropertiesUtil.getProperties(filePath);
	}
	
	/**
	 * 得到项目绝对路径（末尾带斜杠）
	 * 
	 * @return
	 */
	protected String getRealPath() {
		String root = request.getServletContext().getRealPath(File.separator);
		if (!root.endsWith(java.io.File.separator)) {
			root = root + java.io.File.separator;
		}
		return root;
	}

	/**
	 * 得到配置文件目录(带斜杠)
	 * 
	 * @return
	 */
	protected String getPropertiesPath() {
		return getRealPath() + "WEB-INF" + File.separator + "classes" + File.separator;
	}


	/**
	 * 上传文件到某个绝对路径
	 * 
	 * @param uploadDir
	 *            系统的绝对路径
	 * @param file
	 *            文件对象
	 * @return
	 * @throws IOException
	 */
	protected String uploadFile(String uploadDir, MultipartFile file) throws IOException {
		String fileRealName = file.getOriginalFilename(); // 获得原始文件名
		LoserStarFileUtil.createDir(uploadDir);// 创建上传路径
		String newFileName = LoserStarFileUtil.generateUUIDFileName(fileRealName);// 生成新文件名
		LoserStarFileUtil.WriteInputStreamToFilePath(file.getInputStream(), uploadDir + newFileName, false);// 输出文件
		return newFileName;
	}


	/**
	 * 下载文件，指定一个文件的绝对路径以及下载时显示的文件名
	 * 
	 * @param downloadFilePath
	 *            文件的绝对路径
	 * @param downName
	 *            下载时的名称,为null时默认取真实的该文件名称
	 * @throws Exception
	 */
	protected void downloadFile(String downloadFilePath, String downName) throws Exception {
		InputStream inputStream;
		OutputStream outputStream;
		try {
			File file = new File(downloadFilePath);
			if (downName == null || downName.equals("")) {
				downName = file.getName();
			}
			inputStream = new FileInputStream(file);
			outputStream = response.getOutputStream();
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(java.net.URLEncoder.encode(downName, "UTF-8")));
			response.setContentType("application/octet-stream");
			LoserStarFileUtil.WriteInputStreamToOutputStream(inputStream, outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载文件，指定一个文件的绝对路径
	 * 
	 * @param downloadFilePath
	 * @throws Exception
	 */
	protected void downloadFile(String downloadFilePath) throws Exception {
		downloadFile(downloadFilePath, null);
	}

	/**
	 * map转为jfinal的record对象
	 * 
	 * @param mapList
	 * @return
	 */
	protected List<Record> ToRecordList(List<Map<String, Object>> mapList) {
		List<Record> recordList = new ArrayList<>();
		for (Map<String, Object> map : mapList) {
			Record record = new Record();
			record.setColumns(map);
			recordList.add(record);
		}
		return recordList;
	}

	/**
	 * object对象（必须为数组类型的）转为record的List对象
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	protected List<Record> ToRecordList(Object object) throws Exception {
		List<Object> objectList = ToList(object);
		List<Map<String, Object>> mapList = ToMapList(objectList);
		List<Record> recordList = ToRecordList(mapList);
		return recordList;
	}

	/**
	 * object对象转为map
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	protected static Map<String, Object> ToMap(Object obj) throws Exception {
		return LoserStarObjMapConvertUtil.ConvertToMap(obj);
	}

	/**
	 * List<Object>转为List<Map<String,Object>>
	 * 
	 * @param objectList
	 * @return
	 * @throws Exception
	 */
	protected static List<Map<String, Object>> ToMapList(List<Object> objectList) throws Exception {
		return LoserStarObjMapConvertUtil.ConvertListToMapList(objectList);
	}

	/**
	 * object对象转为List<Object>
	 * 
	 * @param object
	 * @return
	 * @throws Exception
	 */
	protected static List<Object> ToList(Object object) throws Exception {
		return LoserStarObjMapConvertUtil.ConvertToList(object);
	}
}
