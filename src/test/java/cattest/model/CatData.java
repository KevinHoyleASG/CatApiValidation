package cattest.model;

import com.opencsv.bean.CsvBindByPosition;

public class CatData {

	@CsvBindByPosition(position = 0)
    private String _id;

    @CsvBindByPosition(position = 1)
    private String text;

    @CsvBindByPosition(position = 2)
    private String type;

    @CsvBindByPosition(position = 3)
    private String user_id;

    @CsvBindByPosition(position = 4)
    private String user_name_first;

    @CsvBindByPosition(position = 5)
    private String user_name_last;    

    @CsvBindByPosition(position = 6)
    private String upvotes;

    @CsvBindByPosition(position = 7)
    private String userUpvoted;

    //Getters & Setters
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name_first() {
		return user_name_first;
	}

	public void setUser_name_first(String user_name_first) {
		this.user_name_first = user_name_first;
	}

	public String getUser_name_last() {
		return user_name_last;
	}

	public void setUser_name_last(String user_name_last) {
		this.user_name_last = user_name_last;
	}

	public String getUpvotes() {
		return upvotes;
	}

	public void setUpvotes(String upvotes) {
		this.upvotes = upvotes;
	}

	public String getUserUpvoted() {
		return userUpvoted;
	}

	public void setUserUpvoted(String userUpvoted) {
		this.userUpvoted = userUpvoted;
	}   


    

}
