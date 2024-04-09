package COCI_06_2

import java.io.File

fun q4(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06_02/q4_input.txt"
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

    val n = input.trim().toIntOrNull()?.also{
        if(it < 3 || it > 100) error("input is out of range")
    } ?: error("invalid input")


}