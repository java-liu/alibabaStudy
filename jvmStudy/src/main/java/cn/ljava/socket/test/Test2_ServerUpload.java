package cn.ljava.socket.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Test2_ServerUpload {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);

        while (true){
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {
                    InputStream is = socket.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    PrintStream ps = new PrintStream(socket.getOutputStream());
                    String fileName = br.readLine();

                    File dir = new File("upload");
                    dir.mkdir();
                    File file = new File(dir, fileName);
                    if(file.exists()){
                        ps.println("存在");
                        socket.close();
                        return;
                    }else{
                        ps.println("不存在");
                    }
                    FileOutputStream fos = new FileOutputStream(file);
                    byte[] arr = new byte[1024];
                    int len;
                    while ((len = is.read(arr))!= -1){
                        fos.write(arr,0, len);
                    }
                    fos.close();
                    socket.close();
                }catch (IOException e){

                }
            }).start();
        }
    }
}
