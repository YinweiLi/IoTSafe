package 物联网安全技术;

public class AWord {

	char Text;//单个的字母
	int No;//字母的序号
	public AWord(){
		Text = 'a';
		No = 0;
	}
	public AWord(char text, int no) {
		super();
		Text = text;
		No = no;
	}
	public AWord( int no) {
		super();
		No = no;
		if(no == 0) Text = 'a';
		if(no == 1) Text ='b' ;
		if(no == 2) Text = 'c';
		if(no == 3) Text = 'd';
		if(no == 4) Text =  'e';
		if(no == 5) Text = 'f';
		if(no == 6) Text = 'g';
		if(no == 7) Text ='h' ;
		if(no == 8) Text = 'i';
		if(no ==  9) Text ='j';
		if(no == 10) Text = 'k';
		if(no == 11) Text = 'l';
		if(no == 12) Text = 'm';
		if(no == 13) Text = 'n';
		if(no == 14) Text = 'o';
		if(no == 15) Text = 'p';
		if(no == 16) Text = 'q';
		if(no == 17) Text = 'r';
		if(no == 18) Text = 's';
		if(no == 19) Text = 't';
		if(no == 20) Text = 'u';
		if(no == 21) Text = 'v';
		if(no == 22) Text = 'w';
		if(no == 23) Text = 'x';
		if(no == 24) Text = 'y';
		if(no == 25) Text = 'z';
	
	}
	public AWord(char text) {
		super();
		Text = text;
		
		if(text == 'a') No = 0;
		if(text == 'b') No = 1;
		if(text == 'c') No = 2;
		if(text == 'd') No = 3;
		if(text == 'e') No = 4;
		if(text == 'f') No = 5;
		if(text == 'g') No = 6;
		if(text == 'h') No = 7;
		if(text == 'i') No = 8;
		if(text == 'j') No = 9;
		if(text == 'k') No = 10;
		if(text == 'l') No = 11;
		if(text == 'm') No = 12;
		if(text == 'n') No = 13;
		if(text == 'o') No = 14;
		if(text == 'p') No = 15;
		if(text == 'q') No = 16;
		if(text == 'r') No = 17;
		if(text == 's') No = 18;
		if(text == 't') No = 19;
		if(text == 'u') No = 20;
		if(text == 'v') No = 21;
		if(text == 'w') No = 22;
		if(text == 'x') No = 23;
		if(text == 'y') No = 24;
		if(text == 'z') No = 25;
		
	}
	@Override
	public String toString() {
		return "AWord [Text=" + Text + ", No=" + No + "]";
	}
	public char getText() {
		return Text;
	}
	public void setText(char text) {
		Text = text;
	}
	public int getNo() {
		return No;
	}
	public void setNo(int no) {
		No = no;
	}
	
}
