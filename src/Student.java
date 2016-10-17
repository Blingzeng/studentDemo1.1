import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;




public class Student implements Comparable<Student>{
	private String number=null;
	private String sex=null;
	private String name=null;
	private double math=0;
	private double China=0;
	private double English=0;
	private double allScore=math+China+English;
	private double averageScore=allScore/3;
	private int classRank=1;
	private int CompusRank=1;
	private String className=null;
	
	
	public Student(String number,String name) {
		// TODO Auto-generated constructor stub
		this.number=number;
		this.name=name;
	}
	
	
	@Override
	public int compareTo(Student student) {
		// TODO Auto-generated method stub
		return number.compareTo(student.getNumber());
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getMath() {
		return math;
	}


	public void setMath(double math) {
		this.math = math;
	}


	public double getChina() {
		return China;
	}


	public void setChina(double china) {
		China = china;
	}


	public double getEnglish() {
		return English;
	}


	public void setEnglish(double english) {
		English = english;
	}
	
	public void setClassRank(int classRank) {
		this.classRank = classRank;
	}
	
	public int getClassRank() {
		return classRank;
	}
	
	public void setCompusRank(int compusRank) {
		CompusRank = compusRank;
	}
	
	public int getCompusRank() {
		return CompusRank;
	}
	
	public double getAllScore() {
		return allScore;
	}
	
	public double getAverageScore() {
		return averageScore;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getClassName() {
		return className;
	}
	
}

class StudentClass implements Comparable<StudentClass>{
	String name=null;
	int count=0;
	
	public StudentClass(String name) {
		// TODO Auto-generated constructor stub
		// TODO Auto-generated constructor stub
				this.name=name;
				FileWriter classFile=null;
				FileWriter recordFile=null;
				File templeFile=new File("f:\\"+name+".txt");
				if(!templeFile.exists()){
					try {
						classFile=new FileWriter("f:\\"+name+".txt");
						recordFile=new FileWriter("f:\\RecordClassName.txt",true);
						recordFile.write(name+"\r\n");
						classFile.close();
						recordFile.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}
	
	@Override
	public int compareTo(StudentClass o) {
		// TODO Auto-generated method stub
		return name.compareTo(o.name);
	}
}

class Manager{
	//已测试，通过
	public boolean correctNameClass(String oldString,String newString) {
		copyFile(oldString, newString);
		deleteClass(oldString);
		TreeSet<String> treeSet=readFileToTreeSetString("RecordClassName");
		if(treeSet.contains(oldString)){
			treeSet.remove(oldString);
			treeSet.add(newString);
			treeSetStringToWriFile(treeSet, "RecordClassName");
			return true;
		}
		else {
			return false;
		}
		
	}
 	//删除的是班级的文件 已测试，通过
	public boolean deleteClass(String className) {
		File templeFile=new File("f:\\"+className+".txt");
		if(!templeFile.exists())
			return false;
		TreeSet<String> treeSet=readFileToTreeSetString("RecordClassName");
		treeSet.remove(className);
		treeSetStringToWriFile(treeSet, "RecordClassName");
		templeFile.delete();
		return true;
	}
	//方法过于麻烦，而且人数统计不足，过时
	public void readClassPast(String classname) {
		File file=new File("f:\\"+classname+".txt");
		if(file.exists()){
		try {
			FileReader classFileReader=new FileReader("f:\\"+classname+".txt");
			BufferedReader classBufferedReader=new BufferedReader(classFileReader);
			char[] templeChar=new char[1024];
			int lenth=0;
			int line=0;
		try {
				while((lenth=classFileReader.read(templeChar))!=-1){
					System.out.println(new String(templeChar,0,lenth));
				}
				while(classBufferedReader.readLine()!=null)
					line++;
				System.out.println("学生人数共有："+line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  }
	}
	//已测试 通过   
	public void readClass(TreeSet<Student> treeSet,String className){ 
		for(Iterator<Student> iterator=treeSet.iterator();iterator.hasNext();){
			Student student=iterator.next();
			printStu(student);
		}
		System.out.println(className+"共有："+treeSet.size());
	}
 	
	public TreeSet<Student> readAllClass() {
		TreeSet<Student> treeSet=new TreeSet<>();
		TreeSet<String> treeSetString=readFileToTreeSetString("RecordClassName");
		for(Iterator<String> iterator=treeSetString.iterator();iterator.hasNext();){
			treeSet.addAll(readFileToTreeSet(iterator.next()));
		}
		return treeSet;
	}
	
	public void addStu(TreeSet<Student> treeSet,Student student){
			treeSet.add(student);
	}
	
	public Student creatStudent(String classNameString,String numberString,String sexString,String nameString,String mathString,String chinaString,String englishsString) {
		Student student=new Student(numberString, nameString);
		student.setClassName(classNameString);
		student.setSex(sexString);
		student.setMath(Double.parseDouble(mathString));
		student.setChina(Double.parseDouble(chinaString));
		student.setEnglish(Double.parseDouble(englishsString));
		return student;
	}
	
	public boolean deleteStu(TreeSet<Student> treeSet,Student student){
		
		if(!treeSet.contains(student))
			return false;
		treeSet.remove(student);
		return true;
	}

	public boolean correctStu(TreeSet<Student> treeSet,Student student,String type,String value) {
		if(treeSet.contains(student))
			return false;
		for(Iterator<Student> iterator=treeSet.iterator();iterator.hasNext();){
			if(iterator.next()==student){
				if(type.equals("className")){
					student.setClassName(value);
				}
				if(type.equals("number")){
					student.setNumber(value);
				}
				if(type.equals("name")){
					student.setName(value);
				}
				if (type.equals("sex")) {
					student.setSex(value);
				}
				if (type.equals("math")) {
					student.setMath(Double.parseDouble(value));
				}
				if (type.equals("China")) {
					student.setChina(Double.parseDouble(value));
				}
				if (type.equals("English")) {
					student.setEnglish(Double.parseDouble(value));
				}
			}
		}
		
		return true;
	}

	public Student seekOfNumber(TreeSet<Student> treeSet,String numberString) {
		Student student=new Student(null, null);
		for(Iterator<Student> iterator= treeSet.iterator();iterator.hasNext();){
			if((student=iterator.next()).getNumber().equals(numberString)){
				return student;
			}
		}
		return null;
		
	}
	
	public TreeSet<Student> seekOfNumber(String numberString){
		TreeSet<Student> templeTreeSet=readAllClass();
		TreeSet<Student> treeSet=new TreeSet<>();
		for(Iterator<Student> iterator=templeTreeSet.iterator();iterator.hasNext();){
			Student student=iterator.next();
			if(student.getNumber().equals(numberString)){
				treeSet.add(student);
			}
		}
		return treeSet;
	}
	
	public Student seekOfName(TreeSet<Student> treeSet,String nameString) {
		Student student=new Student(null, null);
		for(Iterator<Student> iterator=treeSet.iterator();iterator.hasNext();){
			if((student=iterator.next()).getName().equals(nameString));
			return student;
		}
	    return null;
	}
	
	public TreeSet<Student> seekOfName(String nameString) {
		TreeSet<Student> templeTreeSet=readAllClass();
		TreeSet<Student> treeSet=new TreeSet<>();
		for(Iterator<Student> iterator=templeTreeSet.iterator();iterator.hasNext();){
			Student student=iterator.next();
			if(student.getName().equals(nameString)){
				treeSet.add(student);
			}
		}
		
		return treeSet;
	}
	//待改善
	public TreeSet<Student> seekOfScoreLess(double score) {
		TreeSet<Student> templeTreeSet=readAllClass();
		TreeSet<Student> treeSet=new TreeSet<>();
		for(Iterator<Student> iterator=templeTreeSet.iterator();iterator.hasNext();){
			Student student=iterator.next();
			if(student.getAllScore()<=score){
				treeSet.add(student);
			}
		}
		return treeSet;
	}
	//待改善
	public TreeSet<Student> seekOfScoreMore(double score){
		
		TreeSet<Student> templeTreeSet=readAllClass();
		TreeSet<Student> treeSet=new TreeSet<>();
		for(Iterator<Student> iterator=templeTreeSet.iterator();iterator.hasNext();){
			Student student=iterator.next();
			if(student.getAllScore()>score){
				treeSet.add(student);
			}
		}
		return treeSet;
	}
	//已测试 通过
	public TreeSet<Student> seekOfClass(String className){
		TreeSet<Student> treeSet=readFileToTreeSet(className);
		return treeSet;
	}			
	//已测试，通过
	private boolean copyFile(String oldName,String newName) {
		if(oldName==newName)
			return true;
		File templeFile=new File("f:\\"+oldName+".txt");
		if(!templeFile.exists())
			return false;
		templeFile=new File("f:\\"+newName+".txt");
		if(templeFile.exists()){
			return false;
		}
		else {
			try {
				FileReader resourceFile=new FileReader("f:\\"+oldName+".txt");
			try {
				FileWriter copyFile=new FileWriter("f:\\"+newName+".txt");
				char[] store=new char[1024];
				int lenth=0;
				while((lenth=resourceFile.read(store))!=-1){
					copyFile.write(store,0,lenth);
				}
				resourceFile.close();
				copyFile.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return true;
	}
	//已测试 通过
	public TreeSet<Student> readFileToTreeSet(String classname){
		TreeSet<Student> treeSet=new TreeSet<Student>();
		try {
			File file=new File("f:\\"+classname+".txt");
			if(!file.exists())
				return null;
			FileReader fileReader=new FileReader("f:\\"+classname+".txt");
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			String templeString=null;
			try {
				while((templeString =bufferedReader.readLine())!=null){
					if(templeString.length()<3)
						continue;
					Student student=newStudent(templeString);
					treeSet.add(student);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return treeSet;
	}
 	//已测试 通过
	public TreeSet<String> readFileToTreeSetString(String classname){
		TreeSet<String> treeSet=new TreeSet<>();
		try {
			File file=new File("f:\\"+classname+".txt");
			if(!file.exists()){
				System.out.println("文件不存在");
				return null;
			}
				
			FileReader fileReader=new FileReader("f:\\"+classname+".txt");
			BufferedReader bufferedReader=new BufferedReader(fileReader);
			String templeString=null;
			try {
				while((templeString =bufferedReader.readLine())!=null){
					if(templeString.length()<1)
						continue;
					else {
						treeSet.add(templeString);
					}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return treeSet;
	}
	
	//已测试，通过
	public String printStu(Student student) {
		double d=student.getChina()+student.getMath()+student.getEnglish();
		int s=(int) (d/3);
		return (student.getClassName()+" "+student.getNumber()+"  "+student.getSex()+"  "+student.getName()+"   "+student.getMath()+"   "+
				student.getChina()+"   "+student.getEnglish()+"    "+d+"   "+s+"   ");
	}
	//已测试 通过
	private Student newStudent(String templeString) {
		// TODO Auto-generated method stub
//		char[] templeChar=templeString.toCharArray();
//		int len=0;
//		int start=0;
//		String[] strings=new String[6];
//		while(len>templeChar.length){
//			if(templeChar[len]==','){
//				int begin=0;
//				strings[begin]=new String(templeChar,start,len);
//				begin++;
//				start=len+1;
//			}
//			len++;
//		}失败的例子，但是分析不出原因。
		StringBuilder builder=new StringBuilder();
		String[] strings=new String[20];
		char c;
		int len=0;
		for (int i = 0; i < templeString.length(); i++) {
			c=templeString.charAt(i);
			if(c==' '){
				strings[len]=builder.toString();
				builder=new StringBuilder();
				len++;
			}else{
				builder.append(c);
			}
		}
		Student student=new Student(strings[1], strings[3]);
		student.setClassName(strings[0]);
		student.setSex(strings[2]);
		student.setMath(Double.parseDouble(strings[4]));
		student.setChina(Double.parseDouble(strings[5]));
		student.setEnglish(Double.parseDouble(strings[6]));
		return student;
	}
	//已测试 通过
	public boolean treeSetToWriFile(String classname,TreeSet<Student> treeSet) throws InterruptedException {
		int templeInt=0;
		File file=new File("f:\\"+classname+".txt");
		if(!file.exists()){
			return false;
		}
		for(Iterator<Student> iterator=treeSet.iterator();iterator.hasNext();templeInt++){
			Student student=iterator.next();
			try {
				FileWriter fileWriter=new FileWriter("f:\\"+classname+".txt",templeInt>0);
				BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
				fileWriter.write(student.getClassName()+" "+student.getNumber()+" "+student.getSex()+" "+student.getName()+" "+student.getMath()+" "+
				student.getChina()+" "+student.getEnglish()+" "+student.getAllScore()+" "+student.getAverageScore()+" "+"\r\n");
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean treeSetStringToWriFile(TreeSet<String> treeSet,String fileNmaeString) {
		FileWriter fileWriter=null;
		File file=new File("f:\\"+fileNmaeString+".txt");
		if(!file.exists()){
			return false;
		}
		int temple=0;
		for(Iterator<String> iterator=treeSet.iterator();iterator.hasNext();temple++){
			try {
				fileWriter=new FileWriter("f:\\"+fileNmaeString+".txt",temple>0);
				BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
				bufferedWriter.write(iterator.next()+"\r\n");
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	
	
	
}