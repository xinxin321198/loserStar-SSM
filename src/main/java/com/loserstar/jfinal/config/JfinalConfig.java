/**
 * author: loserStar
 * date: 2018年4月17日下午9:40:06
 * email:362527240@qq.com
 * github:https://github.com/xinxin321198
 * remarks:
 */
package com.loserstar.jfinal.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.loserstar.utils.date.LoserStarDateUtils;


/**
 * 集成jfinal的db
 * author: loserStar
 * date: 2019年4月1日下午4:59:19
 * remarks:
 */
@Configuration
public class JfinalConfig {
	// 由于spring中已经注入了DruidDataSource这里直接拿
	
	@Autowired
	@Qualifier("dataSource")
	private org.apache.commons.dbcp.BasicDataSource dataSource;
	
	
	@Bean(initMethod = "start", destroyMethod = "stop")
	public ActiveRecordPlugin init2() {
		System.out.println("init----------Jfinal plugin---------------------"+LoserStarDateUtils.format(new Date()));
		ActiveRecordPlugin arp2 = new ActiveRecordPlugin(dataSource);
		arp2.setShowSql(true);// 打印出执行的sql
		arp2.setDialect(new AnsiSqlDialect());
		arp2.setContainerFactory(new CaseInsensitiveContainerFactory(true));// false 是大写,// true是小写,	// 不写是区分大小写
		arp2.start();
		return arp2;
	}
}
