package com.example.cosminalex.android_skills.app.storage.memory;

import android.os.SystemClock;
import android.util.LruCache;

import java.util.HashMap;
import java.util.Map;

public class ExpiringLruCache<K, V> {
    private final long mExpireTime;
    private final LruCache<K, V> mCache;
    private final Map<K, Long> mExpirationTimes;

    /**
     * @param maxSize          for caches that do not override {@link #sizeOf}, this is
     *                         the maximum number of entries in the cache. For all other caches,
     *                         this is the maximum sum of the sizes of the entries in this cache.
     * @param expireTimeMillis the amount of time in milliseconds that any particular
     *                         cache entry is valid.
     */
    public ExpiringLruCache(int maxSize, long expireTimeMillis) {
        mExpireTime = expireTimeMillis;
        mExpirationTimes = new HashMap<>(maxSize);
        mCache = new MyLruCache(maxSize);
    }

    public synchronized V get(K key) {
        V value = mCache.get(key);
        if (value != null && elapsedRealtime() >= getExpiryTime(key)) {
            remove(key);
            return null;
        }
        return value;
    }

    public synchronized V put(K key, V value) {
        V oldValue = mCache.put(key, value);
        mExpirationTimes.put(key, elapsedRealtime() + mExpireTime);
        return oldValue;
    }

    long elapsedRealtime() { // With Bill Maher
        return SystemClock.elapsedRealtime();
    }

    long getExpiryTime(K key) {
        Long time = mExpirationTimes.get(key);
        if (time == null) {
            return 0;
        }
        return time;
    }

    void removeExpiryTime(K key) {
        mExpirationTimes.remove(key);
    }

    public V remove(K key) {
        return mCache.remove(key);
    }

    public Map<K, V> snapshot() {
        return mCache.snapshot();
    }

    public int createCount() {
        return mCache.createCount();
    }

    public void evictAll() {
        mCache.evictAll();
    }

    public int evictionCount() {
        return mCache.evictionCount();
    }

    public int hitCount() {
        return mCache.hitCount();
    }

    public int maxSize() {
        return mCache.maxSize();
    }

    public int missCount() {
        return mCache.missCount();
    }

    public int putCount() {
        return mCache.putCount();
    }

    public int size() {
        return mCache.size();
    }

    /**
     * Extended the LruCache to override the {@link #entryRemoved} method
     * so we can remove expiration time entries when things are evicted from the cache.
     * <p>
     * Gotta love some of those Google engineers making things difficult with paranoid
     * usage of the {@code final} keyword.
     */
    private class MyLruCache extends LruCache<K, V> {

        public MyLruCache(int maxSize) {
            super(maxSize);
        }

        @Override
        protected void entryRemoved(boolean evicted, K key, V oldValue, V newValue) {
            ExpiringLruCache.this.removeExpiryTime(key); // dirty hack
        }

        @Override
        protected int sizeOf(K key, V value) {
            return ExpiringLruCache.this.sizeOf(key, value);
        }
    }

    /**
     * Returns the size of the entry for {@code key} and {@code value} in
     * user-defined units.  The default implementation returns 1 so that size
     * is the number of entries and max size is the maximum number of entries.
     * <p>
     * <p>An entry's size must not change while it is in the cache.
     */
    protected int sizeOf(K key, V value) {
        return 1;
    }
}
