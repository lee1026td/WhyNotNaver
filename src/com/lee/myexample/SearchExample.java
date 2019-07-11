package com.lee.myexample;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static com.lee.myexample.DataIndexSet.dataIndex;

public class SearchExample {
    public static void main(String[] args) {

        String clientId = "Cj58c1wBO_BmvCavNDOz";       // API 키
        String clientSecret = "6WKyt2hoR4";             // API 시크릿 키

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                System.out.print("\n1. 영화 검색  2. 지역 검색  3. 도서 검색  4. 쇼핑 검색\n5. 지식in 검색  6. 뉴스 검색  7. 지식백과 검색\n8. 검색 기록\n0. 종료\n");
                System.out.print("옵션 선택: ");
                DataIndexSet.field = input.readLine();
                int fieldInt = Integer.parseInt(DataIndexSet.field);

                if(fieldInt > 8 || fieldInt < 0) {
                    System.out.println("잘못된 입력입니다.");
                    continue;
                } else if(fieldInt == 8) {
                    LoadHistory.loadHistory();
                    continue;
                }
                DataIndexSet.setDataIndex();        // 분야 선택에 따른 응답 JSON 데이터의 Key 값 처리

                System.out.print(dataIndex[1][0] + " 검색: ");
                String text = URLEncoder.encode(input.readLine(), "UTF-8");
                System.out.print("최대 검색 결과 개수(10 ~ 30): ");
                int display = Integer.parseInt(input.readLine());

                /* API URL 연결 */
                String apiURL = "https://openapi.naver.com/v1/search/" + DataIndexSet.urlQuery + ".json?query=" + text + "&display=" + display;
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                } else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }

                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine + "\n");
                }
                br.close();
                con.disconnect();

                String[] dataArr = response.toString().split("\"");
                stringReplace(dataArr);             // <b> 태그 삭제

                String[][] output = new String[10][display];         // JSON 데이터를 출력 형식에 맞게 가공
                for(int j = 0; j < dataIndex[0].length; j++) {
                    int k = 0;
                    for(int i=0;i<dataArr.length;i++) {
                        if(dataArr[i].equals(dataIndex[0][j])) {
                            output[j][k] = dataArr[i + 2];
                            k++;
                        }
                    }
                }

                ShoppingProductQuery.shoppingProduct(display, DataIndexSet.field, output);          // 쇼핑 검색에서 API가 응답하는 데이터의 제품 분류 코드를 코드에 대한 설명으로 변환

                /* 검색 결과 전체 출력 */
                System.out.println("\n------------------------------------------------\n");
                data_output:
                for (int i = 0; i < display; i++) {
                    System.out.println((i + 1) + ".");
                    for (int j = 1; j < dataIndex[1].length; j++) {
                        if(output[j - 1][i] == null) break data_output;
                        System.out.println(dataIndex[1][j] + ": " + output[j - 1][i]);
                    }
                    System.out.println();
                }
                System.out.println("\n------------------------------------------------\n");

                System.out.print("검색 결과를 저장하시겠습니까? (Y / N): ");
                String answer = input.readLine();
                if(answer.equals("y")) {
                    SearchDataOutput.searchDataOutput(display, output);
                }

                LoadHistory.setHistory(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void stringReplace(String[] data) {
        String[] filterText = {"<b>", "</b>"};
        for(int i=0;i<data.length;i++) {
            for(int j=0;j<filterText.length;j++) {
                data[i] = data[i].replaceAll(filterText[j], "");
            }
        }
    }
}
