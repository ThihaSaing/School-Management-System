package guest;

public class GuestNews {
	
	private int newsId;
	private String newsTitle;
	private String newsContent;
	
	public GuestNews(int newsid,String newstitle,String newscontent)
	{
		this.newsId=newsid;
		this.newsTitle=newstitle;
		this.newsContent=newscontent;
	}
	

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", newsTitle=" + newsTitle + ", newsContent=" + newsContent + "]";
	}
	

}
