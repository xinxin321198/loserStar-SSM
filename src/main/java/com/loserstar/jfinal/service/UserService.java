/**
 * author: loserStar
 * date: 2019年4月1日下午5:10:02
 * email:362527240@qq.com
 * github:https://github.com/xinxin321198
 * remarks:
 */
package com.loserstar.jfinal.service;

import com.loserstar.utils.db.jfinal.base.imp.BaseService;

/**
 * author: loserStar
 * date: 2019年4月1日下午5:10:02
 * remarks:测试表
 */
public class UserService extends BaseService {
	public static final String TABLE_NAME = "sys_users";
	public static final String PRIMARY_KEY = "id";
	public static final String SOFT_DEL_FIELD= "";
	@Override
	protected String getTableName() {
		return TABLE_NAME;
	}

	@Override
	protected String getPrimaryKey() {
		return PRIMARY_KEY;
	}

	@Override
	protected String getSoftDelField() {
		return SOFT_DEL_FIELD;
	}

}
