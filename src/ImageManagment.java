/**
 * Purpose of class:to create a window that shows pictures of an album in itself.
 * you can control images add new one or delete one in album .
 * methods:
 * constructor->with no argument->it makes the page and manage actions in it
 * load_pics()->void-whit no argument->to load all pictures from file 
 * and add them to array list of MyImage
 * save()->void->with no argument->to save changes in images of album 
 * for example add a new image or delete one
 * slideshow()->void->with no argument ->to slide show images in album
 * automatic->it has used in constructor to add action to button slideshow
 * clock()->to show date and time on screen if user wants
 * Fields of class:
 * JFrame frame;
 * Container contentPane ;
 * JPanel panel;
 * JTextField textField2;
 * JLabel descriptions;
 * JLabel titles;
 * ImageIcon icon;
 * JLabel image_label;
 * JLabel clock_lbl;
 * int image_count=-1;
 * @author Fateme Taraghi
 * @version 1
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class ImageManagment {
	ArrayList<MyImage> album=new ArrayList<MyImage>();
	JFrame frame;
	Container contentPane ;
	JPanel panel;
	JTextField textField2;
	JLabel descriptions;
	JLabel titles;
	ImageIcon icon;
	JLabel image_label;
	JLabel clock_lbl;
	int image_count=-1;//to know number of image in array list to show that
	public ImageManagment() {
		frame=new JFrame("My Album");//rename window to my Album
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//to end program when click mark
		contentPane = frame.getContentPane();//to initial container
		frame.setBounds(0, 0, 550, 450);
		contentPane.setLayout(new BorderLayout(0,0));
		panel=new JPanel();
		panel.setBackground(new Color(204, 204, 255));//to give a color to panel
		contentPane.add(panel,BorderLayout.CENTER);
		panel.setLayout(null);	
		JPanel panel_1=new JPanel();//panel_1 is to add pictures to that
		panel_1.setBounds(52, 76, 429, 180);
		panel.add(panel_1);
		panel_1.setLayout(null);//to give layout with free rules!
		image_label = new JLabel("");
		Image img;
		Image scaled = null;
		try {
		img=ImageIO.read(new File("C:\\Users\ft\\Desktop\\welcome.jpg"));//to read image welcome from file
		scaled=img.getScaledInstance(429, 180, 0);//to fix image to panel_1
		
		}
		catch(IOException io) {
			io.printStackTrace();
		}
		icon=new ImageIcon(scaled);
		image_label.setIcon(icon);
		image_label.setBounds(0, 0, 429, 180);
		panel_1.add(image_label);
		
		JButton btnPreviouse = new JButton("Previous");//show previous photo
		 
		btnPreviouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(image_count==0) {
					JOptionPane.showMessageDialog(frame, "First Of Album!!!");

				}
				else {
					image_count--;
					try {
					MyImage im=album.get(image_count);
					Image imagee=im.image;
					Image scaled=imagee.getScaledInstance(429, 180, 0);
					icon=new ImageIcon(scaled);
					image_label.setIcon(icon);
					String tit=im.title;
					String descript=im.description;
					descriptions.setText(descript);//to add description of photo
					titles.setText(tit);
					
				}
					catch(Exception ex) {
						image_count++;
						JOptionPane.showMessageDialog(frame,"It is welcome photo and there is no previouse image ");
					}
				}
			}
		});
		
		
		btnPreviouse.setBounds(52, 267, 85, 23);
		panel.add(btnPreviouse);
		JButton btnSlideShow = new JButton("SlideShow");
		btnSlideShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				slideshow();
			}	

			
		});
		
		btnSlideShow.setBounds(206, 267, 100, 23);
		panel.add(btnSlideShow);
		JButton btnNext = new JButton("Next");//to show next picture
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(image_count==album.size()-1) {
					JOptionPane.showMessageDialog(frame, "End Of Album!!!");//if it reaches to end of album so it shows this messege

				}
				
				else {
					image_count++;
					MyImage im=album.get(image_count);
					Image imagee=im.image;
					Image scaled=imagee.getScaledInstance(429, 180, 0);
					icon=new ImageIcon(scaled);
					image_label.setIcon(icon);
					String tit=im.title;
					String descript=im.description;
					descriptions.setText(descript);
					titles.setText(tit);
				}
			}
		});
		btnNext.setBounds(394, 267, 89, 23);
		panel.add(btnNext);

		JLabel title_label = new JLabel("Title:");//to add title to image
		title_label.setBounds(94, 17, 31, 14);
		panel.add(title_label);
		
		JLabel description_label = new JLabel("Description:");//to add description to image
		description_label.setBounds(52, 51, 73, 14);
		panel.add(description_label);
		
		descriptions=new JLabel("*************");
		descriptions.setBounds(135, 48, 346, 20);
		panel.add(descriptions);
		titles=new JLabel("************");
		titles.setBounds(135, 14, 96, 20);
		panel.add(titles);
		JMenuBar menuBar=new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu menu=new JMenu("File");
		menuBar.add(menu);
		JMenuItem item1=new JMenuItem("Add New Image");//to add new image
		menu.add(item1);
		item1.addActionListener(new ActionListener() {//annoymous class
		     public void actionPerformed(ActionEvent e) {
		         String address;
		         String titleOfImg;
		         String descriptionOfimg;
		         address=JOptionPane.showInputDialog("Please enter address of image");
		         
		         try {
		         	Image img=ImageIO.read(new File(address));//it will search address that user entered
		         	titleOfImg=JOptionPane.showInputDialog("Please enter title of image");
		         	descriptionOfimg=JOptionPane.showInputDialog("Please enter description of image" );
			         MyImage my=new MyImage(img, address, descriptionOfimg, titleOfImg);
			         album.add(my);
		         	save();
		         			
		         }
		         catch(IOException io) {
		        	 JOptionPane.showMessageDialog(frame, io.getMessage());
		         }
		        		 
		     }
		});
		JMenuItem item2=new JMenuItem("Delete current image");
		menu.add(item2);
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(image_count==-1) {
					JOptionPane.showMessageDialog(frame, "You can't delete this photo");
				}
				else {
				int yes_no=JOptionPane.showConfirmDialog(frame, "Do you really want to delete this photo?","Delete image",JOptionPane.YES_NO_OPTION);
				if(yes_no==0) {//if it returns 0 it means yes
					album.remove(image_count);
					save();
				}
				else {}
				
			}}
		});
		JMenuItem item_clock=new JMenuItem("Add date & time to window");
		menu.add(item_clock);
		item_clock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clock();
			}
		});
		JMenuItem item_title=new JMenuItem("Slid show on a special item");
		menu.add(item_title);
		item_title.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {//to show al images with a title
				ArrayList<MyImage> titledimages=new ArrayList<MyImage>();
				String title=JOptionPane.showInputDialog("Please enter title");
				int found=0;
				for(MyImage image: album) {
					if(image.title.toLowerCase().equals(title)) {
						titledimages.add(image);
						found=1;
					}
				}
				if(found==0) {
					JOptionPane.showMessageDialog(frame, "This title not found!");
					
				}
				else{
					new Thread() {
						public void run() {
							
							for(MyImage my:titledimages) {
								
								Image imagee=my.image;
								Image scaled=imagee.getScaledInstance(429, 180, 0);
								icon=new ImageIcon(scaled);
								image_label.setIcon(icon);
								String tit=my.title;
								String descript=my.description;
								descriptions.setText(descript);
								titles.setText(tit);
								try {
					                Thread.sleep(1000); 
								} catch (Exception e1) {
									e1.printStackTrace();
								}
						}
							JOptionPane.showMessageDialog(frame, "End Of images with this title!!!Press Next to back to First of album");
						}
					}.start();
				image_count=-1;
				}
			}
		});
		JMenuItem item_exit=new JMenuItem("Exit");
		menu.add(item_exit);
		item_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		clock_lbl=new JLabel();
		clock_lbl.setBounds(10, 350, 293, 14);
		frame.setVisible(true);
		JOptionPane.showMessageDialog(frame, "ImageViewer\n" + "version 1"+"\nWriter *M.T*",
				"About Album", 
                JOptionPane.INFORMATION_MESSAGE);
	}
		
	public void load_pics() {//to load addresses of images
		try {
			FileReader reader=new FileReader("url.txt");
			Scanner sc=new Scanner(reader);
			while(sc.hasNext()) {
				String url=sc.nextLine();
				Image image=null;
				try {
					image=ImageIO.read(new File(url));
				}
				catch(IOException io) {
					io.printStackTrace();
				}
				String title=sc.nextLine();
				String description=sc.nextLine();
				MyImage m=new MyImage(image,url, description, title);
				album.add(m);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void save() {
		try {
     		PrintWriter print=new PrintWriter("url.txt");
     		for(MyImage mi:album) {
     		print.println(mi.address+"\n"+mi.title+"\n"+mi.description);
     	}
     		print.close();

     		}
     	catch(Exception ex) {
        	 JOptionPane.showMessageDialog(frame, ex.getMessage());

     	}
	}
	public void slideshow() {//it will be used in constructor
		new Thread() {
			public void run() {
				image_count=-1;
				for(MyImage my:album) {
					image_count++;
					Image imagee=my.image;
					Image scaled=imagee.getScaledInstance(429, 180, 0);
					icon=new ImageIcon(scaled);
					image_label.setIcon(icon);
					String tit=my.title;
					String descript=my.description;
					descriptions.setText(descript);
					titles.setText(tit);
					try {
		                Thread.sleep(1000); 
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
				JOptionPane.showMessageDialog(frame, "End Of Album!!!");
			}
		}.start();
	}
	public void clock() {//it will be used in constructor
		panel.add(clock_lbl);
		Thread clock=new Thread() {
			public void run() {
				try {
					while(true) {
					DateTime dt=new DateTime();
					int day=dt.gettingDay();
					int month=dt.gettingMonth();
					int year=dt.gettingYear();
					int hour=dt.gettingHour();
					int minute=dt.gettingMinute();
					int second=dt.gettingSecond();
					clock_lbl.setText("Time "+hour+":"+minute+":"+second+" Date  "+year+"/"+month+"/"+day);
					sleep(1000);
					}
				
			}
				catch(Exception ex) {
				ex.printStackTrace();
			}
			}
			};
			clock.start();
	}
	
	
}
