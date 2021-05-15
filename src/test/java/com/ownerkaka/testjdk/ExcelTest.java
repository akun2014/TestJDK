package com.ownerkaka.testjdk;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ExcelTest {


    @Test
    public void name() {
        String fileName = "/Users/akun/IdeaProjects/TestJDK/src/test/resources/yangjie.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        List<Map<Integer, String>> list1 = EasyExcel.read(fileName).sheet().doReadSync();

        Set<DemoData> collect1 = list1.stream()
                .filter(v -> {
                    String s = v.get(6);
                    return StringUtils.isNotBlank(s) && s.length() >= 8 && StringUtils.isNumeric(v.get(6).substring(0, 8));
                })
                .map(v -> {
                    String num = v.get(6).substring(0, 8);
                    DemoData demoData = new DemoData();
                    demoData.setNum(num.trim());
                    demoData.setMoney(v.get(13));
                    return demoData;
                }).collect(Collectors.toSet());

        List<Map<Integer, String>> list2 = EasyExcel.read(fileName).sheet(1).doReadSync();
        Set<DemoData> collect2 = list2.stream().map(v -> {
            DemoData demoData = new DemoData();
            demoData.setNum(String.format("%08d", Integer.parseInt(v.get(2))));
            demoData.setMoney(v.get(5));
            return demoData;
        }).collect(Collectors.toSet());

        Sets.SetView<DemoData> intersection = Sets.intersection(collect1, collect2);
        Sets.SetView<DemoData> difference1 = Sets.difference(collect1, collect2);
        Sets.SetView<DemoData> difference2 = Sets.difference(collect2, collect1);

        new ArrayList<>(intersection);


        String fileName0 = "/Users/akun/Desktop/yangjie-00.xlsx";
        String fileName1 = "/Users/akun/Desktop/yangjie-01.xlsx";
        String fileName2 = "/Users/akun/Desktop/yangjie-02.xlsx";
        EasyExcel.write(fileName0, DemoData.class).sheet("交集").doWrite(new ArrayList<>(intersection));
        EasyExcel.write(fileName1, DemoData.class).sheet("k3-vat").doWrite(new ArrayList<>(difference1));
        EasyExcel.write(fileName2, DemoData.class).sheet("vat-k3").doWrite(new ArrayList<>(difference2));


        System.out.println(collect2.size());
    }

    @Getter
    @Setter
    public static class DemoData {
        @ExcelProperty(value = "发票号")
        private String num;
        @ExcelProperty(value = "金额")
        private String money;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DemoData demoData = (DemoData) o;
            return num.equals(demoData.num);
        }

        @Override
        public int hashCode() {
            return Objects.hash(num);
        }
    }
}
