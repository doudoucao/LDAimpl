package edu.iiip.preprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;




import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.common.Term;

public class SegmentDemo {

	public static final String stopWordTable = "./data/停用词.txt";
	Writer writer;
	Set<String> stopWordSet; // 停用词集

	public SegmentDemo() {
		writer = new Writer();
		stopWordSet = new HashSet<String>();
		// 读取停用词

	}

	public void test() {

		// List<Term> termList = StandardTokenizer.segment("商品和服务");
		// System.out.println(termList);
		HanLP.Config.ShowTermNature = false; // 关闭词性显示
		Segment segment = new CRFSegment();
		String[] sentenceArray = new String[] {
				"HanLP是由一系列模型与算法组成的Java工具包，目标是普及自然语言处理在生产环境中的应用。",
				"鐵桿部隊憤怒情緒集結 馬英九腹背受敵", // 繁体无压力
				"馬英九回應連勝文“丐幫說”：稱黨內同志談話應謹慎",
				"高锰酸钾，强氧化剂，紫红色晶体，可溶于水，遇乙醇即被还原。常用作消毒剂、水净化剂、氧化剂、漂白剂、毒气吸收剂、二氧化碳精制剂等。", // 专业名词有一定辨识能力
				"《夜晚的骰子》通过描述浅草的舞女在暗夜中扔骰子的情景,寄托了作者对庶民生活区的情感", // 非新闻语料
				"这个像是真的[委屈]前面那个打扮太江户了，一点不上品...@hankcs", // 微博
				"鼎泰丰的小笼一点味道也没有...每样都淡淡的...淡淡的，哪有食堂2A的好次",
				"克里斯蒂娜·克罗尔说：不，我不是虎妈。我全家都热爱音乐，我也鼓励他们这么做。",
				"今日APPS：Sago Mini Toolbox培养孩子动手能力", "财政部副部长王保安调任国家统计局党组书记",
				"2.34米男子娶1.53米女粉丝 称夫妻生活没问题", "你看过穆赫兰道吗", "乐视超级手机能否承载贾布斯的生态梦" };
		for (String sentence : sentenceArray) {
			List<Term> termList = segment.seg(sentence);
			// 1.去除停用词 标点等
			String result = stopword(termList.toString());
			System.out.println(result);
			writer.append(result);
		}
		writer.close();
	}

	public String stopword(String text) {
		// 去除停用词
		BufferedReader stopWordFileBr = null;
		try {
			stopWordFileBr = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(stopWordTable)), "UTF-8"));
			
			String stopword = null;
			while ((stopword = stopWordFileBr.readLine()) != null) {
				stopWordSet.add(stopword);
			}
			
			String spiltResult = text.substring(text.indexOf('[')+1, text.lastIndexOf(']'));
			String[] arrayResult = spiltResult.split(",");
			
			for(int i=0;i<arrayResult.length;i++){
				if(stopWordSet.contains(arrayResult[i])){
					arrayResult[i] = "";
				}
			}
			stopWordFileBr.close();
			StringBuffer result = new StringBuffer();
			for(int i=0;i<arrayResult.length;i++){
			   result.append(arrayResult[i]);
			}
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void test2() {
		Iterator it = stopWordSet.iterator();
		while(it.hasNext()){
			String str = (String) it.next();
			System.out.println(str);
		}
	}

	public static void main(String[] args) {
		SegmentDemo sd = new SegmentDemo();
		sd.test();
		sd.test2();
	}
}
