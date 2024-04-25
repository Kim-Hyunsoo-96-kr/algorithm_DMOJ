package COCI_06_02

import java.io.File

fun q5(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06_02/q5_input.txt"
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

    var r = 0
    var c = 0
    val map = mutableListOf<String>()
    input.trim().split("\n").also{ inputs ->
        inputs[0].trim().split(" ").map{ it.toIntOrNull() ?: error("invalid input") }.also{ r = it[0]; c = it[1] }
        inputs.subList(1, inputs.size).forEach {
            map.add(it)
        }
    }

    println(map)
}