package com.joseph.standardwebproject.service;

import org.junit.jupiter.api.*;

import java.io.*;

class InputOutputTest {

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before All");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After All");
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void addUser() {
    }

    @Test
    public void testFileReader() throws IOException {
        File file = new File("hello.txt");
        FileReader fr = new FileReader(file);
        char[] cbuf = new char[5];
        int len;
        while( ( len = fr.read(cbuf)) != -1){
            String str = new String(cbuf,0,len);
            System.out.print(str);
        }
        fr.close();
    }
    @Test
    public void testFileWriter() throws IOException {
        File file = new File("dream.txt");
        FileWriter fw = new FileWriter(file);
        fw.write("I have a dream");
        fw.close();
    }
    @Test
    public void testFileReaderAndWriter() throws IOException {
        File srcFile = new File("hello.txt");
        File destFile = new File("hello2.txt");

        FileReader fr = new FileReader(srcFile);
        FileWriter fw = new FileWriter(destFile);

        char[] cbuf = new char[5];
        int len;
        while((len = fr.read(cbuf)) != -1){
            fw.write(cbuf,0,len);
        }
        fr.close();
        fw.close();
    }

    @Test
    public void testFileInputStreamAndOutputStream() throws IOException {
        File srcFile = new File("hello.jpg");
        File destFile = new File("hello2.jpg");

        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }
        fis.close();
        fos.close();
    }

    @Test
    public void testBufferedStream() throws IOException {
        File srcFile = new File("hello.avi");
        File destFile = new File("hello2.avi");

        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte[] buffer = new byte[1024];
        int len;
        while((len = bis.read(buffer)) != -1){
            bos.write(buffer,0,len);
        }
        bis.close();
        bos.close();
    }
    @Test
    public void testBufferedReaderAndWriter() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("hello.txt")));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("hello2.txt")));
        //方式1.使用char[]数组，和Stream方式一样
        //char[] buffer = new char[1024];
        //int len;
        //while((len = br.read(buffer)) != -1){
        //    bw.write(buffer,0,len);
        //}
        //方式2.使用String,但br.readLine()不包含换行符
        String line;
        while( (line = br.readLine()) != null ){
            //方法1：手工换行
            //bw.write(line + "\n");
            //方法2：
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        br.close();
    }
    @Test
    public void testInputStreamReaderAndOutputStreamWriter() throws IOException {
        FileInputStream fis = new FileInputStream("jdbc-utf8.txt");
        FileOutputStream fos = new FileOutputStream("jdbc-gbk.txt");

        InputStreamReader isr = new InputStreamReader(fis, "utf-8");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"gbk");

        char[] buffer = new char[8];
        int len;
        while((len = isr.read(buffer)) != -1){
            osw.write(buffer,0,len);
        }
        osw.close();
        isr.close();
    }

    @Test
    public void testSystemIn() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        while(true){
            String line = br.readLine();
            System.out.println(line.toUpperCase());
            if("exit".equalsIgnoreCase(line)){
                System.out.println("app exit");
                break;
            }
        }
        br.close();
    }

    @Test
    public void testPrintStream() throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(new File("D:\\my.txt"));
        PrintStream ps = new PrintStream(fos,true);
        System.setOut(ps);//把标准输出流从控制台输出改为文件输出

        for(int i = 0; i <=255; i++){
            System.out.print((char) i);//输出ASCII字符
            if(i % 50 == 0){
                System.out.println();//每50个字符换一行
            }
        }
        ps.close();

    }

    @Test
    public void testDataStream() throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
        dos.writeUTF("Joseph");
        dos.writeInt(23);
        dos.writeBoolean(true);
        dos.flush();//刷新操作：将内存中的数据写入文件
        dos.close();

        DataInputStream  dis = new DataInputStream(new FileInputStream("data.txt"));
        String name = dis.readUTF();
        int age = dis.readInt();
        boolean isMale = dis.readBoolean();
        dis.close();
    }

    @Test
    public void testRandomAccessFile() throws IOException {
        File file = new File("my.txt");
        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        raf.seek(3);//将指针调到角标为3的插入位置
        //保存指针3后面的所有数据到StringBuilder中
        StringBuilder sb = new StringBuilder((int) file.length());
        byte[] buffer = new byte[20];
        int len;
        while((len = raf.read(buffer)) != -1){
            sb.append(new String(buffer,0,len));
        }
        //调回指针，写入xyz
        raf.seek(3);
        raf.write("xyz".getBytes());
        //这时指针已经位于xyz之后，直接将StringBuilder中的内容写入到文件即可
        raf.write(sb.toString().getBytes());
        raf.close();
    }
}