package pers.mjw;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;

import org.hibernate.*;

public class UserDoDAO implements UserDAO {

	@Override
	public User find(String name) {
		Configuration cf = new Configuration().configure();
		SessionFactory sf = cf.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction ts = ss.beginTransaction();
		Criteria crit = ss.createCriteria(User.class);
		Criterion c1 = Restrictions.eq("name",name);
		crit.add(c1);

		User SearchUser = new User();
		ArrayList<User> userList = (ArrayList<User>) crit.list();
		System.out.println(userList.size());
		for (User u : userList) {
			SearchUser = u;
			System.out.println(SearchUser);
		}
		ts.commit();
		ss.close();
		return SearchUser;
	}

	@Override
	public User save(User user) {
		Configuration cf = new Configuration().configure();
		SessionFactory sf = cf.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction ts = ss.beginTransaction();
		
		
		ts.commit();
		ss.close();
		return user;
	}

	@Override
	public User update(User user) {
		Configuration cf = new Configuration().configure();
		SessionFactory sf = cf.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction ts = ss.beginTransaction();
		
		
		ts.commit();
		ss.close();
		return user;
		
	}

	@Override
	public User delect(User user) {
		Configuration cf = new Configuration().configure();
		SessionFactory sf = cf.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction ts = ss.beginTransaction();
		
		
		ts.commit();
		ss.close();
		return user;
	}

}
