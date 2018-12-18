package com.hrym.rpc.app.config;

import com.hrym.common.base.BaseConstants;
import com.hrym.common.base.BaseResult;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * es通用接口开发
 * Created by mj on 2018/3/19.
 */
public class ESCommon {

    @Autowired
    private TransportClient transportClient;
    //索引名称
    private String indexName;
    //索引类型
    private String indexType;


    /**
     * 根据id查询文档接口开发
     * @param id
     * @return
     */
    public BaseResult getById(String id){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //设置获取的索引、类型、id
        GetResponse result = transportClient.prepareGet(indexName,indexType,id)
                .get();

        return new BaseResult(code,message,result.getSource());
    }

    /**
     * 增加文档接口开发
     * @param object
     * @return
     */
    public BaseResult add(Object object){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        try {
            IndexResponse result = transportClient.prepareIndex(indexName,indexType)
                    .setSource(object)
                    .get();
            return new BaseResult(code,message,result.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文档接口开发
     * @param id
     * @return
     */
    public BaseResult delete(String id){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        DeleteResponse result = transportClient.prepareDelete(indexName,indexType,id)
                .get();

        return new BaseResult(code,message,result.getResult().toString());
    }

    /**
     * 根据id更新文档接口开发
     * @param id
     * @param object
     * @return
     */
    public BaseResult update(String id,Object object){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;
        //构造更新实例
        UpdateRequest request = new UpdateRequest(indexName,indexType,id);
        try {
            //设置更新内容
            request.doc(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            UpdateResponse result = transportClient.update(request).get();
            return new BaseResult(code,message,result.getResult().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据条件复合查询接口开发
     * @param boolQuery
     * @param pageNo
     * @param pageSize
     * @return
     */
    public BaseResult query(BoolQueryBuilder boolQuery,Integer pageNo,Integer pageSize){

        String code = BaseConstants.GWSCODE0000;
        String message = BaseConstants.GWSMSG0000;

        SearchRequestBuilder builder = transportClient.prepareSearch(indexName)
                .setTypes(indexType)     //索引类型
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)    //数据量大时用SearchType.QUERY_THEN_FETCH
                .setQuery(boolQuery)  //设置查询条件
                .setFrom(pageNo)
                .setSize(pageSize);
        //查看查询条件创建的格式
        System.out.println(builder);
        //获取查询结果
        SearchResponse response = builder.get();

        List<Map<String,Object>> result = new ArrayList<>();
        for (SearchHit hit : response.getHits()){
            result.add(hit.getSource());
        }
        return new BaseResult(code,message,result);
    }


    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public TransportClient getTransportClient() {
        return transportClient;
    }

    public void setTransportClient(TransportClient transportClient) {
        this.transportClient = transportClient;
    }

    public void setEsIndex(String indexName,String indexType,TransportClient transportClient){
        this.indexName = indexName;
        this.indexType = indexType;
        this.transportClient = transportClient;
    }

    public static void main(String[] args) {
        new ESCommon().getById("1");
    }
}
