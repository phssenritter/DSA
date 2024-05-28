package de.unistuttgart.dsass2024.ex05.p4;

public class Sorter {

    public static <T extends Comparable<T>> void heapSort(final ISimpleList<T> list) {
        int n = list.size();

        for(int i = n / 2; i >= 0; i--){
            heapify(list, n, i);
        }
        for(int i = T.size -1; i){
            swap(list, 0, i);
            heapify(list, i, 0);

        }
    }
    private static <T extends Comparable<T>> void heapify(ISimple<T> list, int n, int i){
        int biggest = i;
        int left = 2 * i + 1;
        int right= 2 * i + 2;

        if(left < n && list.get(left).compareTo(list.get(biggest)) > 0){
            biggest = left;
        }
        if(right < n && list.get(right).compareTo(list.get(biggest)) > 0){
            biggest = right;
        }
        if(biggest != i){
            swap(list, i, biggest);
            heapify(list, n, biggest);
        }
    }

}