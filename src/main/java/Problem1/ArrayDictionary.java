package Problem1;

public class ArrayDictionary implements Dictionary {
    private KVEntry[] entries;

    public ArrayDictionary(int capacity) {
        entries = new KVEntry[capacity];
    }

    @Override
    public boolean isEmpty() {
        // NOT IMPLEMENTED YET
        return false;
    }

    private int hashFunction(String key) {
        // not ideal
        return key.length();
    }

    @Override
    public void put(String key, String value) {

        int hashedKey = hashFunction(key);

        // when there's no entry yet
        if (entries[hashedKey] == null) {
            entries[hashedKey] = new KVEntry(key, value);
            return;
        }

        KVEntry ptr = entries[hashedKey];
        while (ptr.next != null) {
            // update value if key already exists
            if (ptr.key.equals(key)) {
                ptr.value = value;
                return;
            }
            ptr = ptr.next;
        }

        // add an entry to the end of the chain
        ptr.next = new KVEntry(key, value);
    }

    @Override
    public void remove(String key) {
        int hashedKey = hashFunction(key);
        KVEntry ptr = entries[hashedKey];
        KVEntry ptr2 = null;

        //if first value is is key to be deleted
        if(ptr != null && ptr.key == key){
            entries[hashedKey] = ptr.next;
            return;
        }

        //if list is not null and first value is not the key, iterate through all values
        while(ptr.next != null && ptr.key != key){
            ptr2 = ptr;
            ptr = ptr.next;
        }

        //hit of of list and kay not found
        if(ptr == null){
            return;
        }

        ptr2.next = ptr.next;

    }

    @Override
    public String get(String key) {
        int hashedKey = hashFunction(key);

        if (entries[hashedKey] == null) {
            return null;
        }

        KVEntry ptr = entries[hashedKey];
        while (ptr != null) {
            if (ptr.key.equals(key)) {
                return ptr.value;
            }
            ptr = ptr.next;
        }
        return null;
    }

    @Override
    public boolean contains(String key) {
        int hashedKey = hashFunction(key);

        if (hashedKey >= entries.length) {
            return false;
        }

        if (entries[hashedKey] == null) {
            return false;
        }

        KVEntry ptr = entries[hashedKey];
        while (ptr != null) {
            if (ptr.key.equals(key)) {
                return true;
            }
            ptr = ptr.next;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null) {
                builder.append("dictionary[" + i + "] = null\n");
                continue;
            }
            KVEntry ptr = entries[i];
            builder.append("dictionary[" + i + "] = {");
            while (ptr != null) {
                builder.append("(" + ptr.key + ", " + ptr.value + ")");
                ptr = ptr.next;
            }
            builder.append("}\n");
        }
        return builder.toString();
    }
}
