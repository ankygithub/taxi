package export;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class TaxiTxt {

	public static void analyze(File file, List dataList) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String taxistr = "";
		int i = 0;
		while (br.ready()) {
			String str = StringUtils.trim(br.readLine());
			if (str.startsWith("2016")) {
				taxistr = str.replace("年", "-").replace("月", "-").replace("日", "");
				i = 0;
			}
			if (StringUtils.isEmpty(str.trim()))
				i = 0;

			if (i == 2) {
				taxistr = taxistr + "\t" + str;

			}
			if (i == 6) {
				dataList.add(taxistr + "\t" + str.replace("元", ""));
				i = 0;
			}
			i++;
		}
	}

	public static void printTaxi(String str) {
		String[] ary = StringUtils.split(str);
		Date date = objectConvDate(ary[0], "yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(cal.DAY_OF_WEEK) == cal.SUNDAY || cal.get(cal.DAY_OF_WEEK) == cal.SATURDAY) {
			if (ary[1].startsWith("09")) {
				System.out.println(ary[0] + "	三叉街新村	联通公司	10	" + ary[2] + "	周末加班");
			} else {
				System.out.println(ary[0] + "	联通公司	三叉街新村	10	" + ary[2] + "	周末加班");
			}
		} else {
			System.out.println(ary[0] + "	联通公司	三叉街新村	10	" + ary[2] + "	加班回家");
		}
	}

	public static Date objectConvDate(Object date, String format) {
		Date dateA = new Date();
		if (date instanceof String) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				dateA = sdf.parse((String) date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (date instanceof Date) {
			dateA = (Date) date;
		} else if (date instanceof GregorianCalendar) {
			dateA = ((GregorianCalendar) date).getTime();
		} else {
			throw new java.lang.IllegalArgumentException("无效的参数");
		}
		return dateA;
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = new File("c:\\workspace\\Taxi\\新建文件夹");
		String[] ext = { "txt" };
		List list = (List) FileUtils.listFiles(file, ext, true);
		List<String> dataList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			File f = (File) list.get(i);
			analyze(f, dataList);
		}
		Collections.sort(dataList);
		for (String str : dataList) {
			printTaxi(str);
		}
	}

}
