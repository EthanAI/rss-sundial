import calculationsandtests.SundialCalculations;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import javax.swing.border.LineBorder;

public class SundialGenerator {

	private JFrame frame;
	private JTextField txtEnterLatitudeHere;
	private JTextField txtEnterLongitudeHere;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SundialGenerator window = new SundialGenerator();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SundialGenerator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 577, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Sundial Generator title label
		JLabel lblSundialGenerator = new JLabel("Sundial Generator");
		lblSundialGenerator.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblSundialGenerator.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblSundialGenerator, BorderLayout.NORTH);
		
		// Main data input panel
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("434px:grow"),},
			new RowSpec[] {
				RowSpec.decode("25px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(11dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(17dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(18dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		// Status label
		final JLabel lblStatusReady = new JLabel("Status: Ready");
		lblStatusReady.setBackground(UIManager.getColor("Label.background"));
		lblStatusReady.setForeground(Color.RED);
		lblStatusReady.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStatusReady.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblStatusReady, "1, 1, fill, center");
		
		// Date label
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblDate, "1, 3, center, default");
		
		// Date input panel
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "1, 7, fill, center");
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Default date combobox
		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_1.setMaximumRowCount(5);
		
		// Month combobox
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Calendar cal = Calendar.getInstance();
				
				// CHANGE DATE COMBOBOX ACCORDING TO THE CHOSEN MONTH
				switch (comboBox.getSelectedIndex())
				{
					case 1: 
						if ((cal.get(Calendar.YEAR))%4 == 0)
						{
							comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"}));
						}
						else
						{
							comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"}));
						}
						break;
					case 3: comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
						break;
					case 5: comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
						break;
					case 8: comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
						break;
					case 10: comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
						break;
					default: comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
						break;
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		comboBox.setMaximumRowCount(5);
		panel_1.add(comboBox);
		panel_1.add(comboBox_1);
		
		// Option to use today's date
		final JCheckBox chckbxUseTodaysDate = new JCheckBox("Use Today's Date");
		chckbxUseTodaysDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxUseTodaysDate.isSelected())
				{
					//lblStatusReady.setText("Status: Using today's date!");
					comboBox.setEnabled(false);
					comboBox_1.setEnabled(false);
				}
				else if (!chckbxUseTodaysDate.isSelected())
				{
					//lblStatusReady.setText("Status: Not using today's date!");
					comboBox.setEnabled(true);
					comboBox_1.setEnabled(true);
				}
			}
		});
		panel.add(chckbxUseTodaysDate, "1, 5, center, default");
		
		// Link to find coordinate data
		JLabel lblFindCoordinateData = new JLabel("Find coordinate data here");
		lblFindCoordinateData.setForeground(Color.BLUE);
		panel.add(lblFindCoordinateData, "1, 9, center, default");
		lblFindCoordinateData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try
				{
					URI url = new URI("http://www.worldatlas.com/aatlas/findlatlong.htm");
					
					if (Desktop.isDesktopSupported())
		            {
		                try
		                {
		                    Desktop.getDesktop().browse(url);
		                }
		                catch (Exception e)
		                {
		                    // CANNOT BROWSE, DO NOTHING FOR NOW
		                }
		            }
		            else
		            {
		                // DESKTOP IS NOT SUPPORTED, DO NOTHING FOR NOW
		            }
				}
				catch (URISyntaxException e)
				{
					// URISyntaxException, do nothing for now
				}	
			}
		});
		
		// Latitude label
		JLabel lblLatitude = new JLabel("Latitude");
		lblLatitude.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblLatitude, "1, 11, center, default");
		
		// Panel for latitude inputs
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "1, 13, center, center");
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Latitude input textfield
		txtEnterLatitudeHere = new JTextField();
		panel_2.add(txtEnterLatitudeHere);
		txtEnterLatitudeHere.setText("Enter Latitude Here");
		txtEnterLatitudeHere.setColumns(20);
		
		// Latitude directional combobox
		final JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"N", "S"}));
		panel_2.add(comboBox_2);
		
		// Longitude label
		JLabel lblLongitude = new JLabel("Longitude");
		lblLongitude.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblLongitude, "1, 15, center, default");
		
		// Panel for longitude inputs
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, "1, 17, center, center");
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Longitude input textfield
		txtEnterLongitudeHere = new JTextField();
		panel_3.add(txtEnterLongitudeHere);
		txtEnterLongitudeHere.setText("Enter Longitude Here");
		txtEnterLongitudeHere.setColumns(20);
		
		// Longitude directional combobox
		final JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"E", "W"}));
		panel_3.add(comboBox_3);
		
		// Mesa Verde link
		JLabel lblMesaVerdeReseach = new JLabel("Mesa Verde Research Group");
		lblMesaVerdeReseach.setForeground(Color.BLUE);
		lblMesaVerdeReseach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try
				{
					URI url = new URI("https://code.google.com/p/rss-sundial/");
					
					if (Desktop.isDesktopSupported())
		            {
		                try
		                {
		                    Desktop.getDesktop().browse(url);
		                }
		                catch (Exception e)
		                {
		                    // CANNOT BROWSE, DO NOTHING FOR NOW
		                }
		            }
		            else
		            {
		                // DESKTOP IS NOT SUPPORTED, DO NOTHING FOR NOW
		            }
				}
				catch (URISyntaxException e)
				{
					// URISyntaxException, do nothing for now
				}	
			}
		});
		panel.add(lblMesaVerdeReseach, "1, 19, center, default");
		
		// Generate Sundial button
		JButton btnGenerateSundial = new JButton("Generate Sundial");
		btnGenerateSundial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//
				// BEGIN DATE CALCULATIONS
				//
				Calendar cal = Calendar.getInstance();
				int year = 0;
				year = cal.get(Calendar.YEAR);
				String yearString = Integer.toString(year); //future or past years not currently supported
				int month = 0;
				String monthString = "";
				int day = 0;
				String dayString = "";
				
				// User selected to use today's date
				if (chckbxUseTodaysDate.isSelected())
				{
					month = cal.get(Calendar.MONTH);
					day = cal.get(Calendar.DATE);
				}
				// User chose their own dates
				else
				{
					// Initializing month and day variables
					month = comboBox.getSelectedIndex() + 1;
					day = comboBox_1.getSelectedIndex() + 1;
				}
				
				
				// Assuring month and day are in 2 digit format
				if (month < 10)
				{
					monthString = "0" + Integer.toString(month);
				}
				else
				{
					monthString = Integer.toString(month);
				}
				if (day < 10)
				{
					dayString = "0" + Integer.toString(day);
				}
				else
				{
					dayString = Integer.toString(day);
				}
				
				// Concatenating the date into YYYYMMDD format
				String dateString = yearString + monthString + dayString;
				int date = Integer.parseInt(dateString);
				//
				// END DATE CALCULATIONS
				//
				
				//
				// BEGIN INPUT PROCESSING
				//
				try
				{
					// Initializing longitude and latitude variables from inputs
					double latitude = Double.parseDouble(txtEnterLatitudeHere.getText());
					double longitude = Double.parseDouble(txtEnterLongitudeHere.getText());
					
					// Longitude and Latitude input validity checking
					if (latitude < 0 && longitude < 0)
					{
						lblStatusReady.setText("Status: Negative coordinates should be indicated with S and W!");
					}
					else if (latitude > 90 && longitude > 180)
					{
						lblStatusReady.setText("Status: Invalid latitude and longitude!");
					}
					else if (latitude > 90)
					{
						lblStatusReady.setText("Status: Invalid latitude!");
					}
					else if (latitude < 0)
					{
						lblStatusReady.setText("Status: Negative latitude should be indicated with S!");
					}
					else if (longitude > 180)
					{
						lblStatusReady.setText("Status: Invalid longitude!");
					}
					else if (longitude < 0)
					{
						lblStatusReady.setText("Status: Negative longitude should be indicated with W!");
					}
					// Recieved valid inputs
					else
					{
						// APPLYING DIRECTIONAL MODIFIERS TO COORDINATES
						if (comboBox_2.getSelectedIndex() == 1)
						{
							latitude = latitude * -1;
						}
						if (comboBox_3.getSelectedIndex() == 1)
						{
							longitude = longitude * -1;
						}
						
						lblStatusReady.setText("Status: Drawing..");
						
						///////////////////////////////////////////////////////
						//                                                   //
						// BEGIN SUNDIAL GENERATION CODE                     //
						//                                                   //
						// CURRENT AVAILABLE VARIABLES:                      //
						//  - date : User selected date in yyyymmdd format   //
						//  - latitude : Specified latitude as a double      //
						//  - longitude : Specified longitude as a double    //
						//                                                   //
						///////////////////////////////////////////////////////
					
						//Calculate values needed for drawing
						double[] hourLineAngles = SundialCalculations.getHourLineAngles(latitude, longitude, date);
						int[] lineLabels = SundialCalculations.getLineLabels(latitude, longitude, date);
						double gnomonAngle = SundialCalculations.getGnomonAngle(latitude);
						
						//debug code to test the values
						/*
						System.out.println(latitude + " " + longitude + " " + date);
						System.out.println(gnomonAngle);
						for(int i = 0; i < hourLineAngles.length; i++) {
							System.out.println(hourLineAngles[i] + " " + lineLabels[i]);
						}
						*/
						
						if(SundialCalculations.isNorthernHemisphere(latitude)) {
							
						}
						//southern hemisphere drawing will be different. 
						else {
							
						}
						
						///////////////////////////////////////////////////////
						//                                                   //
						// END SUNDIAL GENERATION CODE                       //
						//                                                   //
						///////////////////////////////////////////////////////
						Thread.sleep(1000);
						lblStatusReady.setText("Status: Complete!");
					}
				}
				// Catches longitude and latitude inputs that cannot be parsed into numbers
				catch (NumberFormatException e)
				{
					lblStatusReady.setText("Status: Please enter latitude and longitude as numerical values!");
				}
				// Catches interrupt exception for the Thread.sleep() command
				catch (InterruptedException e)
				{
					// Do nothing for now
				}
				//
				// END INPUT PROCESSING
				//
				
			}
		});
		frame.getContentPane().add(btnGenerateSundial, BorderLayout.SOUTH);
	}
}
