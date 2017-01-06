package ��������ȫ����;

import java.util.LinkedList;

public class DES {	
// �˱�Ϊ64λ�ĳ�ʼ�û���
public static int[] IP = { 
			58, 50, 42, 34, 26, 18, 10, 2,
			60, 52, 44, 36, 28, 20, 12, 4,
			62, 54, 46, 38, 30, 22, 14,6, 
			64, 56, 48, 40, 32, 24, 16, 8, 
			57, 49, 41, 33, 25, 17, 9, 1, 
			59, 51, 43, 35, 27, 19, 11, 3, 
			61, 53, 45,37, 29, 21, 13, 5, 
			63, 55, 47, 39, 31, 23, 15, 7
			};
	//�˱�Ϊ64λ���û���
public static int[] IP1 = { 40, 8, 48, 16, 56, 24, 64, 32,
		39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22,
		62,30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4,
		44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19,
		59, 27, 34, 2, 42,10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25}; 
//48λ��չ�任��E
public static int[] E = {
		32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13,
        14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24,
        25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1
};
//56λ��Կ�����е��û�ѡ��1
public static int[] EC1 = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51,
        43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7,
        62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20,
        12, 4};
//48λ��Կ�����е��û�ѡ��2
public static int[] EC2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16,
        7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44,
        49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};
//��Կ�е�ѭ�����Ʊ�
public static int[] LM = {
		 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1
};
//S�ж�ά����4*16
public static int[][] SBox = {
		  { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
          { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
          { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
          { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 }
};
//32λP�û���
public static int[] P = {
		16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8,
        24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25
};
static byte KEY[][] = new byte[16][48];
//���һ������    
static void OutPutArray(int[] a ){
		for(int i = 0;i < a.length ; i++){
			if (i % 8 == 0) {
				System.out.println();
			}
			System.out.print(a[i] +",");
		}
		System.out.println();
	}
static void OutPutArray(byte[] a ){
	for(int i = 0;i < a.length ; i++){
		if (i % 8 == 0) {
			System.out.println();
		}
		System.out.print(a[i] +",");
	}
	System.out.println();
}
static void MoveLeft(byte[] A,int num){//ѭ�����ƺ���
	for(int j = 0; j < num ; j++){
	byte temp;
	temp = A[0];
	for(int i = 0 ; i < A.length -1; i++){
		A[i] = A[i+1];
	}
	A[A.length-1] = temp;
	}
}
static byte[] Combine(byte[] L,byte[] H){//��Ϻ���
	byte[] All = new byte[L.length+H.length];
	for(int i = 0 ; i <L.length;i++)
	{
		All[i] = L[i];
	}	
	for(int i = 0 ; i <H.length;i++)
	{
		All[i+L.length] = H[i];
	}
	return All;
}

static int getIndexX(byte[] a){//S�б任�õ��±�x
	int x;
	x = a[0]*2+a[5];
	return x;
}
static int getIndexY(byte[] a){//S�б任�õ��±�y
	int y;
	y = a[1]*8+a[2]*4+a[3]*2+a[4]*1;
	return y;
}
//ʮ������ת������������ת��Ϊ4λbyte����
static byte[] TentoBin(int a){
	LinkedList B = new LinkedList();
	int beichushu = a;
	int chushu = 2;
	int yushu;
	int shang = -1;
	while(shang != 0){
		shang = beichushu/chushu;
		yushu = beichushu%chushu;
		byte b = (byte)yushu;
		B.addFirst(b);
		beichushu = shang;
	}
	byte[] Bin = {0,0,0,0};
	//Bin = (byte[])B.toArray();
	for(int i =0 ;i<B.size();i++){
	Bin[i] = (byte)B.get(i);
	}
	return Bin;
}
static byte[] Change(byte[] A,byte[] K){//�˻��任
	//�û�����������ҷֿ�
	byte[] L = new byte[32];
	byte[] R = new byte[32];
	for(int i = 0;i <32;i++){
		L[i] = A[i];
				R[i] = A[i+32];
	}
	System.out.println("���ķֿ���һ����һ��:");
	OutPutArray(L);OutPutArray(R);
	byte[] Lnew = new byte[32];
	byte[] Rnew = new byte[32];
	
	//��Կ���û�K64->K56
	byte[] C = new byte[28];
	byte[] D = new byte[28];
	for(int i = 0;i < C.length;i++){
		C[i] =K[EC1[i]-1];
		D[i] =K[EC1[i+28]-1];
	}
	System.out.println("��Կ�ֿ���һ����һ��:");
	OutPutArray(C);OutPutArray(D);
	
	//ѭ��16�γ˻�����
	for(int i = 0 ; i < 16 ; i++){
		
		//�ұߵ�ֱ�ӷŵ����
		Lnew = R.clone();
		//�ұ�32λ��չ�任E,��32λ��չλ48λ
		byte[] ER = new byte[48];
		for(int j = 0; j<ER.length;j++){			
			ER[j] = R[E[j]-1];
		}
		/*System.out.println("E��չ֮��");
		OutPutArray(ER);*/
		
		//ÿһ�ζ�Ҫ����һ����Կ�ģ�������Կ�Ĳ���Ҳ����ѭ����
		MoveLeft(C, LM[i]);
		MoveLeft(D, LM[i]);
		byte[] CB = Combine(C, D);
		byte[] key = new byte[48];
		for(int j = 0 ;j < key.length;j++){
		//	System.out.println(key.length+" "+j+" "+EC2[j]);
			key[j] = CB[ EC2[j]-1 ];
		}
		KEY[i] = key;//��¼��Կ������¼
		/*System.out.println("ÿһ�ε���Կ:");
		OutPutArray(key);*/
		//��48λ����Կ���û���48λ���Ұ벿���������,�����Ȼ����ER��
		for(int j = 0 ; j < key.length ; j++){
			ER[j] = (byte)(ER[j]^key[j]);
		}
		/*System.out.println("^^^^^");
		OutPutArray(ER);*/
		//���֮���ER��ͨ��S�б任���32λ
		byte[] SR = {};//����S�б任��32λ���ݷ���SR��
		byte[][] ERs = new byte[8][6];
		for(int is = 0; is < 8;is++){//��ER��ɶ�ά����ERs
			for(int js = 0;js<6;js++){
				ERs[is][js] = ER[is*6 + js];
			}
		}
		//S�б任
		for(int j = 0; j <ER.length/6;j++){
			System.out.println(getIndexX(ERs[j])+" "+getIndexY(ERs[j])+" "+SBox[getIndexX(ERs[j])][getIndexY(ERs[j])]);
			byte[] tb = new byte[4];
			tb = TentoBin(SBox[getIndexX(ERs[j])][getIndexY(ERs[j])]);
			//OutPutArray(tb);
			SR = Combine(SR, tb);
		//	System.out.print("srsrsrsrsrsrs");
		OutPutArray(SR);
		}
		/*System.out.println("S�б任֮��"+"SR"+SR.length+"bit");
		OutPutArray(SR);*/
		
		//S�б����32λ��SR����P�û���PR��
		byte[] PR = new byte[SR.length];
		for(int j = 0; j<SR.length; j++){
			PR[j] = SR[P[j]-1];
		}
		//.out.println("PR"+PR.length);
		//OutPutArray(PR);
		
		//��L��ߵ���򣬵õ��µ�R-Rnew
		for(int j = 0;j <PR.length;j++){
			Rnew[j] = (byte)(PR[j]^L[j]); 
		}
		//OutPutArray(L);
		//OutPutArray(Lnew);
		L = Lnew.clone();
		R = Rnew.clone();
		//OutPutArray(L);
	}//16������
	byte[] As = new byte[L.length+R.length];
	As = Combine(R,L);
//	OutPutArray(L);OutPutArray(R);
	return As;
}
static byte[] RChange(byte[] A){//���ܵĳ˻��任
	//�û�����������ҷֿ�
		byte[] L = new byte[32];
		byte[] R = new byte[32];
		for(int i = 0;i <32;i++){
			L[i] = A[i];
					R[i] = A[i+32];
		}
		byte[] Lnew = new byte[32];
		byte[] Rnew = new byte[32];
		
		//ѭ��16�γ˻�����
		for(int i = 0 ; i < 16 ; i++){
			
			//�ұߵ�ֱ�ӷŵ����
			Lnew = R.clone();
			//�ұ�32λ��չ�任E,��32λ��չλ48λ
			byte[] ER = new byte[48];
			for(int j = 0; j<ER.length;j++){			
				ER[j] = R[E[j]-1];
			}
			//System.out.println("E��չ֮��");
			//OutPutArray(ER);
			
			//ÿһ�ζ�Ҫ����һ����Կ�ģ�������Կ�Ĳ���Ҳ����ѭ����
			byte[] key = new byte[48];
			key = KEY[15-i];
			//OutPutArray(key);
			//��48λ����Կ���û���48λ���Ұ벿���������,�����Ȼ����ER��
			for(int j = 0 ; j < key.length ; j++){
				ER[j] = (byte)(ER[j]^key[j]);
			}
//			System.out.println("^^^^^");
//			OutPutArray(ER);
			//���֮���ER��ͨ��S�б任���32λ
			byte[] SR = {};//����S�б任��32λ���ݷ���SR��
			byte[][] ERs = new byte[8][6];
			for(int is = 0; is < 8;is++){//��ER��ɶ�ά����ERs
				for(int js = 0;js<6;js++){
					ERs[is][js] = ER[is*6 + js];
				}
			}
			//S�б任
			for(int j = 0; j <ER.length/6;j++){
				byte[] tb = new byte[4];
				tb = TentoBin(SBox[getIndexX(ERs[j])][getIndexY(ERs[j])]);
				SR = Combine(SR, tb);
				}
			//System.out.println("SR"+SR.length+"bit");
			//OutPutArray(SR);
			
			//S�б����32λ��SR����P�û���PR��
			byte[] PR = new byte[SR.length];
			for(int j = 0; j<SR.length; j++){
				PR[j] = SR[P[j]-1];
			}
			//System.out.println("PR"+PR.length);
			//OutPutArray(PR);
			
			//��L��ߵ���򣬵õ��µ�R-Rnew
			for(int j = 0;j <PR.length;j++){
				Rnew[j] = (byte)(PR[j]^L[j]); 
			}
			
			L = Lnew.clone();
			R = Rnew.clone();
			
		}//16������
		byte[] As = new byte[L.length+R.length];
		As = Combine(R,L);
		return As;
}
//�ж��Ƿ�һ��
static boolean IsSame(byte[] A,byte[] B){
	for(int i =0 ;i<A.length;i++){
		if(A[i] != B[i]){
			return false;
		}
	}
	return true;
}
public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		System.out.println("DES");
		//System.out.println("��ʼ�û���IP");
		//OutPutArray(IP);
		//System.out.println("���û���IP-1");
		//OutPutArray(IP1);
	
	//�������64λ����--������
		/*byte[] a = {1,1,0,0,1,0,1,0,
				1,0,0,0,1,1,0,1,
				0,0,0,0,0,0,1,0,
				0,1,1,0,1,0,0,1,
				0,0,1,1,0,0,1,1,
				1,0,0,0,1,1,0,1,
				1,0,0,0,1,1,1,0,
				0,0,1,0,0,1,0,1};*/
		byte[] a= new byte[64];
		for(int i = 0; i<a.length; i++){
		a[i] = (byte) ((Math.random()*2)/1);
		}
		System.out.println("64bit����:");
		OutPutArray(a);
		
		//�������64λ��Կ
		/*byte[] key = {0,0,0,0,0,0,1,1,
				1,1,0,0,1,0,0,0,
				1,1,1,1,1,1,0,0,
				1,1,1,1,1,0,1,1,
				0,1,0,0,0,0,0,0,
				1,1,0,0,0,1,1,0,
				0,0,0,0,1,0,0,1,
				1,1,0,0,0,1,0,1};*/
		byte[] key= new byte[64];
		for(int i = 0; i<key.length; i++){
		key[i] = (byte) ((Math.random()*2)/1);
		}
		System.out.println("64bit��Կ:");
		OutPutArray(key);
		
	//��ʼ�û�������1-64
		//������0-63
		//�����û���ʱ��Ҫ��1
		byte[] acopy = new byte[a.length];
		acopy = a.clone();
		for(int i = 0 ; i < a.length ; i++){
			a[i]	=	 acopy[IP[i]-1]; 
			}
		System.out.println("��ʼ�û�64bit:");
		OutPutArray(a);
	
		byte[] Mula = new byte[a.length];
		Mula = Change(a,key);
		System.out.println("�˻�16��ѭ�����64bit��֮��IP1���û�:");
		OutPutArray(Mula);
		//���û�IP_1
		byte[] Cipher = new byte[a.length];
		for(int i = 0 ; i< Cipher.length;i++){
			Cipher[i] = Mula[IP1[i]-1];
		}
		System.out.println("���û����64λ����:");
		OutPutArray(Cipher);
		
	//���ϼ��ܹ��̽���������ý���---------------------------------------------
		byte[] Ciphercopy = new byte[Cipher.length];
		Ciphercopy = Cipher.clone();
		for(int i = 0 ; i < Cipher.length ; i++){//��ʼ�û�
			Cipher[i]	=	 Ciphercopy[IP[i]-1]; 
			}
		
		byte[] MulCipher = new byte[Cipher.length];
		MulCipher = RChange(Cipher);
		
		byte[] Clear = new byte[Cipher.length];
		for(int i = 0 ; i< Clear.length;i++){
			Clear[i] = MulCipher[IP1[i]-1];
		}
		System.out.println("����:");
		OutPutArray(Clear);
		
		
		if(IsSame(acopy, Clear)){
			System.out.println("һ��");
			OutPutArray(TentoBin(7));
			OutPutArray(TentoBin(14));
		}else{
			System.out.println("��һ��");
		}
		
	}
}
