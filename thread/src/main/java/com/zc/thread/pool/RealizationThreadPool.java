package com.zc.thread.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhangchi
 */
public class RealizationThreadPool<Job extends Runnable> implements ThreadPool<Job>{
    /**
     * 线程池最大线程数
     */
    private static final int MAX_WORKER_NUMBERS = 10;
    /**
     * 线程池核心线程数，本案例中为默认线程数。
     */
    private static final int CORE_WORKER_NUMBERS =5;
    /**
     * 线程池中实际工作线程数量
     */
    private int workNum = CORE_WORKER_NUMBERS;
    /**
     * 线程池最小线程数
     */
    private static final int MIN_WORKER_NUMBERS =1;
    /**
     * 用于存放task任务的队列
     */
    private final LinkedList<Job>  jobs = new LinkedList<>();
    /**
     * 用于存放当前工作线程的集合
     */
    private final List<Worker> works = Collections.synchronizedList(new ArrayList<Worker>());
    /**
     * 用于获取线程名称自增变量
     */
    private AtomicLong threadNum = new AtomicLong();
    RealizationThreadPool(){
        //通过构造器初始化默认线程数量的线程池
        initializeWorkers(workNum);
    }

    private void initializeWorkers(int num){
        for (int i=0;i<num;i++){
            Worker worker = new Worker();
            works.add(worker);
            Thread thread = new Thread(worker,"ThreadPool-worker-"+threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
        if (job!=null){
            synchronized (jobs){ //锁住所有任务列表
                jobs.addLast(job); //添加到任务队列的最后面
                jobs.notify();//通知worker线程
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : works){
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs){
            //判断不能超过最大线程数
            if (num + this.workNum >= MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS-this.workNum;
            }
            initializeWorkers(num);
            this.workNum += num;//更新当前工作线程数
        }
    }

    @Override
    public void removeWorkers(int num) {
        synchronized (jobs){
            if (num>=this.workNum){
                throw new IllegalArgumentException("beyond workNum");
            }
            int count = 0;
            while (count < num){
                Worker wo = works.get(count);
                works.remove(wo);//从当前工作线程列表中删除
                wo.shutdown();//关闭线程
                count++;
            }
            this.workNum -= count;
        }
    }

    @Override
    public int getTaskSize() {
        return jobs.size();
    }
    public  LinkedList<Job> getJobs(){
        return jobs;
    }
}
