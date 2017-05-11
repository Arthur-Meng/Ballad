package pers.mjw;

import java.util.List;
import java.util.Arrays;

public class User {
	String name;
	String password;
	String grade;
	String gender;
	String hobbyall;
	String[] hobby;
	String[] friend;
	String friendall;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobbyall() {
		return hobbyall;
	}

	public void setHobbyall(String hobbyall) {
		this.hobbyall = hobbyall;
	}

	public String[] getHobby() {
		if (hobbyall != null) {
			hobby = hobbyall.split("-");
			for (int i = 0; i < hobby.length; i++) {
				System.out.println(hobby[i]);
			}
		}
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	public String[] getFriend() {
		if (friendall != null) {
			friend = friendall.split("-");
		}
		return friend;
	}

	public void setFriend(String[] friend) {
		this.friend = friend;
	}

	public String getFriendall() {
		return friendall;
	}

	public void setFriendall(String friendall) {
		this.friendall = friendall;
	}

	public Boolean Iffrined(String searvhName) {
		if (this.getFriend() != null) {
			String[] friendList = this.getFriend();
			if (friendList != null) {
				List list = (List) Arrays.asList(friendList);
				if (list != null) {
					if (list.contains(searvhName))
						return true;
				}
			}
		}
		return false;
	}

}
