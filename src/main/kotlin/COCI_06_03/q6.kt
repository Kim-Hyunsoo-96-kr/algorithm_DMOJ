package COCI_06_03

import java.io.*

fun q6(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06_03/q4_input.txt"
    val file = File(path)
    val stringBuilder = StringBuilder()
    try {
        file.forEachLine {
            stringBuilder.append(it)
            stringBuilder.append("\n") // 각 줄 끝에 줄바꿈 문자 추가
        }
    } catch (e: Exception) {
        println("파일을 읽는 도중 오류가 발생했습니다: ${e.message}")
    }
    val input = stringBuilder.toString()
    /** 입력에서 input 읽어오기 */
//    val bf = BufferedReader(InputStreamReader(System.`in`))
//    val input = bf.readText().trim()

    var n:Int
    var tanksPoints:MutableList<Pair<Int, Int>> = mutableListOf()

    input.trim().split("\n").also{ input ->
        n = input[0].toIntOrNull() ?: error("n: int casting fail")
        if(n < 3 || n > 500) error("n: out of range")
        if(input.size - 1 != n) error("tank count is not same n")
        input.subList(1, input.size).forEach { pointStr ->
            tanksPoints.add(pointStr.split(" ").let{ points ->
                if(points.size != 2) error("invalid pointStr")
                points.map{ point -> point.toIntOrNull() ?: error("point: int casting fail") }.let { it[0] to it[1] }
            })
        }
    }

    println(tanksPoints)
}