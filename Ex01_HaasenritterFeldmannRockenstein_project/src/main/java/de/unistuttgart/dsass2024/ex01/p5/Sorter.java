package de.unistuttgart.dsass2024.ex01.p5;

public class Sorter {

    /**
     * Performs selection sort on provided list, works directly on list object,
     * hence no return value
     * 
     * @param <T>  The type of list element
     * @param list List to apply the sorting to; unsorted list at first, sorted list
     *             in the end
     */
    public static <T extends Comparable<T>> void selectionSort(ISimpleList<T> list) {
        int length = list.getSize();

        for(int i = 0; i < length ; i++){
            int maxPos = i;

            for(int j = i + 1; j < length; j++ ){
                if(list.getElement(j).compareTo(list.getElement(maxPos))>0){
                    maxPos = j;
                }
            }
            list.swapElements(i, maxPos);
        }

    }

    /**
     * Performs insertion sort on provided list, works directly on list object,
     * hence no return value
     * 
     * @param <T>  The type of list element
     * @param list List to apply the sorting to; unsorted list at first, sorted list
     *             in the end
     */
    public static <T extends Comparable<T>> void insertionSort(ISimpleList<T> list) {
        int length = list.getSize();
        for(int i = 1; i < length; i++){
            T elementToSort = list.getElement(i);
            int j = i - 1;
            while ((j > -1)&& (list.getElement(j).compareTo(elementToSort) == 1)){
                list.swapElements(j+1,j);
            }
        }


    }

    /**
     * Performs bubble sort on provided list, works directly on list object, hence
     * no return value
     * 
     * @param <T>  The type of list element
     * @param list List to apply the sorting to; unsorted list at first, sorted list
     *             in the end
     */
    public static <T extends Comparable<T>> void bubbleSort(ISimpleList<T> list) {
        int length = list.getSize();
        for(int i = 1; i < length; i++){
            for(int j = 0; j < length - 1; j++){
                if(list.getElement(j).compareTo(list.getElement(j+1)) == 1){
                    list.swapElements(j,j+1);
                }
            }
        }

    }
}