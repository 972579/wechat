package com.wangr.wechat;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WangRui
 * @version 1.0.0
 * @date 2019-05-25 15:44
 * @since JDK 1.8
 */

public class CodeGenerator {
    private static final String driver = "*";
    private static final String url = "*";
    private static final String userName = "*";
    private static final String password = "*";

    public static void main(String[] args) throws SQLException {
        // 一：数据库所有表生成
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl(url);
//        dataSource.setDriverClassName(driver);
//        dataSource.setUsername(userName);
//        dataSource.setPassword(password);
//        Connection con = dataSource.getConnection();
//        DatabaseMetaData metaData = con.getMetaData();
//        ResultSet rs = metaData.getTables(null, null, null, new String[]{"TABLE"});
//        // 遍历所有表名，生成对应文件
//        int i = 0;
//        while (rs.next()) {
//            String tableName = rs.getString("TABLE_NAME");
//            if (StrUtil.isNotEmpty(tableName)) {
//                genneratorCode(tableName);
//                i++;
//            }
//        }
//        System.out.println("总共生成表：" + (i - 1));
        // 二：如果单个表生成方式
        String[] tabNames = new String[]{"test"};
        for (String name : tabNames) {
            genneratorCode(name);
        }
    }

    public static void genneratorCode(String tableName) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = "E:\\codeTest";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driver);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.wangr");
        mpg.setPackageInfo(pc);


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/cloud.manage.mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(com.baomidou.mybatisplus.generator.config.po.TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/src/main/resources/cloud.manage.mapper/" + tableInfo.getEntityName() +
                        "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();


        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperServiceClass("com.wangr.wechat.common.IBaseService");
        strategy.setSuperServiceImplClass("com.wangr.wechat.common.impl.BaseServiceImpl");
        strategy.setInclude(tableName);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
