import java.io.*;
import java.util.*;

public class FloodItGreedySolver {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());

        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = br.readLine().trim().toCharArray();
        }

        List<int[]> moves = new ArrayList<>();
        boolean[][] visited = new boolean[rows][cols];

        // Main algorithm: Find regions greedily
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) {
                    moves.add(new int[]{i, j, floodFill(grid, visited, i, j)});
                }
            }
        }

        System.out.println(moves.size());
        for (int[] move : moves) {
            System.out.printf("%d %d %d%n", move[0], move[1], move[2]);
        }
    }

    private static int floodFill(char[][] grid, boolean[][] visited, int x, int y) {
        int rows = grid.length;
        int cols = grid[0].length;
        char color = grid[x][y];
        int area = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            area++;

            for (int[] dir : directions) {
                int nx = cell[0] + dir[0];
                int ny = cell[1] + dir[1];

                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols &&
                        !visited[nx][ny] && grid[nx][ny] == color) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        return area;
    }
}
