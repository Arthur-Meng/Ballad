package pers.mjw;

public class Blog {
	String name;
	String head;
	String text;
	String date;
	String commentall;

	public String getCommentall() {
		return commentall;
	}

	public void setCommentall(String commentall) {
		this.commentall = commentall;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Blog(String name, String head, String text, String date, String commentall) {
		this.name = name;
		this.head = head;
		this.text = text;
		this.date = date;
		this.commentall = commentall;
	}

	public Blog(String name, String head, String text, String date) {
		this.name = name;
		this.head = head;
		this.text = text;
		this.date = date;

	}
}
