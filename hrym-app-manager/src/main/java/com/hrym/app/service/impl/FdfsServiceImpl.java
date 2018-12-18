package com.hrym.app.service.impl;

import com.google.common.collect.Maps;
import com.hrym.app.service.FdfsService;
import com.hrym.app.service.impl.common.*;
import com.hrym.common.base.BaseConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by hong on 2017/7/6.
 */
@Service
public class FdfsServiceImpl implements FdfsService {

    private static final Log log = LogFactory.getLog(FdfsServiceImpl.class);

    static {
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    private StorageClient1 getStorageClient() throws Exception {

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

        return new StorageClient1(ts, ss);

    }

    @Override
    public Map<String,String> uploadStream(long fileLength,String realName, InputStream in) {

        long t1 = System.currentTimeMillis();

        Map<String,String> ret = Maps.newHashMap();
        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        String fileId = "";

        //附件是否是zip包
        boolean zipSuffix = false;

        try {
            StorageClient1 sc1 = this.getStorageClient();

            long t2 = System.currentTimeMillis();
            log.info(Thread.currentThread()+"init client start"+ (t2-t1));

            String fileExtName = "";
            if (realName.contains(".")) {
                fileExtName = realName.substring(realName.lastIndexOf(".") + 1);
                if(fileExtName.equals("zip"))
                    zipSuffix = true;
            } else {
                fileExtName = "";
            }
            // 设置存储附加字段
            NameValuePair[] metaList = new NameValuePair[2];
            metaList[0] = new NameValuePair("fileName", realName);
            metaList[1] = new NameValuePair("fileExtName", fileExtName);

            fileId = sc1.upload_file1("",fileLength,new UploadStream(in,fileLength),fileExtName,metaList);
            log.info(Thread.currentThread()+"upload spend"+ (System.currentTimeMillis()-t2));

            log.info(Thread.currentThread()+"Upload local file success, fileid=" + fileId);
        } catch (Exception ex) {
            ex.printStackTrace();


            code = BaseConstants.GWSCODE4004;
            message = BaseConstants.GWSMSG4004;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ret.put("code",code);
        ret.put("msg",message);
        ret.put("fileRealName",realName);

        String storePath = "";
        if(zipSuffix) {

            // zip包时特殊处理，加上filename，为zip解压后的文件夹名称
            storePath = StringUtils.isNotBlank(fileId)?ClientGlobal.fastdfs_url_prefix+fileId+"?filename="+realName:"";
        } else {
            storePath = StringUtils.isNotBlank(fileId)?ClientGlobal.fastdfs_url_prefix+fileId:"";
        }

        ret.put("fileStorePath",storePath);
        return ret;
    }
}
