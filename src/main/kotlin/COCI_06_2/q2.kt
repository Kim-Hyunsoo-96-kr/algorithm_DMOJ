package COCI_06_2

import java.io.File

fun q2(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06_02/q2_input.txt"
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


    /** 2줄의 입력을 각각 numberStr, abcStr에 저장 */
    val (numberStr, abcStr) = input.trim().split("\n")

    /** numberStr 문자열을 숫자의 List로 변환 및 오름차순으로 정렬, 그 과정에서 vali 수행 */
    val numbers = numberStr.split(" ").map { it.toIntOrNull() ?: error("number int casting fail")}.sorted().also{ numbers ->
        if(numbers.size != 3) error("count of numbers is out of range")
        if(!numbers.all{ it <= 100 }) error("number is out of range")
    }

    /** abcStr 문자열을 통해 numbers의 순서인 indexs를 얻어냄 */
    val indexs = abcStr.map {
        /** 아스키 코드를 사용 */
        it - 'A'
    }
    println(indexs)

    /** indexs를 순회하며 indexs의 요소의 숫자에 해당하는 인덱스를 가지는 numbers를 찾고 문자열로 만들어 출력 */
    println(indexs.map { numbers[it] }.joinToString(" "))
}