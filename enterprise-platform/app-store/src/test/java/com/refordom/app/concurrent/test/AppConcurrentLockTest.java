//package com.refordom.app.concurrent.test;
//
//import com.refordom.app.store.AppStoreApplication;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.recipes.locks.InterProcessMutex;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.concurrent.TimeUnit;
//
///**
// * <p></p>
// *
// * @author pricess.wang
// * @date 2020/2/21 12:37
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AppStoreApplication.class)
//public class AppConcurrentLockTest {
//
//    @Resource
//    private CuratorFramework curatorFramework;
//
//    @Test
//    public void test(){
//        InterProcessMutex interProcessMutex1 = new InterProcessMutex(curatorFramework,"/app-lock");
//
//        InterProcessMutex interProcessMutex2 = new InterProcessMutex(curatorFramework,"/app-lock");
//
//        try {
//            interProcessMutex1.acquire();
//            interProcessMutex1.acquire();
//            boolean flag = interProcessMutex2.acquire(0, TimeUnit.MILLISECONDS);
//
//            System.err.println(flag);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                interProcessMutex1.release();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
