package com.springtest.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springtest.common.dao.GenericDao;
import com.springtest.system.model.TBaseUser;


@Service("loginservice")
public class LoginService {
	@Autowired
	@Qualifier("genericHibernateDao")
	private GenericDao genericHibernateDao;
	/**
	 * 查找用户信息
	 * @param account	账户
	 * @param password	密码
	 * @return
	 */
	public TBaseUser findTBaseUser(String account,String password){
//		List list=this.genericHibernateDao.executSQL("select * from t_base_user where account='"+account+"' and password='"+password+"'");
		List<TBaseUser>userlist=this.genericHibernateDao.find("from TBaseUser t where t.account=? and t.password=?",new Object[]{account,password});
		if(userlist!=null&&userlist.size()>0){
			return userlist.get(0);
		}else{
			return null;
		}
//		for (Object object : list) {
//			System.out.println(object);
//		}
//		return null;
	}
	/**
	 * 查找用户信息根据userid
	 * @param userid
	 * @return
	 */
	public TBaseUser findTBaseUser(String userid){
		List<TBaseUser>userlist=this.genericHibernateDao.find("from TBaseUser t where t.userid=?",new Object[]{userid});
		if(userlist!=null&&userlist.size()>0){
			return userlist.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 添加用户
	 * @param user 用户信息
	 * @return
	 */
	public Boolean addTBaseUser(TBaseUser user){
		try {
			this.genericHibernateDao.saveOrUpdate(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
