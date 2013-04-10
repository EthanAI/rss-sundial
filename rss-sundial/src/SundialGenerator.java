import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//Test edit by ES 4.10.13
public class SundialGenerator {

	private JFrame frame;
	private JTextField txtEnterLongitudeHere;
	private JTextField txtEnterLatitudeHere;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSundialGenerator = new JLabel("Sundial Generator");
		lblSundialGenerator.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblSundialGenerator.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblSundialGenerator, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("434px:grow"),},
			new RowSpec[] {
				RowSpec.decode("14px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		final JLabel lblStatusReady = new JLabel("Status: Ready");
		lblStatusReady.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblStatusReady, "1, 1, fill, top");
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblDate, "1, 3, center, default");
		
		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane, "1, 5, center, center");
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		comboBox.setMaximumRowCount(5);
		splitPane.setLeftComponent(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_1.setMaximumRowCount(5);
		splitPane.setRightComponent(comboBox_1);
		
		JLabel lblLongitude = new JLabel("Longitude");
		lblLongitude.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblLongitude, "1, 7, center, default");
		
		txtEnterLongitudeHere = new JTextField();
		txtEnterLongitudeHere.setText("Enter Longitude Here");
		panel.add(txtEnterLongitudeHere, "1, 9, fill, default");
		txtEnterLongitudeHere.setColumns(10);
		
		JLabel lblLatitude = new JLabel("Latitude");
		lblLatitude.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblLatitude, "1, 11, center, default");
		
		txtEnterLatitudeHere = new JTextField();
		txtEnterLatitudeHere.setText("Enter Latitude Here");
		panel.add(txtEnterLatitudeHere, "1, 13, fill, default");
		txtEnterLatitudeHere.setColumns(10);
		
		JLabel lblMesaVerdeReseach = new JLabel("Mesa Verde Research Group");
		panel.add(lblMesaVerdeReseach, "1, 17, center, default");
		
		JButton btnGenerateSundial = new JButton("Generate Sundial");
		btnGenerateSundial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					double longitude = Double.parseDouble(txtEnterLongitudeHere.getText());
					double latitude = Double.parseDouble(txtEnterLatitudeHere.getText());
					
					if (Math.abs(longitude) > 180)
					{
						lblStatusReady.setText("Status: Invalid longitude!");
					}
					else if (Math.abs(latitude) > 90)
					{
						lblStatusReady.setText("Status: Invalid latitude!");
					}
					else
					{
						lblStatusReady.setText("Status: Drawing..");
					}
				}
				catch (NumberFormatException e)
				{
					lblStatusReady.setText("Status: Please enter numerical digits as longitude and latitude values!");
				}
					
				
			}
		});
		frame.getContentPane().add(btnGenerateSundial, BorderLayout.SOUTH);
	}
}
