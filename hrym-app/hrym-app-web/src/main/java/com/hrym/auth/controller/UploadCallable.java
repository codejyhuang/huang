package com.hrym.auth.controller;

import com.hrym.rpc.fdfsdfs.FdfsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * Created by hong on 2017/10/17.
 */
public class UploadCallable implements Callable {
    private String fileName;
    private long size;
    private InputStream in;
    private CountDownLatch latch;

    private static final Log log = LogFactory.getLog(UserController.class);


    private FdfsService fdfsService;

    public UploadCallable(long size, String fileName, InputStream in,CountDownLatch latch,FdfsService fdfsService) {
        this.size = size;
        this.fileName = fileName;
        this.in = in;
        this.latch = latch;
        this.fdfsService = fdfsService;
    }

    @Override
    public Object call() throws Exception {

        log.info(Thread.currentThread()+",call start    "+ fileName +",time start="+System.currentTimeMillis());

        Map<String,String> ret =  fdfsService.uploadStream(size,fileName,in);
        log.info(Thread.currentThread()+",call end   "+ fileName +",time end="+System.currentTimeMillis());
        latch.countDown();
        return ret;
    }
}

