import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Component;

import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;

import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.TreeSet;

import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;



public class StudentUI extends JFrame {

	private JPanel contentPane;
	private JTextField classTextField;
	private JTextField numberTextField;
	private JTextField nameTextField;
	private JTextArea showTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentUI frame = new StudentUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\java_work\\studentDemo1.1\\src\\picture.png"));
		getContentPane().setForeground(Color.CYAN);
		setTitle("\u5B66\u751F\u6210\u7EE9\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 953, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u5E7F\u4E1C\u6D77\u6D0B\u5927\u5B66");
		label.setIcon(new ImageIcon("F:\\java_work\\studentDemo1.1\\src\\picture.png"));
		label.setFont(new Font("华文行楷", Font.BOLD | Font.ITALIC, 33));
		label.setFocusable(false);
		label.setSize(new Dimension(10, 50));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLUE);
		label.setBackground(Color.WHITE);
		contentPane.add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel classLable = new JLabel("\u73ED\u7EA7");
		
		classTextField = new JTextField();
		classTextField.setColumns(10);
		
		JLabel numberLabel = new JLabel("\u5B66\u751F\u5B66\u53F7");
		
		numberTextField = new JTextField();
		numberTextField.setColumns(10);
	
		JLabel nameLabel = new JLabel("\u5B66\u751F\u59D3\u540D");
		
		nameTextField = new JTextField();
		
		nameTextField.setColumns(10);
		JButton checkButton = new JButton("\u786E\u8BA4\u67E5\u8BE2");
		checkButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkStudent();
			}

			private void checkStudent() {
				// TODO Auto-generated method stub
				if(classTextField.getText().trim().length()>0&&numberTextField.getText().trim().length()>0&&nameTextField.getText().trim().length()>0){
					Manager manager=new Manager();
					showTextArea.setText("");
					TreeSet<Student> treeSetClass=manager.seekOfClass(classTextField.getText());
					for(Iterator<Student> iterator=treeSetClass.iterator();iterator.hasNext();){
						Student student=iterator.next();
						if(student.getNumber().equals(numberTextField.getText())&&student.getName().equals(nameTextField.getText())){
							
							showTextArea.append(manager.printStu(student));
						}
					}
				}
				else if (classTextField.getText().trim().length()>0&&numberTextField.getText().trim().length()>0&&nameTextField.getText().trim().length()==0) {
					Manager manager=new Manager();
					showTextArea.setText("");
					TreeSet<Student> treeSetClass=manager.seekOfClass(classTextField.getText());
					for(Iterator<Student> iterator=treeSetClass.iterator();iterator.hasNext();){
						Student student=iterator.next();
						if(student.getNumber().equals(numberTextField.getText())){
							
							showTextArea.append(manager.printStu(student)+"\r\n");
						}
						
					}
				}
				else if (classTextField.getText().trim().length()>0&&numberTextField.getText().trim().length()==0&&nameTextField.getText().trim().length()==0) {
					Manager manager=new Manager();
					showTextArea.setText("");
					TreeSet<Student> treeSetClass=manager.seekOfClass(classTextField.getText());
					for(Iterator<Student> iterator=treeSetClass.iterator();iterator.hasNext();){
						Student student=iterator.next();
						
						showTextArea.append(manager.printStu(student)+"\r\n");
					}
				}
				else if (classTextField.getText().trim().length()>0&&numberTextField.getText().trim().length()==0&&nameTextField.getText().trim().length()>0) {
					Manager manager=new Manager();
					showTextArea.setText("");
					TreeSet<Student> treeSetClass=manager.seekOfClass(classTextField.getText());
					for(Iterator<Student> iterator=treeSetClass.iterator();iterator.hasNext();){
						Student student=iterator.next();
						if(student.getName().equals(nameTextField.getText())){
							showTextArea.append(manager.printStu(student)+"\r\n");
						}
					}
				}
				else if (classTextField.getText().trim().length()==0&&numberTextField.getText().trim().length()>0&&nameTextField.getText().trim().length()==0) {
					Manager manager=new Manager();
					showTextArea.setText("");
					TreeSet<Student> treeSetClass=manager.seekOfNumber(numberTextField.getText());
					for(Iterator<Student> iterator=treeSetClass.iterator();iterator.hasNext();){
						Student student=iterator.next();
						showTextArea.append(manager.printStu(student)+"\r\n");
					}
				}
				else if (classTextField.getText().trim().length()==0&&numberTextField.getText().trim().length()==0&&nameTextField.getText().trim().length()>0) {
					Manager manager=new Manager();
					showTextArea.setText("");
					TreeSet<Student> treeSetClass=manager.seekOfName(nameTextField.getText());
					for(Iterator<Student> iterator=treeSetClass.iterator();iterator.hasNext();){
						Student student=iterator.next();
						showTextArea.append(manager.printStu(student)+"\r\n");
					}
				}
				else if (classTextField.getText().trim().length()==0&&numberTextField.getText().trim().length()>0&&nameTextField.getText().trim().length()>0) {
					Manager manager=new Manager();
					showTextArea.setText("");
					TreeSet<Student> treeSetClass=manager.seekOfNumber(numberTextField.getText());
					for(Iterator<Student> iterator=treeSetClass.iterator();iterator.hasNext();){
						Student student=iterator.next();
						if(nameTextField.getText().equals(student.getName()))
							showTextArea.append(manager.printStu(student)+"\r\n");
					}
				}
				else {
					showTextArea.setText("");
				}
			}

			

			
		});
		
		JButton addClassButton = new JButton("\u589E\u52A0\u73ED\u7EA7");
		addClassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonAfterFrame frame=new buttonAfterFrame("输入班级名称","addClass");
				frame.setVisible(true);
			}
		});
		
		JButton correctClassButton = new JButton("\u4FEE\u6539\u73ED\u540D\r\n");
		correctClassButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonAfterFrame frame=new buttonAfterFrame("原班级名称","新班级名称","correctClass");
				frame.setVisible(true);
			}
		});
		
		JButton deleteClassButton = new JButton("\u5220\u9664\u73ED\u7EA7");
		deleteClassButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonAfterFrame frame=new buttonAfterFrame("输入班级名称","deleteClass");
				frame.setVisible(true);
			}
		});
		
		JButton addStuBut = new JButton("\u589E\u52A0\u5B66\u751F");
		addStuBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonAfterFrame frame=new buttonAfterFrame("班级", "学号","性别","姓名","数学","语文","英语");
				frame.setVisible(true);
			}
		});
		
		JButton correctStuBut = new JButton("\u4FEE\u6539\u6539\u5B66\u751F\u4FE1\u606F");
		correctStuBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonAfterFrame frame=new buttonAfterFrame("输入要修改的学生的学号","correctStu");
				frame.setVisible(true);
			}
		});
		
		JButton deleteStuBut = new JButton("\u5220\u9664\u8BE5\u5B66\u751F");
		deleteStuBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonAfterFrame frame=new buttonAfterFrame("输入学生班级","输入该学生学号","deleteStu");
				frame.setVisible(true);
			}
		});
		
		JLabel label_10 = new JLabel("    \u5B66\u53F7     \u6027\u522B   \u59D3\u540D     \u6570\u5B66  \u8BED\u6587    \u82F1\u8BED    \u603B\u5206  \u5E73\u5747\u5206  \u73ED\u6392\u540D  \u6821\u6392\u540D");
		label_10.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(deleteClassButton, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
						.addComponent(correctClassButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
						.addComponent(addClassButton, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(addStuBut, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
									.addGap(176)
									.addComponent(correctStuBut)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(deleteStuBut, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(classLable)
									.addGap(5)
									.addComponent(classTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(numberLabel)
									.addGap(5)
									.addComponent(numberTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(nameLabel)
									.addGap(5)
									.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(checkButton))
								.addComponent(label_10, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 672, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 662, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addComponent(classLable))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(classTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addComponent(numberLabel))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(numberTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(9)
							.addComponent(nameLabel))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(checkButton)))
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(addClassButton, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(59)
							.addComponent(correctClassButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
							.addComponent(deleteClassButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(addStuBut, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
							.addComponent(correctStuBut, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
						.addComponent(deleteStuBut, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		showTextArea =  new JTextArea();
		showTextArea.setEditable(false);
		showTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		scrollPane_1.setViewportView(showTextArea);
		panel.setLayout(gl_panel);
		
		
		
		
	}
	
	
	
	
}
