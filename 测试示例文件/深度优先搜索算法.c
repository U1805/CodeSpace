#include <stdio.h>
#include <stdbool.h>

// 定义图的邻接表
#define MAX_VERTICES 6

int graph[MAX_VERTICES][MAX_VERTICES] = {
    {0, 1, 1, 0, 0, 0},
    {1, 0, 0, 1, 1, 0},
    {1, 0, 0, 0, 0, 1},
    {0, 1, 0, 0, 0, 0},
    {0, 1, 0, 0, 0, 1},
    {0, 0, 1, 0, 1, 0}
};

bool visited[MAX_VERTICES];

// 深度优先搜索函数
void dfs(int vertex) {
    visited[vertex] = true;
    printf("%d\n", vertex);

    for (int i = 0; i < MAX_VERTICES; ++i) {
        if (graph[vertex][i] && !visited[i]) {
            dfs(i);
        }
    }
}

// 使用示例
int main() {
    // 初始化visited数组
    for (int i = 0; i < MAX_VERTICES; ++i) {
        visited[i] = false;
    }

    dfs(0);
    return 0;
}
