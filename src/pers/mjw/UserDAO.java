package pers.mjw;

public interface UserDAO {
	public User find(String name);
	public User save(User user);
	public User update(User user);
	public User delect(User user);
}
