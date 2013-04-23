import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.*;

public class SundialDrawing extends JPanel{
	//TESTING
	static double[] hourLineAngles = new double[13];
	static int[] lineLabels = new int[13];
	static double gnomonAngle;
	static boolean isNorthernHemisphere;

 // Create a constructor method
 public SundialDrawing(double[] hourLineAngles, int[] lineLabels, double gnomonAngle){
	 	super();
 }

  // Create a constructor method
 public SundialDrawing(){
     super();
 }
 //converts the angles on the left side of the 12 line to a x coordinate value
 public double leftSideX(double angle, int length){
	    double x = 800/2 - (length * Math.cos(Math.toRadians(90 + (angle))));
	    return x;
	}

 //converts the angles on the left side of the 12 line to a y coordinate value
 public double leftSideY(double angle, int length){
	    double y = 600 - (length * Math.sin(Math.toRadians(90 + (angle))));
	    return y;
	}
 //converts the angles on the right side of the 12 line to a x coordinate value
 public double rightSideX(double angle, int length){
	    double x = 800/2 - (length * Math.sin(Math.toRadians(180+(angle))));
	    return x;
	}
 //converts the angles on the right side of the 12 line to a y coordinate value
 public double rightSideY(double angle, int length){
	    double y = 600 + (length * Math.cos(Math.toRadians(180+(angle))));
	    return y;
	}
 //converts the gnomon angle to a x coordinate value
 public double gX(double angle, int length){
		    double x = 1270 - (length * Math.cos(Math.toRadians(90-(angle))));
		    return x;
		}
//converts the gnomon angle to a y coordinate value
 public double gY(double angle, int length){
		    double y = 118 - (length * Math.sin(Math.toRadians(90-(angle))));
		    return y;
		}
 //paint method that is written specifically for the sundial.
 public void paint(Graphics g) {
	 Graphics2D g2 = (Graphics2D) g;
		 
	 /**"
	  * dial" of the sundial 
	  * draws out each line of the sundial accordingly and adds labels to each line
	  * all lines are the same length "375", except for the two 6 hour lines if they are < -90 or >90
	  * all lines start at position 400,600 and go out from there based  on the angles.
	 **/
	 double x;
	 double y;
	 if(hourLineAngles[0] < -90.00) {
		 x = leftSideX(hourLineAngles[0], 245);
		 y = leftSideY(hourLineAngles[0], 245);
		 Line2D line1 = new Line2D.Double(400, 600, x, y);
		 g2.draw(line1);
		 g2.drawString(Integer.toString(lineLabels[0]), (int)x, (int)y-10);
	} else {
		x = leftSideX(hourLineAngles[0], 375);
		y = leftSideY(hourLineAngles[0], 375);
		Line2D line1 = new Line2D.Double(400, 600, x, y);
		g2.draw(line1);
		g2.drawString(Integer.toString(lineLabels[0]), (int)x, (int)y+12);
	}
	 
	 x = leftSideX(hourLineAngles[1], 375);
	 y = leftSideY(hourLineAngles[1], 375);
	 Line2D line2 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line2);
	 g2.drawString(Integer.toString(lineLabels[1]), (int)x, (int)y);
	 
	 x = leftSideX(hourLineAngles[2], 375);
	 y = leftSideY(hourLineAngles[2], 375);
	 Line2D line3 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line3);
	 g2.drawString(Integer.toString(lineLabels[2]), (int)x, (int)y);
	 
	 x = leftSideX(hourLineAngles[3], 375);
	 y = leftSideY(hourLineAngles[3], 375);
	 Line2D line4 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line4);
	 g2.drawString(Integer.toString(lineLabels[3]), (int)x, (int)y);
	 
	 x = leftSideX(hourLineAngles[4], 375);
	 y = leftSideY(hourLineAngles[4], 375);
	 Line2D line5 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line5);
	 g2.drawString(Integer.toString(lineLabels[4]), (int)x, (int)y);
	 
	 x = leftSideX(hourLineAngles[5], 375);
	 y = leftSideY(hourLineAngles[5], 375);
	 Line2D line6 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line6);
	 g2.drawString(Integer.toString(lineLabels[5]), (int)x, (int)y);
	 
	 x= leftSideX(hourLineAngles[6], 375);
	 y = leftSideY(hourLineAngles[6], 375);
	 Line2D line7 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line7);
	 g2.drawString(Integer.toString(lineLabels[6]), (int)x, (int)y);
	 
	 x = rightSideX(hourLineAngles[7], 375);
	 y = rightSideY(hourLineAngles[7], 375);
	 Line2D line8 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line8);
	 g2.drawString(Integer.toString(lineLabels[7]), (int)x, (int)y);
	 
	 x = rightSideX(hourLineAngles[8], 375);
	 y = rightSideY(hourLineAngles[8], 375);
	 Line2D line9 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line9);
	 g2.drawString(Integer.toString(lineLabels[8]), (int)x, (int)y);
	 
	 x = rightSideX(hourLineAngles[9], 375);
	 y = rightSideY(hourLineAngles[9], 375);
	 Line2D line10 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line10);
	 g2.drawString(Integer.toString(lineLabels[9]), (int)x, (int)y);
		 
	 x = rightSideX(hourLineAngles[10], 375);
	 y = rightSideY(hourLineAngles[10], 375);
	 Line2D line11 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line11);
	 g2.drawString(Integer.toString(lineLabels[10]), (int)x, (int)y);
		 
	 x = rightSideX(hourLineAngles[11], 375);
	 y = rightSideY(hourLineAngles[11], 375);
	 Line2D line12 = new Line2D.Double(400, 600, x, y);
	 g2.draw(line12);
	 g2.drawString(Integer.toString(lineLabels[11]), (int)x, (int)y);
		 
	 if(hourLineAngles[12] > 90.00) {
		 x = rightSideX(hourLineAngles[12], 245);
		 y = rightSideY(hourLineAngles[12], 245);
		 Line2D lines13 = new Line2D.Double(400, 600, x, y);
		 g2.draw(lines13);
		 g2.drawString(Integer.toString(lineLabels[12]), (int)x, (int)y-10);
	}else {
		x = rightSideX(hourLineAngles[12], 375);
		y = rightSideY(hourLineAngles[12], 375);
		Line2D lines13 = new Line2D.Double(400, 600, x, y);
		g2.draw(lines13);
		g2.drawString(Integer.toString(lineLabels[12]), (int)x, (int)y+12);
	}

	 //lowers the positioning of the AM and PM labels if needed
	 if(hourLineAngles[0] < -90.00 || hourLineAngles[12] > 90.00) {
		 g2.drawString("AM", 300, 675);
		 g2.drawString("PM", 500, 675);
	}else {
		g2.drawString("AM", 300, 650);
		g2.drawString("PM", 500, 650);
	}
		 
	 //checks if the user is located in the northern or southern hemisphere, and prints the direction accordingly
	 if(isNorthernHemisphere) {
		 g2.drawString("True North", (int) leftSideX(hourLineAngles[6], 375)-25, (int)leftSideY(hourLineAngles[6], 375) - 50);
	 }else {
		 g2.drawString("True South", (int) leftSideX(hourLineAngles[6], 375)-25, (int)leftSideY(hourLineAngles[6], 375) - 50);
	 }
		 
	 // setting up the gnomon triangle
	 Line2D linega = new Line2D.Double(1345, 5, 1345, 118);
	 g2.draw(linega);
	 Line2D linego = new Line2D.Double(1345, 5, 0, 2);
	 g2.draw(linego);
		 
	 x=gX(gnomonAngle, 2000);
	 y=gY(gnomonAngle, 2000);
	 Line2D linegh = new Line2D.Double(1345, 118, x, y);
	 g2.draw(linegh);
		 
	 //draws the horizontal and vertical lines for the user to cut
	 Line2D middlecut = new Line2D.Double (1500, 150, 0, 150);
	 g2.draw(middlecut);
	 Line2D verticalcut = new Line2D.Double(800, 150, 800, 800);
	 g2.draw(verticalcut);
		 
	 //listing the instructions
	 g2.drawString("Step 1: Print out this screen with the following settings:", 820, 200);
	 g2.drawString("Orientation: Landscape", 860, 215);
	 g2.drawString("All margins: 0.125 (for best result)", 860, 230);
	 g2.drawString("IMPORTANT!: Print Range: Pages 1 to 1", 860, 245);
	 g2.drawString("Step 2: Cut the 2 lines surrounding the sundial", 820, 275);
	 g2.drawString("Step 3: Cut out a triangle where the lines meet to create a gnomon", 820, 305);
	 g2.drawString("(if the lines do not meet, just cut it till the lines stop,", 860, 320);
	 g2.drawString("then cut a straight line parallel to the border)", 860, 335);
	 g2.drawString("Step 4: Attach your gnomon to the sundial on the 12 line.", 820, 365);
	 g2.drawString("Position the sundial pointing to the direction specified at the top.", 860, 380);
	 g2.drawString("Now you can use your sundial!", 860, 410);
 }	
	

 

 public static void main(String arg[]) throws PrinterException{
	 	//TESTING
	 	isNorthernHemisphere = true;
	 	
	 	//TESTING
	 	hourLineAngles[0] = -90.00;
		hourLineAngles[1] = -75.00;
		hourLineAngles[2] = -60.00;
		hourLineAngles[3] = -45.00;
		hourLineAngles[4] = -30.00;
		hourLineAngles[5] = -15.00;
		hourLineAngles[6] = 00.00;
		hourLineAngles[7] = 15.00;
		hourLineAngles[8] = 30.00;
		hourLineAngles[9] =  45.00;
		hourLineAngles[10] = 60.00;
		hourLineAngles[11] = 75.00;
		hourLineAngles[12] = 90.00;
		
		//TESTING
		lineLabels[0] = 6;
		lineLabels[1] = 7;
		lineLabels[2] = 8;
		lineLabels[3] = 9;
		lineLabels[4] = 10;
		lineLabels[5] = 11;
		lineLabels[6] = 12;
		lineLabels[7] = 1;
		lineLabels[8] = 2;
		lineLabels[9] = 3;
		lineLabels[10] = 4;
		lineLabels[11] = 5;
		lineLabels[12] = 6;
		
		//TESTING
		gnomonAngle =85.00;
	 
	 //creates a new JFrame
	 JFrame frame = new JFrame("Sundial");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(1370,770);

     SundialDrawing panel = new SundialDrawing(hourLineAngles, lineLabels, gnomonAngle);
     frame.setContentPane(panel);          
     frame.setVisible(true); 
     
     //setting up the print dialog	 
     PrinterJob pjob = PrinterJob.getPrinterJob();
     PageFormat preformat = pjob.defaultPage();
     preformat.setOrientation(PageFormat.LANDSCAPE);
     PageFormat postformat = pjob.pageDialog(preformat);
     
     //If user does not hit cancel then print.
     if (preformat != postformat) {
         //Set print component
         pjob.setPrintable(new Printer(frame), postformat);
         if (pjob.printDialog()) {
             pjob.print();
         }
     }
 }
}

//Printer class that is used to bring up the print dialog.
class Printer implements Printable {
    final Component comp;

    public Printer(Component comp){
        this.comp = comp;
    }

    @Override
    public int print(Graphics g, PageFormat format, int page_index) 
            throws PrinterException {
        if (page_index > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        // get the bounds of the component
        Dimension dim = comp.getSize();
        double cHeight = dim.getHeight();
        double cWidth = dim.getWidth();

        // get the bounds of the printable area
        double pHeight = format.getImageableHeight();
        double pWidth = format.getImageableWidth();

        double pXStart = format.getImageableX();
        double pYStart = format.getImageableY();

        double xRatio = pWidth / cWidth;
        double yRatio = pHeight / cHeight;


        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pXStart, pYStart);
        g2.scale(xRatio, yRatio);
        comp.paint(g2);

        return Printable.PAGE_EXISTS;
    }
}