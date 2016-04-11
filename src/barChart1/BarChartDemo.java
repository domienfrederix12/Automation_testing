package barChart1;

/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * ------------------
 * BarChartDemo7.java
 * ------------------
 * (C) Copyright 2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: BarChartDemo7.java,v 1.13 2004/05/19 09:57:51 mungady Exp $
 *
 * Changes
 * -------
 * 27-Jan-2004 : Version 1, based on BarChartDemo.java (DG);
 *
 */



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.text.TextUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Log;
import org.jfree.util.PrintStreamLogTarget;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * A simple demonstration application showing how to create a bar chart with a custom item
 * label generator.
 */
public class BarChartDemo extends ApplicationFrame {

	  /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public BarChartDemo(String title) {

        super(title);
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart, false);
        chartPanel.setPreferredSize(new Dimension(1300, 1000));
        setContentPane(chartPanel);

    }

    /**
     * Returns a sample dataset.
     * 
     * @return The dataset.
     */
    private static CategoryDataset createDataset() {
        
        // row keys...
        String series1 = "Belgium";
        String series2 = "Italy";
        String series3 = "Ireland";
        String series4 = "Sweden";

        // column keys...
        String category1 = "Matches";
        String category2 = "Wins";
        String category3 = "Draws";
        String category4 = "Losses";
     //   String category5 = "Category 5";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, series1, category1);
        dataset.addValue(4.0, series1, category2);
        dataset.addValue(3.0, series1, category3);
        dataset.addValue(5.0, series1, category4);
      //  dataset.addValue(5.0, series1, category5);

        dataset.addValue(5.0, series2, category1);
        dataset.addValue(7.0, series2, category2);
        dataset.addValue(6.0, series2, category3);
        dataset.addValue(8.0, series2, category4);
   //     dataset.addValue(4.0, series2, category5);

        dataset.addValue(4.0, series3, category1);
        dataset.addValue(3.0, series3, category2);
        dataset.addValue(2.0, series3, category3);
        dataset.addValue(3.0, series3, category4);
    //    dataset.addValue(6.0, series3, category5);
        
        return dataset;
        
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private static JFreeChart createChart(CategoryDataset dataset) {
        
        // create the chart...
        JFreeChart chart = ChartFactory.createBarChart(
            "Belgium vs Italy vs Ireland ",         // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, new Color(0, 0, 64)
        );
        GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, new Color(0, 64, 0)
        );
        GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, new Color(64, 0, 0)
        );
        
        GradientPaint gp3 = new GradientPaint(
                0.0f, 0.0f, Color.yellow, 
                0.0f, 0.0f, new Color(64, 0, 0)
            );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        renderer.setSeriesPaint(3, gp2);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }

    private static WebDriverWait wait;
    
    private static WebDriver driver;
    
    private static String statistics;
    
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {



        
     // Demo to buy tickets to the EK 2016
		
        System.out.println("Hello UEFA 2016 !!!");
   		
        statistics = "";
        
        JOptionPane.showMessageDialog(null, "Belgium versus Italy demo start");
        
        
        driver = new FirefoxDriver();
   	    wait = new WebDriverWait(driver, 500);
   	 
   		//Open Home Page
   	    driver.manage().window().maximize();
        driver.get("http://www.uefa.com/");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/header/nav/div/div/ul/li/a[text() = 'UEFA EURO 2016']")));
        
        
        driver.findElement(By.xpath("//div/header/nav/div/div/ul/li/a[text() = 'UEFA EURO 2016']")).click();
       
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div//nav/div/div/ul/li/a[ text() = 'Teams']")));
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div//nav/div/div/ul/li/a[ text() = 'Matches']"))).click();
   	
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div//div//div/a[text() = 'Select your team and follow their EURO journey']")));
        
        driver.findElement(By.xpath("//div/div//div//div/a[text() = 'Select your team and follow their EURO journey']")).click();
      
        wait2Secs();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div//ul/li/span[text()='Belgium']")));
        
        driver.findElement(By.xpath("//div/div/div//ul/li/span[text()='Belgium']")).click();
        
            
        // Belgium
        wait2Secs();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div/table[1]/tbody/tr/td/div/a[text()='21.00'])[1]")));
        
        
        driver.findElement(By.xpath("(//div/table[1]/tbody/tr/td/div/a[text()='21.00'])[1]")).click();
        
        wait2Secs();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/span[text()='Pre-match']")));
       
        
        
        // Go to the statistics
        
        driver.get("http://www.uefa.com/uefaeuro/season=2016/statistics/index.html");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a/span[text()='Teams']")));
        
        driver.findElement(By.xpath("//a/span[text()='Teams']")).click();
        
        
        
        
        // Look up the statistics for Belgium and Italy
          
        getOverviewStats("BE","ITA","Belgium","Italy");
        
        
        
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/ul/li/a[text()='Goals']")));
        
        driver.findElement(By.xpath("//div/div/div/ul/li/a[text()='Goals']")).click();
        
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/input[@value='Search for a team']")));
        
        driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).sendKeys("Belgium");;
        
        
        wait2Secs();
       
       // getGoalsStats("BE","ITA","Belgium","Italy");
       
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/ul/li/a[text()='Attempts']")));
        
        driver.findElement(By.xpath("//div/div/div/ul/li/a[text()='Attempts']")).click();
        
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/input[@value='Search for a team']")));
        
        driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).sendKeys("Belgium");;
        
        
        
        wait2Secs();
        
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/ul/li/a[text()='Passes']")));
        
        driver.findElement(By.xpath("//div/div/div/ul/li/a[text()='Passes']")).click();
        
     
        
        wait2Secs();
        
        
        // Italy
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/input[@value='Search for a team']")));
        
        driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).sendKeys("Italy");;
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/ul/li/a[text()='Overview']")));
        
        driver.findElement(By.xpath("//div/div/div/ul/li/a[text()='Overview']")).click();
        
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/input[@value='Search for a team']")));
        
        driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).sendKeys("Italy");;
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/ul/li/a[text()='Goals']")));
        
        driver.findElement(By.xpath("//div/div/div/ul/li/a[text()='Goals']")).click();
        
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/input[@value='Search for a team']")));
        
        driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).sendKeys("Italy");;
          
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/ul/li/a[text()='Attempts']")));
        
        driver.findElement(By.xpath("//div/div/div/ul/li/a[text()='Attempts']")).click();
        
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/input[@value='Search for a team']")));
        
        driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).sendKeys("Italy");;
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/ul/li/a[text()='Passes']")));
        
        driver.findElement(By.xpath("//div/div/div/ul/li/a[text()='Passes']")).click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/input[@value='Search for a team']")));
        
        driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).sendKeys("Italy");;
     
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        
        
        JOptionPane.showMessageDialog(null, "Belgium versus Italy : \n" + statistics);
        
        BarChartDemo demo = new BarChartDemo("Belgium vs Italy");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        
        
   	}
   	
   	/*
   	 * A method to get the overview statistics
   	 * 
   	 */
   	
   	public static String getOverviewStats(String country, String secondcountry, String countryName, String secondCountryName){
   		
   		 
   		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/ul/li/a[text()='Overview']")));
   	     
   	     driver.findElement(By.xpath("//div/div/div/ul/li/a[text()='Overview']")).click();
   	       
   	     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/input[@value='Search for a team']")));
   	     
   	     driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).sendKeys(countryName);;
   	   
   	     wait2Secs();
   		
   		
   		 String matchesPlayed1 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[2]")).getText();
   	
   		 String wins1 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[3]")).getText();
   		 
   		 String draws1 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[4]")).getText();
   		   
   		 String losses1 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[5]")).getText();
   		 
   		 
   		 driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).clear();
   		 
   		 wait2Secs();
   		 
   		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/input[@value='Search for a team']")));
   	     
   	     driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).sendKeys(secondCountryName);;
   	     
   	     
   	     wait2Secs();
   	     
   	     // Italy
   	     
   	     String matchesPlayed2 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[2]")).getText();
   	 	
   		 String wins2 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[3]")).getText();
   		 
   		 String draws2 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[4]")).getText();
   		   
   		 String losses2 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[5]")).getText();
   		 
   		 
   		driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).clear();
  		 
  		 wait2Secs();
  		 
  		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/input[@value='Search for a team']")));
  	     
  	     driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).sendKeys(secondCountryName);;
  	     
  	     
  	     wait2Secs();
  	     
  	     // Italy
  	     
  	     String matchesPlayed2 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[2]")).getText();
  	 	
  		 String wins2 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[3]")).getText();
  		 
  		 String draws2 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[4]")).getText();
  		   
  		 String losses2 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[5]")).getText();
  		 
  		 
  		driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).clear();
  		 
  		 wait2Secs();
  		 
  		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/div/div/div/input[@value='Search for a team']")));
  	     
  	     driver.findElement(By.xpath("//div/div/div/div/input[@value='Search for a team']")).sendKeys(secondCountryName);;
  	     
  	     
  	     wait2Secs();
  	     
  	     // Italy
  	     
  	     String matchesPlayed2 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[2]")).getText();
  	 	
  		 String wins2 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[3]")).getText();
  		 
  		 String draws2 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[4]")).getText();
  		   
  		 String losses2 = driver.findElement(By.xpath("//tbody/tr[@class='off odd']/td[5]")).getText();
   		 
   	     	
   	     return statistics;
   	}
   	
   	
   	public static String getGoalsStats(String country){
   		
   		
   		 String totalGoalsBE = driver.findElement(By.xpath("//tbody/tr[@class='on odd']/td[2]")).getText();
   	     
   		 statistics = statistics + "Total goals    "+country+": "+ totalGoalsBE + "\n" ;
   	     
   	     String totalCounterBE = driver.findElement(By.xpath("//tbody/tr[@class='on odd']/td[4]")).getText();
   	     
   	     statistics = statistics + "Total goals  "+ country +": "+ totalCounterBE + "\n" ; 
   	     
   	     return statistics;
   	
   	}
   	
   	
   	public static void wait2Secs(){
   		
   		
   		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
   		
   		
   	}

}