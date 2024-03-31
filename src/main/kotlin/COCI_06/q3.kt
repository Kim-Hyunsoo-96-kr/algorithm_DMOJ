package COCI_06

import java.io.File

fun q3(){
    /** 파일에서 input 읽어오기 */
    val path = "src/main/resources/COCI_06/q3_input.txt"
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

    /** input의 길이를 통해 output의 길이를 알 수 있다. (output length = (input length -1) * 4 + 5 */
    /** output의 높이는 5로 고정, 각 라인을 빈 String으로 초기화 */
    val line1 = ""
    val line2 = ""
    val line3 = ""
    val line4 = ""
    val line5 = ""

    /** input을 순회하면서 index % 3 == 2인 경우 wendy frame 적용, 나머지는 perterpan frame 적용 */
    input.forEachIndexed { idx, c ->
        if(idx == 0)
    }


    /** peter pan frame 을 만들어 주는 함수 */
    fun peterPanFrame(c:String):MutableList
}