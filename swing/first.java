package first;

import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;

public class one{
	public String driver = "com.mysql.jdbc.Driver"; 
	public String DB_url ="DB";
	Connection con=null;
	PreparedStatement pstmt=null;//SQL문 실핼
	ResultSet rs=null;//select문 실행을 위해 필요함
	//JTable 구성을 위해 선언
	DefaultTableModel model;
	JFrame frame;
	JTable table;
	JScrollPane sc;
	JPanel panel;
	JTextField numSeq,nameField,adField;
	JButton addBtn,revBtn,delBtn;
	
	public one() {
		
		Dimension dim = new Dimension(1000,500);//dimension은 높이와 폭을 캡슐화하는 클래스
		
		frame = new JFrame("주소록");
		frame.setLocation(200,400);//윈도우를 나타낼 위치 지정
		frame.setPreferredSize(dim);//dim에서 정보를 받아 사이즈를 받는다
		
		String header[] = {"순서","이름","주소"};//테이블에 필요한 항목 선언=컬럼
		Object contents[][] = new Object[0][3];//내용 비움=셀
		model = new DefaultTableModel(contents,header);//셀,컬럼
		table = new JTable(model);//테이블 선언
		sc = new JScrollPane(table);//테이블에 스크롤 추가,swing에는 스크롤 생성이 자동으로 안 되기 때문에
		
		panel = new JPanel();//상단에 들어갈 패널 선언
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));//패널 수평 선언,Y_AXIS 수직 선언
		
		numSeq = new JTextField(5);//텍스트 입력
		nameField = new JTextField(5);//텍스트 입력
		adField = new JTextField(5);//텍스트 입력
		
		panel.add(numSeq);//패널에 추가
		panel.add(nameField);//패널에 추가
		panel.add(adField);//패널에 추가
		
		connect();//DB연결
		select();//DB내용 보여줌
		
		addBtn = new JButton("추가");//버튼 선언 
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object data[]= {numSeq.getText(),nameField.getText(),adField.getText()};//객체에 넣어줌
				model.addRow(data);//저장할 값 배열
				try {
					String sql="insert into AddressBook(Num,Name,ads)"+"value(?,?,?)";//insert문으로 값 넣어주기 위해 선언
					pstmt=con.prepareStatement(sql);
					//3개를 입력해줘야하므로 3개 입력
					pstmt.setString(1, numSeq.getText());
					pstmt.setString(2, nameField.getText());
					pstmt.setString(3, adField.getText());
					//정보 업데이트
					int insert=pstmt.executeUpdate();
					System.out.println("데이터 성공 유무(insert):"+insert);
				}catch(Exception e2) {
					System.out.println("입력 실패:"+e2);
				}//오류처리
				//입력창 비워줌
				numSeq.setText("");
				nameField.setText("");
				adField.setText("");
			}
		});
		revBtn = new JButton("수정");
		revBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					return;//행 선택 안했으면 반환
				}
				else {
					try {
						String sql="UPDATE AddressBook SET Name=?,ads=? WHERE Num=?";//내용 수정을 위해 업데이트 선언
						pstmt=con.prepareStatement(sql);
						
						int row = table.getSelectedRow();
						
						pstmt.setString(1, String.valueOf(model.getValueAt(row, 1)));
						pstmt.setString(2, (String)model.getValueAt(row, 2));
						pstmt.setString(3, String.valueOf(model.getValueAt(row, 0)));
						
						int rev=pstmt.executeUpdate();//업데이트
						
						System.out.println("데이터 성공 유무(rev):"+rev);
					}catch(Exception e2) {
						System.out.println("수정 실패:"+e2);
					}//오류 처리
					model.setRowCount(0);
					select();
					numSeq.setText("");
					nameField.setText("");
					adField.setText("");
				}
			}
		}
				);
		
		delBtn = new JButton("삭제");
		delBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					return;
				}//삭제할 행이 선택되지 않았다면 리턴
				else {
					try {
						String sql="delete from AddressBook where Num=?";
						int row = table.getSelectedRow();
						pstmt=con.prepareStatement(sql);
						
						pstmt.setString(1,String.valueOf(model.getValueAt(row, 0)));
						
						int del=pstmt.executeUpdate();
						
						model.removeRow(table.getSelectedRow());
						
						System.out.println("데이터 성공 유무(del):"+del);
					}catch(Exception e2) {
						System.out.println("삭제 실패:"+e2);
					}
				}//아니면 삭제
			}
		});
	
		panel.add(addBtn);//패널에 추가 버튼 적용
		panel.add(delBtn);//패널에 삭제 버튼 적용
		panel.add(revBtn);//패널에 수정 버튼 적용
		
		frame.add(sc,BorderLayout.CENTER);
		frame.add(panel,BorderLayout.SOUTH);//판넬에 적용되는 모든 것은 남쪽에 배치
		frame.pack();
		frame.setVisible(true);//보이게 한다.
		
		
	}
	public void connect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(DB_url,"ID","");
			System.out.println("접속:"+con);
		}catch(Exception e) {
			System.out.println("DB접속 오류"+e);
		}
	}
	public void select() {
		try {
			String sql = "select * from AddressBook";
			pstmt = con.prepareStatement(sql);
			System.out.println("pstmt : "+pstmt);
			rs = pstmt.executeQuery();//select문장
			System.out.println("rs : "+rs);
			
			while(rs.next()) {
				int nums = rs.getInt("num");
				String names = rs.getString("Name");
				String adss = rs.getString("ads");
				 
				Object data[] = {nums,names,adss};
				model.addRow(data);
				System.out.println(nums+","+names+","+adss);
			 }
		}catch(Exception e) {
			System.out.println("select() 실행 오류 : "+e);
		}
	}
	public static void main(String args[]) {
		new one();
	}
}
