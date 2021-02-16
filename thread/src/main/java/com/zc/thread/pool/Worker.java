package com.zc.thread.pool;

/**
 * 线程池所要执行的具体任务task
 * @author zhangchi
 */
public class Worker implements Runnable{
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){//判断当前线程是否停止
            Job job = null;
            RealizationThreadPool rtp = new RealizationThreadPool();
            synchronized (rtp.getJobs()){
                while (rtp.getJobs().isEmpty()){//如果任务队列为空，线程将在队列出等待。
                    try {
                        rtp.getJobs().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //从任务队列头取出一个任务
                job = (Job) rtp.getJobs().removeFirst();
            }
            if (job!=null){//执行任务方法
                job.run();
            }
        }
    }
    public void shutdown(){
        Thread.currentThread().interrupt();
    }
}
