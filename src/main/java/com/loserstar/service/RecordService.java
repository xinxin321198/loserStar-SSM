/**
 * author: loserStar
 * date: 2019年4月28日下午3:20:40
 * email:362527240@qq.com
 * github:https://github.com/xinxin321198
 * remarks:
 */
package com.loserstar.service;

import org.springframework.stereotype.Service;

import com.loserstar.utils.db.jfinal.base.imp.BaseService;

/**
 * author: loserStar
 * date: 2019年4月28日下午3:20:40
 * remarks:
 */
@Service
public class RecordService extends BaseService {

	@Override
	protected String getTableName() {
		return "record";
	}

	@Override
	protected String getPrimaryKey() {
		return "no";
	}

	@Override
	protected String getSoftDelField() {
		return null;
	}

}
