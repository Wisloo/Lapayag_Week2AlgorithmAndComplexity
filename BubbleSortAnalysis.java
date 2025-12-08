public class BubbleSortAnalysis {
    
    static class BubbleSortMetrics {
        long executionTime;  // in nanoseconds
        int comparisons;
        int swaps;
        
        BubbleSortMetrics(long time, int comp, int swap) {
            this.executionTime = time;
            this.comparisons = comp;
            this.swaps = swap;
        }
    }
    
    // Bubble Sort with metrics tracking
    public static BubbleSortMetrics bubbleSort(int[] arr) {
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;
        
        long startTime = System.nanoTime();
        
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            // If no swaps occurred, array is sorted
            if (!swapped) break;
        }
        
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        
        return new BubbleSortMetrics(executionTime, comparisons, swaps);
    }
    
    // Generate Best Case: Already sorted list
    public static int[] generateBestCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }
    
    // Generate Average Case: Random list
    public static int[] generateAverageCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random() * n);
        }
        return arr;
    }
    
    // Generate Worst Case: Reverse-sorted list
    public static int[] generateWorstCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }
        return arr;
    }
    
    public static void main(String[] args) {
        int[] sizes = {500, 1000, 2000};
        String[] cases = {"Best Case", "Average Case", "Worst Case"};
        
        System.out.println("========================================");
        System.out.println("BUBBLE SORT PERFORMANCE ANALYSIS");
        System.out.println("========================================\n");
        
        for (String caseType : cases) {
            System.out.println("===== " + caseType + ": " + 
                (caseType.equals("Best Case") ? "Already sorted list" :
                 caseType.equals("Average Case") ? "Random list" : 
                 "Reverse-sorted list") + " =====\n");
            
            System.out.printf("%-10s %-20s %-20s %-15s%n", 
                "n", "Execution Time (ms)", "# of Comparisons", "# of Swaps");
            System.out.println("-----------------------------------------------------------------------");
            
            for (int n : sizes) {
                int[] arr;
                
                // Generate appropriate case
                if (caseType.equals("Best Case")) {
                    arr = generateBestCase(n);
                } else if (caseType.equals("Average Case")) {
                    arr = generateAverageCase(n);
                } else {
                    arr = generateWorstCase(n);
                }
                
                // Perform bubble sort and collect metrics
                BubbleSortMetrics metrics = bubbleSort(arr);
                double timeInMs = metrics.executionTime / 1_000_000.0;
                
                System.out.printf("%-10d %-20.4f %-20d %-15d%n", 
                    n, timeInMs, metrics.comparisons, metrics.swaps);
            }
            
            System.out.println();
        }
        
        // Summary for copy to Excel/Google Sheets
        System.out.println("\n========================================");
        System.out.println("COPY TO EXCEL/GOOGLE SHEETS");
        System.out.println("========================================\n");
        System.out.println("n\tBest_Time\tBest_Comp\tBest_Swap\tAvg_Time\tAvg_Comp\tAvg_Swap\tWorst_Time\tWorst_Comp\tWorst_Swap");
        
        for (int n : sizes) {
            // Best Case
            int[] best = generateBestCase(n);
            BubbleSortMetrics bestMetrics = bubbleSort(best);
            
            // Average Case
            int[] avg = generateAverageCase(n);
            BubbleSortMetrics avgMetrics = bubbleSort(avg);
            
            // Worst Case
            int[] worst = generateWorstCase(n);
            BubbleSortMetrics worstMetrics = bubbleSort(worst);
            
            System.out.printf("%d\t%.4f\t%d\t%d\t%.4f\t%d\t%d\t%.4f\t%d\t%d%n",
                n,
                bestMetrics.executionTime / 1_000_000.0, bestMetrics.comparisons, bestMetrics.swaps,
                avgMetrics.executionTime / 1_000_000.0, avgMetrics.comparisons, avgMetrics.swaps,
                worstMetrics.executionTime / 1_000_000.0, worstMetrics.comparisons, worstMetrics.swaps
            );
        }
        
        System.out.println("\n========================================");
        System.out.println("ANALYSIS SUMMARY");
        System.out.println("========================================");
        System.out.println("Best Case (O(n)):");
        System.out.println("  - Already sorted, minimal comparisons, no swaps");
        System.out.println("  - Optimized bubble sort detects sorted array early\n");
        
        System.out.println("Average Case (O(n²)):");
        System.out.println("  - Random order, moderate comparisons and swaps");
        System.out.println("  - Performance between best and worst cases\n");
        
        System.out.println("Worst Case (O(n²)):");
        System.out.println("  - Reverse sorted, maximum comparisons and swaps");
        System.out.println("  - Every element must be swapped multiple times\n");
    }
}
