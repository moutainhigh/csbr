//package com.csbr.cloud.hy.server.generator;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//
///**
// * @author zhangheng
// * @date 2020/4/27 10:28
// */
//public class MysqlGenerator {
//
//
//    /**
//     * 代码生成的根路径
//     */
//    private static final String BASEDIR = "D://mycode";
//
//    private static final String PARENT_PACKAGE = "com.csbr.cloud.hy.server";
//
//    private static final String[] tableName = new String[]{"inv_lot_stock_sap","inv_lot_stock_wms","mf_goods","mf_warehouse_work_result","tl_trans_excep_record","tr_bb_tracing","tr_daily_order_time_limit","tr_delivery_timeout_order","tr_in_bound_result","tr_out_bound_result","tr_sign_timeout_order","tr_tplbb","tr_tplbb_detail","tr_tplpo_main"};
//
//    public static void main(String[] args) {
//
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        //项目路径，这里还是设置为D盘，否则生成到项目目录，替换掉之前的代码。
//        gc.setOutputDir(BASEDIR);
//        //设置作者
//        gc.setAuthor("zhangheng");
//        gc.setFileOverride(false);
//        //是否立刻打开
//        gc.setOpen(true);
//        //设置时间格式
//        gc.setDateType(DateType.ONLY_DATE);
//        //设置主键类型
//        gc.setIdType(IdType.AUTO);
//        //设置生成代码的名字
//        gc.setEntityName("%sEntity")
//                .setServiceName("%sService")
//                .setServiceImplName("%sServiceImpl")
//                .setMapperName("%sMapper")
//                .setControllerName("%sController");
//        mpg.setGlobalConfig(gc);
//
//
//
//        //数据源配置,包括了数据库类型，用户名密码，链接地址。
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver").setDbType(DbType.MYSQL);
//        dataSourceConfig.setUsername("root").setPassword("root").setUrl("jdbc:mysql://49.4.23.228:3306/ms_hy_server?useSSL=false&serverTimezone=UTC");
//        mpg.setDataSource(dataSourceConfig);
//
//
//        //包的配置
//        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setParent(PARENT_PACKAGE);
//        mpg.setPackageInfo(packageConfig);
//
//
//        StrategyConfig strategyConfig = new StrategyConfig();
//        //设置字段名大小写转换
//        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
//        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
//        //设置生成的表名，这里使用exClude来进行排除，写入null，表示生成所有表。
//        strategyConfig.setExclude(null);
//        //设置自定义的service层继承代码，这里使用我定义的service和impl来进行继承---Sopp
////        strategyConfig.setSuperServiceClass("com.sopp.sp.util.MyService");
////        strategyConfig.setSuperServiceImplClass("com.sopp.sp.util.MyServiceImpl");
//        //实体类是否带有lombok风格
//        strategyConfig.setEntityLombokModel(true);
//        //rest风格controller
//        strategyConfig.setRestControllerStyle(true);
//        //允许字段自动注解
////        strategyConfig.setEntityTableFieldAnnotationEnable(true);
//        //设置逻辑删除的字段名
//        strategyConfig.setLogicDeleteFieldName("status");
//        //填充字段配置
////        List<TableFill> list = new ArrayList<>();
////        list.add(new TableFill("createTime", FieldFill.INSERT));
////        list.add(new TableFill("updateTime", FieldFill.INSERT_UPDATE));
////        strategyConfig.setTableFillList(list);
//
//        mpg.setStrategy(strategyConfig);
//
//        mpg.execute();
//    }
//
//}
