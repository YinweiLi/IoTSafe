package 物联网安全技术;

import java.awt.List;
import java.nio.file.ClosedWatchServiceException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.security.auth.kerberos.KeyTab;

public class ClassicalSubstitute {

	public static String getString(){//控制台得到一个字符串
		
		String a = new String();
		Scanner in = new Scanner(System.in);
		a = in.nextLine();
		return a;
	}
	public static int getInt(){//控制台得到int
		int a = 0;
		Scanner in = new Scanner(System.in);
		a = in.nextInt();
		return a;
	}
	public static int qiuni(int k,int f){//求逆--checked
		//扩展得欧几里得算法
		int yushu = 100;
		int chushu = k;
		int beichushu = f;
		LinkedList shang = new LinkedList();//求q数组
		
		while(yushu != 1){//余数为1停止
			yushu = beichushu%chushu;
			int a = beichushu/chushu;
			shang.add(a);//商就是q加入到商得链表里
			beichushu = chushu;
			chushu = yushu;//重新赋值，进入下一轮计算
			
		}
		System.out.println(shang.toString());
		
		int[] b = new int[shang.size()+2];
		b[0] = 0;//初始化b[-1]
		b[1] = 1;//b[0]
		//后面得b计算的时候都往后挪一位
		int i;
		for( i = 2 ; i<shang.size()+2 ;i++)//先把b得内容求出来
		{
			b[i] = (-1)*b[i-1]*(int)shang.get(i-2)+b[i-2];
		}
		int ni = -1;
		int B = b[i-1];
		if( B>0 )
		ni = B%f; 
		else{
			while(B<0){
				B = B + f;
			}
			ni = B%f;
		}
		return ni;//返回结果逆
	}
	public static void Translate(){//放射加密
		
		String ClearWord;
		System.out.println("Please InPut ClearText PassWord:");
		ClearWord = getString();
		System.out.println("InPut K1:");
		int k1 = getInt();
		System.out.println("InPut K2:");
		int k2 = getInt();
				System.out.println("CipherText(y) = K1*x + K2 (mod26)_整体取模");
		char[] ClearWordC = ClearWord.toCharArray();//明文数组
		char[] CipherWordC = new char[ClearWordC.length];//密文数组
		//AWord[] ClearW = new AWord[ClearWordC.length];
		
		System.out.println("明文:");
		AWord ClearW[] = new AWord[ClearWordC.length];//构造AWord对象数组
		for(int i =0 ;i <ClearWordC.length ; i++){//AWord数组初始化
			ClearW[i] = new AWord(ClearWordC[i]);
			System.out.println(ClearW[i].toString());
		}
		
		System.out.println("密文:");
		AWord CipherW[] = new AWord[ClearWordC.length];
		for(int i =0;i<ClearW.length;i++){//加密部分
			int y = (k1*ClearW[i].No + k2)%26;//加密计算密文y
			CipherW[i] = new AWord(y);
			System.out.println(CipherW[i].toString());
		}
		
		int n = qiuni(k1,26);//求k1的逆
		
		
		System.out.println("解密->原文");
		AWord reClearW[] = new AWord[ClearWordC.length];
		for(int i =0 ; i<CipherW.length ; i++ ){//解密部分
			int x = ((CipherW[i].No-k2)*n)%26;
			if(x>=0)
			reClearW[i] = new AWord(x);
			else{
				//int xx = x;
				while(x < 0){
					x = x + 26;
				}
				reClearW[i] = new AWord(x);
			}
			System.out.println(reClearW[i].toString());
		}
		
	}
	public static boolean LExist(char c,LinkedList L){//检查c是否存在
		
		for(int i =0 ;i < L.size() ;i++){
			if(c == (char)L.get(i)){
				return true;
			}		
		}
		return false;
	} 
/*public static boolean CExist(char c,char[] C){
		
		for(int i =0 ;i < C.length ;i++){
			if(c == C[i]){
				return true;
			}	
		}
		return false;
	} */
	public static void Sample_Translate(){
		
		String ClearWord;
		System.out.println("明文ClearWord:" );
		ClearWord = getString();//得到明文
		
		String Key;
		System.out.println("密钥Key:" );
		Key = getString();//得到密文
		
		char[] ClearWordC = ClearWord.toCharArray();
		char[] KeyC = Key.toCharArray();
		
		AWord[] ClearW = new AWord[ClearWordC.length];
		for(int i = 0; i <ClearWordC.length ; i++){
			ClearW[i] = new AWord(ClearWordC[i]);
		}
		
		char[] KeyTable = new char[26];
		KeyTable = "abcdefghijklmnopqrstuvwxyz".toCharArray();//26个字母

		System.out.print("[");
		System.out.print("a"+ ",");
		for(int j = 1 ; j < KeyTable.length ; j ++)
		{
		System.out.print(" "+KeyTable[j] + ",");
		}
		System.out.println("]");
		
		LinkedList KeysL = new LinkedList();//对照表链
		for(int i = 0;i <KeyC.length;i++){
			if(!LExist(KeyC[i], KeysL)){
				KeysL.add(KeyC[i]);
			}
		}
		
		for(int i = 0 ; i < KeyTable.length ; i++){
			if(!LExist(KeyTable[i], KeysL)){
				KeysL.add(KeyTable[i]);
			}
		}
		System.out.println(KeysL.toString());//对应表构造成功
		
		AWord[] CipherW = new AWord[ClearWordC.length];
		
		for(int i = 0 ; i < ClearWordC.length ; i++){
			CipherW[i] = new AWord((char)KeysL.get(ClearW[i].No));
		System.out.println(CipherW[i].toString());	
		}//加密
		
		for(int i = 0 ; i < ClearWordC.length ; i++){
			System.out.println(KeyTable[KeysL.indexOf(CipherW[i].Text)] );
		}//解密
		
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		System.out.println("古典加密算法->仿射加密");
		//Translate();
		Sample_Translate();
	}

}
