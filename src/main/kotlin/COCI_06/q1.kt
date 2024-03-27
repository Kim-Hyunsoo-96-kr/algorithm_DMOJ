package COCI_06

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun q1(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06/q1_input.txt"
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

    /** input을 정수 리스트 list로 변환 */
    val list = input.trim().split("\n").map{ it.toInt() }
    /** modulo 연산 결과를 저장할 set 선언 */
    val set = mutableSetOf<Int>()
    /** list를 순회하며 42 modulo 연산 */
    list.forEach {
        /** 연산 결과를 set에 저장 */
        set.add(it % 42)
    }
    /** set의 size를 출력 */
    println(set.size)

    /** 맹실장님 풀이 */
    println(list.fold(mutableSetOf<Int>()){acc, i ->
        if(i<0 || i>1000) throw Throwable("invalid range 1<=i<=1000: $i")
        acc.add(i % 42)
        acc
    }.size)
}