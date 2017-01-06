package ��������ȫ����;

import java.awt.List;
import java.nio.file.ClosedWatchServiceException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.security.auth.kerberos.KeyTab;

public class ClassicalSubstitute {

	public static String getString(){//����̨�õ�һ���ַ���
		
		String a = new String();
		Scanner in = new Scanner(System.in);
		a = in.nextLine();
		return a;
	}
	public static int getInt(){//����̨�õ�int
		int a = 0;
		Scanner in = new Scanner(System.in);
		a = in.nextInt();
		return a;
	}
	public static int qiuni(int k,int f){//����--checked
		//��չ��ŷ������㷨
		int yushu = 100;
		int chushu = k;
		int beichushu = f;
		LinkedList shang = new LinkedList();//��q����
		
		while(yushu != 1){//����Ϊ1ֹͣ
			yushu = beichushu%chushu;
			int a = beichushu/chushu;
			shang.add(a);//�̾���q���뵽�̵�������
			beichushu = chushu;
			chushu = yushu;//���¸�ֵ��������һ�ּ���
			
		}
		System.out.println(shang.toString());
		
		int[] b = new int[shang.size()+2];
		b[0] = 0;//��ʼ��b[-1]
		b[1] = 1;//b[0]
		//�����b�����ʱ������Ųһλ
		int i;
		for( i = 2 ; i<shang.size()+2 ;i++)//�Ȱ�b�����������
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
		return ni;//���ؽ����
	}
	public static void Translate(){//�������
		
		String ClearWord;
		System.out.println("Please InPut ClearText PassWord:");
		ClearWord = getString();
		System.out.println("InPut K1:");
		int k1 = getInt();
		System.out.println("InPut K2:");
		int k2 = getInt();
				System.out.println("CipherText(y) = K1*x + K2 (mod26)_����ȡģ");
		char[] ClearWordC = ClearWord.toCharArray();//��������
		char[] CipherWordC = new char[ClearWordC.length];//��������
		//AWord[] ClearW = new AWord[ClearWordC.length];
		
		System.out.println("����:");
		AWord ClearW[] = new AWord[ClearWordC.length];//����AWord��������
		for(int i =0 ;i <ClearWordC.length ; i++){//AWord�����ʼ��
			ClearW[i] = new AWord(ClearWordC[i]);
			System.out.println(ClearW[i].toString());
		}
		
		System.out.println("����:");
		AWord CipherW[] = new AWord[ClearWordC.length];
		for(int i =0;i<ClearW.length;i++){//���ܲ���
			int y = (k1*ClearW[i].No + k2)%26;//���ܼ�������y
			CipherW[i] = new AWord(y);
			System.out.println(CipherW[i].toString());
		}
		
		int n = qiuni(k1,26);//��k1����
		
		
		System.out.println("����->ԭ��");
		AWord reClearW[] = new AWord[ClearWordC.length];
		for(int i =0 ; i<CipherW.length ; i++ ){//���ܲ���
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
	public static boolean LExist(char c,LinkedList L){//���c�Ƿ����
		
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
		System.out.println("����ClearWord:" );
		ClearWord = getString();//�õ�����
		
		String Key;
		System.out.println("��ԿKey:" );
		Key = getString();//�õ�����
		
		char[] ClearWordC = ClearWord.toCharArray();
		char[] KeyC = Key.toCharArray();
		
		AWord[] ClearW = new AWord[ClearWordC.length];
		for(int i = 0; i <ClearWordC.length ; i++){
			ClearW[i] = new AWord(ClearWordC[i]);
		}
		
		char[] KeyTable = new char[26];
		KeyTable = "abcdefghijklmnopqrstuvwxyz".toCharArray();//26����ĸ

		System.out.print("[");
		System.out.print("a"+ ",");
		for(int j = 1 ; j < KeyTable.length ; j ++)
		{
		System.out.print(" "+KeyTable[j] + ",");
		}
		System.out.println("]");
		
		LinkedList KeysL = new LinkedList();//���ձ���
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
		System.out.println(KeysL.toString());//��Ӧ����ɹ�
		
		AWord[] CipherW = new AWord[ClearWordC.length];
		
		for(int i = 0 ; i < ClearWordC.length ; i++){
			CipherW[i] = new AWord((char)KeysL.get(ClearW[i].No));
		System.out.println(CipherW[i].toString());	
		}//����
		
		for(int i = 0 ; i < ClearWordC.length ; i++){
			System.out.println(KeyTable[KeysL.indexOf(CipherW[i].Text)] );
		}//����
		
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

		System.out.println("�ŵ�����㷨->�������");
		//Translate();
		Sample_Translate();
	}

}
