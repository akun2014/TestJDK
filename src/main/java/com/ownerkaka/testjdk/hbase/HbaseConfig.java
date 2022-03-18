package com.ownerkaka.testjdk.hbase;

import java.util.List;

import com.alibaba.lindorm.client.core.utils.Bytes;

import lombok.SneakyThrows;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;

public class HbaseConfig {

    @SneakyThrows
    public static void main(String[] args) {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "sl-bp13bz9747cifi6t0-lindorm-serverless.lindorm.rds.aliyuncs.com:30022");
        conf.set("hbase.client.username", "sl-bp13bz9747cifi6t0");
        conf.set("hbase.client.password", "Da5HjDs9");
        Connection connection = ConnectionFactory.createConnection(conf);

        //ddl_test(connection);
        dml_test(connection);
    }

    @SneakyThrows
    public static void ddl_test(Connection connection) {
        try (Admin admin = connection.getAdmin()) {
            // 建表
            HTableDescriptor htd = new HTableDescriptor(TableName.valueOf("tablename"));
            htd.addFamily(new HColumnDescriptor(Bytes.toBytes("family")));
            // 创建一个只有一个分区的表
            // 在生产上建表时建议根据数据特点预先分区
            admin.createTable(htd);

            // disable 表
            //admin.disableTable(TableName.valueOf("tablename"));

            // truncate 表
            //admin.truncateTable(TableName.valueOf("tablename"), true);

            // 删除表
            //admin.deleteTable(TableName.valueOf("tablename"));
        }
    }

    @SneakyThrows
    public static void dml_test(Connection connection) {
        //Table 为非线程安全对象，每个线程在对Table操作时，都必须从Connection中获取相应的Table对象
        try (Table table = connection.getTable(TableName.valueOf("tablename"))) {
            // 插入数据
            //for (int i = 0; i < 10000; i++) {
            //    Put put = new Put(Bytes.toBytes("row_" + i));
            //    put.addColumn(Bytes.toBytes("family"),
            //        i % 2 != 0 ? Bytes.toBytes("qualifier") : Bytes.toBytes("qualifier1"),
            //        Bytes.toBytes("value_" + i))
            //    ;
            //    table.put(put);
            //}

            // 单行读取
            //Get get = new Get(Bytes.toBytes("row"));
            //Result res = table.get(get);
            //System.out.println(new String(res.getRow()));

            //// 删除一行数据
            //Delete delete = new Delete(Bytes.toBytes("row"));
            //table.delete(delete);
            //
            // scan 范围数据
            Scan scan = new Scan(Bytes.toBytes("row_10"), Bytes.toBytes("row_19"));
            ResultScanner scanner = table.getScanner(scan);
            for (Result result : scanner) {
                // 处理查询结果result
                // ...
                //System.out.println(new String(result.getRow()));
                List<Cell> columnCells = result.getColumnCells(Bytes.toBytes("family"), Bytes.toBytes("qualifier"));
                for (Cell columnCell : columnCells) {
                    columnCell.getRowArray();
                }
            }
            scanner.close();
        }
    }
}
