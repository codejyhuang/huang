package com.hrym.common.base;

import com.github.pagehelper.Page;
import org.elasticsearch.action.search.SearchResponse;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by mj on 2017/7/18.
 */
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageNo;
    private int pageSize;
    private long total;
    private List<T> list;
    private boolean hasNextPage;
    private int pages;

    public PageInfo() {
        this.hasNextPage = false;
    }

    public PageInfo(List<T> list) {
        this(list, 8);
    }

    public PageInfo(List<T> list, int navigatePages) {
        this.hasNextPage = false;
        if(list instanceof Page) {
            Page page = (Page)list;
            this.pageNo = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.list = page;
            this.pages = page.getPages();
            this.total = page.getTotal();
        } else if(list instanceof Collection) {
            this.pageNo = 1;
            this.pageSize = list.size();
            this.list = list;
            this.total = (long)list.size();
            this.pages = 1;
        }
        if(list instanceof Collection) {
            this.judgePageBoudary();
        }
    }

    private void judgePageBoudary() {
        this.hasNextPage = this.pageNo < this.pages;
    }


    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isHasNextPage() {
        return this.hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("PageInfo{");
        sb.append("pageNo=").append(this.pageNo);
        sb.append(", pageSize=").append(this.pageSize);
        sb.append(", total=").append(this.total);
        sb.append(", list=").append(this.list);
        sb.append(", hasNextPage=").append(this.hasNextPage);
        sb.append(", pages=").append(this.pages);
        sb.append('}');
        return sb.toString();
    }
}
