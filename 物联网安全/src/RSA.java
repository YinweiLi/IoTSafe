package ��������ȫ����;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class RSA {
	
	public static BigInteger qiuni(BigInteger k,BigInteger f){//����--checked
		//��չ��ŷ������㷨
		BigInteger yushu = new BigInteger("100");
		BigInteger chushu = k;
		BigInteger beichushu = f;
		LinkedList shang = new LinkedList();//��q����
		
		while(!yushu.equals(BigInteger.ONE)){//����Ϊ1ֹͣ
			yushu = beichushu.mod(chushu);
			BigInteger a = beichushu.divide(chushu);
			shang.add(a);//�̾���q���뵽�̵�������
			beichushu = chushu;
			chushu = yushu;//���¸�ֵ��������һ�ּ���
			
		}
		//System.out.println(shang.toString());
		
		BigInteger[] b = new BigInteger[shang.size()+2];
		b[0] = BigInteger.ZERO;//��ʼ��b[-1]
		b[1] = BigInteger.ONE;//b[0]
		//�����b�����ʱ������Ųһλ
		int i;
		BigInteger fuyi = new BigInteger("-1");
		for( i = 2 ; i<shang.size()+2 ;i++)//�Ȱ�b�����������
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
		return ni;//���ؽ����
	}
	public static void main(String[] args) {
		System.out.println("R-S-A");
		System.out.println("�����ڴ��������ӷֽ��������֮��");
		//�õ�����p��q���漴����������������
		Random r = new Random();
		BigInteger p = new BigInteger(100, 1000, r);
		System.out.println("p="+p);
		BigInteger q = new BigInteger(100,1000,r);
		System.out.println("q="+q);
	//p��q�ĵ�n
		BigInteger n = new BigInteger("123");
		n = p.multiply(q);
		System.out.println("n=p*q");
		System.out.println(n);
		//p-1��q-1�õ���(n)
		BigInteger fi_n = new BigInteger("123");
		fi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		System.out.println("fi_n = p-1*q-1");
		System.out.println(fi_n);
		//��1����(n)֮���ҵ�һ�����(n)���ص���
		BigInteger e = new BigInteger("199999999");
		System.out.println("e="+e);
		//Ҫ�ǲ����أ���һֱ��e��10ֱ�����أ���Կe
		while(fi_n.mod(e).equals(BigInteger.ZERO)){
				e = e.add(BigInteger.TEN);
				System.out.println("e++"+e);
		}
		//e����λd�����Ǧ�(n)��˽Կd
		BigInteger d = qiuni(e,fi_n);
		System.out.println("d="+d);
		//��������
		System.out.println("����:");
		String m ;
		Scanner in = new Scanner(System.in);
		m = in.nextLine();
		BigInteger M = new BigInteger(m);
		//���ܣ��ù�Կe��n����
		BigInteger C = new BigInteger("0");
		C = M.modPow(e, n);
		System.out.println("����:"+C);
		//���ܣ���˽Կd��n����
		BigInteger RM = new BigInteger("0");
		RM = C.modPow(d, n);
	
		System.out.println("����:"+RM);
	}
}
