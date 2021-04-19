package com.zc.jdk.data.structure;

import java.util.Iterator;
import java.util.Set;

public abstract class AbstractMapWapper<k,v> implements MapWapper<k,v>{

    protected AbstractMapWapper(){};

  @Override
  public int size(){
      return 0;
  }
    @Override
    public v put(k key, v value) {
        throw new UnsupportedOperationException();
    }

    /**
     * 抽象类中会实现接口中的get方法，但是次方法是通过迭代entrySet,
     * 查询效率与entrySet的大小成线性关系。
     * 大部分情况下都不使用该方法获取value，而是通过子类重写该方法。
     * @param key
     * @return
     */
    @Override
    public v get(Object key){
        Iterator<Entry<k,v>> i = entrySet().iterator();
        if (key ==null){
            while (i.hasNext()){
                Entry<k,v> e = i.next();
                if (e.getKey()==null){
                    return e.getValue();
                }
            }
        }else{
            while (i.hasNext()){
                Entry<k,v> e = i.next();
                if (key.equals(e.getKey())){
                    return e.getValue();
                }
            }
        }
      return null;
    }
    public abstract Set<Entry<k,v>> entrySet();
}
