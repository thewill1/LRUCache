import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    private Node head = null;
    private Node tail = null;

    private Map<Integer, Node> lruMap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int lruSize = scanner.nextInt();

        KeyValuePair[] keyValuePairs = new KeyValuePair[size];

        for (int i=0; i < size; i++)
        {
            keyValuePairs[i] = new KeyValuePair(scanner.nextInt(), scanner.nextInt());
        }

        Solution solution = new Solution();

        solution.LruImplementation(keyValuePairs, lruSize);

        System.out.println("Final LRU:   ");

        for (Map.Entry<Integer, Node> entry : solution.lruMap.entrySet()) {
            Node node = entry.getValue();
            System.out.println(entry.getKey() + "; " + node.getValue());
        }
    }

    private void LruImplementation(KeyValuePair[] keyValuePairs, int lruSize)
    {
        if (lruMap == null)
        {
            lruMap = new HashMap<>(lruSize);
        }

        if (keyValuePairs == null || lruSize < 0)
        {
            return;
        }

        for(int i=0; i< keyValuePairs.length; i++)
        {
            if(head == null && tail == null)
            {
                assignHeadAndTailAndUpdateMap(keyValuePairs[i]);
                continue;
            }

            if (lruMap.containsKey(keyValuePairs[i].getKey()))
            {
                // Move to last - Done
                getNodeAndMoveToLast(keyValuePairs[i].getKey(), keyValuePairs[i].getValue());
            } else
            {
                if (lruMap.size() >= lruSize) {
                    //remove From head; - Done
                    // insert at last; - Done

                    removeFromHead();
                    insertAtlast(keyValuePairs[i]);
                } else
                {
                    //insert at last; - Done

                    insertAtlast(keyValuePairs[i]);
                }
            }
        }
    }

    private void assignHeadAndTailAndUpdateMap(KeyValuePair keyValuePair)
    {
        Node node = new Node(keyValuePair.getKey(), keyValuePair.getValue(), null, null);
        head = node;
        tail = node;

        lruMap.put(keyValuePair.getKey(), node);
    }

    private void getNodeAndMoveToLast(int key, int value)
    {
        if (!lruMap.containsKey(key))
        {
            return;
        }

        Node node = lruMap.get(key);
        node.setValue(value);

        Node prev = node.getPrev();
        Node next = node.getNext();

        if (prev == null && next == null)
        {
            return;
        }

        if (prev == null)
        {
            head = next;
            next.setPrev(null);
        } else if (next == null)
        {
            return;
        }

        if (prev != null) {
            prev.setNext(next);
        }

        tail.setNext(node);
        tail = node;
    }

    private void removeFromHead()
    {
        if (head == null)
        {
            return;
        }

        Node next = head.getNext();

        int keyToRemove = head.getKey();

        if (lruMap.containsKey(keyToRemove))
        {
            lruMap.remove(keyToRemove);
        }

        head = next;
    }

    private void insertAtlast(KeyValuePair keyValuePair)
    {
        if (keyValuePair == null)
        {
            return;
        }

        Node node = new Node(keyValuePair.getKey(), keyValuePair.getValue(), tail, null);

        tail.setNext(node);

        tail = node;

        lruMap.put(keyValuePair.getKey(), node);
    }
}
