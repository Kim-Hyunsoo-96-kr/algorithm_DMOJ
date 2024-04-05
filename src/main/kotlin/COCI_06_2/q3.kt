package COCI_06_2

import java.io.File

fun q3(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06_03/q2_input.txt"
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

    var n1 = 0
    var n2 = 0
    var row1 = ""
    var row2 = ""
    var t = 0

    input.trim().split("\n").also{ inputs ->
        inputs[0].split(" ").map { it.toIntOrNull() ?: error("number int casting fail") }.also{ numbers ->
            if(numbers.size != 2) error("count of numbers is not 2")
            n1 = numbers[0]
            n2 = numbers[1]
        }
        row1 = inputs[1]
        row2 = inputs[2]
        t = inputs[3].toIntOrNull()?.also{
            if( it<0 || it>50 ) error("time is out of range")
        } ?: error("time int casting fail")
    }
}