package com.hrym.auth.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hrym.common.annotation.Allowed;
import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseController;
import com.hrym.common.base.BaseResult;
import com.hrym.rpc.fdfsdfs.FdfsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by hong on 2017/7/7.
 */
@Controller
@RequestMapping(value = "/hrym/api")
public class FileController  extends BaseController {

    @Autowired
    private FdfsService fdfsService;

     //定义线程池大小为9，发表文章最多传9个图片
    private ExecutorService pool = Executors.newFixedThreadPool(9);

    private static final Log log = LogFactory.getLog(UserController.class);

    /**
     * 文件上传
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadFiles")
    @Allowed
    @ResponseBody
    public BaseResult upload(HttpServletRequest request) throws Exception {


        List<String> storageIds = Lists.newArrayList();
        //文件上传的请求
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        //获取请求的参数
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();

        final CountDownLatch latch = new CountDownLatch(fileMap.entrySet().size());

        Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();

        List<Future> futureList = new ArrayList<Future>();

        final List<Map> fileList = Lists.newArrayList();
        //用hasNext() 判断是否有值，用next()方法把元素取出。
        while(it.hasNext()){
            Map.Entry<String, MultipartFile> entry = it.next();
            MultipartFile mFile = entry.getValue();

            String fileName = mFile.getOriginalFilename();

            Callable call = new UploadCallable(mFile.getSize(),fileName,mFile.getInputStream(),latch,fdfsService);

            futureList.add(pool.submit(call));

        }

        try {
            //阻塞，直到latch为0才执行下面的输出语句
            latch.await();
            System.out.println("所有线程执行完毕！");
            for(Future f:futureList) {

                Map map = (Map) f.get();
                fileList.add(map);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Map<String,List> ret = Maps.newHashMap();
        ret.put("storeInfos",fileList);
        return new BaseResult(BaseConstants.GWSCODE0000,BaseConstants.GWSMSG0000,ret);

    }

}
