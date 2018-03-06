package com.loserstar.gradletest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.loserstar.dao.UserDao;
import com.loserstar.entity.User;
import com.loserstar.entity.UserVo;

@Rollback(value=false)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:spring.xml"})
public class userTest {
	
	//mybatis核心的类
//	private   SqlSessionFactory sqlSessionFactory ;
	//mybatis核心的类
//	private SqlSession sqlSession;
	//mapper代理的方式
//	@Autowired
	private   UserDao userDao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void before() throws IOException{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		userDao = (UserDao)applicationContext.getBean("userDao");
		DataSource dataSource = (DataSource)applicationContext.getBean("dataSource");
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	/**
	 * 根据name模糊查询
	 * @throws IOException
	 */
	@Test
	public void findUserListByNameTest() throws IOException{
		User userParam = new User();
		userParam.setUserName("a");
		List<User> userList = userDao.findList(userParam);
		
		for (User resultUser : userList) {
			System.out.println("mapper代理dao--------------查询到user:id:"+resultUser.getId()+" userName:"+resultUser.getUserName()+" password:"+resultUser.getPassword());
		}
		
	}
	
	/**
	 * 使用spring的JDBCTemplate
	 */
	@Test
	public void findUserListByNameForSpringJDBCTest(){
		String sql = "select * from sys_users";
		List<Map<String, Object>> userList = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> resultUser : userList) {
			System.out.println("spring的JDBCTemplate--------------查询到user:id:"+resultUser.get("id")+" userName:"+resultUser.get("user_name")+" password:"+resultUser.get("password"));
		}
	}
	
	
	
	
	
	
	
	/**
	 * 添加用户
	 * @throws IOException
	 */
	public void insertUserTest() throws IOException{
		User insertUser = new User();
		insertUser.setUserName(UUID.randomUUID().toString());
		insertUser.setPassword(String.valueOf(new Random().nextInt(9999999)));
		userDao.insert(insertUser);
		System.out.println("-----------------新添加的记录的id："+insertUser.getId());
	}
	
	/**
	 * 修改用户信息
	 * @throws IOException 
	 */
	public void updateUserTest() throws IOException{
		User updateUser = new User();
		updateUser.setId(16);
		updateUser.setUserName("newName_"+UUID.randomUUID());
		updateUser.setPassword(String.valueOf("newPassword_"+new Random().nextInt(9999999)));
		userDao.update(updateUser);
	}
	
	
	public void deleteUserTest() throws IOException{
		userDao.delete(16);
	}
	
	/**
	 * 得到一个单个用户的示例
	 * @throws IOException
	 */
	public void getUserTest() throws IOException{
		User resultUser = userDao.get(16);
		System.out.println("--------------查询到user:id:"+resultUser.getId()+" userName:"+resultUser.getUserName()+" password:"+resultUser.getPassword());
		
	}
	
	

	
	

	
	
	public void findUserVoListTest(){
		User userParam = new User();
		userParam.setUserName("h");
		List<UserVo> userVoList = userDao.findUserVoList(userParam);
		
		for (UserVo resultUserVo : userVoList) {
			System.out.println("--------------查询到userVo:id:"+resultUserVo.getId()+" name:"+resultUserVo.getName()+" pwd:"+resultUserVo.getPwd());
		}
	}
	
	
	public void findListByIdsTest(){
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(12);
		ids.add(13);
		ids.add(14);
		List<UserVo> userVoList = userDao.findListByIds(ids);
		
		for (UserVo resultUserVo : userVoList) {
			System.out.println("--------------查询到userVo:id:"+resultUserVo.getId()+" name:"+resultUserVo.getName()+" pwd:"+resultUserVo.getPwd());
		}
	}
}
