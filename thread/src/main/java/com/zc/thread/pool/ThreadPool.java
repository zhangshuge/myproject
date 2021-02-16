package com.zc.thread.pool;

/**
 * @author zhangchi
 */
public interface ThreadPool<Job extends Runnable> {
    /**
     * 线程池所要执行的Task
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 向线程池中添加工作线程worker
     * @param num
     */
    void addWorkers(int num);

    /**
     * 从线程池中回收线程worker
     * @param num
     */
    void removeWorkers(int num);

    /**
     * 等待执行的task数量
     * @return
     */
    int getTaskSize();
}
