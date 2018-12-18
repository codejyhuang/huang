package com;

import com.hrym.rpc.fdfsdfs.common.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hong on 2017/10/14.
 */
public class FastDfsTest {

    private static final Log log = LogFactory.getLog(FastDfsTest.class);

    public static void main(String[] args) throws Exception{

        long t = System.currentTimeMillis();
        ClientGlobal.initByProperties("fastdfs-client.properties");


        ExecutorService pool = Executors.newFixedThreadPool(10);
        final CountDownLatch latch = new CountDownLatch(10);

        int i = 10;
        while(i>0){

            Callable call = new MyCallable(latch);

            pool.submit(call);
            i--;
        }

        try {
            //阻塞，直到latch为0才执行下面的输出语句
            latch.await();
            System.out.println("所有线程执行完毕！"+(System.currentTimeMillis()-t));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        TrackerGroup tg = ClientGlobal.getG_tracker_group();
//        TrackerClient tc = new TrackerClient(tg);
//        TrackerServer ts = tc.getConnection();
//
//        if (ts == null) {
//            log.error("getConnection return null");
//        }
//
//        StorageServer ss = tc.getStoreStorage(ts);
//        if (ss == null) {
//            log.error("getStorageServer is null");
//        }
//
//        StorageClient1 sc = new StorageClient1(ts, ss);
//
//        File file = new File("/Users/hong/Desktop/宝2/WechatIMG987.jpeg");
//        FileInputStream fin=new FileInputStream(file);
//
//        NameValuePair[] metaList = new NameValuePair[2];
//        metaList[0] = new NameValuePair("fileName", "qwewe");
//        metaList[1] = new NameValuePair("fileExtName", "dddd");
//
//
//        log.info("now time "+ System.currentTimeMillis());
//        String mm = sc.upload_file1("",fin.available(),new UploadStream(fin,fin.available()),"jpeg",metaList);
//        log.info("now1 time "+ System.currentTimeMillis()+","+mm);
//
//        file = new File("/Users/hong/Desktop/宝2/WechatIMG987.jpeg");
//        fin=new FileInputStream(file);
//
//        mm = sc.upload_file1("",fin.available(),new UploadStream(fin,fin.available()),"jpeg",metaList);
//        log.info("now2 time "+ System.currentTimeMillis()+","+mm);
//
//        file = new File("/Users/hong/Desktop/宝2/WechatIMG987.jpeg");
//        fin=new FileInputStream(file);
//        mm = sc.upload_file1("",fin.available(),new UploadStream(fin,fin.available()),"jpeg",metaList);
//        log.info("now3 time "+ System.currentTimeMillis()+","+mm);
//
//        file = new File("/Users/hong/Desktop/宝2/WechatIMG987.jpeg");
//        fin=new FileInputStream(file);
//        mm = sc.upload_file1("",fin.available(),new UploadStream(fin,fin.available()),"jpeg",metaList);
//        log.info("now4 time "+ System.currentTimeMillis()+","+mm);
//
//        file = new File("/Users/hong/Desktop/宝2/WechatIMG987.jpeg");
//        fin=new FileInputStream(file);
//        mm = sc.upload_file1("",fin.available(),new UploadStream(fin,fin.available()),"jpeg",metaList);
//        log.info("now5 time "+ System.currentTimeMillis()+","+mm);
//
//        file = new File("/Users/hong/Desktop/宝2/WechatIMG987.jpeg");
//        fin=new FileInputStream(file);
//        mm = sc.upload_file1("",fin.available(),new UploadStream(fin,fin.available()),"jpeg",metaList);
//        log.info("now6 time "+ System.currentTimeMillis()+","+mm);
//
//        file = new File("/Users/hong/Desktop/宝2/WechatIMG987.jpeg");
//        fin=new FileInputStream(file);
//        mm = sc.upload_file1("",fin.available(),new UploadStream(fin,fin.available()),"jpeg",metaList);
//        log.info("now7 time "+ System.currentTimeMillis()+","+mm);
//
//        file = new File("/Users/hong/Desktop/宝2/WechatIMG987.jpeg");
//        fin=new FileInputStream(file);
//        mm = sc.upload_file1("",fin.available(),new UploadStream(fin,fin.available()),"jpeg",metaList);
//        log.info("now8 time "+ System.currentTimeMillis()+","+mm);

        log.info("total time "+(System.currentTimeMillis() - t));


    }
}


class MyCallable implements Callable {

    private static final Log log = LogFactory.getLog(MyCallable.class);


    private CountDownLatch latch;

    MyCallable(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public Object call() throws Exception {


        log.info(Thread.currentThread()+","+System.currentTimeMillis());
        TrackerGroup tg = ClientGlobal.getG_tracker_group();
        TrackerClient tc = new TrackerClient(tg);
        TrackerServer ts = tc.getConnection();

        if (ts == null) {
            log.error("getConnection return null");
        }

        StorageServer ss = tc.getStoreStorage(ts);
        if (ss == null) {
            log.error("getStorageServer is null");
        }

        StorageClient1 sc = new StorageClient1(ts, ss);

        long t = System.currentTimeMillis();
        NameValuePair[] metaList = new NameValuePair[2];
        metaList[0] = new NameValuePair("fileName", "qwewe");
        metaList[1] = new NameValuePair("fileExtName", "dddd");

        File file = new File("/Users/hong/Desktop/宝2/WechatIMG987.jpeg");
        FileInputStream fin=new FileInputStream(file);
        String mm = sc.upload_file1("",fin.available(),new UploadStream(fin,fin.available()),"jpeg",metaList);
        log.info( Thread.currentThread() + " end time "+ (System.currentTimeMillis()-t)+","+mm);

        latch.countDown();

        return "";
    }
}
