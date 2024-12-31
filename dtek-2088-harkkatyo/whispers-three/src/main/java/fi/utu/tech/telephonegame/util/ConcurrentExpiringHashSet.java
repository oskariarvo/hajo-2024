package fi.utu.tech.telephonegame.util;

import java.time.Instant;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Do not edit this file. Älä muokkaa tätä tiedostoa.
 * 
 * This class can be used as data storage for already seen messages. 
 * 
 *  The parameter delay determines how long a value is saved in the set.
 *  Parameter expirationCycle determines how often expired values are purged from the set.
 */

public class ConcurrentExpiringHashSet<K> extends Thread {

	private ConcurrentHashMap<K, Instant> map = new ConcurrentHashMap<K, Instant>();
	private long delay;
	private long expirationCycle;

	public ConcurrentExpiringHashSet(long delay, long expirationCycle) {
		this.delay = delay;
		this.expirationCycle = expirationCycle;
		this.start();
	}

	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public void put(K key) {
		map.put(key, Instant.now().plusMillis(delay));
	}

	public void remove(Object key) {
		map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public Set<K> keySet() {
		return map.keySet();
	}

	public void run() {
		while (true) {
			try {
				ConcurrentExpiringHashSet.sleep(expirationCycle);
				map.forEach(4, (k, v) -> {
					if (Instant.now().isAfter(v)) {
						map.remove(k);
					}
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
