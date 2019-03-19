package io.zipcoder;

import io.zipcoder.utils.Item;
import io.zipcoder.utils.ItemParseException;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {


    public List<Item> parseItemList(String valueToParse) {
        List<Item> item = new ArrayList<>();

        String[] arr = valueToParse.split("##");

        for (int i = 0; i < arr.length; i++) {
            try {
                item.add(parseSingleItem(arr[i]+ "##" ));

            } catch (ItemParseException e) {
                e.printStackTrace();
            }
        }

        return item;
    }



    public Item parseSingleItem(String singleItem) throws ItemParseException {
        Item item;


        try {


            String delimiter = "[:|\\^|\\%|\\*|@|;]";
            String stringparse = "naMe" + delimiter + "(\\w+)" + delimiter +
                    "price" + delimiter + "(\\d+.\\d*)" + delimiter
                    + "type" + delimiter + "(\\w+)" + delimiter +
                    "expiration" + delimiter + "(\\d{1,2}/\\d{1,2}/\\d{2,4})##";

            Pattern pattern = Pattern.compile(stringparse, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(singleItem);

            matcher.find();
            // for (int i=0; i<singleItem.length(); i++) {

            matcher.group(1);
            matcher.group(2);
            matcher.group(3);
            matcher.group(4);


            item = new Item(matcher.group(1).toLowerCase(), Double.valueOf(matcher.group(2))
                    , matcher.group(3).toLowerCase(), matcher.group(4));

        } catch (Exception e) {
            throw new ItemParseException();
        }

        return item;
    }


}
