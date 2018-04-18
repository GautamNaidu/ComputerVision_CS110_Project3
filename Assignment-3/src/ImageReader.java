import java.awt.Color;
import java.util.Scanner;

public class ImageReader {

	static ImageUtils utils;

	public static void main(String[] args) 
	{
		utils = new ImageUtils();		
		Color[][] orig = utils.loadImage("src/LennaCV.png"); //Load image into orig object.
		utils.addImage(orig, "Orginial image."); //When displaying the original image, call it "Original Image."
		Color[][] mod = process(orig); //Calls method "process" to run the Gaussian Blur.
		utils.addImage(mod, "Processed image."); //When displaying the image, call it "Processed Image".
		utils.display(); //Display Images.
	}

	public static Color[][] process(Color[][] img) //Method that runs the Gaussian Blur.
	{			
		int averageRed = 0; //This variable's value is replaced for every iteration of the loop.
		int averageGreen = 0; //This variable's value is replaced for every iteration of the loop.
		int averageBlue = 0; //This variable's value is replaced for every iteration of the loop.


		Color[][] tmp = ImageUtils.cloneArray(img); //Takes the image provided from the method input and clones it to a 2D array.

		//Finds the average for Red Value. From 0 to 255.
		for (int i=0; i<tmp.length; i++) //Iterates by row.
		{
			for (int j=0; j<tmp[i].length;j++) // Iterates by column.
			{
				int r0 = tmp[i][j].getRed(); //Gets red value for pixel.
				int g0 = tmp[i][j].getGreen();//Gets green value for pixel.
				int b0 = tmp[i][j].getBlue(); //Gets blue value for pixel.

				if (i>250&&j>250&&i<350&&j<350) //USER CAN CHANGE THIS CONDITION TO ADJUST WHAT GETS BLURRED AND WHAT NOT.
				{
					break; //Break is written here to leave the pixels that meet the condition above alone.
				}
				else //Gaussian Blur code is written below.
				{
					int count = 0; //This counts the number of pixels that meet the if condition below, when calculating the average.
					int average = 0; //a temporary average variable that sums up the values.
					for (int r=-1; r<2; r++) //This iterates through the surrounding rows.
					{
						for (int c=-1; c<2; c++) //This iterates through the surrounding columns.
						{
							if ((i+r)>= 0 && (i+r)<=(tmp.length-1)&&(j+c)>=0&&(j+c)<=(tmp[i].length-1)) //Makes sure that anything that is being called is within the bounds of the image array.
							{	
								count++;
								average += tmp[i+r][j+c].getRed(); //
								averageRed = average/count;
							}
						}
					}
					for (int r=-1; r<2; r++) //This iterates through the surrounding row pixels.
					{
						for (int c=-1; c<2; c++) //This iterates through the surrounding column pixels.
						{
							if ((i+r)>= 0 && (i+r)<=(tmp.length-1)&&(j+c)>=0&&(j+c)<=(tmp.length-1)) //Same if condition above (checks to make sure everything is rationale)
							{
								int g = tmp[i+r][j+c].getGreen();
								int b = tmp[i+r][j+c].getBlue();
								tmp[i+r][j+c]= new Color(averageRed,g,b);	//This actually changes the pixel values for the pixel and the surrounding pixels.
							}
						}
					}
				}
			}
		}
		
		//Finds the average for Green Value. From 0 to 255. The code below is the same as the section above, just for green values.
		for (int z=0; z<tmp.length; z++) 
		{
			for (int j=0; j<tmp[z].length;j++)
			{
				int r0 = tmp[z][j].getRed();
				int g0 = tmp[z][j].getGreen();
				int b0 = tmp[z][j].getBlue();

				if (z>250&&j>250&&z<350&&j<350)
				{
					break;
				}
				else 
				{
					int count = 0;
					int average = 0;
					for (int r=-1; r<2; r++)
					{
						for (int c=-1; c<2; c++)
						{
							if ((z+r)>= 0 && (z+r)<=(tmp.length-1)&&(j+c)>=0&&(j+c)<=(tmp[z].length-1))
							{	
								count++;
								average += tmp[z+r][j+c].getGreen();
								averageGreen = average/count;
							}
						}
					}
					for (int r=-1; r<2; r++)
					{
						for (int c=-1; c<2; c++)
						{
							if ((z+r)>= 0 && (z+r)<=(tmp.length-1)&&(j+c)>=0&&(j+c)<=(tmp.length-1)) 
							{
								int r1 = tmp[z+r][j+c].getRed();
								int b = tmp[z+r][j+c].getBlue();
								tmp[z+r][j+c]= new Color(r1,averageGreen,b);	
							}
						}
					}
				}
			}
		}

		//Finds the average for Blue Value. From 0 to 255. The code below is the same as the section above, just for blue values.
		for (int m=0; m<tmp.length; m++) 
		{
			for (int j=0; j<tmp[m].length;j++)
			{
				int r0 = tmp[m][j].getRed();
				int g0 = tmp[m][j].getGreen();
				int b0 = tmp[m][j].getBlue();

				if (m>250&&j>250&&m<350&&j<350)
				{
					break;
				}
				else {
					int count = 0;
					int average = 0;
					for (int r=-1; r<2; r++)
					{
						for (int c=-1; c<2; c++)
						{
							if ((m+r)>= 0 && (m+r)<=(tmp.length-1)&&(j+c)>=0&&(j+c)<=(tmp[m].length-1))
							{	
								count++;
								average += tmp[m+r][j+c].getBlue();
								averageBlue = average/count;
							}
						}
					}
					for (int r=-1; r<2; r++)
					{
						for (int c=-1; c<2; c++)
						{
							if ((m+r)>= 0 && (m+r)<=(tmp.length-1)&&(j+c)>=0&&(j+c)<=(tmp.length-1)) 
							{
								int g = tmp[m+r][j+c].getGreen();
								int r1 = tmp[m+r][j+c].getRed();
								tmp[m+r][j+c]= new Color(r1,g,averageBlue);	
							}
						}
					}
				}
			}
		}
				
			
		

		return tmp; //Returns the processed image.

	}
}


