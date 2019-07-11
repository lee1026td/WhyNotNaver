package com.lee.myexample;

public class ShoppingProductQuery {
    public static void shoppingProduct(int display, String field, String[][] output) {
        for(int i=0;i<display;i++) {
            if(field.equals("4")) {
                switch (output[6][i]) {
                    case "1": case "2": case "3": output[6][i] = "일반상품";
                        break;
                    case "4": case "5": case "6": output[6][i] = "중고상품";
                        break;
                    case "7": case "8": case "9": output[6][i] = "단종상품";
                        break;
                    case "10": case "11": case "12": output[6][i] = "판매예정상품";
                        break;
                }
            }
        }
    }
}
