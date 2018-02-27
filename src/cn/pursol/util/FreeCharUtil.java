package cn.pursol.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;


public class FreeCharUtil  {
	
	/**
	 * 获取饼状图
	 * @param request
	 * @param title
	 * @param titlefu
	 * @param dataset
	 * @return
	 */
	public static String getPieChartImage(HttpServletRequest request,String title,String titlefu,int width,int height,DefaultPieDataset dataset){
		
		StandardChartTheme standardChartTheme=new StandardChartTheme("CN");     //设置标题字体     
		standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));     //设置图例的字体     
		standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));     //设置轴向的字体     
		standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));     //应用主题样式     
		ChartFactory.setChartTheme(standardChartTheme); 
		
		JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);  
		chart.addSubtitle(new TextTitle(titlefu)); 
		
		PiePlot pieplot = (PiePlot) chart.getPlot();  
		pieplot.setLabelFont(new Font("宋体", 0, 11));  
		StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());//{0}:({1},{2})  
		pieplot.setLabelGenerator(standarPieIG);  
		 
		//没有数据的时候显示的内容   
		pieplot.setNoDataMessage("无数据显示");  
		pieplot.setLabelGap(0.02D);  
		  
		PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot();  
		//设置开始角度   
		pieplot3d.setStartAngle(120D);  
		//设置方向为”顺时针方向“   
		pieplot3d.setDirection(Rotation.CLOCKWISE);  
		//设置透明度，0.5F为半透明，1为不透明，0为全透明   
		pieplot3d.setForegroundAlpha(1F);  
		  
		String filename = "";
		try {
			HttpSession session = request.getSession();
			filename = ServletUtilities.saveChartAsPNG(chart, width, height, null, session);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
		
		return graphURL;
	}
	
	/**
	 * 获取饼状图
	 * @param request
	 * @param title
	 * @param titlefu
	 * @param dataset
	 * @return
	 */
	public static String getLineChartImage(HttpServletRequest request,String title,String xName,String yName,int width,int height,CategoryDataset dataset){
		
		JFreeChart chart = ChartFactory.createLineChart(title, // chart title
				xName, // domain axis label
				yName, // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips
				false // urls
				);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		// 获取绘图区对象
		plot.setBackgroundPaint(Color.LIGHT_GRAY); // 设置绘图区背景色
		plot.setRangeGridlinePaint(Color.WHITE); // 设置水平方向背景线颜色
		plot.setRangeGridlinesVisible(true);// 设置是否显示水平方向背景线,默认值为true
		plot.setDomainGridlinePaint(Color.WHITE); // 设置垂直方向背景线颜色
		plot.setDomainGridlinesVisible(true); // 设置是否显示垂直方向背景线,默认值为false
				
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(new Font("黑体", Font.PLAIN, 12)); // 设置横轴字体
		domainAxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 12));// 设置坐标轴标尺值字体
		domainAxis.setLowerMargin(0.01);// 左边距 边框距离
		domainAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。
		domainAxis.setMaximumCategoryLabelLines(2);
		
		ValueAxis numberaxis = plot.getRangeAxis();
		numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());// Y轴显示整数
		numberaxis.setAutoRangeMinimumSize(1); // 最小跨度
		numberaxis.setUpperMargin(0.18);// 上边距,防止最大的一个数据靠近了坐标轴。
		numberaxis.setLowerBound(0); // 最小值显示0
		numberaxis.setAutoRange(false); // 不自动分配Y轴数据
		numberaxis.setTickMarkStroke(new BasicStroke(1.6f)); // 设置坐标标记大小
		numberaxis.setTickMarkPaint(Color.BLACK); // 设置坐标标记颜色
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));// 设置Y 轴坐标上的文字
		
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 12));// 设置标题文字
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));// 设置图例类别字体
		
		CategoryPlot line = chart.getCategoryPlot();
		// customise the range axis...
		NumberAxis rangeAxis = (NumberAxis) line.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);
		rangeAxis.setUpperMargin(0.20);
		rangeAxis.setLabelAngle(Math.PI / 2.0);
		line.setRangeAxis(rangeAxis);
		String filename = "";
		try {
			filename = ServletUtilities.saveChartAsPNG(chart, width, height, null,request.getSession());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" + filename;
		
		return graphURL;
	}

}
