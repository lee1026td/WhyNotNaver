package com.lee.myexample;

public class DataIndexSet {
    public static String field;
    public static String[][] dataIndex = new String[2][100];
    public static String urlQuery;

    public static void setDataIndex() {
        switch (field) {
            case "1":       // 영화
                dataIndex = new String[][]{
                        {"title", "subtitle", "pubDate", "director", "actor", "userRating"},
                        {"영화", "제목", "영어 제목", "개봉 년도", "감독", "배우", "평점"}
                };
                urlQuery = "movie";
                break;
            case "2":       // 지역
                dataIndex = new String[][]{
                        {"title", "category", "link", "description", "telephone", "address", "roadAddress"},
                        {"지역", "이름", "분류", "세부", "업체 / 기관 링크", "전화번호", "주소", "도로명주소"}
                };
                urlQuery = "local";
                break;
            case "3":       // 도서
                dataIndex = new String[][]{
                        {"title", "author", "price", "publisher", "pubdate", "isbn", "description"},
                        {"도서", "제목", "저자", "가격", "출판사", "출판일", "ISBN", "설명"}
                };
                urlQuery = "book";
                break;
            case "4":       // 쇼핑
                dataIndex = new String[][]{
                        {"title", "link", "image", "lprice", "hprice", "mallName", "productType"},
                        {"쇼핑", "제품 이름", "판매처 링크", "이미지 링크", "최저가", "최고가", "판매자", "제품 정보"}
                };
                urlQuery = "shop";
                break;
            case "5":       // 지식in
                dataIndex = new String[][]{
                        {"title", "link", "description"},
                        {"지식in", "제목", "링크", "세부 내용"}
                };
                urlQuery = "kin";
                break;
            case "6":       // 뉴스
                dataIndex = new String[][]{
                        {"title", "originallink", "description", "pubDate"},
                        {"뉴스", "기사 제목", "언론사 뉴스 링크", "세부 내용", "게시일"}
                };
                urlQuery = "news";
                break;
            case "7":       // 지식백과
                dataIndex = new String[][]{
                        {"title", "link", "description", "thumbnail"},
                        {"지식백과", "제목", "링크", "세부 정의", "이미지"}
                };
                urlQuery = "encyc";
                break;
            case "0":
                System.out.println("종료합니다.");
                System.exit(0);
        }
    }
}
