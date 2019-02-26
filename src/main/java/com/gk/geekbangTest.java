package com.gk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by akun on 2019/2/26.
 */
@Slf4j
public class geekbangTest {

    private String loginUrl = "https://account.geekbang.org/account/ticket/login";
    private String productsUrl = "https://time.geekbang.org/serv/v1/my/products/all";

    ObjectMapper objectMapper = new ObjectMapper();

    private List<Account> accounts = new ArrayList<>();

    @Before
    public void init() {
        accounts.add(new Account("15221595679", "mypassword"));
        accounts.add(new Account("18395202205", "mypassword"));
        accounts.add(new Account("17621990216", "mypassword"));
        accounts.add(new Account("18995041784", "mypassword"));
        accounts.add(new Account("18616237316", "mypassword"));
        accounts.add(new Account("18309510275", "mypassword"));
        accounts.add(new Account("15201934086", "mypassword"));
        accounts.add(new Account("13586405808", "mypassword"));
    }

    @Test
    public void test() throws Exception {

        List<Course> courseList = new ArrayList<>();
        for (Account account : accounts) {
            String ticket = getTicket(account);
            System.out.println("get ticket finished");

            List<Course> course = getCourse(ticket, account);
            System.out.println("get course finished");
            courseList.addAll(course);
            System.out.println("start sleep 2s");
            TimeUnit.SECONDS.sleep(10);
        }
        courseList = courseList.stream().sorted(Comparator.comparing(Course::getType)).collect(Collectors.toList());
        printTable(courseList);
    }

    private String tableHead() {
        return "|    类型    | 名称 | 对应账号 |\n" +
                "| :--------:| :---:| :---:  |\n";
    }

    private List<Course> getCourse(String ticket, Account account) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(productsUrl);
        StringBuilder cookie = new StringBuilder("_ga=GA1.2.1969183941.1551151729; _gid=GA1.2.1205686996.1551151729; Hm_lvt_022f847c4e3acd44d4a2481d9187f1e6=1551151766,1551159539,1551159596; Hm_lpvt_022f847c4e3acd44d4a2481d9187f1e6=1551159596; _gat=1;");
        cookie.append(ticket);
        post.addHeader("Cookie", cookie.toString());
        System.out.println(cookie.toString());
        post.addHeader("Referer", "https://account.geekbang.org/dashboard/buy?utm_source=infoq&utm_medium=sitenavigation");

        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);

        Map map = objectMapper.readValue(result, Map.class);
        List<LinkedHashMap> list = (ArrayList) map.get("data");
        List<Course> courseList = new ArrayList<>();
        for (LinkedHashMap hashMap : list) {
            String title = (String) hashMap.get("title");
            List<LinkedHashMap> courses = (ArrayList) hashMap.get("list");
            for (LinkedHashMap data : courses) {
                Course course = new Course();
                course.setPhone(account.getPhone());
                course.setType(title);
                course.setTitle((String) data.get("title"));
                courseList.add(course);
            }
        }
        return courseList;
    }


    private String getTicket(Account account) throws Exception {
        //{"country":86,"cellphone":"13586405808","password":"mypassword","captcha":"","remember":1,"platform":3,"appid":1}
        Map<String, String> payload = new HashMap<>();
        payload.put("country", "86");
        payload.put("cellphone", account.getPhone());
        payload.put("password", account.getPassword());
        payload.put("remember", "1");
        payload.put("platform", "3");
        payload.put("appid", "1");

        HttpPost post = new HttpPost(loginUrl);
        post.addHeader("Referer", "https://account.geekbang.org/signin?utm_source=infoq&utm_medium=sitenavigation");
        HttpEntity httpEntity = new StringEntity(objectMapper.writeValueAsString(payload), Charsets.UTF_8);
        post.setEntity(httpEntity);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(post);
        Header[] headers = response.getHeaders("Set-Cookie");
        StringBuilder cookie = new StringBuilder();
        for (Header header : headers) {
            cookie.append(header.getValue()).append(";");
        }
        String cookieStr = cookie.toString();
        cookieStr = cookieStr.substring(0, cookieStr.lastIndexOf(";"));
        return cookieStr;
    }

    private void printTable(List<Course> courseList) {
        String tableHead = tableHead();
        StringBuilder cell = new StringBuilder(tableHead);
        for (Course course : courseList) {
            cell.append("|");
            cell.append(course.getType()).append("|")
                    .append(course.getTitle()).append("|")
                    .append(course.getPhone()).append("|")
                    .append("\n");
        }
        System.out.println(cell.toString());

    }

    @Data
    static class Course {
        private String type;
        private String title;
        private String phone;
    }

    @Data
    @AllArgsConstructor
    static class Account {
        private String phone;
        private String password;
    }


}
