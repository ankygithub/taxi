package export;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

public class ExportTaix {
	NumberFormat nf = new DecimalFormat("0.0");

	Map dateMap = new HashMap();

	List dateList = new ArrayList();

	int modI = 4;

	List<String[]> dataList1 = new ArrayList();

	List<String[]> dataList2 = new ArrayList();

	List<String[]> dataList3 = new ArrayList();

	List<String[]> dataList0 = new ArrayList();

	List<String[]>[] listAry = new ArrayList[modI];

	String str = "       83988066\n" 
				+ "           华威\n" 
				+ "       83498066\n" 
				+ "       闽AT#carNum#\n" 
				+ "         #certNum#\n" 
				+ "    #date#\n" 
				+ "          #startTime#\n" 
				+ "          #endTime#\n"
				+ "          2.25元\n" 
				+ "       #km#公里\n" 
				+ "       00:#waitTime#\n" 
				+ "         #money#元\n";

	public void genMap() throws Exception {
		FileReader fr = new FileReader("D:\\workspace\\Test\\excelbx\\month.txt");
		BufferedReader br = new BufferedReader(fr);
		int i = 1;
		while (br.ready()) {
			// dateMap.put(i++, StringUtils.split(br.readLine(), ","));
			if (i % modI == 1) {
				dataList1.add(StringUtils.split(br.readLine(), ","));
			}
			if (i % modI == 2) {
				dataList2.add(StringUtils.split(br.readLine(), ","));
			}
			if (i % modI == 3) {
				dataList3.add(StringUtils.split(br.readLine(), ","));
			}
			if (i % modI == 0) {
				dataList0.add(StringUtils.split(br.readLine(), ","));
			}
			i++;
		}
		listAry[0] = dataList0;
		listAry[1] = dataList1;
		listAry[2] = dataList2;
		listAry[3] = dataList3;
	}

	public String certNum() {
		return String.valueOf("0" + (Math.round(Math.random() * 50000 + 10000)));
	}

	public String waitTime() {
		String str = "";
		long t1 = Math.round(Math.random() * 59);
		String s1;
		if (t1 < 10)
			s1 = "0" + t1;
		else
			s1 = String.valueOf(t1);
		str = "0" + Math.round(Math.random() * 9) + ":" + s1;
		return str;
	}

	public void genTaxi() throws Exception {
		genMap();
		Set set = dateMap.entrySet();
		int i = 0;
		double d = 0.0;
		FileWriter fw = new FileWriter("D:\\workspace\\Test\\excelbx\\export.txt");
		for (Iterator iter = set.iterator(); iter.hasNext();) {
			Map.Entry element = (Map.Entry) iter.next();
			Double km = genKm();
			String money = countMoney(km);
			String[] ary = (String[]) element.getValue();
			String newStr = new String(str);
			newStr = StringUtils.replace(newStr, "#carNum#", carNum(i++));
			newStr = StringUtils.replace(newStr, "#date#", ary[0]);
			newStr = StringUtils.replace(newStr, "#startTime#", ary[1]);
			newStr = StringUtils.replace(newStr, "#endTime#", ary[2]);
			newStr = StringUtils.replace(newStr, "#km#", km.toString());
			newStr = StringUtils.replace(newStr, "#waitTime#", waitTime());
			newStr = StringUtils.replace(newStr, "#certNum#", certNum());

			newStr = StringUtils.replace(newStr, "#money#", money + "0");
			d = d + new Double(money);
			System.out.println(newStr);
			fw.write(newStr);
			for (int j = 0; j < 17; j++) {
				fw.write("\n");
			}

		}
		fw.flush();
		fw.close();
		System.out.println(d);
	}

	public void genTaxi2() throws Exception {
		genMap();

		int i = 0;
		double d = 0.0;
		FileWriter fw = new FileWriter("D:\\workspace\\Test\\excelbx\\export.txt");
		for (int ii = 0; ii < listAry.length; ii++) {
			List<String[]> list = listAry[ii];
			for (int j = 0; j < list.size(); j++) {
				Double km = genKm();
				String money = countMoney(km);
				String[] ary = (String[]) list.get(j);
				String newStr = new String(str);
				newStr = StringUtils.replace(newStr, "#carNum#", carNum(i++));
				newStr = StringUtils.replace(newStr, "#date#", ary[0]);
				newStr = StringUtils.replace(newStr, "#startTime#", ary[1]);
				newStr = StringUtils.replace(newStr, "#endTime#", ary[2]);
				newStr = StringUtils.replace(newStr, "#km#", km.toString());
				newStr = StringUtils.replace(newStr, "#waitTime#", waitTime());
				newStr = StringUtils.replace(newStr, "#certNum#", certNum());
				newStr = StringUtils.replace(newStr, "#money#", money + "0");
				d = d + new Double(money);
				System.out.println(newStr);
				fw.write(newStr);
				for (int k = 0; k < 17; k++) {
					fw.write("\n");
				}
			}
			fw.write("----------------------------\n");
		}
		fw.flush();
		fw.close();
		System.out.println(d);
	}

	public Double genKm() {
		return Double.valueOf(nf.format(RandomUtils.nextFloat() + 9));
	}

	public String countMoney(double d) {
		double money = 8 + 3 + 2.25 * (d - 5) + 2.5;
		double random = (Math.random() + 2);
		return nf.format(money + random);
	}

	public String carNum(int i) {
		Random r = new Random();
		long random = Math.round((r.nextFloat() * 10000 + 1000));
		if (random > 10000)
			random = random - 1000;
		return String.valueOf(random);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExportTaix et = new ExportTaix();

		try {
			et.genTaxi2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
