/**
 * the purpose of class
 * to get time and date from system
 *methods
 *gettingYear():to get year of system-with returned value(year)-no args
 *gettingMonth():to get month of system-with returned value(month)-no args
 *gettingDay():to get day of system-with returned value(day)-no args
 *gettingHour():to get hour of system-with returned value(hour)-no args
 *gettingMinute():to get year of system-with returned value(minute)-no args
 *gettingSecond():to get year of system-with returned value(second)-no args
 *@version 2019-4
 *@author Fatemeh Taraghi
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
public class DateTime {
	int year;
	int month;
	int day;
	int hour;
	int minute;
	int second;
	
	LocalDate today = LocalDate.now();
	public int gettingYear() {
		int year=today.getYear();	
		return year;
	}
	
	public int gettingMonth() {
		int month=today.getMonthValue();
		return month;
	}
	
	public int gettingDay() {
		int dayy= today.getDayOfMonth();
		return dayy;
		
	}
    LocalDateTime time = LocalDateTime.now();
	public int gettingHour() {
	   int hourr=time.getHour();
	   return hourr;
	}
	public int gettingMinute() {
	    int minutee=time.getMinute();
        return minutee;
	}
	public int gettingSecond() {
	    int secondd=time.getSecond();
	    return secondd;

	}
	
}
