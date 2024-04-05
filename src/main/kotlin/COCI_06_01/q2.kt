package COCI_06_01

import java.io.File

fun q2(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06_01/q2_input.txt"
    val file = File(path)
    val stringBuilder = StringBuilder()
    try{
        file.forEachLine {
            stringBuilder.append(it)
            stringBuilder.append("\n")
        }
    }catch(e:Exception){
        println("파일을 읽는 도중 오류가 발생했습니다: ${e.message}")
    }
    val input = stringBuilder.trim().toString()

    /** 입력에서 input 읽어오기 */
//    val bf = BufferedReader(InputStreamReader(System.`in`))
//    val input = bf.readText().trim()

    /** input을 정수로 변환 및 vali 처리 */
    val r = input.toIntOrNull() ?: error("invalid input")
    if(r > 10000 || r < 0) error("invalid input")

    /** r인 원의 넓이 구하기 (소수점 6자리까지 표현) */
    val area = "%.6f".format(r*r*Math.PI)

    /** 유클리드 원 넓이 구하기 */
    val eArea = 2*r*r

    /** 출력 */
    println(area)
    println(eArea)
}