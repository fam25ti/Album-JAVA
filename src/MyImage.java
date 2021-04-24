/**
 * purpose of class:to make image with some attributes ad add them to an album
 * methods:
 * only constructor
 * Fields:
 * Image image;
 * String address;
 * String description;
 * String title;
 * @author  Fateme Taraghi
 */
import java.awt.Image;

public class MyImage {
	Image image;
	String address;
	String description;
	String title;
	public MyImage(Image img,String add,String desc,String t) {
		image=img;
		address=add;
		description=desc;
		title=t;
	}
	

}
