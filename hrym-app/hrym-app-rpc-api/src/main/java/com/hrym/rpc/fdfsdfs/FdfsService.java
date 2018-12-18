package com.hrym.rpc.fdfsdfs;

import com.hrym.common.base.BaseResult;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by hong on 2017/7/6.
 */
public interface FdfsService {

    /**
     * fdfs文件上传接口
     *
     * @param in 流
     * @param realName 源文件名
     * @param fileLength 文件大小
     * @return
     */
    Map<String,String> uploadStream(long fileLength,String realName,InputStream in);

}
