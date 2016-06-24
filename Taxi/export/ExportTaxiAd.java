package export;

import java.io.BufferedReader;
import java.io.File;
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

public class ExportTaxiAd {
	NumberFormat nf = new DecimalFormat("0.0");

	Map dateMap = new HashMap();

	List dateList = new ArrayList();

	String str;

	public void setStr(String str) {
		this.str = str;
	}

	public void genMap() throws Exception {
		FileReader fr = new FileReader("month.txt");
		BufferedReader br = new BufferedReader(fr);

		while (br.ready()) {
			dateList.add(StringUtils.split(br.readLine(), ","));

		}

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
		FileWriter fw = new FileWriter("c:\\workspace\\Test\\excelbx\\export.txt");
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
		FileWriter fw = new FileWriter(new File("export.txt"));

		for (int j = 0; j < dateList.size(); j++) {
			Double km = genKm();
			String money = countMoney(km);
			String[] ary = (String[]) dateList.get(j);
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

		fw.flush();
		fw.close();
		System.out.println(d);
	}

	public Double genKm() {
		return Double.valueOf(nf.format(RandomUtils.nextFloat() + 9.8));
	}

	public String countMoney(double d) {
		double money = 10 + 4.5 + 3 * (d - 5) + 2.5;
		double random = (Math.random() + 2);
		return nf.format(money + random);
	}

	public String carNum(int i) throws Exception {
		Map map = readCarNum();
		Random r = new Random();
		long random = Math.round((r.nextFloat() * 10000 + 1000));
		if (random > 10000)
			random = random - 1000;

		FileWriter fw = new FileWriter(new File("c:\\workspace\\Test\\excelbx\\carNum.txt"), true);
		fw.append(String.valueOf(random) + "\n");
		fw.close();
		if (map.containsKey(String.valueOf(random)))
			this.carNum(1);

		return String.valueOf(random);
	}

	public Map readCarNum() throws Exception {
		Map map = new HashMap();
		FileReader fr = new FileReader("c:\\workspace\\Test\\excelbx\\carNum.txt");
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String str = br.readLine();
			map.put(str, str);
		}
		br.close();
		fr.close();
		return map;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int t;
		if (args.length > 0)
			t = Integer.valueOf(args[0]);
		else
			t = 2;
		ExportTaxiAd eta = new ExportTaxiAd();
		eta.setStr(ReadTemplate.readTemplate(t));
		eta.genTaxi2();
	}
}
