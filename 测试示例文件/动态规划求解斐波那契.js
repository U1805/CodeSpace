// 动态规划求解斐波那契数列第 n 项
function fibonacci(n) {
    let dp = [];
    dp[0] = 0;
    dp[1] = 1;
  
    for (let i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
  
    return dp[n];
  }
  
  // 使用示例
  console.log(fibonacci(6)); // 输出第6项斐波那契数列的值
  