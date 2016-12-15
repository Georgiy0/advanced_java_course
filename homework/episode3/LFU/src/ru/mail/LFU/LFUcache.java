package ru.mail.LFU;

import java.util.LinkedHashMap;
import java.util.Map;

public class LFUcache<K, V>  {

    /**
     * Оболочка, хранящая частоту обращения к записи
     * */
    class CacheEntry {
        private V data;
        private int frequency;

        public V getData() {
            return data;
        }
        public void setData(V data) {
            this.data = data;
        }

        public int getFrequency() {
            return frequency;
        }
        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }
    }

    private int initialCapacity = 10;

    private LinkedHashMap<Object, CacheEntry> cacheMap = new LinkedHashMap<Object, CacheEntry>();

    public LFUcache(int initialCapacity)
    {
        this.initialCapacity = initialCapacity;
    }

    /**
     * Добавление элемента в кэш, если кэш полностью заполнен, то происходит удаления LFU элемента
     * @param key ключ
     * @param data значение
     * @return возвращает null, если при добавлении не произошло удаления LFU элемента,
     * в противном случае возвращает данные, хранившиеся в LFU элементе.
     */
    public V put(K key, V data) {
        if(!isFull()) {
            CacheEntry temp = new CacheEntry();
            temp.setData(data);
            temp.setFrequency(0);

            cacheMap.put(key, temp);
            return null;
        }
        else {
            K entryKeyToBeRemoved = getLFUKey();
            V removed = cacheMap.remove(entryKeyToBeRemoved).data;

            CacheEntry temp = new CacheEntry();
            temp.setData(data);
            temp.setFrequency(0);

            cacheMap.put(key, temp);
            return removed;
        }
    }

    /** находит элемент с самой низкой частотой использования
     * @return ключ LFU элемента
     */
    public K getLFUKey() {
        K key = null;
        int minFreq = Integer.MAX_VALUE;

        for(Map.Entry<Object, CacheEntry> entry : cacheMap.entrySet()) {
            if(minFreq > entry.getValue().frequency) {
                key = (K)entry.getKey();
                minFreq = entry.getValue().frequency;
            }
        }
        return key;
    }

    /**
     * метод для получения элемента из кэша, инкрементирует частоту использования элемента
     * @param key ключ
     * @return
     */
    public V get(Object key) {
        if(cacheMap.containsKey(key)) {
            CacheEntry temp = cacheMap.get(key);
            temp.frequency++;
            cacheMap.put(key, temp);
            return temp.getData();
        }
        return null;
    }

    public boolean isFull() {
        if(cacheMap.size() == initialCapacity)
            return true;
        return false;
    }

    public int size(){
        return cacheMap.size();
    }

    public void clear(){
        cacheMap.clear();
    }

}