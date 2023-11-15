package com.halyn.dto;

import lombok.Data;

@Data
public class PageDto {

    private int totalItemCount; // 전체 아이템 갯수
    private int pageSize; // 페이지네이션 갯수
    private int itemCountByPage; // 한 페이지 당 아이템 갯수
    private int nowPage; // 현재 보고있는 페이지

    public PageDto() {
        this.nowPage = 1;
        this.pageSize = 5;
        this.itemCountByPage = 10;
    }

    public int getOffset() {
        return (nowPage - 1) * itemCountByPage;
    }

    public int getTotalPage() {
        int result = totalItemCount / itemCountByPage;
        if (result % itemCountByPage != 0) {
            result++;
        }
        return result;
    }


    public int getStartPage() {
        return ((this.nowPage - 1) / this.pageSize) * this.pageSize + 1;
    }

    public int getEndPage() {
        int endPage = (getStartPage() + pageSize - 1);
        if (getTotalPage() < endPage) {
            endPage = getTotalPage();
        }
        return endPage;
    }

    /* 1~10
        10페이지 -> 1, 10
        11페이지 -> 11, 20
        21페이지 -> 21, 30
        ( ( (지금페이지 - 1) / 페네갯수 ) * 페네갯수) + 1) )

    */
    public int getPrePage() {
        int prePage = nowPage - pageSize;
        if (prePage < 1){
            prePage = 1;
        }
        return prePage;
        // return (prePage < 1) ? 1 : prePage;
        //              (조건)   ? true : false
    }

    public int getNextPage() {
        int nextPage = nowPage + pageSize;
        if (nextPage > getTotalPage()){
            nextPage = getTotalPage();
        }
        return nextPage;
    }

}
