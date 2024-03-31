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

    /** output의 높이는 5로 고정, 각 라인을 빈 String으로 초기화 */
    val lines = mutableListOf(
        "..#..",
        ".#.#.",
        "#.${input[0]}.#",
        ".#.#.",
        "..#.."
    )

    fun peterPan(startIdx:Int, c:String, isWendyNext:Boolean){
        lines[0] = lines[0].substring(0, startIdx) + "..#.."
        lines[1] = lines[1].substring(0, startIdx) + ".#.#."
        lines[2] = if(isWendyNext) lines[2] + ".$c.#" else lines[2].substring(0, startIdx) + "#.$c.#"
        lines[3] = lines[3].substring(0, startIdx) + ".#.#."
        lines[4] = lines[4].substring(0, startIdx) + "..#.."
    }
    fun wendy(startIdx:Int, c:String){
        lines[0] = lines[0].substring(0, startIdx) + "..*.."
        lines[1] = lines[1].substring(0, startIdx) + ".*.*."
        lines[2] = lines[2].substring(0, startIdx) + "*.$c.*"
        lines[3] = lines[3].substring(0, startIdx) + ".*.*."
        lines[4] = lines[4].substring(0, startIdx) + "..*.."
    }
    /** input을 순회하면서 index % 3 == 2인 경우 wendy frame 적용, 나머지는 perterpan frame 적용 */
    input.substring(1).forEachIndexed{ idx, c ->
        val startIdx = lines[0].lastIndex
        when(idx % 3){
            0 -> peterPan(startIdx, c.toString(),false)
            1 -> wendy(startIdx, c.toString())
            2 -> peterPan(startIdx, c.toString(),true)
        }
    }

    lines.forEach {
        println(it)
    }
}