package io.github.studio22.probably.calculations

class CombinatoricsCalc {
    private fun factorial(n: Int): Int {
        if (n == 0 || n == 1) {
            return 1
        }

        var res = 1
        for (i in 1..n) {
            res *= i
        }
        return res
    }

    fun combinations(n: Int, k: Int): Int {
        return factorial(n) / (factorial(k) * factorial(n - k))
    }
}