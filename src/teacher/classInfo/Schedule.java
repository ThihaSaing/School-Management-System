package teacher.classInfo;

public class Schedule {

	private int scheduleId;
	private String subjectName;
	private String timeFrom;
	private String timeTo;
	private String day;
	private String className;
	
	public Schedule(int scheduleId, String subjectName, String timeFrom, String timeTo, String day, String className){
		this.scheduleId=scheduleId;
		this.subjectName=subjectName;
		this.timeFrom=timeFrom;
		this.timeTo=timeTo;
		this.day=day;
		this.className=className;
	}

	@Override
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", subjectName=" + subjectName + ", timeFrom=" + timeFrom
				+ ", timeTo=" + timeTo + ", day=" + day + ", className=" + className + "]";
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
}
