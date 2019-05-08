/**
 * author: loserStar
 * date: 2019年4月28日下午3:21:13
 * email:362527240@qq.com
 * github:https://github.com/xinxin321198
 * remarks:
 */
package com.loserstar.service;

import org.springframework.stereotype.Service;

import com.loserstar.utils.db.jfinal.base.imp.BaseService;

/**
 * author: loserStar
 * date: 2019年4月28日下午3:21:13
 * remarks:
 */
@Service
public class SubService extends BaseService {

	@Override
	protected String getTableName() {
		return "sub";
	}

	@Override
	protected String getPrimaryKey() {
		return "id";
	}

	@Override
	protected String getSoftDelField() {
		return null;
	}

}
