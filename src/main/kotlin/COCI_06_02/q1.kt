package COCI_06_02

import java.io.File

fun q1(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06_02/q1_input.txt"
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

    val (r1, mean) = input.trim().split(" ")
        /** 입력이 int 타입으로 변환 될 수 있는지 확인 */
        .map{ it.toIntOrNull() ?: error("invalid input") }
        /** 입력으로 들어온 값들이 문제의 범위에 해당하는지 확인 */
        .also{ numbers -> if(!numbers.all{ it >= -1000 && it <= 1000 }) error("invalid input : out of range") }

    /** r2 = 2*mean-r1 */
    val r2 = 2 * mean - r1

    /** 출력 */
    println(r2)
}