package pers.mjw;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import io.goeasy.GoEasy;

/**
 * Servlet implementation class ResultServlet
 */
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResultServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("Personal/Result.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		int i = 1;
		User user = (User) session.getAttribute("User");
		String userName = user.getName();
		String searchName = session.getAttribute("SearchName").toString();
		String follow = "";
		if (request.getParameter("follow").toString().equals("follow")) {
			follow = request.getParameter("follow");
			System.out.println("follow");
		} else {
			follow = "unfollow";
			System.out.println("unfollow");
		}
		try {
			Configuration cf = new Configuration().configure();
			SessionFactory sf = cf.buildSessionFactory();
			Session ss = sf.openSession();
			Transaction ts = ss.beginTransaction();

			/*
			 * Criteria crit = ss.createCriteria(User.class); Criterion c1 =
			 * Restrictions.eq("name", userName); crit.add(c1);
			 * 
			 * User SearchUser = new User(); ArrayList<User> userList =
			 * (ArrayList<User>) crit.list();
			 * System.out.println(userList.size()); for (User u : userList) {
			 * SearchUser = u; System.out.println(SearchUser); }
			 */

			String[] friendList = null;
			if (user.getFriend() != null)
				friendList = user.getFriend();
			List list = null;
			if (friendList != null)
				list = (List) Arrays.asList(friendList);
			if (list != null) {
				if (follow.equals("follow")) {
					if (list.contains(searchName)) {
						out.println("Has followed!");
						response.setHeader("Refresh", "2;URL=result");
					} else {
						String friendAll = "";
						list = (List) Arrays.asList(user.getFriend());
						System.out.println(list.size());
						for (int k = 0; k < list.size(); k++) {
							if (!list.get(k).equals("")) {
								friendAll += "-" + list.get(k);
								System.out.println(k + ":" + list.get(k).toString());
							}
						}
						System.out.println(user.getFriendall());
						friendAll += "-" + searchName;
						user.setFriendall(friendAll);
						System.out.println(friendAll);
						System.out.println(user.getFriendall());
						session.setAttribute("User", user);
						ss.update(user);
						i = 0;
						GoEasy goEasy = new GoEasy("BC-bf72dea123c24a54a1b4c737bafbcea4");
						goEasy.publish(searchName.toString(), userName);
					}
				} else {
					if (list.contains(searchName)) {
						String friendAll = "";
						for (int k = 0; k < list.size(); k++) {
							if (list.get(k).equals(searchName)) {
								System.out.println("remove");
							} else {
								if (!list.get(k).equals("")) {
									friendAll += "-" + list.get(k);
								}
							}
						}
						user.setFriendall(friendAll);
						System.out.println(user.getFriendall());
						ss.update(user);
						i = 0;
					} else {
						out.println("Not followed!");
						response.setHeader("Refresh", "2;URL=result");
					}
				}
			} else {
				if (follow.equals("follow")) {
					String friendAll = "";
					friendAll += "-" + searchName;
					user.setFriendall(friendAll);
					ss.update(user);
					i = 0;
					GoEasy goEasy = new GoEasy("BC-bf72dea123c24a54a1b4c737bafbcea4");
					goEasy.publish(searchName.toString(), userName);
				} else {
					out.println("Not followed!");
					response.setHeader("Refresh", "2;URL=result");
				}

			}
			ts.commit();
			ss.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (i == 0) {
			out.println("Success!");
			response.setHeader("Refresh", "2;URL=result");
		} else {
			out.println("Fali!");
			response.setHeader("Refresh", "2;URL=result");
		}
	}

}
