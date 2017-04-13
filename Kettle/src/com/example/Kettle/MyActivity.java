package com.example.Kettle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MyActivity extends Activity {

     public static final String ip="115.29.4.152";
     public static final int port=1883;

     Button open;
     Button keep;
     Button process;
     Button close;
     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.main);

          open = (Button) findViewById(R.id.open_bt);
          keep = (Button) findViewById(R.id.keep_bt);
          process = (Button) findViewById(R.id.process_bt);
          close = (Button) findViewById(R.id.close_bt);

          //a = '\x10.\x00\x04MQTT\x04\xc2\x00\x1e\x00\x0can3158786236\x00\nsmalKettle\x00\x08smal2014'
          final byte[] byte1 = {0x10, '.', 0x00, 0x04, 'M', 'Q', 'T', 'T', 0x04, (byte) 0xc2, 0x00, 0x1e, 0x00, 0x0c};
          final byte[] byte2 = ("an3158786236").getBytes();
          final byte[] byte3 = {0x00, '\n'};
          final byte[] byte4 = ("smalKettle").getBytes();
          final byte[] byte5 = {0x00, 0x08};
          final byte[] byte6 = ("smal2014").getBytes();

          // b = '\x82\x17\x00\x01\x00\x12/n/A1/an3158786236\x00'
          final byte[] byte7 = {(byte) 0x82, 0x17, 0x00, 0x01, 0x00,0x12};
          final byte[] byte8 = ("/n/A1/an3158786236").getBytes();
          final byte[] byte9 = {0x00};

          //c = '\x82\'\x00\x02\x00\"/p/D2/201502001855/A1/an3158786236\x00'
          final byte[] byte10 = {(byte) 0x82, '\'',0x00, 0x02, 0x00,'\"'};
          final byte[] byte11 = ("/p/D2/201502001855/A1/an3158786236").getBytes();
          final byte[] byte12 = {0x00};

          //d = '\x82\x17\x00\x03\x00\x12/b/D2/201502001855\x00'
          final byte[] byte13 = {(byte) 0x82, 0x17, 0x00, 0x03, 0x00,0x12};
          final byte[] byte14 = ("/b/D2/201502001855").getBytes();
          final byte[] byte15 = {0x00};

          //烧水
          //f = '0>\x00\x12/q/D2/2015020018557A1an3158786236
          // \x0b\x00\x00\x00\x00\x00\x00\x01\x02\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00d\x00\x00\x00\x00\x00\x00'
          final byte[] byte16 = {'0' ,'>', 0x00, 0x12};
          final byte[] byte17 = ("/q/D2/2015020018557A1an3158786236").getBytes();
          final byte[] byte18 = {0x0b, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 'd', 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

          //保温
          //h = '0>\x00\x12/q/D2/2015020018557A1an3158786236\x0c\x00\x00\x00\x00\x00\x00\x00\x02\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x012\xd0\x02\x00\x00\x00\x00'
          final byte[] byte19 = {'0' ,'>', 0x00, 0x12};
          final byte[] byte20 = ("/q/D2/2015020018557A1an3158786236").getBytes();
          final byte[] byte21 = {0x0c, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, '2', (byte) 0xd0, 0x02, 0x00, 0x00, 0x00, 0x00};

          //除氯
          //j = '0>\x00\x12/q/D2/2015020018557A1an3158786236\r\x00\x00\x00\x00\x00\x00\x01\x02\x03\x03\x00\x00\x00\x00\x00\x00\x00\x00\x00d\x00\x00\x00\x00\x00\x00'
          final byte[] byte22 = {'0' ,'>', 0x00, 0x12};
          final byte[] byte23 = ("/q/D2/2015020018557A1an3158786236").getBytes();
          final byte[] byte24 = {'\r', 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01, 0x02, 0x03, 0x03, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 'd', 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};

          //关闭
          //i = '0#\x00\x12/q/D2/2015020018558A1an3158786236'
          final byte[] byte25 = {'0', '#', 0x00, 0x12};
          final byte[] byte26 = ("/q/D2/2015020018558A1an3158786236").getBytes();

          new Thread(){
               public void run(){
                    try{
                         final Socket socket=new Socket(InetAddress.getByName(ip),port);
                         Log.i("MyActivity", "连接建立成功");
                         final DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                         outputStream.write(byte1);
                         outputStream.write(byte2);
                         outputStream.write(byte3);
                         outputStream.write(byte4);
                         outputStream.write(byte5);
                         outputStream.write(byte6);

                         outputStream.write(byte7);
                         outputStream.write(byte8);
                         outputStream.write(byte9);
                         outputStream.write(byte10);
                         outputStream.write(byte11);
                         outputStream.write(byte12);

                         outputStream.write(byte13);
                         outputStream.write(byte14);
                         outputStream.write(byte15);

                         while(true){
                              open.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                        try {
                                             Log.i("MyActivity", "open button clicked");
                                             outputStream.write(byte16);
                                             outputStream.write(byte17);
                                             outputStream.write(byte18);
                                             Log.i("MyActivity", "烧水数据发送成功");

                                        } catch (Exception e) {
                                             e.printStackTrace();
                                        }
                                   }
                              });

                              keep.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                        try {
                                             Log.i("MyActivity", "keep button clicked");
                                             outputStream.write(byte19);
                                             outputStream.write(byte20);
                                             outputStream.write(byte21);
                                             Log.i("MyActivity", "保温数据发送成功");

                                        } catch (Exception e) {
                                             e.printStackTrace();
                                        }
                                   }
                              });

                              process.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                        try {
                                             Log.i("MyActivity", "process button clicked");
                                             outputStream.write(byte22);
                                             outputStream.write(byte23);
                                             outputStream.write(byte24);
                                             Log.i("MyActivity", "烧水数据发送成功");

                                        } catch (Exception e) {
                                             e.printStackTrace();
                                        }
                                   }
                              });

                              close.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                        try {
                                             Log.i("MyActivity", "close button clicked");
                                             outputStream.write(byte25);
                                             outputStream.write(byte26);
                                             Log.i("MyActivity", "保温数据发送成功");

                                        } catch (Exception e) {
                                             e.printStackTrace();
                                        }
                                   }
                              });

                         }

                    }catch(Exception e){
                         System.out.println("客户端异常："+e.getMessage());
                    }

               }
          }.start();

     }
}