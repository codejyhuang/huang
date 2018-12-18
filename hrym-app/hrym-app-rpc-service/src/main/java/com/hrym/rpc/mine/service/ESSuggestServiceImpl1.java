//package com.hrym.rpc.mine.service;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.common.collect.Maps;
//import com.hrym.common.base.BaseConstants;
//import com.hrym.common.base.BaseResult;
//import com.hrym.common.base.PageInfo;
//import com.hrym.rpc.app.common.constant.MyParam;
//import com.hrym.rpc.app.dao.model.VO.SearchResultVO;
//import com.hrym.rpc.app.dao.model.VO.TaskItemVO;
//import com.hrym.rpc.app.dao.model.task.TaskItem;
//import com.hrym.rpc.mine.ESSuggestService;
//import net.sf.json.JSONObject;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.text.Text;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
//import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
//import org.elasticsearch.search.sort.SortOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import scala.annotation.meta.param;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by xiaohan on 2017/11/13.
// */
//public class ESSuggestServiceImpl1 implements ESSuggestService {
//
//    @Autowired
//    private TransportClient transportClient;
//
//    private static final String IndexName = "hrym-elasticsearch";
//    private static ObjectMapper mapper = new ObjectMapper();
//
//
//    /**
//     * 基于类型查看全部
//     * @param param
//     * @return
//     */
//    @Override
//    public BaseResult searchAll(MyParam param) {
//
//        String code = BaseConstants.GWSCODE0000;
//        String message = BaseConstants.GWSMSG0000;
//
//        List<Map<String, Object>> mapList = new ArrayList<>();
//        PageInfo pageInfo = null;
//        QueryBuilder queryBuilders = null;
//        String typeName="resource";
//
//
//        //设置高亮
//        HighlightBuilder hiBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
//        hiBuilder.preTags("<span style=\"color: fc9153\">");
//        hiBuilder.postTags("</span>");
//
//        //如果传入的字段不为空，则根据字段进行查询
//        if (!"".equals(param.getFilterStr()) && param.getFilterStr() != null) {
//            //查询字段
//            queryBuilders = QueryBuilders.queryStringQuery(param.getFilterStr());
//            //设置高亮区域
//            hiBuilder
//                    .field("music_name")
//                    .field("title_desc").field("album_name")
//                    .field("title_name")
//                    .field("title_intro").field("item_desc")
////                    .field("text_content")
//                     .field("text_name");
//        }
//        //如果传入的字段为空，则查询所有
//        else {
//            queryBuilders = QueryBuilders.matchAllQuery();
//        }
//
//        hiBuilder.fragmentSize(500);//高亮内容长度
//        hiBuilder.requireFieldMatch(false);
//
//        SearchResponse response = transportClient.prepareSearch(IndexName)
//                .setTypes(typeName)
//                .setQuery(queryBuilders)
//                .setFrom((param.getPageNo() - 1) * BaseConstants.PAGE_SIZE)
//                .setSize(BaseConstants.PAGE_SIZE)
//                .highlighter(hiBuilder)//高亮字段前缀
//                .setExplain(true)// 设置是否按查询匹配度排序
////                .addSort("resource_id", SortOrder.ASC)
//                .execute().actionGet();
//
//        SearchHits hits = response.getHits();
//
//        int total = (int) hits.getTotalHits();
//
//        for (SearchHit searchHit : hits) {
//
//            //获取高亮域
//            Map<String, HighlightField> result = searchHit.getHighlightFields();
//            Map source = searchHit.getSource();
//            SearchResultVO searchResultVo = (SearchResultVO) JSONObject
//                    .toBean(JSONObject.fromObject(source), SearchResultVO.class);
//            Map<String, Object> map = Maps.newHashMap();
//
//            //大词典
//            if (searchResultVo.getType_id() == null || "".equals(searchResultVo.getType_id())) {
//                HighlightField namesField = result.get("text_name");
//                if (namesField != null && !"".equals(namesField)) {
//                    //取得定于的高亮标签
//                    Text[] nameTexts = namesField.getFragments();
//                    //为name串值增加自定义的高亮标签
//                    String textName = "";
//                    for (Text text : nameTexts) {
//                        textName += text;
//                    }
//                    searchResultVo.setText_name(textName);
//                }
//
////                //从高亮域中取得指定域
////                HighlightField contentField = result.get("text_content");
////                if (!"".equals(contentField) && contentField != null) {
////                    //取得定于的高亮标签
////                    Text[] contentsTexts = contentField.getFragments();
////                    //为content串值增加自定义的高亮标签
////                    String contents = "";
////                    for (Text content : contentsTexts) {
////                        contents += content;
////                    }
////
////                    searchResultVo.setText_content(contents);
////                }
//                map.put("textType", searchResultVo.getText_type());
//                map.put("resourceName", searchResultVo.getText_name());
//                map.put("resourceId", searchResultVo.getText_id());
//                map.put("resourceIntro", searchResultVo.getText_content());
//                map.put("textFrom","丁福保佛学大辞典");
//                map.put("typeId", 10009);
//                map.put("typeName", "大词典");
//                mapList.add(map);
//            } else if(searchResultVo.getType_id()==10008) {
//                   //从高亮域中取得指定域
//                   //高亮显示音乐的名称
//                   HighlightField namesField = result.get("music_name");
//                   if (namesField != null && !"".equals(namesField)) {
//                       //取得定于的高亮标签
//                       Text[] nameTexts = namesField.getFragments();
//                       //为name串值增加自定义的高亮标签
//                       String titles = "";
//                       for (Text text : nameTexts) {
//                           titles += text;
//                       }
//                       searchResultVo.setItem_name(titles);
//                   }
//
//                   //从高亮域中取得指定域
//                   //高亮显示音乐描述
//                   HighlightField contentField = result.get("title_desc");
//                   if (!"".equals(contentField) && contentField != null) {
//                       //取得定于的高亮标签
//                       Text[] contentsTexts = contentField.getFragments();
//                       //为name串值增加自定义的高亮标签
//                       String contents = "";
//                       for (Text content : contentsTexts) {
//                           contents += content;
//                       }
//                       searchResultVo.setTitle_desc(contents);
//                   }
//                  // 从高亮域中取得指定域
//                 // 高亮显示专辑
//                   HighlightField albumField = result.get("album_name");
//                   if (!"".equals(albumField) && albumField != null) {
//                       //取得定于的高亮标签
//                       Text[] albumFields = albumField.getFragments();
//                       //为name串值增加自定义的高亮标签
//                       String albums = "";
//                       for (Text album : albumFields) {
//                           albums += albumField;
//                       }
//                       searchResultVo.setAlbum_name(albums);
//                   }
//                   map.put("author", searchResultVo.getTitle_desc());
//                   map.put("resourceName", searchResultVo.getMusic_name());
//                   map.put("resourceUrl", searchResultVo.getMusic_file());
//                   map.put("resourceId", searchResultVo.getMusic_id());
//                   map.put("musicSubtitle", searchResultVo.getMusic_subtitle());
//                   map.put("albumName", searchResultVo.getAlbum_name());
//                   map.put("resourcePic", searchResultVo.getMusic_pic());
//                   map.put("typeId", searchResultVo.getType_id());
//                   map.put("typeName", searchResultVo.getType_name());
//                   mapList.add(map);
//               }
//               else {
//                   HighlightField itemField = result.get("item_name");
//                   if (itemField != null && !"".equals(itemField)) {
//                       //取得定于的高亮标签
//                       Text[] nameTexts = itemField.getFragments();
//                       //为name串值增加自定义的高亮标签
//                       String titles = "";
//                       for (Text text : nameTexts) {
//                           titles += text;
//                       }
//                       searchResultVo.setItem_name(titles);
//                   }
//
//                   //从高亮域中取得指定域
//                   HighlightField introField = result.get("item_intro");
//                   if (!"".equals(introField) && introField != null) {
//                       //取得定于的高亮标签
//                       Text[] contentsTexts = introField.getFragments();
//                       //为name串值增加自定义的高亮标签
//                       String contents = "";
//                       for (Text text : contentsTexts) {
//                           contents += text;
//                       }
//
//                       searchResultVo.setItem_intro(contents);
//                   }
//
//                   //从高亮域中取得指定域
//                   HighlightField descField = result.get("title_desc");
//                   if (!"".equals(descField) && descField != null) {
//                       //取得定于的高亮标签
//                       Text[] descsTexts = descField.getFragments();
//                       //为name串值增加自定义的高亮标签
//                       String contents = "";
//                       for (Text text : descsTexts) {
//                           contents += text;
//                       }
//
//                       searchResultVo.setTitle_desc(contents);
//                   }
//                   map.put("author",searchResultVo.getTitle_desc());
//                   map.put("resourceName",searchResultVo.getItem_name());
//                   map.put("resourceId",searchResultVo.getItem_id());
//                   map.put("resourceIntro",searchResultVo.getItem_intro());
//                   map.put("resourcePic",searchResultVo.getItem_pic());
//                   map.put("typeId",searchResultVo.getType_id());
//                   map.put("typeName",searchResultVo.getType_name());
//                   mapList.add(map);
//
//               }
//
//            pageInfo = new PageInfo(mapList);
//            pageInfo.setPageSize(BaseConstants.PAGE_SIZE);
//            if (((total - (param.getPageNo() * BaseConstants.PAGE_SIZE)) / BaseConstants.PAGE_SIZE) > 1) {
//                pageInfo.setHasNextPage(true);
//            }
//        }
//
//        Map<String, Object> map = Maps.newHashMap();
//        map.put("list", pageInfo.getList());
//        map.put("hasNextPage", pageInfo.isHasNextPage());
//        return new BaseResult(code, message, map);
//    }
//
//    /**
//     * 基于类型搜索全部
//     * @param param
//     * @return
//     */
//    @Override
//    public BaseResult getAll(MyParam param) {
//        String code = BaseConstants.GWSCODE0000;
//        String message = BaseConstants.GWSMSG0000;
//
//        List<Map<String,Object>> mapList = new ArrayList<>();
//        PageInfo pageInfo = null;
//        QueryBuilder queryBuilders = null;
//        String typeName="item";
//
//        HighlightBuilder hiBuilder = new HighlightBuilder().field("*").requireFieldMatch(false);
//        hiBuilder.preTags("<span style=\"color:fc9153\">");
//        hiBuilder.postTags("</span>");
//
//        //如果传入的字段不为空，则根据字段进行查询
//        if (!"".equals(param.getFilterStr()) && param.getFilterStr() != null) {
//            //查询字段
//            queryBuilders = QueryBuilders.queryStringQuery(param.getFilterStr());//设置高亮区域
//            hiBuilder
//                    .field("music_name")
//                    .field("title_desc").field("album_name")
//                    .field("title_name")
//                    .field("title_intro").field("item_desc");
//        }
//        //如果传入的字段为空，则查询所有
//        else {
//            queryBuilders = QueryBuilders.matchAllQuery();
//        }
//
//        hiBuilder.fragmentSize(500);//高亮内容长度
//        hiBuilder.requireFieldMatch(false);
//
//        SearchResponse response = transportClient.prepareSearch(IndexName)
//                .setTypes(typeName)
//                .setQuery(queryBuilders)
//                .setFrom((param.getPageNo() - 1) * BaseConstants.PAGE_SIZE)
//                .setSize(BaseConstants.PAGE_SIZE)
//                .highlighter(hiBuilder)//高亮字段前缀
//                .setExplain(true)// 设置是否按查询匹配度排序
//                .execute().actionGet();
//
//        SearchHits hits = response.getHits();
//
//        int total = (int) hits.getTotalHits();
//
//        for (SearchHit searchHit : hits) {
//
//            //获取高亮域
//            Map<String, HighlightField> result = searchHit.getHighlightFields();
//            Map source = searchHit.getSource();
//            TaskItemVO taskItemVo = (TaskItemVO) JSONObject
//                    .toBean(JSONObject.fromObject(source), TaskItemVO.class);
//            Map<String, Object> map = Maps.newHashMap();
//
//            //音乐
//            if(taskItemVo.getType_id()==10008 && param.getType()==1) {
//                //从高亮域中取得指定域
//                //高亮显示音乐的名称
//                HighlightField namesField = result.get("music_name");
//                if (namesField != null && !"".equals(namesField)) {
//                    //取得定于的高亮标签
//                    Text[] nameTexts = namesField.getFragments();
//                    //为name串值增加自定义的高亮标签
//                    String titles = "";
//                    for (Text text : nameTexts) {
//                        titles += text;
//                    }
//                    taskItemVo.setItem_name(titles);
//                }
//
//                //从高亮域中取得指定域
//                //高亮显示音乐描述
//                HighlightField contentField = result.get("title_desc");
//                if (!"".equals(contentField) && contentField != null) {
//                    //取得定于的高亮标签
//                    Text[] contentsTexts = contentField.getFragments();
//                    //为name串值增加自定义的高亮标签
//                    String contents = "";
//                    for (Text content : contentsTexts) {
//                        contents += content;
//                    }
//                    taskItemVo.setTitle_desc(contents);
//                }
//                // 从高亮域中取得指定域
//                // 高亮显示专辑
//                HighlightField albumField = result.get("album_name");
//                if (!"".equals(albumField) && albumField != null) {
//                    //取得定于的高亮标签
//                    Text[] albumFields = albumField.getFragments();
//                    //为name串值增加自定义的高亮标签
//                    String albums = "";
//                    for (Text album : albumFields) {
//                        albums += albumField;
//                    }
//                    taskItemVo.setAlbum_name(albums);
//                }
//                map.put("author", taskItemVo.getTitle_desc());
//                map.put("resourceName", taskItemVo.getMusic_name());
//                map.put("resourceUrl", taskItemVo.getMusic_file());
//                map.put("resourceId", taskItemVo.getMusic_id());
//                map.put("musicSubtitle", taskItemVo.getMusic_subtitle());
//                map.put("albumName", taskItemVo.getAlbum_name());
//                map.put("resourcePic", taskItemVo.getMusic_pic());
//                map.put("typeId", taskItemVo.getType_id());
//                map.put("typeName", taskItemVo.getType_name());
//                mapList.add(map);
//            }
//            else if(taskItemVo.getType_id()==10006 && param.getType()==0){
//                HighlightField itemField = result.get("item_name");
//                if (itemField != null && !"".equals(itemField)) {
//                    //取得定于的高亮标签
//                    Text[] nameTexts = itemField.getFragments();
//                    //为name串值增加自定义的高亮标签
//                    String titles = "";
//                    for (Text text : nameTexts) {
//                        titles += text;
//                    }
//                    taskItemVo.setItem_name(titles);
//                }
//
//                //从高亮域中取得指定域
//                HighlightField introField = result.get("item_intro");
//                if (!"".equals(introField) && introField != null) {
//                    //取得定于的高亮标签
//                    Text[] contentsTexts = introField.getFragments();
//                    //为name串值增加自定义的高亮标签
//                    String contents = "";
//                    for (Text text : contentsTexts) {
//                        contents += text;
//                    }
//
//                    taskItemVo.setItem_intro(contents);
//                }
//
//                //从高亮域中取得指定域
//                HighlightField descField = result.get("title_desc");
//                if (!"".equals(descField) && descField != null) {
//                    //取得定于的高亮标签
//                    Text[] descsTexts = descField.getFragments();
//                    //为name串值增加自定义的高亮标签
//                    String contents = "";
//                    for (Text text : descsTexts) {
//                        contents += text;
//                    }
//
//                    taskItemVo.setTitle_desc(contents);
//                }
//                map.put("author",taskItemVo.getTitle_desc());
//                map.put("resourceName",taskItemVo.getItem_name());
//                map.put("resourceId",taskItemVo.getItem_id());
//                map.put("resourceIntro",taskItemVo.getItem_intro());
//                map.put("resourcePic",taskItemVo.getItem_pic());
//                map.put("typeId",taskItemVo.getType_id());
//                map.put("typeName",taskItemVo.getType_name());
//                mapList.add(map);
//            }
//            pageInfo = new PageInfo(mapList);
//            pageInfo.setPageSize(BaseConstants.PAGE_SIZE);
//            if (((total - (param.getPageNo() * BaseConstants.PAGE_SIZE)) / BaseConstants.PAGE_SIZE) > 1) {
//                pageInfo.setHasNextPage(true);
//            }
//
//        }
//        pageInfo = new PageInfo(mapList);
//        Map<String,Object> map = Maps.newHashMap();
//        map.put("list",mapList);
//        map.put("hasNextPage",pageInfo.isHasNextPage());
//
//        return new BaseResult(code,message,map);
//
//    }
//
//
//}