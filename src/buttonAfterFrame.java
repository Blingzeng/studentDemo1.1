import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.io.File;
import java.sql.Struct;
import java.util.Iterator;
import java.util.TreeSet;



public class buttonAfterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField classNameTextField;
	private JLabel numberLable;
	private JTextField numberTextField;
	private JTextField sexTextField;
	private JTextField nameTextField;
	private JTextField mathTextField;
	private JTextField ChinaTextField;
	private JTextField EnglishTextField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public buttonAfterFrame(String string,String typeString) {
		JFrame jFrame=new JFrame();
		setBounds(100, 100, 276, 172);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel(string);
		
		classNameTextField = new JTextField();
		classNameTextField.setColumns(10);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(typeString.equals("addClass")){
					StudentClass studentClass=new StudentClass(classNameTextField.getText());
					Successful successful=new Successful("新建班级成功");
					successful.setVisible(true);
					
				}
				if(typeString.equals("correctStu")){
					// correctStu
					Manager manager =new Manager();
					TreeSet<Student> treeSet=manager.seekOfNumber(classNameTextField.getText());
					if(treeSet!=null){
					for(Iterator<Student> iterator=treeSet.iterator();iterator.hasNext();){
						Student student=iterator.next();
						
						TreeSet<Student> templetTreeSet=manager.readFileToTreeSet(student.getClassName());
						templetTreeSet.remove(student);
						try {
							manager.treeSetToWriFile(student.getClassName(), templetTreeSet);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						buttonAfterFrame buttonAfterFrame=new buttonAfterFrame("班级","学号", "性别","姓名","数学","语文","英语",
								student.getClassName(),student.getNumber(),student.getSex(),student.getName(),""+student.getMath(),""+student.getChina(),""+student.getEnglish());
						buttonAfterFrame.setVisible(true);
						
						}
					}
					
				}
				if(typeString.equals("deleteClass")){
					Manager manager=new Manager();
					
					File templeFile=new File("f:\\"+classNameTextField.getText()+".txt");
					if(!templeFile.exists()){
						errorText errorText=new errorText("该班级不存在");
						errorText.setVisible(true);
					}
					else {
						manager.deleteClass(classNameTextField.getText());
						Successful successful=new Successful("删除班级成功");
						successful.setVisible(true);
					}
					
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(classNameTextField, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(78)
					.addComponent(button)
					.addContainerGap(107, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(classNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	public buttonAfterFrame(String oldstring,String newstring ,String signString) {
		setTitle("\u4FE1\u606F\u8F93\u5165\u63D0\u793A\u6846");
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Desktop\\\u56FE\u72471.png"));
		setBounds(100, 100, 322, 243);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel(oldstring);
		
		classNameTextField = new JTextField();
		classNameTextField.setColumns(10);
		
		
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(signString.equals("correctClass")){
					Manager manager=new Manager();
					File file=new File("f:\\"+classNameTextField.getText()+".txt");
					if(file.exists()&&!classNameTextField.getText().equals(numberTextField.getText())){
					manager.correctNameClass(classNameTextField.getText(), numberTextField.getText());
						Successful successful=new Successful("修改班级成功");
						successful.setVisible(true);
					}
					else {
						errorText errorText=new errorText("班级不存在或者修改班名重复");
						errorText.setVisible(true);
					}
					
				}
				else if(signString.equals("deleteStu")){
					Manager manager=new Manager();
					TreeSet<Student> treeSet=manager.readFileToTreeSet(classNameTextField.getText());
					Student student=manager.seekOfNumber(treeSet, numberTextField.getText());
					if(student!=null){
						treeSet.remove(student);
					}
					else {
						errorText errorText=new errorText("这个班级没有该学生");
						errorText.setVisible(true);
					}
					try {
						manager.treeSetToWriFile(classNameTextField.getText(), treeSet);
						Successful successful=new Successful("删除学生成功");
						successful.setVisible(true);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
				
		});
		
		numberLable = new JLabel(newstring);
		
		numberTextField = new JTextField();
		numberTextField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(93)
					.addComponent(button)
					.addContainerGap(138, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addComponent(numberLable))
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(numberTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
						.addComponent(classNameTextField, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(classNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(numberLable)
						.addComponent(numberTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(49))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	public buttonAfterFrame(String className,String number,String sex,String name,String matnScore,String ChinaScore,String englishScore) {
	setTitle("\u8F93\u5165\u63D0\u793A\u6846");
	setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Desktop\\\u56FE\u72471.png"));
		setBounds(200, 200, 322, 422);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel classNameLable = new JLabel(className);
		
		classNameTextField = new JTextField();
		classNameTextField.setColumns(10);
		
		
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					//addStu
					if(classNameTextField.getText().trim().length()==0){
						errorText errorText=new errorText("请输入班级");
						errorText.setVisible(true);
					}
					else if(numberTextField.getText().trim().length()==0){
						errorText errorText=new errorText("请输入学号");
						errorText.setVisible(true);
					}
					else if(nameTextField.getText().trim().length()==0){
						errorText errorText=new errorText("请输入姓名");
						errorText.setVisible(true);
					}
					else{
						Manager manager =new Manager();
						TreeSet<Student> treeSet=new TreeSet<>();
						treeSet=manager.readFileToTreeSet(classNameTextField.getText());
						if(treeSet==null){
							if(classNameTextField.getText().trim().length()!=0){
								errorText errorText=new errorText("输入班级有误，请重新输入");
								errorText.setVisible(true);
							}
							
						}
						
						try {
							Student student=manager.creatStudent(classNameTextField.getText(),numberTextField.getText(),
									sexTextField.getText(), nameTextField.getText(), mathTextField.getText(), ChinaTextField.getText(),EnglishTextField.getText());
							manager.addStu(treeSet, student);
							manager.treeSetToWriFile(classNameTextField.getText(), treeSet);
							Successful successful=new Successful("增加学生成功");
							successful.setVisible(true);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				
					
				
				
			}
		});
		
		numberLable = new JLabel(number);
		
		numberTextField = new JTextField();
		numberTextField.setColumns(10);
		
		JLabel sexLable = new JLabel(sex);
		
		sexTextField = new JTextField();
		sexTextField.setColumns(10);
		
		JLabel nameLable = new JLabel(name);
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		
		JLabel mathLable = new JLabel(matnScore);
		
		mathTextField = new JTextField();
		mathTextField.setColumns(10);
		
		JLabel ChinaLable = new JLabel(ChinaScore);
		
		ChinaTextField = new JTextField();
		ChinaTextField.setColumns(10);
		
		JLabel EnglishLable = new JLabel(englishScore);
		
		EnglishTextField = new JTextField();
		EnglishTextField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(95)
					.addComponent(button)
					.addContainerGap(136, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(classNameLable, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addComponent(numberLable)
						.addComponent(sexLable)
						.addComponent(nameLable)
						.addComponent(mathLable)
						.addComponent(ChinaLable)
						.addComponent(EnglishLable))
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(EnglishTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
						.addComponent(ChinaTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
						.addComponent(mathTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
						.addComponent(nameTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
						.addComponent(sexTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
						.addComponent(numberTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
						.addComponent(classNameTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(classNameLable)
						.addComponent(classNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(numberLable)
						.addComponent(numberTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(sexLable)
						.addComponent(sexTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameLable)
						.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(mathLable)
						.addComponent(mathTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(ChinaLable)
						.addComponent(ChinaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addComponent(EnglishLable))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(EnglishTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(button)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	public buttonAfterFrame(String className,String number,String sex,String name,String matnScore,String ChinaScore,String englishScore,String classNameF,
							String numberF,String sexF,String nameF,String mathF,String ChinaF,String EnglishF) {
		setTitle("\u8F93\u5165\u63D0\u793A\u6846");
		setIconImage(Toolkit.getDefaultToolkit().getImage("G:\\Desktop\\\u56FE\u72471.png"));
			setBounds(200, 200, 322, 422);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(176, 224, 230));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			JLabel classNameLable = new JLabel(className);
			
			classNameTextField = new JTextField(classNameF);
			classNameTextField.setColumns(10);
			
			
			
			JButton button = new JButton("\u786E\u8BA4");
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
						//addStu
						if(classNameTextField.getText().trim().length()==0){
							errorText errorText=new errorText("请输入班级");
							errorText.setVisible(true);
						}
						else if(numberTextField.getText().trim().length()==0){
							errorText errorText=new errorText("请输入学号");
							errorText.setVisible(true);
						}
						else if(nameTextField.getText().trim().length()==0){
							errorText errorText=new errorText("请输入姓名");
							errorText.setVisible(true);
						}
						else{
							Manager manager =new Manager();
							TreeSet<Student> treeSet=new TreeSet<>();
							treeSet=manager.readFileToTreeSet(classNameTextField.getText());
							if(treeSet==null){
								if(classNameTextField.getText().trim().length()!=0){
									errorText errorText=new errorText("输入班级有误，请重新输入");
									errorText.setVisible(true);
								}
								
							}
							
							try {
								Student student=manager.creatStudent(classNameTextField.getText(),numberTextField.getText(),sexTextField.getText(), nameTextField.getText(), mathTextField.getText(), ChinaTextField.getText(),EnglishTextField.getText());
								manager.addStu(treeSet, student);
								manager.treeSetToWriFile(classNameTextField.getText(), treeSet);
								Successful successful=new Successful("修改学生信息成功");
								successful.setVisible(true);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					
						
					
					
				}
			});
			
			numberLable = new JLabel(number);
			
			numberTextField = new JTextField(numberF);
			numberTextField.setColumns(10);
			
			JLabel sexLable = new JLabel(sex);
			
			sexTextField = new JTextField(sexF);
			sexTextField.setColumns(10);
			
			JLabel nameLable = new JLabel(name);
			
			nameTextField = new JTextField(nameF);
			nameTextField.setColumns(10);
			
			JLabel mathLable = new JLabel(matnScore);
			
			mathTextField = new JTextField(mathF);
			mathTextField.setColumns(10);
			
			JLabel ChinaLable = new JLabel(ChinaScore);
			
			ChinaTextField = new JTextField(ChinaF);
			ChinaTextField.setColumns(10);
			
			JLabel EnglishLable = new JLabel(englishScore);
			
			EnglishTextField = new JTextField(EnglishF);
			EnglishTextField.setColumns(10);
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(95)
						.addComponent(button)
						.addContainerGap(136, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(classNameLable, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
							.addComponent(numberLable)
							.addComponent(sexLable)
							.addComponent(nameLable)
							.addComponent(mathLable)
							.addComponent(ChinaLable)
							.addComponent(EnglishLable))
						.addGap(8)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(EnglishTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
							.addComponent(ChinaTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
							.addComponent(mathTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
							.addComponent(nameTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
							.addComponent(sexTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
							.addComponent(numberTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
							.addComponent(classNameTextField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(classNameLable)
							.addComponent(classNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(numberLable)
							.addComponent(numberTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(27)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(sexLable)
							.addComponent(sexTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(nameLable)
							.addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(mathLable)
							.addComponent(mathTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(ChinaLable)
							.addComponent(ChinaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(21)
								.addComponent(EnglishLable))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(18)
								.addComponent(EnglishTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
						.addComponent(button)
						.addContainerGap())
			);
			contentPane.setLayout(gl_contentPane);
		}
}
