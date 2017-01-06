package 物联网安全技术;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class RSA {
	
	public static BigInteger qiuni(BigInteger k,BigInteger f){//求逆--checked
		//扩展得欧几里得算法
		BigInteger yushu = new BigInteger("100");
		BigInteger chushu = k;
		BigInteger beichushu = f;
		LinkedList shang = new LinkedList();//求q数组
		
		while(!yushu.equals(BigInteger.ONE)){//余数为1停止
			yushu = beichushu.mod(chushu);
			BigInteger a = beichushu.divide(chushu);
			shang.add(a);//商就是q加入到商得链表里
			beichushu = chushu;
			chushu = yushu;//重新赋值，进入下一轮计算
			
		}
		//System.out.println(shang.toString());
		
		BigInteger[] b = new BigInteger[shang.size()+2];
		b[0] = BigInteger.ZERO;//初始化b[-1]
		b[1] = BigInteger.ONE;//b[0]
		//后面得b计算的时候都往后挪一位
		int i;
		BigInteger fuyi = new BigInteger("-1");
		for( i = 2 ; i<shang.size()+2 ;i++)//先把b得内容求出来
		{
			//b[i] = (-1)*b[i-1]*(int)shang.get(i-2)+b[i-2];
			b[i] = fuyi.multiply(b[i-1].multiply((BigInteger)shang.get(i-2))).add(b[i-2]);
		
		}
		BigInteger ni = new BigInteger("-1");
		BigInteger B = b[i-1];
		if( B.compareTo(BigInteger.ZERO) >  0)
		ni = B.mod(f); 
		else{
			while(B.compareTo(BigInteger.ZERO) < 0){
				B = B.add(f);
			}
			ni = B.remainder(f);
		}
		return ni;//返回结果逆
	}
	public static void main(String[] args) {
		System.out.println("R-S-A");
		System.out.println("建立在大整数因子分解的困难性之上");
		//得到大叔p和q，随即产生的两个大素数
		Random r = new Random();
		BigInteger p = new BigInteger(100, 1000, r);
		System.out.println("p="+p);
		BigInteger q = new BigInteger(100,1000,r);
		System.out.println("q="+q);
	//p乘q的到n
		BigInteger n = new BigInteger("123");
		n = p.multiply(q);
		System.out.println("n=p*q");
		System.out.println(n);
		//p-1乘q-1得到φ(n)
		BigInteger fi_n = new BigInteger("123");
		fi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		System.out.println("fi_n = p-1*q-1");
		System.out.println(fi_n);
		//在1到φ(n)之间找到一个与φ(n)互素的数
		BigInteger e = new BigInteger("199999999");
		System.out.println("e="+e);
		//要是不互素，就一直给e＋10直到互素，公钥e
		while(fi_n.mod(e).equals(BigInteger.ZERO)){
				e = e.add(BigInteger.TEN);
				System.out.println("e++"+e);
		}
		//e的逆位d，摸是φ(n)，私钥d
		BigInteger d = qiuni(e,fi_n);
		System.out.println("d="+d);
		//输入明文
		System.out.println("明文:");
		String m ;
		Scanner in = new Scanner(System.in);
		m = in.nextLine();
		BigInteger M = new BigInteger(m);
		//加密，用公钥e和n加密
		BigInteger C = new BigInteger("0");
		C = M.modPow(e, n);
		System.out.println("加密:"+C);
		//解密，用私钥d和n解密
		BigInteger RM = new BigInteger("0");
		RM = C.modPow(d, n);
	
		System.out.println("解密:"+RM);
	}
}
