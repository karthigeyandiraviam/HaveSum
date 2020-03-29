package com.ddk;

/*
**  0 0 0 1
**  0 0 1 1
**  0 0 0 1
**  0 1 0 0
 */
public class FindIslands {
    final int[] columns = {-1, 0, 1, -1, 1, -1, 0, 1};
    final int[] rows =    {-1, -1, -1, 0, 0, 1, 1, 1};

    void buildVisited(int[][] arr, int row, int col, boolean[][] visited) {
        visited[row][col] = true;
        for ( int i = 0 ; i < columns.length ; i++ ) {
            int c = col+columns[i];
            int r = row+rows[i];

            if(isValid(arr, c, r, visited)) {
                buildVisited(arr, r, c, visited);
            }
        }
    }

    boolean isValid(int[][] arr, int col, int row, boolean[][] visited) {
        return col >= 0 && row >= 0 &&
                row < arr.length && col < arr[0].length &&
                arr[row][col] == 1 && !visited[row][col];
    }

    int findIsland(int[][] arr) {
        int numIsland = 0;
        boolean[][] visited = new boolean[arr.length][arr[0].length];

        for ( int i = 0 ; i < arr.length ; i++ ) {
            for (int j = 0; j < arr[i].length; j++) {
                if ( arr[i][j] == 1 && ! visited[i][j] ) {
                    buildVisited(arr, i, j, visited);
                    numIsland++;
                } else {
                    visited[i][j] = true;
                }
            }
        }
        return numIsland;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 1, 0, 1},
                {1, 0, 1, 1},
                {0, 0, 0, 1},
                {0, 1, 0, 0}
        };
        FindIslands fi = new FindIslands();
        System.out.println(fi.findIsland(arr));
    }
}
