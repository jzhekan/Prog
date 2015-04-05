package com.company;

import java.io.*;
import java.util.List;

public class Chunker implements Processor {

    private int chunkSize = 20;
    private ByteArrayOutputStream os;

    private class ChunkerEncoder extends OutputStream{

        private byte buf[];

        public ChunkerEncoder() {
            this.buf = new byte[10];
        }

        public ChunkerEncoder(int size) {
            this.buf = new byte[size];
        }

        @Override
        public void write(int b) throws IOException {

        }

        public void write(byte[] data) {

            try {
                os = new ByteArrayOutputStream();

                int n = data.length / chunkSize;
                int tail = data.length % chunkSize;
                int offset = 0;
                String head = Integer.toHexString(chunkSize) + "\r\n";

                for (int i = 0; i < n; i++) {
                    os.write(head.getBytes());
                    os.write(data, offset, chunkSize);
                    os.write("\r\n".getBytes());
                    offset += chunkSize;
                }
                if (tail > 0) {
                    head = Integer.toHexString(tail) + "\r\n";
                    os.write(head.getBytes());
                    os.write(data, offset, tail);
                    os.write("\r\n".getBytes());
                }

                os.write("0\r\n\r\n".getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }


        }

    }

    private class ChunkerDecoder extends InputStream{

        byte buf[];

        @Override
        public int read() throws IOException {
            return -1;

        }

        public int read(byte[] data)  {

            os = new ByteArrayOutputStream();
            StringBuilder str = new StringBuilder("");
            int b1;
            int count =0;
            int lenthOfBlock =0;

            while (count < data.length) {

                if (lenthOfBlock!=0){

                    os.write(data, count + 1, lenthOfBlock);
                    count = count + lenthOfBlock+3;
                    lenthOfBlock = 0;
                    str.delete(0,str.length());
                }

                if( data[count] == 13 ) {
                    try{
                        b1 = data[count+1];
                        if ( b1 == 10 )
                            lenthOfBlock = Integer.parseInt(str.toString(),16);
                        else
                            str.append((char)b1);
                    }catch(IndexOutOfBoundsException|NumberFormatException e)
                    {
                        System.err.println(e.getMessage());
                    }

                }
                else
                    str.append((char) data[count]);

                //System.out.println( data[count]);
                count++;
            }


          return -1;
        }


    }


    public Chunker(int chunkSize) {
        this.chunkSize = chunkSize;
    }
    
    public int getChunkSize() {
        return chunkSize;
    }   
    
    public void setChunkSize(int value) {
        chunkSize = value;
    }

    public byte[] process(byte[] data, List<String> headers) {

        new ChunkerEncoder().write(data);
        headers.add("Transfer-Encoding: chunked\r\n");
        if (os!=null) {

            //пробуем декодировать
            /*
            new ChunkerDecoder().read(os.toByteArray());
            try {
                FileOutputStream f1 = new FileOutputStream("D:\\1.txt");
                os.writeTo(f1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            */

            return os.toByteArray();
        }
        else
           return null;




    }

    public static void main(String[] args) throws Exception {
        byte[] buf = new byte[10500];

        RandomAccessFile f = new RandomAccessFile("D:\\JavaProjects\\Prog\\httpserver\\src\\com\\company\\index.html", "r");
        try {
            buf = new byte[(int)f.length()];
            f.read(buf, 0, buf.length);
        }
        catch(Exception e){

        }
        finally {
            f.close();
        }

      new Chunker(20).process(buf,null);
    }
}

