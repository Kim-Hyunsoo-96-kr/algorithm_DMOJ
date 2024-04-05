package COCI_06_01

import java.io.File

fun q5(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06_01/q5_input.txt"
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
}