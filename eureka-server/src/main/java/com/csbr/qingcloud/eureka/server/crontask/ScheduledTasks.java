//package com.csbr.qingcloud.eureka.server.crontask;
//
//import ch.ethz.ssh2.ChannelCondition;
//import ch.ethz.ssh2.Connection;
//import ch.ethz.ssh2.Session;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.ArrayUtils;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @author zhangheng
// * @date 2019/11/19 13:54
// * 调度执行zipkin-ScheduledTasks
// */
//@Slf4j
//@Component
//public class ScheduledTasks {
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//
//    /**
//     * zipkin调度任务执行
//     */
////    @Scheduled(fixedRate = 5000)
//    //每天凌晨两点执行
////    @Scheduled(cron="0 0 2 * * ?")
////    public void zipkinStart(){
////        log.info("The time is now {}", dateFormat.format(new Date()));
//////        RemoteInvokeShell("139.9.190.186",2022,"xx","xx","sh /mnt/zipkin/zipkinbuild.sh");
////    }
//
//    /**
//     * 远程执行脚本
//     * @param ip
//     * @param port
//     * @param name
//     * @param pwd
//     * @param cmds
//     */
//    public static void RemoteInvokeShell(String ip,int port,String name,String pwd,String cmds){
//        //执行docker构建任务
//        Connection conn=null;
//        try {
//            conn = new Connection(ip,port);
//            conn.connect();
//            if (conn.authenticateWithPassword(name, pwd)) {
//                // Open a new {@link Session} on this connection
//                Session session = conn.openSession();
//                // Execute a command on the remote machine.
//                session.execCommand(cmds);
//
//                BufferedReader br = new BufferedReader(new InputStreamReader(session.getStdout()));
//                BufferedReader brErr = new BufferedReader(new InputStreamReader(session.getStderr()));
//
//                String line;
//                while ((line = br.readLine()) != null) {
//                    log.info("br={}", line);
//                }
//                while ((line = brErr.readLine()) != null) {
//                    log.info("brErr={}", line);
//                }
//                if (null != br) {
//                    br.close();
//                }
//                if(null != brErr){
//                    brErr.close();
//                }
//                session.waitForCondition(ChannelCondition.EXIT_STATUS, 0);
//                int ret = session.getExitStatus();
//                log.info("getExitStatus:"+ ret);
//            } else {
//                log.info("登录远程机器失败" + ip);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                conn.close();
//            }
//        }
//    }
//
//    /**
//     * 本地执行脚本
//     * scriptPath 脚本路径
//     *  param 参数
//     */
//    public static void execShell(String scriptPath, String ... param){
//        String[] cmd = new String[]{"chmod -R 755 "+scriptPath};
//        //为了解决参数中包含空格
//        cmd= (String[]) ArrayUtils.addAll(cmd,param);
//        //解决脚本没有执行权限
////        ProcessBuilder builder = new ProcessBuilder("/bin/chmod", "755",scriptPath);
//        Process process = null;
//        try {
//            //执行脚本权限
////            process = builder.start();
////            process.waitFor();
//            process = Runtime.getRuntime().exec(cmd);
//            //获取进程的标准输入流
//            final InputStream is1 = process.getInputStream();
//            //获取进城的错误流
//            final InputStream is2 = process.getErrorStream();
//            //启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
//            new Thread() {
//                public void run() {
//                    consumeInputStream(is1);
//                }
//            }.start();
//
//            new Thread() {
//                public void  run() {
//                    consumeInputStream(is2);
//                }
//            }.start();
//            //解决waitFor()死锁
//            int status = process.waitFor();
//            if(status != 0){
//                log.error("Failed to call shell's command and the return status's is: " + status);
//                System.err.println("Failed to call shell's command and the return status's is: " + status);
//            }
//            log.info("调用正常...没有死锁...");
////            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
////            StringBuffer sb = new StringBuffer();
////            String line;
////            while ((line = br.readLine()) != null) {
////                sb.append(line).append("\n");
////            }
////            //执行结果
////            String result = sb.toString();
////            log.info("执行结果:"+result);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            try{
//                process.getErrorStream().close();
//                process.getInputStream().close();
//                process.getOutputStream().close();
//            }
//            catch(Exception ee){}
//        }
//    }
//
//    public static String consumeInputStream(InputStream stream){
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
//            String s ;
//            StringBuilder sb = new StringBuilder();
//            while((s=br.readLine())!=null){
//                System.out.println(s);
//                sb.append(s);
//            }
//            return sb.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }finally{
//            try {
//                stream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
